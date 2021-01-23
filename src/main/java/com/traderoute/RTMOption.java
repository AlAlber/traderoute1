package com.traderoute;

import javafx.beans.property.*;


import java.math.BigDecimal;

public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleIntegerProperty slottingPerSku,estimatedAnnualVolumePerSku;
    private SimpleObjectProperty <BigDecimal> freightOutPerUnit, firstReceiver,secondReceiver,thirdReceiver,
    fourthReceiver,landedStoreCost, resultingEverydayRetailCalcd,resultingEverydayRetailOverride, elasticizedEstimatedUnitVelocity,
            slottingPaybackPeriod, postFreightPostSpoilsWeCollect,unspentTradePerUnit,fourYearEqGpPerSku,fourYearEqGpPerUnit;

    public RTMOption(){
        this.RTMName = new SimpleStringProperty();
        this.slottingPerSku = new SimpleIntegerProperty();
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>();
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>();
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>();
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>();
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.estimatedAnnualVolumePerSku = new SimpleIntegerProperty();
    }

    public RTMOption(String RTMName, BigDecimal freightOutPerUnit, Integer slottingPerSku,
                     BigDecimal firstReceiver, BigDecimal secondReceiver,
                     BigDecimal thirdReceiver, BigDecimal fourthReceiver) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleIntegerProperty(slottingPerSku);
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>(thirdReceiver);
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>(fourthReceiver);
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.estimatedAnnualVolumePerSku = new SimpleIntegerProperty();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postFreightPostSpoilsWeCollect = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();
    }

    public String toString (){
        String stringBuilder= "";
        stringBuilder += "RTMName: " + this.getRTMName() + ", Slotting per Sku:"+ this.getSlottingPerSku()+
            ", Landed Store Cost:" + this.getLandedStoreCost()+ "Calculated:"+ this.getResultingEverydayRetailCalcd();

        return stringBuilder;
    }

    public String getRTMName() {
        return RTMName.get();
    }

    public void setRTMName(String RTMName) {
        this.RTMName = new SimpleStringProperty(RTMName);
    }

    public int getSlottingPerSku() {
        return slottingPerSku.get();
    }

    public void setSlottingPerSku(int slottingPerSku) {
        this.slottingPerSku = new SimpleIntegerProperty(slottingPerSku);
    }

    public BigDecimal getFreightOutPerUnit() {
        if (firstReceiverProperty().get()==null) {
            return new BigDecimal("0.0");
        }
        return freightOutPerUnit.get();
    }

    public void setFreightOutPerUnit(BigDecimal freightOutPerUnit) {
        this.freightOutPerUnit.set(freightOutPerUnit);
    }
    public SimpleObjectProperty<BigDecimal> freightOutPerUnitProperty() {
        return firstReceiver;
    }

    public BigDecimal getFirstReceiver (){
        if (firstReceiverProperty().get()==null) {
            return new BigDecimal("0.0");
        }
        return firstReceiverProperty().get();
    };
    public SimpleObjectProperty<BigDecimal> firstReceiverProperty() {
        return firstReceiver;
    }

    public void setFirstReceiver(BigDecimal firstReceiver) {
        this.firstReceiver.set(firstReceiver);
    }

    public BigDecimal getSecondReceiver() {
        if (secondReceiverProperty().get()==null) {
            return new BigDecimal("0.0");
        }
        return secondReceiver.get();
    }

    public SimpleObjectProperty<BigDecimal> secondReceiverProperty() {
        return secondReceiver;
    }

    public void setSecondReceiver(BigDecimal secondReceiver) {
        this.secondReceiver.set(secondReceiver);
    }

    public BigDecimal getThirdReceiver() {
        if (thirdReceiverProperty().get()==null) {
            return new BigDecimal("0.0");
        }
        return thirdReceiver.get();
    }

    public SimpleObjectProperty<BigDecimal> thirdReceiverProperty() {
        return thirdReceiver;
    }

    public void setThirdReceiver(BigDecimal thirdReceiver) {
        this.thirdReceiver.set(thirdReceiver);
    }
    public BigDecimal getFourthReceiver() {
        if (fourthReceiverProperty().get()==null) {
            return new BigDecimal("0.0");
        }
        return fourthReceiver.get();
    }

    public SimpleObjectProperty<BigDecimal> fourthReceiverProperty() {
        return fourthReceiver;
    }

    public void setFourthReceiver(BigDecimal fourthReceiver) {
        this.fourthReceiver.set(fourthReceiver);
    }

    public BigDecimal getLandedStoreCost() {
        if (landedStoreCostProperty().get()==null ){
            return new BigDecimal("0.0");
        }
        return landedStoreCost.get();
    }

    public SimpleObjectProperty<BigDecimal> landedStoreCostProperty() {
        if (landedStoreCost==null){
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return landedStoreCost;
    }

    public void setLandedStoreCost(BigDecimal landedStoreCost) {
        this.landedStoreCost.set(landedStoreCost);
    }

    public BigDecimal getResultingEverydayRetailCalcd() {
        if (resultingEverydayRetailOverride==null){
            return new BigDecimal(0.0);
        }
        return resultingEverydayRetailCalcd.get();
    }

    public SimpleObjectProperty<BigDecimal> resultingEverydayRetailProperty() {
        if (landedStoreCost==null){
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return resultingEverydayRetailCalcd;
    }

    public void setResultingEverydayRetailCalcd(BigDecimal resultingEverydayRetailCalcd) {
        this.resultingEverydayRetailCalcd.set(resultingEverydayRetailCalcd);
    }

    public BigDecimal getResultingEverydayRetailOverride() {
        if (resultingEverydayRetailOverride.get()==null){
            return new BigDecimal("0.0");
        }
        return resultingEverydayRetailOverride.get();
    }
    public SimpleObjectProperty<BigDecimal> resultingEverydayRetailOverrideProperty() {
        if (landedStoreCost==null){
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return resultingEverydayRetailOverride;
    }

    public void setResultingEverydayRetailOverride(BigDecimal resultingEverydayRetailOverride) {
        this.resultingEverydayRetailOverride.set(resultingEverydayRetailOverride);
    }

    public BigDecimal getElasticizedEstimatedUnitVelocity() {
        return elasticizedEstimatedUnitVelocity.get();
    }

    public void setElasticizedEstimatedUnitVelocity(BigDecimal elasticizedEstimatedUnitVelocity) {
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>(elasticizedEstimatedUnitVelocity);
    }
    public SimpleObjectProperty<BigDecimal> elasticizedEstimatedUnitVelocityProperty() {
        if (landedStoreCost==null){
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return elasticizedEstimatedUnitVelocity;
    }

    public int getEstimatedAnnualVolumePerSku() {
        return estimatedAnnualVolumePerSku.get();
    }

    public void setEstimatedAnnualVolumePerSku(int estimatedAnnualVolumePerSku) {
        this.estimatedAnnualVolumePerSku = new SimpleIntegerProperty(estimatedAnnualVolumePerSku);
    }
    public SimpleIntegerProperty estimatedAnnualVolumePerSkuProperty() {
        if (landedStoreCost==null){
            return new SimpleIntegerProperty(0);
        }
        return estimatedAnnualVolumePerSku;
    }

    public BigDecimal getSlottingPaybackPeriod() {
        return slottingPaybackPeriod.get();
    }

    public SimpleObjectProperty<BigDecimal> slottingPaybackPeriodProperty() {
        return slottingPaybackPeriod;
    }

    public void setSlottingPaybackPeriod(BigDecimal slottingPaybackPeriod) {
        this.slottingPaybackPeriod.set(slottingPaybackPeriod);
    }

    public BigDecimal getPostFreightPostSpoilsWeCollect() {
        return postFreightPostSpoilsWeCollect.get();
    }

    public SimpleObjectProperty<BigDecimal> postFreightPostSpoilsWeCollectProperty() {
        return postFreightPostSpoilsWeCollect;
    }

    public void setPostFreightPostSpoilsWeCollect(BigDecimal postFreightPostSpoilsWeCollect) {
        this.postFreightPostSpoilsWeCollect.set(postFreightPostSpoilsWeCollect);
    }

    public BigDecimal getUnspentTradePerUnit() {
        return unspentTradePerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> unspentTradePerUnitProperty() {
        return unspentTradePerUnit;
    }

    public void setUnspentTradePerUnit(BigDecimal unspentTradePerUnit) {
        this.unspentTradePerUnit.set(unspentTradePerUnit);
    }

    public BigDecimal getFourYearEqGpPerSku() {
        return fourYearEqGpPerSku.get();
    }

    public SimpleObjectProperty<BigDecimal> fourYearEqGpPerSkuProperty() {
        return fourYearEqGpPerSku;
    }

    public void setFourYearEqGpPerSku(BigDecimal fourYearEqGpPerSku) {
        this.fourYearEqGpPerSku.set(fourYearEqGpPerSku);
    }

    public BigDecimal getFourYearEqGpPerUnit() {
        return fourYearEqGpPerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> fourYearEqGpPerUnitProperty() {
        return fourYearEqGpPerUnit;
    }

    public void setFourYearEqGpPerUnit(BigDecimal fourYearEqGpPerUnit) {
        this.fourYearEqGpPerUnit.set(fourYearEqGpPerUnit);
    }
}
/*
Old Bindings used
 */

//        this.landedStoreCost = new ObjectPropertyBase<>(Bindings.max(this.firstReceiverProperty(),Bindings.max(
//                this.secondReceiverProperty(),Bindings.max(this.thirdReceiverProperty(),this.fourthReceiverProperty()))));
//        this.landedStoreCost.bind(Bindings.createObjectBinding(() -> Bindings.max(this.firstReceiverProperty(),Bindings.max(
//                this.secondReceiverProperty(),Bindings.max(this.thirdReceiverProperty(),this.fourthReceiverProperty()))),firstReceiver,secondReceiver,thirdReceiver,fourthReceiver));


//        NumberBinding multipliedGPM = Bindings.divide(Bindings.multiply(this.landedStoreCostProperty() , 100), Bindings.subtract(100, this.everyDayGPM));

//        NumberBinding hundredMinusGPM = Bindings.subtract(100,this.everyDayGPMProperty());
//        NumberBinding hundredTimesLSC = Bindings.multiply(100, this.landedStoreCostProperty());
//        NumberBinding multipliedGPM = hundredTimesLSC.divide(hundredMinusGPM);
//        this.resultingEverydayRetailProperty().bind(multipliedGPM);
/*
Everyday GPM still as field
 */

//    public void setEveryDayGPM(BigDecimal everyDayGPM) {
//        this.everyDayGPM.set(everyDayGPM);
//    }
//public BigDecimal getEveryDayGPM() {
//        if (everyDayGPM.get()==null){
//            return new BigDecimal("0.0");
//        }
//        return everyDayGPM.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> everyDayGPMProperty() {
//        if (everyDayGPM==null) {
//            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
//        }
//        return everyDayGPM;
//    }
