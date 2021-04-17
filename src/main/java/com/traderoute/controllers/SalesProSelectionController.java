package com.traderoute.controllers;

import com.jfoenix.controls.JFXListView;
import com.traderoute.App;
import com.traderoute.data.Product;
import com.traderoute.data.Retailer;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesProSelectionController implements Initializable {
    @FXML
    private TextField retailerSelectField;

    @FXML
    private JFXListView<Retailer> retailerListview;

    private ObservableList<Retailer> retailers = FXCollections.observableArrayList();
    FilteredList<Retailer> retailerFilterItems;

    @FXML
    private JFXListView<Retailer> selectedRetailerListview;

    private ObservableList<Retailer> selectedRetailers = FXCollections.observableArrayList();

    @FXML
    private TextField productSelectField;

    @FXML
    private JFXListView<Product> productListview;

    private ObservableList<Product> products = FXCollections.observableArrayList();
    FilteredList<Product> productFilterItems;

    @FXML
    private JFXListView<Product> selectedProductListview;

    private ObservableList<Product> selectedProducts = FXCollections.observableArrayList();

    private Retailer emptyRetailer = new Retailer();
    private Product emptyProduct = new Product();


    @FXML
    private RadioButton committedRadiobutton;
    @FXML
    private RadioButton uncommittedRadiobutton;

    @FXML
    private Boolean committed;

    @FXML
    private VBox mainVbox;

    private HostServices hostServices;

//    p

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retailerListview.setItems(retailerFilterItems);

        ToggleGroup group = new ToggleGroup();

        committedRadiobutton.setToggleGroup(group);
        committedRadiobutton.setSelected(true);
        uncommittedRadiobutton.setToggleGroup(group);
        retailerListview.setCellFactory(param -> new ListCell<Retailer>() {
            @Override
            protected void updateItem(Retailer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getRetailerName() == null) {
                    setText("");
                } else {
                    setText(item.getRetailerName());
                    setOnMouseClicked(ev -> {
                        if (!selectedRetailers.contains(item)) {
                            checkEmptyRetailer(retailers, selectedRetailers);
                            selectedRetailers.add(retailerListview.getSelectionModel().getSelectedItem());
                            retailers.remove(retailerListview.getSelectionModel().getSelectedItem());
                            checkEmptyRetailer(retailers, selectedRetailers);
                        }
                    });
                }
            }
        });
        selectedRetailerListview.setCellFactory(param -> new ListCell<Retailer>() {
            @Override
            protected void updateItem(Retailer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getRetailerName() == null) {
                    setText("");
                } else {
                    setText(item.getRetailerName());
                    setOnMouseClicked(ev -> {
                        if (!retailers.contains(item)) {
                            System.out.println("Its doing the second");
                            checkEmptyRetailer(selectedRetailers, retailers);
                            retailers.add(selectedRetailerListview.getSelectionModel().getSelectedItem());
                            selectedRetailers.remove(selectedRetailerListview.getSelectionModel().getSelectedItem());
                            checkEmptyRetailer(selectedRetailers, retailers);
                        }
                    });
                }
            }
        });

        productListview.setItems(productFilterItems);


        productListview.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getProductClass() == null) {
                    setText("");
                } else {
                    setText(item.getProductClass() + " ("+ item.getBrandName()+")" );
                    setOnMouseClicked(ev -> {
                        if (!selectedProducts.contains(item)) {
                            checkEmptyProduct(products, selectedProducts);
                            selectedProducts.add(productListview.getSelectionModel().getSelectedItem());
                            products.remove(productListview.getSelectionModel().getSelectedItem());
                            checkEmptyProduct(products, selectedProducts);
                        }
                    });
                }
            }
        });
        selectedProductListview.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getProductClass() == null) {
                    setText("");
                } else {
                    setText(item.getProductClass() + " ("+ item.getBrandName()+")" );
                    setOnMouseClicked(ev -> {
                        if (!products.contains(item)) {
                            checkEmptyProduct(selectedProducts, products);
                            products.add(selectedProductListview.getSelectionModel().getSelectedItem());
                            selectedProducts.remove(selectedProductListview.getSelectionModel().getSelectedItem());
                            checkEmptyProduct(selectedProducts, products);
                        }
                    });
                }
            }
        });
        Platform.runLater(() -> {
            Window window =  mainVbox.getScene().getWindow();
            window.setHeight(mainVbox.getPrefHeight());
            window.setWidth(mainVbox.getPrefWidth());
            window.centerOnScreen();
        });
    }
    public HostServices getHostServices() {
        return hostServices ;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }


    public void checkEmptyRetailer(ObservableList<Retailer> removedItemList,ObservableList<Retailer> addedItemList ){
        if (removedItemList.size()==0){
            removedItemList.add(emptyRetailer);
        } if (addedItemList.size()==1 && addedItemList.contains(emptyRetailer)){
            addedItemList.remove(emptyRetailer);
        }
    }
    public void checkEmptyProduct(ObservableList<Product> removedItemList,ObservableList<Product> addedItemList ){
        if (removedItemList.size()==0){
            removedItemList.add(emptyProduct);
        } if (addedItemList.size()==1 && addedItemList.contains(emptyProduct)){
            addedItemList.remove(emptyProduct);
        }
    }



    public void selectAllRetailersEvent(ActionEvent event){
        for (int i=retailers.size()-1; i>=0 ; i--){
            if (!retailers.contains(emptyRetailer)) {
                checkEmptyRetailer(retailers, selectedRetailers);
                selectedRetailers.add(retailers.get(i));
                retailers.remove(retailers.get(i));
                checkEmptyRetailer(retailers, selectedRetailers);
            }
        }
    }
    public void selectAllProductsEvent(ActionEvent event){
        for (int i=products.size()-1; i>=0 ; i--){
            if (!products.contains(emptyProduct)) {
                checkEmptyProduct(products, selectedProducts);
                selectedProducts.add(products.get(i));
                products.remove(products.get(i));
                checkEmptyProduct(products, selectedProducts);
            }
        }
    }

    public void unselectAllRetailersEvent(ActionEvent event){
        for (int i=selectedRetailers.size()-1; i>=0 ; i--){
            if (!selectedRetailers.contains(emptyRetailer)) {
                checkEmptyRetailer(selectedRetailers, retailers);
                retailers.add(selectedRetailers.get(i));
                selectedRetailers.remove(selectedRetailers.get(i));
                checkEmptyRetailer(selectedRetailers, retailers);
            }
        }
    }

    public void unselectAllProductsEvent(ActionEvent event){
        for (int i=selectedProducts.size()-1; i>=0 ; i--){
            if (!selectedProducts.contains(emptyProduct)) {
                checkEmptyProduct(selectedProducts, products);
                products.add(selectedProducts.get(i));
                selectedProducts.remove(selectedProducts.get(i));
                checkEmptyProduct(selectedProducts, products);
            }
        }
    }



    public void changeCommittedEvent(ActionEvent event){
//        if (committedRadiobutton.isSelected()){
//            committed.set(true);
//        } else if (uncommittedRadiobutton.isSelected()){
//            committed.set(false);
//        } else {
//            System.out.println("Show Notification: Neither Radio Button is selected.");
//        }
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader menuLoader = App.createFXMLLoader("menu");
        App.setSceneRoot(menuLoader.load());
    }
    @FXML
    public void switchToSalesProForma(ActionEvent event)throws IOException {
        FXMLLoader salesProFormaLoader = App.createFXMLLoader("salesProForma");
        App.setSceneRoot(salesProFormaLoader.load());

        SalesProFormaController salesProFormaController = salesProFormaLoader.getController();
        if (committedRadiobutton.isSelected()){
            committed= true;
        } else if (uncommittedRadiobutton.isSelected()){
            committed= false;
        } else {
            System.out.println("Show Notification: Neither Radio Button is selected.");
        }
        salesProFormaController.setHostServices(getHostServices());
        salesProFormaController.setCommitted(committed);
        salesProFormaController.setProducts(selectedProducts);
        salesProFormaController.setRetailers(selectedRetailers);

    }

    public ObservableList<Retailer> getRetailers() {
        return retailers;
    }

    public void setRetailers(ObservableList<Retailer> retailers) {
        this.retailers = retailers;
        retailerFilterItems = new FilteredList<>(retailers);
        retailerListview.setItems(retailerFilterItems);
        // bind predicate to text filterInput text
        retailerFilterItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            String text = retailerSelectField.getText();
            if (text == null || text.isEmpty()) {
                return null;
            } else {
                final String uppercase = text.toUpperCase();
                return (retailer) -> retailer.getRetailerName().toUpperCase().contains(uppercase);
            }
        }, retailerSelectField.textProperty()));
        selectedRetailerListview.setItems(selectedRetailers);
        selectedRetailerListview.getItems().add(emptyRetailer);
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
        productFilterItems = new FilteredList<>(products);
        productListview.setItems(productFilterItems);
        // bind predicate to text filterInput text
        productFilterItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            String text = productSelectField.getText();
            if (text == null || text.isEmpty()) {
                return null;
            } else {
                final String uppercase = text.toUpperCase();
                return (product) -> product.getProductClass().toUpperCase().contains(uppercase) || product.getBrandName().toUpperCase().contains(uppercase);
            }
        }, productSelectField.textProperty()));
        selectedProductListview.setItems(selectedProducts);
        selectedProductListview.getItems().add(emptyProduct);
    }
}
