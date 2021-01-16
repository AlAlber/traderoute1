package com.traderoute;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleIntegerProperty slottingPerSku, resultingEverydayRetailCalcd,
            resultingEverydayRetailOverride, weeklyVelocityAtMin,weeklyVelocityUsfw,
            elasticizedEstimatedUnitVelocity,annualVolumePerSku,slottingPaybackPeriod,
            postFreightSpoilsWeCollect,unspentTradePerUnit,fourYearEqGpPerSku,fourYearEqGpPerUnit;
    private SimpleDoubleProperty freightOutPerUnit, firstReceiver,secondReceiver,thirdReceiver,
            fourthReceiver,landedStoreCost;


    public RTMOption(String RTMName, Double freightOutPerUnit, Integer slottingPerSku,
                     Double firstReceiver, Double secondReceiver,
                     Double thirdReceiver, Double fourthReceiver, Double landedStoreCost,
                     Integer resultingEverydayRetailCalcd,
                     Integer resultingEverydayRetailOverride,
                     Integer weeklyVelocityAtMin, Integer weeklyVelocityUsfw,
                     Integer elasticizedEstimatedUnitVelocity,
                     Integer annualVolumePerSku, Integer slottingPaybackPeriod,
                     Integer postFreightSpoilsWeCollect, Integer unspentTradePerUnit,
                     Integer fourYearEqGpPerSku, Integer fourYearEqGpPerUnit) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleIntegerProperty(slottingPerSku);
        this.freightOutPerUnit = new SimpleDoubleProperty(freightOutPerUnit);
        this.firstReceiver = new SimpleDoubleProperty(firstReceiver);
        this.secondReceiver = new SimpleDoubleProperty(secondReceiver);
        this.thirdReceiver = new SimpleDoubleProperty(thirdReceiver);
        this.fourthReceiver = new SimpleDoubleProperty(fourthReceiver);
        this.landedStoreCost = new SimpleDoubleProperty(landedStoreCost);
        this.resultingEverydayRetailCalcd = new SimpleIntegerProperty(resultingEverydayRetailCalcd);
        this.resultingEverydayRetailOverride = new SimpleIntegerProperty(resultingEverydayRetailOverride);
        this.weeklyVelocityAtMin = new SimpleIntegerProperty(weeklyVelocityAtMin);
        this.weeklyVelocityUsfw = new SimpleIntegerProperty(weeklyVelocityUsfw);
        this.elasticizedEstimatedUnitVelocity = new SimpleIntegerProperty(elasticizedEstimatedUnitVelocity);
        this.annualVolumePerSku = new SimpleIntegerProperty(annualVolumePerSku);
        this.slottingPaybackPeriod = new SimpleIntegerProperty(slottingPaybackPeriod);
        this.postFreightSpoilsWeCollect = new SimpleIntegerProperty(postFreightSpoilsWeCollect);
        this.unspentTradePerUnit = new SimpleIntegerProperty(unspentTradePerUnit);
        this.fourYearEqGpPerSku = new SimpleIntegerProperty(fourYearEqGpPerSku);
        this.fourYearEqGpPerUnit = new SimpleIntegerProperty(fourYearEqGpPerUnit);
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
        this.freightOutPerUnit = new SimpleDoubleProperty(freightOutPerUnit);
    }

    public double getFirstReceiver() {
        return firstReceiver.get();
    }

    public void setFirstReceiver(double firstReceiver) {
        this.firstReceiver = new SimpleDoubleProperty(firstReceiver);
    }

    public double getSecondReceiver() {
        return secondReceiver.get();
    }

    public void setSecondReceiver(double secondReceiver) {
        this.secondReceiver = new SimpleDoubleProperty(secondReceiver);
    }

    public double getThirdReceiver() {
        return thirdReceiver.get();
    }

    public void setThirdReceiver(double thirdReceiver) {
        this.thirdReceiver = new SimpleDoubleProperty(thirdReceiver);
    }

    public double getFourthReceiver() {
        return fourthReceiver.get();
    }

    public void setFourthReceiver(double fourthReceiver) {
        this.fourthReceiver = new SimpleDoubleProperty(fourthReceiver);
    }
    public double getLandedStoreCost() {
        return landedStoreCost.get();
    }

    public void setLandedStoreCost(double landedStoreCost) {
        this.landedStoreCost = new SimpleDoubleProperty(landedStoreCost);
    }

    public int getResultingEverydayRetailCalcd() {
        return resultingEverydayRetailCalcd.get();
    }

    public void setResultingEverydayRetailCalcd(int resultingEverydayRetailCalcd) {
        this.resultingEverydayRetailCalcd = new SimpleIntegerProperty(resultingEverydayRetailCalcd);
    }

    public int getResultingEverydayRetailOverride() {
        return resultingEverydayRetailOverride.get();
    }

    public void setResultingEverydayRetailOverride(int resultingEverydayRetailOverride) {
        this.resultingEverydayRetailOverride = new SimpleIntegerProperty(resultingEverydayRetailOverride);
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

    public int getElasticizedEstimatedUnitVelocity() {
        return elasticizedEstimatedUnitVelocity.get();
    }

    public void setElasticizedEstimatedUnitVelocity(int elasticizedEstimatedUnitVelocity) {
        this.elasticizedEstimatedUnitVelocity = new SimpleIntegerProperty(elasticizedEstimatedUnitVelocity);
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
