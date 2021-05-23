package com.traderoute.cells;

import com.traderoute.BigDecimalTextField;
import com.traderoute.NumberTextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalEditCell1 extends NumberEditCell1{

    public BigDecimalEditCell1(String pre, String post, Number defaultValue, Number minValue, Number maxValue) {
        super(pre, post, defaultValue, minValue, maxValue);
    }

    @Override
    public BigDecimalTextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue) {
        return new BigDecimalTextField(defaultValue, minValue, maxValue, false);
    }

    @Override
    public void commitNumberEdit(String text) {
        commitEdit(new BigDecimal(text));
    }

    @Override
    public Number getZero() {
        return new BigDecimal("0.0");
    }

    @Override
    public String getString(String pre, String post) {
        return getItem() == null ? "" : pre + ((BigDecimal) getItem())
                .setScale(2, RoundingMode.HALF_UP).toString() + post;
    }
}
