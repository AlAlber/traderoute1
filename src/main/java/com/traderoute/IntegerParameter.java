package com.traderoute;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import org.w3c.dom.Text;

import java.math.BigDecimal;

public class IntegerParameter extends Parameter<Integer>{
    private final TextField editor0;
    private final TextField editor1;
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

    public IntegerParameter(String name, String pre, Integer january, Integer february,
                            Integer march, Integer april, Integer may, Integer june,
                            Integer july, Integer august, Integer september, Integer october,
                            Integer november, Integer december){
        super(name, pre, january, february,march, april, may, june, july, august, september, october, november, december);
        editor0 = new TextField();
        editor0.textProperty().bindBidirectional(januaryProperty(), new IntegerStringConverter());
        addIntListener(editor0);
        editor1 = new TextField();
        editor1.textProperty().bindBidirectional(februaryProperty(), new IntegerStringConverter());
        addIntListener(editor1);
        editor2 = new TextField();
        editor2.textProperty().bindBidirectional(marchProperty(), new IntegerStringConverter());
        addIntListener(editor2);
        editor3 = new TextField();
        editor3.textProperty().bindBidirectional(aprilProperty(), new IntegerStringConverter());
        addIntListener(editor3);
        editor4 = new TextField();
        editor4.textProperty().bindBidirectional(mayProperty(), new IntegerStringConverter());
        addIntListener(editor4);
        editor5 = new TextField();
        editor5.textProperty().bindBidirectional(juneProperty(), new IntegerStringConverter());
        addIntListener(editor5);
        editor6 = new TextField();
        editor6.textProperty().bindBidirectional(julyProperty(), new IntegerStringConverter());
        addIntListener(editor6);
        editor7 = new TextField();
        editor7.textProperty().bindBidirectional(augustProperty(), new IntegerStringConverter());
        addIntListener(editor7);
        editor8 = new TextField();
        editor8.textProperty().bindBidirectional(septemberProperty(), new IntegerStringConverter());
        addIntListener(editor8);
        editor9 = new TextField();
        editor9.textProperty().bindBidirectional(octoberProperty(), new IntegerStringConverter());
        addIntListener(editor9);
        editor10 = new TextField();
        editor10.textProperty().bindBidirectional(novemberProperty(), new IntegerStringConverter());
        addIntListener(editor10);
        editor11 = new TextField();
        editor11.textProperty().bindBidirectional(decemberProperty(), new IntegerStringConverter());
        addIntListener(editor11);
    }
    public IntegerParameter(String name, String pre){
        super(name, pre);
        setEditable(true);
        editor0 = new TextField(getJanuary().toString());
        editor0.textProperty().bindBidirectional(januaryProperty(), new IntegerStringConverter());
        addIntListener(editor0);
        editor1 = new TextField();
        editor1.textProperty().bindBidirectional(februaryProperty(), new IntegerStringConverter());
        addIntListener(editor1);
        editor2 = new TextField();
        editor2.textProperty().bindBidirectional(marchProperty(), new IntegerStringConverter());
        addIntListener(editor2);
        editor3 = new TextField();
        editor3.textProperty().bindBidirectional(aprilProperty(), new IntegerStringConverter());
        addIntListener(editor3);
        editor4 = new TextField();
        editor4.textProperty().bindBidirectional(mayProperty(), new IntegerStringConverter());
        addIntListener(editor4);
        editor5 = new TextField();
        editor5.textProperty().bindBidirectional(juneProperty(), new IntegerStringConverter());
        addIntListener(editor5);
        editor6 = new TextField();
        editor6.textProperty().bindBidirectional(julyProperty(), new IntegerStringConverter());
        addIntListener(editor6);
        editor7 = new TextField();
        editor7.textProperty().bindBidirectional(augustProperty(), new IntegerStringConverter());
        addIntListener(editor7);
        editor8 = new TextField();
        editor8.textProperty().bindBidirectional(septemberProperty(), new IntegerStringConverter());
        addIntListener(editor8);
        editor9 = new TextField();
        editor9.textProperty().bindBidirectional(octoberProperty(), new IntegerStringConverter());
        addIntListener(editor9);
        editor10 = new TextField();
        editor10.textProperty().bindBidirectional(novemberProperty(), new IntegerStringConverter());
        addIntListener(editor10);
        editor11 = new TextField();
        editor11.textProperty().bindBidirectional(decemberProperty(), new IntegerStringConverter());
        addIntListener(editor11);
    }
    public void addIntListener(TextField editor){
        editor.textProperty().addListener(new ChangeListener<String>() {
            private boolean changing;
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    editor.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (!changing) {
                    try {
                        changing = true;
                        editor.setText(newValue);
                    } finally {
                        changing = false;
                    }
                }
            }
        });
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
