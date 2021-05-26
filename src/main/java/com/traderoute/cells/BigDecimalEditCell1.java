package com.traderoute.cells;

import com.traderoute.BigDecimalTextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalEditCell1 extends NumberEditCell1{

    public BigDecimalEditCell1(CellSpecs specs) {
        super(specs);
//        this.decPoints = decPoints;
    }

    @Override
    public BigDecimalTextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue) {
        return new BigDecimalTextField(defaultValue, minValue, maxValue, false);
    }

    @Override
    public void commitValueEdit(String text) {
        commitEdit(new BigDecimal(text));
    }

    @Override
    public Number getZeroOrEmptyString() {
        return new BigDecimal("0.0");
    }

    @Override
    public String getString(CellSpecs specs) {
        return getItem() == null ? "" : specs.getPre() + ((BigDecimal) getItem())
                .setScale(specs.getDecPoints(), RoundingMode.HALF_UP).toString() + specs.getPost();
    }
}
