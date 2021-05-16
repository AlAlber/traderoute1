package com.traderoute;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

import java.math.BigDecimal;

public abstract class NumberTextField extends TextField {
    private Number maxValue;
    private Number minValue;
    private Number defaultValue;
    private boolean positiveOnly;
    private SimpleObjectProperty<Number> value;
    public NumberTextField (Number defaultValue, Number minValue, Number maxValue, boolean positiveOnly) {
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.positiveOnly = positiveOnly;
        this.setText(defaultValue.toString());
        setValue(this.getValue());
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Enter a value between "+ minValue + " and " + maxValue + ", please." +
                        " We have\nreset the field to its default value.", ButtonType.OK);
        System.out.println("checking this?");
        setOnAction((args) -> {
                    if (new BigDecimal(this.getText()).compareTo(new BigDecimal(maxValue.toString())) > 0 ||
                            new BigDecimal(this.getText()).compareTo(new BigDecimal(minValue.toString())) < 0) {

                        this.setText(defaultValue.toString());
                        this.value.set(getDefaultValue());
                        alert.showAndWait();
                        }
                    setValue(this.getValue());
        });
    }
    public boolean isPositiveOnly(){
        return positiveOnly;
    }

    public Number getDefaultValue() {
        return defaultValue;
    }
    public abstract Number getValue();

    public SimpleObjectProperty<Number> valueProperty(){ return value;}

    private void setValue(Number newValue){ this.value.set(newValue); }




}
