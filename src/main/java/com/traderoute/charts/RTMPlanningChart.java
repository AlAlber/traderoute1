package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import jfxtras.scene.layout.HBox;

import static javafx.collections.FXCollections.observableArrayList;

public abstract class RTMPlanningChart extends BarChart {

    public RTMPlanningChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setLegendVisible(false);
        this.setAnimated(false);
    }

    public abstract void updateChart(ObservableList<RTMOption> rtmOptions);
}
