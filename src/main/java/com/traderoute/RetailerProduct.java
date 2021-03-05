package com.traderoute;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class RetailerProduct {
    private SimpleObjectProperty<Retailer> retailer;
    private SimpleObjectProperty<Product> product;
    private SimpleObjectProperty<ObservableList<RTMOption>> rtmOptions;
    private SimpleObjectProperty<ObservableList<Sku>> skus;
    private SimpleObjectProperty<ObservableList<Meeting>> meetings;


    public RetailerProduct(Retailer retailer, Product product, ObservableList rtmOptions, ObservableList skus, ObservableList meetings) {
        this.retailer = new SimpleObjectProperty<>(retailer);
        this.product = new SimpleObjectProperty<>(product);
        this.rtmOptions = new SimpleObjectProperty<>(rtmOptions);
        this.skus = new SimpleObjectProperty<>(skus);
        this.meetings = new SimpleObjectProperty<>(meetings);
    }
    public RetailerProduct() {
        this.retailer = new SimpleObjectProperty<>();
        this.product = new SimpleObjectProperty<>();
        this.rtmOptions = new SimpleObjectProperty<>();
        this.skus = new SimpleObjectProperty<>();
        this.meetings = new SimpleObjectProperty<>();
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public SimpleObjectProperty<Retailer> retailerProperty() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
    }

    public Product getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<Product> productProperty() {
        return product;
    }

    public void setProduct(Product product) {
        this.product.set(product);
    }

    public ObservableList<RTMOption> getRtmOptions() {
        return rtmOptions.get();
    }

    public SimpleObjectProperty<ObservableList<RTMOption>> rtmOptionsProperty() {
        return rtmOptions;
    }

    public void setRtmOptions(ObservableList<RTMOption> rtmOptions) {
        this.rtmOptions.set(rtmOptions);
    }

    public ObservableList<Sku> getSkus() {
        return skus.get();
    }

    public SimpleObjectProperty<ObservableList<Sku>> skusProperty() {
        return skus;
    }

    public void setSkus(ObservableList<Sku> skus) {
        this.skus.set(skus);
    }

    public ObservableList<Meeting> getMeetings() {
        return meetings.get();
    }

    public SimpleObjectProperty<ObservableList<Meeting>> meetingsProperty() {
        return meetings;
    }

    public void setMeetings(ObservableList<Meeting> meetings) {
        this.meetings.set(meetings);
    }
}
