package com.traderoute.controllers;

import com.github.plushaze.traynotification.animations.Animations;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.traderoute.*;
import com.traderoute.cells.CustomNonEditCell;
import com.traderoute.cells.CustomTextCell;
import com.traderoute.cells.ParameterEditCell;
import com.traderoute.data.*;
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
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

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




    private ObservableList<ComboBox> rtmBoxes;
    private ObservableList<Button> editButtons;
    private ObservableList<Button> commitButtons;
    private ObservableList<TextField> weeklyVelocityFields;
    private ObservableList<Label> everydayLabels;
    private ObservableList<Label> costLabels;
    private ObservableList<Label> gpmLabels;
    private ObservableList<Label> plannedNet1RateLabels;
    private ObservableList<Label> goalLabels;
    private ObservableList<TableView> toplineTables;
    private ObservableList<TableView> retailerTables;
    private ObservableList<TableColumn> toplineDescriptionColumns;
    private ObservableList<TableColumn> toplineValueColumns;
    private ObservableList<TableColumn> retailerDescriptionColumns;
    private ObservableList<TableColumn> retailerValueColumns;

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

    private List<BigDecimal> oldSkuInDistributionValues = new ArrayList<>(); // Only has to be one because only one gets edited
    private List<List<BigDecimal>> oldConfidencePers = new ArrayList<>();
    private List<List<Integer>> oldSkuCountChanges = new ArrayList<>();

    private SimpleObjectProperty<Retailer> retailer;
