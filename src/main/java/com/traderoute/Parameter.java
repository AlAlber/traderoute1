package com.traderoute;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.converter.BigDecimalStringConverter;
import org.w3c.dom.Text;

import java.math.BigDecimal;

public abstract class Parameter<Object> {


    private final BooleanProperty editable = new SimpleBooleanProperty();

    protected ObjectProperty<Object> january = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> february = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> march = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> april = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> may = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> june = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> july = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> august = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> september = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> october = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> november = new SimpleObjectProperty<>();

    protected ObjectProperty<Object> december = new SimpleObjectProperty<>();

    protected String pre;

    private final String name ;

    public Parameter(String name, String pre, Object january, Object february, Object march, Object april, Object may, Object june, Object july, Object august, Object september, Object october, Object november, Object december, boolean editable) {
        this.name = name ;
        this.pre = pre;
        setJanuary(january);
        setFebruary(february);
        setMarch(march);
        setApril(april);
        setMay(may);
        setJune(june);
        setJuly(july);
        setAugust(august);
        setSeptember(september);
        setOctober(october);
        setNovember(november);
        setDecember(december);
        setEditable(editable);
    }
    public String toString(){
        return name + pre;
    }

    public String getPre() {return pre; }

    public void setPre(String pre) {this.pre= pre;}

    public Parameter(String name, String pre,Object january, Object february, Object march, Object april, Object may, Object june, Object july, Object august, Object september, Object october, Object november, Object december) {
        this(name, pre, january, february,march, april, may, june, july, august, september, october, november, december, true);
    }

    public String getName() {
        return name ;
    }

    public ObjectProperty<Object> januaryProperty() {
        return january;
    }

    public abstract Object getJanuary() ;

    public void setJanuary(Object january) {
        januaryProperty().set(january);
    }
    public ObjectProperty<Object> februaryProperty() {
        return february;
    }

    public abstract Object getFebruary() ;

    public void setFebruary(Object february) {
        februaryProperty().set(february);
    }
    //

    public ObjectProperty<Object> marchProperty() {
        return march;
    }

    public abstract Object getMarch() ;

    public void setMarch(Object march) {
        marchProperty().set(march);
    }
    //
    public ObjectProperty<Object> aprilProperty() {
        return april;
    }

    public abstract Object getApril() ;

    public void setApril(Object april) {
        aprilProperty().set(april);
    }
    //
    public ObjectProperty<Object> mayProperty() {
        return may;
    }

    public abstract Object getMay() ;

    public void setMay(Object may) {
        mayProperty().set(may);
    }
    //
    public ObjectProperty<Object> juneProperty() {
        return june;
    }

    public abstract Object getJune() ;

    public void setJune(Object june) {
        juneProperty().set(june);
    }
    //
    public ObjectProperty<Object> julyProperty() {
        return july;
    }

    public abstract Object getJuly() ;

    public void setJuly(Object july) {
        julyProperty().set(july);
    }
    //
    public ObjectProperty<Object> augustProperty() {
        return august;
    }

    public abstract Object getAugust() ;

    public void setAugust(Object august) {
        augustProperty().set(august);
    }
    //
    public ObjectProperty<Object> septemberProperty() {
        return september;
    }

    public abstract Object getSeptember() ;

    public void setSeptember(Object september) {
        septemberProperty().set(september);
    }
    //
    public ObjectProperty<Object> octoberProperty() {
        return october;
    }

    public abstract Object getOctober() ;

    public void setOctober(Object october) {
        octoberProperty().set(october);
    }
    //
    public ObjectProperty<Object> novemberProperty() {
        return november;
    }

    public abstract Object getNovember() ;

    public void setNovember(Object november) {
        novemberProperty().set(november);
    }
    //
    public ObjectProperty<Object> decemberProperty() {
        return december;
    }

    public abstract Object getDecember() ;

    public void setDecember(Object december) {
        decemberProperty().set(december);
    }


    public BooleanProperty editableProperty() {
        return editable ;
    }

    public boolean isEditable() {
        return editableProperty().get() ;
    }

    public void setEditable(boolean editable) {
        editableProperty().set(editable);
    }

    public abstract TextField getEditor(int i) ;
}
