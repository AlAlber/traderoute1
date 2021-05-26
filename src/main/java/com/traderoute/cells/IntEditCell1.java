package com.traderoute.cells;

import com.traderoute.IntegerTextField;
import com.traderoute.NumberTextField;
import java.lang.Integer;

import java.math.BigDecimal;

public class IntEditCell1<Object, Integer> extends NumberEditCell1 {

    public IntEditCell1() {
        super();
    }

    @Override
    public IntegerTextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue) {
        return new IntegerTextField(defaultValue, minValue, maxValue, false);
    }

    @Override
    public void commitValueEdit(String text) {
        commitEdit(java.lang.Integer.valueOf(text));
    }

    @Override
    public java.lang.Integer getZeroOrEmptyString() {
        return 0;
    }

    @Override
    public String getString(CellSpecs specs) {
        return getItem() == null ? "" : specs.getPre() + getItem().toString() + specs.getPost();
    }
}
