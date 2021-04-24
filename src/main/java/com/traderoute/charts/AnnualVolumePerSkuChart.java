package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;

import static javafx.collections.FXCollections.observableArrayList;

public class AnnualVolumePerSkuChart extends RTMPlanningChart {

    public AnnualVolumePerSkuChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setId("annualVolumePerSkuChart");

    }
    @Override
    public BigDecimal getYValue(RTMOption rtmOption) {
        return rtmOption.getAnnualVolumePerSku();
    }
}
