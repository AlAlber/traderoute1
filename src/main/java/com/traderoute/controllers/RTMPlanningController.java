package com.traderoute.controllers;
import com.traderoute.*;
import com.traderoute.cells.CustomNonEditCell;
import com.traderoute.data.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
//<a href="#{@link}">{@link URL}</a>

/**
 *On this page a user can type in information about their Product for a
 * a specific Retailer. In the brandNameBox a user may select the brand name of
 * a specific Product and then select the product class. On this page the user enters
 * Product specific information about a Retailer in the yearOneStoreCountField,
 * everydayGpmField and spoilsFeesField, which can later be displayed in the
 * Product Class Reporting page.
 *
 * The tables consist of RTMOption Objects which allow the user to input information, first
 * the name of the of the Route-To-Market option(ex. Direct-To-Customer, where user directly transports
 * their goods to a Retailer), then the Slotting Investment, freight cost per unit, how much the first
 * Receiver pays per unit(for ex. KeHE), second, third, fourth if they exist. The table automatically
 * calculates the Landed Store Cost from these 4 columns and if possible calculates the resulting everyday
 * retail price and a suggestion for the overridden retail price which the Retailer usually will round
 * to 0.09(ex. if resulting everyday retail calc'd= 6.43 -> resulting everyday override= 6.49).
 * Once the user enters a rough estimate of the weekly velocity Unit/Flavor/Sku/Week, the second
 * table can calculate unit velocities at different retail prices through the price elasticity
 * index entered by the user on the Products Pricing page. The rest of the calculations are explained
 * in method docs. The graphs compare all the RTMOption for specific fields.
 *
 * @author Alex Alber
 */
public class RTMPlanningController implements Initializable {

    /**
     * All divisions except cell specific divisions should be 4.
     */
    private final int divisionScale = 4;
    /**
     * First Route-To-Market Planning Table, mostly editable.
     */
    @FXML
    private TableView<RTMOption> rtmPlanningTable1;
    /**
     * Column for the name of the Route-To-Market (Editable).
     */
    @FXML
    private TableColumn<RTMOption, String> rtmNameCol1;
    /**
     * Column for the slotting investment with this RTMOption (Editable) .
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> slottingPerSkuCol;
    /**
     * Column for the freight cost per unit with this RTMOption (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> freightOutPerUnitCol;
    /**
     * Column for the cost the first receiver pays with this RTMOption
     * (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> firstReceiverCol;
    /**
     * Column for the cost the second receiver pays with this RTMOption
     * (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> secondReceiverColumn;

    /**
     * Column for the cost the third receiver pays with this RTMOption
     * (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> thirdReceiverColumn;

    /**
     * Column for the cost the fourth receiver pays with this RTMOption
     * (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourthReceiverColumn;

    /**
     * Column for the landed Store Cost of this product with this
     * RTMOption (Not Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> landedStoreCostColumn;

    /**
     * Column for the resulting Everyday Retail Cost of this product
     * with this RTMOption (Not Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> everydayRetailCalcdCol;

    /**
     * Column for the resulting Everyday Retail Override where user
     * can input the final price of the product, likely rounded to X.X9
     * (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> everydayRetailOverrideCol;

    /**
     * Second planning table with RTMOption items (ALL NOT EDITABLE).
     */
    @FXML
    private TableView<RTMOption> rtmPlanningTable2;

    /**
     * Second RTM Option Name column.
     */
    @FXML
    private TableColumn<RTMOption, String> rtmNameColumn2;

    /**
     * Elasticized unit velocity, where unit velocities are adjusted based on
     * the price elasticity of the product.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> elasticizedUnitVelocityColumn;

    /**
     * Column for the Annual Volume Sold Per Sku based on unit Velocity on year
     * one store count.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> annualVolumePerSkuColumn;

    /**
     * Column showing the expected time period it will take to receive back
     * the slotting investment.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodColumn;

    /**
     * Column showing how much the user collects after accounting for spoils,
     * fees and freight cost.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsPerUnitCol;

    /**
     * Column showing how much unspent trade money is available per unit
     * with this RTMOption.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitColumn;
    /**
     * Column showing equivalized gross profit per sku in four years with
     * this RTMOption.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuColumn;
    /**
     * Column showing equivalized gross profit per unit in four years with
     * this RTMOption.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitColumn;
    /**
     * Retailer is main object between scenes, right now still has default
      * value. //TODO
     */
    private SimpleObjectProperty<Retailer> retailer = new SimpleObjectProperty<>();

    //
//                    new Retailer("ahold",
//                                 RTMPlanningController.getRetailerProducts(),
//                            0, new BigDecimal("40"),
//                            158, new BigDecimal("3.0"))
    /**
     * TextField to insert Store Count of this RetailerProduct (specific Product being sold by retailer)
     * in first year.
     */
    @FXML
    private TextField yearOneStoreCountField;
    /**
     * TextField to insert Everyday Gross Profit Margin Percentage of
     * RetailerProduct.
     */
    @FXML
    private TextField everydayGpmField;

    /**
     * TextField to insert percentage of Spoils and Fees of RetailerProduct.
     */
    @FXML
    private TextField spoilsFeesField;

    /**
     * TextField to insert the Weekly Velocity U/F/S/W of product at the minimum price
     * in the everyday retail override column.
     */
    @FXML
    private TextField weeklyUfswAtMinField;
    /**
     * Label that denotes the max of the retail override column.
     */
    @FXML
    private Label maxOverrideLabel;

