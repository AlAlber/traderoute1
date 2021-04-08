package com.traderoute;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


public class ProductSkuSpecificationsController implements Initializable {
//    System.out.println(fxmlLoader.getController().toString());
//        stage.setScene(scene);
//        stage.show();

    private Product product;
    static String retailerName;
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


//    @FXML
//    private TableColumn<Sku, BigDecimal> grossCaseWeightColumn;
//private SimpleIntegerProperty casePack, unitweight, grossCaseWeight;
        flavorDescriptionColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().flavorDescriptionProperty());
        casePackColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casePackProperty());
        unitWeightColumn.setCellValueFactory(cellData -> cellData.getValue().unitWeightProperty());
        grossCaseWeightColumn.setCellValueFactory(cellData -> cellData.getValue().grossCaseWeightProperty());
        netCaseWeightColumn.setCellValueFactory(cellData -> cellData.getValue().netCaseWeightProperty());
        caseTareWeightColumn.setCellValueFactory(cellData -> cellData.getValue().caseTareWeightProperty());
        caseCubeColumn.setCellValueFactory(cellData -> cellData.getValue().caseCubeProperty());
        caseLengthColumn.setCellValueFactory(cellData -> cellData.getValue().caseLengthProperty());
        caseWidthColumn.setCellValueFactory(cellData -> cellData.getValue().caseWidthProperty());
        caseHeightColumn.setCellValueFactory(cellData -> cellData.getValue().caseHeightProperty();
        unitLengthColumn.setCellValueFactory(cellData -> cellData.getValue().unitLengthProperty());
        unitWidthColumn.setCellValueFactory(cellData -> cellData.getValue().unitWidthProperty());
        unitHeightColumn.setCellValueFactory(cellData -> cellData.getValue().unitHeightProperty());
        casesPerLayerColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerLayerProperty());
        layersPerPalletColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().layersPerPalletProperty());
        casesPerPalletColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerPalletProperty());
        palletHeightColumn.setCellValueFactory(cellData -> cellData.getValue().palletHeightProperty());
        palletWeightColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletWeightProperty());
        palletsPerTruckColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletsPerTruckProperty());
        deliveredShelfLifeColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().deliveredShelfLifeProperty());
        packageUpcColumn.setCellValueFactory(cellData -> cellData.getValue().packageUpcProperty());
        caseUpcColumn.setCellValueFactory(cellData -> cellData.getValue().caseUpcProperty());
        unitListColumn.setCellValueFactory(cellData -> cellData.getValue().unitListProperty());

        skuSpecificationTable.setItems(MenuController); // getSkus
        skuSpecificationTable.setEditable(true);


        flavorDescriptionColumn.setCellFactory(tc -> new CustomTextCell<>());
        casePackColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casePackProperty());
        unitWeightColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
        grossCaseWeightColumn.setCellValueFactory(cellData -> cellData.getValue().grossCaseWeightProperty());
        netCaseWeightColumn.setCellValueFactory(cellData -> cellData.getValue().netCaseWeightProperty());
        caseTareWeightColumn.setCellValueFactory(cellData -> cellData.getValue().caseTareWeightProperty());
        caseCubeColumn.setCellValueFactory(cellData -> cellData.getValue().caseCubeProperty());
        caseLengthColumn.setCellValueFactory(cellData -> cellData.getValue().caseLengthProperty());
        caseWidthColumn.setCellValueFactory(cellData -> cellData.getValue().caseWidthProperty());
        caseHeightColumn.setCellValueFactory(cellData -> cellData.getValue().caseHeightProperty();
        unitLengthColumn.setCellValueFactory(cellData -> cellData.getValue().unitLengthProperty());
        unitWidthColumn.setCellValueFactory(cellData -> cellData.getValue().unitWidthProperty());
        unitHeightColumn.setCellValueFactory(cellData -> cellData.getValue().unitHeightProperty());
        casesPerLayerColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerLayerProperty());
        layersPerPalletColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().layersPerPalletProperty());
        casesPerPalletColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerPalletProperty());
        palletHeightColumn.setCellValueFactory(cellData -> cellData.getValue().palletHeightProperty());
        palletWeightColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletWeightProperty());
        palletsPerTruckColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletsPerTruckProperty());
        deliveredShelfLifeColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().deliveredShelfLifeProperty());
        packageUpcColumn.setCellFactory(tc -> new CustomTextCell<>());
        caseUpcColumn.setCellFactory(tc -> new CustomTextCell<>());
        unitListColumn.setCellValueFactory(cellData -> cellData.getValue().unitListProperty());
        brandNameColumn.setCellFactory(tc -> new CustomTextCell<>());
        productClassColumn.setCellFactory(tc -> new CustomTextCell<>());
        listCostColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
        baseFreightColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
        fobCostColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
        net1GoalColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
        blendedCogsColumn.setCellFactory(tc -> new NumberEditCell("$", ""));
        elasticityMultipleColumn.setCellFactory(tc -> new NumberEditCell("", " : 1"));
//        brandNameColumn.setCellValueFactory(tc-> new Button());
        specificationColumn.setCellFactory(ActionButtonTableCell.<Product>forTableColumn("Edit", (Product p) -> {
            try {
                FXMLLoader productSkuSpecificationsLoader = App.createFXMLLoader("productSkuSpecifications");
                App.setSceneRoot(productSkuSpecificationsLoader.load());

                ProductSkuSpecificationsController productSkuSpecificationsController = productSkuSpecificationsLoader.getController();
                productSkuSpecificationsController.setProduct(p);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
//            String retailerName = RetailerNamePopup.display("Add a new Retailer", "Type the Name of the Retailer you'd like to add");
//            Retailer retailer = new Retailer();
//            retailer.setRetailerName(retailerName);
            return p;
        }));

//        addButton.setOnAction(e -> {
//            String retailerName = RetailerNamePopup.display("Add a new Retailer", "Type the Name of the Retailer you'd like to add");
//            Retailer retailer = new Retailer();
//            retailer.setRetailerName(retailerName);
//            observableRetailerList.add(retailer);
//        });
        janColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        febColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        marColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        aprColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        mayColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        junColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        julColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        augColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        sepColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        octColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        novColumn.setCellFactory(tc -> new NumberEditCell("", ""));
        decColumn.setCellFactory(tc -> new NumberEditCell("", ""));
    }

    public void setProduct(Product p) {
    }
}
