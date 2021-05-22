package com.traderoute.data;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class RTMOptionBuilder {
    private final static BigDecimal bd0 = new BigDecimal("0.0");
    private SimpleStringProperty _rtmName = new SimpleStringProperty("New Option");
    private SimpleObjectProperty<RetailerProduct> _retailerProduct;
    private SimpleObjectProperty<BigDecimal> _slottingPerSku = new SimpleObjectProperty<>(bd0),
            _freightOutPerUnit = new SimpleObjectProperty<>(bd0), _firstReceiver = new SimpleObjectProperty<>(bd0),
            _secondReceiver = new SimpleObjectProperty<>(bd0), _thirdReceiver = new SimpleObjectProperty<>(bd0),
            _fourthReceiver = new SimpleObjectProperty<>(bd0), _landedStoreCost  = new SimpleObjectProperty<>(bd0),
            _everydayRetailCalcd = new SimpleObjectProperty<>(bd0), _everydayRetailOverride = new SimpleObjectProperty<>(bd0),
            _elasticizedUnitVelocity  = new SimpleObjectProperty<>(bd0), _slottingPaybackPeriod = new SimpleObjectProperty<>(bd0),
            _postSpoilsPostFreightPerUnit = new SimpleObjectProperty<>(bd0), _unspentTradePerUnit = new SimpleObjectProperty<>(bd0),
            _fourYearEqGpPerSku = new SimpleObjectProperty<>(bd0), _fourYearEqGpPerUnit = new SimpleObjectProperty<>(bd0),
            _annualVolumePerSku = new SimpleObjectProperty<>(bd0);

    public RTMOptionBuilder() {}

    public RTMOption buildRtmOption(){
        return new RTMOption(_retailerProduct.get(), _rtmName.get() ,_slottingPerSku.get(),
                _freightOutPerUnit.get(), _firstReceiver.get(), _secondReceiver.get(), _thirdReceiver.get(),
                _fourthReceiver.get(), _landedStoreCost.get(), _everydayRetailCalcd.get(), _everydayRetailOverride.get(),
                _elasticizedUnitVelocity.get(), _annualVolumePerSku.get(), _slottingPaybackPeriod.get(),
                _postSpoilsPostFreightPerUnit.get(), _unspentTradePerUnit.get(), _fourYearEqGpPerSku.get(),
                _fourYearEqGpPerUnit.get());
    }
    public RTMOptionBuilder retailerProduct(RetailerProduct _retailerProduct){
        this._retailerProduct = new SimpleObjectProperty<>(_retailerProduct);
        return this;
    }

    public RTMOptionBuilder rtmName(String _rtmName){
        this._rtmName = new SimpleStringProperty(_rtmName);
        return this;
    }
    public RTMOptionBuilder slottingPerSku(BigDecimal _slottingPerSku){
        this._slottingPerSku = new SimpleObjectProperty(_slottingPerSku);
        return this;
    }
    public RTMOptionBuilder freightOutPerUnit(BigDecimal _freightOutPerUnit){
        this._freightOutPerUnit = new SimpleObjectProperty(_freightOutPerUnit);
        return this;
    }
    public RTMOptionBuilder firstReceiver(BigDecimal _firstReceiver){
        this._firstReceiver = new SimpleObjectProperty(_firstReceiver);
        return this;
    }
    public RTMOptionBuilder secondReceiver(BigDecimal _secondReceiver){
        this._secondReceiver = new SimpleObjectProperty(_secondReceiver);
        return this;
    }
    public RTMOptionBuilder thirdReceiver(BigDecimal _thirdReceiver){
        this._thirdReceiver = new SimpleObjectProperty(_thirdReceiver);
        return this;
    }
    public RTMOptionBuilder fourthReceiver(BigDecimal _fourthReceiver){
        this._fourthReceiver = new SimpleObjectProperty(_fourthReceiver);
        return this;
    }
    public RTMOptionBuilder landedStoreCost(BigDecimal _landedStoreCost){
        this._landedStoreCost = new SimpleObjectProperty(_landedStoreCost);
        return this;
    }
    public RTMOptionBuilder everydayRetailCalcd(BigDecimal _everydayRetailCalcd){
        this._everydayRetailCalcd = new SimpleObjectProperty(_everydayRetailCalcd);
        return this;
    }
    public RTMOptionBuilder everydayRetailOverride(BigDecimal _everydayRetailOverride){
        this._everydayRetailOverride = new SimpleObjectProperty(_everydayRetailOverride);
        return this;
    }
    public RTMOptionBuilder elasticizedUnitVelocity(BigDecimal _elasticizedUnitVelocity){
        this._elasticizedUnitVelocity = new SimpleObjectProperty(_elasticizedUnitVelocity);
        return this;
    }
    public RTMOptionBuilder annualVolumePerSku(BigDecimal _annualVolumePerSku){
        this._annualVolumePerSku = new SimpleObjectProperty(_annualVolumePerSku);
        return this;
    }
    public RTMOptionBuilder slottingPaybackPeriod(BigDecimal _slottingPaybackPeriod){
        this._slottingPaybackPeriod = new SimpleObjectProperty(_slottingPaybackPeriod);
        return this;
    }
    public RTMOptionBuilder postSpoilsPostFreightPerUnit(BigDecimal _postSpoilsPostFreightPerUnit){
        this._postSpoilsPostFreightPerUnit = new SimpleObjectProperty(_postSpoilsPostFreightPerUnit);
        return this;
    }
    public RTMOptionBuilder _unspentTradePerUnit(BigDecimal _unspentTradePerUnit){
        this._unspentTradePerUnit = new SimpleObjectProperty(_unspentTradePerUnit);
        return this;
    }
    public RTMOptionBuilder fourYearEqGpPerSku(BigDecimal _fourYearEqGpPerSku){
        this._fourYearEqGpPerSku = new SimpleObjectProperty(_fourYearEqGpPerSku);
        return this;
    }
    public RTMOptionBuilder fourYearEqGpPerUnit(BigDecimal _fourYearEqGpPerUnit){
        this._fourYearEqGpPerUnit = new SimpleObjectProperty(_fourYearEqGpPerUnit);
        return this;
    }
}
