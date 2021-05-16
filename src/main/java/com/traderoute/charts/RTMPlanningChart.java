package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.math.BigDecimal;

import static javafx.collections.FXCollections.observableArrayList;

public abstract class RTMPlanningChart extends BarChart {

    public RTMPlanningChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setLegendVisible(false);
        this.setAnimated(false);
        this.setMinWidth(150);
        this.setPrefWidth(170);
    }

    public void updateChart(ObservableList<RTMOption> rtmOptions) {
        XYChart.Series<java.lang.String, java.math.BigDecimal> barChartData = new XYChart.Series();
        for (RTMOption row : rtmOptions) {
            barChartData.getData().add(new XYChart.Data(row.getRtmName(), getYValue(row)));
        }
        this.setData(observableArrayList(new XYChart.Series[]{barChartData}));
    }

    public abstract BigDecimal getYValue(RTMOption rtmOption);
}