    /**
     * Labels for column headers.
     */
    @FXML
    private final Label rtmNameColumnLabel =
            new Label("Route to market options");
    @FXML
    private final Label rtmNameColumnLabel2 =
            new Label("Route to market options");
    @FXML
    private final Label slottingPerSkuLabel =
            new Label("Slotting Per Sku");
    @FXML
    private final Label freightOutPerUnitLabel =
            new Label("Freight Out Per Unit");
    @FXML
    private final Label firstReceiverLabel =
            new Label("First Receiver Pays");
    @FXML
    private final Label secondReceiverLabel =
            new Label("Second Receiver Pays");
    @FXML
    private final Label thirdReceiverLabel =
            new Label("Third Receiver Pays");
    @FXML
    private final Label fourthReceiverLabel =
            new Label("Fourth Receiver Pays");
    @FXML
    private final Label landedStoreCostLabel =
            new Label("Landed Store Cost");
    @FXML
    private final Label everyDayRetailCalcdLabel
            = new Label("Calculated Everyday Retail");
    @FXML
    private final Label everyDayRetailOverrideLabel
            = new Label("Override Everyday Retail");
    @FXML
    private final Label elasticizedUnitVelocityLabel
            = new Label("Elasticized Unit Velocity ");
    @FXML
    private final Label annualVolumePerSkuLabel
            = new Label("Annual Volume Per Sku ");
    @FXML
    private final Label slottingPaybackPeriodLabel =
            new Label("Slotting Payback Period");
    @FXML
    private final Label postFreightPostSpoilsLabel =
            new Label("Post Freight & Spoils We Collect");
    @FXML
    private final Label unspentTradePerUnitLabel =
            new Label("Unspent Trade Per Unit");
    @FXML
    private final Label fourYearEqGpPerSkuLabel =
            new Label("4-Year EQ GP $ Per Sku");
    @FXML
    private final Label fourYearEqGpPerUnitLabel =
            new Label("4-Year EQ GP $ Per Unit");

    /**
     * Tooltips to be added to Column Header Labels.
     */
    @FXML
    private final Tooltip rtmNameColumnTip =
            new Tooltip("Please enter the most likely 'route-to-market'"
                    + " options to get the product to the market.");
    @FXML
    private final Tooltip slottingPerSkuTip =
            new Tooltip("Please enter the required slotting (placement)"
                    + " investment specific to this 'route-to-arket option.'");
    @FXML
    private final Tooltip freightOutPerUnitTip =
            new Tooltip("If we're responsible for the cost of shipping for"
                    + " this route-to -market option, please enter in the 'per"
                    + " unit cost' of this 'Freight-Out.' For F.O.B (Pick-up)"
                    + " Customers, Freight-Out equals $0.");
    @FXML
    private final Tooltip firstReceiverTip =
            new Tooltip("The Per Unit Cost the First Receiver pays us,"
                    + " typically the Unit List Cost or the Unit F.O.B");
    @FXML
    private final Tooltip secondReceiverTip =
            new Tooltip("The Per Unit Cost the Second Receiver pays"
                    + " to the First Receiver.");
    @FXML
    private final Tooltip thirdReceiverTip =
            new Tooltip("The Per Unit Cost the Third Receiver"
                    + " pays to the Second Receiver.");
    @FXML
    private final Tooltip fourthReceiverTip =
            new Tooltip("The Per Unit Cost the Fourth Receiver"
                    + " pays to the Third Receiver.");
    @FXML
    private final Tooltip landedStoreCostTip =
            new Tooltip("The Per Unit Cost the Retail OutLet (Last"
                    + " Receiver pays prior to applying the Required GPM%"
                    + " to establish the Everyday Retail.");
    @FXML
    private final Tooltip everyDayRetailCalcdTip =
            new Tooltip("The auto-calculated Resulting Everyday"
                    + " Retail given the Landed Store Cost and Required "
                    + "Gross Profit Margin %.");
    @FXML
    private final Tooltip everyDayRetailOverrideTip =
            new Tooltip("Please enter the REALISTIC Everyday Retail"
                    + " considering the auto-calculated retail to the left.");
    @FXML
    private final Tooltip elasticizedUnitVelocityTip =
            new Tooltip("For each route-to-market option provided,"
                    + " these are the Estimated Weekly Unit Velocities given"
                    + " the Product Class's Price Elasticity Multiple (for"
                    + " each X% increase in Unit Price there is a Y% decrease"
                    + " in Units Sold");
    @FXML
    private final Tooltip annualVolumePerSkuTip =
            new Tooltip("For each route-to-market option provided,"
                    + " these are the Estimated Annual Volumes Per SKU "
                    + "(accounting for price elasticities)");
    @FXML
    private final Tooltip slottingPaybackPeriodTip =
            new Tooltip("If Slotting is a consideration these"
                    + " are the Payback Periods (in years) for each "
                    + "route-to-market option provided to recoup the "
                    + "initial Slotting Investment.");
    @FXML
    private final Tooltip postFreightPostSpoilsTip =
            new Tooltip("For each route-to-market option provided,"
                    + " the Per Unit Rate we collect after which Freight"
                    + " Costs and Spoils are accounted (but prior to Trade"
                    + " Spending).");
    @FXML
    private final Tooltip unspentTradePerUnitTip =
            new Tooltip("Unspent Trade Per Unit");
    @FXML
    private final Tooltip fourYearEqGpPerSkuTip =
            new Tooltip("4-Year Equivalized Gross Profit $ Per Sku");
    @FXML
    private final Tooltip fourYearEqGpPerUnitTip =
            new Tooltip("4-Year Equivalized Gross Profit $ Per Unit");

    /**
     * Combobox where user can select the brand name of a Product.
     */
    @FXML
    private ComboBox<Product> brandNameBox;
    /**
     * ComboBox where user can select brand name specific product classes.
     */
    @FXML
    private ComboBox<Product> productClassBox;


    /**
     * Displays unit list cost of the selected Product.
     * (General Product information is inserted on Product pricing
     * page by user)
     */
    @FXML
    private Label listLabel;
    /**
     * Displays unit F.O.B. cost of the selected Product.
     */
    @FXML
    private Label fobLabel;
    /**
     * Displays unit net1 goal of the selected Product.
     */
    @FXML
    private Label net1GoalLabel;
    /**
     * Displays the elasticity ratio of the selected Product.
     */
    @FXML
    private Label elasticityRatioLabel;

    /**
     * BarCharts to display relevant table data. X-Axis has
     * RTM Option Name, Y-Axis the respective value.
     */
    @FXML
    private BarChart<?, ?> landedStoreCostChart;
    @FXML
    private BarChart<?, ?> everydayRetailCalcdChart;
    @FXML
    private BarChart<?, ?> elasticizedUnitVelocityChart;
    @FXML
    private BarChart<?, ?> annualVolumePerSkuChart;
    @FXML
    private BarChart<?, ?> slottingPaybackPeriodChart;
    @FXML
    private BarChart<?, ?> unspentTradePerUnitChart;
    @FXML
    private BarChart<?, ?> postSpoilsPostFreightChart;
    @FXML
    private BarChart<?, ?> fourYearEqGpPerSkuChart;
    @FXML
    private BarChart<?, ?> fourYearEqGpPerUnitChart;

