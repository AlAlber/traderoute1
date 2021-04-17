package com.traderoute.data;

import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class BigDecimalParameter extends Parameter<BigDecimal> {

    public BigDecimalParameter(String name, String pre, BigDecimal january, BigDecimal february,
                               BigDecimal march, BigDecimal april, BigDecimal may, BigDecimal june,
                               BigDecimal july, BigDecimal august, BigDecimal september, BigDecimal october,
                               BigDecimal november, BigDecimal december, boolean editable){
        super(name, pre, january, february,march, april, may, june, july, august, september, october, november, december, editable);
    }
    public BigDecimalParameter(String name, String pre, BigDecimal total,  BigDecimal january, BigDecimal february,
                               BigDecimal march, BigDecimal april, BigDecimal may, BigDecimal june,
                               BigDecimal july, BigDecimal august, BigDecimal september, BigDecimal october,
                               BigDecimal november, BigDecimal december, boolean editable){
        super(name, pre, january, february,march, april, may, june, july, august, september, october, november, december, editable);
    }
    public BigDecimalParameter(){
        super.name =new SimpleStringProperty( ""); // Changed name here
        super.pre = "";
        setEditable(false);
    }
    public BigDecimalParameter(String name, String pre){
        super(name, pre);
        setEditable(true);
        if (getName().equals("Skus In Distribution")){
            setEditable(false);
        }
    }
    @Override
    public void setMonth(int month, BigDecimal value){
        super.setMonth(month,value);
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
    public BigDecimal getFebruary(){
        if (february.get() == null) {
            return new BigDecimal("0.0");
        }
        return february.get();}

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
// private SimpleObjectProperty<BigDecimal> actualValue;
//
//public BigDecimal getActualValue() {
//    return actualValue.get();
//}
//
//    public SimpleObjectProperty actualValueProperty() {
//        return actualValue;
//    }
//
//    public void setActualValue(BigDecimal actualValue) {
//        this.actualValue.set(actualValue);
//    }
//valueProperty().addListener(new ChangeListener<String>() {
//            private boolean changing;
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!changing) {
//                    try {
//                        changing = true;
//                        setActualValue(new BigDecimal(newValue));
//                    } finally {
//                        changing = false;
//                    }
//                }
//            }
//        });
