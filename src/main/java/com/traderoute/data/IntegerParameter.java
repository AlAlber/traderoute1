package com.traderoute.data;

public class IntegerParameter extends Parameter<Integer> {

    public IntegerParameter(String name, String pre, Integer january, Integer february, Integer march, Integer april,
            Integer may, Integer june, Integer july, Integer august, Integer september, Integer october,
            Integer november, Integer december) {
        super(name, pre, january, february, march, april, may, june, july, august, september, october, november,
                december);

    }

    public IntegerParameter(String name, String pre, Integer total, Integer january, Integer february, Integer march,
            Integer april, Integer may, Integer june, Integer july, Integer august, Integer september, Integer october,
            Integer november, Integer december) {
        super(name, pre, january, february, march, april, may, june, july, august, september, october, november,
                december);

    }

    public IntegerParameter(String name, String pre) {
        super(name, pre);
        setEditable(true);
    }

    public Integer getTotal() {
        if (total.get() == null) {
            return 0;
        }
        return total.get();
    }

    public Integer getJanuary() {
        if (january.get() == null) {
            return 0;
        }
        return january.get();
    }

    public Integer getFebruary() {
        if (february.get() == null) {
            return 0;
        }
        return february.get();
    }

    public Integer getMarch() {
        if (march.get() == null) {
            return 0;
        }
        return march.get();
    }

    public Integer getApril() {
        if (april.get() == null) {
            return 0;
        }
        return april.get();
    }

    public Integer getMay() {
        if (may.get() == null) {
            return 0;
        }
        return may.get();
    }

    public Integer getJune() {
        if (june.get() == null) {
            return 0;
        }
        return june.get();
    }

    public Integer getJuly() {
        if (july.get() == null) {
            return 0;
        }

        return july.get();
    }

    public Integer getAugust() {
        if (august.get() == null) {
            return 0;
        }
        return august.get();
    }

    public Integer getSeptember() {
        if (september.get() == null) {
            return 0;
        }
        return september.get();
    }

    public Integer getOctober() {
        if (october.get() == null) {
            return 0;
        }
        return october.get();
    }

    public Integer getNovember() {
        if (november.get() == null) {
            return 0;
        }
        return november.get();
    }

    public Integer getDecember() {
        if (december.get() == null) {
            return 0;
        }
        return december.get();
    }
}
