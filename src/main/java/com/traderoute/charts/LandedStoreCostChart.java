package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.math.BigDecimal;

import static javafx.collections.FXCollections.observableArrayList;

public class LandedStoreCostChart extends RTMPlanningChart {

    public LandedStoreCostChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setId("landedStoreCostChart");


    }

    @Override
    public BigDecimal getYValue(RTMOption rtmOption) {
        return rtmOption.getLandedStoreCost();
    }
}
