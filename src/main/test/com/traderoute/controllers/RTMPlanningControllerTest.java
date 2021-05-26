package com.traderoute.controllers;

import com.traderoute.*;
import com.traderoute.charts.*;
import com.traderoute.data.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(ApplicationExtension.class)
class RTMPlanningControllerTest extends TestBaseClass{
    private RTMPlanningController controller;
    private ObservableList<RTMOption> rtmOptions;
    private SimpleObjectProperty<Retailer> retailer= new SimpleObjectProperty<>(new Retailer("ahold", MenuController.getRetailerProducts(),0 ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));
    private RetailerProduct currentRetailerProduct = retailer.get().getRetailerProducts().get(0);
    private RTMOption testOption= new RTMOptionBuilder().retailerProduct(currentRetailerProduct).buildRtmOption();
    private TableView<RTMOption> rtmPlanningTable1;
    private TableView<RTMOption> rtmPlanningTable2;
    private BigDecimalTextField everydayGpmField;
    private IntegerTextField yearOneStoreCountField;
    private BigDecimalTextField spoilsFeesField;
    private BigDecimalTextField weeklyUfswAtMinField;
    private ProductClassComboBox productClassBox;
    private BrandNameComboBox brandNameBox;
    final String table1String = "#rtmPlanningTable1";
    final String table2String = "#rtmPlanningTable2";
    public LandedStoreCostChart landedStoreCostChart;
    public EverydayRetailCalcdChart everydayRetailCalcdChart;
    public ElasticizedUnitVelocityChart elasticizedUnitVelocityChart;
    public AnnualVolumePerSkuChart annualVolumePerSkuChart;
    public SlottingPaybackPeriodChart slottingPaybackPeriodChart;
    public PostSpoilsPostFreightChart postSpoilsPostFreightChart;
    public UnspentTradePerUnitChart unspentTradePerUnitChart;
    public FourYearEqGpPerSkuChart fourYearEqGpPerSkuChart;
    public FourYearEqGpPerUnitChart fourYearEqGpPerUnitChart;

    private TableColumn<RTMOption, String> rtmNameCol1;
    private TableColumn<RTMOption, BigDecimal> slottingPerSkuCol;
    private TableColumn<RTMOption, BigDecimal> freightOutPerUnitCol;
    private TableColumn<RTMOption, BigDecimal> firstReceiverCol;
    private TableColumn<RTMOption, BigDecimal> secondReceiverColumn;
    private TableColumn<RTMOption, BigDecimal> thirdReceiverColumn;
    private TableColumn<RTMOption, BigDecimal> fourthReceiverColumn;
    private TableColumn<RTMOption, BigDecimal> landedStoreCostColumn;
    private TableColumn<RTMOption, BigDecimal> everydayRetailCalcdCol;
    private TableColumn<RTMOption, BigDecimal> everydayRetailOverrideCol;
    private TableColumn<RTMOption, String> rtmNameColumn2;
    private TableColumn<RTMOption, BigDecimal> elasticizedUnitVelocityColumn;
    private TableColumn<RTMOption, BigDecimal> annualVolumePerSkuColumn;
    private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodColumn;
    private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsPerUnitCol;
    private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitColumn;
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuColumn;
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitColumn;

    Label listLabel;
    Label fobLabel;
    Label net1GoalLabel;
    Label elasticityRatioLabel;

    private FXMLLoader fxmlLoader;


