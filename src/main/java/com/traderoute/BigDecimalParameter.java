package com.traderoute;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class BigDecimalParameter extends Parameter<BigDecimal>{
    private TextField editor0;
    private TextField editor1;
    private TextField editor2;
    private TextField editor3;
    private TextField editor4;
    private TextField editor5;
    private TextField editor6;
    private TextField editor7;
    private TextField editor8;
    private TextField editor9;
    private TextField editor10;
    private TextField editor11;

    public BigDecimalParameter(String name, String pre, BigDecimal january, BigDecimal february,
                               BigDecimal march, BigDecimal april, BigDecimal may, BigDecimal june,
                               BigDecimal july, BigDecimal august, BigDecimal september, BigDecimal october,
                               BigDecimal november, BigDecimal december){
        super(name, pre, january, february,march, april, may, june, july, august, september, october, november, december);
        bindAndSetTextFormatters();
    }
    public BigDecimalParameter(){
        editor0 = new TextField();
        super.name ="";
        super.pre = "";
        setEditable(false);
    }
    public BigDecimalParameter(String name, String pre){
        super(name, pre);
        setEditable(true);
        bindAndSetTextFormatters();
    }
    public void bindAndSetTextFormatters(){
        editor0 = new TextField();
        editor1 = new TextField();
        editor2 = new TextField();
        editor3 = new TextField();
        editor4 = new TextField();
        editor5 = new TextField();
        editor6 = new TextField();
        editor7 = new TextField();
        editor8 = new TextField();
        editor9 = new TextField();
        editor10 = new TextField();
        editor11 = new TextField();

        editor0.textProperty().bindBidirectional(januaryProperty(), new BigDecimalStringConverter());
        editor0.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getJanuary().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor1.textProperty().bindBidirectional(februaryProperty(), new BigDecimalStringConverter());
        editor1.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getFebruary().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor2.textProperty().bindBidirectional(marchProperty(), new BigDecimalStringConverter());
        editor2.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getMarch().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor3.textProperty().bindBidirectional(aprilProperty(), new BigDecimalStringConverter());
        editor3.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getApril().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor4.textProperty().bindBidirectional(mayProperty(), new BigDecimalStringConverter());
        editor4.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getMay().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor5.textProperty().bindBidirectional(juneProperty(), new BigDecimalStringConverter());
        editor5.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getJune().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor6.textProperty().bindBidirectional(julyProperty(), new BigDecimalStringConverter());
        editor6.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getJuly().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor7.textProperty().bindBidirectional(augustProperty(), new BigDecimalStringConverter());
        editor7.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getAugust().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor8.textProperty().bindBidirectional(septemberProperty(), new BigDecimalStringConverter());
        editor8.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getSeptember().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor9.textProperty().bindBidirectional(octoberProperty(), new BigDecimalStringConverter());
        editor9.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getOctober().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor10.textProperty().bindBidirectional(novemberProperty(), new BigDecimalStringConverter());
        editor10.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getNovember().doubleValue(), firstTableController.getDoubleInputFilter() ));

        editor11.textProperty().bindBidirectional(decemberProperty(), new BigDecimalStringConverter());
        editor11.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(), getDecember().doubleValue(), firstTableController.getDoubleInputFilter() ));

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

    @Override
    public TextField getEditor(int i) {
        if (i==0) {
            return editor0;
        }
        if (i==1){
            return editor1;
        }
        if (i==2){
        return editor2;
    }
        if (i==3){
        return editor3;
    }
        if (i==4){
        return editor4;
    }
        if (i==5){
        return editor5;
    }
        if (i==6){
        return editor6;
    }
        if (i==7){
        return editor7;
    }
        if (i==8){
        return editor8;
    }
        if (i==9){
        return editor9;
    }
        if (i==10){
        return editor10;
    }
        if (i==11){
        return editor11;
    }
        return editor0;
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
