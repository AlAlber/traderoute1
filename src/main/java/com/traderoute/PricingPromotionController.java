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
import javafx.scene.control.*;
import javafx.util.StringConverter;

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

    private SimpleIntegerProperty currentPromoPlanIndex;
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
        retailerTable0.setItems(getSummaryTable2());


//        firstRtmBox.setItems(getRetailer().getRetailerProducts().get(0).getRtmOptions());

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
        toplineValueColumn0.setCellValueFactory(cellData-> cellData.getValue().summaryValueProperty());

        retailerDescriptionColumn0.setCellValueFactory(cellData -> cellData.getValue().summaryTypeProperty());
        retailerValueColumn0.setCellValueFactory(cellData -> cellData.getValue().summaryValueProperty());

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
        toplineValueColumn0.setCellFactory(tc-> new CustomNonEditCell<>("$",""));

        // Retailer Table
        toplineDescriptionColumn0.setCellFactory(tc-> new CustomTextCell<>());
        toplineValueColumn0.setCellFactory(tc-> new CustomNonEditCell<>("$",""));

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


        rtmBox0.setConverter(new StringConverter<RTMOption>() {
            @Override
            public String toString(RTMOption rtmOption) {
                if (rtmOption!=null) {
                    return rtmOption.getRTMName();
                }
                return null;
            }
            @Override
            public RTMOption fromString(String s) {
                return null;
            }
        });

        weeklyVelocityField0.setTextFormatter(new TextFormatter<Double>(firstTableController.getDoubleInputConverter(), 0.0, firstTableController.getDoubleInputFilter()));

        retailer = new SimpleObjectProperty<>();
        retailer.addListener(new ChangeListener<Retailer>() {
            @Override
            public void changed(ObservableValue<? extends Retailer> observableValue, Retailer oldRetailer, Retailer newRetailer) {
                updateTable1(1, true, 0);
                updateTable1(1, true, 1);
                updateTable1(1, true, 2);

                updateTable1(1, false, 0);
                updateTable1(1, false, 1);
                updateTable1(1, false, 2);

                BigDecimal plannedNet1Rate = getSumValue(1,true).divide(getSumValue(2, true),2 , RoundingMode.HALF_UP);
                plannedNet1RateLabel0.setText("$"+ plannedNet1Rate.toString());
                BigDecimal goalDifference = new BigDecimal("0.0");

                goalDifference = plannedNet1Rate.subtract(newRetailer.getCurrentRetailerProduct().getProduct().getUnitNet1Goal());

                goalLabel0.setText("$"+goalDifference.toString()+ " to goal");
                retailer.get().currentProductProperty().addListener(new ChangeListener<RetailerProduct>() {
                    @Override
                    public void changed(ObservableValue<? extends RetailerProduct> observableValue, RetailerProduct oldRetailer, RetailerProduct newRetailer) {
                        updateTable1(1, true, 0);
                        updateTable1(1, true, 1);
                        updateTable1(1, true, 2);

                        updateTable1(1, false, 0);
                        updateTable1(1, false, 1);
                        updateTable1(1, false, 2);

                        BigDecimal plannedNet1Rate = getSumValue(1,true).divide(getSumValue(2, true),2 , RoundingMode.HALF_UP);
                        plannedNet1RateLabel0.setText("$"+ plannedNet1Rate.toString());
                        BigDecimal goalDifference = new BigDecimal("0.0");

                        goalDifference = plannedNet1Rate.subtract(getRetailer().getCurrentRetailerProduct().getProduct().getUnitNet1Goal());

                        goalLabel0.setText("$"+goalDifference.toString()+ " to goal");
                    }
                });
            }
        });
        this.currentPromoPlanIndex = new SimpleIntegerProperty(0);
        currentPromoPlanIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPromoPlan, Number newPromoPlan) {

            }
        });



//        updateToplineSumValue(1,0);
//
//        updateToplineSumValue(1,1);
//
//        updateToplineSumValue(1, 2);

//        BigDecimal netSalesTopline = new BigDecimal("0.0");
//        for (int i=1; i<=12; i++){
//            netSalesTopline = netSalesTopline.add(getManufacturerNet1Rev(i));
//        }
//        firstYearToplineTable.getItems().get(1).setSummaryValue(netSalesTopline);
//
//        BigDecimal totalVolumeTopline = new BigDecimal("0.0");
//        for (int i=1; i<=12; i++){
//            totalVolumeTopline = totalVolumeTopline.add(getTotalVolume(i));
//        }
//        firstYearToplineTable.getItems().get(2).setSummaryValue(totalVolumeTopline);

        // Retailer Table
