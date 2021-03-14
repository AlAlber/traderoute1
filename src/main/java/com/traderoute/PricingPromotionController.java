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

    private final int manufacturerGrossSalesMethod =0;
    private final int manufacturerNet1RevMethod =1;
    private final int totalVolumeMethod = 2;
    private final int retailerGrossSalesMethod =3;
    private final int retailerGrossProfitMethod =4;


    private ObservableList<ComboBox> rtmBoxes= FXCollections.observableArrayList(rtmBox0, rtmBox1, rtmBox2, rtmBox3);
    private ObservableList<SimpleObjectProperty> editButtons = FXCollections.observableArrayList(new SimpleObjectProperty<>(editButton0),new SimpleObjectProperty<>(editButton1), new SimpleObjectProperty<>(editButton2), new SimpleObjectProperty<>(editButton3));
    private ObservableList<Button> commitButtons = FXCollections.observableArrayList(commitButton0, commitButton1, commitButton2, commitButton3);
    private ObservableList<TextField> weeklyVelocityFields = FXCollections.observableArrayList(weeklyVelocityField0, weeklyVelocityField1,weeklyVelocityField2, weeklyVelocityField3);
    private ObservableList<Label> everydayLabels = FXCollections.observableArrayList(everydayLabel0, everydayLabel1, everydayLabel2, everydayLabel3);
    private ObservableList<Label> costLabel = FXCollections.observableArrayList(costLabel0, costLabel1, costLabel2, costLabel3);
    private ObservableList<Label> gpmLabel = FXCollections.observableArrayList(gpmLabel0,gpmLabel1, gpmLabel2, gpmLabel3);
    private ObservableList<Label> plannedNet1RateLabel = FXCollections.observableArrayList(plannedNet1RateLabel0, plannedNet1RateLabel1, plannedNet1RateLabel2, plannedNet1RateLabel3);
    private ObservableList<Label> goalLabel = FXCollections.observableArrayList(goalLabel0, goalLabel1, goalLabel2, goalLabel3);

    private static final int skusInDistributionIndex = 0;
    private static final int skuCountChangeIndex =1;
    private static final int confidencePerIndex=2;
    private static final int slottingInvestmentIndex =3;
    private static final int storeCountIndex =4;
    private static final int everydayRetailIndex =6;
    private static final int everydayUnitCostIndex =7;
    private static final int seasonalityIndicesIndex =9;
    private static final int promotedRetail1Index =10;
    private static final int requiredGpm1Index =11;
    private static final int durationWeeks1Index =12;
    private static final int volumeLiftMultiple1Index =13;
    private static final int fixedCosts1Index =14;
    private static final int promoUnitCost1Index=15;
    private static final int promoDiscount1Index=16;
    private static final int promotionalCommentaryIndex =17;
    private static final int promotedRetail2Index =18;
    private static final int requiredGpm2Index =19;
    private static final int durationWeeks2Index =20;
    private static final int volumeLiftMultiple2Index =21;
    private static final int fixedCosts2Index =22;
    private static final int promoUnitCost2Index=23;
    private static final int promoDiscount2Index=24;
    private static final int totalVolumeIndex =25;
    private static final int grossProfitPlanIndex =26;


    private BigDecimal oldSkuInDistributionValue;
    private List<BigDecimal> confidencePerOld = new ArrayList<BigDecimal>();
    private List<Integer> skuCountChangeOld = new ArrayList<Integer>();

    private SimpleObjectProperty<Retailer> retailer;
