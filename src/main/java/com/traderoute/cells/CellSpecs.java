package com.traderoute.cells;

public class CellSpecs {
    private String pre;
    private String post;
    private Number defaultValue;
    private Number minValue;
    private Number maxValue;
    private int decPoints;

    public CellSpecs(String pre, String post, Number defaultValue, Number minValue, Number maxValue, int decPoints) {
        this.pre = pre;
        this.post = post;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.decPoints = decPoints;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Number getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Number defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Number getMinValue() {
        return minValue;
    }

    public void setMinValue(Number minValue) {
        this.minValue = minValue;
    }

    public Number getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Number maxValue) {
        this.maxValue = maxValue;
    }

    public int getDecPoints() {
        return decPoints;
    }
}
