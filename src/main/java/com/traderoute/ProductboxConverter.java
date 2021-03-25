package com.traderoute;

import javafx.util.StringConverter;

public class ProductboxConverter extends StringConverter<RetailerProduct>{
    private String type;
    public ProductboxConverter(String type){
        super();
        this.type = type;
    }
        @Override
        public String toString(RetailerProduct retailerProduct) {
            if (retailerProduct != null) {
                if (type.equals("product")) {
                    return retailerProduct.getProduct().getProductClass();
                }
                if (type.equals("brand")) {
                    return retailerProduct.getProduct().getBrandName();
                }
        }
            return null;
        }

        @Override
        public RetailerProduct fromString(String string) {
            return null;
        }
}
