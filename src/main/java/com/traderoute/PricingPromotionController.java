package com.traderoute;

import javafx.application.Platform;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PricingPromotionController implements Initializable {
    @FXML
    private TableView<Parameter<?>> pricingPromotionTableOne;

    @FXML
    private TableView<Parameter<?>> parameterNameTable;

    @FXML
    private TableColumn<Parameter<?>, Object> parameterNameColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> januaryColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> februaryColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> marchColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> aprilColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> mayColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> juneColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> julyColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> augustColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> septemberColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> octoberColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> novemberColumn;
    @FXML
    private TableColumn<Parameter<?>, Object> decemberColumn;

    @FXML
    private TableView<Summary> toplineTable0;
    @FXML
    private TableView<Summary> toplineTable1;
    @FXML
    private TableView<Summary> toplineTable2;
    @FXML
    private TableView<Summary> toplineTable3;

    @FXML
    private TableColumn<Summary, String> toplineDescriptionColumn0;
    @FXML
    private TableColumn<Summary, String> toplineDescriptionColumn1;
    @FXML
    private TableColumn<Summary, String> toplineDescriptionColumn2;
    @FXML
    private TableColumn<Summary, String> toplineDescriptionColumn3;

    @FXML
    private TableColumn <Summary, BigDecimal> toplineValueColumn0;
    @FXML
    private TableColumn <Summary, BigDecimal> toplineValueColumn1;
    @FXML
    private TableColumn <Summary, BigDecimal> toplineValueColumn2;
    @FXML
    private TableColumn <Summary, BigDecimal> toplineValueColumn3;

    @FXML
    private TableView<Summary> retailerTable0;
    @FXML
    private TableView<Summary> retailerTable1;
    @FXML
    private TableView<Summary> retailerTable2;
    @FXML
    private TableView<Summary> retailerTable3;

    @FXML
    private TableColumn<Summary,String> retailerDescriptionColumn0;
    @FXML
    private TableColumn<Summary,String> retailerDescriptionColumn1;
    @FXML
    private TableColumn<Summary,String> retailerDescriptionColumn2;
    @FXML
    private TableColumn<Summary,String> retailerDescriptionColumn3;

    @FXML
    private TableColumn<Summary,BigDecimal> retailerValueColumn0;
    @FXML
    private TableColumn<Summary,BigDecimal> retailerValueColumn1;
    @FXML
    private TableColumn<Summary,BigDecimal> retailerValueColumn2;
    @FXML
    private TableColumn<Summary,BigDecimal> retailerValueColumn3;

    @FXML
    private Label everydayLabel0;
    @FXML
    private Label everydayLabel1;
    @FXML
    private Label everydayLabel2;
    @FXML
    private Label everydayLabel3;

    @FXML
    private Label costLabel0;
    @FXML
    private Label costLabel1;
    @FXML
    private Label costLabel2;
    @FXML
    private Label costLabel3;

    @FXML
    private Label gpmLabel0;
    @FXML
    private Label gpmLabel1;
    @FXML
    private Label gpmLabel2;
    @FXML
    private Label gpmLabel3;

    @FXML
    private Label plannedNet1RateLabel0;
    @FXML
    private Label plannedNet1RateLabel1;
    @FXML
    private Label plannedNet1RateLabel2;
    @FXML
    private Label plannedNet1RateLabel3;


    @FXML
    private Label goalLabel0;
    @FXML
    private Label goalLabel1;
    @FXML
    private Label goalLabel2;
    @FXML
    private Label goalLabel3;

    @FXML
    private TextField weeklyVelocityField0;
    @FXML
    private TextField weeklyVelocityField1;
    @FXML
    private TextField weeklyVelocityField2;
    @FXML
    private TextField weeklyVelocityField3;


    @FXML
    private ComboBox<RTMOption> rtmBox0;
    @FXML
    private ComboBox<RTMOption> rtmBox1;
    @FXML
    private ComboBox<RTMOption> rtmBox2;
    @FXML
    private ComboBox<RTMOption> rtmBox3;


    @FXML
    private Button commitButton0;
    @FXML
    private Button commitButton1;
    @FXML
    private Button commitButton2;
    @FXML
    private Button commitButton3;

    @FXML
    private Button editButton0;
    @FXML
    private Button editButton1;
    @FXML
    private Button editButton2;
    @FXML
    private Button editButton3;



    private BigDecimal oldSkuInDistributionValue;
    private List<BigDecimal> confidencePerOld = new ArrayList<BigDecimal>();
    private List<Integer> skuCountChangeOld = new ArrayList<Integer>();

    private SimpleObjectProperty<Retailer> retailer;
//            new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),firstTableController.getRetailerProducts().get(0) ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));

    private SimpleObjectProperty<RTMOption> firstSelectedRtmOption =new SimpleObjectProperty<>();

    private SimpleIntegerProperty currentPromoPlanIndex= new SimpleIntegerProperty(-1);
    private SimpleObjectProperty<ObservableList<PromoPlan>> promoPlans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        commitButton0.setWrapText(true);
        commitButton1.setWrapText(true);
        commitButton2.setWrapText(true);
        commitButton3.setWrapText(true);

        editButton0.setWrapText(true);
        editButton1.setWrapText(true);
        editButton2.setWrapText(true);
        editButton3.setWrapText(true);

        this.promoPlans= new SimpleObjectProperty<>(getDummyPromoPlans());
        promoPlans.get().get(0).setEditButton(editButton0);
        promoPlans.get().get(1).setEditButton(editButton1);
        promoPlans.get().get(2).setEditButton(editButton2);
        promoPlans.get().get(3).setEditButton(editButton3);


//        editButton1


        pricingPromotionTableOne.setEditable(true);
        pricingPromotionTableOne.setItems(getParameters());
        parameterNameTable.setItems(getParameters());

        toplineTable0.setItems(getSummaryTable());
        toplineTable1.setItems(getSummaryTable());
        toplineTable2.setItems(getSummaryTable());
        toplineTable3.setItems(getSummaryTable());

        retailerTable0.setItems(getSummaryTable2());
        retailerTable1.setItems(getSummaryTable2());
        retailerTable2.setItems(getSummaryTable2());
        retailerTable3.setItems(getSummaryTable2());

        // Name Table
        parameterNameColumn.setCellValueFactory(cellData->(ObservableValue) cellData.getValue().nameProperty());

        //Calculation Table
        januaryColumn.setCellValueFactory(cellData -> (ObservableValue<Object>)cellData.getValue().januaryProperty());
        februaryColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().februaryProperty());
        marchColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().marchProperty());
        aprilColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().aprilProperty());
        mayColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().mayProperty());
        juneColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().juneProperty());
        julyColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().julyProperty());
        augustColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().augustProperty());
        septemberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().septemberProperty());
        octoberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().octoberProperty());
        novemberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().novemberProperty());
        decemberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().decemberProperty());

        // Topline Table
        toplineDescriptionColumn0.setCellValueFactory(cellData-> cellData.getValue().summaryTypeProperty());
        toplineDescriptionColumn1.setCellValueFactory(cellData-> cellData.getValue().summaryTypeProperty());
        toplineDescriptionColumn2.setCellValueFactory(cellData-> cellData.getValue().summaryTypeProperty());
        toplineDescriptionColumn3.setCellValueFactory(cellData-> cellData.getValue().summaryTypeProperty());

        toplineValueColumn0.setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());
        toplineValueColumn1.setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());
        toplineValueColumn2.setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());
        toplineValueColumn3.setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());

        retailerDescriptionColumn0.setCellValueFactory(cellData -> cellData.getValue().summaryTypeProperty());
        retailerDescriptionColumn1.setCellValueFactory(cellData -> cellData.getValue().summaryTypeProperty());
        retailerDescriptionColumn2.setCellValueFactory(cellData -> cellData.getValue().summaryTypeProperty());
        retailerDescriptionColumn3.setCellValueFactory(cellData -> cellData.getValue().summaryTypeProperty());

        retailerValueColumn0.setCellValueFactory(cellData -> cellData.getValue().summaryValueProperty());
        retailerValueColumn1.setCellValueFactory(cellData -> cellData.getValue().summaryValueProperty());
        retailerValueColumn2.setCellValueFactory(cellData -> cellData.getValue().summaryValueProperty());
        retailerValueColumn3.setCellValueFactory(cellData -> cellData.getValue().summaryValueProperty());


        // Name Table
        parameterNameColumn.setCellFactory(tc-> new CustomTextCell<>());

        // Calculation Table
        januaryColumn.setCellFactory(tc -> new ParameterValueEditingCell());
        februaryColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        marchColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        aprilColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        mayColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        juneColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        julyColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        augustColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        septemberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        octoberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        novemberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        decemberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        pricingPromotionTableOne.getSelectionModel().setCellSelectionEnabled(true);

        // Topline Table
        toplineDescriptionColumn0.setCellFactory(tc-> new CustomTextCell<>());
        toplineDescriptionColumn1.setCellFactory(tc-> new CustomTextCell<>());
        toplineDescriptionColumn2.setCellFactory(tc-> new CustomTextCell<>());
        toplineDescriptionColumn3.setCellFactory(tc-> new CustomTextCell<>());

        toplineValueColumn0.setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        toplineValueColumn1.setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        toplineValueColumn2.setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        toplineValueColumn3.setCellFactory(tc-> new CustomNonEditCell<>("$",""));

        // Retailer Table
        retailerDescriptionColumn0.setCellFactory(tc-> new CustomTextCell<>());
        retailerDescriptionColumn1.setCellFactory(tc-> new CustomTextCell<>());
        retailerDescriptionColumn2.setCellFactory(tc-> new CustomTextCell<>());
        retailerDescriptionColumn3.setCellFactory(tc-> new CustomTextCell<>());

        retailerValueColumn0.setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        retailerValueColumn1.setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        retailerValueColumn2.setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        retailerValueColumn3.setCellFactory(tc-> new CustomNonEditCell<>("$",""));


        oldSkuInDistributionValue = (BigDecimal) pricingPromotionTableOne.getItems().get(0).getJanuary();
        oldSkuInDistributionValue = (BigDecimal) pricingPromotionTableOne.getItems().get(0).getJanuary();

        for (int i=1; i<=12; i++){
            skuCountChangeOld.add((Integer) pricingPromotionTableOne.getItems().get(1).getMonth(i));
        }
        for (int i=1; i<=12; i++){
            confidencePerOld.add((BigDecimal) pricingPromotionTableOne.getItems().get(2).getMonth(i));
        }



