package com.traderoute;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleIntegerProperty yearOneStoreCount;
    private SimpleObjectProperty<BigDecimal> slottingPerSku, freightOutPerUnit, firstReceiver,
            secondReceiver, thirdReceiver, fourthReceiver, landedStoreCost,
            resultingEverydayRetailCalcd, resultingEverydayRetailOverride,
            elasticizedEstimatedUnitVelocity, slottingPaybackPeriod,
            postFreightPostSpoilsWeCollectPerUnit, unspentTradePerUnit, fourYearEqGpPerSku, fourYearEqGpPerUnit,
            minOverride,weeklyUSFWAtMin, everydayGPM, spoilsAndFees, estimatedAnnualVolumePerSku;
    private SimpleObjectProperty<Product> product;

    public RTMOption() {
        this.RTMName = new SimpleStringProperty();
        this.slottingPerSku = new SimpleObjectProperty<BigDecimal>();
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>();
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>();
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>();
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>();
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.estimatedAnnualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postFreightPostSpoilsWeCollectPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.everydayGPM = new SimpleObjectProperty<>();
        this.yearOneStoreCount = new SimpleIntegerProperty();
        this.spoilsAndFees = new SimpleObjectProperty<>();

        this.product = new SimpleObjectProperty<>();

        setupListeners();
    }

    public RTMOption(String RTMName, BigDecimal freightOutPerUnit, BigDecimal slottingPerSku,
                     BigDecimal firstReceiver, BigDecimal secondReceiver,
                     BigDecimal thirdReceiver, BigDecimal fourthReceiver) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleObjectProperty<BigDecimal>(slottingPerSku);
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>(thirdReceiver);
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>(fourthReceiver);
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.estimatedAnnualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postFreightPostSpoilsWeCollectPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.everydayGPM = new SimpleObjectProperty<>();
        this.yearOneStoreCount = new SimpleIntegerProperty();
        this.spoilsAndFees = new SimpleObjectProperty<>();
        this.product = new SimpleObjectProperty<Product>();
        setupListeners();

    }
    public RTMOption(String RTMName, BigDecimal freightOutPerUnit, BigDecimal slottingPerSku,
                     BigDecimal firstReceiver,BigDecimal secondReceiver, Integer yearOneStoreCount, BigDecimal everydayGPM,
                    BigDecimal spoilsAndFees, BigDecimal weeklyUSFWAtMin, BigDecimal minOverride) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleObjectProperty<BigDecimal>(slottingPerSku);
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>();
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>();
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.estimatedAnnualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postFreightPostSpoilsWeCollectPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.minOverride = new SimpleObjectProperty<>(minOverride);
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>(weeklyUSFWAtMin);
        this.everydayGPM = new SimpleObjectProperty<>(everydayGPM);
        this.yearOneStoreCount = new SimpleIntegerProperty(yearOneStoreCount);
        this.spoilsAndFees = new SimpleObjectProperty<>(spoilsAndFees);
        setupListeners();
        this.product = new SimpleObjectProperty<>();
    }

    private void setupListeners() {
        yearOneStoreCountProperty().addListener(new RTMUpdateListener<>(this, true));
        productProperty().addListener(new RTMUpdateListener<>(this, false));
        spoilsAndFeesProperty().addListener(new RTMUpdateListener(this, false));
        slottingPerSkuProperty().addListener(new RTMUpdateListener<>(this, false));
        freightOutPerUnitProperty().addListener(new RTMUpdateListener<>(this, false));
        minOverrideProperty().addListener(new RTMUpdateListener<>(this, true));
        firstReceiverProperty().addListener(new RTMUpdateListener<>(this, true));
        weeklyUSFWAtMinProperty().addListener(new RTMUpdateListener<>(this, true));
        /*
        Check if landedStoreCostProperty changed, if it it did calculate everyday Retail
        */
        landedStoreCostProperty().addListener(new ChangeListener<BigDecimal>() {
            private boolean changing;
            @Override
            public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                if (!changing) {
                    try {
                        changing = true;
                        updateResultingEverydayRetailCald();
                    } finally {
                        changing = false;
                    }
                }
            }
        });
        everydayGPMProperty().addListener(new ChangeListener<BigDecimal>() {
            private boolean changing;
            @Override
            public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                if (!changing) {
                    try {
                        changing = true;
                        System.out.println("Is this working");
                        updateResultingEverydayRetailCald();
                    } finally {
                        changing = false;
                    }
                }
            }
        });
    }
    public void updateResultingEverydayRetailCald(){
        if (getEverydayGPM().compareTo(new BigDecimal("0.0"))>0 &&
                getLandedStoreCost().compareTo(new BigDecimal("0.0"))>0){
            setResultingEverydayRetailCalcd((getLandedStoreCost().multiply(new BigDecimal("100")))
                    .divide((getEverydayGPM().subtract(new BigDecimal("100"))), 10, RoundingMode.HALF_UP).abs());
        }
    }
    public void updateElasticizedEstimatedUnitVelocity() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0 && getMinOverride().compareTo(new BigDecimal("100000000000"))<0
        && getMinOverride().compareTo(new BigDecimal("0.0"))>0 && getResultingEverydayRetailOverride().compareTo(new BigDecimal("0.0"))>0) {
            if (getMinOverride().compareTo(getResultingEverydayRetailOverride())==0) {
                System.out.println("are you getting here");
                setElasticizedEstimatedUnitVelocity(this.getWeeklyUSFWAtMin());
            } else {
                setElasticizedEstimatedUnitVelocity(((getResultingEverydayRetailOverride().subtract(getMinOverride()))
                        .divide((getMinOverride()), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("-1.15"))
                        .multiply(getWeeklyUSFWAtMin())).add(getWeeklyUSFWAtMin()));
            }
        }
    }
    public void updateEstimatedAnnualVolumePerSku(){
        if (getYearOneStoreCount()>0 &&  getElasticizedEstimatedUnitVelocity().compareTo(new BigDecimal("0.0"))>0) {
            setEstimatedAnnualVolumePerSku(((new BigDecimal("52").multiply(new BigDecimal(
                    getYearOneStoreCount()).multiply(getElasticizedEstimatedUnitVelocity())))
                    .setScale(10, RoundingMode.HALF_UP)));
        }
    }

    public void updateSlottingPaybackPeriod(){
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0
                && getMinOverride().compareTo(new BigDecimal("0.0"))>0  && getEstimatedAnnualVolumePerSku().compareTo(new BigDecimal("0.0"))>0) {
            if (getSlottingPerSku().compareTo(new BigDecimal("0.0"))==0){
                setSlottingPaybackPeriod(new BigDecimal("0.0"));
            }
            else {
                setSlottingPaybackPeriod(getSlottingPayback());
            }
        }
    }
    public void updatePostFreightPostSpoilsWeCollect(){
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0
                && getMinOverride().compareTo(new BigDecimal("0.0"))>0  && getEstimatedAnnualVolumePerSku().compareTo(new BigDecimal("0.0"))>0) {
            setPostFreightPostSpoilsWeCollectPerUnit(getPostSpoilsAndFreightWeCollectPerUnit());
        }
    }
    public void updateUnspentTrade(){
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0"))>0
                && getMinOverride().compareTo(new BigDecimal("0.0"))>0  && getEstimatedAnnualVolumePerSku().compareTo(new BigDecimal("0.0"))>0) {
            setUnspentTradePerUnit(getPostSpoilsAndStdAllowancesAvailableTrade());
        }
    }
    public void updateFourYearEqGpPerSku() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0 && getEstimatedAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            setFourYearEqGpPerSku(getGrossProfit());
        }
    }
    public void updateFourYearEqGpPerUnit() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0 && getEstimatedAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            setFourYearEqGpPerUnit(getGrossProfitPerUnit());
        }
    }

    public Product getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<Product> productProperty() {
        if (product==null){
            return new SimpleObjectProperty<Product>();
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product.set(product);
    }

    public Integer getYearOneStoreCount() {
        return yearOneStoreCount.get();
    }

    public SimpleIntegerProperty yearOneStoreCountProperty() {
        if (yearOneStoreCount==null){
            return new SimpleIntegerProperty();
        }
        return yearOneStoreCount;
    }

    public void setYearOneStoreCount(Integer yearOneStoreCount) {
        this.yearOneStoreCount.set(yearOneStoreCount);
    }

    public BigDecimal getEverydayGPM() {
        if (everydayGPM.get()==null){
            return new BigDecimal("0.0");
        }
        return everydayGPM.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
        if (everydayGPM ==null){
            return new SimpleObjectProperty<>();
        }
        return everydayGPM;
    }

    public void setEverydayGPM(BigDecimal everydayGPM) {
        this.everydayGPM.set(everydayGPM);
    }

    public BigDecimal getSpoilsAndFees() {
        return spoilsAndFees.get();
    }

    public SimpleObjectProperty<BigDecimal> spoilsAndFeesProperty() {
        return spoilsAndFees;
    }

    public void setSpoilsAndFees(BigDecimal spoilsAndFees) {
        this.spoilsAndFees.set(spoilsAndFees);
    }

    public boolean isFob() {
        if (getFreightOutPerUnit().compareTo(new BigDecimal(0.0)) > 0) {
            return false;
        }
        return true;
    }

    public BigDecimal getFourYearUnitVolumePerSku(){
        return getEstimatedAnnualVolumePerSku().multiply(new BigDecimal(4.0));
    }
    public BigDecimal getOurFreightCost(){
        return getFourYearUnitVolumePerSku().multiply(getFreightOutPerUnit());
    }
    public BigDecimal getGrossRevenueList(){ //retest //Put list in parameters to calculate
        return getFourYearUnitVolumePerSku().multiply(getProduct().getUnitListCost().setScale(10, RoundingMode.HALF_UP)); // HARDCODED FOR NOW, SHOULD BE LIST PRICE
    }
    public BigDecimal getFobDiscount(){ //retest
        if (isFob()){
            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost())).multiply(getFourYearUnitVolumePerSku()); // HARDCODED FOR NOW: (LIST - FOB)*4 Year Unit VOl /SKu
        }
        return new BigDecimal("0.0");
    }
    public BigDecimal getGrossRevenueActual(){
        return getGrossRevenueList().subtract(getFobDiscount());
    }
    public BigDecimal getSpoilsTrade(){  // retest // Put spoils+fees field value in parameter
        return getGrossRevenueActual().multiply(getSpoilsAndFees());
    }
    public BigDecimal getStandardAllowanceTrade(){ //retest //HARDCODED FOR NOW, LIST PRICE NEEDED
        if (isFob()){
            return ((((getProduct().getUnitListCost()).subtract(getFirstReceiver()))
                    .multiply(getFourYearUnitVolumePerSku())).subtract(getFobDiscount())).setScale(10, RoundingMode.HALF_UP);
        }
        BigDecimal zeroValue = (getProduct().getUnitListCost()).subtract(getFirstReceiver());
        zeroValue = zeroValue.multiply(getFourYearUnitVolumePerSku());
        return zeroValue.setScale(10, RoundingMode.HALF_UP);
    }
    public BigDecimal getAfterSpoilsAndStdAllowanceTrade(){ // retest //HARDCODED FOR NOW, LIST PRICE NEEDED, NET1 GOAL NEEDED
        return (getFourYearUnitVolumePerSku().multiply((getProduct().getUnitListCost()).subtract(getProduct().getUnitNet1Goal()))).subtract(getSpoilsTrade()).subtract(getStandardAllowanceTrade());
    }
    public BigDecimal getIfFobFreightCredit(){
        if (isFob()){
            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost())).multiply(getFourYearUnitVolumePerSku());
        }
        return new BigDecimal("0.0");
    }
    public BigDecimal getEqualsNet1Rev(){
        return getGrossRevenueList().subtract(getSpoilsTrade()).subtract(getStandardAllowanceTrade()).subtract(getAfterSpoilsAndStdAllowanceTrade());
    }
    public BigDecimal getTotalFobAndFreightSpending(){
        return getOurFreightCost().add(getFobDiscount());
    }
    public BigDecimal getEqualsNet2Rev(){
        return getEqualsNet1Rev().subtract(getTotalFobAndFreightSpending());
    }
    public BigDecimal getEqualsNet3Rev(){
        return getEqualsNet2Rev().subtract(getSlottingPerSku());
    }
    public BigDecimal getNetRev3Rate(){
        if (getFourYearUnitVolumePerSku().equals(new BigDecimal("0.0"))){
            return new BigDecimal ("0.0");
        }
        return getEqualsNet3Rev().divide((getFourYearUnitVolumePerSku()),10, RoundingMode.HALF_UP).setScale(10,RoundingMode.HALF_UP);
    }
    // IMPLEMENT COGS AND PASS IT
    public BigDecimal getTotalCogs(){
        return getFourYearUnitVolumePerSku().multiply(getProduct().getUnitBlendedCogs()); //HARDCODED FOR NOW SHOULD BE COGS
    }
    public BigDecimal getGrossProfit(){
        return getEqualsNet3Rev().subtract(getTotalCogs());
    }
    public BigDecimal getGrossProfitPerUnit(){
        return getGrossProfit().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
    }
    // IMPLEMENT THIS IN FIRST TABLE CONTROLLER, get the max from gross profit
