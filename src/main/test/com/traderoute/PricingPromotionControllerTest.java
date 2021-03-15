package com.traderoute;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.loadui.testfx.utils.FXTestUtils;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;
import org.junit.jupiter.api.Test;
import smetana.core.CObject;
import org.loadui.testfx.controls.TableViews;


import java.io.IOException;
import java.math.BigDecimal;
import org.loadui.testfx.GuiTest;

import java.security.Key;
import java.util.List;
import static org.loadui.testfx.GuiTest.find;
//import static org.loadui.testfx.controls.TableViews.cell;
//import static org.loadui.testfx.controls.TableViews.row;

@ExtendWith(ApplicationExtension.class)
public class PricingPromotionControllerTest{

//    public static GuiTest controller;

    private ObservableList<RTMOption> rtmOptions;
    private SimpleObjectProperty<Retailer> retailer= new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),firstTableController.getRetailerProducts().get(0) ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));;
    private TableView pricingPromotionTableOne;

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

    private PricingPromotionController controller;


    //    @BeforeClass
//    public static void setUpClass() throws InterruptedException, IOException
//    {
//        FXTestUtils.launchApp(App.class);
//        Thread.sleep(2000);
//        controller = new GuiTest()
//        {
//            @Override
//            protected Parent getRootNode()
//            {
//                return App.getStage().getScene().getRoot();
//            }
//        };
//    }
    @Start
    public void start(Stage stage) throws IOException {
//        System.out.println(getFXMLLoader("secondTable").getController());
        FXMLLoader fxmlLoader = App.createFXMLLoader("pricingPromotion");
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println(fxmlLoader.getController().toString());
        controller = fxmlLoader.getController();
        controller.setRetailer(retailer.get());
        stage.setScene(scene);
        stage.show();

    }
    @org.junit.jupiter.api.BeforeEach
    void setUp(FxRobot robot) {
         rtmOptions = retailer.get().getRetailerProducts().get(0).getRtmOptions();
         pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();
        controller.getPromoPlans().get(0).setSelectedRtm(retailer.get().getRetailerProducts().get(0).getRtmOptions().get(1));

    }

    @Test
    public void testChangeFirstSelectedRtmOption(FxRobot robot){
        ComboBox rtmBox0 = robot.lookup("#rtmBox0").queryAs(ComboBox.class);
        Label everydayLabel0 = robot.lookup("#everydayLabel0").queryAs(Label.class);
        Label costLabel0 = robot.lookup("#costLabel0").queryAs(Label.class);
        Label gpmLabel0 = robot.lookup("#gpmLabel0").queryAs(Label.class);
        Assertions.assertEquals( "$0.00 Everyday",everydayLabel0.getText() ,"everydayLabel0 text should be $0.00 Everyday");
        Assertions.assertEquals( "$0.00 Cost", costLabel0.getText(),"costLabel0 text should be $0.00 Cost before click");
        Assertions.assertEquals("0.00% GPM", gpmLabel0.getText() , "gpmLabel0 text should be 0.00% GPM before click");
        Assertions.assertEquals(rtmOptions.get(1), rtmBox0.getSelectionModel().getSelectedItem(), "Should be second RTM option before click");
        robot.interact(() -> {
//            System.out.println(rtmBox.getSelectionModel().getSelectedItem());]
            rtmBox0.getSelectionModel().select(0);

        });
        Assertions.assertEquals(rtmOptions.get(0),rtmBox0.getSelectionModel().getSelectedItem(), "Should have changed to first RTM after clicking different option");
        Assertions.assertEquals("$5.99 Everyday",everydayLabel0.getText() ,"everydayLabel0 text should be $5.99 Everyday after");
        Assertions.assertEquals( "$3.59 Cost", costLabel0.getText(),"costLabel0 text should be $3.59 Cost after click");
        Assertions.assertEquals("40.00% GPM", gpmLabel0.getText() , "gpmLabel0 text should be 40.00% GPM after click");
    }
    @Test
    public void testFieldsAndButtonsFromOtherPromoPlansDisabled(FxRobot robot) {
        Button commitButton1 = robot.lookup("#commitButton1").queryButton();
        robot.clickOn(commitButton1);
        Assertions.assertFalse(controller.getPromoPlans().get(1).getCommitted());

        TextField textField2 = robot.lookup("#weeklyVelocityField2").queryAs(TextField.class);
        robot.doubleClickOn(textField2);
        robot.doubleClickOn(textField2);
        robot.type(KeyCode.DIGIT7);
        Assertions.assertEquals("",textField2.getText());
    }
    @Test
    public void testEditButtonChangesCurrentPromoPlanIndex(FxRobot robot) {
        Button editButton1 = robot.lookup("#editButton1").queryButton();
        robot.clickOn(editButton1);
        Assertions.assertEquals(controller.getPromoPlans().get(controller.getCurrentPromoPlanIndex()),controller.getPromoPlans().get(1));
    }

    @Test
    public void testChangeWeeklyVelocity(FxRobot robot) {
        TextField weeklyVelocityField0 = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        TableView toplineTable0 = robot.lookup("#toplineTable0").queryTableView();
        TableView retailerTable0 = robot.lookup("#retailerTable0").queryTableView();
        Assertions.assertEquals(((Summary)toplineTable0.getItems().get(0)).getSummaryValue(), new BigDecimal("0.0"), "Topline table values zero initially");
        Assertions.assertEquals(((Summary)toplineTable0.getItems().get(1)).getSummaryValue(), new BigDecimal("0.0"), "Topline table values zero initially");
        Assertions.assertEquals(((Summary)toplineTable0.getItems().get(2)).getSummaryValue(), new BigDecimal("0.0"), "Topline table values zero initially");
        Assertions.assertEquals(((Summary)retailerTable0.getItems().get(0)).getSummaryValue(), new BigDecimal("0.0"), "Retailer table values zero initially");
        Assertions.assertEquals(((Summary)retailerTable0.getItems().get(1)).getSummaryValue(), new BigDecimal("0.0"), "Retailer table values zero initially");
        Assertions.assertEquals(((Summary)retailerTable0.getItems().get(2)).getSummaryValue(), new BigDecimal("0.0"), "Retailer table values zero initially");
        robot.interact(() -> {
            robot.doubleClickOn(weeklyVelocityField0);
            robot.type(KeyCode.DIGIT0,KeyCode.PERIOD, KeyCode.DIGIT7, KeyCode.DIGIT5);
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        });
        Assertions.assertEquals(new BigDecimal("177536.7000"),((Summary)toplineTable0.getItems().get(0)).getSummaryValue(), "Topline table value 0 should be 177536");
        Assertions.assertEquals(new BigDecimal("162984.5390") , ((Summary)toplineTable0.getItems().get(1)).getSummaryValue(), "Topline table value 1 should be 162985");
        Assertions.assertEquals(new BigDecimal("53799.0000") , ((Summary)toplineTable0.getItems().get(2)).getSummaryValue(), "Topline table value 2 should 53799");
        Assertions.assertEquals(new BigDecimal("332565.5100"), ((Summary)retailerTable0.getItems().get(0)).getSummaryValue(), "Retailer table values zero 332566");
        Assertions.assertEquals(new BigDecimal("40.0000"), ((Summary)retailerTable0.getItems().get(1)).getSummaryValue(), "Retailer table value 1 should be 40.00");
        Assertions.assertEquals(new BigDecimal("6.1816"), ((Summary)retailerTable0.getItems().get(2)).getSummaryValue(), "Retailer table value 2 should be 6.18");

    }

    @Test
    public void testInputtingOnTableView(FxRobot robot){
//        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();

        robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 2, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 2, robot));
        robot.type(KeyCode.DIGIT0, KeyCode.PERIOD, KeyCode.DIGIT7, KeyCode.DIGIT5); //.type(.type(KeyCode.DIGIT7).type();
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assert.assertEquals(((Parameter)pricingPromotionTableOne.getItems().get(2)).getFebruary(),new BigDecimal("0.75"));

    }

    @Test
    public void testSkusInPromotionEdit(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();

        robot.doubleClickOn(cell("#pricingPromotionTableOne", 0, 1, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 0, 1, robot));
        robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        // Test that all values before sku count change in july are equal to the input
        Assertions.assertEquals(new BigDecimal("7.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july should be equal to input");
        Assertions.assertEquals(new BigDecimal("7.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getFebruary(), "Values before sku count change in july should be equal to input");
        // Values after July should be 1 more because thats where default parameters have sku count change
        Assertions.assertEquals(new BigDecimal("8.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values before sku count change in july should be equal to input");
        Assertions.assertEquals(new BigDecimal("8.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getDecember(), "Values before sku count change in july should be equal to input");
    }

    @Test
    public void testAddingOnlySkuCountChangeDoesNothing(FxRobot robot) {
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 4, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 4, robot));
        robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("5.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("6.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values after sku count change in july shouldn't have changed");
    }

    @Test
    public void testAddingOnlyConfidencePerDoesNothing(FxRobot robot) {
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 3, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 3, robot));
        robot.type(KeyCode.DIGIT7);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("5.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("6.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values after sku count change in july shouldn't have changed");
    }
    @Test
    public void testChangingEverydayRetail(FxRobot robot) {
        ((BigDecimalParameter) pricingPromotionTableOne.getItems().get(everydayRetailIndex)).setMonth(3, new BigDecimal("6.00"));
        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.60"), ((Parameter)pricingPromotionTableOne.getItems().get(everydayUnitCostIndex)).getMarch(), "Values before sku count change in july shouldn't have changed");
    }

    @Test
    public void testAddingSkuCountChangeAndConfidencePer(FxRobot robot) {
        ParameterEditCell cell = (ParameterEditCell) cell("#pricingPromotionTableOne", 2, 5, robot);
        robot.doubleClickOn(cell);
        robot.doubleClickOn(cell);

        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 5, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 5, robot));
        robot.type(KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("5.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getApril(), "Values before first sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("7.50"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getMay(), "Values after first sku count change in july should have increased by 2.5");
        Assertions.assertEquals(new BigDecimal("8.50"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values after first sku count change in july should have increased by 2.5");
    }
    @Test
    public void testPromo1UnitCostChange(FxRobot robot) {
        ParameterEditCell promotedRetailCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 10, 9, robot);
        robot.doubleClickOn(promotedRetailCell);
        robot.doubleClickOn(promotedRetailCell);

        robot.type(KeyCode.DIGIT5,KeyCode.PERIOD,KeyCode.DIGIT9, KeyCode.DIGIT9);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        ParameterEditCell requiredGpmCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 11, 9, robot);
        robot.doubleClickOn(requiredGpmCell);
        robot.doubleClickOn(requiredGpmCell);
        robot.type(KeyCode.DIGIT4, KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.594"), ((Parameter)pricingPromotionTableOne.getItems().get(15)).getSeptember(), "Promo Unit cost should have changed to 3.59");
        Assertions.assertEquals(new BigDecimal("7.7000"), ((Parameter)pricingPromotionTableOne.getItems().get(16)).getSeptember(), "Promo Discount Percentage should have changed to 7.70");
    }
    @Test
    public void testPromo2UnitCostChange(FxRobot robot) {
        ParameterEditCell promotedRetail2Cell = (ParameterEditCell) cell("#pricingPromotionTableOne", 18, 10, robot);
        robot.doubleClickOn(promotedRetail2Cell);
        robot.doubleClickOn(promotedRetail2Cell);

        robot.type(KeyCode.DIGIT5,KeyCode.PERIOD,KeyCode.DIGIT9, KeyCode.DIGIT9);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        ParameterEditCell requiredGpm2Cell = (ParameterEditCell) cell("#pricingPromotionTableOne", 19, 10, robot);
        robot.doubleClickOn(requiredGpm2Cell);
        robot.doubleClickOn(requiredGpm2Cell);
        robot.type(KeyCode.DIGIT4, KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.594"), ((Parameter)pricingPromotionTableOne.getItems().get(23)).getOctober(), "Promo Unit cost should have changed to 3.59");
        Assertions.assertEquals(new BigDecimal("7.7000"), ((Parameter)pricingPromotionTableOne.getItems().get(24)).getOctober(), "Promo Discount Percentage should have changed to 7.70");
    }
    @Test
    public void testSlottingChangesGrossProfitPlan(FxRobot robot) {
        ParameterEditCell confidencePerCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 2, 5, robot);
        robot.doubleClickOn(confidencePerCell);
        robot.doubleClickOn(confidencePerCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        ParameterEditCell slottingCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 3, 5, robot);
        robot.doubleClickOn(slottingCell);
        robot.doubleClickOn(slottingCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0, KeyCode.DIGIT0,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("-2500"), ((Parameter)pricingPromotionTableOne.getItems().get(26)).getMay(), "Gross Profit Plan should have changed to -2500.00");
    }
    @Test
    public void testChangingStoreCount(FxRobot robot){
        Assertions.assertEquals(158, ((Parameter)pricingPromotionTableOne.getItems().get(storeCountIndex)).getMay(), "Store Count should be 158 initially");

        ParameterEditCell confidencePerCell = (ParameterEditCell) cell("#pricingPromotionTableOne", storeCountIndex, 5, robot);
        robot.doubleClickOn(confidencePerCell);
        robot.doubleClickOn(confidencePerCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(50, ((Parameter)pricingPromotionTableOne.getItems().get(storeCountIndex)).getMay(), "Store Count should now be 50 all the way to december");
        Assertions.assertEquals(50, ((Parameter)pricingPromotionTableOne.getItems().get(storeCountIndex)).getDecember(), "Store Count should now be 50 all the way to december");

        ParameterEditCell slottingCell = (ParameterEditCell) cell("#pricingPromotionTableOne", storeCountIndex, 7, robot);
        robot.doubleClickOn(slottingCell);
        robot.doubleClickOn(slottingCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0, KeyCode.DIGIT0,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(50, ((Parameter)pricingPromotionTableOne.getItems().get(storeCountIndex)).getMay(), "Store Count should still be 50 here");
        Assertions.assertEquals(5000, ((Parameter)pricingPromotionTableOne.getItems().get(storeCountIndex)).getJuly(), "Store Count should be 5000 here");
        Assertions.assertEquals(5000, ((Parameter)pricingPromotionTableOne.getItems().get(storeCountIndex)).getDecember(), "Store Count should be 5000 all the way to december");
    }
    @Test
    public void testGetSlottingGameTheoryd(){
        Assertions.assertEquals(new BigDecimal("3500.0000"), controller.getSlottingGameTheoryd(7));
        Assertions.assertEquals(new BigDecimal("0.00"), controller.getSlottingGameTheoryd(6));
    }
    @Test
    public void testGetEverydayCost(){
        Assertions.assertEquals(new BigDecimal("3.894"), controller.getEverydayCost(9));
    }
    @Test
    public void testGetPromoCost(){
        Assertions.assertEquals(new BigDecimal("3.594"), controller.getPromoCost(7,1));
        Assertions.assertEquals(new BigDecimal("0.00"), controller.getPromoCost(7,2));
    }
    @Test
    public void testGetEverydayRetailWeeks(){
        Assertions.assertEquals(4, controller.getEverydayRetailWeeks(2));
        Assertions.assertEquals(1, controller.getEverydayRetailWeeks(1));
    }
    @Test
    public void testGetEverydayVolume(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("0.000"), controller.getEverydayVolume(6));
        Assertions.assertEquals(new BigDecimal("711.000"), controller.getEverydayVolume(7));
    }
    @Test
    public void testGetPromoVolume(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5925.0000"), controller.getPromoVolume(1,1));
        Assertions.assertEquals(new BigDecimal("0.0000"), controller.getPromoVolume(3,1 ));
        Assertions.assertEquals(new BigDecimal("7110.0000"), controller.getPromoVolume(7,1 ));
        Assertions.assertEquals(new BigDecimal("0.0000"), controller.getPromoVolume(7,2 ));
    }
    @Test
    public void testGetTotalVolume(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5925.0000"), controller.getTotalVolume(6));
        Assertions.assertEquals(new BigDecimal("7821.0000"), controller.getTotalVolume(7 ));
        Assertions.assertEquals(new BigDecimal("7110.0000"), controller.getTotalVolume(8 ));
    }
    @Test
    public void testGetPromoDiscount(FxRobot robot){
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getPromoDiscount(5,1));
        Assertions.assertEquals(new BigDecimal("7.7000"), controller.getPromoDiscount(6,1 ));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getPromoDiscount(6,2 ));
    }
    @Test
    public void testGetRetailerGrossSales(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("39336.075000"), controller.getRetailerGrossSales(1));
        Assertions.assertEquals(new BigDecimal("15381.300000"), controller.getRetailerGrossSales(2));
        Assertions.assertEquals(new BigDecimal("42588.900000"), controller.getRetailerGrossSales(8));
    }
    @Test
    public void testGetRetailerNetCost(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("9228.7800000"), controller.getRetailerNetCost(3));
        Assertions.assertEquals(new BigDecimal("11535.9750000"), controller.getRetailerNetCost(4));
        Assertions.assertEquals(new BigDecimal("23601.6450000"), controller.getRetailerNetCost(1));
    }
    @Test
    public void testGetRetailerGrossProfit(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("17035.5600000"), controller.getRetailerGrossProfit(8));
        Assertions.assertEquals(new BigDecimal("9228.7800000"), controller.getRetailerGrossProfit(10));
        Assertions.assertEquals(new BigDecimal("17035.5600000"), controller.getRetailerGrossProfit(12));
    }
    @Test
    public void testGetManufacturerGrossSalesList(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("23397.825000"), controller.getManufacturerGrossSalesList(1));
        Assertions.assertEquals(new BigDecimal("8508.300000"), controller.getManufacturerGrossSalesList(2));
        Assertions.assertEquals(new BigDecimal("10635.375000"), controller.getManufacturerGrossSalesList(4));
    }
    @Test
    public void testGetManufacturerGrossSalesActual(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("21507.750000"), controller.getManufacturerGrossSalesActual(1));
        Assertions.assertEquals(new BigDecimal("7821.000000"), controller.getManufacturerGrossSalesActual(2));
        Assertions.assertEquals(new BigDecimal("9776.250000"), controller.getManufacturerGrossSalesActual(4));
    }
    @Test
    public void testGetFobDiscounts(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1890.075000"), controller.getFobDiscounts(1));
        Assertions.assertEquals(new BigDecimal("687.300000"), controller.getFobDiscounts(2));
        Assertions.assertEquals(new BigDecimal("859.125000"), controller.getFobDiscounts(4));
    }
    @Test
    public void testGetSpoilsFeesTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("645.23250000"), controller.getSpoilsFeesTS(1));
        Assertions.assertEquals(new BigDecimal("234.63000000"), controller.getSpoilsFeesTS(2));
        Assertions.assertEquals(new BigDecimal("293.28750000"), controller.getSpoilsFeesTS(4));
    }
    @Test
    public void testGetEverydayAllowanceTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1499.025000"), controller.getEverydayAllowanceTS(1));
        Assertions.assertEquals(new BigDecimal("545.100000"), controller.getEverydayAllowanceTS(2));
        Assertions.assertEquals(new BigDecimal("681.375000"), controller.getEverydayAllowanceTS(4));
    }
    @Test
    public void testGetPromoTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1777.5000000"), controller.getPromoTS(1,1));
        Assertions.assertEquals(new BigDecimal("0.0000000"), controller.getPromoTS(2,1));
        Assertions.assertEquals(new BigDecimal("2133.0000000"), controller.getPromoTS(7,1));
        Assertions.assertEquals(new BigDecimal("0.0000000"), controller.getPromoTS(7,2));
    }
    @Test
    public void testFixedCostsTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("500.0"), controller.getFixedCostsTS(1));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getFixedCostsTS(2));
    }
    @Test
    public void testGetTotalTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("4421.76"), controller.getTotalTS(1));
        Assertions.assertEquals(new BigDecimal("779.73"), controller.getTotalTS(2));
        Assertions.assertEquals(new BigDecimal("974.66"), controller.getTotalTS(4));
    }
    @Test
    public void testGetManufacturerNet1Rev(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("18976.06750000"), controller.getManufacturerNet1Rev(1));
        Assertions.assertEquals(new BigDecimal("7728.57000000"), controller.getManufacturerNet1Rev(2));
        Assertions.assertEquals(new BigDecimal("9660.71250000"), controller.getManufacturerNet1Rev(4));
    }
    @Test
    public void testGetManufacturerFreightCost(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1890.075000"), controller.getManufacturerFreightCost(1));
        Assertions.assertEquals(new BigDecimal("687.300000"), controller.getManufacturerFreightCost(2));
        Assertions.assertEquals(new BigDecimal("859.125000"), controller.getManufacturerFreightCost(4));
    }
    @Test
    public void testGetManufacturerNet2Rev(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("17085.990000"), controller.getManufacturerNet2Rev(1));
        Assertions.assertEquals(new BigDecimal("7041.270000"), controller.getManufacturerNet2Rev(2));
        Assertions.assertEquals(new BigDecimal("8801.590000"), controller.getManufacturerNet2Rev(4));
    }
    @Test
    public void testGetManufacturerNet3Rev(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("17085.99250000"), controller.getManufacturerNet3Rev(1));
        Assertions.assertEquals(new BigDecimal("7041.27000000"), controller.getManufacturerNet3Rev(2));
        Assertions.assertEquals(new BigDecimal("17103.19100000"), controller.getManufacturerNet3Rev(7));
    }
    @Test
    public void testGetManufacturerCogs(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("13360.875000"), controller.getManufacturerCogs(1));
        Assertions.assertEquals(new BigDecimal("4858.500000"), controller.getManufacturerCogs(2));
        Assertions.assertEquals(new BigDecimal("6073.125000"), controller.getManufacturerCogs(4));
    }
    @Test
    public void testGetManufacturerGrossProfit(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("3725.11750000"), controller.getManufacturerGrossProfit(1));
        Assertions.assertEquals(new BigDecimal("2182.77000000"), controller.getManufacturerGrossProfit(2));
        Assertions.assertEquals(new BigDecimal("2728.46250000"), controller.getManufacturerGrossProfit(4));
    }
    @Test
    public void testPromoLiftMultiple(FxRobot robot){
        Assertions.assertEquals(new BigDecimal("2.5"), controller.getPromoLiftMultiple(1,1));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getPromoLiftMultiple(2,1));
        Assertions.assertEquals(new BigDecimal("2.5"), controller.getPromoLiftMultiple(7,1));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getPromoLiftMultiple(7,2));
    }




    

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