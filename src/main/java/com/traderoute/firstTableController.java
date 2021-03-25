package com.traderoute;
import javafx.beans.property.SimpleIntegerProperty;
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

import static com.traderoute.AssortmentController.convertToDate;

public class firstTableController implements Initializable {


    // Assign Values to
    @FXML
    private TableView<RTMOption> secondTableView;
    @FXML
    private TableView<RTMOption> firstTableView;
    @FXML
    private TableColumn<RTMOption, String> RTMNameColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> slottingPerSkuColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> freightOutPerUnitColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> firstReceiverColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> secondReceiverColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> thirdReceiverColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourthReceiverColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> landedStoreCostColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> resultingEverydayRetailCalcdColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> resultingEverydayRetailOverrideColumn;
    @FXML
    private TableColumn<RTMOption, String> RTMNameColumn2;
    @FXML
    private TableColumn<RTMOption, BigDecimal> elasticizedEstimatedUnitVelocityColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> estimatedAnnualVolumePerSkuColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsWeCollectPerUnitColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuColumn;
    @FXML
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitColumn;

    private SimpleObjectProperty<Retailer> retailer= new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),0,  new BigDecimal("40") , 158,new BigDecimal("3.0")));;

    @FXML
    private TextField yearOneStoreCountField;
    @FXML
    private TextField everydayGpmField;
    @FXML
    private TextField spoilsFeesField;
    @FXML
    private TextField weeklyUfswAtMinField;

    @FXML
    private Label maxOverrideLabel;

    /*
    Initialization for tooltips
     */
    @FXML
    private final Label RTMNameColumnLabel = new Label("Route to market options");
    @FXML
    private final Label RTMNameColumnLabel2 = new Label("Route to market options");
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

    @FXML
    private final Tooltip RTMNameColumnTip =
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

    @FXML
    private ComboBox<RetailerProduct> productClassBox;
    @FXML
    private ComboBox<RetailerProduct> brandNameBox;

    @FXML
    private Label listLabel;
    @FXML
    private Label fobLabel;
    @FXML
    private Label net1GoalLabel;
    @FXML
    private Label elasticityRatioLabel;

    @FXML
    private BarChart<?, ?> landedStoreCostChart;
    @FXML
    private BarChart<?, ?> resultingEverydayRetailCalcdChart;
    @FXML
    private BarChart<?, ?> elasticizedEstimatedUnitVelocityChart;
    @FXML
    private BarChart<?, ?> estimatedAnnualVolumeChart;
    @FXML
    private BarChart<?, ?> slottingPaybackPeriodChart;
    @FXML
    private BarChart<?,?> unspentTradePerUnitChart;
    @FXML
    private BarChart<?, ?> postSpoilsPostFreightWeCollectChart;
    @FXML
    private BarChart<?, ?> fourYearEqGpPerSkuChart;
    @FXML
    private BarChart<?, ?> fourYearEqGpPerUnitChart;

    private SimpleObjectProperty<RetailerProduct> currentRetailerProduct;
    private SimpleObjectProperty<ObservableList<RTMOption>> currentRtmOptions;



    XYChart.Series<String,BigDecimal> landedStoreCostData = new XYChart.Series();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Set up cell value factories
        setCellValueFactories();

        // Set unique values for brandcombobox and set converters for both brand and productCombobox

        brandNameBox.setItems(getUniqueBrandNames(getRetailer().getRetailerProducts()));
        brandNameBox.setConverter(getBrandComboboxConverter());
        productClassBox.setConverter(getProductComboboxConverter());


        // Restrict input fields to only accept text in Integer or Double format
        yearOneStoreCountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    yearOneStoreCountField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        everydayGpmField.setTextFormatter(new TextFormatter<>(getDoubleInputConverter(), 0.0, getDoubleInputFilter()));
        spoilsFeesField.setTextFormatter(new TextFormatter<>(getDoubleInputConverter(), 0.0, getDoubleInputFilter()));
        weeklyUfswAtMinField.setTextFormatter(new TextFormatter<>(getDoubleInputConverter(), 0.0, getDoubleInputFilter()));

        //Set dummy data
        firstTableView.setItems(getRTMOptions());
        secondTableView.setItems(firstTableView.getItems());


        // Set Bar Chart
        landedStoreCostChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(1)}));
        resultingEverydayRetailCalcdChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(2)}));
        elasticizedEstimatedUnitVelocityChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(3)}));
        estimatedAnnualVolumeChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(4)}));
        slottingPaybackPeriodChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(5)}));
        postSpoilsPostFreightWeCollectChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(6)}));
        unspentTradePerUnitChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(7)}));
        fourYearEqGpPerSkuChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(8)}));
        fourYearEqGpPerUnitChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(9)}));

        // Add Labels to list, give them tooltips
        setToolTipsFirstAndSecondTableview();

        //Set editable columns
        firstTableView.setEditable(true);
        landedStoreCostColumn.setEditable(false);
        resultingEverydayRetailCalcdColumn.setEditable(false);

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
        for (RTMOption row : firstTableView.getItems()) {
//
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
                firstTableView.refresh();
                row.updateElasticizedUnitVelocity();
                maxOverrideLabel.setText("of $" + getMinOverride());
                System.out.println("Why is this no longer happening?");
                firstTableView.refresh();
            }));
        }
    }

    /*
    For Brand Name Bo
     */
    public ObservableList<RetailerProduct> getUniqueBrandNames(ObservableList<RetailerProduct> retailerProducts) {
        ObservableList<RetailerProduct> uniqueBrandNames = FXCollections.observableArrayList();
        ObservableList<String> uniqueBrandNameStrings = FXCollections.observableArrayList();
        for (RetailerProduct retailerProduct : retailerProducts) {
            if (!uniqueBrandNameStrings.contains(retailerProduct.getProduct().getBrandName())) {
                uniqueBrandNameStrings.add(retailerProduct.getProduct().getBrandName());
                uniqueBrandNames.add(retailerProduct);
            }
        }
        return uniqueBrandNames;
    }
    public ObservableList<RetailerProduct> getCorrespondingProductClasses(ObservableList<RetailerProduct> retailerProducts,RetailerProduct selectedBrandName) {
        ObservableList<RetailerProduct> correspondingProductClasses = FXCollections.observableArrayList();
        //Set up product combobox and make it display product class
        for (RetailerProduct retailerProduct : retailerProducts) {
            if (retailerProduct.getProduct().getBrandName().equals(selectedBrandName.getProduct().getBrandName())) {
                correspondingProductClasses.add(retailerProduct);
            }
        }
        return correspondingProductClasses;
    }

    /*
    Product class changed Event
     */
    public void changeBrandComboboxEvent(ActionEvent event) {
        RetailerProduct selectedBrandName = brandNameBox.getSelectionModel().getSelectedItem();
        secondTableView.setItems(firstTableView.getItems());
        listLabel.setText("List = $");
        fobLabel.setText("F.O.B. = $");
        net1GoalLabel.setText("Net 1 Goal = $");
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :  % Volume");
        productClassBox.setItems(getCorrespondingProductClasses(retailer.get().getRetailerProducts(), selectedBrandName));
    }
    /*
    Product class changed Event
     */
    public void changeProductComboboxEvent(ActionEvent event) {

        RetailerProduct selectedProduct = productClassBox.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            productClassBox.setPromptText("Now Select a Product Class");
        } else {
        listLabel.setText("List = $" + selectedProduct.getProduct().getUnitListCost());
        fobLabel.setText("F.O.B. = $" + selectedProduct.getProduct().getUnitFobCost());
        net1GoalLabel.setText("Net 1 Goal = $" + selectedProduct.getProduct().getUnitNet1Goal());
        elasticityRatioLabel.setText("Elasticity Ratio = +1% Price :" + selectedProduct.getProduct().getElasticityMultiple() + "% Volume");
        for (RTMOption row : firstTableView.getItems()) {
            row.setProduct(selectedProduct.getProduct());
        }
    }

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


        secondTableView.refresh();
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    /*
    Return minimum value from override column
     */
    public BigDecimal getMinOverride() {
        BigDecimal smallest = new BigDecimal("100000000000");
        for (RTMOption row : firstTableView.getItems()) {
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
        secondTableView.setItems(firstTableView.getItems());
        for (RTMOption row : secondTableView.getItems()) {
            row.setMinOverride(getMinOverride());
            row.setWeeklyUSFWAtMin(getWeeklyUSFWAtMin());
        }
//        firstTableView.refresh();
//        secondTableView.refresh();
        updateChart(false,false,true,true,true,true,true,true,true);
    }

    /*
    Get estimated annual volume/sku
    */
    public void changeYearOneStoreCount(ActionEvent event) {
        secondTableView.setItems(firstTableView.getItems());
        for (RTMOption row : secondTableView.getItems()) {
            row.setYearOneStoreCount(getYearOneStoreCount());
        }
        secondTableView.refresh();
        updateChart(false,false,false,true,true,true,true,true,true);
    }

    /*
    Set EverydayGPm and do calculation if possible
    */
    public void changeEveryDayGPMCellEvent(ActionEvent event) {
        for (RTMOption row : firstTableView.getItems()) {
            row.setEverydayGPM(this.getEveryDayGPM());
        }
        updateChart(false,true,false,false,false,false,false,false,false);
    }

    /*
    Set EverydayGPm and do calculation if possible
    */
    public void changeSpoilsAndFeesEvent(ActionEvent event) {
        for (RTMOption row : firstTableView.getItems()) {
            row.setSpoilsAndFees(getSpoilsAndFees().divide((new BigDecimal("100")), 4 , RoundingMode.HALF_UP));
        }
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    // Change Slotting Per Sku
    public void changeSlottingPerSkuCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setSlottingPerSku(new BigDecimal(editedCell.getNewValue().toString()));
        secondTableView.setItems(firstTableView.getItems());
        secondTableView.refresh();
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    // Change Override
    public void changeResultingEverydayRetailOverride(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setResultingEverydayRetailOverride(new BigDecimal(editedCell.getNewValue().toString()));
    }
    /*
    Calculate max from the receivers
    */
    public void changeFreightOutPerUnitCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFreightOutPerUnit(new BigDecimal(editedCell.getNewValue().toString()));
        updateChart(false,false,false,false,true,true,true,true,true);
    }
    /*
    Calculate max from the receivers
    */
    public void changeFirstReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFirstReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
        updateChart(false,false,false,false,true,true,true,true,true);
    }

    public void changeSecondReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setSecondReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
    }

    public void changeThirdReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setThirdReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        maxReceivers(RTMOptionSelected);
        ;
    }

    public void changeFourthReceiverCellEvent(TableColumn.CellEditEvent editedCell) {
        RTMOption RTMOptionSelected = firstTableView.getSelectionModel().getSelectedItem();
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
            resultingEverydayRetailCalcdChart.getData().clear();
            resultingEverydayRetailCalcdChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(2)}));
        } if (eEUV) {
            elasticizedEstimatedUnitVelocityChart.getData().clear();
            elasticizedEstimatedUnitVelocityChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(3)}));
        }if (eAVS) {
            estimatedAnnualVolumeChart.getData().clear();
            estimatedAnnualVolumeChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(4)}));
        }if (sPP) {
            slottingPaybackPeriodChart.getData().clear();
            slottingPaybackPeriodChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(5)}));
        }if (pFPS) {
            postSpoilsPostFreightWeCollectChart.getData().clear();
            postSpoilsPostFreightWeCollectChart.setData(FXCollections.observableArrayList(new XYChart.Series[]{getChartData(6)}));
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
    public XYChart.Series<?,?> getChartData(int i) {
        XYChart.Series<String, BigDecimal> barChartData = new XYChart.Series();
        for (RTMOption row : firstTableView.getItems()) {
            if (i==1) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getLandedStoreCost()));
            } if (i==2){
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getResultingEverydayRetailCalcd()));
            } if (i==3) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getElasticizedUnitVelocity()));
            }if (i==4) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getAnnualVolumePerSku()));
            }if (i==5) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getSlottingPaybackPeriod()));
            }if (i==6) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getPostFreightPostSpoilsWeCollectPerUnit()));
            }if (i==7) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getUnspentTradePerUnit()));
            }if (i==8) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getFourYearEqGpPerSku()));
            }if (i==9) {
                barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getFourYearEqGpPerUnit()));
            }
        }
        return barChartData;
    }
    public ObservableList<RTMOption> getCurrentRtmOptions(){
       return currentRtmOptions.get();
    }
    public RTMOption getSpecificRtmOption(int row){
        return getCurrentRtmOptions().get(row);
    }

    // START OF MESS IS HERE
