package com.traderoute;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.Assert;
import org.junit.Test;
import com.traderoute.PricingPromotionController;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PricingPromotionControllerTest {
    private TableView<Parameter> testTableOne;
    private TableColumn<Parameter, Object> januaryColumn;
    private TableColumn<Parameter, Object> februaryColumn;
    private TableColumn<Parameter, Object> marchColumn;
    private TableColumn<Parameter, Object> aprilColumn;
    private TableColumn<Parameter, Object> mayColumn;
    private TableColumn<Parameter, Object> juneColumn;
    private TableColumn<Parameter, Object> julyColumn;
    private TableColumn<Parameter, Object> augustColumn;
    private TableColumn<Parameter, Object> septemberColumn;
    private TableColumn<Parameter, Object> octoberColumn;
    private TableColumn<Parameter, Object> novemberColumn;
    private TableColumn<Parameter, Object> decemberColumn;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testTableOne.getColumns().addAll(januaryColumn, februaryColumn, marchColumn, aprilColumn,mayColumn,juneColumn,julyColumn, augustColumn, septemberColumn, octoberColumn, novemberColumn,decemberColumn);

        ObservableList<Parameter> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("5.0"),new BigDecimal("5.0") ,new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0")));
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
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("5.99"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99")));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("40.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0")));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("2.5"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5")));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("500"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500")));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("3.59"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59")));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("7.7"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7")));

        testTableOne.setItems(parameters);
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
    }

    @Test
    public void testGetSlottingGameTheoryd() {
//        Assert.assertEquals(PricingPromotionController.getSlottingGameTheoryd(), new BigDecimal("3500.0")); //true


    }

    @Test
    public void getEverydayCost() {
    }

    @Test
    public void getPromoCost() {
    }

    @Test
    public void getEverydayRetailWeeks() {
    }

    @Test
    public void getEveryDayVolume() {
    }

    @Test
    public void getWeeksInPeriod() {
    }
}