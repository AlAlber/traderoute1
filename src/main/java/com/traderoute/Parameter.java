package com.traderoute;

import javafx.beans.property.*;
import javafx.scene.control.TextField;

public abstract class Parameter<Object> {
    private final BooleanProperty editable = new SimpleBooleanProperty();
    protected ObjectProperty<Object> january;
    protected ObjectProperty<Object> february;
    protected ObjectProperty<Object> march;
    protected ObjectProperty<Object> april;
    protected ObjectProperty<Object> may;
    protected ObjectProperty<Object> june;
    protected ObjectProperty<Object> july;
    protected ObjectProperty<Object> august;
    protected ObjectProperty<Object> september;
    protected ObjectProperty<Object> october;
    protected SimpleObjectProperty<Object> november;
    protected SimpleObjectProperty<Object> december;
    protected String pre;
    protected SimpleStringProperty name ;

    public Parameter(String name, String pre, Object january, Object february, Object march, Object april, Object may, Object june, Object july, Object august, Object september, Object october, Object november, Object december, boolean editable) {
        this.name = new SimpleStringProperty(name);
        this.pre = pre;


        this.january = new SimpleObjectProperty<>(january);
        this.february = new SimpleObjectProperty<>(february);
        this.march = new SimpleObjectProperty<>(march);
        this.april = new SimpleObjectProperty<>(april);
        this.may = new SimpleObjectProperty<>(may);
        this.june = new SimpleObjectProperty<>(june);
        this.july = new SimpleObjectProperty<>(july);
        this.august = new SimpleObjectProperty<>(august);
        this.september = new SimpleObjectProperty<>(september);
        this.october = new SimpleObjectProperty<>(october);
        this.november = new SimpleObjectProperty<>(november);
        this.december = new SimpleObjectProperty<>(december);
        setEditable(editable);
//        setJanuary(january);
//        setFebruary(february);
//        setMarch(march);
//        setApril(april);
//        setMay(may);
//        setJune(june);
//        setJuly(july);
//        setAugust(august);
//        setSeptember(september);
//        setOctober(october);
//        setNovember(november);
//        setDecember(december);

    }

    public String toString(){
        return name + pre;
    }

    public String getPre() {return pre; }

    public void setPre(String pre) {this.pre= pre;}

    public Parameter(){
        this.name= new SimpleStringProperty("");
        this.pre = "";
        setEditable(false);
    }

    public Parameter(String name, String pre,Object january, Object february, Object march, Object april, Object may, Object june, Object july, Object august, Object september, Object october, Object november, Object december) {
        this(name, pre, january, february,march, april, may, june, july, august, september, october, november, december, true);
    }

    public Parameter(String name, String pre) {
        this.name = new SimpleStringProperty(name);
        this.pre = pre;
        this.january = new SimpleObjectProperty<>();
        this.february = new SimpleObjectProperty<>();
        this.march = new SimpleObjectProperty<>();
        this.april = new SimpleObjectProperty<>();
        this.may = new SimpleObjectProperty<>();
        this.june = new SimpleObjectProperty<>();
        this.july = new SimpleObjectProperty<>();
        this.august = new SimpleObjectProperty<>();
        this.september = new SimpleObjectProperty<>();
        this.october = new SimpleObjectProperty<>();
        this.november = new SimpleObjectProperty<>();
        this.december = new SimpleObjectProperty<>();
        setEditable(true);
    }
    public Object getMonth(int month){
        if (month==1){
            return getJanuary();
        }
        if (month ==2){
            return getFebruary();
        }
        if (month ==3){
            return getMarch();
        }
        if (month ==4){
            return getApril();
        }
        if (month ==5){
            return getMay();
        }
        if (month ==6){
            return getJune();
        }
        if (month ==7){
            return getJuly();
        }
        if (month == 8)
            return getAugust();
        if (month ==9){
            return getSeptember();
        }
        if (month ==10){
            return getOctober();
        }
        if (month ==11){
            return getNovember();
        }
        if (month ==12){
            return getDecember();
        }
        return null;
    }
    public void setMonth(int month, Object value){
        if (month==1){
            setJanuary(value);
        }
        if (month ==2){
            setFebruary(value);
        }
        if (month ==3){
            setMarch(value);
        }
        if (month ==4){
            setApril(value);
        }
        if (month ==5){
            setMay(value);
        }
        if (month ==6){
            setJune(value);
        }
        if (month ==7){
            setJuly(value);
        }
        if (month == 8) {
            setAugust(value);
        }
        if (month ==9){
            setSeptember(value);
        }
        if (month ==10){
            setOctober(value);
        }
        if (month ==11){
            setNovember(value);
        }
        if (month ==12){
            setDecember(value);
        }
    }

