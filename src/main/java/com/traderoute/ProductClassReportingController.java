package com.traderoute;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    private Product currentProduct;

//    FilteredList<Retailer> filterItems = new FilteredList<>(retailers);


    private ObservableList<CheckBox> yearBoxes;
    private ObservableList<Boolean> years = FXCollections.observableArrayList(false, false, false, false);


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
        pointsOfDistributionColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().pointsOfDistributionProperty());
        averageSkusColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().averageSkusProperty());
        averageSellingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().averageSellingPriceProperty());
        weeklyVelocityUfswColumn.setCellValueFactory(cellData -> cellData.getValue().weeklyVelocityUfswProperty());
        selectedRtmColumn.setCellValueFactory(cellData-> cellData.getValue().selectedRtmProperty());

        retailerNameColumn.setCellFactory(tc -> new CustomTextCell<>());
        committedColumn.setCellFactory(tc -> new CustomTextCell<>());
        storeCountColumn.setCellFactory(tc -> new CustomNonEditCellInt("", ""));
        totalVolumetricsColumn.setCellFactory(tc -> new CustomNonEditCell("", ""));
        everydayVolumetricsColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        promotedVolumetricsColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        grossRevenueColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        tradeRevenueColumn.setCellFactory(tc -> new CustomNonEditCell<>("$", ""));
        net1RevenueColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        net1PodColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        net1RateColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        pointsOfDistributionColumn.setCellFactory(tc -> new CustomNonEditCellInt("$", ""));
        averageSkusColumn.setCellFactory(tc -> new CustomNonEditCellInt("$", ""));
        averageSellingPriceColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        weeklyVelocityUfswColumn.setCellFactory(tc -> new CustomNonEditCell("$", ""));
        selectedRtmColumn.setCellFactory(tc -> new CustomTextCell<>());
        yearBoxes = FXCollections.observableArrayList(yearBox0, yearBox1, yearBox2, yearBox3);

        productClassReportingTable.setPadding(new Insets(0,10,0,0));


//        averageSellingPriceColumn.setStyle(".column-header {-fx-size:  20;}");
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
                totaleverydayVolumetrics, totalpromotedVolumetrics, totalGrossRevenue, totalTradeRevenue, totalNet1Revenue,
                avgSellingPrice, avgWeeklyVelocityUfsw, totalPointsOfDistribution, avgNet1Pod, avgNet1Rate, avgSkus,"");
        productClassReportingTable.getItems().remove(0);
        productClassReportingTable.getItems().add(0, totalsReport);
    }

    /*
    Product class changed Event
     */
    public void changeBrandComboboxEvent(ActionEvent event) {
        Product selectedBrandName = brandNameBox.getSelectionModel().getSelectedItem();
        productClassBox.setItems(getCorrespondingProductClasses(RetailerSelectionController.getExampleProducts(), selectedBrandName));
    }

    public void changeCommitted(ActionEvent event) {
        for (ProductClassReport row : productClassReportingTable.getItems()) {
//            if (row.getCurrentPromoPlan().get)
        }
    }

    public ObservableList<Product> getCorrespondingProductClasses(ObservableList<Product> products, Product selectedBrandName) {
        ObservableList<Product> correspondingProductClasses = FXCollections.observableArrayList();
        //Set up product combobox and make it display product class
        for (Product product : products) {
            if (product.getBrandName().equals(selectedBrandName.getBrandName())) {
                correspondingProductClasses.add(product);
            }
        }
        return correspondingProductClasses;
    }

    /*
    Product class changed Event
     */
    public void changeProductComboboxEvent(ActionEvent event) {
        Product selectedProduct = productClassBox.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            productClassBox.setPromptText("Now Select a Product Class");
        } else {
            currentProduct = selectedProduct;
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
            report.setYears(years);
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
            report.updateAll();
        }
    }

    private void updateTable() {
        for (ProductClassReport report : productClassReportingTable.getItems()) {
            for (int i = 0; i < 4; i++) {
                if (yearBoxes.get(i).isSelected()) {
                    for (RetailerProduct retailerProduct : report.getRetailer().getRetailerProducts()) {
                        if (currentProduct.equals(retailerProduct.getProduct())) {
                            for (PromoPlan promoPlan : retailerProduct.getPromoPlans()) {
//                            promoPlan.ge
                            }
                        }
                    }
                }
            }
            if (yearBox0.isSelected()) {

            }
//            report.
        }
    }

    private void updateProduct(Product selectedProduct) {
    }

    public ObservableList<ProductClassReport> getReports() {
        ObservableList<ProductClassReport> productClassReports = FXCollections.observableArrayList();
        productClassReports.add(new ProductClassReport(retailers.get(0), retailers.get(0).getRetailerProducts().get(0), FXCollections.observableArrayList(true, true, false, false), false));
        productClassReports.add(new ProductClassReport(retailers.get(1), retailers.get(0).getRetailerProducts().get(0), FXCollections.observableArrayList(true, true, false, false), false));
        productClassReports.add(new ProductClassReport(retailers.get(0), retailers.get(0).getRetailerProducts().get(0), FXCollections.observableArrayList(true, false, false, false), false));
        return productClassReports;
    }


    public void setRetailers(ObservableList<Retailer> retailers) {
        this.retailers = retailers;

        ProductClassReport totalsReport = new ProductClassReport("TOTALS", true, 0, new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), 0, new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), "");
        this.productClassReportingTable.setItems(getReports());
        this.productClassReportingTable.getItems().add(0, totalsReport);
        this.getTotals();
        this.brandNameBox.setItems(firstTableController.getUniqueBrandNames(RetailerSelectionController.getExampleProducts()));
        if (yearBox0.isSelected()) {

        }
        ObservableList<Sku> skus;
        ObservableList<Sku> alreadySkus = FXCollections.observableArrayList();
        for (int i = 1; i < productClassReportingTable.getItems().size(); i++) {
            skus = productClassReportingTable.getItems().get(i).getRetailerProduct().getSkus();
            for (Sku sku : skus) {
                if (!alreadySkus.contains(sku)) {
                    TableColumn<ProductClassReport, Integer> col = new TableColumn("");
                    col.setCellValueFactory(cellData-> (ObservableValue) cellData.getValue().storeCountProperty());
                    col.setCellFactory(tc->new TableCell<>(){
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText("Hello");
                                }
                                if (item == null) {
                                    setText("");
                                }
                                else {
                                    setText(String.valueOf( item));
                                    if (sku.getStatus().equals("current")) {
                                        setStyle("-fx-background-color: green");
                                    }else if (sku.getStatus().equals("targeted")) {
                                        setStyle("-fx-background-color: yellow");
                                    } else if(sku.getStatus().equals("discontinued")) {
                                        setStyle("-fx-background-color: red");
                                    } else{
                                        setStyle("-fx-background-color: black");
                                    }
                                }
                            }

                        });

                    Label label1 = new Label(sku.getFlavorDescription());
                    label1.setTooltip(new Tooltip(sku.getFlavorDescription() ));

                    VBox vbox = new VBox(label1);
//                vbox.setRotate(-90);
//                vbox.setPadding(new Insets(5, 5, 5, 5));
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
