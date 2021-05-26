package com.traderoute.cells;

import com.traderoute.data.BigDecimalPromoRow;
import com.traderoute.data.IntegerPromoRow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class NumberPromoRowBuilder {
    private SimpleObjectProperty<CellSpecs> _specs = new SimpleObjectProperty(StdSpecs.INTPOS6$.getSpecs());
    private SimpleStringProperty _name = new SimpleStringProperty("");
    private ObjectProperty<Number> _total = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _jan = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _feb = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _mar = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _apr = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _may = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _jun = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _jul = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _aug = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _sep = new SimpleObjectProperty<>(0);
    private ObjectProperty<Number> _oct = new SimpleObjectProperty<>(0);
    private SimpleObjectProperty<Number> _nov = new SimpleObjectProperty<>(0);
    private SimpleObjectProperty<Number> _dec = new SimpleObjectProperty<>(0);
    private SimpleBooleanProperty _editable = new SimpleBooleanProperty(true);

    public BigDecimalPromoRow buildBD() {
        return new BigDecimalPromoRow(_specs.get(), _name.get(), numToDec(_total), numToDec(_jan),
                numToDec(_feb), numToDec(_mar), numToDec(_apr), numToDec(_may),numToDec(_jun)
                , numToDec(_jul), numToDec(_aug), numToDec(_sep), numToDec(_oct), numToDec(_nov),
                numToDec(_dec), _editable.get());
    }
    public IntegerPromoRow buildInt() {
        return new IntegerPromoRow(_specs.get(), _name.get(), numToInt(_total), numToInt(_jan),
                numToInt(_feb), numToInt(_mar), numToInt(_apr), numToInt(_may),numToInt(_jun)
                , numToInt(_jul), numToInt(_aug), numToInt(_sep), numToInt(_oct), numToInt(_nov),
                numToInt(_dec), _editable.get());
    }

    public BigDecimal numToDec(ObjectProperty<Number> property){
        return new BigDecimal(property.get().toString());
    }
    public Integer numToInt (ObjectProperty<Number> property) {
        return property.get().intValue();
    }
    public NumberPromoRowBuilder specs(CellSpecs _specs) {
        this._specs = new SimpleObjectProperty(_specs);
        return this;
    }
    public NumberPromoRowBuilder name(String _name) {
        this._name = new SimpleStringProperty(_name);
        return this;
    }
    public NumberPromoRowBuilder jan(Number _jan){
        this._jan = new SimpleObjectProperty<>(_jan);
        return this;
    }
    public NumberPromoRowBuilder feb(Number _feb){
        this._feb = new SimpleObjectProperty<>(_feb);
        return this;
    }
    public NumberPromoRowBuilder mar(Number _mar){
        this._mar = new SimpleObjectProperty<>(_mar);
        return this;
    }
    public NumberPromoRowBuilder apr(Number _apr){
        this._apr = new SimpleObjectProperty<>(_apr);
        return this;
    }
    public NumberPromoRowBuilder may(Number _may){
        this._may = new SimpleObjectProperty<>(_may);
        return this;
    }
    public NumberPromoRowBuilder jun(Number _jun){
        this._jun = new SimpleObjectProperty<>(_jun);
        return this;
    }
    public NumberPromoRowBuilder jul(Number _jul){
        this._jul = new SimpleObjectProperty<>(_jul);
        return this;
    }
    public NumberPromoRowBuilder aug(Number _aug){
        this._aug = new SimpleObjectProperty<>(_aug);
        return this;
    }
    public NumberPromoRowBuilder sep(Number _sep){
        this._sep = new SimpleObjectProperty<>(_sep);
        return this;
    }
    public NumberPromoRowBuilder oct(Number _oct){
        this._oct = new SimpleObjectProperty<>(_oct);
        return this;
    }
    public NumberPromoRowBuilder nov(Number _nov){
        this._nov = new SimpleObjectProperty<>(_nov);
        return this;
    }
    public NumberPromoRowBuilder dec(Number _dec){
        this._dec = new SimpleObjectProperty<>(_dec);
        return this;
    }
    public NumberPromoRowBuilder editable (boolean editable){
        this._editable = new SimpleBooleanProperty(editable);
        return this;
    }





}
