package com.traderoute.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.math.BigDecimal;

public class RetailerProductSpecs {
    private SimpleIntegerProperty yearOneStoreCount;
    private SimpleObjectProperty<BigDecimal> minOverride, weeklyUSFWAtMin, everydayGpm, spoilsAndFees;

    public RetailerProductSpecs (int yearOneStoreCount, BigDecimal everydayGpm, BigDecimal spoilsAndFees,
                                BigDecimal weeklyUfswAtMin) {
        this.yearOneStoreCount = new SimpleIntegerProperty(yearOneStoreCount);
        this.everydayGpm = new SimpleObjectProperty<>(everydayGpm);
        this.spoilsAndFees = new SimpleObjectProperty<>(spoilsAndFees);
        this.minOverride = new SimpleObjectProperty<>(); // is updated dyamically
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>(weeklyUfswAtMin);
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

    public BigDecimal getEverydayGpm() {
        return everydayGpm.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayGpmProperty() {
        return everydayGpm;
    }

    public void setEverydayGpm(BigDecimal everydayGpm) {
        this.everydayGpm.set(everydayGpm);
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
}