//            new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),firstTableController.getRetailerProducts().get(0) ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));

    TrayNotification summaryTrayNotification = new TrayNotification();
    TrayNotification commitNotification = new TrayNotification("Committed to Forecast", "This Plan is committed to the forecast",Notifications.SUCCESS);
    TrayNotification uncommitNotification = new TrayNotification("Commit Removed","This Plan is no longer committed to the forecast", Notifications.ERROR);
    TrayNotification durationWeeksErrorNotification = new TrayNotification();

    private SimpleIntegerProperty currentPromoPlanIndex= new SimpleIntegerProperty(-1);
    private SimpleObjectProperty<ObservableList<PromoPlan>> promoPlans;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        summaryTrayNotification.setTitle("Summaries Can't Be Calculated Yet");
        summaryTrayNotification.setNotification(Notifications.NOTICE);
        summaryTrayNotification.setRectangleFill(Paint.valueOf("#A79543"));
        summaryTrayNotification.setAnimation(Animations.POPUP);

        commitNotification.setRectangleFill(Paint.valueOf("#A79543"));
        commitNotification.setAnimation(Animations.POPUP);

        uncommitNotification.setRectangleFill(Paint.valueOf("#A79543"));
        uncommitNotification.setAnimation(Animations.POPUP);

        durationWeeksErrorNotification.setTitle("Inserted too many weeks");
        durationWeeksErrorNotification.setNotification( Notifications.ERROR);
        durationWeeksErrorNotification.setRectangleFill(Paint.valueOf("#A79543"));
        durationWeeksErrorNotification.setAnimation(Animations.POPUP);

        rtmBoxes= FXCollections.observableArrayList(rtmBox0, rtmBox1, rtmBox2, rtmBox3);
        editButtons = FXCollections.observableArrayList(editButton0, editButton1, editButton2, editButton3);
        commitButtons = FXCollections.observableArrayList(commitButton0, commitButton1, commitButton2, commitButton3);
        weeklyVelocityFields = FXCollections.observableArrayList(weeklyVelocityField0, weeklyVelocityField1,weeklyVelocityField2, weeklyVelocityField3);
        everydayLabels = FXCollections.observableArrayList(everydayLabel0, everydayLabel1, everydayLabel2, everydayLabel3);
        costLabels = FXCollections.observableArrayList(costLabel0, costLabel1, costLabel2, costLabel3);
        gpmLabels = FXCollections.observableArrayList(gpmLabel0,gpmLabel1, gpmLabel2, gpmLabel3);
        plannedNet1RateLabels = FXCollections.observableArrayList(plannedNet1RateLabel0, plannedNet1RateLabel1, plannedNet1RateLabel2, plannedNet1RateLabel3);
        goalLabels = FXCollections.observableArrayList(goalLabel0, goalLabel1, goalLabel2, goalLabel3);
        toplineTables = FXCollections.observableArrayList(toplineTable0,toplineTable1, toplineTable2,toplineTable3);
        retailerTables = FXCollections.observableArrayList(retailerTable0, retailerTable1, retailerTable2, retailerTable3);
        toplineDescriptionColumns =FXCollections.observableArrayList(toplineDescriptionColumn0, toplineDescriptionColumn1,toplineDescriptionColumn2,toplineDescriptionColumn3);
        toplineValueColumns = FXCollections.observableArrayList(toplineValueColumn0, toplineValueColumn1, toplineValueColumn2, toplineValueColumn3);
        retailerDescriptionColumns = FXCollections.observableArrayList( retailerDescriptionColumn0, retailerDescriptionColumn1,retailerDescriptionColumn2,retailerDescriptionColumn3);
        retailerValueColumns =FXCollections.observableArrayList(retailerValueColumn0, retailerValueColumn1, retailerValueColumn2, retailerValueColumn3);


        pricingPromotionTableOne.setEditable(true);
        pricingPromotionTableOne.setItems(getParameters());

        for (int i =0; i <4; i++){
            toplineTables.get(i).setItems(getSummaryTable());
            retailerTables.get(i).setItems(getSummaryTable2());
            commitButtons.get(i).setWrapText(true);
            editButtons.get(i).setWrapText(true);
            rtmBoxes.get(i).setConverter(new RtmBoxConverter());
            weeklyVelocityFields.get(i).setTextFormatter(new TextFormatter<Double>(RTMPlanningController.getDoubleInputConverter(), 0.0, RTMPlanningController.getDoubleInputFilter()));
            weeklyVelocityFields.get(i).setTooltip(new Tooltip("Please add the weekly velocity Unit/Flavor/Sku/Week"));
            rtmBoxes.get(i).setTooltip(new Tooltip("Please select a Route-to-Market from the ones you configured on the Route to Market Page"));
        }


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
        for (int i =0; i <4; i++){
            ((TableColumn<Summary, String>)toplineDescriptionColumns.get(i)).setCellValueFactory(cellData-> cellData.getValue().summaryTypeProperty());
            ((TableColumn<Summary, String>)toplineDescriptionColumns.get(i)).setCellFactory(tc-> new CustomTextCell<>());

            ((TableColumn<Summary, BigDecimal>)toplineValueColumns.get(i)).setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());
            ((TableColumn<Summary, BigDecimal>)toplineValueColumns.get(i)).setCellFactory(tc-> new CustomNonEditCell<>("$",""));

            ((TableColumn<Summary, String>)retailerDescriptionColumns.get(i)).setCellValueFactory(cellData-> cellData.getValue().summaryTypeProperty());
            ((TableColumn<Summary, String>)retailerDescriptionColumns.get(i)).setCellFactory(tc-> new CustomTextCell<>());

            ((TableColumn<Summary, BigDecimal>)retailerValueColumns.get(i)).setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());
            ((TableColumn<Summary, BigDecimal>)retailerValueColumns.get(i)).setCellFactory(tc-> new CustomNonEditCell<>("$",""));
        }


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

        for (int i=0; i<4; i++) {
            rtmBoxes.get(i).setDisable(true);
            weeklyVelocityFields.get(i).setDisable(true);
            commitButtons.get(i).setDisable(true);
//            if (retailer.get()!=null) {
                rtmBoxes.get(i).setItems(null); // change this
//            }
            }
        this.promoPlans= new SimpleObjectProperty<>();
        retailer = new SimpleObjectProperty<>();
        retailer.addListener(new ChangeListener<Retailer>() {
            @Override
            public void changed(ObservableValue<? extends Retailer> observableValue, Retailer oldRetailer, Retailer newRetailer) {

                promoPlans.set(getDummyPromoPlans());


                for (int j =0; j <4; j++) {
                    List<BigDecimal> oldConfidencePer = new ArrayList<>();
                    List<Integer> oldSkuCountChange = new ArrayList<>();
                    for (int i = 1; i <= 12; i++) {
                        oldSkuCountChange.add((Integer) getPromoPlans().get(j).getParameters().get(1).getMonth(i));
                        oldConfidencePer.add((BigDecimal) getPromoPlans().get(j).getParameters().get(2).getMonth(i));
                    }
                    oldConfidencePers.add(oldConfidencePer);
                    oldSkuCountChanges.add(oldSkuCountChange);
                    oldSkuInDistributionValues.add((BigDecimal) getPromoPlans().get(j).getParameters().get(0).getJanuary());
                    System.out.println("Old Confidence Pers row "+ j + " = "+ oldConfidencePers.get(j));
                    System.out.println("Old Sku Count Changes row "+ j + " = "+ oldSkuCountChanges.get(j));
                }


                for (int i=0; i<4; i++) {
                    rtmBoxes.get(i).setDisable(true);
                    weeklyVelocityFields.get(i).setDisable(true);
                    commitButtons.get(i).setDisable(true);
                    rtmBoxes.get(i).setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions()); // change this
                }
                if (currentPromoPlanIndex.get()!=-1) {
                    updatePromoSummaries();
                    updateMonthlyTotalVolumeAndGrossProfit();
                }

                setCurrentPromoPlanIndex(0);

                retailer.get().currentRetailerProductIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldRetailer, Number newRetailer) {
//                        updatePromoSummaries();
//                        updateMonthlyTotalVolumeAndGrossProfit();
//                        // also update labels in future
//                        pricingPromotionTableOne.setItems(getRetailer().getRetailerProducts().get(retailer.get().getCurrentRetailerProductIndex()).getPromoPlans().get(0).getParameters());


                    }
                });
            }
        });
        currentPromoPlanIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPromoPlanIndex, Number newPromoPlanIndex) {
                // CHange background
                int oldIndex = (int)oldPromoPlanIndex;
                int newIndex = (int)newPromoPlanIndex;

                // Save table items
                if(oldIndex!=-1) {
                    editButtons.get(oldIndex).getParent().setStyle("-fx-background-color: #3b381a");
                    rtmBoxes.get(oldIndex).setDisable(true);
                    weeklyVelocityFields.get(oldIndex).setDisable(true);
                    commitButtons.get(oldIndex).setDisable(true);
                    getPromoPlans().get(oldIndex).setParameters(pricingPromotionTableOne.getItems());
                }
                //Change promo year
                setCurrentPromoPlanIndex(newIndex);
                // Set new table items
                pricingPromotionTableOne.setItems(getPromoPlans().get(newIndex).getParameters());
                // enable buttons
                rtmBoxes.get(newIndex).setDisable(false);
                weeklyVelocityFields.get(newIndex).setDisable(false);
                commitButtons.get(newIndex).setDisable(false);
                // set transparent backgroudn
                editButtons.get(newIndex).getParent().setStyle("-fx-background-color: transparent");
            }
        });


    }

    public void clickEditButton (ActionEvent event){
        for (int i = 0; i< 4; i++){
            if (event.getSource().equals(editButtons.get(i))){
                //Change promo year
                setCurrentPromoPlanIndex(i);
            }
        }
    }

    public void clickCommitButton (ActionEvent event){
        for (int i = 0; i< 4; i++){
            PromoPlan promoPlan = getPromoPlans().get(i);
            Button commitButton = commitButtons.get(getCurrentPromoPlanIndex());
            if (event.getSource().equals(commitButton)){
                if (promoPlan.getCommitted()){
                    promoPlan.setCommitted(false);
                    commitButton.setText("Commit to Forecast");
                    commitButton.setStyle(null);
                    commitButton.setStyle("-fx-background-color:  #ccbe7f");

                    uncommitNotification.showAndDismiss(Duration.seconds(5));
                } else {
                    promoPlan.setCommitted(true);
                    commitButton.setText("Committed to Forecast");
                    commitButton.setStyle("-fx-background-color:green");

                    commitNotification.showAndDismiss(Duration.seconds(5));
                }
            }
        }
    }
    public PromoPlan getCurrentPromoPlan(){
        return getPromoPlans().get(getCurrentPromoPlanIndex());
    }

