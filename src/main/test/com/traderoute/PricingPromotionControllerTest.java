package com.traderoute;

//import com.traderoute.cells.PromoRowEditCell;
import com.traderoute.cells.PromoRowEditCell1;
import com.traderoute.controllers.MenuController;
import com.traderoute.controllers.PricingPromotionController;
import com.traderoute.data.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.math.BigDecimal;

import static org.loadui.testfx.GuiTest.find;
//import static org.loadui.testfx.controls.TableViews.cell;
//import static org.loadui.testfx.controls.TableViews.row;

@ExtendWith(ApplicationExtension.class)
public class PricingPromotionControllerTest extends TestBaseClass {

//    public static GuiTest controller;

    private ObservableList<RTMOption> rtmOptions;
    private SimpleObjectProperty<Retailer> retailer; // new SimpleObjectProperty<>(new Retailer("ahold", RTMPlanningController.getRetailerProducts(),0 ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));;
    private TableView<PromoRow<?>> promoTable;

    private static final int skusInDistributionIndex = 0;
    private static final int skuCountChangeIndex = 1;
    private static final int confidencePerIndex = 2;
    private static final int slottingInvestmentIndex = 3;
    private static final int storeCountIndex = 4;
    private static final int everydayRetailIndex = 6;
    private static final int everydayUnitCostIndex = 7;
    private static final int seasonalityIndicesIndex = 9;
    private static final int promotedRetail1Index = 10;
    private static final int requiredGpm1Index = 11;
    private static final int durationWeeks1Index = 12;
    private static final int volumeLiftMultiple1Index = 13;
    private static final int fixedCosts1Index = 14;
    private static final int promoUnitCost1Index = 15;
    private static final int promoDiscount1Index = 16;
    private static final int promotionalCommentaryIndex = 17;
    private static final int promotedRetail2Index = 18;
    private static final int requiredGpm2Index = 19;
    private static final int durationWeeks2Index = 20;
    private static final int volumeLiftMultiple2Index = 21;
    private static final int fixedCosts2Index = 22;
    private static final int promoUnitCost2Index = 23;
    private static final int promoDiscount2Index = 24;
    private static final int totalVolumeIndex = 25;
    private static final int grossProfitPlanIndex = 26;

    private PricingPromotionController controller;

