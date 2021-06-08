package com.traderoute.data;

import com.traderoute.cells.CellSpecs;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class Summary {
    private CellSpecs specs;
    private SimpleStringProperty summaryType;
    private SimpleObjectProperty<BigDecimal> summaryValue;

    public Summary(CellSpecs specs, String summaryType, BigDecimal summaryValue) {
        this.specs = specs;
        this.summaryType = new SimpleStringProperty(summaryType);
        this.summaryValue = new SimpleObjectProperty<>(summaryValue);
    }

    public CellSpecs getSpecs() {
        return specs;
    }

    public String getSummaryType() {
        return summaryType.get();
    }

    public SimpleStringProperty summaryTypeProperty() {
        return summaryType;
    }

    public void setSummaryType(String summaryType) {
        this.summaryType.set(summaryType);
    }

    public BigDecimal getSummaryValue() {
        return summaryValue.get();
    }

    public SimpleObjectProperty<BigDecimal> summaryValueProperty() {
        return summaryValue;
    }

    public void setSummaryValue(BigDecimal summaryValue) {
        this.summaryValue.set(summaryValue);
    }
}
