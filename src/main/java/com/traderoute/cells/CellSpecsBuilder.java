package com.traderoute.cells;

import java.math.BigDecimal;

public class CellSpecsBuilder {
    private String _pre = "";
    private String _post = "";
    private Number _defaultValue = new BigDecimal("0.0");
    private Number _minValue = new BigDecimal("0.0");
    private Number _maxValue = new BigDecimal("1000000");
    private int _decPoints = 2;


    public CellSpecs build() {
        return new CellSpecs(_pre,_post,_defaultValue,_minValue,_maxValue, _decPoints);
    }

//    public NumberEditCell1 buildBD() {
//        return new BigDecimalEditCell1(_pre, _post, _defaultValue, _minValue, _maxValue, _decimalScale);
//    }

    public CellSpecsBuilder pre(String _pre) {
        this._pre = _pre;
        return this;
    }
    public CellSpecsBuilder post(String _post) {
        this._post = _post;
        return this;
    }
    public CellSpecsBuilder defaultValue(Number _defaultValue) {
        this._defaultValue = _defaultValue;
        return this;
    }
    public CellSpecsBuilder minValue(Number _minValue) {
        this._minValue = _minValue;
        return this;
    }
    public CellSpecsBuilder maxValue(Number _maxValue) {
        this._maxValue = _maxValue;
        return this;
    }
    public CellSpecsBuilder decPoints(int _decPoints) {
        this._decPoints = _decPoints;
        return this;
    }
}
