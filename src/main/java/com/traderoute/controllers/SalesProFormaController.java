package com.traderoute.controllers;

import com.traderoute.App;
import com.traderoute.cells.CustomTextCell;
import com.traderoute.cells.PromoRowNonEditCell;
import com.traderoute.data.*;
import javafx.application.HostServices;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesProFormaController implements Initializable {

    @FXML
    private TableView<PromoRow<?>> p1Table;

    @FXML
    private TableColumn<PromoRow<?>, String> p1PromoRowNameColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1TotalsColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1JanuaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1FebruaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1MarchColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1AprilColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1MayColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1JuneColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1JulyColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1AugustColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1SeptemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1OctoberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1NovemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p1DecemberColumn;

    @FXML
    private TableView<PromoRow<?>> p2Table;

    @FXML
    private TableColumn<PromoRow<?>, String> p2PromoRowNameColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2TotalsColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2JanuaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2FebruaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2MarchColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2AprilColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2MayColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2JuneColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2JulyColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2AugustColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2SeptemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2OctoberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2NovemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p2DecemberColumn;

    @FXML
    private TableView<PromoRow<?>> p3Table;

    @FXML
    private TableColumn<PromoRow<?>, String> p3PromoRowNameColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3TotalsColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3JanuaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3FebruaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3MarchColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3AprilColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3MayColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3JuneColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3JulyColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3AugustColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3SeptemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3OctoberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3NovemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p3DecemberColumn;

    @FXML
    private TableView<PromoRow<?>> p4Table;

    @FXML
    private TableColumn<PromoRow<?>, String> p4PromoRowNameColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4TotalsColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4JanuaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4FebruaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4MarchColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4AprilColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4MayColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4JuneColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4JulyColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4AugustColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4SeptemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4OctoberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4NovemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p4DecemberColumn;

    @FXML
    private TableView<PromoRow<?>> p5Table;

    @FXML
    private TableColumn<PromoRow<?>, String> p5PromoRowNameColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5TotalsColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5JanuaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5FebruaryColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5MarchColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5AprilColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5MayColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5JuneColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5JulyColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5AugustColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5SeptemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5OctoberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5NovemberColumn;

    @FXML
    private TableColumn<PromoRow<?>, Object> p5DecemberColumn;

    @FXML
    private Button pdfButton;

    @FXML
    private VBox mainVbox;

    private HostServices hostServices;

    private ObservableList<Retailer> retailers = FXCollections.observableArrayList();// =
                                                                                     // FXCollections.observableArrayList();
    private ObservableList<Product> products;// = FXCollections.observableArrayList();
    private Boolean committed; // = new SimpleBooleanProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        p1Table.setItems(getp1NamePromoRows());
        p2Table.setItems(getp2NamePromoRows());
        p3Table.setItems(getp3NamePromoRows());
        p4Table.setItems(getp4NamePromoRows());
        p5Table.setItems(getp5NamePromoRows());

        ObservableList<TableColumn<PromoRow<?>, String>> nameColumns = FXCollections.observableArrayList(
                p1PromoRowNameColumn, p2PromoRowNameColumn, p3PromoRowNameColumn, p4PromoRowNameColumn,
                p5PromoRowNameColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> totalColumns = FXCollections
                .observableArrayList(p1TotalsColumn, p2TotalsColumn, p3TotalsColumn, p4TotalsColumn, p5TotalsColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> januaryColumns = FXCollections.observableArrayList(
                p1JanuaryColumn, p2JanuaryColumn, p3JanuaryColumn, p4JanuaryColumn, p5JanuaryColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> februaryColumns = FXCollections.observableArrayList(
                p1FebruaryColumn, p2FebruaryColumn, p3FebruaryColumn, p4FebruaryColumn, p5FebruaryColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> marchColumns = FXCollections
                .observableArrayList(p1MarchColumn, p2MarchColumn, p3MarchColumn, p4MarchColumn, p5MarchColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> aprilColumns = FXCollections
                .observableArrayList(p1AprilColumn, p2AprilColumn, p3AprilColumn, p4AprilColumn, p5AprilColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> mayColumns = FXCollections.observableArrayList(p1MayColumn,
                p2MayColumn, p3MayColumn, p4MayColumn, p5MayColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> juneColumns = FXCollections.observableArrayList(p1JuneColumn,
                p2JuneColumn, p3JuneColumn, p4JuneColumn, p5JuneColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> julyColumns = FXCollections.observableArrayList(p1JulyColumn,
                p2JulyColumn, p3JulyColumn, p4JulyColumn, p5JulyColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> augustColumns = FXCollections
                .observableArrayList(p1AugustColumn, p2AugustColumn, p3AugustColumn, p4AugustColumn, p5AugustColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> septemberColumns = FXCollections.observableArrayList(
                p1SeptemberColumn, p2SeptemberColumn, p3SeptemberColumn, p4SeptemberColumn, p5SeptemberColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> octoberColumns = FXCollections.observableArrayList(
                p1OctoberColumn, p2OctoberColumn, p3OctoberColumn, p4OctoberColumn, p5OctoberColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> novemberColumns = FXCollections.observableArrayList(
                p1NovemberColumn, p2NovemberColumn, p3NovemberColumn, p4NovemberColumn, p5NovemberColumn);
        ObservableList<TableColumn<PromoRow<?>, Object>> decemberColumns = FXCollections.observableArrayList(
                p1DecemberColumn, p2DecemberColumn, p3DecemberColumn, p4DecemberColumn, p5DecemberColumn);

        ObservableList<ObservableList<TableColumn<PromoRow<?>, Object>>> numberColumns = FXCollections
                .observableArrayList(totalColumns, januaryColumns, februaryColumns, marchColumns, aprilColumns,
                        mayColumns, juneColumns, julyColumns, augustColumns, septemberColumns, octoberColumns,
                        novemberColumns, decemberColumns);

        for (TableColumn<PromoRow<?>, String> column : nameColumns) {
            column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            column.setCellFactory(tc -> new CustomTextCell<>());
        }
        for (TableColumn<PromoRow<?>, Object> column : totalColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().totalProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : januaryColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().januaryProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : februaryColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().februaryProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : marchColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().marchProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : aprilColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().aprilProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : mayColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().mayProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : juneColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().juneProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : julyColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().julyProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : augustColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().augustProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : septemberColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().septemberProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : octoberColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().octoberProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : novemberColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().novemberProperty());
        }
        for (TableColumn<PromoRow<?>, Object> column : decemberColumns) {
            column.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().decemberProperty());
        }
        for (ObservableList<TableColumn<PromoRow<?>, Object>> monthColumns : numberColumns) {
            for (TableColumn<PromoRow<?>, Object> column : monthColumns) {
                column.setCellFactory(tc -> new PromoRowNonEditCell());
            }
        }

        retailers.addListener((ListChangeListener<Retailer>) c -> {
            if (committed != null && products != null && retailers != null) {
                updateTables(0);
                System.out.println("is it getting here");
            }
        });

        pdfButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                WritableImage nodeshot = mainVbox.snapshot(new SnapshotParameters(), null);
                File pngFile = new File("chart.png");
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", pngFile);
                } catch (IOException e) {

                }
                PDDocument doc = new PDDocument();
                PDPage page = new PDPage(PDRectangle.A4);

                page.setRotation(90);
                PDImageXObject pdimage;
                PDPageContentStream content;
                FileChooser fileChooser = new FileChooser();
                // Set Initial Directory to Desktop
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
                // Set extension filter, only PDF files will be shown
                fileChooser.setInitialFileName("untitled");
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);

                Stage stage = (Stage) mainVbox.getScene().getWindow();
                // Show open file dialog
                File pdfFile = new File("untitled.pdf");
                try {
                    pdfFile = fileChooser.showSaveDialog(stage);
                    pdimage = PDImageXObject.createFromFile("chart.png", doc);

                    content = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.OVERWRITE, false);
                    PDRectangle pageSize = page.getMediaBox();
                    float pageWidth = pageSize.getWidth();
                    content.transform(new Matrix(0, 1, -1, 0, pageWidth, 0));
                    // Matrix scaleInstance = Matrix.getScaleInstance(0.52f, 0.65f);
                    Matrix scaleInstance = Matrix.getScaleInstance(0.52f, 0.65f);
                    content.transform(scaleInstance);
                    content.drawImage(pdimage, 35, 70);
                    content.close();
                    doc.addPage(page);
                    doc.save(pdfFile);
                    doc.close();
                    pngFile.delete();
                } catch (IOException ex) {
                    Logger.getLogger(ProductClassReportingController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Open PDF file
                HostServices hostServices = getHostServices();
                hostServices.showDocument(pdfFile.getAbsolutePath());
            }
        });

        // Calculation Table
    }

    private ObservableList<PromoPlan> getCurrentPromoPlans(int year) {
        ObservableList<PromoPlan> currentPromoPlans = FXCollections.observableArrayList();
        for (Retailer retailer : retailers) {
            for (RetailerProduct retailerProduct : retailer.getRetailerProducts()) {
                for (Product product : products) {
                    if (retailerProduct.getProduct().equals(product)) {
                        // System.out.println("product " +products.indexOf(product)+ " :"+product);
                        // System.out.println("retailerproduct product " +products.indexOf(product)+ "
                        // :"+retailerProduct.getProduct());
                        PromoPlan promoPlan = retailerProduct.getPromoPlans().get(year);
                        promoPlan.setRetailer(retailer);
                        if (committed) {
                            // System.out.println("retailerproduct product " +retailerProduct.getProduct());
                            // System.out.println("cooommmitted " +committed);
                            if (promoPlan.getCommitted()) {
                                // System.out.println("promoplann " +promoPlan);
                                currentPromoPlans.add(promoPlan);
                            }
                        } else {
                            currentPromoPlans.add(promoPlan);
                            // System.out.println("promoplann2 " +promoPlan);
                        }
                    }
                }
            }
        }
        return currentPromoPlans;
    }

    public void updateTables(int year) {
        for (int i = 0; i < p4Table.getItems().size(); i++) {
            if (i < p1Table.getItems().size()) {
                updateP1PromoRows(0, i);
                updateP2PromoRows(0, i);
                updateP3PromoRows(0, i);
                updateP4PromoRows(0, i);
            } else if (i == 4) {
                updateP3PromoRows(0, i);
                updateP4PromoRows(0, i);
            } else {
                updateP4PromoRows(0, i);
            }
        }
        updateP5PromoRow(0);
    }

    public void updateP1PromoRows(int year, int promoRowIndex) {
        PromoRow<?> promoRow = p1Table.getItems().get(promoRowIndex);
        for (PromoPlan promoPlan : getCurrentPromoPlans(year)) {
            for (int i = 1; i <= 12; i++) {
                switch (promoRowIndex) {
                // Part 1
                case 0:
                    ((BigDecimalPromoRow) promoRow).setMonth(i, (((BigDecimalPromoRow) promoRow).getMonth(i))
                            .add((BigDecimal) promoPlan.getPromoRows().get(0).getMonth(i)));
                    break;
                case 1:
                    ((IntegerPromoRow) promoRow).setMonth(i, ((IntegerPromoRow) promoRow).getMonth(i)
                            + ((IntegerPromoRow) promoPlan.getPromoRows().get(1)).getMonth(i));
                    break;
                case 2:
                    ((IntegerPromoRow) promoRow).setMonth(i, ((IntegerPromoRow) promoRow).getMonth(i)
                            + ((IntegerPromoRow) promoPlan.getPromoRows().get(4)).getMonth(i));
                    break;
                case 3:
                    ((BigDecimalPromoRow) promoRow).setMonth(i,
                            ((BigDecimalPromoRow) promoRow).getMonth(i).add(promoPlan.getSlottingGameTheoryd(i)));
                    break;
                }
            }
        }
        for (int i = 1; i <= 12; i++) {
            if (promoRowIndex == 3) {
                ((BigDecimalPromoRow) promoRow).setTotal(
                        ((BigDecimalPromoRow) promoRow).getTotal().add(((BigDecimalPromoRow) promoRow).getMonth(i)));

            } else if (promoRowIndex == 1) {
                System.out.println(promoRowIndex);
                ((IntegerPromoRow) promoRow)
                        .setTotal(((IntegerPromoRow) promoRow).getTotal() + ((IntegerPromoRow) promoRow).getMonth(i));
            }
        }
    }

    public void updateP2PromoRows(int year, int promoRowIndex) {
        BigDecimalPromoRow promoRow = (BigDecimalPromoRow) p2Table.getItems().get(promoRowIndex);

        for (PromoPlan promoPlan : getCurrentPromoPlans(year)) {
            for (int i = 1; i <= 12; i++) {
                switch (promoRowIndex) {
                case 0:
                    promoRow.setMonth(i,
                            promoRow.getMonth(i).add(promoPlan.getEverydayVolume(i).setScale(4, RoundingMode.HALF_UP)));
                    break;
                case 1:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getPromoVolume(i, 1)));
                    break;
                case 2:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getPromoVolume(i, 2)));
                    break;
                case 3:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getTotalVolume(i)));
                    break;
                }
            }
        }
        for (int i = 1; i <= 12; i++) {
            promoRow.setTotal(promoRow.getTotal().add(promoRow.getMonth(i)));
        }
    }

    public void updateP3PromoRows(int year, int promoRowIndex) {
        BigDecimalPromoRow promoRow = (BigDecimalPromoRow) p3Table.getItems().get(promoRowIndex);
        for (PromoPlan promoPlan : getCurrentPromoPlans(year)) {
            for (int i = 1; i <= 12; i++) {
                switch (promoRowIndex) {
                // Part 3
                case 0:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getRetailerGrossSales(i)));
                    break;
                case 1:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getRetailerNetCost(i)));
                    break;
                case 2:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getRetailerGrossProfit(i)));
                    break;
                case 3:
                    if (promoPlan.getRetailerGrossSales(i).compareTo(new BigDecimal("0.0")) != 0) {
                        promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getRetailerGrossProfit(i)
                                .divide(promoPlan.getRetailerGrossSales(i), 4, RoundingMode.HALF_UP)));
                    }
                    // Make total function
                    break;
                case 4:
                    if (promoPlan.getTotalVolume(i).compareTo(new BigDecimal("0.0")) != 0) {
                        promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getRetailerGrossSales(i)
                                .divide(promoPlan.getTotalVolume(i), 4, RoundingMode.HALF_UP)));
                    }
                    // Make total function
                    break;
                }
            }
        }
        int totalPromoPlansConsidered = getCurrentPromoPlans(year).size();
        for (int i = 1; i <= 12; i++) {
            if (promoRowIndex != 3 || promoRowIndex != 4) { // Set totals for bigdecimals
                promoRow.setTotal(promoRow.getTotal().add(promoRow.getMonth(i)));
            } else if (promoRowIndex == 3 || promoRowIndex == 4) { // PromoRows with averages
                promoRow.setMonth(i,
                        promoRow.getMonth(i).divide(new BigDecimal(totalPromoPlansConsidered), 4, RoundingMode.HALF_UP));
                promoRow.setTotal(promoRow.getTotal().add(promoRow.getMonth(i)));
            }
        }
        if (promoRowIndex == 3 || promoRowIndex == 4) { // PromoRows with averages
            promoRow.setTotal(promoRow.getTotal().divide(new BigDecimal("12"), 4, RoundingMode.HALF_UP));
        }
    }

    public void updateP4PromoRows(int year, int promoRowIndex) {
        BigDecimalPromoRow promoRow = (BigDecimalPromoRow) p4Table.getItems().get(promoRowIndex);
        for (PromoPlan promoPlan : getCurrentPromoPlans(year)) {
            for (int i = 1; i <= 12; i++) {
                switch (promoRowIndex) {
                // Part 4
                case 0:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerGrossSalesList(i)));
                    break;
                case 1:
                case 9:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getFobDiscounts(i)));
                    break;
                case 2:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerGrossSalesActual(i)));
                    break;
                case 3:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getTotalTS(i)));
                    break;
                case 4:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getSpoilsFeesTS(i)));
                    break;
                case 5:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getEverydayAllowanceTS(i)));
                    break;
                case 6:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getPromoTS(i, 1)));
                    break;
                case 7:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getPromoTS(i, 2)));
                    break;
                case 8:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getFixedCostsTS(i)));
                    break;
                // Case 9 is merged
                case 10:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerNet1Rev(i)));
                    break;
                case 11:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerFreightCost(i)));
                    break;
                case 12:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerNet2Rev(i)));
                    break;
                case 13:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getSlottingGameTheoryd(i)));
                    break;
                case 14:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerNet3Rev(i)));
                    break;
                case 15:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerCogs(i)));
                    break;
                case 16:
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerGrossProfit(i)));
                    break;
                // Part 5
                }
            }
        }
        for (int i = 1; i <= 12; i++) {
            promoRow.setTotal(promoRow.getTotal().add(promoRow.getMonth(i)));
        }
    }

    public void updateP5PromoRow(int year) {
        BigDecimalPromoRow promoRow = (BigDecimalPromoRow) p5Table.getItems().get(0);
        for (PromoPlan promoPlan : getCurrentPromoPlans(year)) {
            for (int i = 1; i <= 12; i++) {
                if (promoPlan.getTotalVolume(i).compareTo(new BigDecimal("0.0")) > 0) {
                    promoRow.setMonth(i, promoRow.getMonth(i).add(promoPlan.getManufacturerNet1Rev(i)
                            .divide(promoPlan.getTotalVolume(1), 4, RoundingMode.HALF_UP)));
                }
            }
        }
        int totalPromoPlansConsidered = getCurrentPromoPlans(year).size();
        for (int i = 1; i <= 12; i++) { // PromoRow with averages
            if (totalPromoPlansConsidered > 0) {
                promoRow.setMonth(i,
                        promoRow.getMonth(i).divide(new BigDecimal(totalPromoPlansConsidered), 4, RoundingMode.HALF_UP));
                promoRow.setTotal(promoRow.getTotal().add(promoRow.getMonth(i)));
            }
        } // PromoRows with averages
        promoRow.setTotal(promoRow.getTotal().divide(new BigDecimal("12"), 4, RoundingMode.HALF_UP));
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader menuLoader = App.createFXMLLoader("menu");
        App.setSceneRoot(menuLoader.load());
    }

    public ObservableList<Retailer> getRetailers() {
        return retailers;
    }

    public ObservableList<PromoRow<?>> getp1NamePromoRows() {
        ObservableList<PromoRow<?>> namePromoRows = FXCollections.observableArrayList();
        namePromoRows.add(new BigDecimalPromoRow("Projected Sku Placements", ""));
        namePromoRows.add(new IntegerPromoRow("Targeted New Placements", ""));
        namePromoRows.add(new IntegerPromoRow("Store Count", ""));
        namePromoRows.add(new BigDecimalPromoRow("Slotting", "$"));
        return namePromoRows;
    }

    public ObservableList<PromoRow<?>> getp2NamePromoRows() {
        ObservableList<PromoRow<?>> namePromoRows = FXCollections.observableArrayList();
        namePromoRows.add(new BigDecimalPromoRow("Everyday Volume", ""));
        namePromoRows.add(new BigDecimalPromoRow("Promo 1 Volume", ""));
        namePromoRows.add(new BigDecimalPromoRow("Promo 2 Volume", ""));
        namePromoRows.add(new BigDecimalPromoRow("Total Volume", ""));
        return namePromoRows;
    }

    public ObservableList<PromoRow<?>> getp3NamePromoRows() {
        ObservableList<PromoRow<?>> namePromoRows = FXCollections.observableArrayList();
        namePromoRows.add(new BigDecimalPromoRow("Retailer: Gross Sales", ""));
        namePromoRows.add(new BigDecimalPromoRow("Retailer: Net Cost", ""));
        namePromoRows.add(new BigDecimalPromoRow("Retailer: Gross Profit", ""));
        namePromoRows.add(new BigDecimalPromoRow("Retailer: Gross Profit Margin %", ""));
        namePromoRows.add(new BigDecimalPromoRow("Retailer: Average Selling Price", ""));
        return namePromoRows;
    }

    public ObservableList<PromoRow<?>> getp4NamePromoRows() {
        ObservableList<PromoRow<?>> namePromoRows = FXCollections.observableArrayList();
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer: Gross Sales (List)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("F.O.B Discounts", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Gross Sales Actual", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Total Trade Spending", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Spoils & Fees (Trade Spending)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Everyday Allowance (Trade Spending)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Promo 1 (Trade Spending)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Promo 2 (Trade Spending)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Fixed Costs (Trade Spending)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("F.O.B Freight Credit To Trade", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Net1 Revenue", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Freight Cost (Non-F.O.B.)", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Net2 Revenue", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Slotting", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Net3 Revenue", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Cost of Goods Sold", "$"));
        namePromoRows.add(new BigDecimalPromoRow("Manufacturer Gross Profit", "$"));
        return namePromoRows;
    }

    public ObservableList<PromoRow<?>> getp5NamePromoRows() {
        ObservableList<PromoRow<?>> namePromoRows = FXCollections.observableArrayList();
        namePromoRows.add(new BigDecimalPromoRow("Net1 Revenue Rate (Sales Management)", "$"));
        return namePromoRows;
    }

    public void setRetailers(ObservableList<Retailer> retailers) {
        // System.out.println("before p1" + p1Table.getItems().toString());
        // System.out.println("before p2" + p2Table.getItems().toString());
        // System.out.println("before p3" + p3Table.getItems().toString());
        // System.out.println("before p4" + p4Table.getItems().toString());
        // System.out.println("before p5" + p5Table.getItems().toString());
        // System.out.println("committed:" + committed + "");
        // System.out.println("coorent promo plans"+ getCurrentPromoPlans(0));
        this.retailers = retailers;
        updateTables(0);
        // System.out.println("after p1" + p1Table.getItems().toString());
        // System.out.println("after p2" + p2Table.getItems().toString());
        // System.out.println("after p3" + p3Table.getItems().toString());
        // System.out.println("after p4" + p4Table.getItems().toString());
        // System.out.println("after p5" + p5Table.getItems().toString());
        p1Table.refresh();
        p2Table.refresh();
        p3Table.refresh();
        p4Table.refresh();
        p5Table.refresh();
        // System.out.println("coorent promo plans atfer"+ getCurrentPromoPlans(0));
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }

    public boolean isCommitted() {
        return committed;
    }

    public Boolean committedProperty() {
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}