//        BigDecimal grossSalesRetailer = new BigDecimal("0.0");
//        for (int i=1; i<=12; i++){
//            grossSalesRetailer = grossSalesRetailer.add(getRetailerGrossSales(i));
//        }
//        firstYearRetailerTable.getItems().get(0).setSummaryValue(grossSalesRetailer.setScale(2,RoundingMode.HALF_UP));
//
//        BigDecimal grossProfitRetailer = new BigDecimal("0.0");
//        for (int i=1; i<=12; i++){
//            grossProfitRetailer = grossProfitRetailer.add(getRetailerGrossProfit(i));
//        }
//        firstYearRetailerTable.getItems().get(1).setSummaryValue(grossProfitRetailer.divide(grossSalesRetailer,2,RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
//
//        BigDecimal avgRetail = new BigDecimal("0.0");
//        firstYearRetailerTable.getItems().get(2).setSummaryValue(grossSalesRetailer.divide(totalVolumeTopline,2,RoundingMode.HALF_UP));

//        retailer.addListener(new ChangeListener<Retailer>() {
//            private boolean changing;
//            @Override
//            public void changed(ObservableValue<? extends Retailer> observable, Retailer oldValue, Retailer newValue) {
//                if (!changing) {
//                    try {
//                        changing = true;
//                        updateResultingEverydayRetailCald();
//                    } finally {
//                        changing = false;
//                    }
//                }
//            }
//        });

    }

    public void clickEditButton (ActionEvent event){
        Button button = (Button) event.getSource();
        String buttonId = ((Button) event.getSource()).getId();
        System.out.println(((Button) event.getSource()).getId());
        int year = -1;
        if (buttonId.equals("editButton0")){
            year=0;
        } else if (buttonId.equals("editButton1")){
            year=1;
        } else if (buttonId.equals("editButton2")) {
            year=2;
        } else if (buttonId.equals("editButton3")){
            year = 3;
        }else {
            System.out.print("not an accepted edit button=" + buttonId );
        }
        promoPlans.get().get(currentPromoPlanIndex.get()).getEditButton().getParent().setStyle("-fx-background-color: #3b381a");
        button.getParent().setStyle("-fx-background-color: transparent");
        // Save table items
        getPromoPlans().get(getCurrentPromoPlanIndex()).setParameters(pricingPromotionTableOne.getItems());
        //Change promo year
        setCurrentPromoPlanIndex(year);
        // Set new table items
        pricingPromotionTableOne.setItems(getPromoPlans().get(getCurrentPromoPlanIndex()).getParameters());

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
    public void updatePromoSummaries(int selectedYear){
        for (int i= 0; i<3; i++) {
            updateTable1(1, true,i );
            updateTable1(1, false,i );
        }
    }

    // if boolean topline is true, then its the topline table of a year otherwise its retailer
    public void updateTable1(int selectedYear, boolean topline, int value) {
        TableView<Summary> table = new TableView<>();
        switch (selectedYear) {
            case 1:
                if (topline){
                    table = toplineTable0;
                } else{
                    table = retailerTable0;
                }
                break;
            case 2:
                if (topline){
//                    table = secondYearToplineTable;
                } else{
//                    table = secondYearRetailTable;
                }
                break;
            case 3:
                if (topline){
//                    table = thirdYearToplineTable;
                } else{
//                    table = thirdYearRetailTable;
                }
                break;
            case 4:
                if (topline){
//                    table = thirdYearToplineTable;
                } else{
//                    table = thirdYearRetailTable;
                }
                break;
        }
        table.getItems().get(value).setSummaryValue(getSumValue(value,topline));
    }

    public void updateTable(int selectedYear, int tableNumber, int value) {
        TableView<Summary> table = new TableView<>();
        switch (tableNumber) {
            case 1:
                table = toplineTable0;
                break;
            case 2:
//                table = firstYearRetailTable;
                break;
            case 3:
//                table = secondYearToplineTable;
                break;
            case 4:
//                table = secondYearRetailTable;
                break;
            case 5:
//                table = thirdYearToplineTable;
                break;
            case 6:
//                table = thirdYearRetailTable;
                break;
            case 7:
//                table = fourthYearToplineTable;
                break;
            case 8:
//                table = fourthYearToplineTable;
                break;
        }
//        table.getItems().get(value).setSummaryValue(getSumValue(value));
    }

    // if value 0, then its the first value of the summary table,1 is second of topline
    // year determines which level
    public void updateToplineSumValue(int year, int value ){
        BigDecimal summedValue= new BigDecimal("0.0");
        for (int i=1; i<=12; i++){
            switch(value){
                case 0:
                    summedValue = summedValue.add(getManufacturerGrossSalesActual(i));
                    break;
                case 1:
                    summedValue = summedValue.add(getManufacturerNet1Rev(i));
                    break;
                case 2:
                    summedValue = summedValue.add(getTotalVolume(i));
                    break;
            }
        }
        TableView<Summary> toplineTable= new TableView<>();
        switch(year){
            case 1:
                toplineTable = toplineTable0;
                break;
            case 2:
//                toplineTable = secondYearToplineTable;
                break;
            case 3:
//                toplineTable = thirdYearToplineTable;
                break;
            case 4:
//                toplineTable = fourthYearToplineTable;
        }
        toplineTable.getItems().get(value).setSummaryValue(summedValue);
    }



    public ComboBox<RTMOption> getRtmBox0() {
        return rtmBox0;
    }
    public void changeWeeklyVelocityEvent(ActionEvent event) {
        // Set Current promoPlan
        updatePromoSummaries(1);
    }
    public BigDecimal getWeeklyVelocity(){
        if (weeklyVelocityField0.getText() == null) {
            return new BigDecimal("0.0");
        }
        return new BigDecimal(weeklyVelocityField0.getText());
    }

    public void changeFirstRtmBoxEvent(ActionEvent event) {
        RTMOption selectedRtmOption = rtmBox0.getSelectionModel().getSelectedItem();
        everydayLabel0.setText("$" + selectedRtmOption.getResultingEverydayRetailOverride() + " Everyday");
        costLabel0.setText("$" + selectedRtmOption.getLandedStoreCost().setScale(2,RoundingMode.HALF_UP)+ " Cost");
        BigDecimal gpm = new BigDecimal("0.0");
        if (!selectedRtmOption.getLandedStoreCost().equals(new BigDecimal("0.0"))) {
            gpm = (selectedRtmOption.getResultingEverydayRetailOverride().subtract(selectedRtmOption.getLandedStoreCost())).divide(selectedRtmOption.getResultingEverydayRetailOverride(), 2, RoundingMode.HALF_UP);
        }
        gpmLabel0.setText(gpm.multiply(new BigDecimal("100"))+ "% GPM");
        setFirstSelectedRtmOption(selectedRtmOption);
        System.out.println("Selected RTM Changed:"+ getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm());
        updatePromoSummaries(1);
    }

    private ObservableList getSummaryTable() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales $", new BigDecimal("0.0")));
        summaries.add(new Summary("Net Sales $", new BigDecimal("0.0")));
        summaries.add(new Summary("Total Units", new BigDecimal("0.0")));
        return summaries;
    }
    private ObservableList getSummaryTable2() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales $", new BigDecimal("0.0")));
        summaries.add(new Summary("GPM %", new BigDecimal("0.0")));
        summaries.add(new Summary("Avg. Retail", new BigDecimal("0.0")));
        return summaries;
    }

    //    public static FXMLLoader getFxmlLoader(String fxml) throws IOException{
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml+".fxml"));
//        return fxmlLoader;
//    }
    public void hideTable(ActionEvent event) {
        pricingPromotionTableOne.setVisible(false);
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
        updatePromoSummaries(1);
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
        promoPlans.add(new PromoPlan(getParameters(),getSummaryTable(), getSummaryTable2(),new BigDecimal("0.75"),firstTableController.getRTMOptions().get(0), false, 0, rtmBox0));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, 1 , rtmBox1));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, 2 , rtmBox2));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getSummaryTable(), getSummaryTable2(), new BigDecimal("0.0"), firstTableController.getRTMOptions().get(0),false, 3, rtmBox3 ));
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
        BigDecimal everydayGPM = getRetailer().getEverydayGPM(); //gonna need to get this from the other page -- FIXED
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
        BigDecimal weeklyVelocity = new BigDecimal("0.75"); // need to add textfield for this -- changed this
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
        BigDecimal weeklyVelocity = new BigDecimal("0.75"); // need to add textfield for this --changed
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
        if (isFob(month)){ // promoplan1.get().getSelectedRTM.isFob()
            return  getTotalVolume(month).multiply((getRetailer().getCurrentRetailerProduct().getProduct().getUnitListCost()).subtract(getRetailer().getCurrentRetailerProduct().getProduct().getUnitFobCost())); // LISt and FOB -- CHANGED
        }
        return new BigDecimal("0.0");
    }
    public boolean isFob(int month){  //HARDCODED IS FOB CHECK WHERE USED
        return true;
    }

    public BigDecimal getSpoilsFeesTS(int month){
        return (new BigDecimal("3.0")).divide(new BigDecimal("100")).multiply(getManufacturerGrossSalesActual(month)); // SPOILS FEES FROM PREVIOUS PAGE
    }
    public BigDecimal getEverydayAllowanceTS(int month){
        //HARDCODED FOR NOW
        if (isFob(month)){                                  // F.O.B                              //First Receiver
            return getTotalVolume(month).multiply((new BigDecimal("3.30")).subtract(new BigDecimal("3.07")));
        }
        else{
            return getTotalVolume(month).multiply((new BigDecimal("3.59")).subtract(new BigDecimal("3.07")));
        }
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
        return new BigDecimal("0.0").add(getFobDiscounts(month)); //HARDCODED THE FREIHT OUT PER UNIT
    }
    public BigDecimal getManufacturerNet2Rev(int month){
        return getManufacturerNet1Rev(month).subtract(getManufacturerFreightCost(month));
    }
    public BigDecimal getManufacturerNet3Rev(int month){
        return getManufacturerNet2Rev(month).subtract(getSlottingGameTheoryd(month));
    }
    public BigDecimal getManufacturerCogs (int month){
        return new BigDecimal("2.05").multiply(getTotalVolume(month)); //2.05 COGS
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
