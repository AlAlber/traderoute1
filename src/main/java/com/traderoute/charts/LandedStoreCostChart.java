package com.traderoute.charts;

import com.traderoute.CustomBarChart;
import com.traderoute.charts.UpdatableChart;
import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;

public class LandedStoreCostChart<String, BigDecimal> extends BarChart implements UpdatableChart{
    @FXML
    private BarChart<String, BigDecimal> barchart;

    public LandedStoreCostChart(Axis axis, Axis axis1) {
        super(axis, axis1);
    }


    @Override
    public void updateChart(ObservableList<RTMOption> rtmOptions) {
        XYChart.Series<java.lang.String, java.math.BigDecimal> barChartData = new XYChart.Series();
        for (RTMOption row : rtmOptions) {
            barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getLandedStoreCost()));
        }
        this.setData(observableArrayList(new XYChart.Series[]{barChartData}));
    }



}
