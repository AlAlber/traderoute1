package com.traderoute.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.traderoute.*;
import com.traderoute.cells.*;
import com.traderoute.data.*;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductClassReportingController implements Initializable {
    @FXML
    private TableView<ProductClassReport> productClassReportingTable;
    @FXML
    private TableColumn<ProductClassReport, String> retailerNameColumn;
    @FXML
    private TableColumn<ProductClassReport, Boolean> committedColumn;
    @FXML
    private TableColumn<ProductClassReport, Integer> storeCountColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> totalVolumetricsColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> everydayVolumetricsColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> promotedVolumetricsColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> grossRevenueColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> tradeRevenueColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> net1RevenueColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> net1PodColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> net1RateColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> pointsOfDistributionColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> averageSkusColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> averageSellingPriceColumn;
    @FXML
    private TableColumn<ProductClassReport, BigDecimal> weeklyVelocityUfswColumn;
    @FXML
    private TableColumn<ProductClassReport, String> selectedRtmColumn;

    private ObservableList<Retailer> retailers;

    @FXML
    private JFXComboBox<Product> brandNameBox;

    @FXML
    private JFXComboBox<Product> productClassBox;

    @FXML
    private JFXCheckBox committedOnlyBox;

    @FXML
    private JFXCheckBox yearBox0;

    @FXML
    private JFXCheckBox yearBox1;

    @FXML
    private JFXCheckBox yearBox2;

    @FXML
    private JFXCheckBox yearBox3;

    private SimpleObjectProperty<Product> currentProduct = new SimpleObjectProperty<>(
            MenuController.getExampleProducts().get(0));

    // FilteredList<Retailer> filterItems = new FilteredList<>(retailers);

    private ObservableList<CheckBox> yearBoxes;
    private ObservableList<Boolean> years = FXCollections.observableArrayList(false, false, false, false);

    private ObservableList<TableColumn<ProductClassReport, ?>> skuColumns = FXCollections.observableArrayList();

    @FXML
    private JFXButton pdfButton;

    @FXML
    private VBox mainVbox;
    private HostServices hostServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        retailers = FXCollections.observableArrayList();

        brandNameBox.setConverter(new ProductboxConverter("brand"));
        productClassBox.setConverter(new ProductboxConverter("product"));

        retailerNameColumn.setCellValueFactory(cellData -> cellData.getValue().retailerNameProperty());
        committedColumn.setCellValueFactory(cellData -> cellData.getValue().committedProperty());
        storeCountColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().storeCountProperty());
        totalVolumetricsColumn.setCellValueFactory(cellData -> cellData.getValue().totalVolumetricsProperty());
        everydayVolumetricsColumn.setCellValueFactory(cellData -> cellData.getValue().everydayVolumetricsProperty());
        promotedVolumetricsColumn.setCellValueFactory(cellData -> cellData.getValue().promotedVolumetricsProperty());
        grossRevenueColumn.setCellValueFactory(cellData -> cellData.getValue().grossRevenueProperty());
        tradeRevenueColumn.setCellValueFactory(cellData -> cellData.getValue().tradeRevenueProperty());
        net1RevenueColumn.setCellValueFactory(cellData -> cellData.getValue().net1RevenueProperty());
        net1PodColumn.setCellValueFactory(cellData -> cellData.getValue().net1PodProperty());
        net1RateColumn.setCellValueFactory(cellData -> cellData.getValue().net1RateProperty());
        pointsOfDistributionColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().pointsOfDistributionProperty());
        averageSkusColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().averageSkusProperty());
        averageSellingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().averageSellingPriceProperty());
        weeklyVelocityUfswColumn.setCellValueFactory(cellData -> cellData.getValue().weeklyVelocityUfswProperty());
        selectedRtmColumn.setCellValueFactory(cellData -> cellData.getValue().selectedRtmProperty());


        retailerNameColumn.setCellFactory(tc -> new CustomTextCell<>());
        committedColumn.setCellFactory(tc -> new CustomTextCell<>());
        storeCountColumn.setCellFactory(tc -> new IntEditCell1<>(StdSpecs.INTPOS5X.getSpecs()));
        totalVolumetricsColumn.setCellFactory(tc -> new CustomNonEditCell("", ""));
        everydayVolumetricsColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        promotedVolumetricsColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        grossRevenueColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        tradeRevenueColumn.setCellFactory(tc -> new CustomNonEditCell<>("$", ""));
        net1RevenueColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        net1PodColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        net1RateColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        pointsOfDistributionColumn.setCellFactory(tc -> new CustomNonEditCellInt("", ""));
        averageSkusColumn.setCellFactory(tc -> new CustomNonEditCellInt("", ""));
        averageSellingPriceColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        weeklyVelocityUfswColumn.setCellFactory(tc -> new CustomNonEditCell("", ""));
        selectedRtmColumn.setCellFactory(tc -> new CustomTextCell<>());
        yearBoxes = FXCollections.observableArrayList(yearBox0, yearBox1, yearBox2, yearBox3);

        productClassReportingTable.setPadding(new Insets(0, 10, 0, 0));

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

                Stage stage = (Stage) brandNameBox.getScene().getWindow();
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
        Platform.runLater(() -> {
            Window window = mainVbox.getScene().getWindow();
            window.setHeight(mainVbox.getPrefHeight());
            window.setWidth(mainVbox.getPrefWidth());
            window.centerOnScreen();
        });


        // averageSellingPriceColumn.setStyle(".column-header {-fx-size: 20;}");
    }

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void getTotals() {
        Integer totalStoreCount = 0;
        BigDecimal totalTotalVolumetrics = new BigDecimal("0.0");
        BigDecimal totaleverydayVolumetrics = new BigDecimal("0.0");
        BigDecimal totalpromotedVolumetrics = new BigDecimal("0.0");
        BigDecimal totalGrossRevenue = new BigDecimal("0.0");
        BigDecimal totalTradeRevenue = new BigDecimal("0.0");
        BigDecimal totalNet1Revenue = new BigDecimal("0.0");
        BigDecimal avgNet1Pod = new BigDecimal("0.0");
        BigDecimal avgNet1Rate = new BigDecimal("0.0");
        Integer totalPointsOfDistribution = 0;
        BigDecimal avgSkus = new BigDecimal("0.0");
        BigDecimal avgSellingPrice = new BigDecimal("0.0");
        BigDecimal avgWeeklyVelocityUfsw = new BigDecimal("0.0");
        for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
            ProductClassReport report = productClassReportingTable.getItems().get(i);
            totalStoreCount = totalStoreCount + report.getStoreCount();
            totalTotalVolumetrics = totalTotalVolumetrics.add(report.getTotalVolumetrics());
            totaleverydayVolumetrics = totaleverydayVolumetrics.add(report.getEverydayVolumetrics());
            totalpromotedVolumetrics = totalpromotedVolumetrics.add(report.getPromotedVolumetrics());
            totalGrossRevenue = totalGrossRevenue.add(report.getGrossRevenue());
            totalTradeRevenue = totalTradeRevenue.add(report.getTradeRevenue());
            totalNet1Revenue = totalNet1Revenue.add(report.getNet1Revenue());
            avgNet1Pod = avgNet1Pod.add(report.getNet1Pod());
            avgNet1Rate = avgNet1Rate.add(report.getNet1Rate());
            totalPointsOfDistribution = totalPointsOfDistribution + report.getPointsOfDistribution();
            avgSkus = avgSkus.add(report.getAverageSkus());
            avgSellingPrice = avgSellingPrice.add(report.getAverageSellingPrice());
            avgWeeklyVelocityUfsw = avgWeeklyVelocityUfsw.add(report.getWeeklyVelocityUfsw());
        }
        Integer reportCount = productClassReportingTable.getItems().size() - 1;
        avgNet1Pod = avgNet1Pod.divide(new BigDecimal(reportCount), 2, RoundingMode.HALF_UP);
        avgNet1Rate = avgNet1Rate.divide(new BigDecimal(reportCount), 2, RoundingMode.HALF_UP);
        avgSkus = avgSkus.divide(new BigDecimal(reportCount), 2, RoundingMode.HALF_UP);
        avgSellingPrice = avgSellingPrice.divide(new BigDecimal(reportCount), 2, RoundingMode.HALF_UP);
        avgWeeklyVelocityUfsw = avgWeeklyVelocityUfsw.divide(new BigDecimal(reportCount), 2, RoundingMode.HALF_UP);
        ProductClassReport totalsReport = new ProductClassReport("TOTALS", true, totalStoreCount, totalTotalVolumetrics,
                totaleverydayVolumetrics, totalpromotedVolumetrics, totalGrossRevenue, totalTradeRevenue,
                totalNet1Revenue, avgSellingPrice, avgWeeklyVelocityUfsw, totalPointsOfDistribution, avgNet1Pod,
                avgNet1Rate, avgSkus, "", FXCollections.observableArrayList());
        productClassReportingTable.getItems().remove(0);
        productClassReportingTable.getItems().add(0, totalsReport);

        currentProductProperty().addListener(((arg, oldVal, newVal) -> {

        }));
    }

    public Product getCurrentProduct() {
        return currentProduct.get();
    }

    public SimpleObjectProperty<Product> currentProductProperty() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct.set(currentProduct);
    }

    /*
     * Product class changed Event
     */
    public void changeBrandComboboxEvent(ActionEvent event) {
        Product selectedBrandName = brandNameBox.getSelectionModel().getSelectedItem();
        productClassBox
                .setItems(getCorrespondingProductClasses(MenuController.getExampleProducts(), selectedBrandName));
    }

    public void changeCommitted(ActionEvent event) {
        for (ProductClassReport row : productClassReportingTable.getItems()) {
            // if (row.getCurrentPromoPlan().get)
        }
    }

    public ObservableList<Product> getCorrespondingProductClasses(ObservableList<Product> products,
            Product selectedBrandName) {
        ObservableList<Product> correspondingProductClasses = FXCollections.observableArrayList();
        // Set up product combobox and make it display product class
        for (Product product : products) {
            if (product.getBrandName().equals(selectedBrandName.getBrandName())) {
                correspondingProductClasses.add(product);
            }
        }
        return correspondingProductClasses;
    }
    // public void getSkuTotals(){
    // ObservableList<Sku> skus;
    // ObservableList<Sku> alreadySkus = FXCollections.observableArrayList();
    // ObservableList<Integer> skuStoreCountTotals = FXCollections.observableArrayList();
    //
    // Integer totalSkus = 0;
    // Integer totalAlreadySkus = 0;
    // for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
    // skus= productClassReportingTable.getItems().get(i).getRetailerProduct().getSkus();
    // for (int j =0; j< skus.size(); j++) {
    // Integer total =0;
    // Sku sku = skus.get(j);
    // if (!alreadySkus.contains(sku)) {
    // if (sku.getStatus().equals("current")) {
    // skuStoreCountTotals.set(totalSkus, skuStoreCountTotals.get(totalSkus) +
    // productClassReportingTable.getItems().get(i).getStoreCount().get(0));
    // }
    // totalAlreadySkus++;
    // totalSkus++;
    // } else{
    // if (sku.getStatus().equals("current")) {
    // skuStoreCountTotals.set(totalSkus, skuStoreCountTotals.get(totalSkus) +
    // productClassReportingTable.getItems().get(i).getStoreCount().get(0));
    // }
    // skuStoreCountTotals.set(totalSkus,
    // skuStoreCountTotals.get(totalSkus)+productClassReportingTable.getItems().get(i).getStoreCount().get(0) );
    //
    // }
    // }
    //
    // }
    // for (int k= 0; k < alreadySkus.size(); k++){
    // if (alreadySkus.get(k).equals()){
    //
    // }
    // }
    // }

    /*
     * Product class changed Event=
     */
    public void changeProductComboboxEvent(ActionEvent event) {
        Product selectedProduct = productClassBox.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            productClassBox.setPromptText("Now Select a Product Class");
        } else {
            currentProduct.set(selectedProduct);
            for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
                ProductClassReport report = productClassReportingTable.getItems().get(i);
                boolean noneExist = true;
                for (RetailerProduct retailerProduct : report.getRetailer().getRetailerProducts()) {
                    System.out.println("currentproduct: " + currentProduct.get().toString());
                    System.out.println("retailerproductproduct: " + retailerProduct.getProduct());
                    if (retailerProduct.getProduct().getBrandName().equals(currentProduct.get().getBrandName())
                            && retailerProduct.getProduct().getProductClass()
                                    .equals(currentProduct.get().getProductClass())) {
                        System.out.println("getting HHHre");
                        report.setRetailerProduct(retailerProduct);
                        report.updateAll();
                        productClassReportingTable.refresh();
                        noneExist = false;
                    }
                }
                addSkuColumns();
                if (noneExist) {
                    report.setRetailerProduct(null);
                    report.updateAll();
                    // report.setTotalVolumetrics(new BigDecimal("0.0"));
                    // report.setPromotedVolumetrics(new BigDecimal("0.0"));
                    // report.setEverydayVolumetrics(new BigDecimal("0.0"));
                    // report.setGrossRevenue(new BigDecimal("0.0"));
                    // report.setTradeRevenue(new BigDecimal("0.0"));
                    // report.setNet1Revenue(new BigDecimal("0.0"));
                    // report.setNet1Rate(new BigDecimal("0.0"));
                    // report.setNet1Pod(new BigDecimal("0.0"));
                    // report.setAverageSellingPrice(new BigDecimal("0.0"));
                    // report.setWeeklyVelocityUfsw(new BigDecimal("0.0"));
                }
            }
            this.getTotals();
        }
    }

    public void changeYearBox(ActionEvent event) {
        for (int i = 0; i < yearBoxes.size(); i++) {
            if (yearBoxes.get(i).isSelected()) {
                years.set(i, true);
            } else {
                years.set(i, false);
            }
        }
        for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
            ProductClassReport report = productClassReportingTable.getItems().get(i);
            report.setSelectedYears(years);
            report.updateAll();
            getTotals();
        }
    }

    public void changeOnlyCommitted(ActionEvent event) {
        for (ProductClassReport report : productClassReportingTable.getItems()) {
            if (committedOnlyBox.isSelected()) {
                report.setOnlyCommitted(true);
            } else {
                report.setOnlyCommitted(false);
            }
            getTotals();
        }
    }

    private void updateTable() {
        for (ProductClassReport report : productClassReportingTable.getItems()) {
            for (int i = 0; i < 4; i++) {
                if (yearBoxes.get(i).isSelected()) {
                    for (RetailerProduct retailerProduct : report.getRetailer().getRetailerProducts()) {
                        if (currentProduct.equals(retailerProduct.getProduct())) {
                            for (PromoPlan promoPlan : retailerProduct.getPromoPlans()) {
                                // promoPlan.ge
                            }
                        }
                    }
                }
            }
            if (yearBox0.isSelected()) {

            }
            // report.
        }
    }

    private void updateProduct(Product selectedProduct) {
    }



    public ObservableList<ProductClassReport> getReports() {
        ObservableList<ProductClassReport> productClassReports = FXCollections.observableArrayList();
        productClassReports.add(new ProductClassReport(retailers.get(0), retailers.get(0).getRetailerProducts().get(0),
                FXCollections.observableArrayList(false, false, false, false), false));
        productClassReports.add(new ProductClassReport(retailers.get(1), retailers.get(1).getRetailerProducts().get(0),
                FXCollections.observableArrayList(false, false, false, false), false));
        productClassReports.add(new ProductClassReport(retailers.get(0), retailers.get(0).getRetailerProducts().get(0),
                FXCollections.observableArrayList(false, false, false, false), false));
        return productClassReports;
    }

    public Map<Sku, Integer> getSkuTotals() {

        ObservableList<Integer> storeCountTotals;
        Map<Sku, Integer> map = new HashMap<Sku, Integer>();
        // map.put("dog", "type of animal");animal
        for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
            Integer totalStoreCount = 0;
            if (productClassReportingTable.getItems().get(i).getRetailerProduct() != null) {
                ObservableList<Sku> skus = productClassReportingTable.getItems().get(i).getRetailerProduct().getSkus();
                for (Sku sku : skus) {
                    if (sku.getStatus().equals("current")) {
                        if (map.get(sku) != null) {
                            map.put(sku, map.get(sku) + productClassReportingTable.getItems().get(i).getStoreCount());
                        } else {
                            map.put(sku, productClassReportingTable.getItems().get(i).getStoreCount());
                        }
                    }
                }
            }
        }
        return map;

    }

    public void addSkuColumns() {
        for (TableColumn skuColumn : skuColumns) {
            productClassReportingTable.getColumns().remove(skuColumn);
        }
        ObservableList<Sku> skus;
        ObservableList<Sku> alreadySkus = FXCollections.observableArrayList();
        for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
            if (productClassReportingTable.getItems().get(i).getRetailerProduct() != null) {
                skus = productClassReportingTable.getItems().get(i).getRetailerProduct().getSkus();
                System.out.println("report " + i + " = " + productClassReportingTable.getItems().get(i).toString());
                for (Sku sku : skus) {
                    if (!alreadySkus.contains(sku)) {
                        TableColumn<ProductClassReport, Integer> col = new TableColumn("");
                        skuColumns.add(col);
                        col.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().storeCountProperty());
                        col.setCellFactory(tc -> new TableCell<>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText("Hello");
                                }
                                if (item == null) {
                                    setText("");
                                } else {
                                    ProductClassReport report;
                                    if (getTableRow() != null) {
                                        if (getTableRow().getIndex() != 0) {
                                            report = getTableRow().getItem();
                                            if (report.getRetailerProduct() != null) {
                                                if (report.getRetailerProduct().getSkus().contains(sku)) {
                                                    setText(String.valueOf(item));

                                                    if (sku.getStatus().equals("current")) {
                                                        setTooltip(new Tooltip(report.getRetailerName()
                                                                + " : Current Sku, at " + item + "stores"));
                                                        setStyle("-fx-background-color: rgb(0,255,0,0.5)");
                                                    } else if (sku.getStatus().equals("targeted")) {
                                                        setTooltip(new Tooltip(report.getRetailerName()
                                                                + " : Targeted Sku, at " + item + "stores"));
                                                        setStyle("-fx-background-color: rgb(255,255,0,0.25)");
                                                    } else if (sku.getStatus().equals("discontinued")) {
                                                        setTooltip(new Tooltip(report.getRetailerName()
                                                                + " : Discontinued Sku, at " + item + "stores"));
                                                        setStyle("-fx-background-color: rgb(255,0,0,0.4)");
                                                    }
                                                }
                                            }
                                        } else {
                                            if (getSkuTotals().get(sku) != null) {
                                                setText(String.valueOf(getSkuTotals().get(sku)));
                                                setTooltip(
                                                        new Tooltip(" Total Skus in Store " + getSkuTotals().get(sku)));
                                            } else {
                                                setText(String.valueOf(0));
                                                setTooltip(new Tooltip(" Total Skus in Store " + 0));
                                            }

                                            setStyle("-fx-background-color: rgb(105,105,105,0.5)");
                                        }
                                    }
                                }
                            }

                        });

                        Label label1 = new Label(sku.getFlavorDescription());
                        label1.setTooltip(new Tooltip(sku.getFlavorDescription()));

                        VBox vbox = new VBox(label1);
                        // vbox.setRotate(-90);
                        // vbox.setPadding(new Insets(5, 5, 5, 5));
                        vbox.setMinWidth(45);
                        Group g = new Group(vbox);

                        col.setGraphic(g);

                        productClassReportingTable.getColumns().addAll(col);
                        alreadySkus.add(sku);
                    }
                }
            }

        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader menuLoader = App.createFXMLLoader("menu");
        App.setSceneRoot(menuLoader.load());

    }

    public void setRetailers(ObservableList<Retailer> retailers) {
        this.retailers = retailers;

        ProductClassReport totalsReport = new ProductClassReport("TOTALS", true, 0, new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), 0, new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), "", FXCollections.observableArrayList());
        this.productClassReportingTable.setItems(getReports());
        this.productClassReportingTable.getItems().add(0, totalsReport);
        this.getTotals();
        // REENTER THIS WITH NEW BRANDNAMEBOX
//        this.brandNameBox.setItems(RTMPlanningController.getUniqueBrandNames(MenuController.getExampleProducts()));
        if (yearBox0.isSelected()) {

        }
        addSkuColumns();

    }
    private class TableView extends TableView implements TableWithStyles {

        @Override
        public String getTableCellStyles(int col, int row) {
            String style = "";
            if (col ==1 && row==0){
                style = "-fx-background-color= steelblue";
            }
            return style;
        }
    }
}
