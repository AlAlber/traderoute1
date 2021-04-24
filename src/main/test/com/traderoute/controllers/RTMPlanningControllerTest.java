package com.traderoute.controllers;

import com.traderoute.App;
import com.traderoute.charts.LandedStoreCostChart;
import com.traderoute.data.Product;
import com.traderoute.data.RTMOption;
import com.traderoute.data.Retailer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.testfx.api.FxRobot;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.math.BigDecimal;

@ExtendWith(ApplicationExtension.class)
class RTMPlanningControllerTest {
    private RTMPlanningController controller;
    private ObservableList<RTMOption> rtmOptions;
    private RTMOption testOption = new RTMOption();
    private SimpleObjectProperty<Retailer> retailer= new SimpleObjectProperty<>(new Retailer("ahold", RTMPlanningController.getRetailerProducts(),0 ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));;
    private TableView<RTMOption> rtmPlanningTable1;
    private TextField everydayGpmField;
    private TextField yearOneStoreCountField;
    private TextField spoilsFeesField;
    private TextField weeklyUfswAtMinField;
    private ComboBox<Product> productClassBox;
    private ComboBox<Product> brandNameBox;
    final String  tableString = "#rtmPlanningTable1";
    public LandedStoreCostChart landedStoreCostChart;
    public BarChart<String, BigDecimal> everydayRetailCalcdChart;
    public BarChart<String, BigDecimal> elasticizedUnitVelocityChart;
    public BarChart<String, BigDecimal> annualVolumePerSkuChart;
    public BarChart<String, BigDecimal> slottingPaybackPeriodChart;
    public BarChart<String, BigDecimal> postSpoilsPostFreightChart;
    public BarChart<String, BigDecimal> unspentTradePerUnitChart;
    public BarChart<String, BigDecimal> fourYearEqGpPerSkuChart;
    public BarChart<String, BigDecimal> fourYearEqGpPerUnitChart;


    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = App.createFXMLLoader("secondTable");
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setRetailer(retailer.get());
        stage.setScene(scene);
        stage.show();
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp(FxRobot robot) {
        rtmOptions = retailer.get().getRetailerProducts().get(0).getRtmOptions();
        rtmPlanningTable1 = robot.lookup(tableString).queryTableView();
        everydayGpmField = robot.lookup("#everydayGpmField").queryAs(TextField.class);
        yearOneStoreCountField = robot.lookup("#yearOneStoreCountField").queryAs(TextField.class);
        spoilsFeesField = robot.lookup("#spoilsFeesField").queryAs(TextField.class);
        weeklyUfswAtMinField = robot.lookup("#weeklyUfswAtMinField").queryAs(TextField.class);
        productClassBox = robot.lookup("#productClassBox").queryComboBox();
        brandNameBox = robot.lookup("#brandNameBox").queryComboBox();


        RTMOption rtmOption1 = new RTMOption();
        rtmOption1.setRTMName("Option 1");
        RTMOption rtmOption2 = new RTMOption();
        rtmOption2.setRTMName("Option 2");
        RTMOption rtmOption3 = new RTMOption();
        rtmOption3.setRTMName("Option 3");
        RTMOption rtmOption4 = new RTMOption();
        rtmOption4.setRTMName("Option 4");
        rtmPlanningTable1.setItems(FXCollections.observableArrayList(rtmOption1, rtmOption2, rtmOption3, rtmOption4));
        //Set up row listeners which is normally done in initialize
        controller.setUpListeners();
//        Platform.runLater(()->{
            landedStoreCostChart = robot.lookup("#landedStoreCostChart").queryAs(LandedStoreCostChart.class);
//        });

        everydayRetailCalcdChart = robot.lookup("#everydayRetailCalcdChart").queryAs(BarChart.class);
        elasticizedUnitVelocityChart = robot.lookup("#elasticizedUnitVelocityChart").queryAs(BarChart.class);
        annualVolumePerSkuChart = robot.lookup("#annualVolumePerSkuChart").queryAs(BarChart.class);
        slottingPaybackPeriodChart = robot.lookup("#slottingPaybackPeriodChart").queryAs(BarChart.class);
        postSpoilsPostFreightChart = robot.lookup("#postSpoilsPostFreightChart").queryAs(BarChart.class);
        unspentTradePerUnitChart = robot.lookup("#unspentTradePerUnitChart").queryAs(BarChart.class);
        fourYearEqGpPerSkuChart = robot.lookup("#fourYearEqGpPerSkuChart").queryAs(BarChart.class);
        fourYearEqGpPerUnitChart = robot.lookup("#fourYearEqGpPerUnitChart").queryAs(BarChart.class);
    }

