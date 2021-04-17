package com.traderoute.controllers;
import com.traderoute.*;
import com.traderoute.cells.CustomNonEditCell;
import com.traderoute.data.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;
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
     * First Route-To-Market Planning Table, mostly editable
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
    private final Label rtmNameColumnLabel = new Label("Route to market options");
    @FXML
    private final Label rtmNameColumnLabel2 = new Label("Route to market options");
    @FXML
    private final Label slottingPerSkuLabel = new Label("Slotting Per Sku");
    @FXML
    private final Label freightOutPerUnitLabel = new Label("Freight Out Per Unit");
    @FXML
    private final Label firstReceiverLabel = new Label("First Receiver Pays");
    @FXML
    private final Label secondReceiverLabel = new Label("Second Receiver Pays");
    @FXML
    private final Label thirdReceiverLabel = new Label("Third Receiver Pays");
    @FXML
    private final Label fourthReceiverLabel = new Label("Fourth Receiver Pays");
    @FXML
    private final Label landedStoreCostLabel = new Label("Landed Store Cost");
    @FXML
    private final Label resultingEveryDayRetailCalcdLabel = new Label("Calculated Everyday Retail");
    @FXML
    private final Label resultingEveryDayRetailOverrideLabel = new Label("Override Everyday Retail");
    @FXML
    private final Label elasticizedEstimatedUnitVelocityLabel = new Label("Elasticized Unit Velocity ");
    @FXML
    private final Label estimatedAnnualVolumePerSkuLabel = new Label("Annual Volume Per Sku ");
    @FXML
    private final Label slottingPaybackPeriodLabel = new Label("Slotting Payback Period");
    @FXML
    private final Label postFreightPostSpoilsWeCollectLabel = new Label("Post Freight & Spoils We Collect");
    @FXML
    private final Label unspentTradePerUnitLabel = new Label("Unspent Trade Per Unit");
    @FXML
    private final Label fourYearEqGpPerSkuLabel = new Label("4-Year EQ GP $ Per Sku");
    @FXML
    private final Label fourYearEqGpPerUnitLabel = new Label("4-Year EQ GP $ Per Unit");

    /**
     * Tooltips to be added to Column Header Labels.
     */
    @FXML
    private final Tooltip rtmNameColumnTip =
            new Tooltip("Please enter the most likely 'route-to-market' options to get the product to the market.");
    @FXML
    private final Tooltip slottingPerSkuTip =
            new Tooltip("Please enter the required slotting (placement) investment specific to this 'route-to-arket option.'");
    @FXML
    private final Tooltip freightOutPerUnitTip =
            new Tooltip("If we're responsible for the cost of shipping for this route-to -market option, please enter in the 'per unit cost' of this 'Freight-Out.' For F.O.B (Pick-up) Customers, Freight-Out equals $0.");
    @FXML
    private final Tooltip firstReceiverTip =
            new Tooltip("The Per Unit Cost the First Receiver pays us, typically the Unit List Cost or the Unit F.O.B");
    @FXML
    private final Tooltip secondReceiverTip =
            new Tooltip("The Per Unit Cost the Second Receiver pays to the First Receiver.");
    @FXML
    private final Tooltip thirdReceiverTip =
            new Tooltip("The Per Unit Cost the Third Receiver pays to the Second Receiver.");
    @FXML
    private final Tooltip fourthReceiverTip =
            new Tooltip("The Per Unit Cost the Fourth Receiver pays to the Third Receiver.");
    @FXML
    private final Tooltip landedStoreCostTip =
            new Tooltip("The Per Unit Cost the Retail OutLet (Last Receiver pays prior to applying the Required GPM% to establish the Everyday Retail.");
    @FXML
    private final Tooltip resultingEveryDayRetailCalcdTip =
            new Tooltip("The auto-calculated Resulting Everyday Retail given the Landed Store Cost and Required Gross Profit Margin %.");
    @FXML
    private final Tooltip resultingEveryDayRetailOverrideTip =
            new Tooltip("Please enter the REALISTIC Everyday Retail considering the auto-calculated retail to the left.");
    @FXML
    private final Tooltip elasticizedEstimatedUnitVelocityTip =
            new Tooltip("For each route-to-market option provided, these are the Estimated Weekly Unit Velocities given the Product Class's Price Elasticity Multiple (for each X% increase in Unit Price there is a Y% decrease in Units Sold");
    @FXML
    private final Tooltip estimatedAnnualVolumePerSkuTip =
            new Tooltip("For each route-to-market option provided, these are the Estimated Annual Volumes Per SKU (accounting for price elasticities)");
    @FXML
    private final Tooltip slottingPaybackPeriodTip =
            new Tooltip("If Slotting is a consideration these are the Payback Periods (in years) for each route-to-market option provided to recoup the initial Slotting Investment.");
    @FXML
    private final Tooltip postFreightPostSpoilsWeCollectTip =
            new Tooltip("For each route-to-market option provided, the Per Unit Rate we collect after which Freight Costs and Spoils are accounted (but prior to Trade Spending).");
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

        brandNameBox.setItems(getUniqueBrandNames(MenuController.getExampleProducts()));
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



    /*
         Set Up listeners for everyDayGPM and landed store Cost Property
         */
    public void setUpListeners() {
        /*
        Check if landedStoreCostProperty changed, if it it did calculate everyday Retail
        */
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setupListeners();
            row.landedStoreCostProperty().addListener(((arg, oldVal, newVal) -> {
                System.out.println("is this happening");
                updateChart(true,false, false, false, false, false, false, false, false);
            }));
            row.RTMNameProperty().addListener(((arg, oldVal, newVal) -> {
                System.out.println("is this happening");
                updateChart(true,true,true,true,true,true,true,true,true);
            }));
            /*
            Update chart and set max label
            */
            row.resultingEverydayRetailCalcdProperty().addListener(((arg, oldVal, newVal) -> {
                maxOverrideLabel.setText("of $" + getMinOverride());
                System.out.println("Why is this no longer happening?");
                updateChart(false,true,false,false,false,false,false,false,false);
            }));
            /*
            Check if resulting everyday retail changed, if it did check the max of the column and assign it to maxOverrideLabel
            */
            row.resultingEverydayRetailOverrideProperty().addListener(((arg, oldVal, newVal) -> {
                row.setMinOverride(getMinOverride());
                rtmPlanningTable1.refresh();
                row.updateElasticizedUnitVelocity();
                maxOverrideLabel.setText("of $" + getMinOverride());
                System.out.println("Why is this no longer happening?");
                rtmPlanningTable1.refresh();
            }));
        }
    }

    /*
    For Brand Name Bo
     */
    public static  ObservableList<Product> getUniqueBrandNames(ObservableList<Product> products) {
        ObservableList<Product> uniqueBrandNames = FXCollections.observableArrayList();
        ObservableList<String> uniqueBrandNameStrings = FXCollections.observableArrayList();
        for (Product product : products) {
            if (!uniqueBrandNameStrings.contains(product.getBrandName())) {
                uniqueBrandNameStrings.add(product.getBrandName());
                uniqueBrandNames.add(product);
            }
        }
        return uniqueBrandNames;
    }
    public static ObservableList<Product> getCorrespondingProductClasses(ObservableList<Product> retailerProducts,Product selectedBrandName) {
        ObservableList<Product> correspondingProductClasses = FXCollections.observableArrayList();
        //Set up product combobox and make it display product class
        for (Product product : retailerProducts) {
            if (product.getBrandName().equals(product.getBrandName())) {
                correspondingProductClasses.add(product);
            }
        }
        return correspondingProductClasses;
    }

    /*
    Product class changed Event
     */
    public void changeBrandComboboxEvent(ActionEvent event) {
        Product selectedBrandName = brandNameBox.getSelectionModel().getSelectedItem();
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        listLabel.setText("List = $");
        fobLabel.setText("F.O.B. = $");
        net1GoalLabel.setText("Net 1 Goal = $");
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :  % Volume");
        productClassBox.setItems(getCorrespondingProductClasses(MenuController.getExampleProducts(), selectedBrandName));
    }
    /*
    Product class changed Event
     */
    public void changeProductComboboxEvent(ActionEvent event) {

        Product selectedProduct = productClassBox.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            productClassBox.setPromptText("Now Select a Product Class");
        } else {
            listLabel.setText("List = $" + selectedProduct.getUnitListCost());
            fobLabel.setText("F.O.B. = $" + selectedProduct.getUnitFobCost());
            net1GoalLabel.setText("Net 1 Goal = $" + selectedProduct.getUnitNet1Goal());
            elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :" + selectedProduct.getElasticityMultiple() + "% Volume");
            for (RTMOption row : rtmPlanningTable1.getItems()) {
                row.setProduct(selectedProduct);
            }
            updateRetailerProduct(selectedProduct);
        }
    }
    public int getProductIndex(Product product) {
        // create addProduct method in product page
        int productIndex=-1;
        for (RetailerProduct retailerProduct: getRetailer().getRetailerProducts()){
            if (retailerProduct.getProduct().equals(product)){
                productIndex =getRetailer().getRetailerProducts().indexOf(retailerProduct);
            }
        }
        return productIndex;
        // TEMPORARY
    }

    public void updateRetailerProduct(Product product){
        int productIndex= getProductIndex(product);
        retailer.get().setcurrentRetailerProductIndex(productIndex);

        // TEMPORARILY GET PRODUCT INDEX THROUGH RETAILERPRODUCTS
        rtmPlanningTable1.setItems(getRetailer().getRetailerProducts().get(productIndex).getRtmOptions());
        rtmPlanningTable2.setItems(getRetailer().getRetailerProducts().get(productIndex).getRtmOptions());
        rtmPlanningTable2.refresh();
        updateChart(false,false,false,false,true,true,true,true,true);
//        this.retailer.set(retailer);
        ObservableList<RetailerProduct> retailerProducts =getRetailer().getRetailerProducts();
        RetailerProduct currentRetailerProduct = retailerProducts.get(getRetailer().getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions = currentRetailerProduct.getRtmOptions();
        this.rtmPlanningTable1.setItems(currentRtmOptions);
        setUpListeners();
        //CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION INSTEAD OF PRODUCT SELECTION
        this.rtmPlanningTable2.setItems(currentRtmOptions);

        listLabel.setText("List = $" + currentRetailerProduct.getProduct().getUnitListCost());
        fobLabel.setText("F.O.B. = $" + currentRetailerProduct.getProduct().getUnitFobCost());
        net1GoalLabel.setText("Net 1 Goal = $" + currentRetailerProduct.getProduct().getUnitNet1Goal());
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :" +  currentRetailerProduct.getProduct().getElasticityMultiple() + "% Volume");
        // Stuff that should be implemented differently
        this.weeklyUfswAtMinField.setText(currentRtmOptions.get(0).getWeeklyUSFWAtMin().toString());
        for (RTMOption row: rtmPlanningTable1.getItems()){
            row.setProduct(currentRetailerProduct.getProduct());
            row.setYearOneStoreCount(getRetailer().getYearOneStoreCount());
            row.setEverydayGPM(getRetailer().getEverydayGPM());
            row.setSpoilsAndFees(getRetailer().getSpoilsFees().divide((new BigDecimal("100")), 4 , RoundingMode.HALF_UP));
            maxReceivers(row);
            row.updateResultingEverydayRetailCald();
            updateChart(true,true,true,true,true,true,true,true,true );
        }
        rtmPlanningTable2.setVisible(false);
        rtmPlanningTable2.setVisible(true);
        rtmPlanningTable2.refresh();


    };

        // IDEA FOR LOGIC BEHIND RETAILER PRODUCT
//        ObservableList<RetailerProduct> allRetailerProducts = retailer.get().getRetailerProducts();
//        for (int i = 0; i<allRetailerProducts.size(); i++){
//            if (selectedProduct.equals(allRetailerProducts.get(i).getProduct())){
//                retailer.get().setCurrentRetailerProduct(allRetailerProducts.get(i));
//            }
//            if (i== allRetailerProducts.size()-1){
//                RetailerProduct retailerProductToAdd = new RetailerProduct();
//                retailerProductToAdd.setProduct(selectedProduct);
//                retailerProductToAdd.setRetailer(retailer.get());
//                retailerProductToAdd.setRtmOptions(null); // Add functionality here;
//                retailer.get().setCurrentRetailerProduct(new RetailerProduct());
//            }
//        }



    /*
    Return minimum value from override column
     */
    public BigDecimal getMinOverride() {
        BigDecimal smallest = new BigDecimal("100000000000");
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            BigDecimal currentNumber = row.getResultingEverydayRetailOverride();
            if (currentNumber.compareTo(smallest) < 0 && currentNumber.compareTo(new BigDecimal("0.0"))>0) {
                smallest = currentNumber;
            }
        }
        return smallest;
    }

    /*
    Get elasticized velocities from weeklyUSFWAtMin Field  and do calculation if possible
    */
    public void changeWeeklyUSFWAtMinEvent(ActionEvent event) {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        for (RTMOption row : rtmPlanningTable2.getItems()) {
            row.setMinOverride(getMinOverride());
            row.setWeeklyUSFWAtMin(getWeeklyUSFWAtMin());
        }
        rtmPlanningTable2.refresh();
        updateChart(false,false,true,true,true,true,true,true,true);
    }
    /*
    Get elasticized velocities from weeklyUSFWAtMin Field  and do calculation if possible
    */
    public void changeOverrideEvent(TableColumn.CellEditEvent editedCell) {
        System.out.println("please get here at least");
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setResultingEverydayRetailOverride(new BigDecimal(editedCell.getNewValue().toString()));
        for (RTMOption row : rtmPlanningTable2.getItems()) {
            row.setMinOverride(getMinOverride());
        }
        System.out.println(RTMOptionSelected.getRTMName() + ", elasticized= " + RTMOptionSelected.getElasticizedUnitVelocity());
        System.out.println(RTMOptionSelected.getRTMName() + ", annual Volume= " + RTMOptionSelected.getAnnualVolumePerSku());
        rtmPlanningTable2.refresh();
        updateChart(false,false,true,true,true,true,true,true,true);
    }

    /*
    Get estimated annual volume/sku
    */
    public void changeYearOneStoreCount(ActionEvent event) {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        for (RTMOption row : rtmPlanningTable2.getItems()) {
            row.setYearOneStoreCount(getYearOneStoreCount());
        }
        rtmPlanningTable2.refresh();
        updateChart(false,false,false,true,true,true,true,true,true);
    }

    /*
    Set EverydayGPm and do calculation if possible
    */
    public void changeEveryDayGPMCellEvent(ActionEvent event) {
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setEverydayGPM(this.getEveryDayGPM());
        }
        updateChart(false,true,false,false,false,false,false,false,false);
    }

    /*
    Set EverydayGPm and do calculation if possible
    */
    public void changeSpoilsAndFeesEvent(ActionEvent event) {
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setSpoilsAndFees(getSpoilsAndFees().divide((new BigDecimal("100")), 4 , RoundingMode.HALF_UP));
        }
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    // Change Slotting Per Sku
    public void changeSlottingPerSkuCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setSlottingPerSku(new BigDecimal(editedCell.getNewValue().toString()));
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        rtmPlanningTable2.refresh();
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    /*
    Calculate max from the receivers
    */
    public void changeFreightOutPerUnitCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFreightOutPerUnit(new BigDecimal(editedCell.getNewValue().toString()));
        updateChart(false,false,false,false,true,true,true,true,true);
    }
    /*
    Calculate max from the receivers
    */
    public void changeFirstReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFirstReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    public void changeSecondReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setSecondReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
    }

    public void changeThirdReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setThirdReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
        ;
    }

    public void changeFourthReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = rtmPlanningTable1.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFourthReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
    }

    public void maxReceivers(RTMOption selectedOption) {
        BigDecimal maxReceivers = selectedOption.getFirstReceiver().max(selectedOption.getSecondReceiver()
                .max(selectedOption.getThirdReceiver().max(selectedOption.getFourthReceiver())));
        if (maxReceivers.compareTo(new BigDecimal("0.0")) > 0) {
            selectedOption.setLandedStoreCost(maxReceivers);
        }
    }

    /*
Return Value from Year One Store Count
*/
    public int getYearOneStoreCount() {
        if (yearOneStoreCountField.getText() == null) {
            return 0;
        }
        return Integer.valueOf(yearOneStoreCountField.getText());
    }

    /*
    Return Value from EveryDayGPMField
    */
    public BigDecimal getEveryDayGPM() {
        if (everydayGpmField.getText() == null) {
            return new BigDecimal("0.0");
        }
        return new BigDecimal(everydayGpmField.getText());
    }

    /*
    Return Value from Spoils And Fees Count
    */
    public BigDecimal getSpoilsAndFees() {
        if (spoilsFeesField.getText() == null) {
            return new BigDecimal(0.0);
        }
        return new BigDecimal(spoilsFeesField.getText());
    }

    /*
    Return Value from EveryDayGPMField
    */
    public BigDecimal getWeeklyUSFWAtMin() {
        if (weeklyUfswAtMinField.getText() == null) {
            return new BigDecimal("0.0");
        }
        return new BigDecimal(weeklyUfswAtMinField.getText());
    }

    public void updateChart(boolean lSC, boolean rERC, boolean eEUV, boolean eAVS, boolean sPP, boolean pFPS, boolean uTPU, boolean fYEQS, boolean fYEQU){
        if (lSC) {
            landedStoreCostChart.getData().clear();
            landedStoreCostChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(1)}));
        } if (rERC){
            everydayRetailCalcdChart.getData().clear();
            everydayRetailCalcdChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(2)}));
        } if (eEUV) {
            elasticizedUnitVelocityChart.getData().clear();
            elasticizedUnitVelocityChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(3)}));
        }if (eAVS) {
            annualVolumePerSkuChart.getData().clear();
            annualVolumePerSkuChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(4)}));
        }if (sPP) {
            slottingPaybackPeriodChart.getData().clear();
            slottingPaybackPeriodChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(5)}));
        }if (pFPS) {
            postSpoilsPostFreightChart.getData().clear();
            postSpoilsPostFreightChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(6)}));
        }if (uTPU) {
            unspentTradePerUnitChart.getData().clear();
            unspentTradePerUnitChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(7)}));
        }if (fYEQS) {
            fourYearEqGpPerSkuChart.getData().clear();
            fourYearEqGpPerSkuChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(8)}));
        }if (fYEQU) {
            fourYearEqGpPerUnitChart.getData().clear();
            fourYearEqGpPerUnitChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(9)}));
        }
    }

    //load first values.

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
                            row.getRTMName(), row.getElasticizedUnitVelocity()));
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
                case 9:
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


    /*
    Loads dummy table data
    */
    public static ObservableList<RTMOption> getRTMOptions() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOption("Direct-to-Customer", new BigDecimal("0.29"),BigDecimal.valueOf(7500), BigDecimal.valueOf(3.59),
                BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        testOption.setResultingEverydayRetailOverride(new BigDecimal("5.99"));
        testOption.setLandedStoreCost(new BigDecimal("3.59"));
        RTMOptions.add(testOption);
        RTMOption optionTwo = new RTMOption("Direct-to-Customer", new BigDecimal("0.0"),BigDecimal.valueOf(3500), BigDecimal.valueOf(3.07),
                BigDecimal.valueOf(3.75), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("6.49"));
        optionTwo.setResultingEverydayRetailCalcd(new BigDecimal("6.32"));
        optionTwo.setLandedStoreCost(new BigDecimal("3.79"));
        optionTwo.setRTMName("Option2");
        RTMOptions.add(optionTwo);
        RTMOption optionThree = new RTMOption();
        optionThree.setRTMName("Option3");
        RTMOptions.add(optionThree);
        RTMOption optionFour = new RTMOption();
        optionFour.setRTMName("Option4");
        RTMOptions.add(optionFour);
        return RTMOptions;
    }
    /*
    Load dummy RetailerProduct
     */
    public static ObservableList<RetailerProduct> getRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts = FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        ObservableList<Meeting>  meetings = FXCollections.observableArrayList();
        skus.addAll(new Sku("dill", "current", "great taste"), new Sku("dill", "current", "great taste"), new Sku("dill", "current", "great taste"));
        meetings.addAll(new Meeting("Review Meeting", "here", LocalDate.of(2022,12,5), "11:15","will be fun"), new Meeting());
        retailerProducts.add(new RetailerProduct( new Product("Big Time Food Company", "24 oz pickles", new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("2.05"), new BigDecimal("-1.15"), new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), MenuController.getExtendedSkus()), getRTMOptions(), skus,meetings, getDummyPromoPlans()));
        return retailerProducts;
    }
    public static ObservableList<PromoPlan> getDummyPromoPlans(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(0));
        promoPlans.add(new PromoPlan(1));
        promoPlans.add(new PromoPlan(2));
        promoPlans.add(new PromoPlan(3));
        return promoPlans;
    }

    @FXML
    private void switchToAssortment(final ActionEvent event) throws IOException {
//        App.setRoot("assortment");
        FXMLLoader assortmentLoader = App.createFXMLLoader("assortment");
        App.setSceneRoot(assortmentLoader.load());

        AssortmentController assortmentController = assortmentLoader.getController();
        assortmentController.setRetailer(retailer.get());
    }
    @FXML
    private void switchToRetailerSelection(final ActionEvent event) throws IOException {
        FXMLLoader retailerSelectionLoader = App
                .createFXMLLoader("retailerSelection");
        App.setSceneRoot(retailerSelectionLoader.load());
        RetailerSelectionController assortmentController =
                retailerSelectionLoader.getController();
        assortmentController.setRetailer(retailer.get());
    }
    @FXML
    private void switchToPricingPromotion(final ActionEvent event)
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

        resultingEveryDayRetailCalcdLabel.setTooltip(resultingEveryDayRetailCalcdTip);
        everydayRetailCalcdCol.setGraphic(resultingEveryDayRetailCalcdLabel);

        resultingEveryDayRetailOverrideLabel.setTooltip(resultingEveryDayRetailOverrideTip);
        everydayRetailOverrideCol.setGraphic(resultingEveryDayRetailOverrideLabel);

        rtmNameColumn2.setGraphic(rtmNameColumnLabel2);

        elasticizedEstimatedUnitVelocityLabel.setTooltip(elasticizedEstimatedUnitVelocityTip);
        elasticizedUnitVelocityColumn.setGraphic(elasticizedEstimatedUnitVelocityLabel);

        estimatedAnnualVolumePerSkuLabel.setTooltip(estimatedAnnualVolumePerSkuTip);
        annualVolumePerSkuColumn.setGraphic(estimatedAnnualVolumePerSkuLabel);

        slottingPaybackPeriodLabel.setTooltip(slottingPaybackPeriodTip);
        slottingPaybackPeriodColumn.setGraphic(slottingPaybackPeriodLabel);

        postFreightPostSpoilsWeCollectLabel.setTooltip(postFreightPostSpoilsWeCollectTip);
        postFreightPostSpoilsPerUnitCol.setGraphic(postFreightPostSpoilsWeCollectLabel);

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
                .getValue().resultingEverydayRetailCalcdProperty());
        everydayRetailOverrideCol.setCellValueFactory(cellData -> cellData.getValue().resultingEverydayRetailOverrideProperty());
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
        slottingPerSkuCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        freightOutPerUnitCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        firstReceiverCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        landedStoreCostColumn.setCellFactory(tc->new CustomNonEditCell("$",""));
        everydayRetailCalcdCol.setCellFactory(tc->new CustomNonEditCell("$", ""));