//    public BigDecimal getGrossProfitIndex(){
//        if (getGrossProfit().equals(new BigDecimal("0.0"))){
//            return new BigDecimal("0.0");
//        }
//        return getGrossProfit()
//    }

    public BigDecimal getSlottingPayback(){
        return (getSlottingPerSku().divide((getGrossProfitPerUnit()), 10, RoundingMode.HALF_UP)).divide(getEstimatedAnnualVolumePerSku(), 5, RoundingMode.HALF_UP);
    }
    // IMPLEMENT GET GROSS PROFIT INDEX
    public BigDecimal getPostSpoilsAndFreightWeCollect(){
        return getGrossRevenueList().subtract(getSpoilsTrade()).subtract(getOurFreightCost()).subtract(getFobDiscount());
    }
    public BigDecimal getPostSpoilsAndFreightWeCollectPerUnit(){
        return getPostSpoilsAndFreightWeCollect().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
    }
    public BigDecimal getPostSpoilsAndStdAllowancesAvailableTrade(){
        return getAfterSpoilsAndStdAllowanceTrade().divide((getFourYearUnitVolumePerSku()),10 , RoundingMode.HALF_UP);
    }


    public String toString() {
        String stringBuilder = "";
        stringBuilder += "RTMName: " + this.getRTMName() + ", Slotting per Sku:" + this.getSlottingPerSku() + "Calcd" + this.getResultingEverydayRetailCalcd();
        return stringBuilder;
    }

    public String getRTMName() {
        if (RTMName.get()==null){
            return new String("");
        }
        return RTMName.get();
    }

    public void setRTMName(String RTMName) {
        this.RTMName = new SimpleStringProperty(RTMName);
    }
    public SimpleStringProperty RTMNameProperty(){
        if (RTMName==null){
            return new SimpleStringProperty("");
        }
        return RTMName;
    }

    public BigDecimal getSlottingPerSku() {
        if (slottingPerSku.get()==null){
            return new BigDecimal("0.0");
        }
        return slottingPerSku.get();
    }
    public SimpleObjectProperty<BigDecimal> slottingPerSkuProperty() {
        if (slottingPerSku==null){
            return new SimpleObjectProperty<BigDecimal>();
        }
        return slottingPerSku;
    }

    public void setSlottingPerSku(BigDecimal slottingPerSku) {
        this.slottingPerSku.set(slottingPerSku);
    }

    public BigDecimal getFreightOutPerUnit() {
        if (freightOutPerUnit.get() == null) {
            return new BigDecimal("0.0");
        }
        return freightOutPerUnit.get();
    }

    public void setFreightOutPerUnit(BigDecimal freightOutPerUnit) {
        this.freightOutPerUnit.set(freightOutPerUnit);
    }

    public SimpleObjectProperty<BigDecimal> freightOutPerUnitProperty() {
        return freightOutPerUnit;
    }

    public BigDecimal getFirstReceiver() {
        if (firstReceiverProperty().get() == null) {
            return new BigDecimal("0.0");
        }
        return firstReceiverProperty().get();
    }

    ;

    public SimpleObjectProperty<BigDecimal> firstReceiverProperty() {
        return firstReceiver;
    }

    public void setFirstReceiver(BigDecimal firstReceiver) {
        this.firstReceiver.set(firstReceiver);
    }

    public BigDecimal getSecondReceiver() {
        if (secondReceiverProperty().get() == null) {
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
        if (thirdReceiverProperty().get() == null) {
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
        if (fourthReceiverProperty().get() == null) {
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
        if (landedStoreCostProperty().get() == null) {
            return new BigDecimal("0.0");
        }
        return landedStoreCost.get();
    }

    public SimpleObjectProperty<BigDecimal> landedStoreCostProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return landedStoreCost;
    }

    public void setLandedStoreCost(BigDecimal landedStoreCost) {
        this.landedStoreCost.set(landedStoreCost);
    }

    public BigDecimal getResultingEverydayRetailCalcd() {
        if (resultingEverydayRetailCalcd.get() == null) {
            return new BigDecimal("0.0");
        }
        return resultingEverydayRetailCalcd.get();
    }

    public SimpleObjectProperty<BigDecimal> resultingEverydayRetailProperty() {
        if (resultingEverydayRetailCalcd == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return resultingEverydayRetailCalcd;
    }

    public void setResultingEverydayRetailCalcd(BigDecimal resultingEverydayRetailCalcd) {
        this.resultingEverydayRetailCalcd.set(resultingEverydayRetailCalcd);
    }

    public BigDecimal getResultingEverydayRetailOverride() {
        if (resultingEverydayRetailOverride.get() == null) {
            return new BigDecimal("0.0");
        }
        return resultingEverydayRetailOverride.get();
    }

    public SimpleObjectProperty<BigDecimal> resultingEverydayRetailOverrideProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return resultingEverydayRetailOverride;
    }

    public void setResultingEverydayRetailOverride(BigDecimal resultingEverydayRetailOverride) {
        this.resultingEverydayRetailOverride.set(resultingEverydayRetailOverride);
    }

    public BigDecimal getMinOverride() {
        if (minOverride.get()==null){
            return new BigDecimal("0.0");
        }
        return minOverride.get();
    }

    public SimpleObjectProperty<BigDecimal> minOverrideProperty() {
        return minOverride;
    }

    public void setMinOverride(BigDecimal minOverride) {
        this.minOverride.set(minOverride);
    }

    public BigDecimal getWeeklyUSFWAtMin() {
        if (weeklyUSFWAtMin.get()==null){
            return new BigDecimal("0.0");
        }
        return weeklyUSFWAtMin.get();
    }

    public SimpleObjectProperty<BigDecimal> weeklyUSFWAtMinProperty() {
        return weeklyUSFWAtMin;
    }

    public void setWeeklyUSFWAtMin(BigDecimal weeklyUSFWAtMin) {
        this.weeklyUSFWAtMin.set(weeklyUSFWAtMin);
    }

    public BigDecimal getElasticizedEstimatedUnitVelocity() {
        if (elasticizedEstimatedUnitVelocity.get()==null){
            return new BigDecimal("0.0");
        }
        return elasticizedEstimatedUnitVelocity.get();
    }

    public void setElasticizedEstimatedUnitVelocity(BigDecimal elasticizedEstimatedUnitVelocity) {
        this.elasticizedEstimatedUnitVelocity = new SimpleObjectProperty<BigDecimal>(elasticizedEstimatedUnitVelocity);
    }

    public SimpleObjectProperty<BigDecimal> elasticizedEstimatedUnitVelocityProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return elasticizedEstimatedUnitVelocity;
    }

    public BigDecimal getEstimatedAnnualVolumePerSku() {
        if (estimatedAnnualVolumePerSku.get()==null){
            return new BigDecimal("0.0");
        }
        return estimatedAnnualVolumePerSku.get();
    }

    public void setEstimatedAnnualVolumePerSku(BigDecimal estimatedAnnualVolumePerSku) {
        this.estimatedAnnualVolumePerSku = new SimpleObjectProperty(estimatedAnnualVolumePerSku);
    }

    public SimpleObjectProperty estimatedAnnualVolumePerSkuProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty(0);
        }
        return estimatedAnnualVolumePerSku;
    }

    public BigDecimal getSlottingPaybackPeriod() {
        if (slottingPaybackPeriod.get()==null){
            return new BigDecimal("0.0");
        }
        return slottingPaybackPeriod.get();
    }

    public SimpleObjectProperty<BigDecimal> slottingPaybackPeriodProperty() {
        return slottingPaybackPeriod;
    }

    public void setSlottingPaybackPeriod(BigDecimal slottingPaybackPeriod) {
        this.slottingPaybackPeriod.set(slottingPaybackPeriod);
    }

    public BigDecimal getPostFreightPostSpoilsWeCollectPerUnit() {
        if (postFreightPostSpoilsWeCollectPerUnit.get()==null){
            return new BigDecimal("0.0");
        }
        return postFreightPostSpoilsWeCollectPerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> postFreightPostSpoilsWeCollectPerUnitProperty() {
        return postFreightPostSpoilsWeCollectPerUnit;
    }

    public void setPostFreightPostSpoilsWeCollectPerUnit(BigDecimal postFreightPostSpoilsWeCollectPerUnit) {
        this.postFreightPostSpoilsWeCollectPerUnit.set(postFreightPostSpoilsWeCollectPerUnit);
    }

    public BigDecimal getUnspentTradePerUnit() {
        if (unspentTradePerUnit.get()==null){
            return new BigDecimal("0.0");
        }
        return unspentTradePerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> unspentTradePerUnitProperty() {
        return unspentTradePerUnit;
    }

    public void setUnspentTradePerUnit(BigDecimal unspentTradePerUnit) {
        this.unspentTradePerUnit.set(unspentTradePerUnit);
    }

    public BigDecimal getFourYearEqGpPerSku() {
        if (fourYearEqGpPerSku.get()==null){
            return new BigDecimal("0.0");
        }
        return fourYearEqGpPerSku.get();
    }

    public SimpleObjectProperty<BigDecimal> fourYearEqGpPerSkuProperty() {
        return fourYearEqGpPerSku;
    }

    public void setFourYearEqGpPerSku(BigDecimal fourYearEqGpPerSku) {
        this.fourYearEqGpPerSku.set(fourYearEqGpPerSku);
    }

    public BigDecimal getFourYearEqGpPerUnit() {
        if (fourYearEqGpPerUnit.get()==null){
            return new BigDecimal("0.0");
        }
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
Previous implementation of External table values
 */
//        this.fourYearUnitVolumePerSku = new SimpleObjectProperty<BigDecimal>();
//        this.ourFreightCost = new SimpleObjectProperty<BigDecimal>();
//        this.grossRevenueList = new SimpleObjectProperty<BigDecimal>();
//        this.fobDiscount = new SimpleObjectProperty<BigDecimal>();
//        this.grossRevenueActual = new SimpleObjectProperty<BigDecimal>();
//        this.spoilsTrade = new SimpleObjectProperty<BigDecimal>();
//        this.standardAllowanceTrade = new SimpleObjectProperty<BigDecimal>();
//        this.afterSpoilsAndStandardAllowanceAvailableTrade = new SimpleObjectProperty<BigDecimal>();
//        this.ifFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
//        this.equalsNet1Rev = new SimpleObjectProperty<BigDecimal>();
//        this.totalFobFreightCredit = new SimpleObjectProperty<BigDecimal>();
//        this.equalsNet2Rev = new SimpleObjectProperty<BigDecimal>();
//        this.totalFobFreightSpending = new SimpleObjectProperty<BigDecimal>();
//        this.equalsNet3Rev = new SimpleObjectProperty<BigDecimal>();
//        this.netRev3Rate = new SimpleObjectProperty<BigDecimal>();
//        this.cogs = new SimpleObjectProperty<BigDecimal>(new BigDecimal("2.05")); //HARDCODED FOR NOW
//        this.totalCogs = new SimpleObjectProperty<BigDecimal>();
//        this.grossProfit = new SimpleObjectProperty<BigDecimal>();
//        this.grossProfitPerUnit = new SimpleObjectProperty<BigDecimal>();
//        this.grossProfitIndex = new SimpleObjectProperty<BigDecimal>();
//        this.slottingPayback = new SimpleObjectProperty<BigDecimal>();
//        this.postSpoilsAndFreightWeCollect = new SimpleObjectProperty<BigDecimal>();
//        this.postSpoilsAndStdAllowancesAvailableTrade = new SimpleObjectProperty<BigDecimal>();

//    public BigDecimal getFourYearUnitVolumePerSku() {
//        return fourYearUnitVolumePerSku.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> fourYearUnitVolumePerSkuProperty() {
//        return fourYearUnitVolumePerSku;
//    }
//
//    public void setFourYearUnitVolumePerSku(BigDecimal fourYearUnitVolumePerSku) {
//        this.fourYearUnitVolumePerSku.set(fourYearUnitVolumePerSku);
//    }
//
//    public BigDecimal getOurFreightCost() {
//        return ourFreightCost.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> ourFreightCostProperty() {
//        return ourFreightCost;
//    }
//
//    public void setOurFreightCost(BigDecimal ourFreightCost) {
//        this.ourFreightCost.set(ourFreightCost);
//    }
//
//    public BigDecimal getGrossRevenueList() {
//        return grossRevenueList.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> grossRevenueListProperty() {
//        return grossRevenueList;
//    }
//
//    public void setGrossRevenueList(BigDecimal grossRevenueList) {
//        this.grossRevenueList.set(grossRevenueList);
//    }
//
//    public BigDecimal getFobDiscount() {
//        return fobDiscount.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> fobDiscountProperty() {
//        return fobDiscount;
//    }
//
//    public void setFobDiscount(BigDecimal fobDiscount) {
//        this.fobDiscount.set(fobDiscount);
//    }
//
//    public BigDecimal getGrossRevenueActual() {
//        return grossRevenueActual.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> grossRevenueActualProperty() {
//        return grossRevenueActual;
//    }
//
//    public void setGrossRevenueActual(BigDecimal grossRevenueActual) {
//        this.grossRevenueActual.set(grossRevenueActual);
//    }
//
//    public BigDecimal getSpoilsTrade() {
//        return spoilsTrade.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> spoilsTradeProperty() {
//        return spoilsTrade;
//    }
//
//    public void setSpoilsTrade(BigDecimal spoilsTrade) {
//        this.spoilsTrade.set(spoilsTrade);
//    }
//
//    public BigDecimal getStandardAllowanceTrade() {
//        return standardAllowanceTrade.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> standardAllowanceTradeProperty() {
//        return standardAllowanceTrade;
//    }
//
//    public void setStandardAllowanceTrade(BigDecimal standardAllowanceTrade) {
//        this.standardAllowanceTrade.set(standardAllowanceTrade);
//    }
//
//    public BigDecimal getAfterSpoilsAndStandardAllowanceAvailableTrade() {
//        return afterSpoilsAndStandardAllowanceAvailableTrade.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> afterSpoilsAndStandardAllowanceAvailableTradeProperty() {
//        return afterSpoilsAndStandardAllowanceAvailableTrade;
//    }
//
//    public void setAfterSpoilsAndStandardAllowanceAvailableTrade(BigDecimal afterSpoilsAndStandardAllowanceAvailableTrade) {
//        this.afterSpoilsAndStandardAllowanceAvailableTrade.set(afterSpoilsAndStandardAllowanceAvailableTrade);
//    }
//
//    public BigDecimal getIfFobFreightCredit() {
//        return ifFobFreightCredit.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> ifFobFreightCreditProperty() {
//        return ifFobFreightCredit;
//    }
//
//    public void setIfFobFreightCredit(BigDecimal ifFobFreightCredit) {
//        this.ifFobFreightCredit.set(ifFobFreightCredit);
//    }
//
//    public BigDecimal getEqualsNet1Rev() {
//        return equalsNet1Rev.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> equalsNet1RevProperty() {
//        return equalsNet1Rev;
//    }
//
//    public void setEqualsNet1Rev(BigDecimal equalsNet1Rev) {
//        this.equalsNet1Rev.set(equalsNet1Rev);
//    }
//
//    public BigDecimal getTotalFobFreightCredit() {
//        return totalFobFreightCredit.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> totalFobFreightCreditProperty() {
//        return totalFobFreightCredit;
//    }
//
//    public void setTotalFobFreightCredit(BigDecimal totalFobFreightCredit) {
//        this.totalFobFreightCredit.set(totalFobFreightCredit);
//    }
//
//    public BigDecimal getEqualsNet2Rev() {
//        return equalsNet2Rev.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> equalsNet2RevProperty() {
//        return equalsNet2Rev;
//    }
//
//    public void setEqualsNet2Rev(BigDecimal equalsNet2Rev) {
//        this.equalsNet2Rev.set(equalsNet2Rev);
//    }
//
//    public BigDecimal getTotalFobFreightSpending() {
//        return totalFobFreightSpending.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> totalFobFreightSpendingProperty() {
//        return totalFobFreightSpending;
//    }
//
//    public void setTotalFobFreightSpending(BigDecimal totalFobFreightSpending) {
//        this.totalFobFreightSpending.set(totalFobFreightSpending);
//    }
//
//    public BigDecimal getEqualsNet3Rev() {
//        return equalsNet3Rev.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> equalsNet3RevProperty() {
//        return equalsNet3Rev;
//    }
//
//    public void setEqualsNet3Rev(BigDecimal equalsNet3Rev) {
//        this.equalsNet3Rev.set(equalsNet3Rev);
//    }
//
//    public BigDecimal getNetRev3Rate() {
//        return netRev3Rate.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> netRev3RateProperty() {
//        return netRev3Rate;
//    }
//
//    public void setNetRev3Rate(BigDecimal netRev3Rate) {
//        this.netRev3Rate.set(netRev3Rate);
//    }
//
//    public BigDecimal getCogs() {
//        return cogs.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> cogsProperty() {
//        return cogs;
//    }
//
//    public void setCogs(BigDecimal cogs) {
//        this.cogs.set(cogs);
//    }
//
//    public BigDecimal getTotalCogs() {
//        return totalCogs.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> totalCogsProperty() {
//        return totalCogs;
//    }
//
//    public void setTotalCogs(BigDecimal totalCogs) {
//        this.totalCogs.set(totalCogs);
//    }
//
//    public BigDecimal getGrossProfit() {
//        return grossProfit.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> grossProfitProperty() {
//        return grossProfit;
//    }
//
//    public void setGrossProfit(BigDecimal grossProfit) {
//        this.grossProfit.set(grossProfit);
//    }
//
//    public BigDecimal getGrossProfitPerUnit() {
//        return grossProfitPerUnit.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> grossProfitPerUnitProperty() {
//        return grossProfitPerUnit;
//    }
//
//    public void setGrossProfitPerUnit(BigDecimal grossProfitPerUnit) {
//        this.grossProfitPerUnit.set(grossProfitPerUnit);
//    }
//
//    public BigDecimal getGrossProfitIndex() {
//        return grossProfitIndex.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> grossProfitIndexProperty() {
//        return grossProfitIndex;
//    }
//
//    public void setGrossProfitIndex(BigDecimal grossProfitIndex) {
//        this.grossProfitIndex.set(grossProfitIndex);
//    }
//
//    public BigDecimal getSlottingPayback() {
//        return slottingPayback.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> slottingPaybackProperty() {
//        return slottingPayback;
//    }
//
//    public void setSlottingPayback(BigDecimal slottingPayback) {
//        this.slottingPayback.set(slottingPayback);
//    }
//
//    public BigDecimal getPostSpoilsAndFreightWeCollect() {
//        return postSpoilsAndFreightWeCollect.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> postSpoilsAndFreightWeCollectProperty() {
//        return postSpoilsAndFreightWeCollect;
//    }
//
//    public void setPostSpoilsAndFreightWeCollect(BigDecimal postSpoilsAndFreightWeCollect) {
//        this.postSpoilsAndFreightWeCollect.set(postSpoilsAndFreightWeCollect);
//    }
//
//    public BigDecimal getPostSpoilsAndStdAllowancesAvailableTrade() {
//        return postSpoilsAndStdAllowancesAvailableTrade.get();
//    }
//
//    public SimpleObjectProperty<BigDecimal> postSpoilsAndStdAllowancesAvailableTradeProperty() {
//        return postSpoilsAndStdAllowancesAvailableTrade;
//    }
//
//    public void setPostSpoilsAndStdAllowancesAvailableTrade(BigDecimal postSpoilsAndStdAllowancesAvailableTrade) {
//        this.postSpoilsAndStdAllowancesAvailableTrade.set(postSpoilsAndStdAllowancesAvailableTrade);
//    }
//}
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
