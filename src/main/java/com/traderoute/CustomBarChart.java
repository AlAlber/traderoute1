package com.traderoute;

import com.traderoute.data.RTMOption;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;

public abstract class CustomBarChart extends VBox {
    @FXML
    private BarChart<String, BigDecimal> barchart;

    public CustomBarChart() throws IOException {
        FXMLLoader fxmlLoader = App.createFXMLLoader("customBarChart");
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        barchart.setAnimated(false);
        barchart.setLegendVisible(false);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public abstract void updateChart(ObservableList<RTMOption> rtmOptions);


    public void setData(ObservableList<XYChart.Series<String, BigDecimal>> observableArrayList){
        barchart.setData(observableArrayList);
    }
}
