package com.traderoute;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

import java.math.BigDecimal;

public class NumberTextField extends TextField {
    private Number maxValue;
    private Number minValue;
    private Number defaultValue;
    private boolean positiveOnly;
    public NumberTextField (Number defaultValue, Number minValue, Number maxValue, boolean positiveOnly) {
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.positiveOnly = positiveOnly;
        this.setText(defaultValue.toString());
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Enter a value between "+ minValue + " and " + maxValue + ", please." +
                        " We have\nreset the field to its default value.", ButtonType.OK);

        setOnAction((args) -> {
                    if (new BigDecimal(this.getText()).compareTo(new BigDecimal(maxValue.toString())) > 0 ||
                            new BigDecimal(this.getText()).compareTo(new BigDecimal(minValue.toString())) < 0) {
                        this.setText(defaultValue.toString());
                        alert.showAndWait();
                        }});
    }

    public Number getDefaultValue() {
        return defaultValue;
    }
}
