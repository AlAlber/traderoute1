package com.traderoute;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import org.testfx.framework.junit5.Init;

import java.io.IOError;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsPricingController implements Initializable {

    @FXML
    private TableView<Product> productsPricingTable;

    @FXML
    private TableColumn<Product, String> brandNameColumn;

    @FXML
    private TableColumn<Product, String> productClassColumn;

    @FXML
    private TableColumn<Product, BigDecimal> listCostColumn;

    @FXML
    private TableColumn<Product, BigDecimal> baseFreightColumn;

    @FXML
    private TableColumn<Product, BigDecimal> fobCostColumn;

    @FXML
    private TableColumn<Product, BigDecimal> net1GoalColumn;

    @FXML
    private TableColumn<Product, BigDecimal> blendedCogsColumn;

    @FXML
    private TableColumn<Product, BigDecimal> elasticityMultipleColumn;

    @FXML
    private TableColumn<Product, BigDecimal> janColumn;

    @FXML
    private TableColumn<Product, BigDecimal> febColumn;

    @FXML
    private TableColumn<Product, BigDecimal> marColumn;

    @FXML
    private TableColumn<Product, BigDecimal> aprColumn;

    @FXML
    private TableColumn<Product, BigDecimal> mayColumn;

    @FXML
    private TableColumn<Product, BigDecimal> junColumn;

    @FXML
    private TableColumn<Product, BigDecimal> julColumn;

    @FXML
    private TableColumn<Product, BigDecimal> augColumn;

    @FXML
    private TableColumn<Product, BigDecimal> sepColumn;

    @FXML
    private TableColumn<Product, BigDecimal> octColumn;

    @FXML
    private TableColumn<Product, BigDecimal> novColumn;

    @FXML
    private TableColumn<Product, BigDecimal> decColumn;

    @FXML
    private TableColumn<Product, Button> specificationColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productsPricingTable.setItems(MenuController.getExampleProducts());
        productsPricingTable.setEditable(true);
        brandNameColumn.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
        productClassColumn.setCellValueFactory(cellData -> cellData.getValue().productClassProperty());
        listCostColumn.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().unitListCostProperty());
        baseFreightColumn.setCellValueFactory(cellData -> cellData.getValue().unitBaseFreightProperty());
        fobCostColumn.setCellValueFactory(cellData -> cellData.getValue().unitFobCostProperty());
        net1GoalColumn.setCellValueFactory(cellData -> cellData.getValue().unitNet1GoalProperty());
        blendedCogsColumn.setCellValueFactory(cellData -> cellData.getValue().unitBlendedCogsProperty());
        elasticityMultipleColumn.setCellValueFactory(cellData -> cellData.getValue().elasticityMultipleProperty());

        janColumn.setCellValueFactory(cellData -> cellData.getValue().janIndiceProperty());
        febColumn.setCellValueFactory(cellData -> cellData.getValue().febIndiceProperty());
        marColumn.setCellValueFactory(cellData -> cellData.getValue().marIndiceProperty());
        aprColumn.setCellValueFactory(cellData -> cellData.getValue().aprIndiceProperty());
        mayColumn.setCellValueFactory(cellData -> cellData.getValue().mayIndiceProperty());
        junColumn.setCellValueFactory(cellData -> cellData.getValue().junIndiceProperty());
        julColumn.setCellValueFactory(cellData -> cellData.getValue().julIndiceProperty());
        augColumn.setCellValueFactory(cellData -> cellData.getValue().augIndiceProperty());
        sepColumn.setCellValueFactory(cellData -> cellData.getValue().sepIndiceProperty());
        octColumn.setCellValueFactory(cellData -> cellData.getValue().octIndiceProperty());
        novColumn.setCellValueFactory(cellData -> cellData.getValue().novIndiceProperty());
        decColumn.setCellValueFactory(cellData -> cellData.getValue().decIndiceProperty());



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
}
