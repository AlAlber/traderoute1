package com.traderoute;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class firstTableController implements Initializable {

    // Assign Values to
    @FXML private TableView<RTMOption> secondTableView;
    @FXML private TableView<RTMOption> firstTableView;
    @FXML private TableColumn<RTMOption, String> RTMNameColumn;
    @FXML private TableColumn<RTMOption, Integer> slottingPerSkuColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> freightOutPerUnitColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> firstReceiverColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> secondReceiverColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> thirdReceiverColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> fourthReceiverColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> landedStoreCostColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> resultingEverydayRetailCalcdColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> resultingEverydayRetailOverrideColumn;
    @FXML private TableColumn<RTMOption, String> RTMNameColumn2;
    @FXML private TableColumn<RTMOption, BigDecimal> elasticizedEstimatedUnitVelocityColumn;
    @FXML private TableColumn<RTMOption, Integer> estimatedAnnualVolumePerSkuColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsWeCollectColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuColumn;
    @FXML private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitColumn;

    @FXML private TextField everyDayGPMField;
    @FXML private TextField weeklyUFSWAtMinField;
    @FXML private TextField yearOneStoreCountField;

    @FXML private Label maxOverrideLabel;

    /*
    Initialization for tooltips
     */
    @FXML private final Label RTMNameColumnLabel = new Label ("Route to market options");
    @FXML private final Label slottingPerSkuLabel = new Label ("Slotting Per Sku");
    @FXML private final Label freightOutPerUnitLabel = new Label ("Freight Out Per Unit");
    @FXML private final Label firstReceiverLabel = new Label ("First Receiver Pays");
    @FXML private final Label secondReceiverLabel = new Label ("Second Receiver Pays");
    @FXML private final Label thirdReceiverLabel = new Label ("Third Receiver Pays");
    @FXML private final Label fourthReceiverLabel = new Label ("Fourth Receiver Pays");
    @FXML private final Label landedStoreCostLabel = new Label ("Landed Store Cost");
    @FXML private final Label resultingEveryDayRetailCalcdLabel = new Label ("Resulting Everyday Retail Calculated");
    @FXML private final Label resultingEveryDayRetailOverrideLabel = new Label ("Resulting Everyday Retail Override");
    @FXML private final Label elasticizedEstimatedUnitVelocityLabel = new Label ("Elasticized Estimated Annual Volume /Sku ");
    @FXML private final Label estimatedAnnualVolumePerSkuLabel = new Label ("Estimated Annual Volume / Sku ");
    @FXML private final Label slottingPaybackPeriodLabel = new Label ("Slotting Payback Period");
    @FXML private final Label postFreightPostSpoilsWeCollectLabel = new Label ("Post Freight Post Spoils We Collect");
    @FXML private final Label unspentTradePerUnitLabel = new Label ("Unspent Trade Per Unit");
    @FXML private final Label fourYearEqGpPerSkuLabel = new Label ("4-Year EQ GP $ Per Sku");
    @FXML private final Label fourYearEqGpPerUnitLabel = new Label ("4-Year EQ GP $ Per Unit");

    @FXML private final Tooltip RTMNameColumnTip =
            new Tooltip ("Please enter the most likely 'route-to-market' options to get the product to the market.");
    @FXML private final Tooltip slottingPerSkuTip =
            new Tooltip ("Please enter the required slotting (placement) investment specific to this 'route-to-arket option.'");
    @FXML private final Tooltip freightOutPerUnitTip =
            new Tooltip ("If we're responsible for the cost of shipping for this route-to -market option, please enter in the 'per unit cost' of this 'Freight-Out.' For F.O.B (Pick-up) Customers, Freight-Out equals $0.");
    @FXML private final Tooltip firstReceiverTip =
            new Tooltip ("The Per Unit Cost the First Receiver pays us, typically the Unit List Cost or the Unit F.O.B");
    @FXML private final Tooltip secondReceiverTip =
            new Tooltip ("The Per Unit Cost the Second Receiver pays to the First Receiver.");
    @FXML private final Tooltip thirdReceiverTip =
            new Tooltip ("The Per Unit Cost the Third Receiver pays to the Second Receiver.");
    @FXML private final Tooltip fourthReceiverTip =
            new Tooltip ("The Per Unit Cost the Fourth Receiver pays to the Third Receiver.");
    @FXML private final Tooltip landedStoreCostTip =
            new Tooltip ("The Per Unit Cost the Retail OutLet (Last Receiver pays prior to applying the Required GPM% to establish the Everyday Retail.");
    @FXML private final Tooltip resultingEveryDayRetailCalcdTip =
            new Tooltip ("The auto-calculated Resulting Everyday Retail given the Landed Store Cost and Required Gross Profit Margin %.");
    @FXML private final Tooltip resultingEveryDayRetailOverrideTip =
            new Tooltip ("Please enter the REALISTIC Everyday Retail considering the auto-calculated retail to the left.");
    @FXML private final Tooltip elasticizedEstimatedUnitVelocityTip =
            new Tooltip ("For each route-to-market option provided, these are the Estimated Weekly Unit Velocities given the Product Class's Price Elasticity Multiple (for each X% increase in Unit Price there is a Y% decrease in Units Sold");
    @FXML private final Tooltip estimatedAnnualVolumePerSkuTip =
            new Tooltip ("For each route-to-market option provided, these are the Estimated Annual Volumes Per SKU (accounting for price elasticities)");
    @FXML private final Tooltip slottingPaybackPeriodTip =
            new Tooltip ("If Slotting is a consideration these are the Payback Periods (in years) for each route-to-market option provided to recoup the initial Slotting Investment.");
    @FXML private final Tooltip postFreightPostSpoilsWeCollectTip =
            new Tooltip ("For each route-to-market option provided, the Per Unit Rate we collect after which Freight Costs and Spoils are accounted (but prior to Trade Spending).");
    @FXML private final Tooltip unspentTradePerUnitTip =
            new Tooltip ("Unspent Trade Per Unit");
    @FXML private final Tooltip fourYearEqGpPerSkuTip =
            new Tooltip ("4-Year Equivalized Gross Profit $ Per Sku");
    @FXML private final Tooltip fourYearEqGpPerUnitTip =
            new Tooltip ("4-Year Equivalized Gross Profit $ Per Unit");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set up cell value factories
        setCellValueFactories();

        //Set dummy data
        firstTableView.setItems(getRTMOptions());
        secondTableView.setItems(firstTableView.getItems());

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

            row.landedStoreCostProperty().addListener(new ChangeListener<BigDecimal>() {
                private boolean changing;
                @Override
                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                    if (!changing) {
                        try {
                            changing = true;
                            row.setResultingEverydayRetailCalcd(((row.getLandedStoreCost().multiply(new BigDecimal("100")))
                                    .divide((getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs()));
                        } finally {
                            changing = false;
                        }
                    }
                }
            });
            /*
            Set Override to automatically take current value of resulting every day retail calculated
            */
            row.resultingEverydayRetailProperty().addListener(new ChangeListener<BigDecimal>() {
                private boolean changing;

                @Override
                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                    if (!changing) {
                        try {
                            changing = true;
                                row.setResultingEverydayRetailOverride(row.getResultingEverydayRetailCalcd());
                                maxOverrideLabel.setText("of $" + getMinOverride());
                                firstTableView.refresh();
                        } finally {
                            changing = false;
                        }
                    }
                }
            });
            /*
            Check if resulting everyday retail changed, if it did check the max of the column and assign it to maxOverrideLabel
            */
            row.resultingEverydayRetailOverrideProperty().addListener(new ChangeListener<BigDecimal>() {
                private boolean changing;

                @Override
                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                    if (!changing) {
                        try {
                            changing = true;
                            maxOverrideLabel.setText("of $" + getMinOverride());
                            firstTableView.refresh();
                        } finally {
                            changing = false;
                        }
                    }
                }
            });

            /*
            Check if resulting everyday retail changed, if it did check the max of the column and assign it to maxOverrideLabel
            */
            row.elasticizedEstimatedUnitVelocityProperty().addListener(new ChangeListener<BigDecimal>() {
                private boolean changing;

                @Override
                public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                    if (!changing) {
                        try {
                            changing = true;
                            row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
                                    getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
                                    .setScale(0,RoundingMode.HALF_UP)).intValue()));
                            secondTableView.refresh();
                        } finally {
                            changing = false;
                        }
                    }
                }
            });
        }}

        /*
        Set EverydayGPm and do calculation if possible
        */
        public void changeEveryDayGPMCellEvent (ActionEvent event) {
//            BigDecimal newEveryDayGPM = new BigDecimal(everyDayGPMField.getText());
            for (RTMOption row : firstTableView.getItems()) {
                    row.setResultingEverydayRetailCalcd((row.getLandedStoreCost().multiply(new BigDecimal("100")))
                            .divide((this.getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs());
                    //FIGURE OUT CORRECT ROUNDING
                if (this.getEveryDayGPM()== new BigDecimal("100.0")){

                }
            }
        }
        /*
        Return minimum value from override column
         */
        public BigDecimal getMinOverride() {
            BigDecimal smallest = new BigDecimal("100000000000");
            for (RTMOption row : firstTableView.getItems()) {
                BigDecimal currentNumber = row.getResultingEverydayRetailOverride();
                if (currentNumber.compareTo(smallest) < 0) {
                    smallest = currentNumber;
                }
            }
            return smallest;
        }
    /*
    Get elasticized velocities from weeklyUSFWAtMin Field  and do calculation if possible
    */
    public void changeWeeklyUSFWAtMinEvent (ActionEvent event) {
//            BigDecimal newEveryDayGPM = new BigDecimal(everyDayGPMField.getText());
        secondTableView.setItems(firstTableView.getItems());
        for (RTMOption row : secondTableView.getItems()) {
            if (this.getMinOverride()==row.getResultingEverydayRetailOverride()){
                row.setElasticizedEstimatedUnitVelocity(this.getWeeklyUSFWAtMin());
                System.out.println("Are you getting here");
            }
            else {
                row.setElasticizedEstimatedUnitVelocity(((row.getResultingEverydayRetailOverride().subtract(this.getMinOverride()))
                        .divide((this.getMinOverride()), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("-1.15"))
                        .multiply(this.getWeeklyUSFWAtMin())).add(this.getWeeklyUSFWAtMin()));
            }
        }
        secondTableView.refresh();
    }
    /*
    Get estimated annual volume/sku
    */
    public void changeYearOneStoreCount (ActionEvent event) {
//            BigDecimal newEveryDayGPM = new BigDecimal(everyDayGPMField.getText());
        secondTableView.setItems(firstTableView.getItems());
        for (RTMOption row : secondTableView.getItems()) {
            System.out.println("say cheese");
            row.setEstimatedAnnualVolumePerSku(Integer.valueOf(((new BigDecimal("52").multiply(new BigDecimal(
                    this.getYearOneStoreCount()).multiply(row.getElasticizedEstimatedUnitVelocity())))
                    .setScale(0,RoundingMode.HALF_UP)).intValue()));
        }
        secondTableView.refresh();
    }
    /*
    Return Value from EveryDayGPMField
    */
    public BigDecimal getEveryDayGPM(){
        if (everyDayGPMField.getText()==null){
            return new BigDecimal("0.0");
        }
        return new BigDecimal(everyDayGPMField.getText());
    }
    /*
    Return Value from EveryDayGPMField
    */
    public BigDecimal getWeeklyUSFWAtMin(){
        if (weeklyUFSWAtMinField.getText()== null){
            return new BigDecimal("0.0");
        }
        return new BigDecimal(weeklyUFSWAtMinField.getText());
    }
    /*
    Return Value from Year One Store Count
    */
    public int getYearOneStoreCount(){
        if (yearOneStoreCountField.getText()== null){
            return 0;
        }
        return Integer.valueOf(yearOneStoreCountField.getText());
    }
    /*
    Calculate max from the receivers
    */

    public void changeFirstReceiverCellEvent(TableColumn.CellEditEvent editedCell)
    {
        RTMOption RTMOptionSelected =  firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFirstReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        RTMOptionSelected.setLandedStoreCost(RTMOptionSelected.getFirstReceiver().max(RTMOptionSelected.getSecondReceiver()
                .max(RTMOptionSelected.getThirdReceiver().max(RTMOptionSelected.getFourthReceiver()))));
    }
    public void changeSecondReceiverCellEvent(TableColumn.CellEditEvent editedCell)
    {
        RTMOption RTMOptionSelected =  firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setSecondReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        RTMOptionSelected.setLandedStoreCost(RTMOptionSelected.getFirstReceiver().max(RTMOptionSelected.getSecondReceiver()
                .max(RTMOptionSelected.getThirdReceiver().max(RTMOptionSelected.getFourthReceiver()))));
    }
    public void changeThirdReceiverCellEvent(TableColumn.CellEditEvent editedCell)
    {
        RTMOption RTMOptionSelected =  firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setThirdReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        RTMOptionSelected.setLandedStoreCost(RTMOptionSelected.getFirstReceiver().max(RTMOptionSelected.getSecondReceiver()
                .max(RTMOptionSelected.getThirdReceiver().max(RTMOptionSelected.getFourthReceiver()))));
    }
    public void changeFourthReceiverCellEvent(TableColumn.CellEditEvent editedCell)
    {
        RTMOption RTMOptionSelected =  firstTableView.getSelectionModel().getSelectedItem();
        RTMOptionSelected.setFourthReceiver(new BigDecimal(editedCell.getNewValue().toString()));
        RTMOptionSelected.setLandedStoreCost(RTMOptionSelected.getFirstReceiver().max(RTMOptionSelected.getSecondReceiver()
                .max(RTMOptionSelected.getThirdReceiver().max(RTMOptionSelected.getFourthReceiver()))));
    }

    /*
    Loads dummy data
    */
    public ObservableList<RTMOption> getRTMOptions () {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOptions.add(new RTMOption("Frank", BigDecimal.valueOf(1.3), 0, BigDecimal.valueOf(0.5),
                BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.3), BigDecimal.valueOf(3.95)));
//            RTMOptions.add(new RTMOption("Rebecca", 2.1, 0, BigDecimal.valueOf(0.5),
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
            RTMOptions.add(new RTMOption());

            return RTMOptions;
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

        elasticizedEstimatedUnitVelocityLabel.setTooltip(elasticizedEstimatedUnitVelocityTip);
        elasticizedEstimatedUnitVelocityColumn.setGraphic(elasticizedEstimatedUnitVelocityLabel);

        estimatedAnnualVolumePerSkuLabel.setTooltip(estimatedAnnualVolumePerSkuTip);
        estimatedAnnualVolumePerSkuColumn.setGraphic(estimatedAnnualVolumePerSkuLabel);

        slottingPaybackPeriodLabel.setTooltip(slottingPaybackPeriodTip);
        slottingPaybackPeriodColumn.setGraphic(slottingPaybackPeriodLabel);

        postFreightPostSpoilsWeCollectLabel.setTooltip(postFreightPostSpoilsWeCollectTip);
        postFreightPostSpoilsWeCollectColumn.setGraphic(postFreightPostSpoilsWeCollectLabel);

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
    public void setCellValueFactories(){

        RTMNameColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, String>("RTMName"));
        slottingPerSkuColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("slottingPerSku"));
        freightOutPerUnitColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("freightOutPerUnit"));
        firstReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("firstReceiver"));
        secondReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("secondReceiver"));
        thirdReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("thirdReceiver"));
        fourthReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("fourthReceiver"));
        landedStoreCostColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("landedStoreCost"));
        resultingEverydayRetailCalcdColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("resultingEverydayRetailCalcd"));
        resultingEverydayRetailOverrideColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("resultingEverydayRetailOverride"));
        elasticizedEstimatedUnitVelocityColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("elasticizedEstimatedUnitVelocity"));
        estimatedAnnualVolumePerSkuColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("estimatedAnnualVolumePerSku"));
        slottingPaybackPeriodColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("slottingPaybackPeriod"));
        postFreightPostSpoilsWeCollectColumn.setCellValueFactory(new PropertyValueFactory<RTMOption,BigDecimal>("postFreightPostSpoilsWeCollect"));
        unspentTradePerUnitColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, BigDecimal>("unspentTradePerUnit"));
        fourYearEqGpPerSkuColumn.setCellValueFactory(new PropertyValueFactory<RTMOption,BigDecimal>("fourYearEqGpPerSku"));
        fourYearEqGpPerUnitColumn.setCellValueFactory(new PropertyValueFactory<RTMOption,BigDecimal>("fourYearEqGpPerUnit"));
        RTMNameColumn2.setCellValueFactory(new PropertyValueFactory<RTMOption, String>("RTMName"));
    }

    /*
    Set Cell Factories for first and second Table View
    */
    public void setCellFactories() {

        RTMNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        slottingPerSkuColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        freightOutPerUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        firstReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        landedStoreCostColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        resultingEverydayRetailCalcdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        resultingEverydayRetailOverrideColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        elasticizedEstimatedUnitVelocityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        estimatedAnnualVolumePerSkuColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        slottingPaybackPeriodColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        postFreightPostSpoilsWeCollectColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        unspentTradePerUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fourYearEqGpPerSkuColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fourYearEqGpPerUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
    }
    }

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

