package com.traderoute;

import javafx.util.StringConverter;

public class ComboboxConverter extends StringConverter<Product>{
    private String type;
    public ComboboxConverter(String type){
        super();
        this.type = type;
    }
        @Override
        public String toString(Product product) {
            if (product!= null) {
                if (type.equals("product")) {
                    return product.getProductClass();
                }
                if (type.equals("brand")){
                    return product.getBrandName();
                }
            }
            return null;
        }

        @Override
        public Product fromString(String string) {
            return null;
        }
}
