package com.traderoute;

import javafx.util.StringConverter;

public class DoubleInputConverter extends StringConverter<Double> {
    @Override
    public Double fromString(final String s) {
        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
            return 0.0;
        } else {
            return Double.valueOf(s);
        }
    }

    @Override
    public String toString(final Double d) {
        return d.toString();
    }

}
