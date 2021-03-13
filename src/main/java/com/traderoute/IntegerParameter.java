package com.traderoute;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import org.w3c.dom.Text;

import java.math.BigDecimal;

public class IntegerParameter extends Parameter<Integer>{

    public IntegerParameter(String name, String pre, Integer january, Integer february,
                            Integer march, Integer april, Integer may, Integer june,
                            Integer july, Integer august, Integer september, Integer october,
                            Integer november, Integer december){
        super(name, pre, january, february,march, april, may, june, july, august, september, october, november, december);

    }
    public IntegerParameter(String name, String pre){
        super(name, pre);
        setEditable(true);
    }
    public Integer getJanuary(){
        if (january.get() == null) {
            return 0;
        }
        return january.get();
    }
    public Integer getFebruary(){ return february.get();}

    @Override
    public Integer getMarch() {
        if (january.get() == null) {
            return 0;
        }
        return march.get();
    }

    @Override
    public Integer getApril() {
        return april.get();
    }

    @Override
    public Integer getMay() {
        return may.get();
    }

    @Override
    public Integer getJune() {
        return june.get();
    }

    @Override
    public Integer getJuly() {
        return july.get();
    }

    @Override
    public Integer getAugust() {
        return august.get();
    }

    @Override
    public Integer getSeptember() {
        return september.get();
    }

    @Override
    public Integer getOctober() {
        return october.get();
    }

    @Override
    public Integer getNovember() {
        return november.get();
    }

    @Override
    public Integer getDecember() {
        return december.get();
    }
}
