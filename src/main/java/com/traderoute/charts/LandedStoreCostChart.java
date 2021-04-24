package com.traderoute.charts;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import static javafx.collections.FXCollections.observableArrayList;

public class LandedStoreCostChart extends BarChart implements UpdatableChart{
//    @FXML
//    private BarChart<String, BigDecimal> barchart;

    public LandedStoreCostChart(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        this.setId("landedStoreCostChart");
        this.setAnimated(false);
        this.setLegendVisible(false);
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