//            new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),firstTableController.getRetailerProducts().get(0) ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));


    private SimpleIntegerProperty currentPromoPlanIndex= new SimpleIntegerProperty(-1);
    private SimpleObjectProperty<ObservableList<PromoPlan>> promoPlans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        for (Button commitButton: commitButtons.get()){
//            commitButton.setWrapText(true);
//        }
        for (SimpleObjectProperty editButton: editButtons){
            if (editButton.get()!=null) {
                ((Button)editButton.get()).setWrapText(true);
            } 9
        }

        pricingPromotionTableOne.setEditable(true);
        pricingPromotionTableOne.setItems(getParameters());

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
        januaryColumn.setCellFactory(tc -> new ParameterEditCell());
        februaryColumn.setCellFactory(tc-> new ParameterEditCell());
        marchColumn.setCellFactory(tc-> new ParameterEditCell());
        aprilColumn.setCellFactory(tc-> new ParameterEditCell());
        mayColumn.setCellFactory(tc-> new ParameterEditCell());
        juneColumn.setCellFactory(tc-> new ParameterEditCell());
        julyColumn.setCellFactory(tc-> new ParameterEditCell());
        augustColumn.setCellFactory(tc-> new ParameterEditCell());
        septemberColumn.setCellFactory(tc-> new ParameterEditCell());
        octoberColumn.setCellFactory(tc-> new ParameterEditCell());
        novemberColumn.setCellFactory(tc-> new ParameterEditCell());
        decemberColumn.setCellFactory(tc-> new ParameterEditCell());
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

        rtmBox0.setConverter(new RtmBoxConverter());
        rtmBox1.setConverter(new RtmBoxConverter());
        rtmBox2.setConverter(new RtmBoxConverter());
        rtmBox3.setConverter(new RtmBoxConverter());

        weeklyVelocityField0.setTextFormatter(new TextFormatter<Double>(firstTableController.getDoubleInputConverter(), 0.0, firstTableController.getDoubleInputFilter()));

        this.promoPlans= new SimpleObjectProperty<>();
        retailer = new SimpleObjectProperty<>();
        retailer.addListener(new ChangeListener<Retailer>() {
            @Override
            public void changed(ObservableValue<? extends Retailer> observableValue, Retailer oldRetailer, Retailer newRetailer) {

                promoPlans.set(getDummyPromoPlans());

                promoPlans.get().get(0).setEditButton(editButton0);
                promoPlans.get().get(1).setEditButton(editButton1);
                promoPlans.get().get(2).setEditButton(editButton2);
                promoPlans.get().get(3).setEditButton(editButton3);

                for (int i=0; i<4; i++) {
                    getPromoPlans().get(i).getRtmBox().setDisable(true);
                    getPromoPlans().get(i).getWeeklyPromoUfswField().setDisable(true);
                    getPromoPlans().get(i).getCommitButton().setDisable(true);
                }
                if (currentPromoPlanIndex.get()!=-1) {
                    updatePromoSummaries();
                    updateMonthlyTotalVolumeAndGrossProfit();
                }

                rtmBox0.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());
                rtmBox1.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());
                rtmBox2.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());
                rtmBox3.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());


                weeklyVelocityField0.setText("0.0");

                setCurrentPromoPlanIndex(0);

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
                if (promoPlan.getCommitted()){
                    promoPlan.setCommitted(false);
                    commitButton.setText("Commit to Forecast");
                    commitButton.setStyle(null);
                } else {
                    promoPlan.setCommitted(true);
                    commitButton.setText("Committed to Forecast");
                    commitButton.setStyle("-fx-background-color:green");
                }
            }
        }
    }
    public BigDecimal getTwelveMonthTotal(int methodToCall){
        BigDecimal computedValue = new BigDecimal("0.0");
        for (int i= 1; i<=12; i++) {
            switch (methodToCall) {
                case 0:
                    computedValue = computedValue.add(getManufacturerGrossSalesActual(i));
                    break;
                case 1:
                    computedValue = computedValue.add(getManufacturerNet1Rev(i));
                    break;
                case 2:
                    computedValue = computedValue.add(getTotalVolume(i));
                    break;
                case 3:
                    computedValue = computedValue.add(getRetailerGrossSales(i));
                    break;
                case 4:
                    computedValue= computedValue.add(getRetailerGrossProfit(i));
                    break;
                default:
                    System.out.println("No method corresponds to this int");
            }
        }
        return computedValue;
    }
    /*

     */
    public BigDecimal getSumValue(int value, boolean topline ) {
        BigDecimal sumValue = new BigDecimal("0.0");
        switch (value) {
            case 0:
                if (topline){
                    sumValue = getTwelveMonthTotal(manufacturerGrossSalesMethod);
                } else{
                    sumValue = getTwelveMonthTotal(retailerGrossSalesMethod);
                } break;
            case 1:
                if (topline) {
                    sumValue = getTwelveMonthTotal(manufacturerNet1RevMethod);
                } else {
                    if (getTwelveMonthTotal(retailerGrossSalesMethod).compareTo(new BigDecimal("0.0"))==0) {
                        sumValue = new BigDecimal("0.0");
                    } else{
                        sumValue = getTwelveMonthTotal(retailerGrossProfitMethod).divide(getTwelveMonthTotal(retailerGrossSalesMethod), 4 , RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    }
                } break;
            case 2:
                if (topline) {
                    sumValue = getTwelveMonthTotal(totalVolumeMethod);
                } else {
                    if (getTwelveMonthTotal(totalVolumeMethod).compareTo(new BigDecimal("0.0"))==0) {
                        sumValue= new BigDecimal("0.0");
                    } else {
                        sumValue = getTwelveMonthTotal(retailerGrossSalesMethod).divide(getTwelveMonthTotal(totalVolumeMethod), 4, RoundingMode.HALF_UP);
                    }
                }break;
            } return sumValue.setScale(4,RoundingMode.HALF_UP);
    }

    public void updatePromoSummaries(){
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

        for (int i= 0; i<3; i++) {
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

    public void changeSkusInDistribution(BigDecimal newCellValue,BigDecimalParameter selectedParam){
        BigDecimal difference = newCellValue.subtract(oldSkuInDistributionValue);
        for (int i=2; i<=12; i++) {
            selectedParam.setMonth(i, selectedParam.getMonth(i).add(difference));
        }
        oldSkuInDistributionValue = newCellValue;
    }
    public void changeSkuCountChange(int rowIndex, int colIndex){
        BigDecimal confidencePer = (BigDecimal)pricingPromotionTableOne.getItems().get(rowIndex+1).getMonth(colIndex);
        BigDecimal skuCountChange = BigDecimal.valueOf((int) pricingPromotionTableOne.getItems().get(rowIndex).getMonth(colIndex));
        BigDecimal oldSkuCountChange = BigDecimal.valueOf(skuCountChangeOld.get(colIndex-1)); //error here
        Parameter skusInDistribution = pricingPromotionTableOne.getItems().get(rowIndex-1);
        BigDecimal addToSkusInDistribution = (skuCountChange.subtract(oldSkuCountChange)).multiply( confidencePer).divide((new BigDecimal("100.0")),2, RoundingMode.HALF_UP);
        for (int i=colIndex; i<=12; i++) {
            skusInDistribution.setMonth(i, ((BigDecimalParameter) skusInDistribution).getMonth(i).add(addToSkusInDistribution));
        }
        skuCountChangeOld.set(colIndex-1,skuCountChange.intValue());
    }
    public void changeConfidencePer(int rowIndex, int colIndex){
        BigDecimal skuCountChange = BigDecimal.valueOf((int) pricingPromotionTableOne.getItems().get(rowIndex-1).getMonth(colIndex));
        Parameter skusInDistribution = pricingPromotionTableOne.getItems().get(rowIndex-2);
        BigDecimal oldConfidencePer = (BigDecimal) confidencePerOld.get(colIndex-1);
        BigDecimal confidencePer = (BigDecimal)pricingPromotionTableOne.getItems().get(rowIndex).getMonth(colIndex);
        BigDecimal addToSkusInDistribution = (confidencePer.subtract(oldConfidencePer))
                .multiply(skuCountChange).divide((new BigDecimal("100.0")),2, RoundingMode.HALF_UP);
        for (int i=colIndex; i<=12; i++) {
            skusInDistribution.setMonth(i, ((BigDecimalParameter) skusInDistribution).getMonth(i).add(addToSkusInDistribution));
        }
        confidencePerOld.set(colIndex-1, confidencePer);
    }
    public void changePromoRetailRequiredGpm(int colIndex, int promo){
        int promoUnitCostIndex = 0;
        int promoDiscountIndex = 0;
        if (promo==1){
            promoUnitCostIndex=promoUnitCost1Index;
            promoDiscountIndex=promoDiscount1Index;
        }if (promo==2){
            promoUnitCostIndex=promoUnitCost2Index;
            promoDiscountIndex=promoDiscount2Index;
        }
        ObservableList<Parameter<?>> params = pricingPromotionTableOne.getItems();
        ((BigDecimalParameter) params.get(promoUnitCostIndex)).setMonth(colIndex, getPromoCost(colIndex, promo));
        ((BigDecimalParameter) params.get(promoDiscountIndex)).setMonth(colIndex, getPromoDiscount(colIndex, promo));
    }
    public void changeMonthCellEvent(TableColumn.CellEditEvent editedCell) {
        Parameter parameterSelected = pricingPromotionTableOne.getSelectionModel().getSelectedItem();
        TableColumn<Parameter<?>,?> column = editedCell.getTableColumn();
        int colIndex = pricingPromotionTableOne.getColumns().indexOf(column);
        int rowIndex = pricingPromotionTableOne.getSelectionModel().getFocusedIndex();
        parameterSelected.setMonth(colIndex, editedCell.getNewValue());
        System.out.println("column index"+colIndex);
        System.out.println("Parameter Selected:"+parameterSelected.toString());
        if (rowIndex==skusInDistributionIndex){
            changeSkusInDistribution((BigDecimal) editedCell.getNewValue(),(BigDecimalParameter) parameterSelected);
        }if (rowIndex==skuCountChangeIndex){
            changeSkuCountChange(rowIndex,colIndex);
        }if (rowIndex==confidencePerIndex){
            changeConfidencePer(rowIndex,colIndex);
        }if (rowIndex==storeCountIndex){
            for (int i= colIndex; i<=12; i++ ) {
                parameterSelected.setMonth(i, editedCell.getNewValue());
            }
        }if (rowIndex==everydayRetailIndex){  // ALso needs to depend on GPM, changing
            ((BigDecimalParameter)pricingPromotionTableOne.getItems().get(rowIndex+1)).setMonth(colIndex, getEverydayCost(colIndex));
        }if (rowIndex==promotedRetail1Index || rowIndex==requiredGpm1Index){
            changePromoRetailRequiredGpm(colIndex,1);
        }if (rowIndex==promotedRetail2Index || rowIndex==requiredGpm2Index) {
            changePromoRetailRequiredGpm(colIndex,2);
        }
        updateMonthlyTotalVolumeAndGrossProfit();
        updatePromoSummaries();
    }

    public void updateMonthlyTotalVolumeAndGrossProfit(){
        for (Parameter param: pricingPromotionTableOne.getItems()) {
                if (param.getName().startsWith("Total Volume=")) {
                    BigDecimal total = new BigDecimal("0.0");
                    for (int i = 1; i <= 12; i++) {
                        param.setMonth(i, getTotalVolume(i).setScale(0, RoundingMode.HALF_UP));
                        total = total.add(getTotalVolume(i));
                    }
                    param.setName("Total Volume= " + total.setScale(0, RoundingMode.HALF_UP).toString());
                }
                if (param.getName().startsWith("Gross Profit ")) {
                    BigDecimal total = new BigDecimal("0.0");
                    for (int i = 1; i <= 12; i++) {
                        param.setMonth(i, getManufacturerGrossProfit(i).setScale(0, RoundingMode.HALF_UP));
                        total = total.add(getManufacturerGrossProfit(i));
                    }
                    param.setName("Gross Profit (Plan)= $"+ total.setScale(0, RoundingMode.HALF_UP));
                }

        }
    }

    public ObservableList<PromoPlan> getDummyPromoPlans(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();promoPlans.add(new PromoPlan(getParameters(),getSummaryTable(), getSummaryTable2(),new BigDecimal("0.0"),retailer.get().getRetailerProducts().get(0).getRtmOptions().get(1), false, rtmBox0, weeklyVelocityField0, editButton0, commitButton0,toplineTable0,retailerTable0, everydayLabel0, costLabel0, gpmLabel0, plannedNet1RateLabel0, goalLabel0));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false , rtmBox1, weeklyVelocityField1, editButton1, commitButton1, toplineTable1, retailerTable1, everydayLabel1, costLabel1, gpmLabel1, plannedNet1RateLabel1, goalLabel1));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, rtmBox2, weeklyVelocityField2, editButton2, commitButton2, toplineTable2, retailerTable2, everydayLabel2, costLabel2, gpmLabel2, plannedNet1RateLabel2, goalLabel2));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, rtmBox3, weeklyVelocityField3, editButton3, commitButton3, toplineTable3, retailerTable3, everydayLabel3, costLabel3, gpmLabel3, plannedNet1RateLabel3, goalLabel3));
        return promoPlans;
    }

    public ObservableList<Parameter<?>> getParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("5.0"),new BigDecimal("5.0") ,new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,2,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("50.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7000.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 158, 158, 158,158,158,158,158,158,158,158,158,158));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("6.49"),new BigDecimal("6.49") ,new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.89"),new BigDecimal("3.89") ,new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),false));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("0.91"),new BigDecimal("0.91") ,new BigDecimal("0.93"),new BigDecimal("0.95"),new BigDecimal("1.07"),new BigDecimal("1.27"),new BigDecimal("1.46"),new BigDecimal("1.23"),new BigDecimal("0.86"),new BigDecimal("0.80"),new BigDecimal("0.82"),new BigDecimal("0.86"),false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("5.99"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("40.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4,0,0,0,0,4,4,4,0,0,0,4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("2.5"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.59"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("7.7"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR","" ,"","","","4 Week TPR","4 Week TPR","4 Week TPR","","","","4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        return parameters;
    }
    public ObservableList<Parameter<?>> getEmptyParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),false));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "","" ,"","","","","","","","","",""));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        return parameters;
    }

    public BigDecimal getSlottingGameTheoryd (int month){
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal confidencePer = ((BigDecimal) params.get(confidencePerIndex).getMonth(month)).divide(new BigDecimal(100.0));
        BigDecimal slottingInvestment = ((BigDecimal) params.get(slottingInvestmentIndex).getMonth(month));
        return confidencePer.multiply(slottingInvestment);
    }

    public BigDecimal getEverydayCost (int month){
        BigDecimal everydayGPM = getRetailer().getEverydayGPM();
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal everydayRetail = ((BigDecimal) params.get(everydayRetailIndex).getMonth(month));
        return everydayRetail.multiply(new BigDecimal("1.0").subtract(everydayGPM.divide(new BigDecimal(100.0))));
    }
    public BigDecimal getPromoCost (int month, int promo){
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal requiredGPM = new BigDecimal("0.0");
        BigDecimal promotedRetail = new BigDecimal("0.0");
        if (promo==1) {
            requiredGPM = (BigDecimal)params.get(requiredGpm1Index).getMonth(month);
            promotedRetail = (BigDecimal)params.get(promotedRetail1Index).getMonth(month);
        } if (promo==2) {
            requiredGPM = (BigDecimal)params.get(requiredGpm2Index).getMonth(month);
            promotedRetail = (BigDecimal)params.get(promotedRetail2Index).getMonth(month);
        }
        return promotedRetail.multiply((new BigDecimal("1.0").subtract(requiredGPM.divide(new BigDecimal("100.0")))));
    }

    public BigDecimal getEverydayRetailWeeks(int month){
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal durationWeeks1 = new BigDecimal((int) params.get(durationWeeks1Index).getMonth(month));
        BigDecimal durationWeeks2 = new BigDecimal((int) params.get(durationWeeks2Index).getMonth(month));
        return getWeeksInPeriod(month).subtract(durationWeeks1).subtract(durationWeeks2);
    }

    public BigDecimal getEverydayVolume(int month) {
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal skusInDistribution = (BigDecimal) params.get(skusInDistributionIndex).getMonth(month);
        BigDecimal weeklyVelocity = getWeeklyVelocity();
        BigDecimal storeCount = new BigDecimal((int) params.get(storeCountIndex).getMonth(month));
        BigDecimal everyDayRetailWeeks = getEverydayRetailWeeks(month);
        return skusInDistribution.multiply(storeCount).multiply(weeklyVelocity).multiply(everyDayRetailWeeks);
    }
    public BigDecimal getWeeklyVelocity(){
        String weeklyUfsw = getPromoPlans().get(getCurrentPromoPlanIndex()).getWeeklyPromoUfswField().getText();
        BigDecimal weeklyVelocity = new BigDecimal("0.0");
        if (weeklyUfsw!=null){
            weeklyVelocity= new BigDecimal(weeklyUfsw);
        }
        return weeklyVelocity;
    }
    public BigDecimal getPromoVolume(int month, int promo){
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal skusInDistribution = (BigDecimal) params.get(skusInDistributionIndex).getMonth(month);
        BigDecimal weeklyVelocity = getWeeklyVelocity();
        BigDecimal storeCount = new BigDecimal((int) params.get(storeCountIndex).getMonth(month));
        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
        BigDecimal durationWeeks = new BigDecimal("0.0");
        if (promo==1) {
            volumeLiftMultiple = (BigDecimal)params.get(volumeLiftMultiple1Index).getMonth(month);
            durationWeeks= new BigDecimal((int)params.get(durationWeeks1Index).getMonth(month));;
        }if (promo==2) {
            volumeLiftMultiple = (BigDecimal)params.get(volumeLiftMultiple2Index).getMonth(month);
            durationWeeks= new BigDecimal((int)params.get(durationWeeks2Index).getMonth(month));;
        }
        return skusInDistribution.multiply(weeklyVelocity).multiply(storeCount).multiply(volumeLiftMultiple).multiply(durationWeeks);
    }

    public BigDecimal getTotalVolume(int month){
        return getEverydayVolume(month).add(getPromoVolume(month,1).add(getPromoVolume(month,2)));
    }
    public BigDecimal getPromoDiscount(int month, int promo){
        if (getEverydayCost(month).compareTo(new BigDecimal("0.0"))==0 || getPromoCost(month,promo).compareTo(new BigDecimal("0.0"))==0){
            return new BigDecimal("0.0");
        } return new BigDecimal("100").multiply((getEverydayCost(month).subtract(getPromoCost(month, promo))).divide(getEverydayCost(month),4, RoundingMode.HALF_UP));
    }
    public BigDecimal getRetailerGrossSales(int month){
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal promotedRetail1 = (BigDecimal)params.get(promotedRetail1Index).getMonth(month);
        BigDecimal promotedRetail2 = (BigDecimal)params.get(promotedRetail2Index).getMonth(month);
        BigDecimal everydayRetail = (BigDecimal)params.get(everydayRetailIndex).getMonth(month);
        return everydayRetail.multiply(getEverydayVolume(month))
                .add(promotedRetail1.multiply(getPromoVolume(month,1)))
                .add(promotedRetail2.multiply(getPromoVolume(month,2)));
    }
    public BigDecimal getRetailerNetCost(int month){
        return (getEverydayCost(month).multiply(getEverydayVolume(month))).add(getPromoCost(month,1).multiply(getPromoVolume(month,1))).add(getPromoCost(month,2).multiply(getPromoVolume(month,2)));
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
                Product product = getRetailer().getCurrentRetailerProduct().getProduct();
                return getTotalVolume(month).multiply((product.getUnitListCost()).subtract(product.getUnitFobCost())); // LISt and FOB -- CHANGED
            }return new BigDecimal("0.0");
        } System.out.println("FaLSE VALUES PLEASE SELECT AN RTM OPTION- fob discoutns");
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
        } System.out.println("False values: Please select an RTM- everydayALLOWANcETS");
        return null;
    }
    public BigDecimal getPromoTS(int month, int promo){
        return getPromoVolume(month,promo).multiply(getEverydayCost(month).subtract(getPromoCost(month,promo)));
    }
    public BigDecimal getFixedCostsTS(int month){
        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
        BigDecimal fixedCosts1 = (BigDecimal)params.get(fixedCosts1Index).getMonth(month);;
        BigDecimal fixedCosts2 = (BigDecimal)params.get(fixedCosts2Index).getMonth(month);
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
        } System.out.println("PLEASE SELECT RTM - man freight cost");
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
    public BigDecimal getPromoLiftMultiple(int month, int promo){
        ObservableList<Parameter<?>> params = pricingPromotionTableOne.getItems();
        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
        if (promo==1){
            volumeLiftMultiple= (BigDecimal) params.get(volumeLiftMultiple1Index).getMonth(month);
        } if (promo==2) {
            volumeLiftMultiple= (BigDecimal) params.get(volumeLiftMultiple2Index).getMonth(month);
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
