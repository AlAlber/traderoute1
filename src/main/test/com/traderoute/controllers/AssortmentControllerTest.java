package com.traderoute.controllers;

import com.traderoute.App;
import com.traderoute.TestBaseClass;
import com.traderoute.data.Meeting;
import com.traderoute.data.RTMOption;
import com.traderoute.data.Retailer;
import com.traderoute.data.Sku;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.Assert.*;

@ExtendWith(ApplicationExtension.class)
public class AssortmentControllerTest extends TestBaseClass {

    private FXMLLoader fxmlLoader;

    private AssortmentController controller;
    final String meetingTableString = "#meetingTable";
    final String skuTableString = "#skuTable";
    TableView<Meeting> meetingTable;
    TableView<Sku> skuTable;

    private TableColumn<Sku, String> statusColumn;

    private SimpleObjectProperty<Retailer> retailer;
    @Start
    public void start(Stage stage) throws IOException {
        fxmlLoader = App.createFXMLLoader("assortment");
        Scene scene = new Scene(fxmlLoader.load());
        App.setNewScene(scene);
        controller = fxmlLoader.getController();
//        controller.setRetailer(retailer.get());

        stage.setScene(App.getScene());
        stage.show();
    }
    @org.junit.jupiter.api.BeforeEach
    void setUp(FxRobot robot) {
        skuTable = robot.lookup(skuTableString).queryTableView();
        meetingTable = robot.lookup(meetingTableString).queryTableView();
        ObservableList<TableColumn<Sku, ?>> columns= skuTable.getColumns();
        statusColumn = (TableColumn<Sku, String>) columns.get(0);
    }

    @Test
    public void testGetFocusedMeeting(FxRobot robot){
        robot.clickOn(cell(meetingTableString, 0, 1, robot));
        Assertions.assertEquals(meetingTable.getItems().get(0), controller.getFocusedMeeting());
    }
    @Test
    public void testGetFocusedSku(FxRobot robot){
        robot.clickOn(cell(skuTableString, 0, 1, robot));
        Assertions.assertEquals(skuTable.getItems().get(0), controller.getFocusedSku());
    }
    @Test
    public void testChangeFlavorDescription(FxRobot robot){
        robot.doubleClickOn(cell(skuTableString, 0, 0, robot));
        robot.write("New Description").press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals("New Description",skuTable.getItems().get(0).getFlavorDescription());
    }
    @Test
    public void testChangeStatus(FxRobot robot){
        TableCell<Sku, String> cell = (TableCell<Sku, String>) cell(skuTableString, 0, 1, robot);
        ComboBox<String> box = (ComboBox<String>) cell.getGraphic();
        robot.interact(() -> box.getSelectionModel().select(1));
        Assertions.assertEquals("Targeted",skuTable.getItems().get(0).getStatus());
    }
    @Test
    public void testChangeSkuNotes(FxRobot robot){
        robot.doubleClickOn(cell(skuTableString, 0, 2, robot));
        robot.write("Note").press(KeyCode.ENTER).release(KeyCode.ENTER);
        Assertions.assertEquals("Note", skuTable.getItems().get(0).getSkuNotes() );
    }
    







}