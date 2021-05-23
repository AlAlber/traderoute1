package com.traderoute.cells;

import com.traderoute.IntegerTextField;
import com.traderoute.NumberTextField;
import java.lang.Integer;

import java.math.BigDecimal;

public class IntEditCell1<Object, Integer> extends NumberEditCell1 {

    public IntEditCell1(Number defaultValue, Number minValue, Number maxValue) {
        super(defaultValue, minValue, maxValue);
    }

    @Override
    public IntegerTextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue) {
        return new IntegerTextField(defaultValue, minValue, maxValue, false);
    }

    @Override
    public void commitNumberEdit(String text) {
        commitEdit(java.lang.Integer.valueOf(text));
    }

    @Override
    public java.lang.Integer getZero() {
        return 0;
    }

    @Override
    public String getString(String pre, String post) {
        return getItem() == null ? "" : pre + getItem().toString() + post;
    }
}
