package com.traderoute.data;

import com.traderoute.RTMUpdateListener;
import javafx.beans.property.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleIntegerProperty yearOneStoreCount;
    private SimpleObjectProperty<BigDecimal> slottingPerSku, freightOutPerUnit, firstReceiver, secondReceiver,
            thirdReceiver, fourthReceiver, landedStoreCost, everydayRetailCalcd, resultingEverydayRetailOverride,
            elasticizedUnitVelocity, slottingPaybackPeriod, postSpoilsPostFreightPerUnit, unspentTradePerUnit,
            fourYearEqGpPerSku, fourYearEqGpPerUnit, minOverride, weeklyUSFWAtMin, everydayGPM, spoilsAndFees,
            annualVolumePerSku;
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
        this.everydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.elasticizedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.annualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsPostFreightPerUnit = new SimpleObjectProperty<BigDecimal>();
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

    public RTMOption(String RTMName, BigDecimal freightOutPerUnit, BigDecimal slottingPerSku, BigDecimal firstReceiver,
            BigDecimal secondReceiver, BigDecimal thirdReceiver, BigDecimal fourthReceiver) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleObjectProperty<BigDecimal>(slottingPerSku);
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>(thirdReceiver);
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>(fourthReceiver);
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.everydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.elasticizedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.annualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsPostFreightPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.minOverride = new SimpleObjectProperty<>();
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>();
        this.everydayGPM = new SimpleObjectProperty<>();
        this.yearOneStoreCount = new SimpleIntegerProperty();
        this.spoilsAndFees = new SimpleObjectProperty<>();
        this.product = new SimpleObjectProperty<Product>();
        // setupListeners();

    }

    public RTMOption(String RTMName, BigDecimal freightOutPerUnit, BigDecimal slottingPerSku, BigDecimal firstReceiver,
            BigDecimal secondReceiver, Integer yearOneStoreCount, BigDecimal everydayGPM, BigDecimal spoilsAndFees,
            BigDecimal weeklyUSFWAtMin, BigDecimal minOverride) {
        this.RTMName = new SimpleStringProperty(RTMName);
        this.slottingPerSku = new SimpleObjectProperty<BigDecimal>(slottingPerSku);
        this.freightOutPerUnit = new SimpleObjectProperty<BigDecimal>(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<BigDecimal>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<BigDecimal>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<BigDecimal>();
        this.fourthReceiver = new SimpleObjectProperty<BigDecimal>();
        this.landedStoreCost = new SimpleObjectProperty<BigDecimal>();
        this.everydayRetailCalcd = new SimpleObjectProperty<BigDecimal>();
        this.resultingEverydayRetailOverride = new SimpleObjectProperty<BigDecimal>();
        this.elasticizedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.annualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsPostFreightPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.minOverride = new SimpleObjectProperty<>(minOverride);
        this.weeklyUSFWAtMin = new SimpleObjectProperty<>(weeklyUSFWAtMin);
        this.everydayGPM = new SimpleObjectProperty<>(everydayGPM);
        this.yearOneStoreCount = new SimpleIntegerProperty(yearOneStoreCount);
        this.spoilsAndFees = new SimpleObjectProperty<>(spoilsAndFees);
        this.product = new SimpleObjectProperty<>();
    }

    public void setupListeners() {
        yearOneStoreCountProperty().addListener(new RTMUpdateListener<>(this, true));
        productProperty().addListener(new RTMUpdateListener<>(this, false));
        spoilsAndFeesProperty().addListener(new RTMUpdateListener(this, false));
        slottingPerSkuProperty().addListener(new RTMUpdateListener<>(this, false));
        freightOutPerUnitProperty().addListener(new RTMUpdateListener<>(this, false));
        resultingEverydayRetailOverrideProperty().addListener(new RTMUpdateListener<>(this, true));
        minOverrideProperty().addListener(new RTMUpdateListener<>(this, true));
        firstReceiverProperty().addListener(new RTMUpdateListener<>(this, true));
        weeklyUSFWAtMinProperty().addListener(new RTMUpdateListener<>(this, true));
        /*
         * Check if landedStoreCostProperty changed, if it it did calculate everyday Retail
         */
        landedStoreCostProperty().addListener(((arg, oldVal, newVal) -> {
            updateResultingEverydayRetailCald();
        }));
        everydayGPMProperty().addListener(((arg, oldVal, newVal) -> {
            updateResultingEverydayRetailCald();
        }));
    }

    public void updateResultingEverydayRetailCald() {
        if (getEverydayGPM().compareTo(new BigDecimal("0.0")) > 0
                && getLandedStoreCost().compareTo(new BigDecimal("0.0")) > 0) {
            BigDecimal newValue = (getLandedStoreCost().multiply(new BigDecimal("100")))
                    .divide((getEverydayGPM().subtract(new BigDecimal("100"))), 10, RoundingMode.HALF_UP).abs();
            setEverydayRetailCalcd(newValue);
            setResultingEverydayRetailOverride(newValue);
        }
    }

    public void updateElasticizedUnitVelocity() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("100000000000")) < 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0
                && getResultingEverydayRetailOverride().compareTo(new BigDecimal("0.0")) > 0) {
            if (getMinOverride().compareTo(getResultingEverydayRetailOverride()) == 0) {
                setElasticizedUnitVelocity(this.getWeeklyUSFWAtMin());
            } else {
                setElasticizedUnitVelocity(((getResultingEverydayRetailOverride().subtract(getMinOverride()))
                        .divide((getMinOverride()), 10, RoundingMode.HALF_UP)
                        .multiply(getProduct().getElasticityMultiple()).multiply(getWeeklyUSFWAtMin()))
                                .add(getWeeklyUSFWAtMin()));
            }
        }
    }

    public void updateEstimatedAnnualVolumePerSku() {
        if (getYearOneStoreCount() > 0 && getElasticizedUnitVelocity().compareTo(new BigDecimal("0.0")) > 0) {
            setAnnualVolumePerSku(((new BigDecimal("52")
                    .multiply(new BigDecimal(getYearOneStoreCount()).multiply(getElasticizedUnitVelocity())))
                            .setScale(10, RoundingMode.HALF_UP)));
        }
    }

    public void updateSlottingPaybackPeriod() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0
                && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            if (getSlottingPerSku().compareTo(new BigDecimal("0.0")) == 0) {
                setSlottingPaybackPeriod(new BigDecimal("0.0"));
            } else {
                setSlottingPaybackPeriod(getSlottingPayback());
            }
        }
    }

    public void updatePostFreightPostSpoilsWeCollect() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0
                && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            setPostSpoilsPostFreightPerUnit(getPostSpoilsAndFreightWeCollectPerUnit());
        }
    }

    public void updateUnspentTrade() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0
                && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            setUnspentTradePerUnit(getPostSpoilsAndStdAllowancesAvailableTrade());
        }
    }

    public void updateFourYearEqGpPerSku() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0
                && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            setFourYearEqGpPerSku(getGrossProfit());
        }
    }

    public void updateFourYearEqGpPerUnit() {
        if (getWeeklyUSFWAtMin().compareTo(new BigDecimal("0.0")) > 0
                && getMinOverride().compareTo(new BigDecimal("0.0")) > 0
                && getAnnualVolumePerSku().compareTo(new BigDecimal("0.0")) > 0) {
            setFourYearEqGpPerUnit(getGrossProfitPerUnit());
        }
    }

    public Product getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<Product> productProperty() {
        if (product == null) {
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
        if (yearOneStoreCount == null) {
            return new SimpleIntegerProperty();
        }
        return yearOneStoreCount;
    }

    public void setYearOneStoreCount(Integer yearOneStoreCount) {
        this.yearOneStoreCount.set(yearOneStoreCount);
    }

    public BigDecimal getEverydayGPM() {
        if (everydayGPM.get() == null) {
            return new BigDecimal("0.0");
        }
        return everydayGPM.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
        if (everydayGPM == null) {
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

    public BigDecimal getFourYearUnitVolumePerSku() {
        return getAnnualVolumePerSku().multiply(new BigDecimal(4.0));
    }

    public BigDecimal getOurFreightCost() {
        return getFourYearUnitVolumePerSku().multiply(getFreightOutPerUnit());
    }

    public BigDecimal getGrossRevenueList() {
        return getFourYearUnitVolumePerSku()
                .multiply(getProduct().getUnitListCost().setScale(10, RoundingMode.HALF_UP));
    }

    public BigDecimal getFobDiscount() {
        if (isFob()) {
            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost()))
                    .multiply(getFourYearUnitVolumePerSku());
        }
        return new BigDecimal("0.0");
    }

    public BigDecimal getGrossRevenueActual() {
        return getGrossRevenueList().subtract(getFobDiscount());
    }

    public BigDecimal getSpoilsTrade() { // retest // Put spoils+fees field value in parameter
        return getGrossRevenueActual().multiply(getSpoilsAndFees());
    }

    public BigDecimal getStandardAllowanceTrade() { // retest //HARDCODED FOR NOW, LIST PRICE NEEDED
        if (isFob()) {
            return ((((getProduct().getUnitListCost()).subtract(getFirstReceiver()))
                    .multiply(getFourYearUnitVolumePerSku())).subtract(getFobDiscount())).setScale(10,
                            RoundingMode.HALF_UP);
        }
        BigDecimal zeroValue = (getProduct().getUnitListCost()).subtract(getFirstReceiver());
        zeroValue = zeroValue.multiply(getFourYearUnitVolumePerSku());
        return zeroValue.setScale(10, RoundingMode.HALF_UP);
    }

    public BigDecimal getAfterSpoilsAndStdAllowanceTrade() { // retest //HARDCODED FOR NOW, LIST PRICE NEEDED, NET1 GOAL
                                                             // NEEDED
        return (getFourYearUnitVolumePerSku()
                .multiply((getProduct().getUnitListCost()).subtract(getProduct().getUnitNet1Goal())))
                        .subtract(getSpoilsTrade()).subtract(getStandardAllowanceTrade());
    }

    public BigDecimal getIfFobFreightCredit() {
        if (isFob()) {
            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost()))
                    .multiply(getFourYearUnitVolumePerSku());
        }
        return new BigDecimal("0.0");
    }

    public BigDecimal getEqualsNet1Rev() {
        return getGrossRevenueList().subtract(getSpoilsTrade()).subtract(getStandardAllowanceTrade())
                .subtract(getAfterSpoilsAndStdAllowanceTrade());
    }

    public BigDecimal getTotalFobAndFreightSpending() {
        return getOurFreightCost().add(getFobDiscount());
    }

    public BigDecimal getEqualsNet2Rev() {
        return getEqualsNet1Rev().subtract(getTotalFobAndFreightSpending());
    }

    public BigDecimal getEqualsNet3Rev() {
        return getEqualsNet2Rev().subtract(getSlottingPerSku());
    }

    public BigDecimal getNetRev3Rate() {
        if (getFourYearUnitVolumePerSku().equals(new BigDecimal("0.0"))) {
            return new BigDecimal("0.0");
        }
        return getEqualsNet3Rev().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP).setScale(10,
                RoundingMode.HALF_UP);
    }

    // IMPLEMENT COGS AND PASS IT
    public BigDecimal getTotalCogs() {
        return getFourYearUnitVolumePerSku().multiply(getProduct().getUnitBlendedCogs()); // HARDCODED FOR NOW SHOULD BE
                                                                                          // COGS
    }

    public BigDecimal getGrossProfit() {
        return getEqualsNet3Rev().subtract(getTotalCogs());
    }

    public BigDecimal getGrossProfitPerUnit() {
        return getGrossProfit().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
    }
    // IMPLEMENT THIS IN FIRST TABLE CONTROLLER, get the max from gross profit
    // public BigDecimal getGrossProfitIndex(){
    // if (getGrossProfit().equals(new BigDecimal("0.0"))){
    // return new BigDecimal("0.0");
    // }
    // return getGrossProfit()
    // }

    public BigDecimal getSlottingPayback() {
        return (getSlottingPerSku().divide((getGrossProfitPerUnit()), 10, RoundingMode.HALF_UP))
                .divide(getAnnualVolumePerSku(), 5, RoundingMode.HALF_UP);
    }

    // IMPLEMENT GET GROSS PROFIT INDEX
    public BigDecimal getPostSpoilsAndFreightWeCollect() {
        return getGrossRevenueList().subtract(getSpoilsTrade()).subtract(getOurFreightCost())
                .subtract(getFobDiscount());
    }

    public BigDecimal getPostSpoilsAndFreightWeCollectPerUnit() {
        return getPostSpoilsAndFreightWeCollect().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
    }

    public BigDecimal getPostSpoilsAndStdAllowancesAvailableTrade() {
        return getAfterSpoilsAndStdAllowanceTrade().divide((getFourYearUnitVolumePerSku()), 10, RoundingMode.HALF_UP);
    }

    public String toString() {
        String stringBuilder = "";
        stringBuilder += "RTMName: " + this.getRTMName() + ", Slotting per Sku:" + this.getSlottingPerSku() + "Calcd"
                + this.getEverydayRetailCalcd();
        return stringBuilder;
    }

    public String getRTMName() {
        if (RTMName.get() == null) {
            return new String("");
        }
        return RTMName.get();
    }

    public SimpleStringProperty rtmNameProperty() {
        return RTMName;
    }

    public void setRTMName(String RTMName) {
        this.RTMName = new SimpleStringProperty(RTMName);
    }

    public SimpleStringProperty RTMNameProperty() {
        if (RTMName == null) {
            return new SimpleStringProperty("");
        }
        return RTMName;
    }

    public BigDecimal getSlottingPerSku() {
        if (slottingPerSku.get() == null) {
            return new BigDecimal("0.0");
        }
        return slottingPerSku.get();
    }

    public SimpleObjectProperty<BigDecimal> slottingPerSkuProperty() {
        if (slottingPerSku == null) {
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

    public BigDecimal getEverydayRetailCalcd() {
        if (everydayRetailCalcd.get() == null) {
            return new BigDecimal("0.0");
        }
        return everydayRetailCalcd.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayRetailCalcdProperty() {
        if (everydayRetailCalcd == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return everydayRetailCalcd;
    }

    public void setEverydayRetailCalcd(BigDecimal everydayRetailCalcd) {
        this.everydayRetailCalcd.set(everydayRetailCalcd);
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
        if (minOverride.get() == null) {
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
        if (weeklyUSFWAtMin.get() == null) {
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

    public BigDecimal getElasticizedUnitVelocity() {
        if (elasticizedUnitVelocity.get() == null) {
            return new BigDecimal("0.0");
        }
        return elasticizedUnitVelocity.get();
    }

    public void setElasticizedUnitVelocity(BigDecimal elasticizedUnitVelocity) {
        this.elasticizedUnitVelocity = new SimpleObjectProperty<BigDecimal>(elasticizedUnitVelocity);
    }

    public SimpleObjectProperty<BigDecimal> elasticizedUnitVelocityProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return elasticizedUnitVelocity;
    }

    public BigDecimal getAnnualVolumePerSku() {
        if (annualVolumePerSku.get() == null) {
            return new BigDecimal("0.0");
        }
        return annualVolumePerSku.get();
    }

    public void setAnnualVolumePerSku(BigDecimal annualVolumePerSku) {
        this.annualVolumePerSku = new SimpleObjectProperty(annualVolumePerSku);
    }

    public SimpleObjectProperty annualVolumePerSkuProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty(0);
        }
        return annualVolumePerSku;
    }

    public BigDecimal getSlottingPaybackPeriod() {
        if (slottingPaybackPeriod.get() == null) {
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

    public BigDecimal getPostSpoilsPostFreightPerUnit() {
        if (postSpoilsPostFreightPerUnit.get() == null) {
            return new BigDecimal("0.0");
        }
        return postSpoilsPostFreightPerUnit.get();
    }

    public SimpleObjectProperty<BigDecimal> postSpoilsPostFreightPerUnitProperty() {
        return postSpoilsPostFreightPerUnit;
    }

    public void setPostSpoilsPostFreightPerUnit(BigDecimal postSpoilsPostFreightPerUnit) {
        this.postSpoilsPostFreightPerUnit.set(postSpoilsPostFreightPerUnit);
    }

    public BigDecimal getUnspentTradePerUnit() {
        if (unspentTradePerUnit.get() == null) {
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
        if (fourYearEqGpPerSku.get() == null) {
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
        if (fourYearEqGpPerUnit.get() == null) {
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
