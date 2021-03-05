package com.traderoute;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class Summary {
    private SimpleStringProperty summaryType;
    private SimpleObjectProperty<BigDecimal> summaryValue;

    public Summary(String summaryType, BigDecimal summaryValue) {
        this.summaryType = new SimpleStringProperty(summaryType);
        this.summaryValue = new SimpleObjectProperty<>(summaryValue);
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
