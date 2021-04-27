package com.traderoute;

import com.traderoute.data.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import static javafx.collections.FXCollections.observableArrayList;

public class ProductClassComboBox extends ComboBox<Product> {
    public ProductClassComboBox(){
        super();
        this.setPromptText("Please Select a Product Class");
        this.setId("productClassBox");
        this.setConverter(new StringConverter<Product>() {
            @Override
            public String toString (Product product) {
                if (product != null) {
                    return product.getProductClass();
                }
                return null;
            }
            @Override
            public Product fromString(String string) {
                return null;
            }
        });

    }
    public void setCorrespondingItems(ObservableList<Product> products, Product productWithSelectedBrandName){
        this.setItems(getCorrectProductClasses(products, productWithSelectedBrandName));
    }
    public ObservableList<Product> getCorrectProductClasses(final ObservableList<Product> products,
                                                            Product productWithSelectedBrandName) {
        ObservableList<Product> correspondingProductClasses = observableArrayList();
        // Set up product combobox and make it display product class
        for (Product product : products) {
            if (product.getBrandName().equals(productWithSelectedBrandName.getBrandName())){
                correspondingProductClasses.add(product);
            }
        }
        return correspondingProductClasses;
    }

}
