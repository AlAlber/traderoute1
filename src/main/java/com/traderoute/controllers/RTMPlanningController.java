package com.traderoute.controllers;

import com.traderoute.*;
import com.traderoute.cells.CustomNonEditCell;
import com.traderoute.charts.*;
import com.traderoute.data.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.BigDecimalStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;

/**
 * On this page a user can type in information about their Product for a a specific Retailer. In the brandNameBox a user
 * may select the brand name of a specific Product and then select the product class. On this page the user enters
 * Product specific information about a Retailer in the yearOneStoreCountField, everydayGpmField and spoilsFeesField,
 * which can later be displayed in the Product Class Reporting page.
 *
 * The tables consist of RTMOption Objects which allow the user to input information, first the name of the of the
 * Route-To-Market option(ex. Direct-To-Customer, where user directly transports their goods to a Retailer), then the
 * Slotting Investment, freight cost per unit, how much the first Receiver pays per unit(for ex. KeHE), second, third,
 * fourth if they exist. The table automatically calculates the Landed Store Cost from these 4 columns and if possible
 * calculates the resulting everyday retail price and a suggestion for the overridden retail price which the Retailer
 * usually will round to 0.09(ex. if resulting everyday retail calc'd= 6.43 -> resulting everyday override= 6.49). Once
 * the user enters a rough estimate of the weekly velocity Unit/Flavor/Sku/Week, the second table can calculate unit
 * velocities at different retail prices through the price elasticity index entered by the user on the Products Pricing
 * page. The rest of the calculations are explained in method docs. The graphs compare all the RTMOption for specific
 * fields.
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
     * Column for the cost the first receiver pays with this RTMOption (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> firstReceiverCol;
    /**
     * Column for the cost the second receiver pays with this RTMOption (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> secondReceiverColumn;

    /**
     * Column for the cost the third receiver pays with this RTMOption (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> thirdReceiverColumn;

    /**
     * Column for the cost the fourth receiver pays with this RTMOption (Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourthReceiverColumn;

    /**
     * Column for the landed Store Cost of this product with this RTMOption (Not Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> landedStoreCostColumn;

    /**
     * Column for the resulting Everyday Retail Cost of this product with this RTMOption (Not Editable).
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> everydayRetailCalcdCol;

    /**
     * Column for the resulting Everyday Retail Override where user can input the final price of the product, likely
     * rounded to X.X9 (Editable).
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
     * Elasticized unit velocity, where unit velocities are adjusted based on the price elasticity of the product.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> elasticizedUnitVelocityColumn;

    /**
     * Column for the Annual Volume Sold Per Sku based on unit Velocity on year one store count.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> annualVolumePerSkuColumn;

    /**
     * Column showing the expected time period it will take to receive back the slotting investment.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodColumn;

    /**
     * Column showing how much the user collects after accounting for spoils, fees and freight cost.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsPerUnitCol;

    /**
     * Column showing how much unspent trade money is available per unit with this RTMOption.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitColumn;
    /**
     * Column showing equivalized gross profit per sku in four years with this RTMOption.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuColumn;
    /**
     * Column showing equivalized gross profit per unit in four years with this RTMOption.
     */
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitColumn;
    /**
     * Retailer is main object between scenes, right now still has default value. //TODO
     */
    private SimpleObjectProperty<Retailer> retailer = new SimpleObjectProperty<>();

    /**
     * TextField to insert Store Count of this RetailerProduct (specific Product being sold by retailer) in first year.
     */
    @FXML
    private IntegerTextField yearOneStoreCountField =
            new IntegerTextField(0, 0,10000, true);
    /**
     * TextField to insert Everyday Gross Profit Margin Percentage of RetailerProduct.
     */
    @FXML
    private BigDecimalTextField everydayGpmField =
            new BigDecimalTextField(new BigDecimal("40.0"),
                    new BigDecimal("1.0"), new BigDecimal("99.0"), false);
    /**
     * TextField to insert percentage of Spoils and Fees of RetailerProduct.
     */
    @FXML
    private BigDecimalTextField spoilsFeesField =
            new BigDecimalTextField(new BigDecimal("3.0"),
                    new BigDecimal("1.0"), new BigDecimal("99.0"), false);
    /**
     * TextField to insert the Weekly Velocity U/F/S/W of product at the minimum price in the everyday retail override
     * column.
     */
    @FXML
    private BigDecimalTextField weeklyUfswAtMinField =
            new BigDecimalTextField(new BigDecimal("1.0"),
                    new BigDecimal("0.0"), new BigDecimal("1000.0"), false);
    /**
     * Label that denotes the max of the retail override column.
     */
    @FXML
    private Label maxOverrideLabel;
    /**
     * Combobox where user can select the brand name of a Product.
     */
    @FXML
    private BrandNameComboBox brandNameBox = new BrandNameComboBox();
    /**
     * ComboBox where user can select brand name specific product classes.
     */
    @FXML
    private ProductClassComboBox productClassBox = new ProductClassComboBox();

    /**
     * Displays unit list cost of the selected Product. (General Product information is inserted on Product pricing page
     * by user)
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
     * BarCharts to display relevant table data. X-Axis has RTM Option Name, Y-Axis the respective value.
     */
    @FXML
    private LandedStoreCostChart landedStoreCostChart= new LandedStoreCostChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private EverydayRetailCalcdChart everydayRetailCalcdChart = new EverydayRetailCalcdChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private ElasticizedUnitVelocityChart elasticizedUnitVelocityChart = new ElasticizedUnitVelocityChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private AnnualVolumePerSkuChart annualVolumePerSkuChart = new AnnualVolumePerSkuChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private SlottingPaybackPeriodChart slottingPaybackPeriodChart = new SlottingPaybackPeriodChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private PostSpoilsPostFreightChart unspentTradePerUnitChart = new PostSpoilsPostFreightChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private UnspentTradePerUnitChart postSpoilsPostFreightChart = new UnspentTradePerUnitChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private FourYearEqGpPerSkuChart fourYearEqGpPerSkuChart = new FourYearEqGpPerSkuChart(new CategoryAxis(), new NumberAxis());
    @FXML
    private FourYearEqGpPerUnitChart fourYearEqGpPerUnitChart = new FourYearEqGpPerUnitChart(new CategoryAxis(), new NumberAxis());

    /**
     * Called when new page is created from FXML. - Sets Cell Value factories to reflect RTM Option data. - Sets unique
     * item brand names to brandnamebox - Assigns Converters to brandNameBox, productClassBox - Makes year One Store
     * Count accept only Integers. - Assigns TextFormatter with converter and input filter for number values in double
     * format (ex. 0.00) in everydayGpmField, spoilsFeesField, weeklyUfswAtMinField. - sets default RTMOption items for
     * both tables. - sets data to charts. - Sets editable and non-editable Columns and Tables. - Adds listeners to rows
     * for chart updates. - Sets Cell Factories, which determine how Column cells are displayed for both tables.
     * 
     * @param url
     *            default javafx param in initialize method.
     * @param resourceBundle
     *            default javafx param in initialize method.
     */
    @FXML
    private HBox chartHBox;
    @FXML
    private VBox textfieldVBox;
    @FXML
    private VBox productVBox;
    @FXML
    private VBox weeklyUfswVBox;

    private SimpleObjectProperty<RetailerProduct> currentRetailerProduct;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        chartHBox.getChildren().addAll(landedStoreCostChart, everydayRetailCalcdChart,
                elasticizedUnitVelocityChart, annualVolumePerSkuChart, slottingPaybackPeriodChart,
                postSpoilsPostFreightChart, unspentTradePerUnitChart, fourYearEqGpPerSkuChart,
                fourYearEqGpPerUnitChart);
        textfieldVBox.getChildren().addAll(yearOneStoreCountField, everydayGpmField, spoilsFeesField);
        weeklyUfswVBox.getChildren().add(weeklyUfswAtMinField);
        yearOneStoreCountField.setId("yearOneStoreCountField");
        everydayGpmField.setId("everydayGpmField");
        spoilsFeesField.setId("spoilsFeesField");
        weeklyUfswAtMinField.setId("weeklyUfswAtMinField");
        yearOneStoreCountField.addEventHandler(ActionEvent.ACTION, e -> changeYearOneStoreCount());
        everydayGpmField.addEventHandler(ActionEvent.ACTION, e -> changeEveryDayGpmCellEvent());
        spoilsFeesField.addEventHandler(ActionEvent.ACTION, e -> changeSpoilsAndFeesEvent());
        weeklyUfswAtMinField.addEventHandler(ActionEvent.ACTION, e -> changeWeeklyUSFWAtMinEvent());

        currentRetailerProduct = new SimpleObjectProperty<>(MenuController.getRetailerProducts().get(0));
        rtmPlanningTable1.getItems().stream().forEach(e -> e.setRetailerProduct(currentRetailerProduct.get()));

        productVBox.getChildren().add(0, brandNameBox);
        productVBox.getChildren().add(1, productClassBox);
        brandNameBox.setOnAction(e -> changeBrandComboboxEvent());
        productClassBox.setOnAction(e -> changeProductClassComboboxEvent());

        setCellValueFactories();

        brandNameBox.setUniqueItems(MenuController.getExampleProducts());

        updateCharts();

        rtmPlanningTable1.setEditable(true);
        landedStoreCostColumn.setEditable(false);
        everydayRetailCalcdCol.setEditable(false);

        setUpListeners();
        setCellFactories();
    }

    /**
     * Set Up listeners in all the rows (which get called to make the calculations.Also sets up listeners for
     * everyDayGPM,landed store Cost Property. updateChart method takes boolean arguments lSC-landedstorecost,
     * eRC-everydayRetailCalcd, e
     */
    public void setUpListeners() {
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setupListeners();
            row.landedStoreCostProperty().addListener(((arg, oldVal, newVal) -> {
                landedStoreCostChart.updateChart(rtmPlanningTable1.getItems());
            }));
            row.rtmNameProperty().addListener(((arg, oldVal, newVal) -> {
                updateCharts();
            }));
            row.everydayRetailCalcdProperty().addListener(((arg, oldVal, newVal) -> {
                maxOverrideLabel.setText("of $" + getMinOverride());
                updateCharts();
            }));
            row.everydayRetailOverrideProperty().addListener(((arg, oldVal, newVal) -> {
                getSpecs().setMinOverride(getMinOverride());
                rtmPlanningTable1.refresh();
                row.updateElasticizedUnitVelocity();
                maxOverrideLabel.setText("of $" + getMinOverride());
                rtmPlanningTable1.refresh();
            }));
        }
    }
    public RetailerProductSpecs getSpecs() {
        return currentRetailerProduct.get().getSpecs();
    }

    /**
     * Listener reacts to event on brandNameBox. Sets Product info labels and sets items in product- ClassBox
     */
    public void changeBrandComboboxEvent() {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        listLabel.setText("List = $");
        fobLabel.setText("F.O.B. = $");
        net1GoalLabel.setText("Net 1 Goal = $");
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :  % Volume");
        ObservableList<Product> productsWithBrandName = productClassBox.getCorrectProductClasses(MenuController.getExampleProducts(),
                brandNameBox.getSelectionModel().getSelectedItem());
        productClassBox.setItems(productsWithBrandName);
    }

    /**
     * A different option was chosen from the product combo box now retailerProduct needs to be updated, labels need to
     * be updated and all the RTM Option's products need to be updated (//TODO this functionality could be done better)
     */
    public void changeProductClassComboboxEvent() {
        Product selectedProduct = productClassBox.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            productClassBox.setPromptText("Now Select a Product Class");
        } else {
            listLabel.setText("List = $" + selectedProduct.getUnitListCost());
            fobLabel.setText("F.O.B. = $" + selectedProduct.getUnitFobCost());
            net1GoalLabel.setText("Net 1 Goal = $" + selectedProduct.getUnitNet1Goal());
            elasticityRatioLabel
                    .setText("Elasticity Ratio = +1% Price :" + selectedProduct.getElasticityMultiple() + "% Volume");
            for (RTMOption row : rtmPlanningTable1.getItems()) {
                // FIX THIS ASAP, should update retailerProduct instead
//                row.setProduct(selectedProduct);
            }
//            updateRetailerProduct(selectedProduct);
        }
    }

    /**
     * This method return the index of the selected product from the product class box and returns its index within the
     * RetailerProducts of the Retailer.
     * 
     * @param product
     *            product from the product class Box
     * 
     * @return index of the specific Product within the RetailerProduct's of the retailer
     */
    public int getProductIndex(final Product product) {
        // create addProduct method in product page
        int productIndex = -1;
        for (RetailerProduct retailerProduct : getRetailer().getRetailerProducts()) {
            if (retailerProduct.getProduct().equals(product)) {
                productIndex = getRetailer().getRetailerProducts().indexOf(retailerProduct);
            }
        }
        return productIndex;
        // TEMPORARY
    }

    /**
     * Updates RetailerProduct, so all tables need to be updated, all textfields,labels (except column header labels)
     * and charts.
     * 
     * @param product
     *            Product given in order to select or add RetailerProduct to Retailer.
     */

    public void updateRetailerProduct(final Product product) {
        int productIndex = getProductIndex(product);
        retailer.get().setcurrentRetailerProductIndex(productIndex);

        // TEMPORARILY GET PRODUCT INDEX THROUGH RETAILERPRODUCTS
        rtmPlanningTable1.setItems(getRetailer().getRetailerProducts().get(productIndex).getRtmOptions());
        rtmPlanningTable2.setItems(getRetailer().getRetailerProducts().get(productIndex).getRtmOptions());
        rtmPlanningTable2.refresh();
        setUpListeners();
        ObservableList<RetailerProduct> retailerProducts = getRetailer().getRetailerProducts();
        RetailerProduct currentRetailerProduct1 = retailerProducts.get(getRetailer().getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions = currentRetailerProduct1.getRtmOptions();
        this.rtmPlanningTable1.setItems(currentRtmOptions);
        setUpListeners();
        // CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION
        // INSTEAD OF PRODUCT SELECTION
        this.rtmPlanningTable2.setItems(currentRtmOptions);

        listLabel.setText("List = $" + currentRetailerProduct1.getProduct().getUnitListCost());
        fobLabel.setText("F.O.B. = $" + currentRetailerProduct1.getProduct().getUnitFobCost());
        net1GoalLabel.setText("Net 1 Goal = $" + currentRetailerProduct1.getProduct().getUnitNet1Goal());
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :"
                + currentRetailerProduct1.getProduct().getElasticityMultiple() + "% Volume");
        // Stuff that should be implemented differently
        this.weeklyUfswAtMinField.setText(getSpecs().getWeeklyUSFWAtMin().toString());
        for (RTMOption row : rtmPlanningTable1.getItems()) {
            row.setRetailerProduct(currentRetailerProduct.get());
//            row.setProduct(currentRetailerProduct.getProduct());
//            row.setEverydayGPM(getRetailer().getEverydayGPM());
//            row.setSpoilsAndFees(
//                    getRetailer().getSpoilsFees().divide((new BigDecimal("100")), divisionScale, RoundingMode.HALF_UP));
            maxReceivers(row);
            row.updateResultingEverydayRetailCald();
        }
        updateCharts();
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
            BigDecimal currentNumber = row.getEverydayRetailOverride();
            if (currentNumber.compareTo(smallest) < 0 && currentNumber.compareTo(new BigDecimal("0.0")) > 0) {
                smallest = currentNumber;
            }
        }
        return smallest;
    }

    /**
     * Get elasticized velocities from weeklyUSFWAtMin Field and refresh second table to see results from row listeners.
     * update charts.
     */
    public void changeWeeklyUSFWAtMinEvent() {
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        getSpecs().setMinOverride(getMinOverride());
        getSpecs().setWeeklyUSFWAtMin(weeklyUfswAtMinField.getValue());
//        for (RTMOption row : rtmPlanningTable2.getItems()) {
//            row.setMinOverride(getMinOverride());
//            row.setWeeklyUSFWAtMin(weeklyUfswAtMinField.getValue());
//        }
        rtmPlanningTable2.refresh();
        updateCharts();
    }

    /**
     * Get elasticized velocities from weeklyUSFWAtMin Field and do calculation if possible.
     * 
     * @param editedCell
     *            Cell that edited in everyday Retail Override Column.
     */
    public void changeOverrideEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setEverydayRetailOverride(new BigDecimal(editedCell.getNewValue().toString()));
        getSpecs().setMinOverride(getMinOverride());

