package com.traderoute;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class StringParameter extends Parameter<String> {
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

    public StringParameter(String name, String pre, String january, String february,
                           String march, String april, String may, String june,
                           String july, String august, String september, String october,
                           String november, String december) {
        super(name, pre, january, february, march, april, may, june, july, august, september, october, november, december);
        bindEditorToMonth();
    }
    public StringParameter(String name, String pre){
        super(name, pre);
        bindEditorToMonth();
    }

    @Override
    public String getJanuary() {
        if(january.get()==null){
            return "";
        }
        return january.get();
    }

    @Override
    public String getFebruary() {
        if(february.get()==null){
            return "";
        }
        return february.get();
    }

    @Override
    public String getMarch() {
        if(march.get()==null){
            return "";
        }
        return march.get();
    }

    @Override
    public String getApril() {
        if(april.get()==null){
            return "";
        }
        return april.get();
    }

    @Override
    public String getMay() {
        if(may.get()==null){
            return "";
        }
        return may.get();
    }

    @Override
    public String getJune() {
        if(june.get()==null){
            return "";
        }
        return june.get();
    }

    @Override
    public String getJuly() {
        if(july.get()==null){
            return "";
        }
        return july.get();
    }

    @Override
    public String getAugust() {
        if(august.get()==null){
            return "";
        }
        return august.get();
    }

    @Override
    public String getSeptember() {
        if(september.get()==null){
            return "";
        }
        return september.get();
    }

    @Override
    public String getOctober() {
        if(october.get()==null){
            return "";
        }
        return october.get();
    }

    @Override
    public String getNovember() {
        if(november.get()==null){
            return "";
        }
        return november.get();
    }

    @Override
    public String getDecember() {
        if(december.get()==null){
            return "";
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

    public void bindEditorToMonth(){
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
        editor0.textProperty().bindBidirectional(januaryProperty());

        editor1.textProperty().bindBidirectional(februaryProperty());

        editor2.textProperty().bindBidirectional(marchProperty());

        editor3.textProperty().bindBidirectional(aprilProperty());

        editor4.textProperty().bindBidirectional(mayProperty());

        editor5.textProperty().bindBidirectional(juneProperty());

        editor6.textProperty().bindBidirectional(julyProperty());

        editor7.textProperty().bindBidirectional(augustProperty());

        editor8.textProperty().bindBidirectional(septemberProperty());

        editor9.textProperty().bindBidirectional(octoberProperty());

        editor10.textProperty().bindBidirectional(novemberProperty());

        editor11.textProperty().bindBidirectional(decemberProperty());
    }
}
