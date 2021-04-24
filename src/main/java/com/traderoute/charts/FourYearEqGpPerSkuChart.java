package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;

import static javafx.collections.FXCollections.observableArrayList;

public class FourYearEqGpPerSkuChart extends RTMPlanningChart {

    public FourYearEqGpPerSkuChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setId("fourYearEqGpPerSkuChart");

    }

    @Override
    public BigDecimal getYValue(RTMOption rtmOption) {
        return rtmOption.getFourYearEqGpPerSku();
    }
}
