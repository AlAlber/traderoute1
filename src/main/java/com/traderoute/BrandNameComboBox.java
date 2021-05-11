package com.traderoute;

import com.traderoute.data.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import static javafx.collections.FXCollections.observableArrayList;

public class BrandNameComboBox extends ComboBox<Product> {

    public BrandNameComboBox() {
        super();
        this.setPromptText("Please Select a Product Class");
        this.setId("brandNameBox");
        this.setConverter(new StringConverter<Product>() {
            @Override
        public String toString (Product product) {
            if (product != null) {
                return product.getBrandName(); //Hello
            }
            return null;
        }
        @Override
        public Product fromString(String string) {
            return null;
        }
        });
    }
    public void setUniqueItems(ObservableList<Product> products){
        this.setItems(getUniqueBrandNames(products));
    }

    public static ObservableList<Product> getUniqueBrandNames(final ObservableList<Product> products) {
        ObservableList<Product> uniqueBrandNames = observableArrayList();
        ObservableList<String> uniqueBrandNameStrs = observableArrayList();
        for (Product product : products) {
            if (!uniqueBrandNameStrs.contains(product.getBrandName())) {
                uniqueBrandNameStrs.add(product.getBrandName());
                uniqueBrandNames.add(product);
            }
        }
        return uniqueBrandNames;
    }

}