//    public void updateResultingEverydayRetailCald(int row){
//        BigDecimal landedStoreCost = getCurrentRtmOptions().get(row).getLandedStoreCost();
//        BigDecimal everydayGpm = getRetailer().getEverydayGPM();
//        if (everydayGpm.compareTo(new BigDecimal("0.0"))>0 &&
//                landedStoreCost.compareTo(new BigDecimal("0.0"))>0){
//            BigDecimal newValue = (landedStoreCost.multiply(new BigDecimal("100")))
//                    .divide((everydayGpm.subtract(new BigDecimal("100"))), 4, RoundingMode.HALF_UP).abs();
//            getSpecificRtmOption(row).setResultingEverydayRetailCalcd(newValue);
//            getSpecificRtmOption(row).setResultingEverydayRetailOverride(newValue);
//        }
//    }
//    public void updateElasticizedUnitVelocity() {
//        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0 && getMinOverride().compareTo(new BigDecimal("100000000000"))<0
//                && getMinOverride().compareTo(new BigDecimal("0.0"))>0 && getResultingEverydayRetailOverride().compareTo(new BigDecimal("0.0"))>0) {
//            if (getMinOverride().compareTo(getResultingEverydayRetailOverride())==0) {
//                System.out.println("are you getting here");
//                setElasticizedUnitVelocity(this.getWeeklyUSFWAtMin());
//            } else {
//                setElasticizedUnitVelocity(((getResultingEverydayRetailOverride().subtract(getMinOverride()))
//                        .divide((getMinOverride()), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("-1.15"))
//                        .multiply(getWeeklyUSFWAtMin())).add(getWeeklyUSFWAtMin()));
//            }
//        }
//    }
//    public void updateEstimatedAnnualVolumePerSku(){
//        if (getYearOneStoreCount()>0 &&  getElasticizedUnitVelocity().compareTo(new BigDecimal("0.0"))>0) {
//            setAnnualVolumePerSku(((new BigDecimal("52").multiply(new BigDecimal(
//                    getYearOneStoreCount()).multiply(getElasticizedUnitVelocity())))
//                    .setScale(10, RoundingMode.HALF_UP)));
//        }
//    }
//
//    public void updateSlottingPaybackPeriod(){
//        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0
//                && getMinOverride().compareTo(new BigDecimal("0.0"))>0  && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0"))>0) {
//            if (getSlottingPerSku().compareTo(new BigDecimal("0.0"))==0){
//                setSlottingPaybackPeriod(new BigDecimal("0.0"));
//            }
//            else {
//                setSlottingPaybackPeriod(getSlottingPayback());
//            }
//        }
//    }
//    public void updatePostFreightPostSpoilsWeCollect(){
//        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0
//                && getMinOverride().compareTo(new BigDecimal("0.0"))>0  && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0"))>0) {
//            setPostFreightPostSpoilsWeCollectPerUnit(getPostSpoilsAndFreightWeCollectPerUnit());
//        }
//    }
//    public void updateUnspentTrade(){
//        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0
//                && getMinOverride().compareTo(new BigDecimal("0.0"))>0  && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0"))>0) {
//            setUnspentTradePerUnit(getPostSpoilsAndStdAllowancesAvailableTrade());
//        }
//    }
//    public void updateFourYearEqGpPerSku() {
//        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
//                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0 && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
//            setFourYearEqGpPerSku(getGrossProfit());
//        }
//    }
//    public void updateFourYearEqGpPerUnit() {
//        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
//                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0 && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
//            setFourYearEqGpPerUnit(getGrossProfitPerUnit());
//        }
//    }
//
//    public Product getProduct() {
//        return product.get();
//    }
//
//    public SimpleObjectProperty<Product> productProperty() {
//        if (product==null){
//            return new SimpleObjectProperty<Product>();
//        }
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product.set(product);
//    }
//
//    public Integer getYearOneStoreCount() {
//        return yearOneStoreCount.get();
//    }
//
//    public SimpleIntegerProperty yearOneStoreCountProperty() {
//        if (yearOneStoreCount==null){
//            return new SimpleIntegerProperty();
//        }
//        return yearOneStoreCount;
//    }
//
//    public void setYearOneStoreCount(Integer yearOneStoreCount) {
//        this.yearOneStoreCount.set(yearOneStoreCount);
//    }
//
//    public BigDecimal getEverydayGPM() {
//        if (everydayGPM.get()==null){
//            return new BigDecimal("0.0");
//        }
//        return everydayGPM.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
//        if (everydayGPM ==null){
//            return new SimpleObjectProperty<>();
//        }
//        return everydayGPM;
//    }
//
//    public void setEverydayGPM(BigDecimal everydayGPM) {
//        this.everydayGPM.set(everydayGPM);
//    }
//
//    public BigDecimal getSpoilsAndFees() {
//        return spoilsAndFees.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> spoilsAndFeesProperty() {
//        return spoilsAndFees;
//    }
//
//    public void setSpoilsAndFees(BigDecimal spoilsAndFees) {
//        this.spoilsAndFees.set(spoilsAndFees);
//    }
//
//    public boolean isFob() {
//        if (getFreightOutPerUnit().compareTo(new BigDecimal(0.0)) > 0) {
//            return false;
//        }
//        return true;
//    }
//
//    public BigDecimal getFourYearUnitVolumePerSku(){
//        return getAnnualVolumePerSku().multiply(new BigDecimal(4.0));
//    }
//    public BigDecimal getOurFreightCost(){
//        return getFourYearUnitVolumePerSku().multiply(getFreightOutPerUnit());
//    }
//    public BigDecimal getGrossRevenueList(){ //retest //Put list in parameters to calculate
//        return getFourYearUnitVolumePerSku().multiply(getProduct().getUnitListCost().setScale(10, RoundingMode.HALF_UP)); // HARDCODED FOR NOW, SHOULD BE LIST PRICE
//    }
//    public BigDecimal getFobDiscount(){ //retest
//        if (isFob()){
//            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost())).multiply(getFourYearUnitVolumePerSku()); // HARDCODED FOR NOW: (LIST - FOB)*4 Year Unit VOl /SKu
//        }
//        return new BigDecimal("0.0");
//    }
//    public BigDecimal getGrossRevenueActual(){
//        return getGrossRevenueList().subtract(getFobDiscount());
//    }
//    public BigDecimal getSpoilsTrade(){  // retest // Put spoils+fees field value in parameter
//        return getGrossRevenueActual().multiply(getSpoilsAndFees());
//    }
//    public BigDecimal getStandardAllowanceTrade(){ //retest //HARDCODED FOR NOW, LIST PRICE NEEDED
//        if (isFob()){
//            return ((((getProduct().getUnitListCost()).subtract(getFirstReceiver()))
//                    .multiply(getFourYearUnitVolumePerSku())).subtract(getFobDiscount())).setScale(10, RoundingMode.HALF_UP);
//        }
//        BigDecimal zeroValue = (getProduct().getUnitListCost()).subtract(getFirstReceiver());
//        zeroValue = zeroValue.multiply(getFourYearUnitVolumePerSku());
//        return zeroValue.setScale(10, RoundingMode.HALF_UP);
//    }
//    public BigDecimal getAfterSpoilsAndStdAllowanceTrade(){ // retest //HARDCODED FOR NOW, LIST PRICE NEEDED, NET1 GOAL NEEDED
//        return (getFourYearUnitVolumePerSku().multiply((getProduct().getUnitListCost()).subtract(getProduct().getUnitNet1Goal()))).subtract(getSpoilsTrade()).subtract(getStandardAllowanceTrade());
//    }
//    public BigDecimal getIfFobFreightCredit(){
//        if (isFob()){
//            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost())).multiply(getFourYearUnitVolumePerSku());
//        }
//        return new BigDecimal("0.0");
//    }
//    public BigDecimal getEqualsNet1Rev(){
//        return getGrossRevenueList().subtract(getSpoilsTrade()).subtract(getStandardAllowanceTrade()).subtract(getAfterSpoilsAndStdAllowanceTrade());
//    }
//    public BigDecimal getTotalFobAndFreightSpending(){
//        return getOurFreightCost().add(getFobDiscount());
//    }
//    public BigDecimal getEqualsNet2Rev(){
//        return getEqualsNet1Rev().subtract(getTotalFobAndFreightSpending());
//    }
//    public BigDecimal getEqualsNet3Rev(){
//        return getEqualsNet2Rev().subtract(getSlottingPerSku());
//    }
//    public BigDecimal getNetRev3Rate(){
//        if (getFourYearUnitVolumePerSku().equals(new BigDecimal("0.0"))){
//            return new BigDecimal ("0.0");
//        }
//        return getEqualsNet3Rev().divide((getFourYearUnitVolumePerSku()),10, RoundingMode.HALF_UP).setScale(10,RoundingMode.HALF_UP);
//    }
//    // IMPLEMENT COGS AND PASS IT
//    public BigDecimal getTotalCogs(){
//        return getFourYearUnitVolumePerSku().multiply(getProduct().getUnitBlendedCogs()); //HARDCODED FOR NOW SHOULD BE COGS
//    }
//    public BigDecimal getGrossProfit(){
//        return getEqualsNet3Rev().subtract(getTotalCogs());
//    }
//    public BigDecimal getGrossProfitPerUnit(){
//        return getGrossProfit().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
//    }
//    // IMPLEMENT THIS IN FIRST TABLE CONTROLLER, get the max from gross profit
////    public BigDecimal getGrossProfitIndex(){
////        if (getGrossProfit().equals(new BigDecimal("0.0"))){
////            return new BigDecimal("0.0");
////        }
////        return getGrossProfit()
////    }
//
//    public BigDecimal getSlottingPayback(){
//        return (getSlottingPerSku().divide((getGrossProfitPerUnit()), 10, RoundingMode.HALF_UP)).divide(getAnnualVolumePerSku(), 5, RoundingMode.HALF_UP);
//    }
//    // IMPLEMENT GET GROSS PROFIT INDEX
//    public BigDecimal getPostSpoilsAndFreightWeCollect(){
//        return getGrossRevenueList().subtract(getSpoilsTrade()).subtract(getOurFreightCost()).subtract(getFobDiscount());
//    }
//    public BigDecimal getPostSpoilsAndFreightWeCollectPerUnit(){
//        return getPostSpoilsAndFreightWeCollect().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
//    }
//    public BigDecimal getPostSpoilsAndStdAllowancesAvailableTrade(){
//        return getAfterSpoilsAndStdAllowanceTrade().divide((getFourYearUnitVolumePerSku()),10 , RoundingMode.HALF_UP);
//    }
    // END OF MESSS HERE IN THEORY

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
        meetings.addAll(new Meeting("Review Meeting", "here", convertToDate(LocalDate.of(2022,12,5)), "11:15","will be fun"), new Meeting());
        retailerProducts.add(new RetailerProduct( new Product("Big Time Food Company", "24 oz pickles", new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("2.05"), new BigDecimal("-1.15")), getRTMOptions(), skus,meetings, RetailerSelectionController.getDummyPromoPlans()));
        return retailerProducts;
    }



    /*
    Loads dummy product data
    */
    public ObservableList<Product> getExampleProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Big Time Food Company", "24 oz pickles", new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("2.05"), new BigDecimal("-1.15")));
        products.add(new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20")));
        products.add(new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20")));
        products.add(new Product("Small Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20")));
        return products;
    }
    @FXML
    private void switchToAssortment(ActionEvent event) throws IOException {
//        App.setRoot("assortment");
        FXMLLoader assortmentLoader = App.createFXMLLoader("assortment");
        App.setSceneRoot(assortmentLoader.load());

        AssortmentController assortmentController =assortmentLoader.getController();
        assortmentController.setRetailer(retailer.get());
    }
    @FXML
    private void switchToRetailerSelection(ActionEvent event) throws IOException {
//        App.setRoot("assortment");
        FXMLLoader retailerSelectionLoader = App.createFXMLLoader("retailerSelection");
        App.setSceneRoot(retailerSelectionLoader.load());

        RetailerSelectionController assortmentController =retailerSelectionLoader.getController();
//        assortmentController.setRetailer(retailer.get());
    }
    @FXML
    private void switchToPricingPromotion(ActionEvent event) throws IOException {
        FXMLLoader pricingPromotionLoader = App.createFXMLLoader("pricingPromotion");
        App.setSceneRoot(pricingPromotionLoader.load());

        PricingPromotionController pricingPromotionController =pricingPromotionLoader.getController();
        pricingPromotionController.setRetailer(retailer.get());
    }


    /*
    Set Column header tooltips for first and second tableview
    */
    public void setToolTipsFirstAndSecondTableview() {
        RTMNameColumnLabel.setTooltip(RTMNameColumnTip);
        RTMNameColumn.setGraphic(RTMNameColumnLabel);

        freightOutPerUnitLabel.setTooltip(freightOutPerUnitTip);
        freightOutPerUnitColumn.setGraphic(freightOutPerUnitLabel);

        slottingPerSkuLabel.setTooltip(slottingPerSkuTip);
        slottingPerSkuColumn.setGraphic(slottingPerSkuLabel);

        firstReceiverLabel.setTooltip(firstReceiverTip);
        firstReceiverColumn.setGraphic(firstReceiverLabel);

        secondReceiverLabel.setTooltip(secondReceiverTip);
        secondReceiverColumn.setGraphic(secondReceiverLabel);

        thirdReceiverLabel.setTooltip(thirdReceiverTip);
        thirdReceiverColumn.setGraphic(thirdReceiverLabel);

        fourthReceiverLabel.setTooltip(fourthReceiverTip);
        fourthReceiverColumn.setGraphic(fourthReceiverLabel);

        landedStoreCostLabel.setTooltip(landedStoreCostTip);
        landedStoreCostColumn.setGraphic(landedStoreCostLabel);

        resultingEveryDayRetailCalcdLabel.setTooltip(resultingEveryDayRetailCalcdTip);
        resultingEverydayRetailCalcdColumn.setGraphic(resultingEveryDayRetailCalcdLabel);

        resultingEveryDayRetailOverrideLabel.setTooltip(resultingEveryDayRetailOverrideTip);
        resultingEverydayRetailOverrideColumn.setGraphic(resultingEveryDayRetailOverrideLabel);

        RTMNameColumn2.setGraphic(RTMNameColumnLabel2);

        elasticizedEstimatedUnitVelocityLabel.setTooltip(elasticizedEstimatedUnitVelocityTip);
        elasticizedEstimatedUnitVelocityColumn.setGraphic(elasticizedEstimatedUnitVelocityLabel);

        estimatedAnnualVolumePerSkuLabel.setTooltip(estimatedAnnualVolumePerSkuTip);
        estimatedAnnualVolumePerSkuColumn.setGraphic(estimatedAnnualVolumePerSkuLabel);

        slottingPaybackPeriodLabel.setTooltip(slottingPaybackPeriodTip);
        slottingPaybackPeriodColumn.setGraphic(slottingPaybackPeriodLabel);

        postFreightPostSpoilsWeCollectLabel.setTooltip(postFreightPostSpoilsWeCollectTip);
        postFreightPostSpoilsWeCollectPerUnitColumn.setGraphic(postFreightPostSpoilsWeCollectLabel);

        unspentTradePerUnitLabel.setTooltip(unspentTradePerUnitTip);
        unspentTradePerUnitColumn.setGraphic(unspentTradePerUnitLabel);

        fourYearEqGpPerSkuLabel.setTooltip(fourYearEqGpPerSkuTip);
        fourYearEqGpPerSkuColumn.setGraphic(fourYearEqGpPerSkuLabel);

        fourYearEqGpPerUnitLabel.setTooltip(fourYearEqGpPerUnitTip);
        fourYearEqGpPerUnitColumn.setGraphic(fourYearEqGpPerUnitLabel);
    }

    /*
    Set up cellValue factories
    */
    public void setCellValueFactories() {

        RTMNameColumn.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
        slottingPerSkuColumn.setCellValueFactory(cellData -> cellData.getValue().slottingPerSkuProperty());
        freightOutPerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().freightOutPerUnitProperty());
        firstReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().firstReceiverProperty());
        secondReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().secondReceiverProperty());
        thirdReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().thirdReceiverProperty());
        fourthReceiverColumn.setCellValueFactory(cellData -> cellData.getValue().fourthReceiverProperty());
        landedStoreCostColumn.setCellValueFactory(cellData -> cellData.getValue().landedStoreCostProperty());
        resultingEverydayRetailCalcdColumn.setCellValueFactory(cellData -> cellData.getValue().resultingEverydayRetailCalcdProperty());
        resultingEverydayRetailOverrideColumn.setCellValueFactory(cellData -> cellData.getValue().resultingEverydayRetailOverrideProperty());
        elasticizedEstimatedUnitVelocityColumn.setCellValueFactory(cellData -> cellData.getValue().elasticizedUnitVelocityProperty());
        estimatedAnnualVolumePerSkuColumn.setCellValueFactory(cellData -> cellData.getValue().annualVolumePerSkuProperty());
        slottingPaybackPeriodColumn.setCellValueFactory(cellData -> cellData.getValue().slottingPaybackPeriodProperty());
        postFreightPostSpoilsWeCollectPerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().postFreightPostSpoilsWeCollectPerUnitProperty());
        unspentTradePerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().unspentTradePerUnitProperty());
        fourYearEqGpPerSkuColumn.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerSkuProperty());
        fourYearEqGpPerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerUnitProperty());
        RTMNameColumn2.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
    }

    /*
    Set Cell Factories for first and second Table View
    */
    public void setCellFactories() {

        RTMNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        slottingPerSkuColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        freightOutPerUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        firstReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        landedStoreCostColumn.setCellFactory(tc->new CustomNonEditCell("$",""));
        resultingEverydayRetailCalcdColumn.setCellFactory(tc->new CustomNonEditCell("$", ""));
//                TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        resultingEverydayRetailOverrideColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        elasticizedEstimatedUnitVelocityColumn.setCellFactory(tc->new CustomNonEditCell(""," U/S/F/W"));
        estimatedAnnualVolumePerSkuColumn.setCellFactory(tc->new TableCell<>(){
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
        postFreightPostSpoilsWeCollectPerUnitColumn.setCellFactory(tc->new CustomNonEditCell("$", " Per Unit"));
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
    public StringConverter<RetailerProduct> getProductComboboxConverter(){
        return new ProductboxConverter("product");
    }
    /*
    Return a converter for brand combobox
     */
    public StringConverter<RetailerProduct> getBrandComboboxConverter() {
        return new ProductboxConverter("brand");
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
        ObservableList<RetailerProduct> retailerProducts =retailer.getRetailerProducts();
        RetailerProduct currentRetailerProduct = retailerProducts.get(retailer.getCurrentRetailerProductIndex());
        ObservableList<RTMOption> currentRtmOptions = currentRetailerProduct.getRtmOptions();
        this.firstTableView.setItems(currentRtmOptions);
        setUpListeners();
        //CHANGE PRODUCT BOX TO AVE RETAILER OPTION SELECTION INSTEAD OF PRODUCT SELECTION
        this.secondTableView.setItems(currentRtmOptions);

        this.brandNameBox.setItems(getUniqueBrandNames(retailerProducts));
        this.brandNameBox.valueProperty().setValue(currentRetailerProduct);
        this.productClassBox.setItems(getCorrespondingProductClasses(retailerProducts,currentRetailerProduct));
        this.productClassBox.valueProperty().setValue(currentRetailerProduct);
        this.firstTableView.setItems(currentRtmOptions);
        this.yearOneStoreCountField.setText(String.valueOf(retailer.getYearOneStoreCount()));
        this.everydayGpmField.setText(retailer.getEverydayGPM().toString());
        this.spoilsFeesField.setText(retailer.getSpoilsFees().toString());
        // Stuff that should be implemented differently
        this.weeklyUfswAtMinField.setText(currentRtmOptions.get(0).getWeeklyUSFWAtMin().toString());
        for (RTMOption row: firstTableView.getItems()){
            row.setProduct(currentRetailerProduct.getProduct());
            row.setYearOneStoreCount(retailer.getYearOneStoreCount());
            row.setEverydayGPM(retailer.getEverydayGPM());
            row.setSpoilsAndFees(retailer.getSpoilsFees().divide((new BigDecimal("100")), 4 , RoundingMode.HALF_UP));
        }
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