    public String getName() {
        return name.get() ;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObjectProperty<Object> januaryProperty() {
        if (january==null){
            return new SimpleObjectProperty<>();
        }
        return january;
    }

    public abstract Object getJanuary() ;

    public void setJanuary(Object january) {
        januaryProperty().set(january);
    }
    public ObjectProperty<Object> februaryProperty() {
        if (february==null){
            return new SimpleObjectProperty<>();
        }
        return february;
    }

    public abstract Object getFebruary() ;

    public void setFebruary(Object february) {
        februaryProperty().set(february);
    }
    //

    public ObjectProperty<Object> marchProperty() {
        if (march==null){
            return new SimpleObjectProperty<>();
        }
        return march;
    }

    public abstract Object getMarch() ;

    public void setMarch(Object march) {
        marchProperty().set(march);
    }
    //
    public ObjectProperty<Object> aprilProperty() {
        if (april==null){
            return new SimpleObjectProperty<>();
        }
        return april;
    }

    public abstract Object getApril() ;

    public void setApril(Object april) {
        aprilProperty().set(april);
    }
    //
    public ObjectProperty<Object> mayProperty() {
        if (may==null){
            return new SimpleObjectProperty<>();
        }
        return may;
    }

    public abstract Object getMay() ;

    public void setMay(Object may) {
        mayProperty().set(may);
    }
    //
    public ObjectProperty<Object> juneProperty() {
        if (june==null){
            return new SimpleObjectProperty<>();
        }
        return june;
    }

    public abstract Object getJune() ;

    public void setJune(Object june) {
        juneProperty().set(june);
    }
    //
    public ObjectProperty<Object> julyProperty() {
        if (july==null){
            return new SimpleObjectProperty<>();
        }
        return july;
    }

    public abstract Object getJuly() ;

    public void setJuly(Object july) {
        julyProperty().set(july);
    }
    //
    public ObjectProperty<Object> augustProperty() {
        if (august==null){
            return new SimpleObjectProperty<>();
        }
        return august;
    }

    public abstract Object getAugust() ;

    public void setAugust(Object august) {
        augustProperty().set(august);
    }
    //
    public ObjectProperty<Object> septemberProperty() {
        if (september==null){
            return new SimpleObjectProperty<>();
        }
        return september;
    }

    public abstract Object getSeptember() ;

    public void setSeptember(Object september) {
        septemberProperty().set(september);
    }
    //
    public ObjectProperty<Object> octoberProperty() {
        if (october==null){
            return new SimpleObjectProperty<>();
        }
        return october;
    }

    public abstract Object getOctober() ;

    public void setOctober(Object october) {
        octoberProperty().set(october);
    }
    //
    public ObjectProperty<Object> novemberProperty() {
        if (november==null){
            return new SimpleObjectProperty<>();
        }
        return november;
    }

    public abstract Object getNovember() ;

    public void setNovember(Object november) {
        novemberProperty().set(november);
    }
    //
    public ObjectProperty<Object> decemberProperty() {
        if (december==null){
            return new SimpleObjectProperty<>();
        }
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

}
