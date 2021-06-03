package com.traderoute.cells;

import java.math.BigDecimal;

public class NumberEditCellBuilder {
    private String _pre = "";
    private String _post = "";
    private Number _defaultValue = new BigDecimal("0.0");
    private Number _minValue = new BigDecimal("0.0");
    private Number _maxValue = new BigDecimal("1000000");
    private int _decimalScale = 2;


    public NumberEditCell1 buildInt() {
//        return new IntEditCell1(_pre,_post,_defaultValue,_minValue,_maxValue);
        return null;
    }

    public NumberEditCell1 buildBD() {
//        return new BigDecimalEditCell1(_pre, _post, _defaultValue, _minValue, _maxValue, _decimalScale);
        return null;
    }

    public NumberEditCellBuilder pre(String _pre) {
        this._pre = _pre;
        return this;
    }
    public NumberEditCellBuilder post(String _post) {
        this._post = _post;
        return this;
    }
    public NumberEditCellBuilder defaultValue(Number _defaultValue) {
        this._defaultValue = _defaultValue;
        return this;
    }
    public NumberEditCellBuilder minValue(Number _minValue) {
        this._minValue = _minValue;
        return this;
    }
    public NumberEditCellBuilder maxValue(Number _maxValue) {
        this._maxValue = _maxValue;
        return this;
    }
    public NumberEditCellBuilder decimalScale(int _decimalScale) {
        this._decimalScale = _decimalScale;
        return this;
    }
}
