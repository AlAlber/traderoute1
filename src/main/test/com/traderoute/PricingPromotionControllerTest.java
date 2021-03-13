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
        PricingPromotionController controller = fxmlLoader.getController();
        controller.setRetailer(retailer.get());
        stage.setScene(scene);
        stage.show();

    }
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
         rtmOptions = retailer.get().getRetailerProducts().get(0).getRtmOptions();


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
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();
        robot.interact(() -> {
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 2, robot));
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 2, robot));
            robot.type(KeyCode.DIGIT0, KeyCode.PERIOD, KeyCode.DIGIT7, KeyCode.DIGIT5); //.type(.type(KeyCode.DIGIT7).type();
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        });
        Assert.assertEquals(((Parameter)pricingPromotionTableOne.getItems().get(2)).getMarch(),new BigDecimal("0.75"));

    }


    @Test
    public void goToPricingPromotion(FxRobot robot) {
        Button pricingPromotionButton = robot.lookup("#pricingPromotionButton").queryButton();
        robot.interact(() -> {
            robot.clickOn(pricingPromotionButton);
        });
    }

    @Test
    public void testSkusInPromotionEdit(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();
        robot.interact(() -> {
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 0, 0, robot));
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 0, 0, robot));
            robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        });
        // Test that all values before sku count change in july are equal to the input
        Assertions.assertEquals(new BigDecimal("7.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july should be equal to input");
        Assertions.assertEquals(new BigDecimal("7.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getFebruary(), "Values before sku count change in july should be equal to input");
        // Values after July should be 1 more because thats where default parameters have sku count change
        Assertions.assertEquals(new BigDecimal("8.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values before sku count change in july should be equal to input");
        Assertions.assertEquals(new BigDecimal("8.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getDecember(), "Values before sku count change in july should be equal to input");
    }

    @Test
    public void testAddingOnlySkuCountChangeDoesNothing(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();
        robot.interact(() -> {
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 3, robot));
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 3, robot));
            robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        });
        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("5.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("6.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values after sku count change in july shouldn't have changed");
    }

    @Test
    public void testAddingOnlyConfidencePerDoesNothing(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();
        robot.interact(() -> {
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 3, robot));
            robot.doubleClickOn(cell("#pricingPromotionTableOne", 2, 3, robot));
            robot.type(KeyCode.DIGIT7); //.type(.type(KeyCode.DIGIT7).type();
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        });
        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("5.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJune(), "Values before sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("6.00"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values after sku count change in july shouldn't have changed");
    }

    @Test
    public void testAddingSkuCountChangeAndConfidencePer(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();

        ParameterEditCell cell = (ParameterEditCell) cell("#pricingPromotionTableOne", 2, 4, robot);
        robot.doubleClickOn(cell);
        robot.doubleClickOn(cell);

        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 4, robot));
        robot.doubleClickOn(cell("#pricingPromotionTableOne", 1, 4, robot));
        robot.type(KeyCode.DIGIT5);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        Assertions.assertEquals(new BigDecimal("5.0"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getApril(), "Values before first sku count change in july shouldn't have changed");
        Assertions.assertEquals(new BigDecimal("7.50"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getMay(), "Values after first sku count change in july should have increased by 2.5");
        Assertions.assertEquals(new BigDecimal("8.50"), ((Parameter)pricingPromotionTableOne.getItems().get(0)).getJuly(), "Values after first sku count change in july should have increased by 2.5");
    }
    @Test
    public void testPromoUnitCostChange(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();

        ParameterEditCell promotedRetailCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 10, 8, robot);
        robot.doubleClickOn(promotedRetailCell);
        robot.doubleClickOn(promotedRetailCell);

        robot.type(KeyCode.DIGIT5,KeyCode.PERIOD,KeyCode.DIGIT9, KeyCode.DIGIT9);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        ParameterEditCell requiredGpmCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 11, 8, robot);
        robot.doubleClickOn(requiredGpmCell);
        robot.doubleClickOn(requiredGpmCell);
        robot.type(KeyCode.DIGIT4, KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Check to see values havent changed yet
        Assertions.assertEquals(new BigDecimal("3.594"), ((Parameter)pricingPromotionTableOne.getItems().get(15)).getSeptember(), "Promo Unit cost should have changed to 3.59");
        Assertions.assertEquals(new BigDecimal("7.7000"), ((Parameter)pricingPromotionTableOne.getItems().get(16)).getSeptember(), "Promo Discount Percentage should have changed to 7.70");
    }
    @Test
    public void testSlottingChangesGrossProfitPlan(FxRobot robot) {
        TableView pricingPromotionTableOne = robot.lookup("#pricingPromotionTableOne").queryTableView();

        ParameterEditCell confidencePerCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 2, 4, robot);
        robot.doubleClickOn(confidencePerCell);
        robot.doubleClickOn(confidencePerCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        ParameterEditCell slottingCell = (ParameterEditCell) cell("#pricingPromotionTableOne", 3, 4, robot);
        robot.doubleClickOn(slottingCell);
        robot.doubleClickOn(slottingCell);
        robot.type(KeyCode.DIGIT5,KeyCode.DIGIT0, KeyCode.DIGIT0,KeyCode.DIGIT0);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("-2500"), ((Parameter)pricingPromotionTableOne.getItems().get(26)).getMay(), "Gross Profit Plan should have changed to -2500.00");
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