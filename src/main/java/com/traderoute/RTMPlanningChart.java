package com.traderoute;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public abstract class RTMPlanningChart extends BarChart {

    public RTMPlanningChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setLegendVisible(false);
        this.setAnimated(false);
    }
    public abstract void updateChart(ObservableList<RTMOption> rtmOptions);
}