//                TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        everydayRetailOverrideCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        elasticizedUnitVelocityColumn.setCellFactory(tc->new CustomNonEditCell(""," U/S/F/W"));
        annualVolumePerSkuColumn.setCellFactory(tc->new TableCell<>(){
            @Override
        protected void updateItem(BigDecimal item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText("Hello");
            }
            if (item == null) {
                setText("");
            } else {
                setText(String.format("%,.0f", item,0, RoundingMode.HALF_UP) + " Units");
            }
        }});
        slottingPaybackPeriodColumn.setCellFactory(tc->new CustomNonEditCell("", " Years"));
        postFreightPostSpoilsPerUnitCol.setCellFactory(tc->new CustomNonEditCell("$", " Per Unit"));
        unspentTradePerUnitColumn.setCellFactory(tc->new CustomNonEditCell("$", " Per Unit"));
        fourYearEqGpPerSkuColumn.setCellFactory(tc->new CustomNonEditCell("$", " Per Sku"));
        fourYearEqGpPerUnitColumn.setCellFactory(tc->new CustomNonEditCell("$", " Per Unit"));
    }


    /*
    Return a filter to use in the double text formatters
     */
    public static UnaryOperator<TextFormatter.Change> getDoubleInputFilter(){
        Pattern validEditingState = Pattern.compile("-?(([1-9+][0-9]*)|0)?(\\.[0-9]*)?");

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
    /*
    Return a converter to use in the double text formatters
     */
    public static StringConverter<Double> getDoubleInputConverter(){
        StringConverter<Double> converter = new StringConverter<>() {
            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0;
                } else {
                    return Double.valueOf(s);
                }
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        return converter;
    }
    /*
    Return a converter for product combobox
     */
    public StringConverter<Product> getProductComboboxConverter(){
        return new ProductboxConverter("product");
    }
    /*
    Return a converter for brand combobox
     */
    public StringConverter<Product> getBrandComboboxConverter() {
        return new ProductboxConverter("brand");
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
        ObservableList<RetailerProduct> retailerProducts =getRetailer().getRetailerProducts();
        RetailerProduct currentRetailerProduct = retailerProducts.get(getRetailer().getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions = currentRetailerProduct.getRtmOptions();
//        this.firstTableView.setItems(currentRtmOptions);
//        setUpListeners();
//        //CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION INSTEAD OF PRODUCT SELECTION
//        this.secondTableView.setItems(currentRtmOptions);

        updateRetailerProduct(currentRetailerProduct.getProduct());
        this.brandNameBox.setItems(getUniqueBrandNames(MenuController.getExampleProducts()));
        this.brandNameBox.valueProperty().setValue(currentRetailerProduct.getProduct());
        this.productClassBox.setItems(getCorrespondingProductClasses(MenuController.getExampleProducts(),currentRetailerProduct.getProduct()));
        this.productClassBox.valueProperty().setValue(currentRetailerProduct.getProduct());
//        listLabel.setText("List = $" + currentRetailerProduct.getProduct().getUnitListCost());
//        fobLabel.setText("F.O.B. = $" + currentRetailerProduct.getProduct().getUnitFobCost());
//        net1GoalLabel.setText("Net 1 Goal = $" + currentRetailerProduct.getProduct().getUnitNet1Goal());
//        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :" +  currentRetailerProduct.getProduct().getElasticityMultiple() + "% Volume");
        this.yearOneStoreCountField.setText(String.valueOf(retailer.getYearOneStoreCount()));
        this.everydayGpmField.setText(retailer.getEverydayGPM().toString());
        this.spoilsFeesField.setText(retailer.getSpoilsFees().toString());
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
/*
Different bar data iteration attempt
 */

//            List<BigDecimal> propertyList = new ArrayList<>();
//            propertyList.add(1, row.getLandedStoreCost());
//            propertyList.add(2, row.getResultingEverydayRetailCalcd());
//            propertyList.add(3, row.getElasticizedEstimatedUnitVelocity());
//            propertyList.add(4, row.getEstimatedAnnualVolumePerSku());
//            propertyList.add(5, row.getSlottingPaybackPeriod());
//            propertyList.add(6, row.getPostFreightPostSpoilsWeCollectPerUnit());
//            propertyList.add(7, row.getFourYearEqGpPerSku());
//            propertyList.add(8, row.getFourYearEqGpPerUnit());
/*
Create unique callback function for cell factories
 */
//    public Callback<TableColumn<RTMOption, BigDecimal>, TableCell<RTMOption, BigDecimal>> getBigDecimalTextField() {
//        return tc-> new TableCell<RTMOption, BigDecimal>() {
//            @Override
//            protected void updateItem(BigDecimal landedStoreCost, boolean empty) {
//                super.updateItem(landedStoreCost, empty);
//                if (empty) {
//                    setText("Hello");
//                }
//                if (landedStoreCost == null) {
//                    setText("");
//                } else {
//                    setText(String.format("%,.2f", landedStoreCost));
//                }
//            }
//        };
//    }

/*
Create a new tablecell factory
 */
//tc -> new TableCell<RTMOption, BigDecimal>() {
//@Override
//protected void updateItem(BigDecimal resultingEverydayRetailCalcd, boolean empty) {
//        super.updateItem(resultingEverydayRetailCalcd, empty);
//        if (empty) {
//        setText("Hello");
//        }
//        if (resultingEverydayRetailCalcd == null) {
//        setText("");
//        } else {
//        setText("$" + String.format("%,.2f", resultingEverydayRetailCalcd));
//        }
//        }
//        }
//

// Create a Retailer Specs Object and assign it to all the RTMOptions
//        RetailerSpecifics retailerSpecs = new RetailerSpecifics();
//        for (RTMOption row : firstTableView.getItems()){
//            row.setRetailerSpecs(retailerSpecs);
//        }
    /*
    Update resulting EverydayRetailCalcd
     */
//    public void updateResultingEverydayRetailCalcd(){
//        for (RTMOption row : firstTableView.getItems()) {
//            if (this.getEveryDayGPM().compareTo(new BigDecimal("0.0")) > 0 &&
//                    row.getLandedStoreCost().compareTo(new BigDecimal("0.0")) > 0) {
//                row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")))
//                        .divide((this.getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs());
//            }
//        }
//        firstTableView.refresh();
//    }
//    /*
//    Update elasticized estimated Unit Velocity
//     */
//    public void updateElasticizedEstimatedUnitVelocity() {
//        secondTableView.setItems(firstTableView.getItems());
//        for (RTMOption row : secondTableView.getItems()) {
//            if (this.getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0 && this.getMinOverride().compareTo(new BigDecimal("100000000000")) < 0) {
//                if (this.getMinOverride() == row.getResultingEverydayRetailOverride()) {
//                    row.setElasticizedEstimatedUnitVelocity(this.getWeeklyUSFWAtMin());
//                } else {
//                    row.setElasticizedEstimatedUnitVelocity(((row.getResultingEverydayRetailOverride().subtract(this.getMinOverride()))
//                            .divide((this.getMinOverride()), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("-1.15"))
//                            .multiply(this.getWeeklyUSFWAtMin())).add(this.getWeeklyUSFWAtMin()));
//                }
//            }
//        }
//        secondTableView.refresh();
//    }
//    /*
//    Update estimated annual volume per sku
//     */
//    public void updateEstimatedAnnualVolumePerSku(){
//        secondTableView.setItems(firstTableView.getItems());
//        for (RTMOption row : secondTableView.getItems()) {
//            if (this.getYearOneStoreCount() > 0 && row.getElasticizedEstimatedUnitVelocity().compareTo(new BigDecimal("0.0")) > 0) {
//                row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
//                        this.getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
//                        .setScale(0, RoundingMode.HALF_UP)).intValue()));
//            }
//        }
//        secondTableView.refresh();
//    }
//
//            PLANNED
//            row.landedStoreCostProperty().addListener(new ChangeListener<BigDecimal>() {
//                private boolean changing;
//                @Override
//                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
//                    if (!changing) {
//                        try {
//                            changing = true;
//                            row.setResultingEverydayRetailCalcd(((row.getLandedStoreCost().multiply(new BigDecimal("100")))
//                                    .divide((getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs()));
//                        } finally {
//                            changing = false;
//                        }
//                    }
//                }
//            });

            /*
            Check if resulting everyday retail changed, if it did check the max of the column and assign it to maxOverrideLabel
            PLANNED
            */
//            row.elasticizedEstimatedUnitVelocityProperty().addListener(new ChangeListener<BigDecimal>() {
//                private boolean changing;
//
//                @Override
//                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
//                    if (!changing) {
//                        try {
//                            changing = true;
//                            row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
//                                    getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
//                                    .setScale(0,RoundingMode.HALF_UP)).intValue()));
//                            secondTableView.refresh();
//                        } finally {
//                            changing = false;
//                        }
//                    }
//                }
//            });
//    /*
//    Get elasticized velocities from weeklyUSFWAtMin Field  and do calculation if possible
//    */
//    public void changeWeeklyUSFWAtMinEvent (ActionEvent event) {
////            BigDecimal newEveryDayGPM = new BigDecimal(everyDayGPMField.getText());
//        secondTableView.setItems(firstTableView.getItems());
//        for (RTMOption row : secondTableView.getItems()) {
//            row.setMinOverride(getMinOverride());
//            row.setWeeklyUSFWAtMin(getWeeklyUSFWAtMin());
//        }
////      FOR TEST
////        for (RTMOption row : secondTableView.getItems()) {
////            if (this.getMinOverride()==row.getResultingEverydayRetailOverride()){
////                row.setElasticizedEstimatedUnitVelocity(this.getWeeklyUSFWAtMin());
////            }
////            else {
////                row.setElasticizedEstimatedUnitVelocity(((row.getResultingEverydayRetailOverride().subtract(this.getMinOverride()))
////                        .divide((this.getMinOverride()), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("-1.15"))
////                        .multiply(this.getWeeklyUSFWAtMin())).add(this.getWeeklyUSFWAtMin()));
////            }
////            row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
////                    getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
////                    .setScale(0,RoundingMode.HALF_UP)).intValue()));
////            row.setSlottingPaybackPeriod(row.getSlottingPayback());
////        }
//        secondTableView.refresh();
//    }
    /*
    some rtm options
     */
//    RTMOptions.add(new RTMOption("Rebecca", 2.1, 0, BigDecimal.valueOf(0.5),
//                    BigDecimal.valueOf(0.4), BigDecimal.valueOf(0.5), BigDecimal.valueOf(3.45),
//                    0, 0, 0,
//                    0, 0, 0,
//                    0, 0, 0,
//                    0));
//            RTMOptions.add(new RTMOption("Mr.", 3.1, 0,
//                    BigDecimal.valueOf(0.2),
//                    BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.00001), BigDecimal.valueOf(4.00),
//                    0, 0, 0,
//                    0, 0, 0,
//                    0, 0, 0, 0
//            ));
    /*
    Get estimated annual volume/sku
    */
//    public void changeYearOneStoreCount (ActionEvent event) {
//
//        secondTableView.setItems(firstTableView.getItems());
//        for (RTMOption row : secondTableView.getItems()) {
//            row.setYearOneStoreCount(getYearOneStoreCount());
//            FOR TEST
//            row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
//                    this.getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
//                    .setScale(0,RoundingMode.HALF_UP)).intValue()));
//        }
//        secondTableView.refresh();
//    }


    /*
    Set EverydayGPm and do calculation if possible
    */
//    public void changeEveryDayGPMCellEvent (ActionEvent event) {
////            BigDecimal newEveryDayGPM = new BigDecimal(everyDayGPMField.getText());
//        for (RTMOption row : firstTableView.getItems()) {
//            row.setEverydayGPM(this.getEveryDayGPM());
//            FOR TEST
//            row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")))
//                    .divide((this.getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs());
//            //FIGURE OUT CORRECT ROUNDING
//            if (this.getEveryDayGPM()== new BigDecimal("100.0")){
//            }
//        }
//    }

/*
Setting Column headers to text and giving them tooltips
 */
//
//        TableColumnHeader RTMNameColumnHeader = (TableColumnHeader) firstTableView.lookup("#" + RTMNameColumn.getId());
//        Label label = (Label) RTMNameColumnHeader.lookup(".label");
//        label.setTooltip(new Tooltip("This is RTM Name Column tooltip"));
//        Label RTMNameColumnLabel = new Label ("RTM Option");
//        RTMNameColumnLabel.setTooltip(new Tooltip("This is RTM Name Column tooltip"));
//        RTMNameColumn.setGraphic(RTMNameColumnLabel);
//
// IDEA FOR SINGLE METHOD FOR ALL CALCS
//public void updateValues(){
//    for (RTMOption row : firstTableView.getItems()) {
//        BigDecimal maxReceivers = row.getFirstReceiver().max(row.getSecondReceiver()
//                .max(row.getThirdReceiver().max(row.getFourthReceiver())));
//        if (maxReceivers.compareTo(new BigDecimal("0.0"))>0){
//            row.setLandedStoreCost(row.getFirstReceiver().max(row.getSecondReceiver()
//                    .max(row.getThirdReceiver().max(row.getFourthReceiver()))));
//        }
//        if (this.getEveryDayGPM().compareTo(new BigDecimal("0.0"))>0 &&
//                row.getLandedStoreCost().compareTo(new BigDecimal("0.0"))>0){
//            row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")))
//                    .divide((this.getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs());
//        }
//        if (this.getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0 && this.getMinOverride().compareTo(new BigDecimal("100000000000"))<0) {
//            if (this.getMinOverride() == row.getResultingEverydayRetailOverride()) {
//                row.setElasticizedEstimatedUnitVelocity(this.getWeeklyUSFWAtMin());
//            } else {
//                row.setElasticizedEstimatedUnitVelocity(((row.getResultingEverydayRetailOverride().subtract(this.getMinOverride()))
//                        .divide((this.getMinOverride()), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("-1.15"))
//                        .multiply(this.getWeeklyUSFWAtMin())).add(this.getWeeklyUSFWAtMin()));
//            }
//        }
//        if (this.getYearOneStoreCount()>0 && row.getElasticizedEstimatedUnitVelocity().compareTo(new BigDecimal("0.0"))>0) {
//            row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
//                    this.getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
//                    .setScale(0, RoundingMode.HALF_UP)).intValue()));
//        }
//    }
//}

/*
List for tooltips
 */
//    Label[] columnHeaderLabels = new Label[20];
//List<Label> columnHeaderLabels = new ArrayList<Label>(RTMNameColumnLabel, slottingPerSkuLabel,
//        freightOutPerUnitLabel,firstReceiverLabel,secondReceiverLabel,
//        thirdReceiverLabel,fourthReceiverLabel,landedStoreCostLabel,
//        resultingEveryDayRetailCalcdLabel,resultingEveryDayRetailOverrideLabel,
//        elasticizedEstimatedUnitVelocityLabel,estimatedAnnualVolumePerSkuLabel,
//        slottingPaybackPeriodLabel,postFreightPostSpoilsWeCollectLabel,
//        unspentTradePerUnitLabel,fourYearEqGpPerSkuLabel, fourYearEqGpPerUnitLabel);
////    Label RTMNameColumnLabel = new Label ("RTM Option");
//    //        RTMNameColumnLabel.setTooltip(new Tooltip("This is RTM Name Column tooltip"));
////        RTMNameColumn.setGraphic(RTMNameColumnLabel);
//
//
//    //    Tooltip[] columnHeaderTooltips = new Tooltip[20];
//    Tooltip[] columnHeaderTooltips = {RTMNameColumnTip, slottingPerSkuTip, freightOutPerUnitTip,
//            firstReceiverTip,secondReceiverTip,thirdReceiverTip,fourthReceiverTip,
//            landedStoreCostTip,resultingEveryDayRetailCalcdTip,resultingEveryDayRetailOverrideTip,
//            elasticizedEstimatedUnitVelocityTip, estimatedAnnualVolumePerSkuTip,
//            slottingPaybackPeriodTip, postFreightPostSpoilsWeCollectTip,
//            unspentTradePerUnitTip,fourYearEqGpPerSkuTip, fourYearEqGpPerUnitTip};
//    TableColumn[] columnsForTooltips = {RTMNameColumn,slottingPerSkuColumn,
//            freightOutPerUnitColumn,firstReceiverColumn,secondReceiverColumn,
//            thirdReceiverColumn,fourthReceiverColumn,landedStoreCostColumn,
//            resultingEverydayRetailCalcdColumn,resultingEverydayRetailOverrideColumn,
//            elasticizedEstimatedUnitVelocityColumn, estimatedAnnualVolumePerSkuColumn,
//            slottingPaybackPeriodColumn,postFreightPostSpoilsWeCollectColumn,
//            unspentTradePerUnitColumn, fourYearEqGpPerSkuColumn, fourYearEqGpPerUnitColumn};

/*
    Old Listener for EveryDayGPM Property()
     */


//            row.everyDayGPMProperty().addListener(new ChangeListener<BigDecimal>() {
//                private boolean changing;
//
//                @Override
//                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
//                    if (!changing) {
//                        try {
//                            changing = true;
//                            if (!row.getLandedStoreCost().equals(new BigDecimal(0.0)) && row.getLandedStoreCost() != null) {
//                                row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100"))).divide((row.getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP)); //newValue.subtract(new BigDecimal("100"))divide(getTotalValue(), RoundingMode.HALF_DOWN)));
//                                firstTableView.refresh();
//                            }
//                        } finally {
//                            changing = false;
//                        }
//                    }
//                }
//            });

    /*
     TO BE CHECKED, Old method for checking if landed store cost gets called through here or the listeener
     */

//    public void changeLandedStoreCostEvent (ActionEvent event) {
//        for (RTMOption row : firstTableView.getItems()) {
//            System.out.print("Am i being called");
//            if (!row.getLandedStoreCost().equals(new BigDecimal(0.0)) && row.getLandedStoreCost() !=null) {
//                row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")).
//                        divide(row.getEveryDayGPM().subtract(new BigDecimal("100")).multiply(new BigDecimal(-1)), 2, RoundingMode.HALF_UP)));
//                firstTableView.refresh();
//            }
//
//        }
//    }
        /*
        Textformatter attempt with double
         */

//        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
//            UnaryOperator<TextFormatter.Change> filter = c -> {
//                String text = c.getControlNewText();
//                if (validEditingState.matcher(text).matches()) {
//                    return c;
//                } else {
//                    return null;
//                }
//            };
//
//        StringConverter<Double> converter = new StringConverter<>() {
//
//            @Override
//            public Double fromString(String s) {
//                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
//                    return 0.0;
//                } else {
//                    return Double.valueOf(s);
//                }
//            }
//
//            @Override
//            public String toString(Double d) {
//                return d.toString();
//            }
//        };
//
//        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
//        TextFormatter<Double> textFormatter2 = new TextFormatter<>(converter, 0.0, filter);
//        TextFormatter<Double> textFormatter3 = new TextFormatter<>(converter, 0.0, filter);
//        everyDayGPMField.setTextFormatter(textFormatter);
//        spoilsFeesField.setTextFormatter(textFormatter2);
//        yearOneStoreCountField.setTextFormatter(textFormatter3);
//        textFormatter.valueProperty().addListener((ObservableValue<? extends BigDecimal> obs, BigDecimal oldValue, BigDecimal newValue) -> {
//                System.out.println("User entered value: " + newValue);
//                for (RTMOption row : firstTableView.getItems()) {
//                    row.setEveryDayGPM(newValue);
//                    row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")).divide(row.getEveryDayGPM().subtract(new BigDecimal("100")).multiply(new BigDecimal(-1)))));
//                    firstTableView.refresh();
//                    System.out.println(row.toString());
//                }
//            });

        /*
        Calculate the resulting everyday retail cost from everyday GPM, Landed Store Cost Property
        */
//        for (RTMOption row : firstTableView.getItems()) {
//            row.resultingEverydayRetailProperty().bind(Bindings.divide(Bindings.multiply(
//                    100,row.landedStoreCostProperty()), Bindings.subtract(100,row.everyDayGPMProperty())));;
//        }

        /*/
        Earlier version with bindings, adding listener to everyDayGPM
         */
//            row.resultingEverydayRetailProperty().bind(Bindings.divide(Bindings.multiply(
//                    100,row.landedStoreCostProperty()), Bindings.subtract(100,row.everyDayGPMProperty())));;
//            row.everyDayGPMProperty().addListener(new ChangeListener<BigDecimal>() {
//                private boolean changing;
//
//                @Override
//                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
//                    if (!changing) {
//                        try {
//                            changing = true;
//                            row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100"))).divide(row.getEveryDayGPM().subtract(new BigDecimal("100")).multiply(new BigDecimal(-1)))); //newValue.subtract(new BigDecimal("100"))divide(getTotalValue(), RoundingMode.HALF_DOWN)));
//                            firstTableView.refresh();
//                        } finally {
//                            changing = false;
//                        }
//                    }
//                }
//            });

        /*
           Binding to first receiver property attempt, would be too expensive in code
        */
//            row.firstReceiverProperty().addListener(new ChangeListener<BigDecimal>() {
//                private boolean changing;
//
//                @Override
//                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
////                    if (row.getLandedStoreCost()==new BigDecimal(0.0) || row.getLandedStoreCost() == null){
////                        row.setFirstReceiver(newValue);
////                        row.setLandedStoreCost(row.getFirstReceiver().max(row.getSecondReceiver().max(row.getThirdReceiver().max(row.getFourthReceiver()))));
////                        firstTableView.refresh();
////                    }
//                    if (!changing) {
//                        try {
//                            changing = true;
//                            row.setFirstReceiver(newValue);
//                            row.setLandedStoreCost(row.getFirstReceiver().max(row.getSecondReceiver().max(row.getThirdReceiver().max(row.getFourthReceiver()))));
//                            firstTableView.refresh();
//                        } finally {
//                            changing = false;
//                        }
//                    }
//                }
//            });



//Textformatter attempt two

//            Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
//            UnaryOperator<TextFormatter.Change> filter = c -> {
//                String text = c.getControlNewText();
//                if (validEditingState.matcher(text).matches()) {
//                    return c;
//                } else {
//                    return null;
//                }
//            };
//            TextFormatter<BigDecimal> textFormatter = new TextFormatter<>(new MoneyStringConverter(), BigDecimal.valueOf(0.0), filter);
//            everyDayGPMField.setTextFormatter(textFormatter);
            /*
        Set New Everyday GPM % to the input value
         */
//            textFormatter.valueProperty().addListener((ObservableValue<? extends BigDecimal> obs, BigDecimal oldValue, BigDecimal newValue) -> {
//                System.out.println("User entered value: " + newValue);
//                for (RTMOption row : firstTableView.getItems()) {
//                    row.setEveryDayGPM(newValue);
////                    row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")).divide(row.getEveryDayGPM().subtract(new BigDecimal("100")).multiply(new BigDecimal(-1)))));
//                    firstTableView.refresh();
//                    System.out.println(row.toString());
//                }
//            });

        /*
    create new binding
     */
//    private void configureResultingEveryDayRetailCalcdProperty(){
//        for (RTMOption row : firstTableView.getItems()) {
//////            row.resultingEverydayRetailProperty().bind(Bindings.divide(Bindings.multiply(
//////                    100,row.landedStoreCostProperty()), Bindings.subtract(100,row.everyDayGPMProperty())));;
////            row.everyDayGPMProperty().addListener(new ChangeListener<BigDecimal>() {
////                private boolean changing;
////
////                @Override public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
////                    if( !changing ) {
////                        try {
////                            changing = true;
////                            row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().add(new BigDecimal("100"))).divide(row.getEveryDayGPM().subtract(new BigDecimal("100")))); //newValue.subtract(new BigDecimal("100"))divide(getTotalValue(), RoundingMode.HALF_DOWN)));
////                        }
////                        finally {
////                            changing = false;
////                        }
////                    }
////                }
////            });
//            row.resultingEverydayRetailProperty().bind(Bindings.createObjectBinding(new Callable<BigDecimal>() {
//                @Override
//                public BigDecimal call() throws Exception {
//                    BigDecimal multiply = row.getLandedStoreCost().multiply(new BigDecimal("100"));
//                    BigDecimal multipl1 = row.getEveryDayGPM().subtract(new BigDecimal("100"));
//
//
////                            add(new BigDecimal("-100")); //multiply.divide(
////
//                    return (BigDecimal) multiply;
//                }
//            }, row.landedStoreCostProperty(), (Observable) row.getEveryDayGPM()));

//
//    }

