package com.traderoute;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.math.BigDecimal;

public class IntegerTextField extends NumberTextField{
    public IntegerTextField(Number defaultValue, Number minValue, Number maxValue,
                            boolean positiveOnly ) {
        super(defaultValue, minValue, maxValue, positiveOnly);
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue,
                                final String newValue) {
                if (!newValue.matches("\\d*")) {
                    setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public Integer getValue() {
        return Integer.valueOf(this.getText());
    }
}
