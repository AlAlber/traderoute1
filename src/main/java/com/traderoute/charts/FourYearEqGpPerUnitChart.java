package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import static javafx.collections.FXCollections.observableArrayList;

public class FourYearEqGpPerUnitChart extends RTMPlanningChart {

    public FourYearEqGpPerUnitChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setId("fourYearEqGpPerUnitChart");

    }

    @Override
    public void updateChart(ObservableList<RTMOption> rtmOptions) {
        XYChart.Series<java.lang.String, java.math.BigDecimal> barChartData = new XYChart.Series();
        for (RTMOption row : rtmOptions) {
            barChartData.getData().add(new XYChart.Data(row.getRTMName(), row.getFourYearEqGpPerUnit()));
        }
        this.setData(observableArrayList(new XYChart.Series[]{barChartData}));
    }
}