    @Start
    public void start(Stage stage) throws IOException {
        fxmlLoader = App.createFXMLLoader("secondTable");
        Scene scene = new Scene(fxmlLoader.load());
        App.setNewScene(scene);
        controller = fxmlLoader.getController();
        controller.setRetailer(retailer.get());

        stage.setScene(App.getScene());
        stage.show();
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp(FxRobot robot) {
//        rtmOptions = retailer.get().getRetailerProducts().get(0).getRtmOptions();
        rtmPlanningTable1 = robot.lookup(table1String).queryTableView();
        rtmPlanningTable2 = robot.lookup(table2String).queryTableView();
        everydayGpmField = robot.lookup("#everydayGpmField").queryAs(BigDecimalTextField.class);
        yearOneStoreCountField = robot.lookup("#yearOneStoreCountField").queryAs(IntegerTextField.class);
        spoilsFeesField = robot.lookup("#spoilsFeesField").queryAs(BigDecimalTextField.class);
        weeklyUfswAtMinField = robot.lookup("#weeklyUfswAtMinField").queryAs(BigDecimalTextField.class);
        productClassBox = robot.lookup("#productClassBox").queryAs(ProductClassComboBox.class);
        brandNameBox = robot.lookup("#brandNameBox").queryAs(BrandNameComboBox.class);


        RTMOption rtmOption1 = new RTMOptionBuilder().retailerProduct(currentRetailerProduct).rtmName("Option 1").buildRtmOption();
        RTMOption rtmOption2 = new RTMOptionBuilder().retailerProduct(currentRetailerProduct).rtmName("Option 2").buildRtmOption();
        RTMOption rtmOption3 = new RTMOptionBuilder().retailerProduct(currentRetailerProduct).rtmName("Option 3").buildRtmOption();
        RTMOption rtmOption4 = new RTMOptionBuilder().retailerProduct(currentRetailerProduct).rtmName("Option 4").buildRtmOption();
        rtmPlanningTable1.setItems(FXCollections.observableArrayList(rtmOption1, rtmOption2, rtmOption3, rtmOption4));
        //Set up row listeners which is normally done in initialize
        controller.setUpListeners();
        rtmPlanningTable2.setItems(rtmPlanningTable1.getItems());

        listLabel = robot.lookup("#listLabel").queryAs(Label.class);
        fobLabel = robot.lookup("#fobLabel").queryAs(Label.class);
        net1GoalLabel = robot.lookup("#net1GoalLabel").queryAs(Label.class);
        elasticityRatioLabel = robot.lookup("#elasticityRatioLabel").queryAs(Label.class);

        landedStoreCostChart = robot.lookup("#landedStoreCostChart").queryAs(LandedStoreCostChart.class);
        everydayRetailCalcdChart = robot.lookup("#everydayRetailCalcdChart").queryAs(EverydayRetailCalcdChart.class);
        elasticizedUnitVelocityChart = robot.lookup("#elasticizedUnitVelocityChart").queryAs(ElasticizedUnitVelocityChart.class);
        annualVolumePerSkuChart = robot.lookup("#annualVolumePerSkuChart").queryAs(AnnualVolumePerSkuChart.class);
        slottingPaybackPeriodChart = robot.lookup("#slottingPaybackPeriodChart").queryAs(SlottingPaybackPeriodChart.class);
        postSpoilsPostFreightChart = robot.lookup("#postSpoilsPostFreightChart").queryAs(PostSpoilsPostFreightChart.class);
        unspentTradePerUnitChart = robot.lookup("#unspentTradePerUnitChart").queryAs(UnspentTradePerUnitChart.class);
        fourYearEqGpPerSkuChart = robot.lookup("#fourYearEqGpPerSkuChart").queryAs(FourYearEqGpPerSkuChart.class);
        fourYearEqGpPerUnitChart = robot.lookup("#fourYearEqGpPerUnitChart").queryAs(FourYearEqGpPerUnitChart.class);
        ObservableList<TableColumn<RTMOption, ?>> columns1= rtmPlanningTable1.getColumns();
        rtmNameCol1= (TableColumn<RTMOption,String>) columns1.get(0);
        slottingPerSkuCol= (TableColumn<RTMOption,BigDecimal>) columns1.get(1);
        freightOutPerUnitCol= (TableColumn<RTMOption,BigDecimal>) columns1.get(2);
        firstReceiverCol= (TableColumn<RTMOption,BigDecimal>) columns1.get(3);
        secondReceiverColumn= (TableColumn<RTMOption,BigDecimal>) columns1.get(4);
        thirdReceiverColumn= (TableColumn<RTMOption,BigDecimal>) columns1.get(5);
        fourthReceiverColumn= (TableColumn<RTMOption,BigDecimal>) columns1.get(6);
        landedStoreCostColumn= (TableColumn<RTMOption,BigDecimal>) columns1.get(7);
        everydayRetailCalcdCol= (TableColumn<RTMOption,BigDecimal>) columns1.get(8);
        everydayRetailOverrideCol= (TableColumn<RTMOption,BigDecimal>) columns1.get(9);

        ObservableList<TableColumn<RTMOption, ?>> columns2= rtmPlanningTable2.getColumns();
        rtmNameColumn2= (TableColumn<RTMOption,String>) columns2.get(0);
        elasticizedUnitVelocityColumn= (TableColumn<RTMOption,BigDecimal>) columns2.get(1);
        annualVolumePerSkuColumn= (TableColumn<RTMOption,BigDecimal>) columns2.get(2);
        slottingPaybackPeriodColumn= (TableColumn<RTMOption,BigDecimal>) columns2.get(3);
        postFreightPostSpoilsPerUnitCol= (TableColumn<RTMOption,BigDecimal>) columns2.get(4);
        unspentTradePerUnitColumn= (TableColumn<RTMOption,BigDecimal>) columns2.get(5);
        fourYearEqGpPerSkuColumn= (TableColumn<RTMOption,BigDecimal>) columns2.get(6);
        fourYearEqGpPerUnitColumn= (TableColumn<RTMOption,BigDecimal>) columns2.get(7);
    }

    @Test
    public void testFirstReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(table1String, 3, 3, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFirstReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testSecondReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(table1String, 3, 4, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getSecondReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testThirdReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(table1String, 3, 5, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getThirdReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testFourthReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(table1String, 3, 6, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFourthReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("0.7"));
    }

    @Test
    public void testReceiversCorrectlyPickMaxLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell(table1String, 3, 6, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFourthReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("0.7"));

        robot.doubleClickOn(cell(table1String, 3, 5, robot));
        robot.write("1.1");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getThirdReceiver(),new BigDecimal("1.1"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("1.1"));

        robot.doubleClickOn(cell(table1String, 3, 3, robot));
        robot.write("0.9");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFirstReceiver(),new BigDecimal("0.9"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("1.1"));

        robot.doubleClickOn(cell(table1String, 3, 4, robot));
        robot.write("5.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getSecondReceiver(),new BigDecimal("5.0"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("5.0"));
    }

    @Test
    public void testMaxReceiverAndEverydayGpmUpdateEverydayRetails(FxRobot robot){
        robot.doubleClickOn(cell(table1String, 3, 3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFirstReceiver(),new BigDecimal("3.59"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("3.59"));

        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5.9833333333"),rtmPlanningTable1.getItems().get(3).getEverydayRetailCalcd());
        Assertions.assertEquals(new BigDecimal("5.9833333333"),rtmPlanningTable1.getItems().get(3).getEverydayRetailOverride());
    }

    @Test
    public void testProductSelectionUpdatesLabels(FxRobot robot){
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
            productClassBox.getSelectionModel().select(0);
        });

        FxAssert.verifyThat(listLabel, LabeledMatchers.hasText("List = $3.59")) ;
        Assertions.assertEquals("List = $3.59", listLabel.getText());
        Assertions.assertEquals("F.O.B. = $3.30", fobLabel.getText());
        Assertions.assertEquals("Net 1 Goal = $2.99", net1GoalLabel.getText());
        Assertions.assertEquals("Elasticity Ratio = +1% Price :-1.15% Volume", elasticityRatioLabel.getText());
    }
    @Test
    public void testFullWorkflow1(FxRobot robot) {
        rtmPlanningTable1.getItems().stream().forEach(e ->
                e.setRetailerProduct(retailer.get().getRetailerProducts().get(0)));
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

        robot.doubleClickOn(cell(table1String, 3, 0, robot));
        robot.write("Direct To Customer Model");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getRtmName(),"Direct To Customer Model");
        robot.doubleClickOn(cell(table1String, 3, 1, robot));
        robot.write("7500");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getSlottingPerSku(),new BigDecimal("7500"));
        robot.doubleClickOn(cell(table1String, 3, 2, robot));
        robot.write("0.29");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFreightOutPerUnit(),new BigDecimal("0.29"));
        robot.doubleClickOn(cell(table1String, 3, 3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFirstReceiver(),new BigDecimal("3.59"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getLandedStoreCost(),new BigDecimal("3.59"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getEverydayRetailCalcd(),new BigDecimal("5.9833333333"), "Resulting Calcd should have changed");
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getEverydayRetailOverride(),new BigDecimal("5.9833333333"), "Resulting Override should have changed");
//      ^^ ADD THIS LATER ^^
        robot.doubleClickOn(cell(table1String, 3, 9, robot));
        robot.write("5.99");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getEverydayRetailOverride(),new BigDecimal("5.99"));

        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("1.20");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
//        Assertions.assertEquals(new BigDecimal("1.20"), ((RTMOption)firstTableView.getItems().get(3)).getWeeklyUSFWAtMin());
//        ^^KEEPS CHANGING FROM 1.20 to 1.2  or VICE VERSA
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getElasticizedUnitVelocity(), new BigDecimal("1.2"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getAnnualVolumePerSku(), new BigDecimal("9859.2000000000"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getSlottingPaybackPeriod(), new BigDecimal("1.65436")); // Refactor this in RTM Option
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getPostSpoilsPostFreightPerUnit(), new BigDecimal("3.1923000000")); // Refactor this in RTM Option
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getUnspentTradePerUnit(), new BigDecimal("0.4923000000"));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFourYearEqGpPerSku(), new BigDecimal("18133.920000000000000000000000000000")); // Refactor this in RTM Option
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(3).getFourYearEqGpPerUnit(), new BigDecimal("0.4598222980"));
    }
    @Test
    public void testChartUpdateRtmName(FxRobot robot) throws InterruptedException {
        Assertions.assertEquals("Option 1", rtmPlanningTable1.getItems().get(0).getRtmName());
        robot.doubleClickOn(cell(table1String, 0, 0, robot));
        robot.write("New RTM Name");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals("New RTM Name", rtmPlanningTable1.getItems().get(0).getRtmName());
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
        robot.doubleClickOn(cell(table1String, 0, 4, robot));
        robot.write("4.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("4.0"), rtmPlanningTable1.getItems().get(0).getLandedStoreCost());
        assertEqualsYChartValueForFirstRTMOption(robot, landedStoreCostChart, new BigDecimal("4.0"));
    }
    @Test
    public void testChartUpdateEverydayRetailCalcd(FxRobot robot) {
        Assertions.assertEquals(new BigDecimal("0.0"), rtmPlanningTable1.getItems().get(0).getFirstReceiver());
        robot.doubleClickOn(cell(table1String,0,3, robot));
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
        robot.doubleClickOn(cell(table1String,0,3, robot));
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
        robot.doubleClickOn(cell(table1String,0,3, robot));
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
    public void testYearOneStoreCountUpdates(FxRobot robot){
        RTMOption firstRtmOption = replaceValuesInSingleRtmOption(-1, robot);

        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("158");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        assertEqualsTestRtmOption(firstRtmOption);
    }
    @Test
    public void testEverydayGpmUpdates(FxRobot robot){
        RTMOption firstRtmOption = replaceValuesInSingleRtmOption(-1, robot);

        robot.doubleClickOn(everydayGpmField);
        robot.write("40");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        assertEqualsTestRtmOption(firstRtmOption);
    }
    @Test
    public void testSpoilsFeesUpdates(FxRobot robot){
        RTMOption firstRtmOption = replaceValuesInSingleRtmOption(-1, robot);
        robot.doubleClickOn(spoilsFeesField);
        robot.write("3.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        assertEqualsTestRtmOption(firstRtmOption);
    }
    @Test
    public void testWeeklyUfswAtMinUpdates(FxRobot robot){
        RTMOption firstRtmOption = replaceValuesInSingleRtmOption(-1, robot);
        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("1.2");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        System.out.println(firstRtmOption.getMinOverride());
        assertEqualsTestRtmOption(firstRtmOption);
    }

    public RTMOption replaceValuesInSingleRtmOption(int replaceField, FxRobot robot){
        RTMOption firstRtmOption = rtmPlanningTable1.getItems().get(0);
        if (replaceField!=0){
            rtmPlanningTable1.getItems().stream().forEach(e -> e.setRetailerProduct(retailer.get().getRetailerProducts().get(0)));
        }  if (replaceField!=1){
            firstRtmOption.setFreightOutPerUnit(new BigDecimal("0.29"));
        } if (replaceField!=2){
            firstRtmOption.setSlottingPerSku(new BigDecimal("7500"));
        } if (replaceField!=3){
            firstRtmOption.setFirstReceiver(new BigDecimal("3.59"));
        } if (replaceField!=4) {
            robot.interact(() -> firstRtmOption.setEverydayRetailOverride(new BigDecimal("5.99")));
        }
        return firstRtmOption;
    }
    public void assertEqualsTestRtmOption(RTMOption firstRtmOption){
        Assertions.assertEquals(new BigDecimal("1.2"), firstRtmOption.getElasticizedUnitVelocity());
        Assertions.assertEquals(new BigDecimal("9859.2000"), firstRtmOption.getAnnualVolumePerSku());
        Assertions.assertEquals(new BigDecimal("1.6544"), firstRtmOption.getSlottingPaybackPeriod());
        Assertions.assertEquals(new BigDecimal("3.1923"), firstRtmOption.calcPostSpoilsAndFreightWeCollectPerUnit());
        Assertions.assertEquals(new BigDecimal("0.4923"), firstRtmOption.getUnspentTradePerUnit());
        Assertions.assertEquals(new BigDecimal("18133.9200"), firstRtmOption.getFourYearEqGpPerSku());
        Assertions.assertEquals(new BigDecimal("0.4598"), firstRtmOption.getFourYearEqGpPerUnit());
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
            everydayRetailCalcdChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, everydayRetailCalcdChart, new BigDecimal("6.788"));
    }
    @Test
    public void testUpdateChartElasticizedUnitVelocityChart(FxRobot robot){
        testOption.setElasticizedUnitVelocity(new BigDecimal("50"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            elasticizedUnitVelocityChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, elasticizedUnitVelocityChart, new BigDecimal("50"));
    }
    @Test
    public void testUpdateChartAnnualVolumePerSku(FxRobot robot){
        testOption.setAnnualVolumePerSku(new BigDecimal("202"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            annualVolumePerSkuChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, annualVolumePerSkuChart, new BigDecimal("202"));
    }
    @Test
    public void testUpdateChartSlottingPerSku(FxRobot robot){
        testOption.setSlottingPaybackPeriod(new BigDecimal("0.22"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            slottingPaybackPeriodChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, slottingPaybackPeriodChart, new BigDecimal("0.22"));
    }
    @Test
    public void testUpdateChartPostFreightPostSpoils(FxRobot robot){
        testOption.setPostSpoilsPostFreightPerUnit(new BigDecimal("0.25"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            postSpoilsPostFreightChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, postSpoilsPostFreightChart, new BigDecimal("0.25"));
    }
    @Test
    public void testUpdateChartUnspentTradePerUnit(FxRobot robot){
        testOption.setUnspentTradePerUnit(new BigDecimal("0.22"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            unspentTradePerUnitChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, unspentTradePerUnitChart, new BigDecimal("0.22"));
    }

    @Test
    public void testUpdateChartFourYearEqGpPerSku(FxRobot robot){
        testOption.setFourYearEqGpPerSku(new BigDecimal("0.20002"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            fourYearEqGpPerSkuChart.updateChart(rtmPlanningTable1.getItems());
        });
        assertEqualsYChartValueForFirstRTMOption(robot, fourYearEqGpPerSkuChart, new BigDecimal("0.20002"));
    }
    @Test
    public void testUpdateChartFourYearEqGpPerUnit(FxRobot robot){
        testOption.setFourYearEqGpPerUnit(new BigDecimal("0.555"));
        rtmPlanningTable1.getItems().set(0,testOption);
        robot.interact(()->{
            fourYearEqGpPerUnitChart.updateChart(rtmPlanningTable1.getItems());
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
        testOption.setPostSpoilsPostFreightPerUnit(new BigDecimal("6"));
        testOption.setUnspentTradePerUnit(new BigDecimal("7"));
        testOption.setFourYearEqGpPerSku(new BigDecimal("8"));
        testOption.setFourYearEqGpPerUnit(new BigDecimal("9"));
        robot.interact(()->{
            controller.updateCharts();
        });
        assertEqualsYChartValueForFirstRTMOption(robot, landedStoreCostChart, new BigDecimal("1"));
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
    public void testGetFocusedRtmOption(FxRobot robot){
        robot.clickOn(cell(table1String,1,3, robot));
        Assertions.assertEquals(rtmPlanningTable1.getItems().get(1), controller.getFocusedRtmOption(), "Focused RTM Option is wrong");
    }

    @Test
    public void testIntegerTextFieldGetValue(FxRobot robot){
        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("1000");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(1000, (int) yearOneStoreCountField.getValue(), "Textfield gets wrong value");
    }
    @Test
    public void testBigDecimalTextFieldGetValue(FxRobot robot){
        robot.doubleClickOn(everydayGpmField);
        robot.write("99");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("99.0"), everydayGpmField.getValue(), "Textfield gets wronb value");
    }
    @Test
    public void testYearOneStoreCountFieldEnteringValueGreaterThan10000(FxRobot robot){
        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("10001");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals( yearOneStoreCountField.getText(), yearOneStoreCountField.getDefaultValue().toString(), "Textfield ignores decimal points");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }
    @Test
    public void testYearOneStoreCountFieldEnteringDecimalValue(FxRobot robot){
        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals( yearOneStoreCountField.getText(), String.valueOf(359), "Textfield ignores decimal points");
    }
    @Test
    public void testYearOneStoreCountFieldEnteringNegativeValue(FxRobot robot){
        robot.doubleClickOn(yearOneStoreCountField);
        robot.write("-39");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(yearOneStoreCountField.getText(), String.valueOf(39),  "Textfield ignores negative values");
    }
    @Test
    public void testEverydayGpmFieldEnteringValueLessThan1(FxRobot robot){
        robot.doubleClickOn(everydayGpmField);
        robot.write("0.99");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(everydayGpmField.getText(), everydayGpmField.getDefaultValue().toString(),  "Textfield rejects values less than 1");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }
    @Test
    public void testEverydayGpmFieldEnteringValueGreaterThan99(FxRobot robot){
        robot.doubleClickOn(everydayGpmField);
        robot.write("100");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(everydayGpmField.getText(), everydayGpmField.getDefaultValue().toString(),  "Textfield rejects values greater than 99");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }

    @Test
    public void testEverydayGpmFieldEnteringMinValueAsDecimal(FxRobot robot){
        robot.doubleClickOn(everydayGpmField);
        robot.write("1.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(everydayGpmField.getText(), String.valueOf(1.0),  "Textfield accepts decimal values");
    }
    @Test
    public void testSpoilsFeesFieldEnteringMaxValueAsDecimal(FxRobot robot){
        robot.doubleClickOn(spoilsFeesField);
        robot.write("99.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(spoilsFeesField.getText(), String.valueOf(99.0),  "Textfield accepts decimal value");
    }
    @Test
    public void testSpoilsFeesFieldEnteringValueGreaterThan99(FxRobot robot){
        robot.doubleClickOn(spoilsFeesField);
        robot.write("100");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(spoilsFeesField.getText(), spoilsFeesField.getDefaultValue().toString(),  "Textfield rejects values greater than 99");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }
    @Test
    public void testSpoilsFeesFieldEnteringNegativeValue(FxRobot robot){
        robot.doubleClickOn(spoilsFeesField);
        robot.write("-1");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(spoilsFeesField.getText(), spoilsFeesField.getDefaultValue().toString(),  "Textfield rejects negative values");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }
    @Test
    public void testWeeklyUfswAtMinFieldEnteringValueOver1000(FxRobot robot){
        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("1001");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(weeklyUfswAtMinField.getText(), weeklyUfswAtMinField.getDefaultValue().toString(),  "Textfield rejects values over 1000");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }
    @Test
    public void testWeeklyUfswAtMinFieldEnteringNegativeValue(FxRobot robot){
        robot.doubleClickOn(weeklyUfswAtMinField);
        robot.write("-1");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(weeklyUfswAtMinField.getText(), weeklyUfswAtMinField.getDefaultValue().toString(),  "Textfield rejects negative values over 1000");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
    }


    @Test
    public void testSlottingPerSkuCellStartsWithDollar(FxRobot robot){
        TableCell cell = cell(table1String,3,1,robot);
        typeInCell(cell,"30.0", robot);
        Assertions.assertEquals("$30.00", cell.getText());
    }
    @Test
    public void testFreightOutPerUnitCellStartsWithDollar(FxRobot robot){
        TableCell tableCell = cell(table1String,3,2, robot);
        typeInCell(tableCell, "30.0", robot);
        Assertions.assertEquals("$30.00", tableCell.getText());
    }
    @Test
    public void testFirstReceiverCellStartsWithDollar(FxRobot robot){
        TableCell tableCell = cell(table1String,3,3, robot);
        typeInCell(tableCell, "30.0", robot);
        Assertions.assertEquals("$30.00", tableCell.getText());
    }
    @Test
    public void testSecondReceiverCellStartsWithDollar(FxRobot robot){
        TableCell tableCell = cell(table1String,3,4, robot);
        typeInCell(tableCell, "30.0", robot);
        Assertions.assertEquals("$30.00", tableCell.getText());
    }
    @Test
    public void testThirdReceiverCellStartsWithDollar(FxRobot robot){
        TableCell tableCell = cell(table1String,3,5, robot);
        typeInCell(tableCell, "30.0", robot);
        Assertions.assertEquals("$30.00", tableCell.getText());
    }
    @Test
    public void testFourthReceiverCellStartsWithDollar(FxRobot robot){
        TableCell tableCell = cell(table1String,3,6, robot);
        typeInCell(tableCell, "30.0", robot);
        Assertions.assertEquals("$30.00", tableCell.getText());
    }
    public void setCellValue(TableCell cell, String value, FxRobot robot){

    }
    public RTMOption testRTMOptionCell(int row){
        return rtmPlanningTable1.getItems().get(row);
    }

    @Test
    public void testEverydayRetailCalcdCellStartsWithDollar(FxRobot robot) {
        robot.interact(()->
                testRTMOptionCell(3).setEverydayRetailCalcd(new BigDecimal("30.0")));
        Assertions.assertEquals("$30.00", cell(table1String,3,8, robot).getText());
    }
    @Test
    public void testEverydayRetailOverrideCellStartsWithDollar(FxRobot robot) {
        TableCell tableCell = cell(table1String,3,9, robot);
        typeInCell(tableCell, "30.0", robot);
        Assertions.assertEquals("$30.00", tableCell.getText());
    }
    @Test
    public void testElasticizedUnitVelocityCellStartsWithDollar(FxRobot robot) {
        robot.interact(()-> testRTMOptionCell(3).setElasticizedUnitVelocity(new BigDecimal("30.0")));
        Assertions.assertEquals("30.00 U/F/S/W", cell(table2String,3,1, robot).getText());
    }
    @Test
    public void testAnnualVolumePerSkuCellStartsWithDollar(FxRobot robot) {
        robot.interact(()-> testRTMOptionCell(3).setAnnualVolumePerSku(new BigDecimal("30.0")));
        Assertions.assertEquals("30 Units", cell(table2String,3,2, robot).getText());
    }
    @Test
    public void testSlottingPaybackPeriodCellStartsWithDollar(FxRobot robot) {
        robot.interact(()->
                testRTMOptionCell(3).setSlottingPaybackPeriod(new BigDecimal("30.0")));
        Assertions.assertEquals("30.00 Years", cell(table2String,3,3, robot).getText());
    }
    @Test
    public void testPostSpoilsPostFreightPerUnitCellStartsWithDollar(FxRobot robot) {
        robot.interact(()->
                testRTMOptionCell(3).setPostSpoilsPostFreightPerUnit(new BigDecimal("30.0")));
        Assertions.assertEquals("$30.00 Per Unit", cell(table2String,3,4, robot).getText());
    }
    @Test
    public void testUnspentTradePerUnitCellStartsWithDollar(FxRobot robot) {
        robot.interact(()->
                testRTMOptionCell(3).setUnspentTradePerUnit(new BigDecimal("30.0")));
        Assertions.assertEquals("$30.00 Per Unit", cell(table2String,3,5, robot).getText());
    }
    @Test
    public void testFourYearEqGpPerSkuCellStartsWithDollar(FxRobot robot) {
        robot.interact(()->
                testRTMOptionCell(3).setFourYearEqGpPerSku(new BigDecimal("30.0")));
        Assertions.assertEquals("$30 Per Sku", cell(table2String,3,6, robot).getText());
    }
    @Test
    public void testFourYearEqGpPerUnitCellStartsWithDollar(FxRobot robot) {
        robot.interact(()->
                testRTMOptionCell(3).setFourYearEqGpPerUnit(new BigDecimal("30.0")));
        Assertions.assertEquals("$30.00 Per Unit", cell(table2String,3,7, robot).getText());
    }


    @Test
    public void testGetUniqueBrandNamesForBrandComboBox(FxRobot robot){
        ObservableList<Product> productsWithUniqueBrandNames =
                brandNameBox.getUniqueBrandNames(MenuController.getExampleProducts());
        ObservableList<String> stringList= FXCollections.observableArrayList();
        for (Product product: productsWithUniqueBrandNames){
            stringList.add(product.getBrandName());
        }
        Set<String> uniqueSet = new HashSet<String>(stringList);
        Assertions.assertTrue(uniqueSet.size()==stringList.size());
    }
    @Test
    public void testGetCorrespondingProductClassesForComboBox(FxRobot robot){
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
        });
        Product productWithCorrectBrandName = brandNameBox.getSelectionModel().getSelectedItem();
        ObservableList<Product> productsWithCorrectBrandName =
                productClassBox.getCorrectProductClasses(MenuController.getExampleProducts(),
                        productWithCorrectBrandName);
        for (Product product: productsWithCorrectBrandName){
            Assertions.assertEquals(productWithCorrectBrandName.getBrandName(), product.getBrandName());
            System.out.println("product class"+ product.getProductClass());
        }
    }
    @Test
    public void testBrandNameBoxConverter(FxRobot robot){
        Product exampleProduct = new Product();
        exampleProduct.setBrandName("edeka");
        Assertions.assertEquals(exampleProduct.getBrandName(),
                brandNameBox.getConverter().toString(exampleProduct));
    }
    @Test
    public void testProductClassBoxConverter(FxRobot robot){
        Product exampleProduct = new Product();
        exampleProduct.setProductClass("pickles");
        Assertions.assertEquals(exampleProduct.getProductClass(),
                productClassBox.getConverter().toString(exampleProduct));
    }
    @Test
    public void testBrandNameBoxEvent(FxRobot robot){
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
        });
        FxAssert.verifyThat(listLabel, LabeledMatchers.hasText("List = $"));
        FxAssert.verifyThat(fobLabel, LabeledMatchers.hasText("F.O.B. = $"));
        FxAssert.verifyThat(net1GoalLabel, LabeledMatchers.hasText("Net 1 Goal = $"));
        FxAssert.verifyThat(elasticityRatioLabel, LabeledMatchers.hasText("Elasticity Ratio = +1% Price :  % Volume"));
    }
    @Test
    public void testProductClassBoxEvent(FxRobot robot){
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
            productClassBox.getSelectionModel().select(0);
        });
        Product selectedProduct =  productClassBox.getSelectionModel().getSelectedItem();
        FxAssert.verifyThat(listLabel, LabeledMatchers.hasText("List = $"+selectedProduct.getUnitListCost()));
        FxAssert.verifyThat(fobLabel, LabeledMatchers.hasText("F.O.B. = $" + selectedProduct.getUnitFobCost()));
        FxAssert.verifyThat(net1GoalLabel, LabeledMatchers.hasText("Net 1 Goal = $"+ selectedProduct.getUnitNet1Goal()));
        FxAssert.verifyThat(elasticityRatioLabel, LabeledMatchers.hasText("Elasticity Ratio = +1% Price :"+selectedProduct.getElasticityMultiple()+ "% Volume"));
        for (RTMOption row: rtmPlanningTable1.getItems()){
            Assertions.assertEquals(selectedProduct, row.getProduct());
        }
    }
    @Test
    public void testColumnHeaderLabels(){
        Assertions.assertEquals(((Label)rtmNameCol1.getGraphic()).getText(), "Route-to-Market Options");
        Assertions.assertEquals(((Label)slottingPerSkuCol.getGraphic()).getText(), "Slotting Per Sku");
        Assertions.assertEquals(((Label)freightOutPerUnitCol.getGraphic()).getText(), "Freight Out Per Unit");
        Assertions.assertEquals(((Label)firstReceiverCol.getGraphic()).getText(), "First Receiver Pays");
        Assertions.assertEquals(((Label)secondReceiverColumn.getGraphic()).getText(), "Second Receiver Pays");
        Assertions.assertEquals(((Label)thirdReceiverColumn.getGraphic()).getText(), "Third Receiver Pays");
        Assertions.assertEquals(((Label)fourthReceiverColumn.getGraphic()).getText(), "Fourth Receiver Pays");
        Assertions.assertEquals(((Label)landedStoreCostColumn.getGraphic()).getText(), "Landed Store Cost");
        Assertions.assertEquals(((Label)everydayRetailCalcdCol.getGraphic()).getText(), "Calculated Everyday Retail");
        Assertions.assertEquals(((Label)everydayRetailOverrideCol.getGraphic()).getText(), "Override Everyday Retail");

        Assertions.assertEquals(((Label)rtmNameColumn2.getGraphic()).getText(), "Route-to-Market Options");
        Assertions.assertEquals(((Label)elasticizedUnitVelocityColumn.getGraphic()).getText(), "Elasticized Unit Velocity");
        Assertions.assertEquals(((Label)annualVolumePerSkuColumn.getGraphic()).getText(), "Annual Volume Per Sku");
        Assertions.assertEquals(((Label)slottingPaybackPeriodColumn.getGraphic()).getText(), "Slotting Payback Period");
        Assertions.assertEquals(((Label)postFreightPostSpoilsPerUnitCol.getGraphic()).getText(), "Post Freight & Spoils We Collect");
        Assertions.assertEquals(((Label)unspentTradePerUnitColumn.getGraphic()).getText(), "Unspent Trade Per Unit");
        Assertions.assertEquals(((Label)fourYearEqGpPerSkuColumn.getGraphic()).getText(), "4-Year EQ GP $ Per Sku");
        Assertions.assertEquals(((Label)fourYearEqGpPerUnitColumn.getGraphic()).getText(), "4-Year EQ GP $ Per Unit");
    }
    @Test
    public void testColumnHeaderToolTips(){
        Assertions.assertEquals(((Label)rtmNameCol1.getGraphic()).getTooltip().getText(), "Please enter the most likely 'Route-To-Market'" + " options to get the product to the market.");
        Assertions.assertEquals(((Label)slottingPerSkuCol.getGraphic()).getTooltip().getText(), "Please enter the required slotting (placement)" + " investment specific to this 'Route-To-Market' option.");
        //TODO add dynamic value for end of below tooltip freight-out value
        Assertions.assertEquals(((Label)freightOutPerUnitCol.getGraphic()).getTooltip().getText(), "If we're responsible for the cost of shipping for this 'Route-To-Market' option, please enter in the 'per unit cost' of this 'Freight-Out.' For F.O.B (Pick-up) Customers, Freight-Out equals $0.");
        Assertions.assertEquals(((Label)firstReceiverCol.getGraphic()).getTooltip().getText(), "The Per Unit Cost the First Receiver pays us,"
                + " typically the Unit List Cost or the Unit F.O.B");
        Assertions.assertEquals(((Label)secondReceiverColumn.getGraphic()).getTooltip().getText(), "The Per Unit Cost the Second Receiver pays"
                + " to the First Receiver.");
        Assertions.assertEquals(((Label)thirdReceiverColumn.getGraphic()).getTooltip().getText(), "The Per Unit Cost the Third Receiver"
                + " pays to the Second Receiver.");
        Assertions.assertEquals(((Label)fourthReceiverColumn.getGraphic()).getTooltip().getText(), "The Per Unit Cost the Fourth Receiver"
                + " pays to the Third Receiver.");
        Assertions.assertEquals(((Label)landedStoreCostColumn.getGraphic()).getTooltip().getText(), "The Per Unit Cost the Retail OutLet (Last Receiver) pays prior to applying the Required GPM% to establish the Everyday Retail.");
        Assertions.assertEquals(((Label)everydayRetailCalcdCol.getGraphic()).getTooltip().getText(), "The auto-calculated Resulting Everyday"
                + " Retail given the Landed Store Cost and Required " + "Gross Profit Margin %.");
        Assertions.assertEquals(((Label)everydayRetailOverrideCol.getGraphic()).getTooltip().getText(), "Please enter the REALISTIC Everyday Retail"
                + " considering the auto-calculated retail to the left.");

        Assertions.assertEquals(((Label)elasticizedUnitVelocityColumn.getGraphic()).getTooltip().getText(), "For each 'Route-To-Market' option provided, these are the Estimated Weekly Unit Velocities given the Product Class's Price Elasticity Multiple (for each X% increase in Unit Price there is a Y% decrease in Units Sold");
        Assertions.assertEquals(((Label)annualVolumePerSkuColumn.getGraphic()).getTooltip().getText(), "For each route-to-market option provided,"
                + " these are the Estimated Annual Volumes Per SKU " + "(accounting for price elasticities)");
        Assertions.assertEquals(((Label)slottingPaybackPeriodColumn.getGraphic()).getTooltip().getText(), "If Slotting is a consideration these" + " are the Payback Periods (in years) for each "
                + "route-to-market option provided to recoup the " + "initial Slotting Investment.");
        Assertions.assertEquals(((Label)postFreightPostSpoilsPerUnitCol.getGraphic()).getTooltip().getText(), "For each route-to-market option provided," + " the Per Unit Rate we collect after which Freight"
                + " Costs and Spoils are accounted (but prior to Trade" + " Spending).");
        Assertions.assertEquals(((Label)unspentTradePerUnitColumn.getGraphic()).getTooltip().getText(), "Unspent Trade Per Unit");
        Assertions.assertEquals(((Label)fourYearEqGpPerSkuColumn.getGraphic()).getTooltip().getText(), "4-Year Equivalized Gross Profit $ Per Sku");
        Assertions.assertEquals(((Label)fourYearEqGpPerUnitColumn.getGraphic()).getTooltip().getText(), "4-Year Equivalized Gross Profit $ Per Unit");
    }
    @Test
    public void testSwitchToAssortment(FxRobot robot){
        Button switchToAssortmentButton = robot.lookup("#switchToAssortmentButton").queryButton();
        robot.clickOn(switchToAssortmentButton);

        Assertions.assertTrue(App.getFxmlLoader().getController() instanceof AssortmentController);
    }
    @Test
    public void testSwitchToRetailerSelection(FxRobot robot){
        Button switchToRetailerSelectionButton = robot.lookup("#switchToRetailerSelectionButton").queryButton();
        robot.clickOn(switchToRetailerSelectionButton);

        Assertions.assertTrue(App.getFxmlLoader().getController() instanceof RetailerSelectionController);
    }
    @Test
    public void testSwitchToPricingPromotion(FxRobot robot){
        Button switchToPricingPromotionButton = robot.lookup("#switchToPricingPromotionButton").queryButton();
        robot.clickOn(switchToPricingPromotionButton);

        Assertions.assertTrue(App.getFxmlLoader().getController() instanceof PricingPromotionController);
    }




}