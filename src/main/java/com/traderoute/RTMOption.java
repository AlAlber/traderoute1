package com.traderoute;

import javafx.beans.property.*;


import java.math.BigDecimal;

public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleIntegerProperty slottingPerSku, weeklyVelocityAtMin,weeklyVelocityUsfw,annualVolumePerSku,slottingPaybackPeriod,
            postFreightSpoilsWeCollect,unspentTradePerUnit,fourYearEqGpPerSku,fourYearEqGpPerUnit;
    private SimpleDoubleProperty freightOutPerUnit;
    private SimpleObjectProperty <BigDecimal> landedStoreCost, firstReceiver,secondReceiver,thirdReceiver,
    fourthReceiver, resultingEverydayRetailCalcd,resultingEverydayRetailOverride, elasticizedEstimatedUnitVelocity;
    public RTMOption(){
        this.RTMName = new SimpleStringProperty();
        this.slottingPerSku = new SimpleIntegerProperty();
        this.freightOutPerUnit = new SimpleDoubleProperty();
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>();
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>();
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>();
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>();
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
    }

    public RTMOption(String RTMName, Double freightOutPerUnit, Integer slottingPerSku,
                     BigDecimal firstReceiver, BigDecimal secondReceiver,
                     BigDecimal thirdReceiver, BigDecimal fourthReceiver,
                     Integer resultingEverydayRetailOverride,
                     Integer weeklyVelocityAtMin, Integer weeklyVelocityUsfw,
                     BigDecimal elasticizedEstimatedUnitVelocity,
                     Integer annualVolumePerSku, Integer slottingPaybackPeriod,
                     Integer postFreightSpoilsWeCollect, Integer unspentTradePerUnit,
                     Integer fourYearEqGpPerSku, Integer fourYearEqGpPerUnit) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleIntegerProperty(slottingPerSku);
        this.freightOutPerUnit = new SimpleDoubleProperty(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>(thirdReceiver);
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>(fourthReceiver);
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();

        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.weeklyVelocityAtMin = new SimpleIntegerProperty(weeklyVelocityAtMin);
        this.weeklyVelocityUsfw = new SimpleIntegerProperty(weeklyVelocityUsfw);
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.annualVolumePerSku = new SimpleIntegerProperty(annualVolumePerSku);
        this.slottingPaybackPeriod = new SimpleIntegerProperty(slottingPaybackPeriod);
        this.postFreightSpoilsWeCollect = new SimpleIntegerProperty(postFreightSpoilsWeCollect);
        this.unspentTradePerUnit = new SimpleIntegerProperty(unspentTradePerUnit);
        this.fourYearEqGpPerSku = new SimpleIntegerProperty(fourYearEqGpPerSku);
        this.fourYearEqGpPerUnit = new SimpleIntegerProperty(fourYearEqGpPerUnit);
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

    public double getFreightOutPerUnit() {
        return freightOutPerUnit.get();
    }

    public void setFreightOutPerUnit(double freightOutPerUnit) {
        this.freightOutPerUnit.set(freightOutPerUnit);
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

    public int getWeeklyVelocityAtMin() {
        return weeklyVelocityAtMin.get();
    }

    public void setWeeklyVelocityAtMin(int weeklyVelocityAtMin) {
        this.weeklyVelocityAtMin = new SimpleIntegerProperty(weeklyVelocityAtMin);
    }

    public int getWeeklyVelocityUsfw() {
        return weeklyVelocityUsfw.get();
    }

    public void setWeeklyVelocityUsfw(int weeklyVelocityUsfw) {
        this.weeklyVelocityUsfw = new SimpleIntegerProperty(weeklyVelocityUsfw);
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

    public int getAnnualVolumePerSku() {
        return annualVolumePerSku.get();
    }

    public void setAnnualVolumePerSku(int annualVolumePerSku) {
        this.annualVolumePerSku = new SimpleIntegerProperty(annualVolumePerSku);
    }

    public int getSlottingPaybackPeriod() {
        return slottingPaybackPeriod.get();
    }

    public void setSlottingPaybackPeriod(int slottingPaybackPeriod) {
        this.slottingPaybackPeriod = new SimpleIntegerProperty(slottingPaybackPeriod);
    }

    public int getPostFreightSpoilsWeCollect() {
        return postFreightSpoilsWeCollect.get();
    }

    public void setPostFreightSpoilsWeCollect(int postFreightSpoilsWeCollect) {
        this.postFreightSpoilsWeCollect = new SimpleIntegerProperty(postFreightSpoilsWeCollect);
    }

    public int getUnspentTradePerUnit() {
        return unspentTradePerUnit.get();
    }

    public void setUnspentTradePerUnit(int unspentTradePerUnit) {
        this.unspentTradePerUnit = new SimpleIntegerProperty(unspentTradePerUnit);
    }

    public int getFourYearEqGpPerSku() {
        return fourYearEqGpPerSku.get();
    }

    public void setFourYearEqGpPerSku(int fourYearEqGpPerSku) {
        this.fourYearEqGpPerSku = new SimpleIntegerProperty(fourYearEqGpPerSku);
    }

    public int getFourYearEqGpPerUnit() {
        return fourYearEqGpPerUnit.get();
    }

    public void setFourYearEqGpPerUnit(int fourYearEqGpPerUnit) {
        this.fourYearEqGpPerUnit = new SimpleIntegerProperty(fourYearEqGpPerUnit);
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