//        PromoPlan defaultPromoPlan = new PromoPlan(1);
//        defaultPromoPlan.setCommited(false);
//        this.currentPromoPlan = new SimpleObjectProperty<>();
//        firstSelectedRtmOption.bindBidirectional();


        rtmBox0.setConverter(new RtmBoxConverter());
        rtmBox1.setConverter(new RtmBoxConverter());
        rtmBox2.setConverter(new RtmBoxConverter());
        rtmBox3.setConverter(new RtmBoxConverter());



        weeklyVelocityField0.setTextFormatter(new TextFormatter<Double>(firstTableController.getDoubleInputConverter(), 0.0, firstTableController.getDoubleInputFilter()));

        for (int i=0; i<4; i++) {
            getPromoPlans().get(i).getRtmBox().setDisable(true);
            getPromoPlans().get(i).getWeeklyPromoUfswField().setDisable(true);
            getPromoPlans().get(i).getCommitButton().setDisable(true);
        }

        currentPromoPlanIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPromoPlanIndex, Number newPromoPlanIndex) {
                PromoPlan newPromoPlan = getPromoPlans().get((int)newPromoPlanIndex);
                // CHange background

                // Save table items
                if((int)oldPromoPlanIndex!=-1) {
                    PromoPlan oldPromoPlan = getPromoPlans().get((int)oldPromoPlanIndex);
                    oldPromoPlan.getEditButton().getParent().setStyle("-fx-background-color: #3b381a");
                    oldPromoPlan.getRtmBox().setDisable(true);
                    oldPromoPlan.getWeeklyPromoUfswField().setDisable(true);
                    oldPromoPlan.getCommitButton().setDisable(true);
                    oldPromoPlan.setParameters(pricingPromotionTableOne.getItems());
                }
                //Change promo year
                setCurrentPromoPlanIndex((int)newPromoPlanIndex);
                // Set new table items
                pricingPromotionTableOne.setItems(newPromoPlan.getParameters());
                // enable buttons
                newPromoPlan.getRtmBox().setDisable(false);
                newPromoPlan.getWeeklyPromoUfswField().setDisable(false);
                newPromoPlan.getCommitButton().setDisable(false);
                // set transparent backgroudn
                newPromoPlan.getEditButton().getParent().setStyle("-fx-background-color: transparent");
            }
        });
        setCurrentPromoPlanIndex(0);


        retailer = new SimpleObjectProperty<>();
        retailer.addListener(new ChangeListener<Retailer>() {
            @Override
            public void changed(ObservableValue<? extends Retailer> observableValue, Retailer oldRetailer, Retailer newRetailer) {

                updatePromoSummaries();
                updateMonthlyTotalVolumeAndGrossProfit();

                rtmBox0.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());
                rtmBox1.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());
                rtmBox2.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());
                rtmBox3.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());


                weeklyVelocityField0.setText("0.0");

                retailer.get().currentProductProperty().addListener(new ChangeListener<RetailerProduct>() {
                    @Override
                    public void changed(ObservableValue<? extends RetailerProduct> observableValue, RetailerProduct oldRetailer, RetailerProduct newRetailer) {
                        updatePromoSummaries();
                        updateMonthlyTotalVolumeAndGrossProfit();
                        // also update labels in future
                    }
                });
            }
        });
    }

    public void clickEditButton (ActionEvent event){
        for (int i = 0; i< 4; i++){
            if (event.getSource().equals(getPromoPlans().get(i).getEditButton())){
                //Change promo year
                setCurrentPromoPlanIndex(i);
            }
        }
    }

    public void clickCommitButton (ActionEvent event){
        for (int i = 0; i< 4; i++){
            PromoPlan promoPlan = getPromoPlans().get(i);
            Button commitButton = promoPlan.getCommitButton();
            if (event.getSource().equals(commitButton)){
                if (promoPlan.isCommited()){
                    promoPlan.setCommited(false);
                    commitButton.setText("Commit to Forecast");
                    commitButton.setStyle(null);
                } else {
                    promoPlan.setCommited(true);
                    commitButton.setText("Committed to Forecast");
                    commitButton.setStyle("-fx-background-color:green");
                }
            }
        }
    }

    /*

     */
    public BigDecimal getSumValue(int value, boolean topline ) {
        BigDecimal summedValue = new BigDecimal("0.0");
        for (int i = 1; i <= 12; i++) {
            switch (value) {
                case 0:
                    if (topline){
                        summedValue = summedValue.add(getManufacturerGrossSalesActual(i));
                    }
                    else{
                        summedValue = summedValue.add(getRetailerGrossSales(i)).setScale(2, RoundingMode.HALF_UP);
                    }
                    break;
                case 1:
                    if (topline) {
                        summedValue = summedValue.add(getManufacturerNet1Rev(i));
                    }
                    else {
                        summedValue = summedValue.add(getRetailerGrossProfit(i));
                    }
                    break;
                case 2:
                    if (topline) {
                        summedValue = summedValue.add(getTotalVolume(i));
                    }
                    break;
                case 3:
                    summedValue = summedValue.add(getRetailerGrossSales(i));
                    break;
                case 4:
                    summedValue = summedValue.add(getRetailerGrossProfit(i));
                    break;
                case 5:
                    break;
            }
        }
        // GPM = Gross Profit divided by gross sales
        if (value==1 && !topline){
            if (getSumValue(0, false).compareTo(new BigDecimal("0.0"))==0) {
                return new BigDecimal("0.0");
            }
            return summedValue.divide(getSumValue(0, false), 2 , RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
        }
        // Avg Retail = Retailer Gross Sales divided by total volume
        if (value==2 && !topline){
            if (getSumValue(2, true).compareTo(new BigDecimal("0.0"))==0) {
                return new BigDecimal("0.0");
            }
            return getSumValue(0, false).divide(getSumValue(2, true), 2 , RoundingMode.HALF_UP);
        }
        else{
            return summedValue;
        }
    }
    public void updatePromoSummaries(){
        for (int i= 0; i<3; i++) {
            PromoPlan currentPromoPlan = getPromoPlans().get(getCurrentPromoPlanIndex());
            // CHANGE THIS, needs to update with table being updated
            BigDecimal plannedNet1Rate = new BigDecimal("0.0");
            if (getSumValue(2, true).compareTo(new BigDecimal("0.0"))>0) {
                plannedNet1Rate = getSumValue(1, true).divide(getSumValue(2, true), 2, RoundingMode.HALF_UP);
            }
            currentPromoPlan.getPlannedNet1RateLabel().setText("$"+ plannedNet1Rate.toString());

            BigDecimal goalDifference = plannedNet1Rate.subtract(getRetailer().getCurrentRetailerProduct().getProduct().getUnitNet1Goal());
            if (goalDifference.compareTo(new BigDecimal("0.0"))>0){
                currentPromoPlan.getGoalLabel().getParent().setStyle("-fx-background-color: red");
            }
            currentPromoPlan.getGoalLabel().setText("$"+goalDifference.toString()+ " to goal");

            updateTable(getCurrentPromoPlanIndex(), i);
        }
    }

    public void updateTable(int selectedYear, int value) {
        TableView<Summary> toplineTable = new TableView<>();
        TableView<Summary> retailerTable = new TableView<>();
        switch (selectedYear) {
            case 0:
                toplineTable = toplineTable0;
                retailerTable = retailerTable0;
                break;
            case 1:
                toplineTable = toplineTable1;
                retailerTable = retailerTable1;
                break;
            case 2:
                toplineTable = toplineTable2;
                retailerTable = retailerTable2;
                break;
            case 3:
                toplineTable = toplineTable3;
                retailerTable = retailerTable3;
                break;
        }
        toplineTable.getItems().get(value).setSummaryValue(getSumValue(value,true));
        retailerTable.getItems().get(value).setSummaryValue(getSumValue(value, false));
    }

    public ComboBox<RTMOption> getRtmBox0() {
        return rtmBox0;
    }
    public void changeWeeklyVelocityEvent(ActionEvent event) {
        // Set Current promoPlan
        updatePromoSummaries(); // CHAAANGED
        updateMonthlyTotalVolumeAndGrossProfit();
    }
    public BigDecimal getWeeklyVelocity(){
        if (weeklyVelocityField0.getText() == null) {
            return new BigDecimal("0.0");
        }
        return new BigDecimal(weeklyVelocityField0.getText());
    }

    public void changeRtmBoxEvent(ActionEvent event) {
        //
        PromoPlan currentPromoPlan = getPromoPlans().get(getCurrentPromoPlanIndex());
        RTMOption selectedRtmOption = currentPromoPlan.getRtmBox().getSelectionModel().getSelectedItem();
        currentPromoPlan.getEverydayLabel().setText("$" + selectedRtmOption.getResultingEverydayRetailOverride() + " Everyday");
        currentPromoPlan.getCostLabel().setText("$" + selectedRtmOption.getLandedStoreCost().setScale(2,RoundingMode.HALF_UP)+ " Cost");
        BigDecimal gpm = new BigDecimal("0.0");
        if (!selectedRtmOption.getLandedStoreCost().equals(new BigDecimal("0.0"))) {
            gpm = (selectedRtmOption.getResultingEverydayRetailOverride().subtract(selectedRtmOption.getLandedStoreCost())).divide(selectedRtmOption.getResultingEverydayRetailOverride(), 2, RoundingMode.HALF_UP);
        }
        currentPromoPlan.getGpmLabel().setText(gpm.multiply(new BigDecimal("100"))+ "% GPM");

        updatePromoSummaries();
        updateMonthlyTotalVolumeAndGrossProfit();
    }

    private ObservableList getSummaryTable() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("Net Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("Total Units", new BigDecimal("0.0")));
        return summaries;
    }
    private ObservableList getSummaryTable2() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("GPM", new BigDecimal("0.0")));
        summaries.add(new Summary("Avg. Retail", new BigDecimal("0.0")));
        return summaries;
    }

    public void hideTable(ActionEvent event) {
        pricingPromotionTableOne.setItems(getParameters());
    }
    public void changeMonthCellEvent(TableColumn.CellEditEvent editedCell) {
        Parameter parameterSelected = pricingPromotionTableOne.getSelectionModel().getSelectedItem();
        TableColumn<Parameter<?>,?> column = editedCell.getTableColumn();
        int colIndexPlusOne = pricingPromotionTableOne.getColumns().indexOf(column)+1;
        System.out.println("column index"+colIndexPlusOne);
        System.out.println("Parameter Selected:"+parameterSelected.toString());
        if (parameterSelected.getName().equals("Skus In Distribution")){
            BigDecimal difference = ((BigDecimal) editedCell.getNewValue()).subtract(oldSkuInDistributionValue);
            for (int i=2; i<=12; i++) {
                parameterSelected.setMonth(i, ((BigDecimalParameter) parameterSelected).getMonth(i).add(difference));
            }
            oldSkuInDistributionValue = (BigDecimal) editedCell.getNewValue();
        }
        if (parameterSelected.getName().equals("Sku-Count Change")){
            int selectedIndex = pricingPromotionTableOne.getSelectionModel().getFocusedIndex();

            BigDecimal confidencePer = (BigDecimal)pricingPromotionTableOne.getItems().get(selectedIndex+1).getMonth(colIndexPlusOne);
            BigDecimal skuCountChange = BigDecimal.valueOf((int) pricingPromotionTableOne.getItems().get(selectedIndex).getMonth(colIndexPlusOne));
            BigDecimal oldSkuCountChange = BigDecimal.valueOf(skuCountChangeOld.get(colIndexPlusOne-1)); //error here
            Parameter skusInDistribution = pricingPromotionTableOne.getItems().get(selectedIndex-1);
            BigDecimal addToSkusInDistribution = (skuCountChange.subtract(oldSkuCountChange)).multiply( confidencePer).divide((new BigDecimal("100.0")),2, RoundingMode.HALF_UP);
            for (int i=colIndexPlusOne; i<=12; i++) {
                skusInDistribution.setMonth(i, ((BigDecimalParameter) skusInDistribution).getMonth(i).add(addToSkusInDistribution));
            }
            skuCountChangeOld.set(colIndexPlusOne-1,skuCountChange.intValue());

//            BigDecimal addToSkusInDistribution = ((BigDecimal) confidencePer.getMonth(colIndexPlusOne)).multiply((BigDecimal) parameterSelected.getMonth(colIndexPlusOne));
//            System.out.println("Add to skus in Distribution"+ addToSkusInDistribution);
        }
        if (parameterSelected.getName().equals("Confidence %")){
            int selectedIndex = pricingPromotionTableOne.getSelectionModel().getFocusedIndex();
            BigDecimal skuCountChange = BigDecimal.valueOf((int) pricingPromotionTableOne.getItems().get(selectedIndex-1).getMonth(colIndexPlusOne));
            Parameter skusInDistribution = pricingPromotionTableOne.getItems().get(selectedIndex-2);
            BigDecimal oldConfidencePer = (BigDecimal) confidencePerOld.get(colIndexPlusOne-1);
            BigDecimal confidencePer = (BigDecimal)pricingPromotionTableOne.getItems().get(selectedIndex).getMonth(colIndexPlusOne);
            BigDecimal addToSkusInDistribution = (confidencePer.subtract(oldConfidencePer))
                    .multiply(skuCountChange).divide((new BigDecimal("100.0")),2, RoundingMode.HALF_UP);
            for (int i=colIndexPlusOne; i<=12; i++) {
                skusInDistribution.setMonth(i, ((BigDecimalParameter) skusInDistribution).getMonth(i).add(addToSkusInDistribution));
            }
            confidencePerOld.set(colIndexPlusOne-1, confidencePer);
        }
        if (parameterSelected.getName().equals("Everyday Retail")){  // ALso needs to depend on GPM, changing
            int selectedIndex = pricingPromotionTableOne.getSelectionModel().getFocusedIndex();
            ((BigDecimalParameter)pricingPromotionTableOne.getItems().get(selectedIndex+1)).setMonth(colIndexPlusOne, getEverydayCost(colIndexPlusOne));
        }
        if (parameterSelected.getName().equals("Promoted Retail 1") || parameterSelected.getName().equals("Required GPM % 1")){
            Parameter promoUnitCost1;
            getPromoCost(colIndexPlusOne, 1);
            for (Parameter row: pricingPromotionTableOne.getItems()){
                if (row.getName().equals("Promo Unit Cost 1")){
                    row.setMonth(colIndexPlusOne,getPromoCost(colIndexPlusOne, 1));
                }
            }
        }
        if (parameterSelected.getName().equals("Promoted Retail 2") || parameterSelected.getName().equals("Required GPM % 2")){
            for (Parameter row: pricingPromotionTableOne.getItems()){
                if (row.getName().equals("Promo Unit Cost 2")){
                    row.setMonth(colIndexPlusOne,getPromoCost(colIndexPlusOne, 2));
                }
            }
        }
        if (parameterSelected.getName().equals("Promoted Retail 1") || parameterSelected.getName().equals("Required GPM % 1") || parameterSelected.getName().equals("Everyday Retail")){
            for (Parameter row: pricingPromotionTableOne.getItems()){
                if (row.getName().equals("Promo Discount % 1")){
                    row.setMonth(colIndexPlusOne,getPromoDiscount(colIndexPlusOne, 1));
                }
            }
        }
        if (parameterSelected.getName().equals("Promoted Retail 2") || parameterSelected.getName().equals("Required GPM % 2") || parameterSelected.getName().equals("Everyday Retail")){
            for (Parameter row: pricingPromotionTableOne.getItems()){
                if (row.getName().equals("Promo Discount % 2")){
                    row.setMonth(colIndexPlusOne,getPromoDiscount(colIndexPlusOne, 2));
                }
            }
        }
        updateMonthlyTotalVolumeAndGrossProfit();
        updatePromoSummaries(); // CHAAANGED
    }