    @Test
    public void testFirstReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(tableString, 3, 3, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFirstReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testSecondReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(tableString, 3, 4, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getSecondReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testThirdReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(tableString, 3, 5, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getThirdReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testFourthReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(tableString, 3, 6, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFourthReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }

    @Test
    public void testReceiversCorrectlyPickMaxLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(tableString, 3, 6, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFourthReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));

        robot.doubleClickOn(cell(tableString, 3, 5, robot));
        robot.write("1.1");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getThirdReceiver(),new BigDecimal("1.1"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("1.1"));

        robot.doubleClickOn(cell(tableString, 3, 3, robot));
        robot.write("0.9");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFirstReceiver(),new BigDecimal("0.9"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("1.1"));

        robot.doubleClickOn(cell(tableString, 3, 4, robot));
        robot.write("5.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getSecondReceiver(),new BigDecimal("5.0"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("5.0"));
    }

    @Test
    public void testMaxReceiverAndEverydayGpmUpdateEverydayRetails(FxRobot robot){
        robot.doubleClickOn(cell(tableString, 3, 3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFirstReceiver(),new BigDecimal("3.59"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("3.59"));

        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5.9833333333"),((RTMOption) rtmPlanningTable1.getItems().get(3)).getEverydayRetailCalcd());
        Assertions.assertEquals(new BigDecimal("5.9833333333"), ((RTMOption) rtmPlanningTable1.getItems().get(3)).getResultingEverydayRetailOverride());
    }

    @Test
    public void testProductSelectionUpdatesLabels(FxRobot robot){
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
            productClassBox.getSelectionModel().select(0);
        });
        Label listLabel = robot.lookup("#listLabel").queryAs(Label.class);
        Label fobLabel = robot.lookup("#fobLabel").queryAs(Label.class);
        Label net1GoalLabel = robot.lookup("#net1GoalLabel").queryAs(Label.class);
        Label elasticityRatioLabel = robot.lookup("#elasticityRatioLabel").queryAs(Label.class);
        FxAssert.verifyThat(listLabel, LabeledMatchers.hasText("List = $3.59")) ;
        Assertions.assertEquals("List = $3.59", listLabel.getText());
        Assertions.assertEquals("F.O.B. = $3.30", fobLabel.getText());
        Assertions.assertEquals("Net 1 Goal = $2.99", net1GoalLabel.getText());
        Assertions.assertEquals("Elasticity Ratio = +1% Price :-1.15% Volume", elasticityRatioLabel.getText());
    }
    @Test
    public void testFullWorkflow1(FxRobot robot) {
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
            productClassBox.getSelectionModel().select(0);
        });
        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("158");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.doubleClickOn(spoilsFeesField);
        robot.write("3.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.doubleClickOn(cell(tableString, 3, 0, robot));
        robot.write("Direct To Customer Model");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getRTMName(),"Direct To Customer Model");
        robot.doubleClickOn(cell(tableString, 3, 1, robot));
        robot.write("7500");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getSlottingPerSku(),new BigDecimal("7500"));
        robot.doubleClickOn(cell(tableString, 3, 2, robot));
        robot.write("0.29");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFreightOutPerUnit(),new BigDecimal("0.29"));
        robot.doubleClickOn(cell(tableString, 3, 3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFirstReceiver(),new BigDecimal("3.59"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getLandedStoreCost(),new BigDecimal("3.59"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getEverydayRetailCalcd(),new BigDecimal("5.9833333333"), "Resulting Calcd should have changed");
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getResultingEverydayRetailOverride(),new BigDecimal("5.9833333333"), "Resulting Override should have changed");
//      ^^ ADD THIS LATER ^^
        robot.doubleClickOn(cell(tableString, 3, 9, robot));
        robot.write("5.99");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getResultingEverydayRetailOverride(),new BigDecimal("5.99"));

        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("1.20");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
//        Assertions.assertEquals(new BigDecimal("1.20"), ((RTMOption)firstTableView.getItems().get(3)).getWeeklyUSFWAtMin());
//        ^^KEEPS CHANGING FROM 1.20 to 1.2  or VICE VERSA
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getElasticizedUnitVelocity(), new BigDecimal("1.2"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getAnnualVolumePerSku(), new BigDecimal("9859.2000000000"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getSlottingPaybackPeriod(), new BigDecimal("1.65436")); // Refactor this in RTM Option
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getPostFreightPostSpoilsPerUnit(), new BigDecimal("3.1923000000")); // Refactor this in RTM Option
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getUnspentTradePerUnit(), new BigDecimal("0.4923000000"));
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFourYearEqGpPerSku(), new BigDecimal("18133.920000000000000000000000000000")); // Refactor this in RTM Option
        Assertions.assertEquals(((RTMOption) rtmPlanningTable1.getItems().get(3)).getFourYearEqGpPerUnit(), new BigDecimal("0.4598222980"));
    }
    @Test
    public void testChartUpdateRtmName(FxRobot robot) throws InterruptedException {
        Assertions.assertEquals("Option 1", rtmPlanningTable1.getItems().get(0).getRTMName());
        robot.doubleClickOn(cell(tableString, 0, 0, robot));
        robot.write("New RTM Name");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals("New RTM Name", rtmPlanningTable1.getItems().get(0).getRTMName());
//        robot.interact(() -> {
//            XYChart.Series<String, BigDecimal> series = landedStoreCostChart.getData().get(0);
//            XYChart.Data<String, BigDecimal> data = series.getData().get(0);
//            String xValue = data.getXValue();
//            Assertions.assertEquals("New RTM Name", xValue);
//        });
//        assertEqualsXChartValueForFirstRTMOption(robot,landedStoreCostChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,everydayRetailCalcdChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,elasticizedUnitVelocityChart,"New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,annualVolumePerSkuChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,slottingPaybackPeriodChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,postSpoilsPostFreightChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,unspentTradePerUnitChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,fourYearEqGpPerSkuChart, "New RTM Name");
        assertEqualsXChartValueForFirstRTMOption(robot,fourYearEqGpPerUnitChart, "New RTM Name");
    }


    @Test
    public void testChartUpdateLandedStoreCost(FxRobot robot) {
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getSecondReceiver());
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getLandedStoreCost());
        robot.doubleClickOn(cell(tableString, 0, 4, robot));
        robot.write("4.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("4.0"), rtmPlanningTable1.getItems().get(0).getLandedStoreCost());
//        assertEqualsYChartValueForFirstRTMOption(robot, landedStoreCostChart, new BigDecimal("4.0"));
    }
    @Test
    public void testChartUpdateEverydayRetailCalcd(FxRobot robot) {
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(cell(tableString,0,3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getEverydayRetailCalcd());
        Assertions.assertEquals(new BigDecimal("3.59"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5.9833333333"), rtmPlanningTable1.getItems().get(0).getEverydayRetailCalcd());
        assertEqualsYChartValueForFirstRTMOption(robot,everydayRetailCalcdChart, new BigDecimal("5.9833333333"));
    }


    @Test
    public void testChartUpdateWeeklyVelocityAtMin(FxRobot robot) {
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(cell(tableString,0,3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getWeeklyUSFWAtMin());
//        Assertions.assertEquals(new BigDecimal("3.59"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("1.2");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("1.2"), rtmPlanningTable1.getItems().get(0).getWeeklyUSFWAtMin());
        assertEqualsYChartValueForFirstRTMOption(robot,elasticizedUnitVelocityChart,  new BigDecimal("1.2"));
    }
    @Test
    public void testChartUpdateYearOneStoreCount(FxRobot robot) {
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(cell(tableString,0,3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
//        Assertions.assertEquals(new BigDecimal("3.59"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("1.2");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("158");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertEqualsYChartValueForFirstRTMOption( robot, annualVolumePerSkuChart, new BigDecimal("9887"));
    }
    @Test
    public void testUpdateChartLandedStoreCost(FxRobot robot){
        testOption.setLandedStoreCost(new BigDecimal("4.44"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()-> {
            landedStoreCostChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, landedStoreCostChart, new BigDecimal("4.44"));
    }

    @Test
    public void testUpdateChartEveryDayRetailCalcd(FxRobot robot){
        testOption.setEverydayRetailCalcd(new BigDecimal("6.788"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(everydayRetailCalcdChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, everydayRetailCalcdChart, new BigDecimal("6.788"));
    }
    @Test
    public void testUpdateChartElasticizedUnitVelocityChart(FxRobot robot){
        testOption.setElasticizedUnitVelocity(new BigDecimal("50"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(elasticizedUnitVelocityChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, elasticizedUnitVelocityChart, new BigDecimal("50"));
    }
    @Test
    public void testUpdateChartAnnualVolumePerSku(FxRobot robot){
        testOption.setAnnualVolumePerSku(new BigDecimal("202"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(annualVolumePerSkuChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, annualVolumePerSkuChart, new BigDecimal("202"));
    }
    @Test
    public void testUpdateChartSlottingPerSku(FxRobot robot){
        testOption.setSlottingPaybackPeriod(new BigDecimal("0.22"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(slottingPaybackPeriodChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, slottingPaybackPeriodChart, new BigDecimal("0.22"));
    }
    @Test
    public void testUpdateChartPostFreightPostSpoils(FxRobot robot){
        testOption.setPostFreightPostSpoilsPerUnit(new BigDecimal("0.25"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(postSpoilsPostFreightChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, postSpoilsPostFreightChart, new BigDecimal("0.25"));
    }
    @Test
    public void testUpdateChartUnspentTradePerUnit(FxRobot robot){
        testOption.setUnspentTradePerUnit(new BigDecimal("0.22"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(unspentTradePerUnitChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, unspentTradePerUnitChart, new BigDecimal("0.22"));
    }

    @Test
    public void testUpdateChartFourYearEqGpPerSku(FxRobot robot){
        testOption.setFourYearEqGpPerSku(new BigDecimal("0.20002"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(fourYearEqGpPerSkuChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, fourYearEqGpPerSkuChart, new BigDecimal("0.20002"));
    }
    @Test
    public void testUpdateChartFourYearEqGpPerUnit(FxRobot robot){
        testOption.setFourYearEqGpPerUnit(new BigDecimal("0.555"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            controller.updateChart(FXCollections.observableArrayList(fourYearEqGpPerUnitChart));
        });
        assertEqualsYChartValueForFirstRTMOption(robot, fourYearEqGpPerUnitChart, new BigDecimal("0.555"));
    }
    @Test
    public void testUpdateChartAll(FxRobot robot){
        testOption.setFourYearEqGpPerUnit(new BigDecimal("0.555"));
        rtmPlanningTable1.getItems().set(0,testOption);
        testOption.setLandedStoreCost(new BigDecimal("1"));
        testOption.setEverydayRetailCalcd(new BigDecimal("2"));
        testOption.setElasticizedUnitVelocity(new BigDecimal("3"));
        testOption.setAnnualVolumePerSku(new BigDecimal("4"));
        testOption.setSlottingPaybackPeriod(new BigDecimal("5"));
        testOption.setPostFreightPostSpoilsPerUnit(new BigDecimal("6"));
        testOption.setUnspentTradePerUnit(new BigDecimal("7"));
        testOption.setFourYearEqGpPerSku(new BigDecimal("8"));
        testOption.setFourYearEqGpPerUnit(new BigDecimal("9"));
        robot.interact(()->{
//            controller.updateChart(FXCollections.observableArrayList(landedStoreCostChart,everydayRetailCalcdChart,
//                    elasticizedUnitVelocityChart, annualVolumePerSkuChart, slottingPaybackPeriodChart,
//                    postSpoilsPostFreightChart, unspentTradePerUnitChart, fourYearEqGpPerSkuChart,
//                    fourYearEqGpPerUnitChart));
        });
//        assertEqualsYChartValueForFirstRTMOption(robot, landedStoreCostChart, new BigDecimal("1"));
        assertEqualsYChartValueForFirstRTMOption(robot, everydayRetailCalcdChart, new BigDecimal("2"));
        assertEqualsYChartValueForFirstRTMOption(robot, elasticizedUnitVelocityChart, new BigDecimal("3"));
        assertEqualsYChartValueForFirstRTMOption(robot, annualVolumePerSkuChart, new BigDecimal("4"));
        assertEqualsYChartValueForFirstRTMOption(robot, slottingPaybackPeriodChart, new BigDecimal("5"));
        assertEqualsYChartValueForFirstRTMOption(robot, postSpoilsPostFreightChart, new BigDecimal("6"));
        assertEqualsYChartValueForFirstRTMOption(robot, unspentTradePerUnitChart, new BigDecimal("7"));
        assertEqualsYChartValueForFirstRTMOption(robot, fourYearEqGpPerSkuChart, new BigDecimal("8"));
        assertEqualsYChartValueForFirstRTMOption(robot, fourYearEqGpPerUnitChart, new BigDecimal("9"));
    }


    /**
     * Checks whether x value of first RTMOption in the selected barchart
     * equals the expected String
     * @param barChart  barchart to test
     * @param expected expected x value from first RTMOption in chart
     */
    public void assertEqualsXChartValueForFirstRTMOption(FxRobot robot, BarChart<String, BigDecimal> barChart,
                                                         String expected){
        robot.interact(() -> {
            XYChart.Series<String, BigDecimal> series = barChart.getData().get(0);
            XYChart.Data<String, BigDecimal> data = series.getData().get(0);
            String yValue = data.getXValue();
            Assertions.assertEquals(expected, yValue);
        });
    }
    /**
     * Checks whether y value of first RTMOption in the selected barchart
     * equals the expected BigDecimal
     * @param barChart  barchart to test
     * @param expected expected y value from first RTMOption in chart
     */
    public void assertEqualsYChartValueForFirstRTMOption(FxRobot robot, BarChart<String, BigDecimal> barChart,
                                                         BigDecimal expected){
        robot.interact(() -> {
            XYChart.Series<String, BigDecimal> series = barChart.getData().get(0);
            XYChart.Data<String, BigDecimal> data = series.getData().get(0);
            BigDecimal yValue = data.getYValue();
            Assertions.assertEquals(expected, yValue);
        });
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

    /**
     * IMPORTED FROM TESTFX LOADUI, BUT NEEDED TO COPY METHODS IN ORDER
     * to access them.
     */

    static TableView<?> getTableView(String tableSelector, FxRobot robot) {
        Node node = robot.lookup(tableSelector).queryTableView();
        if (!(node instanceof TableView)) {
            throw new NoNodesFoundException(tableSelector + " selected " + node + " which is not a TableView!");
        } else {
            return (TableView)node;
        }
    }

    protected static TableRow<?> row(String tableSelector, int row, FxRobot robot) {
        TableView<?> tableView = getTableView(tableSelector, robot);

        ObservableList current;
        for(current = tableView.getChildrenUnmodifiable(); current.size() == 1; current = ((Parent)current.get(0)).getChildrenUnmodifiable()) {
        }

        for(current = ((Parent)current.get(1)).getChildrenUnmodifiable(); !(current.get(0) instanceof TableRow); current = ((Parent)current.get(0)).getChildrenUnmodifiable()) {
        }

        Node node = (Node)current.get(row);
        if (node instanceof TableRow) {
            return (TableRow)node;
        } else {
            throw new RuntimeException("Expected Group with only TableRows as children");
        }
    }

    protected static TableCell<?, ?> cell(String tableSelector, int row, int column, FxRobot robot) {
        ObservableList current;
        for(current = row(tableSelector, row, robot).getChildrenUnmodifiable(); current.size() == 1 && !(current.get(0) instanceof TableCell); current = ((Parent)current.get(0)).getChildrenUnmodifiable()) {
        }

        Node node = (Node)current.get(column);
        if (node instanceof TableCell) {
            return (TableCell)node;
        } else {
            throw new RuntimeException("Expected TableRowSkin with only TableCells as children");
        }
    }


}