package com.traderoute;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class firstTableControllerTest {
    private ObservableList<RTMOption> rtmOptions;
    private SimpleObjectProperty<Retailer> retailer= new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),firstTableController.getRetailerProducts().get(0) ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));;


    @Start
    public void start(Stage stage) throws IOException {
//        System.out.println(getFXMLLoader("secondTable").getController());
        FXMLLoader fxmlLoader = App.createFXMLLoader("secondTable");
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println(fxmlLoader.getController().toString());
        firstTableController controller = fxmlLoader.getController();
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
        TableView firstTableView = robot.lookup("#firstTableView").queryTableView();
        robot.interact(() -> {
            robot.doubleClickOn(cell("#firstTableView", 2, 2, robot));
//            TextField textField = robot.lookup("editor2").queryAs(TextField.class);
//            robot.clickOn(textField);
            robot.type(KeyCode.DIGIT0).release(KeyCode.DIGIT0).type(KeyCode.PERIOD).release(KeyCode.PERIOD).type(KeyCode.DIGIT7).release(KeyCode.DIGIT7);
            robot.write("100000000000");
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        });
        Assert.assertEquals(((RTMOption)firstTableView.getItems().get(2)).getFreightOutPerUnit(),new BigDecimal("0.75"));

    }


    @Test
    public void goToPricingPromotion(FxRobot robot) {
        Button pricingPromotionButton = robot.lookup("#pricingPromotionButton").queryButton();
        robot.interact(() -> {
            robot.clickOn(pricingPromotionButton);
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