//        selectedCells.addListener(new ListChangeListener<TablePosition>() {
//            @Override
//            public void onChanged(Change change) {
//                for (TablePosition pos : selectedCells) {
//                    System.out.println("Cell selected in row "+pos.getRow()+" and column "+pos.getTableColumn().getText());
//                }
//            }
//        });2
//
//        TablePosition<Parameter<?>,?> column = pricingPromotionTableOne.getSelectionModel().getSelectedCells();
//        int colIndex = getTableView().getColumns().indexOf(column);
//        ParameterSelected.getMonth


    public void updateMonthlyTotalVolumeAndGrossProfit(){
        for (Parameter param: pricingPromotionTableOne.getItems()) {
                if (param.getName().startsWith("Total Volume=")) {
                    BigDecimal total = new BigDecimal("0.0");
                    for (int i = 1; i <= 12; i++) {
                        param.setMonth(i, getTotalVolume(i).setScale(0, RoundingMode.HALF_UP));
                        total = total.add(getTotalVolume(i));
                    }
                    param.setName("Total Volume= " + total.setScale(0, RoundingMode.HALF_UP).toString());
                    parameterNameTable.setItems(pricingPromotionTableOne.getItems());
                }
                if (param.getName().startsWith("Gross Profit ")) {
                    BigDecimal total = new BigDecimal("0.0");
                    for (int i = 1; i <= 12; i++) {
                        param.setMonth(i, getManufacturerGrossProfit(i).setScale(0, RoundingMode.HALF_UP));
                        total = total.add(getManufacturerGrossProfit(i));
                    }
                    param.setName("Gross Profit (Plan)= $"+ total.setScale(0, RoundingMode.HALF_UP));
                    parameterNameTable.setItems(pricingPromotionTableOne.getItems());
                }

        }
    }

    public RTMOption getFirstSelectedRtmOption() {
        return firstSelectedRtmOption.get();
    }

    public SimpleObjectProperty<RTMOption> firstSelectedRtmOptionProperty() {
        return firstSelectedRtmOption;
    }

    public void setFirstSelectedRtmOption(RTMOption firstSelectedRtmOption) {
        this.firstSelectedRtmOption.set(firstSelectedRtmOption);
    }

    public ObservableList<PromoPlan> getDummyPromoPlans(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getParameters(),getSummaryTable(), getSummaryTable2(),new BigDecimal("0.75"),firstTableController.getRTMOptions().get(1), false, 0, rtmBox0, weeklyVelocityField0, editButton0, commitButton0,toplineTable0,retailerTable0, everydayLabel0, costLabel0, gpmLabel0, plannedNet1RateLabel0, goalLabel0));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, 1 , rtmBox1, weeklyVelocityField1, editButton1, commitButton1, toplineTable1, retailerTable1, everydayLabel1, costLabel1, gpmLabel1, plannedNet1RateLabel1, goalLabel1));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, 2 , rtmBox2, weeklyVelocityField2, editButton2, commitButton2, toplineTable2, retailerTable2, everydayLabel2, costLabel2, gpmLabel2, plannedNet1RateLabel2, goalLabel2));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, 3, rtmBox3, weeklyVelocityField3, editButton3, commitButton3, toplineTable3, retailerTable3, everydayLabel3, costLabel3, gpmLabel3, plannedNet1RateLabel3, goalLabel3));
        return promoPlans;
    }

    public ObservableList<Parameter<?>> getParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("5.0"),new BigDecimal("5.0") ,new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"), false));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,2,0,0,0,0,0));
        Parameter confidencePer = new BigDecimalParameter("Confidence %", "%");
        confidencePer.setJuly(new BigDecimal(50.0));
        parameters.add(confidencePer);
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7000.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 158, 158, 158,158,158,158,158,158,158,158,158,158));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("6.49"),new BigDecimal("6.49") ,new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),true));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.89"),new BigDecimal("3.89") ,new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),true));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("0.91"),new BigDecimal("0.91") ,new BigDecimal("0.93"),new BigDecimal("0.95"),new BigDecimal("1.07"),new BigDecimal("1.27"),new BigDecimal("1.46"),new BigDecimal("1.23"),new BigDecimal("0.86"),new BigDecimal("0.80"),new BigDecimal("0.82"),new BigDecimal("0.86"),true));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("5.99"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("40.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4,0,0,0,0,4,4,4,0,0,0,4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("2.5"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.59"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"), true));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("7.7"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"), true));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR","" ,"","","","4 Week TPR","4 Week TPR","4 Week TPR","","","","4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        return parameters;
    }
    public ObservableList<Parameter<?>> getEmptyParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        Parameter confidencePer = new BigDecimalParameter("Confidence %", "%");
        parameters.add(confidencePer);
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR","" ,"","","","4 Week TPR","4 Week TPR","4 Week TPR","","","","4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        return parameters;
    }

    public BigDecimal getSlottingGameTheoryd (int month){
        BigDecimal confidencePer = new BigDecimal("0.0");
        BigDecimal slottingInvestment = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (row.getName().equals("Confidence %")){
                confidencePer = ((BigDecimal) row.getMonth(month)).divide(new BigDecimal(100.0)) ;
            }
            if (row.getName().equals("Slotting Investment")){
                slottingInvestment = (BigDecimal) row.getMonth(month);
            }
        }
        return confidencePer.multiply(slottingInvestment);
    }

    public BigDecimal getEverydayCost (int month){
        BigDecimal everydayGPM = getRetailer().getEverydayGPM();
        BigDecimal everydayRetail = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (row.getName().equals("Everyday Retail")){
                everydayRetail = ((BigDecimal) row.getMonth(month));
            }
        }
        return everydayRetail.multiply(new BigDecimal("1.0").subtract(everydayGPM.divide(new BigDecimal(100.0))));
    }
    public BigDecimal getPromoCost (int month, int promo){
        BigDecimal requiredGPM = new BigDecimal("0.0");
        BigDecimal promotedRetail = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (promo==1) {
                if (row.getName().equals("Required GPM % 1")) {
                    requiredGPM = ((BigDecimal) row.getMonth(month));
                }
                if (row.getName().equals("Promoted Retail 1")) {
                    promotedRetail = ((BigDecimal) row.getMonth(month));
                }
            }
            if (promo==2) {
                if (row.getName().equals("Required GPM % 2")) {
                    requiredGPM = ((BigDecimal) row.getMonth(month));
                }
                if (row.getName().equals("Promoted Retail 2")) {
                    promotedRetail = ((BigDecimal) row.getMonth(month));
                }
            }
        }
        return promotedRetail.multiply((new BigDecimal("1.0").subtract(requiredGPM.divide(new BigDecimal("100.0")))));
    }

    public BigDecimal getEverydayRetailWeeks(int month){
        BigDecimal durationWeeks2 = new BigDecimal("0.0");
        BigDecimal durationWeeks1 = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (row.getName().equals("Duration (weeks) 1")){
                durationWeeks1 = new BigDecimal((int) row.getMonth(month));
            }
            if (row.getName().equals("Duration (weeks) 2")){
                durationWeeks2 = new BigDecimal((int) row.getMonth(month));
            }
        }
        return getWeeksInPeriod(month).subtract(durationWeeks1).subtract(durationWeeks2);
    }
    // Add textfield for weekly velocity
    public BigDecimal getEveryDayVolume (int month) {
        BigDecimal skusInDistribution = new BigDecimal("0.0");
        BigDecimal weeklyVelocity = new BigDecimal("0.0"); // need to add textfield for this -- changed this
        BigDecimal storeCount = new BigDecimal("0.0");
        BigDecimal everyDayRetailWeeks = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (row.getName().equals("Skus In Distribution")){
                skusInDistribution = (BigDecimal) row.getMonth(month);
            }
            if (row.getName().equals("Store Count")){
                storeCount = new BigDecimal((int)row.getMonth(month));
            }
            if (weeklyVelocityField0.getText()!=null && !new BigDecimal(weeklyVelocityField0.getText()).equals(new BigDecimal("0.0"))){ //added this
                weeklyVelocity= new BigDecimal(weeklyVelocityField0.getText());
            }
        }
        everyDayRetailWeeks = getEverydayRetailWeeks(month);
        return skusInDistribution.multiply(storeCount).multiply(weeklyVelocity).multiply(everyDayRetailWeeks);
    }
    public BigDecimal getPromoVolume(int month, int promo){
        BigDecimal skusInDistribution = new BigDecimal("0.0");
        BigDecimal weeklyVelocity = new BigDecimal("0.0");
        BigDecimal storeCount = new BigDecimal("0.0");
        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
        BigDecimal durationWeeks = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (row.getName().equals("Skus In Distribution")) {
                skusInDistribution = ((BigDecimal) row.getMonth(month));
            }
            if (row.getName().equals("Store Count")){
                storeCount = new BigDecimal((int)row.getMonth(month));
            }
            if (promo==1) {
                if (row.getName().equals("Volume Lift Multiple 1")) {
                    volumeLiftMultiple = ((BigDecimal) row.getMonth(month));
                }
                if (row.getName().equals("Duration (weeks) 1")) {
                    durationWeeks= new BigDecimal((int) row.getMonth(month));
                }
            }
            if (weeklyVelocityField0.getText()!=null && !new BigDecimal(weeklyVelocityField0.getText()).equals(new BigDecimal("0.0"))){ //added this
                weeklyVelocity= new BigDecimal(weeklyVelocityField0.getText());
            }
            if (promo==2) {
                if (row.getName().equals("Volume Lift Multiple 2")) {
                    volumeLiftMultiple = ((BigDecimal) row.getMonth(month));
                }
                if (row.getName().equals("Duration (weeks) 2")) {
                    durationWeeks= new BigDecimal((int) row.getMonth(month));
                }
            }
        }
        return skusInDistribution.multiply(weeklyVelocity).multiply(storeCount).multiply(volumeLiftMultiple).multiply(durationWeeks);
    }

    public BigDecimal getTotalVolume(int month){
        return getEveryDayVolume(month).add(getPromoVolume(month,1).add(getPromoVolume(month,2)));
    }
    public BigDecimal getPromoDiscount(int month, int promo){
        if (getEverydayCost(month).compareTo(new BigDecimal("0.0"))==0 || getPromoCost(month,promo).compareTo(new BigDecimal("0.0"))==0){
            return new BigDecimal("0.0");
        }
        return new BigDecimal("100").multiply((getEverydayCost(month).subtract(getPromoCost(month, promo))).divide(getEverydayCost(month),4, RoundingMode.HALF_UP));
    }
    public BigDecimal getRetailerGrossSales(int month){
        BigDecimal promotedRetail1 = new BigDecimal("0.0");
        BigDecimal promotedRetail2 = new BigDecimal("0.0");
        BigDecimal everydayRetail = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()) {
            if (row.getName().equals("Everyday Retail")) {
                everydayRetail = ((BigDecimal) row.getMonth(month));
            }
            if (row.getName().equals("Promoted Retail 1")) {
                promotedRetail1 = ((BigDecimal) row.getMonth(month));
            }
            if (row.getName().equals("Promoted Retail 2")) {
                promotedRetail2 = ((BigDecimal) row.getMonth(month));
            }
        }
        BigDecimal grossSales = everydayRetail.multiply(getEveryDayVolume(month));
        grossSales =grossSales.add(promotedRetail1.multiply(getPromoVolume(month,1)));
        grossSales = grossSales.add(promotedRetail2.multiply(getPromoVolume(month,2)));
        return grossSales;
    }
    public BigDecimal getRetailerNetCost(int month){
        return (getEverydayCost(month).multiply(getEveryDayVolume(month))).add(getPromoCost(month,1).multiply(getPromoVolume(month,1))).add(getPromoCost(month,2).multiply(getPromoVolume(month,2)));
    }
    public BigDecimal getRetailerGrossProfit(int month){
        return getRetailerGrossSales(month).subtract(getRetailerNetCost(month));
    }
    public BigDecimal getManufacturerGrossSalesList(int month){
        return getTotalVolume(month).multiply(getRetailer().getCurrentRetailerProduct().getProduct().getUnitListCost()); //new BigDecimal("3.59") HARDCODED LIST FOR NOW
    }
    public BigDecimal getManufacturerGrossSalesActual(int month){
        return getManufacturerGrossSalesList(month).subtract(getFobDiscounts(month));
    }

    public BigDecimal getFobDiscounts(int month) {// HARDCODED TRUE FOR NOW
        RTMOption selectedRtm = getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
        if (selectedRtm!=null) {
            if (selectedRtm.isFob()) {// F.O.B
                return getTotalVolume(month).multiply((getRetailer().getCurrentRetailerProduct().getProduct().getUnitListCost()).subtract(getRetailer().getCurrentRetailerProduct().getProduct().getUnitFobCost())); // LISt and FOB -- CHANGED
            }
            return new BigDecimal("0.0");
        }
        System.out.println("FaLSE VALUES PLEASE SELECT AN RTM OPTION- fob discoutns");
        return null;
    }

    public BigDecimal getSpoilsFeesTS(int month){
        return (getRetailer().getSpoilsFees()).divide(new BigDecimal("100")).multiply(getManufacturerGrossSalesActual(month));
    }
    public BigDecimal getEverydayAllowanceTS(int month){
        RTMOption selectedRtm = getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
        Product product= getRetailer().getCurrentRetailerProduct().getProduct();
        if (selectedRtm!=null) {
            if (selectedRtm.isFob()) {
                return getTotalVolume(month).multiply((product.getUnitFobCost()).subtract(selectedRtm.getFirstReceiver()));
            } else {
                return getTotalVolume(month).multiply((product.getUnitListCost()).subtract(selectedRtm.getFirstReceiver()));
            }
        }
        System.out.println("False values: Please select an RTM- everydayALLOWANcETS");
        return null;
    }
    public BigDecimal getPromoTS(int month, int promo){
        return getPromoVolume(month,promo).multiply(getEverydayCost(month).subtract(getPromoCost(month,promo)));
    }
    public BigDecimal getFixedCostsTS(int month){
        BigDecimal fixedCosts1 = new BigDecimal("0.0");
        BigDecimal fixedCosts2 = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()) {
            if (row.getName().equals("Fixed Costs 1")) {
                fixedCosts1 = ((BigDecimal) row.getMonth(month));
            }
            if (row.getName().equals("Fixed Costs 2")) {
                fixedCosts2 = ((BigDecimal) row.getMonth(month));
            }
        }
        return fixedCosts1.add(fixedCosts2);
    }
    public BigDecimal getTotalTS (int month){
        return getEverydayAllowanceTS(month).add(getPromoTS(month,1)).add(getPromoTS(month,2)).add(getFixedCostsTS(month)).add(getSpoilsFeesTS(month));
    }
    public BigDecimal getManufacturerNet1Rev(int month){ // CONTAINS BUG IN MAIN EXCEL APP
        return getManufacturerGrossSalesActual(month).subtract(getTotalTS(month)).add(getFobDiscounts(month));
    }
    public BigDecimal getManufacturerFreightCost(int month){
        RTMOption selectedRTM= getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
        if (selectedRTM!=null) {
            return selectedRTM.getFreightOutPerUnit().add(getFobDiscounts(month)); //HARDCODED THE FREIHT OUT PER UNIT
        }
        System.out.println("PLEASE SELECT RTM - man freight cost");
        return null;
    }
    public BigDecimal getManufacturerNet2Rev(int month){
        return getManufacturerNet1Rev(month).subtract(getManufacturerFreightCost(month));
    }
    public BigDecimal getManufacturerNet3Rev(int month){
        return getManufacturerNet2Rev(month).subtract(getSlottingGameTheoryd(month));
    }
    public BigDecimal getManufacturerCogs (int month){
        BigDecimal cogs = getRetailer().getCurrentRetailerProduct().getProduct().getUnitBlendedCogs();
        return cogs.multiply(getTotalVolume(month));
    }
    public BigDecimal getManufacturerGrossProfit (int month){
        return getManufacturerNet3Rev(month).subtract(getManufacturerCogs(month)); //2.05 COGS
    }
    public BigDecimal getPromoLiftMultiple(int month){
        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()) {
            if (row.getName().equals("Volume Lift Multiple 1")) {
                volumeLiftMultiple = ((BigDecimal) row.getMonth(month));
            }
        }
        return volumeLiftMultiple;
    }


    public BigDecimal getWeeksInPeriod(int month){
        if (month == 2 ||month == 3 ||month == 5 || month == 6 || month == 8 || month == 9 || month == 11 || month ==12){
            return new BigDecimal("4.0");
        }
        return new BigDecimal("5.0");
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
    }

    public Integer getCurrentPromoPlanIndex() {
        return currentPromoPlanIndex.get();
    }

    public SimpleIntegerProperty currentPromoPlanIndexProperty() {
        return currentPromoPlanIndex;
    }

    public void setCurrentPromoPlanIndex(Integer currentPromoPlanIndex) {
        this.currentPromoPlanIndex.set(currentPromoPlanIndex);
    }

    public ObservableList<PromoPlan> getPromoPlans() {
        return promoPlans.get();
    }

    public SimpleObjectProperty<ObservableList<PromoPlan>> promoPlansProperty() {
        return promoPlans;
    }

    public void setPromoPlans(ObservableList<PromoPlan> promoPlans) {
        this.promoPlans.set(promoPlans);
    }

    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        FXMLLoader secondTableLoader = App.createFXMLLoader("secondTable");
        App.setSceneRoot(secondTableLoader.load());

        firstTableController firstTableController =secondTableLoader.getController();
        firstTableController.setRetailer(getRetailer());
    }
    @FXML
    private void switchToAssortment(ActionEvent event) throws IOException {
        FXMLLoader assortmentLoader = App.createFXMLLoader("assortment");
        App.setSceneRoot(assortmentLoader.load());

        AssortmentController assortmentController =assortmentLoader.getController();
        assortmentController.setRetailer(getRetailer());
    }
}