//    public BigDecimal getTwelveMonthTotal(int methodToCall){
//        BigDecimal computedValue = new BigDecimal("0.0");
//        for (int i= 1; i<=12; i++) {
//            switch (methodToCall) {
//                case 0:
//                    computedValue = computedValue.add(getCurrentPromoPlan().getManufacturerGrossSalesActual(i));
//                    break;
//                case 1:
//                    computedValue = computedValue.add(getCurrentPromoPlan().getManufacturerNet1Rev(i));
//                    break;
//                case 2:
//                    computedValue = computedValue.add(getCurrentPromoPlan().getTotalVolume(i));
//                    break;
//                case 3:
//                    computedValue = computedValue.add(getCurrentPromoPlan().getRetailerGrossSales(i));
//                    break;
//                case 4:
//                    computedValue= computedValue.add(getCurrentPromoPlan().getRetailerGrossProfit(i));
//                    break;
//                default:
//                    System.out.println("No method corresponds to this int");
//            }
//        }
//        return computedValue;
//    }
    /*

     */
//    public BigDecimal getSumValue(int value, boolean topline ) {
//        BigDecimal sumValue = new BigDecimal("0.0");
//        switch (value) {
//            case 0:
//                if (topline){
//                    sumValue = getCurrentPromoPlan().getTwelveMonthTotal(manufacturerGrossSalesMethod);
//                } else{
//                    sumValue = getCurrentPromoPlan().getTwelveMonthTotal(retailerGrossSalesMethod);
//                } break;
//            case 1:
//                if (topline) {
//                    sumValue = getCurrentPromoPlan().getTwelveMonthTotal(manufacturerNet1RevMethod);
//                } else {
//                    if (getCurrentPromoPlan().getTwelveMonthTotal(retailerGrossSalesMethod).compareTo(new BigDecimal("0.0"))==0) {
//                        sumValue = new BigDecimal("0.0");
//                    } else{
//                        sumValue = getCurrentPromoPlan().getTwelveMonthTotal(retailerGrossProfitMethod).divide(getCurrentPromoPlan().getTwelveMonthTotal(retailerGrossSalesMethod), 4 , RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
//                    }
//                } break;
//            case 2:
//                if (topline) {
//                    sumValue = getCurrentPromoPlan().getTwelveMonthTotal(totalVolumeMethod);
//                } else {
//                    if (getCurrentPromoPlan().getTwelveMonthTotal(totalVolumeMethod).compareTo(new BigDecimal("0.0"))==0) {
//                        sumValue= new BigDecimal("0.0");
//                    } else {
//                        sumValue = getCurrentPromoPlan().getTwelveMonthTotal(retailerGrossSalesMethod).divide(getCurrentPromoPlan().getTwelveMonthTotal(totalVolumeMethod), 4, RoundingMode.HALF_UP);
//                    }
//                }break;
//            } return sumValue.setScale(4,RoundingMode.HALF_UP);
//    }

    public void updatePromoSummaries(){
        if (showSummaryNotification()){
            return;
        } else {
            System.out.println("geetin here?");
            // CHANGE THIS, needs to update with table being updated
            BigDecimal plannedNet1Rate = new BigDecimal("0.0");
            if (getCurrentPromoPlan().getSumValue(2, true).compareTo(new BigDecimal("0.0")) > 0) {
                if (showSummaryNotification()) {
                    plannedNet1Rate = new BigDecimal("0.0");
                } else {
                    plannedNet1Rate = getCurrentPromoPlan().getSumValue(1, true).divide(getCurrentPromoPlan().getSumValue(2, true), 2, RoundingMode.HALF_UP);
                }
            }
            plannedNet1RateLabels.get(getCurrentPromoPlanIndex()).setText("$" + plannedNet1Rate.toString());

            BigDecimal goalDifference = plannedNet1Rate.subtract(getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct().getUnitNet1Goal());
            Label goalLabel = goalLabels.get(getCurrentPromoPlanIndex());
            if (goalDifference.compareTo(new BigDecimal("0.0")) > 0) {
                goalLabel.getParent().setStyle("-fx-background-color: red");
            }
            goalLabel.setText("$" + goalDifference.toString() + " to goal");

            for (int i = 0; i < 3; i++) {
                ((Summary)toplineTables.get(getCurrentPromoPlanIndex()).getItems().get(i)).setSummaryValue(getCurrentPromoPlan().getSumValue(i, true));
                ((Summary)retailerTables.get(getCurrentPromoPlanIndex()).getItems().get(i)).setSummaryValue(getCurrentPromoPlan().getSumValue(i, false));
            }
        }

    }

    public void changeWeeklyVelocityEvent(ActionEvent event) {
        PromoPlan currentPlan = getPromoPlans().get(getCurrentPromoPlanIndex());
        currentPlan.setWeeklyPromoUfsw(getWeeklyVelocity());
        updatePromoSummaries();
        updateMonthlyTotalVolumeAndGrossProfit();
    }

    public boolean showSummaryNotification() {
        if (getCurrentPromoPlanIndex() == -1) {
            return false;
        }else {
            PromoPlan currentPlan = getPromoPlans().get(getCurrentPromoPlanIndex());

            if (currentPlan.getSelectedRtm() == null && currentPlan.getWeeklyPromoUfsw().compareTo(new BigDecimal("0.0")) == 0) {
                summaryTrayNotification.setMessage("Please select a Route-to-Market and enter a weekly velocity for your product.");
                summaryTrayNotification.showAndDismiss(Duration.seconds(5));
                return true;
            } else if (currentPlan.getSelectedRtm() == null) {
                summaryTrayNotification.setMessage("Please select a Route-to-Market.");
                summaryTrayNotification.showAndDismiss(Duration.seconds(5));
                return true;
            } else if (currentPlan.getWeeklyPromoUfsw().compareTo(new BigDecimal("0.0")) == 0) {
                summaryTrayNotification.setMessage("Please enter a weekly velocity for your product.");
                summaryTrayNotification.showAndDismiss(Duration.seconds(5));
                return true;
            } else {
                return false;
            }
        }
    }


    public void changeRtmBoxEvent(ActionEvent event) {
        PromoPlan currentPromoPlan = getPromoPlans().get(getCurrentPromoPlanIndex());
        RTMOption selectedRtmOption = (RTMOption) rtmBoxes.get(getCurrentPromoPlanIndex()).getSelectionModel().getSelectedItem();
        everydayLabels.get(getCurrentPromoPlanIndex()).setText("$" + selectedRtmOption.getResultingEverydayRetailOverride() + " Everyday");
        costLabels.get(getCurrentPromoPlanIndex()).setText("$" + selectedRtmOption.getLandedStoreCost().setScale(2,RoundingMode.HALF_UP)+ " Cost");
        BigDecimal gpm = new BigDecimal("0.0");
        if (!selectedRtmOption.getLandedStoreCost().equals(new BigDecimal("0.0"))) {
            gpm = (selectedRtmOption.getResultingEverydayRetailOverride().subtract(selectedRtmOption.getLandedStoreCost())).divide(selectedRtmOption.getResultingEverydayRetailOverride(), 2, RoundingMode.HALF_UP);
        }
        gpmLabels.get(getCurrentPromoPlanIndex()).setText(gpm.multiply(new BigDecimal("100"))+ "% GPM");
        currentPromoPlan.setSelectedRtm(selectedRtmOption);
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
        String title = "Results Cannot Be Calculated Yet";
        String message = "Please select a Route to Market from the Dropdown";

        Notification notification = Notifications.NOTICE;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.setRectangleFill(Paint.valueOf("#A79543"));
        tray.setAnimation(Animations.POPUP);
        tray.showAndDismiss(Duration.seconds(5));
        pricingPromotionTableOne.setItems(getParameters());
    }

    public void changeSkusInDistribution(BigDecimal newCellValue, BigDecimalParameter selectedParam){
        BigDecimal difference = newCellValue.subtract(oldSkuInDistributionValues.get(getCurrentPromoPlanIndex()));
        for (int i=2; i<=12; i++) {
            selectedParam.setMonth(i, selectedParam.getMonth(i).add(difference));
        }
        oldSkuInDistributionValues.set(getCurrentPromoPlanIndex(),newCellValue);
    }
    public void changeSkuCountChange(int rowIndex, int colIndex){
        BigDecimal confidencePer = (BigDecimal)pricingPromotionTableOne.getItems().get(rowIndex+1).getMonth(colIndex);
        BigDecimal skuCountChange = BigDecimal.valueOf((int) pricingPromotionTableOne.getItems().get(rowIndex).getMonth(colIndex));
        BigDecimal oldSkuCountChange = BigDecimal.valueOf(oldSkuCountChanges.get(getCurrentPromoPlanIndex()).get(colIndex-1)); //error here
        Parameter skusInDistribution = pricingPromotionTableOne.getItems().get(rowIndex-1);
        BigDecimal addToSkusInDistribution = (skuCountChange.subtract(oldSkuCountChange)).multiply( confidencePer).divide((new BigDecimal("100.0")),2, RoundingMode.HALF_UP);
        for (int i=colIndex; i<=12; i++) {
            skusInDistribution.setMonth(i, ((BigDecimalParameter) skusInDistribution).getMonth(i).add(addToSkusInDistribution));
        }
        oldSkuCountChanges.get(getCurrentPromoPlanIndex()).set(colIndex-1,skuCountChange.intValue());
    }
    public void changeConfidencePer(int rowIndex, int colIndex){
        BigDecimal skuCountChange = BigDecimal.valueOf((int) pricingPromotionTableOne.getItems().get(rowIndex-1).getMonth(colIndex));
        Parameter skusInDistribution = pricingPromotionTableOne.getItems().get(rowIndex-2);
        BigDecimal oldConfidencePer = (BigDecimal) oldConfidencePers.get(getCurrentPromoPlanIndex()).get(colIndex-1);
        BigDecimal confidencePer = (BigDecimal)pricingPromotionTableOne.getItems().get(rowIndex).getMonth(colIndex);
        BigDecimal addToSkusInDistribution = (confidencePer.subtract(oldConfidencePer))
                .multiply(skuCountChange).divide((new BigDecimal("100.0")),2, RoundingMode.HALF_UP);
        for (int i=colIndex; i<=12; i++) {
            skusInDistribution.setMonth(i, ((BigDecimalParameter) skusInDistribution).getMonth(i).add(addToSkusInDistribution));
        }
        oldConfidencePers.get(getCurrentPromoPlanIndex()).set(colIndex-1, confidencePer);
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
        ((BigDecimalParameter) params.get(promoUnitCostIndex)).setMonth(colIndex, getCurrentPromoPlan().getPromoCost(colIndex, promo));
        ((BigDecimalParameter) params.get(promoDiscountIndex)).setMonth(colIndex, getCurrentPromoPlan().getPromoDiscount(colIndex, promo));
    }

    public void changeMonthCellEvent(TableColumn.CellEditEvent editedCell) {
        Parameter parameterSelected = pricingPromotionTableOne.getSelectionModel().getSelectedItem();
        TableColumn<Parameter<?>,?> column = editedCell.getTableColumn();
        int colIndex = pricingPromotionTableOne.getColumns().indexOf(column);
        int rowIndex = pricingPromotionTableOne.getSelectionModel().getFocusedIndex();
        if (rowIndex!=promotionalCommentaryIndex){
            if (new BigDecimal(editedCell.getNewValue().toString()).compareTo(new BigDecimal("0.0"))<0){
                parameterSelected.setMonth(colIndex,new BigDecimal("0.0"));
                return;
            }else if (rowIndex==durationWeeks1Index || rowIndex == durationWeeks2Index){
                if ((int)editedCell.getNewValue()>getCurrentPromoPlan().getWeeksInPeriod(colIndex)){
                    parameterSelected.setMonth(colIndex,0);
                    durationWeeksErrorNotification.setMessage("This month has only "+ getCurrentPromoPlan().getWeeksInPeriod(colIndex) + " weeks total in its period.");
                    durationWeeksErrorNotification.showAndDismiss(Duration.seconds(5));
                }
            } else {
                parameterSelected.setMonth(colIndex, editedCell.getNewValue());
            }
        }
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
        }if (rowIndex==everydayRetailIndex){  // ALso needs to depend on GPM, changing , check with john what needs to happen with this
            ((BigDecimalParameter)pricingPromotionTableOne.getItems().get(rowIndex+1)).setMonth(colIndex, getCurrentPromoPlan().getEverydayCost(colIndex));
        }if (rowIndex==promotedRetail1Index || rowIndex==requiredGpm1Index){
            changePromoRetailRequiredGpm(colIndex,1);
        }if (rowIndex==promotedRetail2Index || rowIndex==requiredGpm2Index) {
            changePromoRetailRequiredGpm(colIndex,2);
        }
        updateMonthlyTotalVolumeAndGrossProfit();
        updatePromoSummaries();
    }

    public void updateMonthlyTotalVolumeAndGrossProfit() {
        if (showSummaryNotification()) {
            return;
        } else {
            // Update monthly total volumes and year total volume
            BigDecimalParameter totalVolumeParam = (BigDecimalParameter) pricingPromotionTableOne.getItems().get(totalVolumeIndex);

            BigDecimal yearTotalVolume = new BigDecimal("0.0");
            for (int i = 1; i <= 12; i++) {
                totalVolumeParam.setMonth(i, getCurrentPromoPlan().getTotalVolume(i).setScale(0, RoundingMode.HALF_UP));
                yearTotalVolume = yearTotalVolume.add(getCurrentPromoPlan().getTotalVolume(i));
            }
            totalVolumeParam.setName("Total Volume= " + yearTotalVolume.setScale(0, RoundingMode.HALF_UP).toString());

            // Update monthly grossProfit and year total gross profit
            BigDecimalParameter grossProfitPlanParam = (BigDecimalParameter) pricingPromotionTableOne.getItems().get(grossProfitPlanIndex);

            BigDecimal yearTotalGrossProfit = new BigDecimal("0.0");
            for (int i = 1; i <= 12; i++) {
                grossProfitPlanParam.setMonth(i, getCurrentPromoPlan().getManufacturerGrossProfit(i).setScale(0, RoundingMode.HALF_UP));
                yearTotalGrossProfit = yearTotalGrossProfit.add(getCurrentPromoPlan().getManufacturerGrossProfit(i));
            }
            grossProfitPlanParam.setName("Gross Profit (Plan)= $" + yearTotalGrossProfit.setScale(0, RoundingMode.HALF_UP));
        }
    }


    public  ObservableList<PromoPlan> getDummyPromoPlans(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getParameters(),getSummaryTable(), getSummaryTable2(),new BigDecimal("0.0"),getRetailer().getRetailerProducts().get(retailer.get().getCurrentRetailerProductIndex()).getPromoPlans().get(0).getSelectedRtm(),  false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"),getRetailer().getRetailerProducts().get(retailer.get().getCurrentRetailerProductIndex()).getPromoPlans().get(1).getSelectedRtm(),false ));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"),getRetailer().getRetailerProducts().get(retailer.get().getCurrentRetailerProductIndex()).getPromoPlans().get(2).getSelectedRtm(),false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"),getRetailer().getRetailerProducts().get(retailer.get().getCurrentRetailerProductIndex()).getPromoPlans().get(3).getSelectedRtm(),false));
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
    public static ObservableList<Parameter<?>> getEmptyParameters(){
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

//    public BigDecimal getSlottingGameTheoryd (int month){
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal confidencePer = ((BigDecimal) params.get(confidencePerIndex).getMonth(month)).divide(new BigDecimal(100.0));
//        BigDecimal slottingInvestment = ((BigDecimal) params.get(slottingInvestmentIndex).getMonth(month));
//        return confidencePer.multiply(slottingInvestment);
//    }
//
//    public BigDecimal getEverydayCost (int month){
//        BigDecimal everydayGPM = getRetailer().getEverydayGPM();
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal everydayRetail = ((BigDecimal) params.get(everydayRetailIndex).getMonth(month));
//        return everydayRetail.multiply(new BigDecimal("1.0").subtract(everydayGPM.divide(new BigDecimal(100.0))));
//    }
//    public BigDecimal getPromoCost (int month, int promo){
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal requiredGPM = new BigDecimal("0.0");
//        BigDecimal promotedRetail = new BigDecimal("0.0");
//        if (promo==1) {
//            requiredGPM = (BigDecimal)params.get(requiredGpm1Index).getMonth(month);
//            promotedRetail = (BigDecimal)params.get(promotedRetail1Index).getMonth(month);
//        } if (promo==2) {
//            requiredGPM = (BigDecimal)params.get(requiredGpm2Index).getMonth(month);
//            promotedRetail = (BigDecimal)params.get(promotedRetail2Index).getMonth(month);
//        }
//        return promotedRetail.multiply((new BigDecimal("1.0").subtract(requiredGPM.divide(new BigDecimal("100.0")))));
//    }
//
//    public int getEverydayRetailWeeks(int month){
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        int durationWeeks1 = (int) params.get(durationWeeks1Index).getMonth(month);
//        int durationWeeks2 = (int) params.get(durationWeeks2Index).getMonth(month);
//        return getWeeksInPeriod(month)- (durationWeeks1)-(durationWeeks2);
//    }
//
//    public BigDecimal getEverydayVolume(int month) {
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal skusInDistribution = (BigDecimal) params.get(skusInDistributionIndex).getMonth(month);
//        BigDecimal weeklyVelocity = getWeeklyVelocity();
//        BigDecimal storeCount = new BigDecimal((int) params.get(storeCountIndex).getMonth(month));
//        BigDecimal everyDayRetailWeeks = new BigDecimal(getEverydayRetailWeeks(month));
//        return skusInDistribution.multiply(storeCount).multiply(weeklyVelocity).multiply(everyDayRetailWeeks);
//    }
    public BigDecimal getWeeklyVelocity(){
        String weeklyUfsw = weeklyVelocityFields.get(getCurrentPromoPlanIndex()).getText();
        BigDecimal weeklyVelocity = new BigDecimal("0.0");
        if (weeklyUfsw!=null){
            weeklyVelocity= new BigDecimal(weeklyUfsw);
        }
        return weeklyVelocity;
    }
//    public BigDecimal getPromoVolume(int month, int promo){
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal skusInDistribution = (BigDecimal) params.get(skusInDistributionIndex).getMonth(month);
//        BigDecimal weeklyVelocity = getWeeklyVelocity();
//        BigDecimal storeCount = new BigDecimal((int) params.get(storeCountIndex).getMonth(month));
//        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
//        BigDecimal durationWeeks = new BigDecimal("0.0");
//        if (promo==1) {
//            volumeLiftMultiple = (BigDecimal)params.get(volumeLiftMultiple1Index).getMonth(month);
//            durationWeeks= new BigDecimal((int)params.get(durationWeeks1Index).getMonth(month));;
//        }if (promo==2) {
//            volumeLiftMultiple = (BigDecimal)params.get(volumeLiftMultiple2Index).getMonth(month);
//            durationWeeks= new BigDecimal((int)params.get(durationWeeks2Index).getMonth(month));;
//        }
//        return skusInDistribution.multiply(weeklyVelocity).multiply(storeCount).multiply(volumeLiftMultiple).multiply(durationWeeks);
//    }
//
//    public BigDecimal getTotalVolume(int month){
//        return getEverydayVolume(month).add(getPromoVolume(month,1).add(getPromoVolume(month,2)));
//    }
//    public BigDecimal getPromoDiscount(int month, int promo){
//        if (getEverydayCost(month).compareTo(new BigDecimal("0.0"))==0 || getPromoCost(month,promo).compareTo(new BigDecimal("0.0"))==0){
//            return new BigDecimal("0.0");
//        } return new BigDecimal("100").multiply((getEverydayCost(month).subtract(getPromoCost(month, promo))).divide(getEverydayCost(month),4, RoundingMode.HALF_UP));
//    }
//    public BigDecimal getRetailerGrossSales(int month){
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal promotedRetail1 = (BigDecimal)params.get(promotedRetail1Index).getMonth(month);
//        BigDecimal promotedRetail2 = (BigDecimal)params.get(promotedRetail2Index).getMonth(month);
//        BigDecimal everydayRetail = (BigDecimal)params.get(everydayRetailIndex).getMonth(month);
//        return everydayRetail.multiply(getEverydayVolume(month))
//                .add(promotedRetail1.multiply(getPromoVolume(month,1)))
//                .add(promotedRetail2.multiply(getPromoVolume(month,2)));
//    }
//    public BigDecimal getRetailerNetCost(int month){
//        return (getEverydayCost(month).multiply(getEverydayVolume(month))).add(getPromoCost(month,1).multiply(getPromoVolume(month,1))).add(getPromoCost(month,2).multiply(getPromoVolume(month,2)));
//    }
//    public BigDecimal getRetailerGrossProfit(int month){
//        return getRetailerGrossSales(month).subtract(getRetailerNetCost(month));
//    }
//    public BigDecimal getManufacturerGrossSalesList(int month){
//        return getTotalVolume(month).multiply(getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct().getUnitListCost()); //new BigDecimal("3.59") HARDCODED LIST FOR NOW
//    }
//    public BigDecimal getManufacturerGrossSalesActual(int month){
//        return getManufacturerGrossSalesList(month).subtract(getFobDiscounts(month));
//    }
//
//    public BigDecimal getFobDiscounts(int month) {// HARDCODED TRUE FOR NOW
//
//        RTMOption selectedRtm = getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
//        if (selectedRtm!=null) {
//            if (selectedRtm.isFob()) {// F.O.B
//                Product product = getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct();
//                return getTotalVolume(month).multiply((product.getUnitListCost()).subtract(product.getUnitFobCost())); // LISt and FOB -- CHANGED
//            }return new BigDecimal("0.0");
//        }
//
//        return null;
//    }
//
//    public BigDecimal getSpoilsFeesTS(int month){
//        return (getRetailer().getSpoilsFees()).divide(new BigDecimal("100")).multiply(getManufacturerGrossSalesActual(month));
//    }
//    public BigDecimal getEverydayAllowanceTS(int month){
//        RTMOption selectedRtm = getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
//        Product product= getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct();
//        if (selectedRtm!=null) {
//            if (selectedRtm.isFob()) {
//                return getTotalVolume(month).multiply((product.getUnitFobCost()).subtract(selectedRtm.getFirstReceiver()));
//            } else {
//                return getTotalVolume(month).multiply((product.getUnitListCost()).subtract(selectedRtm.getFirstReceiver()));
//            }
//        } System.out.println("False values: Please select an RTM- everydayALLOWANcETS");
//        return null;
//    }
//    public BigDecimal getPromoTS(int month, int promo){
//        return getPromoVolume(month,promo).multiply(getEverydayCost(month).subtract(getPromoCost(month,promo)));
//    }
//    public BigDecimal getFixedCostsTS(int month){
//        ObservableList<Parameter<?>> params =pricingPromotionTableOne.getItems();
//        BigDecimal fixedCosts1 = (BigDecimal)params.get(fixedCosts1Index).getMonth(month);;
//        BigDecimal fixedCosts2 = (BigDecimal)params.get(fixedCosts2Index).getMonth(month);
//        return fixedCosts1.add(fixedCosts2);
//    }
//    public BigDecimal getTotalTS (int month){
//        return getEverydayAllowanceTS(month).add(getPromoTS(month,1)).add(getPromoTS(month,2)).add(getFixedCostsTS(month)).add(getSpoilsFeesTS(month));
//    }
//    public BigDecimal getManufacturerNet1Rev(int month){ // CONTAINS BUG IN MAIN EXCEL APP
//        return getManufacturerGrossSalesActual(month).subtract(getTotalTS(month)).add(getFobDiscounts(month));
//    }
//    public BigDecimal getManufacturerFreightCost(int month){
//        RTMOption selectedRTM= getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
//        if (selectedRTM!=null) {
//            return selectedRTM.getFreightOutPerUnit().add(getFobDiscounts(month)); //HARDCODED THE FREIHT OUT PER UNIT
//        } System.out.println("PLEASE SELECT RTM - man freight cost");
//        return null;
//    }
//    public BigDecimal getManufacturerNet2Rev(int month){
//        return getManufacturerNet1Rev(month).subtract(getManufacturerFreightCost(month));
//    }
//    public BigDecimal getManufacturerNet3Rev(int month){
//        return getManufacturerNet2Rev(month).subtract(getSlottingGameTheoryd(month));
//    }
//    public BigDecimal getManufacturerCogs (int month){
//        BigDecimal cogs = getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct().getUnitBlendedCogs();
//        return cogs.multiply(getTotalVolume(month));
//    }
//    public BigDecimal getManufacturerGrossProfit (int month){
//        return getManufacturerNet3Rev(month).subtract(getManufacturerCogs(month)); //2.05 COGS
//    }
//    public BigDecimal getPromoLiftMultiple(int month, int promo){
//        ObservableList<Parameter<?>> params = pricingPromotionTableOne.getItems();
//        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
//        if (promo==1){
//            volumeLiftMultiple= (BigDecimal) params.get(volumeLiftMultiple1Index).getMonth(month);
//        } if (promo==2) {
//            volumeLiftMultiple= (BigDecimal) params.get(volumeLiftMultiple2Index).getMonth(month);
//        }
//        return volumeLiftMultiple;
//    }
//
//    public static int getWeeksInPeriod(int month){
//        if (month == 2 ||month == 3 ||month == 5 || month == 6 || month == 8 || month == 9 || month == 11 || month ==12){
//            return 4;
//        }
//        return 5;
//    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
        RetailerProduct currentRetailerProduct  = retailer.getRetailerProducts().get(retailer.getCurrentRetailerProductIndex());
        promoPlans.set(currentRetailerProduct.getPromoPlans());

        setCurrentPromoPlanIndex(0);
        this.getCurrentPromoPlan().setRetailer(retailer);
        // also update labels in future
        if (getCurrentPromoPlanIndex()>-1) {
            pricingPromotionTableOne.setItems(currentRetailerProduct.getPromoPlans().get(getCurrentPromoPlanIndex()).getParameters());
        }for (int i = 0 ; i<4; i++) {
            weeklyVelocityFields.get(i).setText(getPromoPlans().get(i).getWeeklyPromoUfsw().toString());
            if (getPromoPlans().get(i).getSelectedRtm() != null) {
                rtmBoxes.get(i).valueProperty().setValue(getPromoPlans().get(i).getSelectedRtm());
            }
            if (getPromoPlans().get(i).getCommitted()) {
                commitButtons.get(i).setText("Committed");
            }
            // make it call this
            if (getPromoPlans().get(i).getSelectedRtm()!=null){
                final ComboBox rtmBox = rtmBoxes.get(i);
                final PromoPlan promoPlan= getPromoPlans().get(i);
                System.out.println("selected rtm= "+ getPromoPlans().get(i).getSelectedRtm().toString());
//                rtmBoxes.get(i).valueProperty().setValue(getPromoPlans().get(i).getSelectedRtm());
//                System.out.println("rtm box value= "+rtmBoxes.get(i).getValue());
                rtmBoxes.get(i).setItems(retailer.getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getRtmOptions());
//                rtmBox0.valueProperty().setValue(getPromoPlans().get(i).getSelectedRtm());
//                rtmBoxes.get(i).setPromptText(rtmBoxes.get(i).getConverter().toString(rtmBoxes.get(i).getValue()));
                System.out.println("sselected rtm= "+ rtmBoxes.get(i).getSelectionModel().getSelectedItem().toString());
                rtmBoxes.get(i).setVisible(false);
                rtmBoxes.get(i).setVisible(true);
            }
        }
        updatePromoSummaries();
        updateMonthlyTotalVolumeAndGrossProfit();

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

        RTMPlanningController firstTableController =secondTableLoader.getController();
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
