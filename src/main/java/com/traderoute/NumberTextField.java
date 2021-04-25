package com.traderoute;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {
    private Number maxValue;
    private Number minValue;
    private Number defaultValue;
    public NumberTextField (Number defaultValue, Number minValue, Number maxValue) {
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

}
