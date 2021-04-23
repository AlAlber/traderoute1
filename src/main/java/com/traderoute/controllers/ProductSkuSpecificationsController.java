package com.traderoute.controllers;

import com.traderoute.*;
import com.traderoute.cells.CustomTextCell;
import com.traderoute.cells.NumberEditCell;
import com.traderoute.data.Product;
import com.traderoute.data.Sku;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductSkuSpecificationsController implements Initializable {

    @FXML
    private TableView<Sku> skuSpecificationTable;

    @FXML
    private TableColumn<Sku, String> flavorDescriptionColumn;

    @FXML
    private TableColumn<Sku, Integer> casePackColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> unitWeightColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> grossCaseWeightColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> netCaseWeightColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> caseTareWeightColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> caseCubeColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> caseLengthColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> caseWidthColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> caseHeightColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> unitLengthColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> unitWidthColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> unitHeightColumn;

    @FXML
    private TableColumn<Sku, Integer> casesPerLayerColumn;

    @FXML
    private TableColumn<Sku, Integer> layersPerPalletColumn;

    @FXML
    private TableColumn<Sku, Integer> casesPerPalletColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> palletHeightColumn;

    @FXML
    private TableColumn<Sku, Integer> palletWeightColumn;

    @FXML
    private TableColumn<Sku, Integer> palletsPerTruckColumn;

    @FXML
    private TableColumn<Sku, Integer> deliveredShelfLifeColumn;

    @FXML
    private TableColumn<Sku, String> packageUpcColumn;

    @FXML
    private TableColumn<Sku, String> caseUpcColumn;

    @FXML
    private TableColumn<Sku, BigDecimal> unitListColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // @FXML
        // private TableColumn<Sku, BigDecimal> grossCaseWeightColumn;
        // private SimpleIntegerProperty casePack, unitweight, grossCaseWeight;
        skuSpecificationTable.setItems(MenuController.getExtendedSkus()); // getSkus
        skuSpecificationTable.setEditable(true);

        flavorDescriptionColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().flavorDescriptionProperty());
        casePackColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casePackProperty());
        unitWeightColumn.setCellValueFactory(cellData -> cellData.getValue().unitWeightProperty());
        grossCaseWeightColumn.setCellValueFactory(cellData -> cellData.getValue().grossCaseWeightProperty());
        netCaseWeightColumn.setCellValueFactory(cellData -> cellData.getValue().netCaseWeightProperty());
        caseTareWeightColumn.setCellValueFactory(cellData -> cellData.getValue().caseTareWeightProperty());
        caseCubeColumn.setCellValueFactory(cellData -> cellData.getValue().caseCubeProperty());
        caseLengthColumn.setCellValueFactory(cellData -> cellData.getValue().caseLengthProperty());
        caseWidthColumn.setCellValueFactory(cellData -> cellData.getValue().caseWidthProperty());
        caseHeightColumn.setCellValueFactory(cellData -> cellData.getValue().caseHeightProperty());
        unitLengthColumn.setCellValueFactory(cellData -> cellData.getValue().unitLengthProperty());
        unitWidthColumn.setCellValueFactory(cellData -> cellData.getValue().unitWidthProperty());
        unitHeightColumn.setCellValueFactory(cellData -> cellData.getValue().unitHeightProperty());
        casesPerLayerColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerLayerProperty());
        layersPerPalletColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().layersPerPalletProperty());
        casesPerPalletColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerPalletProperty());
        palletHeightColumn.setCellValueFactory(cellData -> cellData.getValue().palletHeightProperty());
        palletWeightColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletWeightProperty());
        palletsPerTruckColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletsPerTruckProperty());
        deliveredShelfLifeColumn
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().deliveredShelfLifeProperty());
        packageUpcColumn.setCellValueFactory(cellData -> cellData.getValue().packageUpcProperty());
        caseUpcColumn.setCellValueFactory(cellData -> cellData.getValue().caseUpcProperty());
        unitListColumn.setCellValueFactory(cellData -> cellData.getValue().unitListProperty());

        flavorDescriptionColumn.setCellFactory(tc -> new CustomTextCell<>());
        casePackColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        unitWeightColumn.setCellFactory(tc -> new NumberEditCell("", " lbs."));
        grossCaseWeightColumn.setCellFactory(tc -> new NumberEditCell("", " lbs."));
        netCaseWeightColumn.setCellFactory((tc -> new NumberEditCell("", " lbs.")));
        caseTareWeightColumn.setCellFactory(tc -> new NumberEditCell("", " lbs."));
        caseCubeColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        caseLengthColumn.setCellFactory(tc -> new NumberEditCell("", "\""));
        caseWidthColumn.setCellFactory(tc -> new NumberEditCell("", "\""));
        caseHeightColumn.setCellFactory(tc -> new NumberEditCell("", "\""));
        unitLengthColumn.setCellFactory(tc -> new NumberEditCell("", "\""));
        unitWidthColumn.setCellFactory(tc -> new NumberEditCell("", "\""));
        unitHeightColumn.setCellFactory(tc -> new NumberEditCell("", "\""));
        casesPerLayerColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        layersPerPalletColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        casesPerPalletColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        palletHeightColumn.setCellFactory(tc -> new NumberEditCell("", " \""));
        palletWeightColumn.setCellFactory(tc -> new NumberEditCell("", " lbs."));
        palletsPerTruckColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        deliveredShelfLifeColumn.setCellFactory(tc -> new NumberEditCell("", " Months"));
        packageUpcColumn.setCellFactory(tc -> new CustomTextCell<>());
        caseUpcColumn.setCellFactory(tc -> new CustomTextCell<>());
        unitListColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader menuLoader = App.createFXMLLoader("menu");
        App.setSceneRoot(menuLoader.load());

    }

    public void setProduct(Product p) {
        skuSpecificationTable.setItems(p.getSkus());
    }
}
