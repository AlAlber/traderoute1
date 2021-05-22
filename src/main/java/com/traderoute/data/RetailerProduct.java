package com.traderoute.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class RetailerProduct {
    private SimpleObjectProperty<Product> product;
    private SimpleObjectProperty<ObservableList<RTMOption>> rtmOptions;
    private SimpleObjectProperty<ObservableList<Sku>> skus;
    private SimpleObjectProperty<ObservableList<Meeting>> meetings;
    private SimpleObjectProperty<ObservableList<PromoPlan>> promoPlans;
//    private SimpleIntegerProperty yearOneStoreCount;
    private SimpleObjectProperty<RetailerProductSpecs> specs;
//    private SimpleObjectProperty<BigDecimal> minOverride, weeklyUSFWAtMin, everydayGpm, spoilsAndFees;

    public RetailerProduct(Product product, ObservableList rtmOptions, ObservableList skus, ObservableList meetings,
            ObservableList promoPlans, RetailerProductSpecs specs) {
        this.product = new SimpleObjectProperty<>(product);
        this.rtmOptions = new SimpleObjectProperty<>(rtmOptions);
        this.skus = new SimpleObjectProperty<>(skus);
        this.meetings = new SimpleObjectProperty<>(meetings);
        this.promoPlans = new SimpleObjectProperty<>(promoPlans);
        this.specs = new SimpleObjectProperty<>(specs);
    }

    public RetailerProduct() {
        this.product = new SimpleObjectProperty<>();
        this.rtmOptions = new SimpleObjectProperty<>();
        this.skus = new SimpleObjectProperty<>();
        this.meetings = new SimpleObjectProperty<>();
        this.promoPlans = new SimpleObjectProperty<>();
    }

    public ObservableList<PromoPlan> getPromoPlans() {
        return promoPlans.get();
    }

    public SimpleObjectProperty<ObservableList<PromoPlan>> promoPlansProperty() {
        return promoPlans;
    }

    public void setPromoPlans(ObservableList<PromoPlan> promoPlans) {
        this.promoPlans.set(promoPlans);
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

    public RetailerProductSpecs getSpecs() {
        return specs.get();
    }

    public SimpleObjectProperty<RetailerProductSpecs> specsProperty() {
        return specs;
    }

    public void setSpecs(RetailerProductSpecs specs) {
        this.specs.set(specs);
    }
}
