package com.traderoute.data;

import com.traderoute.data.Parameter;

public class StringParameter extends Parameter<String> {

    public StringParameter(String name, String pre, String january, String february, String march, String april,
            String may, String june, String july, String august, String september, String october, String november,
            String december) {
        super(name, pre, january, february, march, april, may, june, july, august, september, october, november,
                december);
    }

    public StringParameter(String name, String pre) {
        super(name, pre);
    }

    public String getTotal() {
        if (total.get() == null) {
            return "";
        }
        return total.get();
    }

    @Override
    public String getJanuary() {
        if (january.get() == null) {
            return "";
        }
        return january.get();
    }

    @Override
    public String getFebruary() {
        if (february.get() == null) {
            return "";
        }
        return february.get();
    }

    @Override
    public String getMarch() {
        if (march.get() == null) {
            return "";
        }
        return march.get();
    }

    @Override
    public String getApril() {
        if (april.get() == null) {
            return "";
        }
        return april.get();
    }

    @Override
    public String getMay() {
        if (may.get() == null) {
            return "";
        }
        return may.get();
    }

    @Override
    public String getJune() {
        if (june.get() == null) {
            return "";
        }
        return june.get();
    }

    @Override
    public String getJuly() {
        if (july.get() == null) {
            return "";
        }
        return july.get();
    }

    @Override
    public String getAugust() {
        if (august.get() == null) {
            return "";
        }
        return august.get();
    }

    @Override
    public String getSeptember() {
        if (september.get() == null) {
            return "";
        }
        return september.get();
    }

    @Override
    public String getOctober() {
        if (october.get() == null) {
            return "";
        }
        return october.get();
    }

    @Override
    public String getNovember() {
        if (november.get() == null) {
            return "";
        }
        return november.get();
    }

    @Override
    public String getDecember() {
        if (december.get() == null) {
            return "";
        }
        return december.get();
    }
}