    private String promoTableString = "#promoTable";

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = App.createFXMLLoader("pricingPromotion");
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.show();

    }

    @org.junit.jupiter.api.BeforeEach
    void setUp(FxRobot robot) {
        retailer = new SimpleObjectProperty<>(new Retailer("ahold", MenuController.getRetailerProducts(),
                0, new BigDecimal("40"), 158, new BigDecimal("3.0")));
//        controller.setRetailer(retailer.get());
        rtmOptions = retailer.get().getRetailerProducts().get(0).getRtmOptions();
        promoTable = robot.lookup(promoTableString).queryTableView();
//        controller.getPromoPlans().get(0).setSelectedRtm(retailer.get().getRetailerProducts().get(0).getRtmOptions().get(1));
//        controller.setCurrentPromoPlanIndex(0);
    }

    public void checkBDPromoCell(int row, int col, String input,String expected, String tableString, FxRobot robot){
        TableView table = robot.lookup(tableString).queryTableView();
        TableCell tableCell = cell(tableString, row, col, robot);
        robot.interact(()->((BigDecimalPromoRow)table.getItems().
                get(row)).setMonth(col,new BigDecimal(input)));
        Assertions.assertEquals(expected,tableCell.getText());
    }
    public void checkIntPromoCell(int row, int col, String input,String expected,String tableString, FxRobot robot){
        TableView table = robot.lookup(tableString).queryTableView();
        TableCell tableCell = cell(tableString, row, col, robot);
        robot.interact(()->((IntegerPromoRow)table.getItems().
                get(row)).setMonth(col, Integer.valueOf(input)));
        Assertions.assertEquals(expected,tableCell.getText());
    }
    @Test
    public void testSkuInDistributionCellHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(0,1,"30", "30.00", promoTableString,robot);
    }

    @Test
    public void testSkuCountChangeCellHasNoPrePost(FxRobot robot) {
        checkIntPromoCell(1,1,"30", "30", promoTableString,robot);
    }
    @Test
    public void testConfidencePerCellHasPercent(FxRobot robot) {
        checkBDPromoCell(2,1,"30", "30.00%", promoTableString,robot);
    }
    @Test
    public void testSlottingInvestMentCellHasDollar(FxRobot robot) {
        checkBDPromoCell(3,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testStoreCountCellHasNoPrePost(FxRobot robot) {
        checkIntPromoCell(4,1,"30", "30", promoTableString,robot);
    }
    @Test
    public void testEverydayRetailCellHasDollar(FxRobot robot) {
        checkBDPromoCell(6,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testEverydayUnitCostCellHasDollar(FxRobot robot) {
        checkBDPromoCell(7,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testSeasonalityIndicesCellHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(9,1,"30", "30.00", promoTableString,robot);
    }
    @Test
    public void testPromotedRetail1CellHasDollar(FxRobot robot) {
        checkBDPromoCell(10,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testRequiredGpm1CellHasPercent(FxRobot robot) {
        checkBDPromoCell(11,1,"30", "30.00%", promoTableString,robot);
    }
    @Test
    public void testDurationWeeks1CellHasNoPrePost(FxRobot robot) {
        checkIntPromoCell(12,1,"4", "4", promoTableString,robot);
    }
    @Test
    public void testVolumeLiftMultiple1CellHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(13,1,"30", "30.00", promoTableString,robot);
    }
    @Test
    public void testFixedCosts1CellHasDollar(FxRobot robot) {
        checkBDPromoCell(14,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testPromoUnitCost1CellHasDollar(FxRobot robot) {
        checkBDPromoCell(15,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testPromoDiscount1HasNoPercent(FxRobot robot) {
        checkBDPromoCell(16,1,"30", "30.00%", promoTableString,robot);
    }
    @Test
    public void testPromotionalCommentaryHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(17,1,"30", "30", promoTableString,robot);
    }
    @Test
    public void testPromotedRetail2CellHasDollar(FxRobot robot) {
        checkBDPromoCell(18,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testRequiredGpm2CellHasPercent(FxRobot robot) {
        checkBDPromoCell(19,1,"30", "30.00%", promoTableString,robot);
    }
    //TODO TEST FOR HIGHER VALUES
    @Test
    public void testDurationWeeks2CellHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(20,1,"4", "4", promoTableString,robot);
    }
    @Test
    public void testVolumeLiftMultiple2CellHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(21,1,"30", "30.00", promoTableString,robot);
    }
    @Test
    public void testFixedCosts2CellHasDollar(FxRobot robot) {
        checkBDPromoCell(22,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testPromoUnitCost2CellHasNoDollar(FxRobot robot) {
        checkBDPromoCell(23,1,"30", "$30.00", promoTableString,robot);
    }
    @Test
    public void testPromoDiscount2HasPercent(FxRobot robot) {
        checkBDPromoCell(24,1,"30", "30.00%", promoTableString,robot);
    }
    @Test
    public void testTotalVolumeCellHasNoPrePost(FxRobot robot) {
        checkBDPromoCell(25,1,"30", "30", promoTableString,robot);
    }
    @Test
    public void testGrossProfitCellHasDollar(FxRobot robot) {
        checkBDPromoCell(26,1,"30", "$30", promoTableString,robot);
    }





//    @Test
//    public void test
//    @Test
//    public void testElasticizedUnitVelocityCellStartsWithDollar(FxRobot robot) {
//        robot.interact(()-> testRTMOptionCell(3).setElasticizedUnitVelocity(new BigDecimal("30.0")));
//        Assertions.assertEquals("30.00 U/F/S/W", cell(table2String,3,1, robot).getText());
//    }

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
        Assertions.assertEquals("0.0",textField2.getText());
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

        Assert.assertEquals(((PromoRow) promoTable.getItems().get(2)).getFebruary(),new BigDecimal("0.75"));

    }

    @Test
    public void testSkusInPromotionEdit(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();

        robot.doubleClickOn(cell("#pricingPromotionTableOne", 0, 1, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 0, 1, robot));
        robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        // Test that all values before sku count change in july are equal to the input
        Assertions.assertEquals(new BigDecimal("7.0"), ((PromoRow)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july should be equal to input");
        Assertions.assertEquals(new BigDecimal("7.0"), ((PromoRow)pricingPromotionTableOne.getItems().get(0)).getFebruary(), "Values before sku count change in july should be equal to input");
        // Values after July should be 1 more because thats where default parameters have sku count change
        Assertions.assertEquals(new BigDecimal("8.0"), ((PromoRow)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values before sku count change in july should be equal to input");
        Assertions.assertEquals(new BigDecimal("8.0"), ((PromoRow)pricingPromotionTableOne.getItems().get(0)).getDecember(), "Values before sku count change in july should be equal to input");
    }

    @Test
    public void testAddingOnlySkuCountChangeDoesNothing(FxRobot robot) {
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 4, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 4, robot));
        robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("5.00"), ((PromoRow) promoTable.getItems().get(0)).getJune(), "Values before sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("6.00"), ((PromoRow) promoTable.getItems().get(0)).getJuly(), "Values after sku count change in july shouldn't have changed");
    }

    @Test
    public void testAddingOnlyConfidencePerDoesNothing(FxRobot robot) {
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 3, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 3, robot));
        robot.type(KeyCode.DIGIT7);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("5.00"), ((PromoRow) promoTable.getItems().get(0)).getJune(), "Values before sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("6.00"), ((PromoRow) promoTable.getItems().get(0)).getJuly(), "Values after sku count change in july shouldn't have changed");
    }
    @Test
    public void testChangingEverydayRetail(FxRobot robot) {
        ((BigDecimalPromoRow) promoTable.getItems().get(everydayRetailIndex)).setMonth(3, new BigDecimal("6.00"));
        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.60"), ((PromoRow) promoTable.getItems().get(everydayUnitCostIndex)).getMarch(), "Values before sku count change in july shouldn't have changed");
        // CHECK WITH JOHN IF WE EVEN NEED TO TEST THIS
    }

    @Test
    public void testAddingSkuCountChangeAndConfidencePer(FxRobot robot) {
        PromoRowEditCell1 cell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 2, 5, robot);
        robot.doubleClickOn(cell);
        robot.doubleClickOn(cell);

        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 5, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 5, robot));
        robot.type(KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("5.0"), ((PromoRow) promoTable.getItems().get(0)).getApril(), "Values before first sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("7.50"), ((PromoRow) promoTable.getItems().get(0)).getMay(), "Values after first sku count change in july should have increased by 2.5");
        Assertions.assertEquals(new BigDecimal("8.50"), ((PromoRow) promoTable.getItems().get(0)).getJuly(), "Values after first sku count change in july should have increased by 2.5");
    }
    @Test
    public void testPromo1UnitCostChange(FxRobot robot) {
        PromoRowEditCell1 promotedRetailCell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 10, 9, robot);
        robot.doubleClickOn(promotedRetailCell);
        robot.doubleClickOn(promotedRetailCell);

        robot.type(KeyCode.DIGIT5,KeyCode.PERIOD,KeyCode.DIGIT9, KeyCode.DIGIT9);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        PromoRowEditCell1 requiredGpmCell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 11, 9, robot);
        robot.doubleClickOn(requiredGpmCell);
        robot.doubleClickOn(requiredGpmCell);
        robot.type(KeyCode.DIGIT4, KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.594"), ((PromoRow) promoTable.getItems().get(15)).getSeptember(), "Promo Unit cost should have changed to 3.59");
        Assertions.assertEquals(new BigDecimal("7.7000"), ((PromoRow) promoTable.getItems().get(16)).getSeptember(), "Promo Discount Percentage should have changed to 7.70");
    }
    @Test
    public void testPromo2UnitCostChange(FxRobot robot) {
        PromoRowEditCell1 promotedRetail2Cell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 18, 10, robot);
        robot.doubleClickOn(promotedRetail2Cell);
        robot.doubleClickOn(promotedRetail2Cell);

        robot.type(KeyCode.DIGIT5,KeyCode.PERIOD,KeyCode.DIGIT9, KeyCode.DIGIT9);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        PromoRowEditCell1 requiredGpm2Cell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 19, 10, robot);
        robot.doubleClickOn(requiredGpm2Cell);
        robot.doubleClickOn(requiredGpm2Cell);
        robot.type(KeyCode.DIGIT4, KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.594"), ((PromoRow) promoTable.getItems().get(23)).getOctober(), "Promo Unit cost should have changed to 3.59");
        Assertions.assertEquals(new BigDecimal("7.7000"), ((PromoRow) promoTable.getItems().get(24)).getOctober(), "Promo Discount Percentage should have changed to 7.70");
    }
    @Test
    public void testSlottingChangesGrossProfitPlan(FxRobot robot) {
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        ComboBox rtmBox0 =robot.lookup("#rtmBox0").queryAs(ComboBox.class);
        robot.interact(() -> {
//            System.out.println(rtmBox.getSelectionModel().getSelectedItem());]
            rtmBox0.getSelectionModel().select(1);

        });
        PromoRowEditCell1 confidencePerCell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 2, 5, robot);
        robot.doubleClickOn(confidencePerCell);
        robot.doubleClickOn(confidencePerCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        PromoRowEditCell1 slottingCell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", 3, 5, robot);
        robot.doubleClickOn(slottingCell);
        robot.doubleClickOn(slottingCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0, KeyCode.DIGIT0,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);



        Assertions.assertEquals(new BigDecimal("-317"), ((PromoRow) promoTable.getItems().get(26)).getMay(), "Gross Profit Plan should have changed to -317");
    }
    @Test
    public void testChangingStoreCount(FxRobot robot){
        Assertions.assertEquals(158, ((PromoRow) promoTable.getItems().get(storeCountIndex)).getMay(), "Store Count should be 158 initially");

        PromoRowEditCell1 confidencePerCell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", storeCountIndex, 5, robot);
        robot.doubleClickOn(confidencePerCell);
        robot.doubleClickOn(confidencePerCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(50, ((PromoRow) promoTable.getItems().get(storeCountIndex)).getMay(), "Store Count should now be 50 all the way to december");
        Assertions.assertEquals(50, ((PromoRow) promoTable.getItems().get(storeCountIndex)).getDecember(), "Store Count should now be 50 all the way to december");

        PromoRowEditCell1 slottingCell = (PromoRowEditCell1) cell("#pricingPromotionTableOne", storeCountIndex, 7, robot);
        robot.doubleClickOn(slottingCell);
        robot.doubleClickOn(slottingCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0, KeyCode.DIGIT0,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(50, ((PromoRow) promoTable.getItems().get(storeCountIndex)).getMay(), "Store Count should still be 50 here");
        Assertions.assertEquals(5000, ((PromoRow) promoTable.getItems().get(storeCountIndex)).getJuly(), "Store Count should be 5000 here");
        Assertions.assertEquals(5000, ((PromoRow) promoTable.getItems().get(storeCountIndex)).getDecember(), "Store Count should be 5000 all the way to december");
    }
    @Test
    public void testGetSlottingGameTheoryd(){
        Assertions.assertEquals(new BigDecimal("3500.0000"), controller.getCurrentPromoPlan().getSlottingGameTheoryd(7));
        Assertions.assertEquals(new BigDecimal("0.00"), controller.getCurrentPromoPlan().getSlottingGameTheoryd(6));
    }
    @Test
    public void testGetEverydayCost(){
        Assertions.assertEquals(new BigDecimal("3.894"), controller.getCurrentPromoPlan().getEverydayCost(9));
    }
    @Test
    public void testGetPromoCost(){
        Assertions.assertEquals(new BigDecimal("3.594"), controller.getCurrentPromoPlan().getPromoCost(7,1));
        Assertions.assertEquals(new BigDecimal("0.00"), controller.getCurrentPromoPlan().getPromoCost(7,2));
    }
    @Test
    public void testGetEverydayRetailWeeks(){
        Assertions.assertEquals(4, controller.getCurrentPromoPlan().getEverydayRetailWeeks(2));
        Assertions.assertEquals(1, controller.getCurrentPromoPlan().getEverydayRetailWeeks(1));
    }
    @Test
    public void testGetEverydayVolume(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("0.000"), controller.getCurrentPromoPlan().getEverydayVolume(6));
        Assertions.assertEquals(new BigDecimal("711.000"), controller.getCurrentPromoPlan().getEverydayVolume(7));
    }
    @Test
    public void testGetPromoVolume(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5925.0000"), controller.getCurrentPromoPlan().getPromoVolume(1,1));
        Assertions.assertEquals(new BigDecimal("0.0000"), controller.getCurrentPromoPlan().getPromoVolume(3,1 ));
        Assertions.assertEquals(new BigDecimal("7110.0000"), controller.getCurrentPromoPlan().getPromoVolume(7,1 ));
        Assertions.assertEquals(new BigDecimal("0.0000"), controller.getCurrentPromoPlan().getPromoVolume(7,2 ));
    }
    @Test
    public void testGetTotalVolume(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5925.0000"), controller.getCurrentPromoPlan().getTotalVolume(6));
        Assertions.assertEquals(new BigDecimal("7821.0000"), controller.getCurrentPromoPlan().getTotalVolume(7 ));
        Assertions.assertEquals(new BigDecimal("7110.0000"), controller.getCurrentPromoPlan().getTotalVolume(8 ));
    }
    @Test
    public void testGetPromoDiscount(FxRobot robot){
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getCurrentPromoPlan().getPromoDiscount(5,1));
        Assertions.assertEquals(new BigDecimal("7.7000"), controller.getCurrentPromoPlan().getPromoDiscount(6,1 ));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getCurrentPromoPlan().getPromoDiscount(6,2 ));
    }
    @Test
    public void testGetRetailerGrossSales(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("39336.075000"), controller.getCurrentPromoPlan().getRetailerGrossSales(1));
        Assertions.assertEquals(new BigDecimal("15381.30000"), controller.getCurrentPromoPlan().getRetailerGrossSales(2));
        Assertions.assertEquals(new BigDecimal("42588.900000"), controller.getCurrentPromoPlan().getRetailerGrossSales(8));
    }
    @Test
    public void testGetRetailerNetCost(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("9228.780000"), controller.getCurrentPromoPlan().getRetailerNetCost(3));
        Assertions.assertEquals(new BigDecimal("11535.975000"), controller.getCurrentPromoPlan().getRetailerNetCost(4));
        Assertions.assertEquals(new BigDecimal("23601.6450000"), controller.getCurrentPromoPlan().getRetailerNetCost(1));
    }
    @Test
    public void testGetRetailerGrossProfit(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("17035.5600000"), controller.getCurrentPromoPlan().getRetailerGrossProfit(8));
        Assertions.assertEquals(new BigDecimal("9228.780000"), controller.getCurrentPromoPlan().getRetailerGrossProfit(10));
        Assertions.assertEquals(new BigDecimal("17035.5600000"), controller.getCurrentPromoPlan().getRetailerGrossProfit(12));
    }
    @Test
    public void testGetManufacturerGrossSalesList(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("23397.825000"), controller.getCurrentPromoPlan().getManufacturerGrossSalesList(1));
        Assertions.assertEquals(new BigDecimal("8508.300000"), controller.getCurrentPromoPlan().getManufacturerGrossSalesList(2));
        Assertions.assertEquals(new BigDecimal("10635.375000"), controller.getCurrentPromoPlan().getManufacturerGrossSalesList(4));
    }
    @Test
    public void testGetManufacturerGrossSalesActual(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("21507.750000"), controller.getCurrentPromoPlan().getManufacturerGrossSalesActual(1));
        Assertions.assertEquals(new BigDecimal("7821.000000"), controller.getCurrentPromoPlan().getManufacturerGrossSalesActual(2));
        Assertions.assertEquals(new BigDecimal("9776.250000"), controller.getCurrentPromoPlan().getManufacturerGrossSalesActual(4));
    }
    @Test
    public void testGetFobDiscounts(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1890.075000"), controller.getCurrentPromoPlan().getFobDiscounts(1));
        Assertions.assertEquals(new BigDecimal("687.300000"), controller.getCurrentPromoPlan().getFobDiscounts(2));
        Assertions.assertEquals(new BigDecimal("859.125000"), controller.getCurrentPromoPlan().getFobDiscounts(4));
    }
    @Test
    public void testGetSpoilsFeesTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("645.23250000"), controller.getCurrentPromoPlan().getSpoilsFeesTS(1));
        Assertions.assertEquals(new BigDecimal("234.63000000"), controller.getCurrentPromoPlan().getSpoilsFeesTS(2));
        Assertions.assertEquals(new BigDecimal("293.28750000"), controller.getCurrentPromoPlan().getSpoilsFeesTS(4));
    }
    @Test
    public void testGetEverydayAllowanceTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1499.025000"), controller.getCurrentPromoPlan().getEverydayAllowanceTS(1));
        Assertions.assertEquals(new BigDecimal("545.100000"), controller.getCurrentPromoPlan().getEverydayAllowanceTS(2));
        Assertions.assertEquals(new BigDecimal("681.375000"), controller.getCurrentPromoPlan().getEverydayAllowanceTS(4));
    }
    @Test
    public void testGetPromoTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1777.5000000"), controller.getCurrentPromoPlan().getPromoTS(1,1));
        Assertions.assertEquals(new BigDecimal("0.0000000"), controller.getCurrentPromoPlan().getPromoTS(2,1));
        Assertions.assertEquals(new BigDecimal("2133.0000000"), controller.getCurrentPromoPlan().getPromoTS(7,1));
        Assertions.assertEquals(new BigDecimal("0.0000000"), controller.getCurrentPromoPlan().getPromoTS(7,2));
    }
    @Test
    public void testFixedCostsTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("500.0"), controller.getCurrentPromoPlan().getFixedCostsTS(1));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getCurrentPromoPlan().getFixedCostsTS(2));
    }
    @Test
    public void testGetTotalTS(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("4421.75750000"), controller.getCurrentPromoPlan().getTotalTS(1));
        Assertions.assertEquals(new BigDecimal("779.73000000"), controller.getCurrentPromoPlan().getTotalTS(2));
        Assertions.assertEquals(new BigDecimal("974.66250000"), controller.getCurrentPromoPlan().getTotalTS(4));
    }
    @Test
    public void testGetManufacturerNet1Rev(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("18976.06750000"), controller.getCurrentPromoPlan().getManufacturerNet1Rev(1));
        Assertions.assertEquals(new BigDecimal("7728.57000000"), controller.getCurrentPromoPlan().getManufacturerNet1Rev(2));
        Assertions.assertEquals(new BigDecimal("9660.71250000"), controller.getCurrentPromoPlan().getManufacturerNet1Rev(4));
    }
    @Test
    public void testGetManufacturerFreightCost(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("1890.075000"), controller.getCurrentPromoPlan().getManufacturerFreightCost(1));
        Assertions.assertEquals(new BigDecimal("687.300000"), controller.getCurrentPromoPlan().getManufacturerFreightCost(2));
        Assertions.assertEquals(new BigDecimal("859.125000"), controller.getCurrentPromoPlan().getManufacturerFreightCost(4));
    }
    @Test
    public void testGetManufacturerNet2Rev(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("17085.99250000"), controller.getCurrentPromoPlan().getManufacturerNet2Rev(1));
        Assertions.assertEquals(new BigDecimal("7041.27000000"), controller.getCurrentPromoPlan().getManufacturerNet2Rev(2));
        Assertions.assertEquals(new BigDecimal("8801.58750000"), controller.getCurrentPromoPlan().getManufacturerNet2Rev(4));
    }
    @Test
    public void testGetManufacturerNet3Rev(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("17085.99250000"), controller.getCurrentPromoPlan().getManufacturerNet3Rev(1));
        Assertions.assertEquals(new BigDecimal("7041.27000000"), controller.getCurrentPromoPlan().getManufacturerNet3Rev(2));
        Assertions.assertEquals(new BigDecimal("17103.19100000"), controller.getCurrentPromoPlan().getManufacturerNet3Rev(7));
    }
    @Test
    public void testGetManufacturerCogs(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("13360.875000"), controller.getCurrentPromoPlan().getManufacturerCogs(1));
        Assertions.assertEquals(new BigDecimal("4858.500000"), controller.getCurrentPromoPlan().getManufacturerCogs(2));
        Assertions.assertEquals(new BigDecimal("6073.125000"), controller.getCurrentPromoPlan().getManufacturerCogs(4));
    }
    @Test
    public void testGetManufacturerGrossProfit(FxRobot robot){
        TextField weeklyUFSW = robot.lookup("#weeklyVelocityField0").queryAs(TextField.class);
        robot.doubleClickOn(weeklyUFSW);
        robot.type(KeyCode.DIGIT0,KeyCode.PERIOD,KeyCode.DIGIT7,KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("3725.11750000"), controller.getCurrentPromoPlan().getManufacturerGrossProfit(1));
        Assertions.assertEquals(new BigDecimal("2182.77000000"), controller.getCurrentPromoPlan().getManufacturerGrossProfit(2));
        Assertions.assertEquals(new BigDecimal("2728.46250000"), controller.getCurrentPromoPlan().getManufacturerGrossProfit(4));
    }
    @Test
    public void testPromoLiftMultiple(FxRobot robot){
        Assertions.assertEquals(new BigDecimal("2.5"), controller.getCurrentPromoPlan().getPromoLiftMultiple(1,1));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getCurrentPromoPlan().getPromoLiftMultiple(2,1));
        Assertions.assertEquals(new BigDecimal("2.5"), controller.getCurrentPromoPlan().getPromoLiftMultiple(7,1));
        Assertions.assertEquals(new BigDecimal("0.0"), controller.getCurrentPromoPlan().getPromoLiftMultiple(7,2));
    }


}