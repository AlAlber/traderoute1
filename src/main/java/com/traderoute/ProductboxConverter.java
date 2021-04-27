package com.traderoute;

import com.traderoute.data.Product;
import javafx.util.StringConverter;

public class ProductboxConverter extends StringConverter<Product> {
    private String type;

    public ProductboxConverter(String type) {
        super();
        this.type = type;
    }

    @Override
    public String toString(Product retailerProduct) {
        if (retailerProduct != null) {
            if (type.equals("product")) {
                return retailerProduct.getProductClass();
            }
            if (type.equals("brand")) {
                return retailerProduct.getBrandName();
            }
//            return this.getProductString();
        }
        return null;
    }

//    private abstract String getProductString();

    @Override
    public Product fromString(String string) {
        return null;
    }
}
