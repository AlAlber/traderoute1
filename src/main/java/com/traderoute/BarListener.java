package com.traderoute;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BarListener<BigDecimal> implements ChangeListener<BigDecimal> {
        private boolean changing;
        private int chartNumber;
        private int valueToUpdate;
        private firstTableController controller;
        public BarListener(firstTableController controller, int chartNumber, int valueToUpdate){
            this.controller = controller;
            this.chartNumber = chartNumber;
            this.valueToUpdate = valueToUpdate;
        }
        @Override
        public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
            if (!changing) {
                try {
                    changing = true;
                    List<BarChart> chartList = new ArrayList<>();
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.add(controller.getLandedStoreCostChart());
                    chartList.get(chartNumber).getData().clear();
                    chartList.get(chartNumber).setData(FXCollections.observableArrayList(new XYChart.Series[]{controller.getChartData(valueToUpdate)}));
                } finally {
                    changing = false;
                }
            }
        }
}