    /**
     * Called when new page is created from FXML.
     * - Sets Cell Value factories to reflect RTM Option data.
     * - Sets unique item brand names to brandnamebox
     * - Assigns Converters to brandNameBox, productClassBox
     * - Makes year One Store Count accept only Integers.
     * - Assigns TextFormatter with converter and input filter for
     * number values in double format (ex. 0.00) in everydayGpmField,
     * spoilsFeesField, weeklyUfswAtMinField.
     * - sets default RTMOption items for both tables.
     * - sets data to charts.
     * - Sets editable and non-editable Columns and Tables.
     * - Adds listeners to rows for chart updates.
     * - Sets Cell Factories, which determine how Column cells are displayed
     * for both tables.
     * @param url default javafx param in initialize method.
     * @param resourceBundle default javafx param in initialize method.
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {


        //Set up cell value factories
        setCellValueFactories();

        // Set unique values for brandcombobox and set converters for both brand and productCombobox

        brandNameBox.setItems(
                getUniqueBrandNames(MenuController.getExampleProducts()));
        brandNameBox.setConverter(getBrandComboboxConverter());
        productClassBox.setConverter(getProductComboboxConverter());


        // Restrict input fields to only accept text in Integer or Double format
        yearOneStoreCountField.textProperty().addListener(
            new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String>
                observable, final String oldValue, final String newValue) {
                if (!newValue.matches("\\d*")) {
                    yearOneStoreCountField.setText(newValue
                            .replaceAll("[^\\d]", ""));
                }
            }
        });
        everydayGpmField.setTextFormatter(new TextFormatter<>(
                getDoubleInputConverter(), 0.0, getDoubleInputFilter()));
        spoilsFeesField.setTextFormatter(new TextFormatter<>(
                getDoubleInputConverter(), 0.0, getDoubleInputFilter()));
        weeklyUfswAtMinField.setTextFormatter(new TextFormatter<>(
                getDoubleInputConverter(), 0.0, getDoubleInputFilter()));

        //Set dummy data
        rtmPlanningTable1.setItems(getRTMOptions());
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());


        // Set Bar Chart
        landedStoreCostChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(1)}));
        everydayRetailCalcdChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(2)}));
        elasticizedUnitVelocityChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(3)}));
        annualVolumePerSkuChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(4)}));
        slottingPaybackPeriodChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(5)}));
        postSpoilsPostFreightChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(6)}));
        unspentTradePerUnitChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(7)}));
        fourYearEqGpPerSkuChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(8)}));
        fourYearEqGpPerUnitChart.setData(FXCollections.observableArrayList(
                new XYChart.Series[]{getChartData(9)}));

        // Add Labels to list, give them tooltips
        setToolTipsTable1And2();

        //Set editable columns
        rtmPlanningTable1.setEditable(true);
        landedStoreCostColumn.setEditable(false);
        everydayRetailCalcdCol.setEditable(false);


        //Set up event listerners
        setUpListeners();

        // Set cell factories
        setCellFactories();


    }
    /**
     * Set Up listeners in all the rows (which get called to make the
     * calculations.Also sets up listeners for everyDayGPM,landed
     * store Cost Property. updateChart method takes boolean arguments
     * lSC-landedstorecost, eRC-everydayRetailCalcd, e
     */
    public void setUpListeners() {
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setupListeners();
            row.landedStoreCostProperty().addListener((
                (arg, oldVal, newVal) -> {
                updateChart(true, false, false,
                        false, false, false, false,
                        false, false);
            }));
            row.RTMNameProperty().addListener(((arg, oldVal, newVal) -> {
                updateChart(true, true, true,
                        true, true, true,
                        true, true, true);
            }));
            row.everydayRetailCalcdProperty().
                    addListener(((arg, oldVal, newVal) -> {
                maxOverrideLabel.setText("of $" + getMinOverride());
                updateChart(false, true, false,
                        false, false, false, false,
                        false, false);
            }));
            row.resultingEverydayRetailOverrideProperty()
                    .addListener(((arg, oldVal, newVal) -> {
                row.setMinOverride(getMinOverride());
                rtmPlanningTable1.refresh();
                row.updateElasticizedUnitVelocity();
                maxOverrideLabel.setText("of $" + getMinOverride());
                rtmPlanningTable1.refresh();
            }));
        }
    }
    /**
    * Get Unique list of brand names from Product list.
     * @param products contains list of all Products
     *       ProductsPricingController.
     * @return uniqueBrandNames list of unique Products with
     * correct brand name- can later be compared in corresponding product
     * classes method.
     */
    public static  ObservableList<Product> getUniqueBrandNames(
            final ObservableList<Product> products) {
        ObservableList<Product> uniqueBrandNames =
                FXCollections.observableArrayList();
        ObservableList<String> uniqueBrandNameStrs =
                FXCollections.observableArrayList();
        for (Product product : products) {
            if (!uniqueBrandNameStrs.contains(product.getBrandName())) {
                uniqueBrandNameStrs.add(product.getBrandName());
                uniqueBrandNames.add(product);
            }
        }
        return uniqueBrandNames;
    }
    /**
     * @param products a sample list of products that have the right brand
     *                 name.
     * @return ObservableList<Product> all products with correct brandName,
     * which user has selected on the brandNameBox.
     */
    public static ObservableList<Product> getCorrectProductClasses(
            final ObservableList<Product> products) {
        ObservableList<Product> correspondingProductClasses =
                FXCollections.observableArrayList();
        //Set up product combobox and make it display product class
        for (Product product : products) {
            if (product.getBrandName().equals(product.getBrandName())) {
                correspondingProductClasses.add(product);
            }
        }
        return correspondingProductClasses;
    }

    /**
    * Listener reacts to event on brandNameBox.
     * Sets Product info labels and sets items in product-
     * ClassBox
     */
    public void changeBrandComboboxEvent() {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        listLabel.setText("List = $");
        fobLabel.setText("F.O.B. = $");
        net1GoalLabel.setText("Net 1 Goal = $");
        elasticityRatioLabel.setText(
                "Elasticity Ratio = +1% Price :  % Volume");
        ObservableList<Product> productsWithBrandName =
                getCorrectProductClasses(
                        MenuController.getExampleProducts());
        productClassBox.setItems(productsWithBrandName);
    }
    /**
    * A different option was chosen from the product combo box now
     * retailerProduct needs to be updated, labels need to be updated
     * and all the RTM Option's products need to be updated (//TODO
     * this functionality could be done better)
     */
    public void changeProductComboboxEvent() {

        Product selectedProduct = productClassBox
                .getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            productClassBox.setPromptText("Now Select a Product Class");
        } else {
            listLabel.setText("List = $" + selectedProduct.getUnitListCost());
            fobLabel.setText("F.O.B. = $" + selectedProduct.getUnitFobCost());
            net1GoalLabel.setText("Net 1 Goal = $"
                    + selectedProduct.getUnitNet1Goal());
            elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :"
                    + selectedProduct.getElasticityMultiple() + "% Volume");
            for (RTMOption row : rtmPlanningTable1.getItems()) {
                row.setProduct(selectedProduct);
            }
            updateRetailerProduct(selectedProduct);
        }
    }

    /**
     * This method return the index of the selected product from
     * the product class box and returns its index within the
     * RetailerProducts of the Retailer.
     * @param product product from the product class Box
     * @return index of the specific Product within the RetailerProduct's
     * of the retailer
     */
    public int getProductIndex(final Product product) {
        // create addProduct method in product page
        int productIndex = -1;
        for (RetailerProduct retailerProduct
                : getRetailer().getRetailerProducts()) {
            if (retailerProduct.getProduct().equals(product)) {
                productIndex = getRetailer().getRetailerProducts()
                        .indexOf(retailerProduct);
            }
        }
        return productIndex;
        // TEMPORARY
    }
    /**
     * Updates RetailerProduct, so all tables need to be updated,
     * all textfields,labels (except column header labels) and charts.
     * @param product Product given in order to select or add
     *                RetailerProduct to Retailer.
     */

    public void updateRetailerProduct(final Product product) {
        int productIndex = getProductIndex(product);
        retailer.get().setcurrentRetailerProductIndex(productIndex);

        // TEMPORARILY GET PRODUCT INDEX THROUGH RETAILERPRODUCTS
        rtmPlanningTable1.setItems(getRetailer().getRetailerProducts()
                .get(productIndex).getRtmOptions());
        rtmPlanningTable2.setItems(getRetailer().getRetailerProducts()
                .get(productIndex).getRtmOptions());
        rtmPlanningTable2.refresh();
        updateChart(false, false, false, false,
                true, true, true, true, true);
//        this.retailer.set(retailer);
        ObservableList<RetailerProduct> retailerProducts =
                getRetailer().getRetailerProducts();
        RetailerProduct currentRetailerProduct =
                retailerProducts.get(getRetailer()
                        .getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions =
                currentRetailerProduct.getRtmOptions();
        this.rtmPlanningTable1.setItems(currentRtmOptions);
        setUpListeners();
        //CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION
        // INSTEAD OF PRODUCT SELECTION
        this.rtmPlanningTable2.setItems(currentRtmOptions);

        listLabel.setText("List = $" + currentRetailerProduct
                .getProduct().getUnitListCost());
        fobLabel.setText("F.O.B. = $" + currentRetailerProduct
                .getProduct().getUnitFobCost());
        net1GoalLabel.setText("Net 1 Goal = $" + currentRetailerProduct
                .getProduct().getUnitNet1Goal());
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :"
                + currentRetailerProduct.getProduct()
                .getElasticityMultiple() + "% Volume");
        // Stuff that should be implemented differently
        this.weeklyUfswAtMinField.setText(currentRtmOptions.get(0)
                .getWeeklyUSFWAtMin().toString());
        for (RTMOption row: rtmPlanningTable1.getItems()) {
            row.setProduct(currentRetailerProduct.getProduct());
            row.setYearOneStoreCount(getRetailer().getYearOneStoreCount());
            row.setEverydayGPM(getRetailer().getEverydayGPM());
            row.setSpoilsAndFees(getRetailer().getSpoilsFees()
                    .divide((new BigDecimal("100")), divisionScale, RoundingMode
                            .HALF_UP));
            maxReceivers(row);
            row.updateResultingEverydayRetailCald();
            updateChart(true, true, true, true, true,
                    true, true, true, true);
        }
        rtmPlanningTable2.setVisible(false);
        rtmPlanningTable2.setVisible(true);
        rtmPlanningTable2.refresh();


    };

    /**
    * @return minimum value from override column
     */
    public BigDecimal getMinOverride() {
        BigDecimal smallest = new BigDecimal("100000000000");
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            BigDecimal currentNumber = row.getResultingEverydayRetailOverride();
            if (currentNumber.compareTo(smallest) < 0 && currentNumber
                    .compareTo(new BigDecimal("0.0")) > 0) {
                smallest = currentNumber;
            }
        }
        return smallest;
    }
    /**
    * Get elasticized velocities from weeklyUSFWAtMin Field and
     * refresh second table to see results from row listeners.
     * update charts.
    */
    public void changeWeeklyUSFWAtMinEvent() {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        for (RTMOption row : rtmPlanningTable2.getItems()) {
            row.setMinOverride(getMinOverride());
            row.setWeeklyUSFWAtMin(getWeeklyUSFWAtMin());
        }
        rtmPlanningTable2.refresh();
        updateChart(false, false, true, true,
                true, true, true, true, true);
    }
    /**
    * Get elasticized velocities from weeklyUSFWAtMin Field  and do
     * calculation if possible.
     * @param editedCell Cell that edited in everyday Retail Override Column.
    */
    public void changeOverrideEvent(
            final TableColumn.CellEditEvent editedCell) {
        System.out.println("please get here at least");
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setResultingEverydayRetailOverride(
                new BigDecimal(editedCell.getNewValue().toString()));
        for (RTMOption row : rtmPlanningTable2.getItems()) {
            row.setMinOverride(getMinOverride());
        }
        System.out.println(rtmOptionSelected.getRTMName() + ", elasticized= "
                + rtmOptionSelected.getElasticizedUnitVelocity());
        System.out.println(rtmOptionSelected.getRTMName()
                + ", annual Volume= " + rtmOptionSelected
                .getAnnualVolumePerSku());
        rtmPlanningTable2.refresh();
        updateChart(false, false, true, true,
                true, true, true, true, true);
    }

    /**
    * Year One Store Count changed event. Set to yearOneStoreCount Field in
     * FXML. Sets Year One Store Count Field in RTMOptions. Also updates charts.
    */
    public void changeYearOneStoreCount() {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        for (RTMOption row : rtmPlanningTable2.getItems()) {
            row.setYearOneStoreCount(getYearOneStoreCount());
        }
        rtmPlanningTable2.refresh();
        updateChart(false, false, false, true,
                true, true, true, true, true);
    }
    /**
    * //TODO Set RetailerProduct EverydayGpm.
     * Set RTMProduct EverydayGpm in rows which calls listeners, updateChart.
    */
    public void changeEveryDayGpmCellEvent() {
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setEverydayGPM(this.getEveryDayGpm());
        }
        updateChart(false, true, false, false,
                false, false, false, false, false);
    }
    /**
     * //TODO Set RetailerProduct Spoils Fees.
    * Set spoils fees in row and update necessary values.
     * spoils fees is sent as decimal percentage to RTMOption.
    */
    public void changeSpoilsAndFeesEvent() {
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setSpoilsAndFees(getSpoilsAndFees()
                    .divide((new BigDecimal("100")), divisionScale, RoundingMode
                            .HALF_UP));
        }
        updateChart(false, false, false, false,
                true, true, true, true, true);
    }
    /**
     * Slotting per Sku input registered from tableview cell, update
     * second table and relevant charts.
     * @param editedCell cell edited by user.
     */
    public void changeSlottingPerSkuCellEvent(
            final TableColumn.CellEditEvent editedCell) {
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setSlottingPerSku(
                new BigDecimal(editedCell.getNewValue().toString()));
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        rtmPlanningTable2.refresh();
        updateChart(false, false, false,
                false, true, true, true,
                true, true);
    }

    /**
     * Freight out per unit (Money that User pays for transporting goods)
     * input registered from tableview cell, set value in RTMOption which
     * calls listeners.
     * @param editedCell cell edited by user.
     */
    public void changeFreightOutPerUnitCellEvent(
            final TableColumn.CellEditEvent editedCell) {
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setFreightOutPerUnit(
                new BigDecimal(editedCell.getNewValue().toString()));
        updateChart(false, false, false,
                false, true, true, true,
                true, true);
    }
    /**
     * First Receiver Pays (Money that first Receiver (ex. KeHE)
     * pays per unit) input registered from tableview cell, set
     * value in RTMOption which calls listeners.
     * @param editedCell cell edited by user.
     */
    public void changeFirstReceiverCellEvent(
            final TableColumn.CellEditEvent editedCell) {
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setFirstReceiver(
                new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(rtmOptionSelected);
        updateChart(false, false, false, false,
                true, true, true, true, true);
    }
    /**
     * Second Receiver Pays (Money that second Receiver (ex. KeHE)
     * pays per unit) input registered from tableview cell, set
     * value in RTMOption which calls listeners.
     * @param editedCell cell edited by user.
     */
    public void changeSecondReceiverCellEvent(
            final TableColumn.CellEditEvent editedCell) {
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setSecondReceiver(
                new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(rtmOptionSelected);
    }
    /**
     * Third Receiver Pays (Money that third Receiver (ex. KeHE)
     * pays per unit) input registered from tableview cell, set
     * value in RTMOption which calls listeners.
     * @param editedCell cell edited by user.
     */
    public void changeThirdReceiverCellEvent(
            final TableColumn.CellEditEvent editedCell) {
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setThirdReceiver(new BigDecimal(editedCell
                .getNewValue().toString()));
        maxReceivers(rtmOptionSelected);
    }
    /**
     * Fourth Receiver Pays (Money that fourth Receiver (ex. KeHE)
     * pays per unit) input registered from tableview cell, set
     * value in RTMOption which calls listeners.
     * @param editedCell cell edited by user.
     */
    public void changeFourthReceiverCellEvent(
            final TableColumn.CellEditEvent editedCell) {
        RTMOption rtmOptionSelected = rtmPlanningTable1
                .getSelectionModel().getSelectedItem();
        rtmOptionSelected.setFourthReceiver(new BigDecimal(editedCell
                .getNewValue().toString()));
        maxReceivers(rtmOptionSelected);
    }
    /**
     * Calculates the maximum value from first,second,third and
     * fourth receiver column values for a specifc RTMOption.
     * @param selectedOption RTMOption where max value from receivers
     *                       needs to be calculated.
     */
    public void maxReceivers(final RTMOption selectedOption) {
        BigDecimal maxReceivers = selectedOption.getFirstReceiver()
                .max(selectedOption.getSecondReceiver()
                .max(selectedOption.getThirdReceiver()
                .max(selectedOption.getFourthReceiver())));
        if (maxReceivers.compareTo(new BigDecimal("0.0")) > 0) {
            selectedOption.setLandedStoreCost(maxReceivers);
        }
    }
    /**
     * @return Integer value of the text in the yearOneStoreCount
     * field.
     */
    public int getYearOneStoreCount() {
        if (yearOneStoreCountField.getText() == null) {
            return 0;
        }
        return Integer.valueOf(yearOneStoreCountField.getText());
    }
    /**
     * @return BigDecimal value of the text in the everudauGpm
     * field.
     */
    public BigDecimal getEveryDayGpm() {
        if (everydayGpmField.getText() == null) {
            return new BigDecimal("0.0");
        }
        return new BigDecimal(everydayGpmField.getText());
    }
    /**
     * @return BigDecimal value of the text in spoilsFees
     * field.
     */
    public BigDecimal getSpoilsAndFees() {
        if (spoilsFeesField.getText() == null) {
            return new BigDecimal(0.0);
        }
        return new BigDecimal(spoilsFeesField.getText());
    }
    /**
     * @return BigDecimal value of the text in the
     * weeklyUfswAtMin field.
     */
    public BigDecimal getWeeklyUSFWAtMin() {
        if (weeklyUfswAtMinField.getText() == null) {
            return new BigDecimal("0.0");
        }
        return new BigDecimal(weeklyUfswAtMinField.getText());
    }

    /**
     * Updates selected bar charts.
     */
    public void updateChart(final boolean lSC,
                            final boolean eRC, final boolean eUV,
                            final boolean aVS, final boolean sPP,
                            final boolean pFPS, final boolean uTPU,
                            final boolean fYEQS, final boolean fYEQU){
        if (lSC) {
            landedStoreCostChart.getData().clear();
            landedStoreCostChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(1)}));
        } if (eRC){
            everydayRetailCalcdChart.getData().clear();
            everydayRetailCalcdChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(2)}));
        } if (eUV) {
            elasticizedUnitVelocityChart.getData().clear();
            elasticizedUnitVelocityChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(3)}));
        }if (aVS) {
            annualVolumePerSkuChart.getData().clear();
            annualVolumePerSkuChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(4)}));
        }if (sPP) {
            slottingPaybackPeriodChart.getData().clear();
            slottingPaybackPeriodChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(5)}));
        }if (pFPS) {
            postSpoilsPostFreightChart.getData().clear();
            postSpoilsPostFreightChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(6)}));
        }if (uTPU) {
            unspentTradePerUnitChart.getData().clear();
            unspentTradePerUnitChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(7)}));
        }if (fYEQS) {
            fourYearEqGpPerSkuChart.getData().clear();
            fourYearEqGpPerSkuChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(8)}));
        }if (fYEQU) {
            fourYearEqGpPerUnitChart.getData().clear();
            fourYearEqGpPerUnitChart.setData(
                    FXCollections.observableArrayList(
                            new XYChart.Series[]{getChartData(9)}));
        }
    }
    /**
     *
     * @param chartNumber 1 of 9 chart numbers from left to right.
     * @return XYChart.Series<?, ?> chartData for BarChart.
     */
    public XYChart.Series<?, ?> getChartData(final int chartNumber) {
        XYChart.Series<String, BigDecimal> barChartData = new XYChart.Series();
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            switch (chartNumber) {
                case 1:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getLandedStoreCost()));
                    break;
                case 2:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getEverydayRetailCalcd()));
                    break;
                case 3:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(),
                            row.getElasticizedUnitVelocity()));
                    break;
                case 4:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getAnnualVolumePerSku()));
                    break;
                case 5:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getSlottingPaybackPeriod()));
                    break;
                case 6:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(),
                            row.getPostFreightPostSpoilsWeCollectPerUnit()));
                    break;
                case 7:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getUnspentTradePerUnit()));
                    break;
                case 8:
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getFourYearEqGpPerSku()));
                    break;
                case 9 :
                    barChartData.getData().add(new XYChart.Data(
                            row.getRTMName(), row.getFourYearEqGpPerUnit()));
                    break;
                default:
                    System.out.println("ERROR: no such barchart");
                    break;
            }
            }
        return barChartData;
    }
    /**
    * Loads dummy table data.
    */
    public static ObservableList<RTMOption> getRTMOptions() {
        ObservableList<RTMOption> rtmOptions =
                FXCollections.observableArrayList();
        RTMOption testOption = new RTMOption("Direct-to-Customer",
                new BigDecimal("0.29"), new BigDecimal("7500"),
                new BigDecimal("3.59"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"));
        testOption.setResultingEverydayRetailOverride(new BigDecimal("5.99"));
        testOption.setLandedStoreCost(new BigDecimal("3.59"));
        rtmOptions.add(testOption);
        RTMOption optionTwo = new RTMOption("Direct-to-Customer",
                new BigDecimal("0.0"), new BigDecimal("3500"),
                new BigDecimal("3.07"), new BigDecimal("3.75"),
                new BigDecimal("0.0"), new BigDecimal("0.0"));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("6.49"));
        optionTwo.setEverydayRetailCalcd(new BigDecimal("6.32"));
        optionTwo.setLandedStoreCost(new BigDecimal("3.79"));
        optionTwo.setRTMName("Option2");
        rtmOptions.add(optionTwo);
        RTMOption optionThree = new RTMOption();
        optionThree.setRTMName("Option3");
        rtmOptions.add(optionThree);
        RTMOption optionFour = new RTMOption();
        optionFour.setRTMName("Option4");
        rtmOptions.add(optionFour);
        return rtmOptions;
    }
    /**
    * Load dummy RetailerProduct.
     * */
    public static ObservableList<RetailerProduct> getRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts =
                FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        ObservableList<Meeting>  meetings = FXCollections.observableArrayList();
        skus.addAll(new Sku("dill", "current",
                        "great taste"), new Sku("dill",
                        "current", "great taste"),
                new Sku("dill", "current", "great taste"));
        meetings.addAll(new Meeting("Review Meeting", "here",
                LocalDate.of(2022, 12,5),
                "11:15", "will be fun"), new Meeting());
        retailerProducts.add(new RetailerProduct(new Product(
                "Big Time Food Company", "24 oz pickles",
                new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"),
                new BigDecimal("2.05"), new BigDecimal("-1.15"),
                new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"),
                 new BigDecimal("0.0"), new BigDecimal("0.0"),
                 new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"),
                MenuController.getExtendedSkus()), getRTMOptions(),
                skus, meetings, getDummyPromoPlans()));
        return retailerProducts;
    }
    public static ObservableList<PromoPlan> getDummyPromoPlans() {
        ObservableList<PromoPlan> promoPlans =
                FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(0));
        promoPlans.add(new PromoPlan(1));
        promoPlans.add(new PromoPlan(2));
        promoPlans.add(new PromoPlan(3));
        return promoPlans;
    }

    @FXML
    private void switchToAssortment() throws IOException {
        FXMLLoader assortmentLoader = App.createFXMLLoader("assortment");
        App.setSceneRoot(assortmentLoader.load());

        AssortmentController assortmentController =
                assortmentLoader.getController();
        assortmentController.setRetailer(retailer.get());
    }
    @FXML
    private void switchToRetailerSelection() throws IOException {
        FXMLLoader retailerSelectionLoader = App
                .createFXMLLoader("retailerSelection");
        App.setSceneRoot(retailerSelectionLoader.load());
        RetailerSelectionController assortmentController =
                retailerSelectionLoader.getController();
        assortmentController.setRetailer(retailer.get());
    }
    @FXML
    private void switchToPricingPromotion()
            throws IOException {
        FXMLLoader pricingPromotionLoader = App
                .createFXMLLoader("pricingPromotion");
        App.setSceneRoot(pricingPromotionLoader.load());

        PricingPromotionController pricingPromotionController =
                pricingPromotionLoader.getController();
        pricingPromotionController.setRetailer(retailer.get());
    }
    /**
     * Set Column header labels and tooltips for first and second planning
     * table.
     */
    public void setToolTipsTable1And2() {
        rtmNameColumnLabel.setTooltip(rtmNameColumnTip);
        rtmNameCol1.setGraphic(rtmNameColumnLabel);

        freightOutPerUnitLabel.setTooltip(freightOutPerUnitTip);
        freightOutPerUnitCol.setGraphic(freightOutPerUnitLabel);

        slottingPerSkuLabel.setTooltip(slottingPerSkuTip);
        slottingPerSkuCol.setGraphic(slottingPerSkuLabel);

        firstReceiverLabel.setTooltip(firstReceiverTip);
        firstReceiverCol.setGraphic(firstReceiverLabel);

        secondReceiverLabel.setTooltip(secondReceiverTip);
        secondReceiverColumn.setGraphic(secondReceiverLabel);

        thirdReceiverLabel.setTooltip(thirdReceiverTip);
        thirdReceiverColumn.setGraphic(thirdReceiverLabel);

        fourthReceiverLabel.setTooltip(fourthReceiverTip);
        fourthReceiverColumn.setGraphic(fourthReceiverLabel);

        landedStoreCostLabel.setTooltip(landedStoreCostTip);
        landedStoreCostColumn.setGraphic(landedStoreCostLabel);

        everyDayRetailCalcdLabel.setTooltip(everyDayRetailCalcdTip);
        everydayRetailCalcdCol.setGraphic(everyDayRetailCalcdLabel);

        everyDayRetailOverrideLabel.setTooltip(everyDayRetailOverrideTip);
        everydayRetailOverrideCol.setGraphic(everyDayRetailOverrideLabel);

        rtmNameColumn2.setGraphic(rtmNameColumnLabel2);

        elasticizedUnitVelocityLabel.setTooltip(elasticizedUnitVelocityTip);
        elasticizedUnitVelocityColumn.setGraphic(elasticizedUnitVelocityLabel);

        annualVolumePerSkuLabel.setTooltip(annualVolumePerSkuTip);
        annualVolumePerSkuColumn.setGraphic(annualVolumePerSkuLabel);

        slottingPaybackPeriodLabel.setTooltip(slottingPaybackPeriodTip);
        slottingPaybackPeriodColumn.setGraphic(slottingPaybackPeriodLabel);

        postFreightPostSpoilsLabel.setTooltip(postFreightPostSpoilsTip);
        postFreightPostSpoilsPerUnitCol.setGraphic(postFreightPostSpoilsLabel);

        unspentTradePerUnitLabel.setTooltip(unspentTradePerUnitTip);
        unspentTradePerUnitColumn.setGraphic(unspentTradePerUnitLabel);

        fourYearEqGpPerSkuLabel.setTooltip(fourYearEqGpPerSkuTip);
        fourYearEqGpPerSkuColumn.setGraphic(fourYearEqGpPerSkuLabel);

        fourYearEqGpPerUnitLabel.setTooltip(fourYearEqGpPerUnitTip);
        fourYearEqGpPerUnitColumn.setGraphic(fourYearEqGpPerUnitLabel);
    }

    /**
     * Set up cellValue factories, which look for properties of RTMOption
     * item in the table.
     */
    public void setCellValueFactories() {
        rtmNameCol1.setCellValueFactory(cellData -> cellData.getValue()
                .rtmNameProperty());
        slottingPerSkuCol.setCellValueFactory(cellData -> cellData.getValue()
                .slottingPerSkuProperty());
        freightOutPerUnitCol.setCellValueFactory(cellData -> cellData.getValue()
                .freightOutPerUnitProperty());
        firstReceiverCol.setCellValueFactory(cellData -> cellData.getValue()
                .firstReceiverProperty());
        secondReceiverColumn.setCellValueFactory(cellData -> cellData.getValue()
                .secondReceiverProperty());
        thirdReceiverColumn.setCellValueFactory(cellData -> cellData.getValue()
                .thirdReceiverProperty());
        fourthReceiverColumn.setCellValueFactory(cellData -> cellData
                .getValue().fourthReceiverProperty());
        landedStoreCostColumn.setCellValueFactory(cellData -> cellData
                .getValue().landedStoreCostProperty());
        everydayRetailCalcdCol.setCellValueFactory(cellData -> cellData
                .getValue().everydayRetailCalcdProperty());
        everydayRetailOverrideCol.setCellValueFactory(
                cellData -> cellData.getValue()
                        .resultingEverydayRetailOverrideProperty());
        elasticizedUnitVelocityColumn.setCellValueFactory(cellData -> cellData
                .getValue().elasticizedUnitVelocityProperty());
        annualVolumePerSkuColumn.setCellValueFactory(cellData -> cellData
                .getValue().annualVolumePerSkuProperty());
        slottingPaybackPeriodColumn.setCellValueFactory(cellData -> cellData
                .getValue().slottingPaybackPeriodProperty());
        postFreightPostSpoilsPerUnitCol.setCellValueFactory(cellData -> cellData
                .getValue().postFreightPostSpoilsWeCollectPerUnitProperty());
        unspentTradePerUnitColumn.setCellValueFactory(cellData -> cellData
                .getValue().unspentTradePerUnitProperty());
        fourYearEqGpPerSkuColumn.setCellValueFactory(cellData -> cellData
                .getValue().fourYearEqGpPerSkuProperty());
        fourYearEqGpPerUnitColumn.setCellValueFactory(cellData -> cellData
                .getValue().fourYearEqGpPerUnitProperty());
        rtmNameColumn2.setCellValueFactory(cellData -> cellData.getValue()
                .rtmNameProperty());
    }

    /**
     *
     */
    public void setCellFactories() {

        rtmNameCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        slottingPerSkuCol.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        freightOutPerUnitCol.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        firstReceiverCol.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        landedStoreCostColumn.setCellFactory(
                tc -> new CustomNonEditCell("$", ""));
        everydayRetailCalcdCol.setCellFactory(
                tc -> new CustomNonEditCell("$", ""));
        everydayRetailOverrideCol.setCellFactory(TextFieldTableCell
                .forTableColumn(new BigDecimalStringConverter()));
        elasticizedUnitVelocityColumn.setCellFactory(
                tc -> new CustomNonEditCell("", " U/S/F/W"));
        annualVolumePerSkuColumn.setCellFactory(
                tc -> new TableCell<>() {
                @Override
                protected void updateItem(
                    final BigDecimal item, final boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText("");
                    } else {
                        setText(String.format("%,.0f", item, 0, RoundingMode
                        .HALF_UP) + " Units");
            }
        }
        });
        slottingPaybackPeriodColumn.setCellFactory(
                tc -> new CustomNonEditCell("", " Years"));
        postFreightPostSpoilsPerUnitCol.setCellFactory(
                tc -> new CustomNonEditCell("$", " Per Unit"));
        unspentTradePerUnitColumn.setCellFactory(tc
                -> new CustomNonEditCell("$", " Per Unit"));
        fourYearEqGpPerSkuColumn.setCellFactory(tc
                -> new CustomNonEditCell("$", " Per Sku"));
        fourYearEqGpPerUnitColumn.setCellFactory(tc
                -> new CustomNonEditCell("$", " Per Unit"));
    }
    /**
    * Return a filter to use in the double text formatters (which are
     * almost always converted into BigDecimals.
     * @return UnaryOperator<TextFormatter.Change> filter to use in
     *      doubletextformatter.
     */
    public static UnaryOperator<TextFormatter.Change>
                                getDoubleInputFilter() {
        Pattern validEditingState = Pattern
                .compile("-?(([1-9+][0-9]*)|0)?(\\.[0-9]*)?");
            UnaryOperator<TextFormatter.Change> filter = c -> {
                String text = c.getControlNewText();
                if (validEditingState.matcher(text).matches()) {
                    return c;
                } else {
                    return null;
                }
            };
            return filter;
    }
    /**
     * Input converter for textfields with inputs of type double
     * which can be used for BigDecimals as well.
    * @return StringConverter<Double> a converter to use in the
     * double text formatters.
     */
    public static StringConverter<Double> getDoubleInputConverter() {
        StringConverter<Double> converter = new StringConverter<>() {
            @Override
            public Double fromString(final String s) {
                if (s.isEmpty() || "-".equals(s)
                        || ".".equals(s) || "-.".equals(s)) {
                    return 0.0;
                } else {
                    return Double.valueOf(s);
                }
            }

            @Override
            public String toString(final Double d) {
                return d.toString();
            }
        };
        return converter;
    }
    /**
    * @return StringConverter<Product> a converter for product combobox.
     */
    public StringConverter<Product> getProductComboboxConverter() {
        return new ProductboxConverter("product");
    }
    /**
     * @return StringConverter<Product> a converter for brand combobox
     */
    public StringConverter<Product> getBrandComboboxConverter() {
        return new ProductboxConverter("brand");
    }
    /**
     * @return Retailer Retailer contains all the information,
     * including RetailerProducts and Promotional Plans (PromoPlan)s.
     */
    public Retailer getRetailer() {
        return retailer.get();
    }
    /**
     * @param selectedRetailer selected Retailer from RetailerSelection
     * page.
     */
    public void setRetailer(final Retailer selectedRetailer) {
        this.retailer.set(selectedRetailer);
        ObservableList<RetailerProduct> retailerProducts = getRetailer()
                .getRetailerProducts();
        RetailerProduct currentRetailerProduct = retailerProducts
                .get(getRetailer().getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions = currentRetailerProduct
                .getRtmOptions();
//        this.firstTableView.setItems(currentRtmOptions);
//        setUpListeners();
//        CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION
//        INSTEAD OF PRODUCT SELECTION
//        this.secondTableView.setItems(currentRtmOptions);

        updateRetailerProduct(currentRetailerProduct.getProduct());
        this.brandNameBox.setItems(getUniqueBrandNames(MenuController
                .getExampleProducts()));
        this.brandNameBox.valueProperty().setValue(currentRetailerProduct
                .getProduct());
        this.productClassBox.setItems(getCorrectProductClasses(
                MenuController.getExampleProducts() ));
        this.productClassBox.valueProperty()
                .setValue(currentRetailerProduct.getProduct());
//        listLabel.setText("List = $" + currentRetailerProduct.getProduct().getUnitListCost());
//        fobLabel.setText("F.O.B. = $" + currentRetailerProduct.getProduct().getUnitFobCost());
//        net1GoalLabel.setText("Net 1 Goal = $" + currentRetailerProduct.getProduct().getUnitNet1Goal());
//        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :" +  currentRetailerProduct.getProduct().getElasticityMultiple() + "% Volume");
        this.yearOneStoreCountField.setText(String.valueOf(selectedRetailer
                .getYearOneStoreCount()));
        this.everydayGpmField.setText(selectedRetailer
                .getEverydayGPM().toString());
        this.spoilsFeesField.setText(selectedRetailer
                .getSpoilsFees().toString());
        // Stuff that should be implemented differently
//        this.weeklyUfswAtMinField.setText(currentRtmOptions.get(0).getWeeklyUSFWAtMin().toString());
//        for (RTMOption row: firstTableView.getItems()){
//            row.setProduct(currentRetailerProduct.getProduct());
//            row.setYearOneStoreCount(getRetailer().getYearOneStoreCount());
//            row.setEverydayGPM(getRetailer().getEverydayGPM());
//            row.setSpoilsAndFees(getRetailer().getSpoilsFees().divide((new BigDecimal("100")), 4 , RoundingMode.HALF_UP));
//            maxReceivers(row);
//            row.updateResultingEverydayRetailCald();
//            updateChart(true,true,true,true,true,true,true,true,true );
//        }
//        secondTableView.setVisible(false);
//        secondTableView.setVisible(true);
//        secondTableView.refresh();


    }
}


