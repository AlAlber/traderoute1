package com.traderoute;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleIntegerProperty slottingPerSku,estimatedAnnualVolumePerSku;
    SimpleBooleanProperty isFob;
    private SimpleObjectProperty <BigDecimal> freightOutPerUnit, firstReceiver,
            secondReceiver,thirdReceiver, fourthReceiver,landedStoreCost,
            resultingEverydayRetailCalcd,resultingEverydayRetailOverride,
            elasticizedEstimatedUnitVelocity, slottingPaybackPeriod, postFreightPostSpoilsWeCollectPerUnit
            ,unspentTradePerUnit,fourYearEqGpPerSku,fourYearEqGpPerUnit,
            fourYearUnitVolumePerSku,ourFreightCost, grossRevenueList, fobDiscount, grossRevenueActual, spoilsTrade, standardAllowanceTrade,
            afterSpoilsAndStandardAllowanceAvailableTrade, ifFobFreightCredit, equalsNet1Rev, totalFobFreightCredit, equalsNet2Rev,
            totalFobFreightSpending, equalsNet3Rev, netRev3Rate,cogs,totalCogs, grossProfit,grossProfitPerUnit,
            grossProfitIndex, slottingPayback, postSpoilsAndFreightWeCollect, postSpoilsAndStdAllowancesAvailableTrade;


    ;

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
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postFreightPostSpoilsWeCollectPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.fourYearUnitVolumePerSku = new SimpleObjectProperty<BigDecimal>();
        this.ourFreightCost = new SimpleObjectProperty<BigDecimal>();
        this.grossRevenueList = new SimpleObjectProperty<BigDecimal>();
        this.fobDiscount = new SimpleObjectProperty<BigDecimal>();
        this.grossRevenueActual = new SimpleObjectProperty<BigDecimal>();
        this.spoilsTrade = new SimpleObjectProperty<BigDecimal>();
        this.standardAllowanceTrade = new SimpleObjectProperty<BigDecimal>();
        this.afterSpoilsAndStandardAllowanceAvailableTrade = new SimpleObjectProperty<BigDecimal>();
        this.ifFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
        this.equalsNet1Rev = new SimpleObjectProperty<BigDecimal>();
        this.totalFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
        this.equalsNet2Rev = new SimpleObjectProperty<BigDecimal>();
        this.totalFobFreightSpending = new SimpleObjectProperty<BigDecimal>();
        this.equalsNet3Rev = new SimpleObjectProperty<BigDecimal>();
        this.netRev3Rate = new SimpleObjectProperty<BigDecimal>();
        this.cogs = new SimpleObjectProperty<BigDecimal>(new BigDecimal("2.05")); //HARDCODED FOR NOW
        this.totalCogs = new SimpleObjectProperty<BigDecimal>();
        this.grossProfit = new SimpleObjectProperty<BigDecimal>();
        this.grossProfitPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.grossProfitIndex = new SimpleObjectProperty<BigDecimal>();
        this.slottingPayback = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsAndFreightWeCollect = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsAndStdAllowancesAvailableTrade = new SimpleObjectProperty<BigDecimal>();
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
        this.postFreightPostSpoilsWeCollectPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.fourYearUnitVolumePerSku = new SimpleObjectProperty<BigDecimal>();
        this.ourFreightCost = new SimpleObjectProperty<BigDecimal>();
        this.grossRevenueList = new SimpleObjectProperty<BigDecimal>();
        this.fobDiscount = new SimpleObjectProperty<BigDecimal>();
        this.grossRevenueActual = new SimpleObjectProperty<BigDecimal>();
        this.spoilsTrade = new SimpleObjectProperty<BigDecimal>();
        this.standardAllowanceTrade = new SimpleObjectProperty<BigDecimal>();
        this.afterSpoilsAndStandardAllowanceAvailableTrade = new SimpleObjectProperty<BigDecimal>();
        this.ifFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
        this.equalsNet1Rev = new SimpleObjectProperty<BigDecimal>();
        this.totalFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
        this.equalsNet2Rev = new SimpleObjectProperty<BigDecimal>();
        this.totalFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
        this.equalsNet3Rev = new SimpleObjectProperty<BigDecimal>();
        this.netRev3Rate = new SimpleObjectProperty<BigDecimal>();
        this.cogs = new SimpleObjectProperty<BigDecimal>(new BigDecimal("2.05")); //HARDCODED FOR NOW
        this.totalCogs = new SimpleObjectProperty<BigDecimal>();
        this.grossProfit = new SimpleObjectProperty<BigDecimal>();
        this.grossProfitPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.grossProfitIndex = new SimpleObjectProperty<BigDecimal>();
        this.slottingPayback = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsAndFreightWeCollect = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsAndStdAllowancesAvailableTrade = new SimpleObjectProperty<BigDecimal>();
        setUpInternalListeners();
    }

    private void setUpInternalListeners() {
        fourYearUnitVolumePerSku.addListener(new ChangeListener<BigDecimal>() {
            private boolean changing;
            @Override
            public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                if (!changing) {
                    try {
                        changing = true;
//                        setResultingEverydayRetailCalcd(((row.getLandedStoreCost().multiply(new BigDecimal("100")))
//                                .divide((getEveryDayGPM().subtract(new BigDecimal("100"))), 2, RoundingMode.HALF_UP).abs()));
                    } finally {
                        changing = false;
                    }
                }
            }
        });
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

    public BigDecimal getPostFreightPostSpoilsWeCollectPerUnit() {
        return postFreightPostSpoilsWeCollectPerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> postFreightPostSpoilsWeCollectPerUnitProperty() {
        return postFreightPostSpoilsWeCollectPerUnit;
    }

    public void setPostFreightPostSpoilsWeCollectPerUnit(BigDecimal postFreightPostSpoilsWeCollectPerUnit) {
        this.postFreightPostSpoilsWeCollectPerUnit.set(postFreightPostSpoilsWeCollectPerUnit);
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

    public BigDecimal getFourYearUnitVolumePerSku() {
        return fourYearUnitVolumePerSku.get();
    }

    public SimpleObjectProperty<BigDecimal> fourYearUnitVolumePerSkuProperty() {
        return fourYearUnitVolumePerSku;
    }

    public void setFourYearUnitVolumePerSku(BigDecimal fourYearUnitVolumePerSku) {
        this.fourYearUnitVolumePerSku.set(fourYearUnitVolumePerSku);
    }

    public BigDecimal getOurFreightCost() {
        return ourFreightCost.get();
    }

    public SimpleObjectProperty<BigDecimal> ourFreightCostProperty() {
        return ourFreightCost;
    }

    public void setOurFreightCost(BigDecimal ourFreightCost) {
        this.ourFreightCost.set(ourFreightCost);
    }

    public BigDecimal getGrossRevenueList() {
        return grossRevenueList.get();
    }

    public SimpleObjectProperty<BigDecimal> grossRevenueListProperty() {
        return grossRevenueList;
    }

    public void setGrossRevenueList(BigDecimal grossRevenueList) {
        this.grossRevenueList.set(grossRevenueList);
    }

    public BigDecimal getFobDiscount() {
        return fobDiscount.get();
    }

    public SimpleObjectProperty<BigDecimal> fobDiscountProperty() {
        return fobDiscount;
    }

    public void setFobDiscount(BigDecimal fobDiscount) {
        this.fobDiscount.set(fobDiscount);
    }

    public BigDecimal getGrossRevenueActual() {
        return grossRevenueActual.get();
    }

    public SimpleObjectProperty<BigDecimal> grossRevenueActualProperty() {
        return grossRevenueActual;
    }

    public void setGrossRevenueActual(BigDecimal grossRevenueActual) {
        this.grossRevenueActual.set(grossRevenueActual);
    }

    public BigDecimal getSpoilsTrade() {
        return spoilsTrade.get();
    }

    public SimpleObjectProperty<BigDecimal> spoilsTradeProperty() {
        return spoilsTrade;
    }

    public void setSpoilsTrade(BigDecimal spoilsTrade) {
        this.spoilsTrade.set(spoilsTrade);
    }

    public BigDecimal getStandardAllowanceTrade() {
        return standardAllowanceTrade.get();
    }

    public SimpleObjectProperty<BigDecimal> standardAllowanceTradeProperty() {
        return standardAllowanceTrade;
    }

    public void setStandardAllowanceTrade(BigDecimal standardAllowanceTrade) {
        this.standardAllowanceTrade.set(standardAllowanceTrade);
    }

    public BigDecimal getAfterSpoilsAndStandardAllowanceAvailableTrade() {
        return afterSpoilsAndStandardAllowanceAvailableTrade.get();
    }

    public SimpleObjectProperty<BigDecimal> afterSpoilsAndStandardAllowanceAvailableTradeProperty() {
        return afterSpoilsAndStandardAllowanceAvailableTrade;
    }

    public void setAfterSpoilsAndStandardAllowanceAvailableTrade(BigDecimal afterSpoilsAndStandardAllowanceAvailableTrade) {
        this.afterSpoilsAndStandardAllowanceAvailableTrade.set(afterSpoilsAndStandardAllowanceAvailableTrade);
    }

    public BigDecimal getIfFobFreightCredit() {
        return ifFobFreightCredit.get();
    }

    public SimpleObjectProperty<BigDecimal> ifFobFreightCreditProperty() {
        return ifFobFreightCredit;
    }

    public void setIfFobFreightCredit(BigDecimal ifFobFreightCredit) {
        this.ifFobFreightCredit.set(ifFobFreightCredit);
    }

    public BigDecimal getEqualsNet1Rev() {
        return equalsNet1Rev.get();
    }

    public SimpleObjectProperty<BigDecimal> equalsNet1RevProperty() {
        return equalsNet1Rev;
    }

    public void setEqualsNet1Rev(BigDecimal equalsNet1Rev) {
        this.equalsNet1Rev.set(equalsNet1Rev);
    }

    public BigDecimal getTotalFobFreightCredit() {
        return totalFobFreightCredit.get();
    }

    public SimpleObjectProperty<BigDecimal> totalFobFreightCreditProperty() {
        return totalFobFreightCredit;
    }

    public void setTotalFobFreightCredit(BigDecimal totalFobFreightCredit) {
        this.totalFobFreightCredit.set(totalFobFreightCredit);
    }

    public BigDecimal getEqualsNet2Rev() {
        return equalsNet2Rev.get();
    }

    public SimpleObjectProperty<BigDecimal> equalsNet2RevProperty() {
        return equalsNet2Rev;
    }

    public void setEqualsNet2Rev(BigDecimal equalsNet2Rev) {
        this.equalsNet2Rev.set(equalsNet2Rev);
    }

    public BigDecimal getTotalFobFreightSpending() {
        return totalFobFreightSpending.get();
    }

    public SimpleObjectProperty<BigDecimal> totalFobFreightSpendingProperty() {
        return totalFobFreightSpending;
    }

    public void setTotalFobFreightSpending(BigDecimal totalFobFreightSpending) {
        this.totalFobFreightSpending.set(totalFobFreightSpending);
    }

    public BigDecimal getEqualsNet3Rev() {
        return equalsNet3Rev.get();
    }

    public SimpleObjectProperty<BigDecimal> equalsNet3RevProperty() {
        return equalsNet3Rev;
    }

    public void setEqualsNet3Rev(BigDecimal equalsNet3Rev) {
        this.equalsNet3Rev.set(equalsNet3Rev);
    }

    public BigDecimal getNetRev3Rate() {
        return netRev3Rate.get();
    }

    public SimpleObjectProperty<BigDecimal> netRev3RateProperty() {
        return netRev3Rate;
    }

    public void setNetRev3Rate(BigDecimal netRev3Rate) {
        this.netRev3Rate.set(netRev3Rate);
    }

    public BigDecimal getCogs() {
        return cogs.get();
    }

    public SimpleObjectProperty<BigDecimal> cogsProperty() {
        return cogs;
    }

    public void setCogs(BigDecimal cogs) {
        this.cogs.set(cogs);
    }

    public BigDecimal getTotalCogs() {
        return totalCogs.get();
    }

    public SimpleObjectProperty<BigDecimal> totalCogsProperty() {
        return totalCogs;
    }

    public void setTotalCogs(BigDecimal totalCogs) {
        this.totalCogs.set(totalCogs);
    }

    public BigDecimal getGrossProfit() {
        return grossProfit.get();
    }

    public SimpleObjectProperty<BigDecimal> grossProfitProperty() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit.set(grossProfit);
    }

    public BigDecimal getGrossProfitPerUnit() {
        return grossProfitPerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> grossProfitPerUnitProperty() {
        return grossProfitPerUnit;
    }

    public void setGrossProfitPerUnit(BigDecimal grossProfitPerUnit) {
        this.grossProfitPerUnit.set(grossProfitPerUnit);
    }

    public BigDecimal getGrossProfitIndex() {
        return grossProfitIndex.get();
    }

    public SimpleObjectProperty<BigDecimal> grossProfitIndexProperty() {
        return grossProfitIndex;
    }

    public void setGrossProfitIndex(BigDecimal grossProfitIndex) {
        this.grossProfitIndex.set(grossProfitIndex);
    }

    public BigDecimal getSlottingPayback() {
        return slottingPayback.get();
    }

    public SimpleObjectProperty<BigDecimal> slottingPaybackProperty() {
        return slottingPayback;
    }

    public void setSlottingPayback(BigDecimal slottingPayback) {
        this.slottingPayback.set(slottingPayback);
    }

    public BigDecimal getPostSpoilsAndFreightWeCollect() {
        return postSpoilsAndFreightWeCollect.get();
    }

    public SimpleObjectProperty<BigDecimal> postSpoilsAndFreightWeCollectProperty() {
        return postSpoilsAndFreightWeCollect;
    }

    public void setPostSpoilsAndFreightWeCollect(BigDecimal postSpoilsAndFreightWeCollect) {
        this.postSpoilsAndFreightWeCollect.set(postSpoilsAndFreightWeCollect);
    }

    public BigDecimal getPostSpoilsAndStdAllowancesAvailableTrade() {
        return postSpoilsAndStdAllowancesAvailableTrade.get();
    }

    public SimpleObjectProperty<BigDecimal> postSpoilsAndStdAllowancesAvailableTradeProperty() {
        return postSpoilsAndStdAllowancesAvailableTrade;
    }

    public void setPostSpoilsAndStdAllowancesAvailableTrade(BigDecimal postSpoilsAndStdAllowancesAvailableTrade) {
        this.postSpoilsAndStdAllowancesAvailableTrade.set(postSpoilsAndStdAllowancesAvailableTrade);
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
