package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;

public interface UpdatableChart {
    public void updateChart(ObservableList<RTMOption> rtmOptions);
}
