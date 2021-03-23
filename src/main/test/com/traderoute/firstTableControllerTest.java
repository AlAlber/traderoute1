package com.traderoute;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private firstTableController controller;
    private ObservableList<RTMOption> rtmOptions;
    private SimpleObjectProperty<Retailer> retailer= new SimpleObjectProperty<>(new Retailer("ahold", firstTableController.getRetailerProducts(),firstTableController.getRetailerProducts().get(0) ,  new BigDecimal("40") , 158,new BigDecimal("3.0")));;
    TableView firstTableView;
    TextField everydayGpmField;
    TextField yearOneStoreCountField;
    TextField weeklyUfswAtMinField;
    ComboBox<Product> productClassBox;
    ComboBox<Product> brandNameBox;


    @Start
    public void start(Stage stage) throws IOException {
//        System.out.println(getFXMLLoader("secondTable").getController());
        FXMLLoader fxmlLoader = App.createFXMLLoader("secondTable");
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
        firstTableView = robot.lookup("#firstTableView").queryTableView();
        everydayGpmField = robot.lookup("#everydayGpmField").queryAs(TextField.class);
        yearOneStoreCountField = robot.lookup("#yearOneStoreCountField").queryAs(TextField.class);
        weeklyUfswAtMinField = robot.lookup("#weeklyUfswAtMinField").queryAs(TextField.class);
        productClassBox = robot.lookup("#productClassBox").queryComboBox();
        brandNameBox = robot.lookup("#brandNameBox").queryComboBox();


    }

    @Test
    public void testFirstReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 3, 3, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getFirstReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testSecondReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 3, 4, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getSecondReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testThirdReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 3, 5, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getThirdReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }
    @Test
    public void testFourthReceiverUpdatesLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 3, 6, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getFourthReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));
    }

    @Test
    public void testReceiversCorrectlyPickMaxLandedStoreCost(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 3, 6, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getFourthReceiver(),new BigDecimal("0.7"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("0.7"));

        robot.doubleClickOn(cell("#firstTableView", 3, 5, robot));
        robot.write("1.1");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getThirdReceiver(),new BigDecimal("1.1"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("1.1"));

        robot.doubleClickOn(cell("#firstTableView", 3, 3, robot));
        robot.write("0.9");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getFirstReceiver(),new BigDecimal("0.9"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("1.1"));

        robot.doubleClickOn(cell("#firstTableView", 3, 4, robot));
        robot.write("5.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getSecondReceiver(),new BigDecimal("5.0"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("5.0"));
    }

    @Test
    public void testMaxReceiverAndEverydayGpmUpdateEverydayRetails(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 3, 3, robot));
        robot.write("3.59");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getFirstReceiver(),new BigDecimal("3.59"));
        Assertions.assertEquals(((RTMOption)firstTableView.getItems().get(3)).getLandedStoreCost(),new BigDecimal("3.59"));

        robot.doubleClickOn(everydayGpmField);
        robot.write("40.0");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals(new BigDecimal("5.9833333333"),((RTMOption)firstTableView.getItems().get(3)).getResultingEverydayRetailCalcd());
        Assertions.assertEquals(new BigDecimal("5.9833333333"), ((RTMOption)firstTableView.getItems().get(3)).getResultingEverydayRetailOverride());
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
        Assertions.assertEquals("List = $3.59", listLabel.getText());
        Assertions.assertEquals("F.O.B. = $3.30", fobLabel.getText());
        Assertions.assertEquals("Net 1 Goal = $2.99", net1GoalLabel.getText());
        Assertions.assertEquals("Elasticity Ratio = +1% Price :-1.15% Volume", elasticityRatioLabel.getText());
    }




    @Test
    public void testChangeWeeklyVelocity(FxRobot robot) {
        robot.interact(() -> {
            brandNameBox.getSelectionModel().select(0);
            productClassBox.getSelectionModel().select(0);
            yearOneStoreCountField.setText("158");
            everydayGpmField.setText("40.0");
            yearOneStoreCountField.setText("3.0");
        });
    }

    @Test
    public void testInputtingOnTableView(FxRobot robot){
        robot.doubleClickOn(cell("#firstTableView", 2, 2, robot));
        robot.write("0.7");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assert.assertEquals(((RTMOption)firstTableView.getItems().get(2)).getFreightOutPerUnit(),new BigDecimal("0.7"));
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