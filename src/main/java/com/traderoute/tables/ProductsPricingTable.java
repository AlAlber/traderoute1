package com.traderoute.tables;

import com.traderoute.App;
import com.traderoute.CustomColumn;
import com.traderoute.cells.*;
import com.traderoute.data.Product;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.math.BigDecimal;

public class ProductsPricingTable extends  CustomTable{
    private TableColumn<Product, String> brandNameCol = new CustomColumn(140.0, "Brand", "Brand");
    private TableColumn<Product, String> productClassCol = new CustomColumn(140.0, "Product Class", "Product Class");
    private TableColumn<Product, BigDecimal> listCostCol = new CustomColumn(100.0, "Unit List Cost", "Unit List Cost");
    private TableColumn<Product, BigDecimal> baseFreightCol = new CustomColumn(100.0, "Unit Base Freight", "Unit Base Freight");
    private TableColumn<Product, BigDecimal> fobCostCol = new CustomColumn(100.0, "Unit F.O.B. Cost", "Unit F.O.B. Cost");
    private TableColumn<Product, BigDecimal> net1GoalCol = new CustomColumn(100.0, "Unit Net1 Goal", "Unit Net1 Goal");
    private TableColumn<Product, BigDecimal> blendedCogsCol = new CustomColumn(100.0, "Unit Blended COGS", "Unit Blended Cost of Goods Sold");
    private TableColumn<Product, BigDecimal> emptyCol = new TableColumn<>();
    private TableColumn<Product, BigDecimal> elasticityMultipleCol = new CustomColumn(139.2, "Neg Price Elasticity Multiple", "Negative Price Elasticity Multiple");
    private TableColumn<Product, BigDecimal> janCol = new CustomColumn(30.0, "Jan", "January");
    private TableColumn<Product, BigDecimal> febCol = new CustomColumn(30.0, "Feb", "February");
    private TableColumn<Product, BigDecimal> marCol = new CustomColumn(30.0, "Mar", "March");
    private TableColumn<Product, BigDecimal> aprCol = new CustomColumn(30.0, "Apr", "April");
    private TableColumn<Product, BigDecimal> mayCol = new CustomColumn(30.0, "May", "May");
    private TableColumn<Product, BigDecimal> junCol = new CustomColumn(30.0, "Jun", "June");
    private TableColumn<Product, BigDecimal> julCol = new CustomColumn(30.0, "Jul", "July");
    private TableColumn<Product, BigDecimal> augCol = new CustomColumn(30.0, "Aug", "August");
    private TableColumn<Product, BigDecimal> sepCol = new CustomColumn(30.0, "Sep", "September");
    private TableColumn<Product, BigDecimal> octCol = new CustomColumn(30.0, "Oct", "October");
    private TableColumn<Product, BigDecimal> novCol = new CustomColumn(30.0, "Nov", "November");
    private TableColumn<Product, BigDecimal> decCol = new CustomColumn(30.0, "Dec", "December");
    private TableColumn<Product, Button> specificationCol = new CustomColumn(140.0, "Edit Specifications", "Edit Specifications");

    public ProductsPricingTable () {
        emptyCol.setPrefWidth(17.0);
        getColumns().addAll(brandNameCol, productClassCol, listCostCol, baseFreightCol, fobCostCol,
                net1GoalCol, blendedCogsCol, emptyCol, elasticityMultipleCol, emptyCol, janCol, febCol, marCol, aprCol,
                mayCol, junCol, julCol, augCol, sepCol, octCol, novCol, decCol);
        getColumns().stream().forEach(tcol -> setSortPolicy(e -> false));
    }

    @Override
    public void setCellFactories() {
        CellSpecs decSpecs5 = StdSpecs.DECPOS5$.getSpecs();
        brandNameCol.setCellFactory(tc -> new CustomTextCell<>());
        productClassCol.setCellFactory(tc -> new CustomTextCell<>());
        listCostCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs5));
        baseFreightCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs5));
        fobCostCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs5));
        net1GoalCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs5));
        blendedCogsCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs5));
        elasticityMultipleCol.setCellFactory(tc -> new BigDecimalEditCell1(new CellSpecsBuilder().pre("").post(" : 1").build()));
        // brandNameColumn.setCellValueFactory(tc-> new Button());
        specificationCol.setCellFactory(ActionButtonTableCell.<Product> forTableColumn("Edit", (Product p) -> {
            // try {
            // Stage window = new Stage();
            // window.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader productSkuSpecificationsLoader = null;
            try {
                productSkuSpecificationsLoader = App.createFXMLLoader("productSkuSpecifications");
                App.setSceneRoot(productSkuSpecificationsLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;
        }));
        CellSpecs decSpecs3 = new CellSpecsBuilder().pre("")
                .defaultValue(new BigDecimal("1.00")).maxValue(new BigDecimal("100.00")).build();
        janCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        febCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        marCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        aprCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        mayCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        junCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        julCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        augCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        sepCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        octCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        novCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
        decCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs3));
    }

    @Override
    public void setCellValueFactories() {
        brandNameCol.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
        productClassCol.setCellValueFactory(cellData -> cellData.getValue().productClassProperty());
        listCostCol.setCellValueFactory(cellData ->  cellData.getValue().unitListCostProperty());
        baseFreightCol.setCellValueFactory(cellData -> cellData.getValue().unitBaseFreightProperty());
        fobCostCol.setCellValueFactory(cellData -> cellData.getValue().unitFobCostProperty());
        net1GoalCol.setCellValueFactory(cellData -> cellData.getValue().unitNet1GoalProperty());
        blendedCogsCol.setCellValueFactory(cellData -> cellData.getValue().unitBlendedCogsProperty());
        elasticityMultipleCol.setCellValueFactory(cellData -> cellData.getValue().elasticityMultipleProperty());

        janCol.setCellValueFactory(cellData -> cellData.getValue().janIndiceProperty());
        febCol.setCellValueFactory(cellData -> cellData.getValue().febIndiceProperty());
        marCol.setCellValueFactory(cellData -> cellData.getValue().marIndiceProperty());
        aprCol.setCellValueFactory(cellData -> cellData.getValue().aprIndiceProperty());
        mayCol.setCellValueFactory(cellData -> cellData.getValue().mayIndiceProperty());
        junCol.setCellValueFactory(cellData -> cellData.getValue().junIndiceProperty());
        julCol.setCellValueFactory(cellData -> cellData.getValue().julIndiceProperty());
        augCol.setCellValueFactory(cellData -> cellData.getValue().augIndiceProperty());
        sepCol.setCellValueFactory(cellData -> cellData.getValue().sepIndiceProperty());
        octCol.setCellValueFactory(cellData -> cellData.getValue().octIndiceProperty());
        novCol.setCellValueFactory(cellData -> cellData.getValue().novIndiceProperty());
        decCol.setCellValueFactory(cellData -> cellData.getValue().decIndiceProperty());
    }
}
