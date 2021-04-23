package com.traderoute;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;

import java.math.BigDecimal;

public class LandedStoreCostChart<String, BigDecimal> extends BarChart<String, BigDecimal> implements UpdatableChart {

    public LandedStoreCostChart(Axis<String> axis, Axis<BigDecimal> axis1) {
        super(axis, axis1);
    }

    @Override
    public void updateChart() {
        
    }

}
