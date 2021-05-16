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
    private SimpleIntegerProperty yearOneStoreCount;
    private SimpleObjectProperty<BigDecimal> minOverride, weeklyUSFWAtMin, everydayGPM, spoilsAndFees;

    public RetailerProduct(Product product, ObservableList rtmOptions, ObservableList skus, ObservableList meetings,
            ObservableList promoPlans, int yearOneStoreCount) {
        this.product = new SimpleObjectProperty<>(product);
        this.rtmOptions = new SimpleObjectProperty<>(rtmOptions);
        this.skus = new SimpleObjectProperty<>(skus);
        this.meetings = new SimpleObjectProperty<>(meetings);
        this.promoPlans = new SimpleObjectProperty<>(promoPlans);

        this.yearOneStoreCount = new SimpleIntegerProperty(yearOneStoreCount);
        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.everydayGPM = new SimpleObjectProperty<>();
        this.spoilsAndFees = new SimpleObjectProperty<>();
    }

    public RetailerProduct() {
        this.product = new SimpleObjectProperty<>();
        this.rtmOptions = new SimpleObjectProperty<>();
        this.skus = new SimpleObjectProperty<>();
        this.meetings = new SimpleObjectProperty<>();
        this.promoPlans = new SimpleObjectProperty<>();

        this.yearOneStoreCount = new SimpleIntegerProperty();
        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.everydayGPM = new SimpleObjectProperty<>();
        this.spoilsAndFees = new SimpleObjectProperty<>();
    }

    public int getYearOneStoreCount() {
        return yearOneStoreCount.get();
    }

    public SimpleIntegerProperty yearOneStoreCountProperty() {
        return yearOneStoreCount;
    }

    public void setYearOneStoreCount(int yearOneStoreCount) {
        this.yearOneStoreCount.set(yearOneStoreCount);
    }

    public BigDecimal getMinOverride() {
        return minOverride.get();
    }

    public SimpleObjectProperty<BigDecimal> minOverrideProperty() {
        return minOverride;
    }

    public void setMinOverride(BigDecimal minOverride) {
        this.minOverride.set(minOverride);
    }

    public BigDecimal getWeeklyUSFWAtMin() {
        return weeklyUSFWAtMin.get();
    }

    public SimpleObjectProperty<BigDecimal> weeklyUSFWAtMinProperty() {
        return weeklyUSFWAtMin;
    }

    public void setWeeklyUSFWAtMin(BigDecimal weeklyUSFWAtMin) {
        this.weeklyUSFWAtMin.set(weeklyUSFWAtMin);
    }

    public BigDecimal getEverydayGPM() {
        return everydayGPM.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
        return everydayGPM;
    }

    public void setEverydayGPM(BigDecimal everydayGPM) {
        this.everydayGPM.set(everydayGPM);
    }

    public BigDecimal getSpoilsAndFees() {
        return spoilsAndFees.get();
    }

    public SimpleObjectProperty<BigDecimal> spoilsAndFeesProperty() {
        return spoilsAndFees;
    }

    public void setSpoilsAndFees(BigDecimal spoilsAndFees) {
        this.spoilsAndFees.set(spoilsAndFees);
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
}
