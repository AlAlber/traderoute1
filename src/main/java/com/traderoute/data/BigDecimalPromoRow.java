package com.traderoute.data;

import com.traderoute.cells.CellSpecs;
import com.traderoute.cells.CellSpecsBuilder;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class BigDecimalPromoRow extends PromoRow<BigDecimal> {

    public BigDecimalPromoRow(CellSpecs specs, String name, BigDecimal january, BigDecimal february, BigDecimal march,
                              BigDecimal april, BigDecimal may, BigDecimal june, BigDecimal july, BigDecimal august, BigDecimal september,
                              BigDecimal october, BigDecimal november, BigDecimal december, boolean editable) {
        super(specs, name, january, february, march, april, may, june, july, august, september, october, november,
                december, editable);
    }
    public BigDecimalPromoRow(CellSpecs specs, String name, BigDecimal total,  BigDecimal january, BigDecimal february, BigDecimal march,
                              BigDecimal april, BigDecimal may, BigDecimal june, BigDecimal july, BigDecimal august, BigDecimal september,
                              BigDecimal october, BigDecimal november, BigDecimal december, boolean editable) {
        super(specs, name, january, february, march, april, may, june, july, august, september, october, november,
                december, editable);
    }


    public BigDecimalPromoRow() {
        super.name = new SimpleStringProperty(""); // Changed name here
//        super.pre = "";
        super.specs = new CellSpecsBuilder().build();
        setEditable(false);
    }

    public BigDecimalPromoRow(CellSpecs specs, String name) {
        super(specs, name);
        setEditable(true);
        if (getName().equals("Skus In Distribution")) {
            setEditable(false);
        }
    }

    @Override
    public void setMonth(int month, BigDecimal value) {
        super.setMonth(month, value);
    }

    public BigDecimal getTotal() {
        if (total.get() == null) {
            return new BigDecimal("0.0");
        }
        return total.get();
    }

    public BigDecimal getJanuary() {
        if (january.get() == null) {
            return new BigDecimal("0.0");
        }
        return january.get();
    }

    public BigDecimal getFebruary() {
        if (february.get() == null) {
            return new BigDecimal("0.0");
        }
        return february.get();
    }

    @Override
    public BigDecimal getMarch() {
        if (march.get() == null) {
            return new BigDecimal("0.0");
        }
        return march.get();
    }

    @Override
    public BigDecimal getApril() {
        if (april.get() == null) {
            return new BigDecimal("0.0");
        }
        return april.get();
    }

    @Override
    public BigDecimal getMay() {
        if (may.get() == null) {
            return new BigDecimal("0.0");
        }
        return may.get();
    }

    @Override
    public BigDecimal getJune() {
        if (june.get() == null) {
            return new BigDecimal("0.0");
        }
        return june.get();
    }

    @Override
    public BigDecimal getJuly() {
        if (july.get() == null) {
            return new BigDecimal("0.0");
        }
        return july.get();
    }

    @Override
    public BigDecimal getAugust() {
        if (august.get() == null) {
            return new BigDecimal("0.0");
        }
        return august.get();
    }

    @Override
    public BigDecimal getSeptember() {
        if (september.get() == null) {
            return new BigDecimal("0.0");
        }
        return september.get();
    }

    @Override
    public BigDecimal getOctober() {
        if (october.get() == null) {
            return new BigDecimal("0.0");
        }
        return october.get();
    }

    @Override
    public BigDecimal getNovember() {
        if (november.get() == null) {
            return new BigDecimal("0.0");
        }
        return november.get();
    }

    @Override
    public BigDecimal getDecember() {
        if (december.get() == null) {
            return new BigDecimal("0.0");
        }
        return december.get();
    }

}