//        for (RTMOption row : rtmPlanningTable2.getItems()) {
//            row.setMinOverride(getMinOverride());
//        }
        rtmPlanningTable2.refresh();
        updateCharts();
    }

    /**
     * Year One Store Count changed event. Set to yearOneStoreCount Field in FXML. Sets Year One Store Count Field in
     * RTMOptions. Also updates charts.
     */
    public void changeYearOneStoreCount() {
        System.out.println("hello");
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        getSpecs().setYearOneStoreCount(yearOneStoreCountField.getValue());
//        for (RTMOption row : rtmPlanningTable2.getItems()) {
//            row.setYearOneStoreCount(yearOneStoreCountField.getValue());
//        }

        System.out.println(rtmPlanningTable1.getItems().get(0).getYearOneStoreCount());
        rtmPlanningTable2.refresh();
        updateCharts();
    }

    /**
     * //TODO Set RetailerProduct EverydayGpm. Set RTMProduct EverydayGpm in rows which calls listeners, updateChart.
     */
    public void changeEveryDayGpmCellEvent() {
        getSpecs().setEverydayGpm(everydayGpmField.getValue());
//        for (RTMOption row : rtmPlanningTable1.getItems()) {
//            row.setEverydayGPM(everydayGpmField.getValue());
//        }
        updateCharts();
    }

    /**
     * //TODO Set RetailerProduct Spoils Fees. Set spoils fees in row and update necessary values. spoils fees is sent
     * as decimal percentage to RTMOption.
     */
    public void changeSpoilsAndFeesEvent() {
        getSpecs().setSpoilsAndFees(spoilsFeesField.getValue()
                .divide((new BigDecimal("100")),
                        divisionScale, RoundingMode.HALF_UP));
//        for (RTMOption row : rtmPlanningTable1.getItems()) {
//            row.setSpoilsAndFees(
//                    spoilsFeesField.getValue()
//                            .divide((new BigDecimal("100")),
//                                    divisionScale, RoundingMode.HALF_UP));
//        }
        updateCharts();
    }

    /**
     * Slotting per Sku input registered from tableview cell, update second table and relevant charts.
     * 
     * @param editedCell
     *            cell edited by user.
     */
    public void changeSlottingPerSkuCellEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setSlottingPerSku(new BigDecimal(editedCell.getNewValue().toString()));
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());
        rtmPlanningTable2.refresh();
        updateCharts();
    }

    /**
     * Freight out per unit (Money that User pays for transporting goods) input registered from tableview cell, set
     * value in RTMOption which calls listeners.
     * 
     * @param editedCell
     *            cell edited by user.
     */
    public void changeFreightOutPerUnitCellEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setFreightOutPerUnit(new BigDecimal(editedCell.getNewValue().toString()));
        updateCharts();
    }

    /**
     * First Receiver Pays (Money that first Receiver (ex. KeHE) pays per unit) input registered from tableview cell,
     * set value in RTMOption which calls listeners.
     * 
     * @param editedCell
     *            cell edited by user.
     */
    public void changeFirstReceiverCellEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setFirstReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(getFocusedRtmOption());
        updateCharts();
    }

    /**
     * Second Receiver Pays (Money that second Receiver (ex. KeHE) pays per unit) input registered from tableview cell,
     * set value in RTMOption which calls listeners.
     * 
     * @param editedCell
     *            cell edited by user.
     */
    public void changeSecondReceiverCellEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setSecondReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(getFocusedRtmOption());
    }

    /**
     * Third Receiver Pays (Money that third Receiver (ex. KeHE) pays per unit) input registered from tableview cell,
     * set value in RTMOption which calls listeners.
     * 
     * @param editedCell
     *            cell edited by user.
     */
    public void changeThirdReceiverCellEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setThirdReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(getFocusedRtmOption());
    }

    /**
     * Fourth Receiver Pays (Money that fourth Receiver (ex. KeHE) pays per unit) input registered from tableview cell,
     * set value in RTMOption which calls listeners.
     * 
     * @param editedCell
     *            cell edited by user.
     */
    public void changeFourthReceiverCellEvent(final TableColumn.CellEditEvent editedCell) {
        getFocusedRtmOption().setFourthReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(getFocusedRtmOption());
    }

    /**
     * Calculates the maximum value from first,second,third and fourth receiver column values for a specifc RTMOption.
     * 
     * @param selectedOption
     *            RTMOption where max value from receivers needs to be calculated.
     */
    public void maxReceivers(final RTMOption selectedOption) {
        BigDecimal maxReceivers = selectedOption.getFirstReceiver().max(selectedOption.getSecondReceiver()
                .max(selectedOption.getThirdReceiver().max(selectedOption.getFourthReceiver())));
        if (maxReceivers.compareTo(new BigDecimal("0.0")) > 0) {
            selectedOption.setLandedStoreCost(maxReceivers);
        }
    }

    /**
     * Updates all bar charts.
     */
    public void updateCharts() {
        ObservableList<RTMPlanningChart> charts = FXCollections
                .observableArrayList(landedStoreCostChart,everydayRetailCalcdChart,
                    elasticizedUnitVelocityChart, annualVolumePerSkuChart, slottingPaybackPeriodChart,
                    postSpoilsPostFreightChart, unspentTradePerUnitChart, fourYearEqGpPerSkuChart,
                    fourYearEqGpPerUnitChart);
        charts.stream().forEach(chart -> chart.updateChart(rtmPlanningTable1.getItems()));
    }

    private void switchScenes(String fxml) throws IOException {
        App.getNewController(fxml).setRetailer(retailer.get());
    }

    @FXML
    private void switchToAssortment() throws IOException {
        System.out.println("workign");
        switchScenes("assortment");

    }
    @FXML
    private void switchToRetailerSelection() throws IOException {
        switchScenes("retailerSelection");
    }
    @FXML
    private void switchToPricingPromotion() throws IOException {
        switchScenes("pricingPromotion");
    }

    /**
     * Set up cellValue factories, which look for properties of RTMOption item in the table.
     */
    public void setCellValueFactories() {
        rtmNameCol1.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
        slottingPerSkuCol.setCellValueFactory(cellData -> cellData.getValue().slottingPerSkuProperty());
        freightOutPerUnitCol.setCellValueFactory(cellData -> cellData.getValue().freightOutPerUnitProperty());
        firstReceiverCol.setCellValueFactory(cellData -> cellData.getValue().firstReceiverProperty());
        secondReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().secondReceiverProperty());
        thirdReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().thirdReceiverProperty());
        fourthReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().fourthReceiverProperty());
        landedStoreCostColumn.setCellValueFactory(cellData -> cellData.getValue().landedStoreCostProperty());
        everydayRetailCalcdCol.setCellValueFactory(cellData -> cellData.getValue().everydayRetailCalcdProperty());
        everydayRetailOverrideCol
                .setCellValueFactory(cellData -> cellData.getValue().everydayRetailOverrideProperty());
        elasticizedUnitVelocityColumn
                .setCellValueFactory(cellData -> cellData.getValue().elasticizedUnitVelocityProperty());
        annualVolumePerSkuColumn.setCellValueFactory(cellData -> cellData.getValue().annualVolumePerSkuProperty());
        slottingPaybackPeriodColumn
                .setCellValueFactory(cellData -> cellData.getValue().slottingPaybackPeriodProperty());
        postFreightPostSpoilsPerUnitCol
                .setCellValueFactory(cellData -> cellData.getValue().postSpoilsPostFreightPerUnitProperty());
        unspentTradePerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().unspentTradePerUnitProperty());
        fourYearEqGpPerSkuColumn.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerSkuProperty());
        fourYearEqGpPerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerUnitProperty());
        rtmNameColumn2.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
    }

    /**
     * Sets cell factories for every column.
     */
    public void setCellFactories() {

        rtmNameCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        slottingPerSkuCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        freightOutPerUnitCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        firstReceiverCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        landedStoreCostColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        everydayRetailCalcdCol.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        everydayRetailOverrideCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        elasticizedUnitVelocityColumn.setCellFactory(tc -> new CustomNonEditCell("", " U/S/F/W"));
        annualVolumePerSkuColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(final BigDecimal item, final boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(String.format("%,.0f", item, 0, RoundingMode.HALF_UP) + " Units");
                }
            }
        });
        slottingPaybackPeriodColumn.setCellFactory(tc -> new CustomNonEditCell("", " Years"));
        postFreightPostSpoilsPerUnitCol.setCellFactory(tc -> new CustomNonEditCell("$", " Per Unit"));
        unspentTradePerUnitColumn.setCellFactory(tc -> new CustomNonEditCell("$", " Per Unit"));
        fourYearEqGpPerSkuColumn.setCellFactory(tc -> new CustomNonEditCell("$", " Per Sku"));
        fourYearEqGpPerUnitColumn.setCellFactory(tc -> new CustomNonEditCell("$", " Per Unit"));
    }

    /**
     * @return Retailer Retailer contains all the information, including RetailerProducts and Promotional Plans
     *         (PromoPlan)s.
     */
    public Retailer getRetailer() {
        return retailer.get();
    }

    /**
     * @param selectedRetailer
     *            selected Retailer from RetailerSelection page.
     */
    public void setRetailer(final Retailer selectedRetailer) {
        this.retailer.set(selectedRetailer);
        ObservableList<RetailerProduct> retailerProducts = getRetailer().getRetailerProducts();
        RetailerProduct thisRetailerProduct = retailerProducts.get(getRetailer().getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions = thisRetailerProduct.getRtmOptions();
        // this.firstTableView.setItems(currentRtmOptions);
        // setUpListeners();
        // CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION
        // INSTEAD OF PRODUCT SELECTION
        // this.secondTableView.setItems(currentRtmOptions);

        currentRetailerProduct.set(getRetailer().getRetailerProducts().get(0));
//        rtmPlanningTable1.getItems().stream().forEach(e -> e.setRetailerProduct(currentRetailerProduct.get()));

        updateRetailerProduct(thisRetailerProduct.getProduct());

        this.brandNameBox.setUniqueItems(MenuController.getExampleProducts());
        this.brandNameBox.valueProperty().setValue(thisRetailerProduct.getProduct());
        this.productClassBox.setItems(productClassBox.getCorrectProductClasses(MenuController.getExampleProducts(), brandNameBox.getValue()));
        this.productClassBox.valueProperty().setValue(thisRetailerProduct.getProduct());
        // listLabel.setText("List = $" + currentRetailerProduct.getProduct().getUnitListCost());
        // fobLabel.setText("F.O.B. = $" + currentRetailerProduct.getProduct().getUnitFobCost());
        // net1GoalLabel.setText("Net 1 Goal = $" + currentRetailerProduct.getProduct().getUnitNet1Goal());
        // elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :" +
        // currentRetailerProduct.getProduct().getElasticityMultiple() + "% Volume");
        this.yearOneStoreCountField.setText(String.valueOf(selectedRetailer.getYearOneStoreCount()));
        this.everydayGpmField.setText(selectedRetailer.getEverydayGPM().toString());
        this.spoilsFeesField.setText(selectedRetailer.getSpoilsFees().toString());
        // Stuff that should be implemented differently
        // this.weeklyUfswAtMinField.setText(currentRtmOptions.get(0).getWeeklyUSFWAtMin().toString());
        // for (RTMOption row: firstTableView.getItems()){
        // row.setProduct(currentRetailerProduct.getProduct());
        // row.setYearOneStoreCount(getRetailer().getYearOneStoreCount());
        // row.setEverydayGPM(getRetailer().getEverydayGPM());
        // row.setSpoilsAndFees(getRetailer().getSpoilsFees().divide((new BigDecimal("100")), 4 ,
        // RoundingMode.HALF_UP));
        // maxReceivers(row);
        // row.updateResultingEverydayRetailCald();
        // updateChart(true,true,true,true,true,true,true,true,true );
        // }
        // secondTableView.setVisible(false);
        // secondTableView.setVisible(true);
        // secondTableView.refresh();

    }

    public RTMOption getFocusedRtmOption() {
        return rtmPlanningTable1.getSelectionModel().getSelectedItem();
    }
}
