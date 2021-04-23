package com.traderoute.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class Retailer {
    private SimpleStringProperty retailerName;
    private SimpleObjectProperty<BigDecimal> everydayGPM, spoilsFees;
    private SimpleIntegerProperty yearOneStoreCount;
    private SimpleIntegerProperty currentRetailerProductIndex;
    private SimpleObjectProperty<ObservableList<RetailerProduct>> retailerProducts;

    public Retailer(String retailerName, ObservableList<RetailerProduct> retailerProducts,
            Integer currentRetailerProductIndex, BigDecimal everydayGPM, Integer yearOneStoreCount,
            BigDecimal spoilsFees) {
        this.retailerName = new SimpleStringProperty(retailerName);
        this.currentRetailerProductIndex = new SimpleIntegerProperty(currentRetailerProductIndex);
        this.retailerProducts = new SimpleObjectProperty<>(retailerProducts);
        this.everydayGPM = new SimpleObjectProperty(everydayGPM);
        this.yearOneStoreCount = new SimpleIntegerProperty(yearOneStoreCount);
        this.spoilsFees = new SimpleObjectProperty<>(spoilsFees);
    }

    public Retailer() {
        this.retailerName = new SimpleStringProperty();
        this.currentRetailerProductIndex = new SimpleIntegerProperty();
        this.retailerProducts = new SimpleObjectProperty<>();
        this.everydayGPM = new SimpleObjectProperty();
        this.yearOneStoreCount = new SimpleIntegerProperty();
        this.spoilsFees = new SimpleObjectProperty<>();
    }

    public Integer getCurrentRetailerProductIndex() {
        return currentRetailerProductIndex.get();
    }

    public SimpleIntegerProperty currentRetailerProductIndexProperty() {
        return currentRetailerProductIndex;
    }

    public void setcurrentRetailerProductIndex(Integer currentRetailerProductIndex) {
        this.currentRetailerProductIndex.set(currentRetailerProductIndex);
    }

    public ObservableList<RetailerProduct> getRetailerProducts() {
        return retailerProducts.get();
    }

    public SimpleObjectProperty<ObservableList<RetailerProduct>> retailerProductsProperty() {
        return retailerProducts;
    }

    public void setRetailerProducts(ObservableList<RetailerProduct> retailerProducts) {
        this.retailerProducts.set(retailerProducts);
    }

    public String getRetailerName() {
        return retailerName.get();
    }

    public SimpleStringProperty retailerNameProperty() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName.set(retailerName);
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

    public BigDecimal getEverydayGPM() {
        return everydayGPM.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
        return everydayGPM;
    }

    public void setEverydayGPM(BigDecimal everydayGPM) {
        this.everydayGPM.set(everydayGPM);
    }

    public BigDecimal getSpoilsFees() {
        return spoilsFees.get();
    }

    public SimpleObjectProperty<BigDecimal> spoilsFeesProperty() {
        return spoilsFees;
    }

    public void setSpoilsFees(BigDecimal spoilsFees) {
        this.spoilsFees.set(spoilsFees);
    }
}
