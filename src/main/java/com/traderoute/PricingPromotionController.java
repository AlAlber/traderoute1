package com.traderoute;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class PricingPromotionController implements Initializable {
    @FXML
    private TableView<Parameter<?>> pricingPromotionTableOne;

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

    private TableRow<Parameter<?>> monthRow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pricingPromotionTableOne.setEditable(true);
        pricingPromotionTableOne.setItems(getParameters());
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
    }

    public ObservableList<Parameter<?>> getParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("5.0"),new BigDecimal("5.0") ,new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0")));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        Parameter confidencePer = new BigDecimalParameter("Confidence %", "%");
        confidencePer.setJuly(new BigDecimal(50.0));
        parameters.add(confidencePer);
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7000.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
        parameters.add(new IntegerParameter("Store Count", "", 158, 158, 158,158,158,158,158,158,158,158,158,158));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("6.49"),new BigDecimal("6.49") ,new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49")));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.89"),new BigDecimal("3.89") ,new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89")));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("0.91"),new BigDecimal("0.91") ,new BigDecimal("0.93"),new BigDecimal("0.95"),new BigDecimal("1.07"),new BigDecimal("1.27"),new BigDecimal("1.46"),new BigDecimal("1.23"),new BigDecimal("0.86"),new BigDecimal("0.80"),new BigDecimal("0.82"),new BigDecimal("0.86")));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("5.99"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99")));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("40.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0")));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4,0,0,0,0,4,4,4,0,0,0,4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("2.5"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5")));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500")));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.59"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59")));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("7.7"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7")));
        parameters.add(new StringParameter("Promotional Commentary", "%", "4 Week TPR","" ,"","","","4 Week TPR","4 Week TPR","4 Week TPR","","","","4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")));
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
        BigDecimal everydayGPM = new BigDecimal("40.0"); //gonna need to get this from the other page
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
        BigDecimal weeklyVelocity = new BigDecimal("0.75"); // need to add textfield for this
        BigDecimal storeCount = new BigDecimal("0.0");
        BigDecimal everyDayRetailWeeks = new BigDecimal("0.0");
        for (Parameter row: pricingPromotionTableOne.getItems()){
            if (row.getName().equals("Skus In Distribution")){
                skusInDistribution = (BigDecimal) row.getMonth(month);
            }
            if (row.getName().equals("Store Count")){
                storeCount = new BigDecimal((int)row.getMonth(month));
            }
        }
        everyDayRetailWeeks = getEverydayRetailWeeks(month);
        return skusInDistribution.multiply(storeCount).multiply(weeklyVelocity).multiply(everyDayRetailWeeks);
    }
    public BigDecimal getPromoVolume(int month, int promo){
        BigDecimal skusInDistribution = new BigDecimal("0.0");
        BigDecimal weeklyVelocity = new BigDecimal("0.75"); // need to add textfield for this
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
        System.out.println(grossSales);
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
        return getTotalVolume(month).multiply(new BigDecimal("3.59")); // HARDCODED LIST FOR NOW
    }
    public BigDecimal getManufacturerGrossSalesActual(int month){
        return getManufacturerGrossSalesList(month).subtract(getFobDiscounts(month));
    }

    public BigDecimal getFobDiscounts(int month) {// HARDCODED TRUE FOR NOW
        if (isFob(month)){
            return  getTotalVolume(month).multiply((new BigDecimal("3.59")).subtract(new BigDecimal("3.30"))); // LISt and FOB
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


    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        App.setRoot("secondTable");
    }
    @FXML
    private void switchToAssortment(ActionEvent event) throws IOException {
        App.setRoot("assortment");
    }
}
