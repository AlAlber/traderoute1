package com.traderoute.controllers;

import com.traderoute.App;
import com.traderoute.TestBaseClass;
import com.traderoute.data.ProductClassReport;
import com.traderoute.data.Retailer;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.function.BiConsumer;

@ExtendWith(ApplicationExtension.class)
public class ProductClassReportingControllerTest extends TestBaseClass {

    private ObservableList<Retailer> retailers = MenuController.getExampleRetailers();
    private ProductClassReportingController controller;
    private TableView<ProductClassReport> productClassReportingTable;

    private String promoTableString = "#productClassReportingTable";

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = App.createFXMLLoader("productClassReporting");
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setRetailers(retailers);
        stage.setScene(scene);
        stage.show();

    }

    @org.junit.jupiter.api.BeforeEach
    void setUp(FxRobot robot) {


//        retailer = new SimpleObjectProperty<>(new Retailer("ahold", MenuController.getRetailerProducts(),
//                0, new BigDecimal("40"), 158, new BigDecimal("3.0")));
////        controller.setRetailer(retailer.get());
//        rtmOptions = retailer.get().getRetailerProducts().get(0).getRtmOptions();
//        promoTable = robot.lookup(promoTableString).queryTableView();
//        controller.getPromoPlans().get(0).setSelectedRtm(retailer.get().getRetailerProducts().get(0).getRtmOptions().get(1));
//        controller.setCurrentPromoPlanIndex(0);
    }

//    public void checkBDCell(int row, int col, String input, String expected, String tableString, FxRobot robot, BiConsumer consumer){
//        TableView table = robot.lookup(tableString).queryTableView();
//        TableCell tableCell = cell(tableString, row, col, robot);
//        ProductClassReport report = (ProductClassReport)table.getItems().get(row);
////        BiConsumer<ProductClassReport, String> consumer = (repor, inpu) -> repor.setNet1Pod(new BigDecimal(inpu));
//        robot.interact(()-> report.setColumn(consumer, report, input));
//        Assertions.assertEquals(expected,tableCell.getText());
//    }

//    public static void executeRowAction (Consumer<Integer> action, Integer row) {
//        action.accept(row);
//    }
//
//    public void executeAction() {
//        Function<FxRobot, Function<TableView, Function<Integer, Function<Integer, Consumer<String>>>>> f = rob -> t -> r -> c -> i -> {
//            rob.interact(()->(IntegerPromoRow)t.getItems().
//                    get(r)).setMonth(col, Integer.valueOf(input))); )
//        }
//        f.apply(rob).apply(t).apply(r).apply(c).accept(i);
//    }
    @Test
    public void testStoreCountCell(FxRobot robot){
        BiConsumer<ProductClassReport, String> consumer = (repor, inpu) -> repor.setStoreCount(Integer.valueOf(inpu));
        checkCell(1,2, "158", "158", promoTableString, consumer, robot);
    }
    @Test
    public void testNet1PodCell(FxRobot robot){
        BiConsumer<ProductClassReport, String> consumer = (repor, inpu) -> repor.setStoreCount(Integer.valueOf(inpu));
        checkCell(1,2, "157", "157", promoTableString, consumer, robot);
    }



    // THis is the data that the controller is setting automatically after calling setRetailers
//    public ObservableList<ProductClassReport> getReports() {
//        ObservableList<ProductClassReport> productClassReports = FXCollections.observableArrayList();
//        productClassReports.add(new ProductClassReport(retailers.get(0), retailers.get(0).getRetailerProducts().get(0),
//                FXCollections.observableArrayList(false, false, false, false), false));
//        productClassReports.add(new ProductClassReport(retailers.get(1), retailers.get(1).getRetailerProducts().get(0),
//                FXCollections.observableArrayList(false, false, false, false), false));
//        productClassReports.add(new ProductClassReport(retailers.get(0), retailers.get(0).getRetailerProducts().get(0),
//                FXCollections.observableArrayList(false, false, false, false), false));
//        return productClassReports;
//    }


}