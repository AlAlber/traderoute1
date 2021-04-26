package com.traderoute;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class BigDecimalTextField extends NumberTextField{
    private String regexString = "";
    public BigDecimalTextField(Number defaultValue, Number minValue, Number maxValue, boolean positiveOnly) {
        super(defaultValue, minValue, maxValue, positiveOnly);
        this.setTextFormatter(new TextFormatter(getDoubleInputConverter(), defaultValue.doubleValue(),
                getDoubleInputFilter()));
        if (positiveOnly){
//            regexString = "^(?:(?![,0-9]{14})\\d{1,3}(?:,\\d{3})*(?:\\.\\d{1,2})?|(?![.0-9]{14})\\d{1,3}(?:\\.\\d{3})*(?:\\,\\d{1,2})?)$";
        } else {
//            regexString = "-?(([1-9+][0-9]*)|0)?(\\.[0-9]*)?";
        }
    }

//    public String getRegex(){
//        if (isPositiveOnly()){
//            regexString = "^(?:(?![,0-9]{14})\\d{1,3}(?:,\\d{3})*(?:\\[.]\\d{1,2})?|(?![.0-9]{14})\\d{1,3}(?:\\[.]\\d{3})*(?:\\,\\d{1,2})?)$";
//        } else {
//            System.out.println("is it seeing this");
//            regexString = "-?(([1-9+][0-9]*)|0)?(\\.[0-9]*)?";
//        }
//        return regexString;
//    }
    /**
     * Return a filter to use in the double text formatters (which are almost always converted into BigDecimals.
     *
     * @return UnaryOperator<TextFormatter.Change> filter to use in doubletextformatter.
     */
    public UnaryOperator<TextFormatter.Change> getDoubleInputFilter() {
        Pattern validEditingState = Pattern.compile("-?(([1-9+][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null;
            }
        };
        return filter;
    }

    /**
     * Input converter for textfields with inputs of type double which can be used for BigDecimals as well.
     *
     * @return StringConverter<Double> a converter to use in the double text formatters.
     */
    public static StringConverter<Double> getDoubleInputConverter() {
        StringConverter<Double> converter = new StringConverter<>() {
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
        };
        return converter;
    }
}
