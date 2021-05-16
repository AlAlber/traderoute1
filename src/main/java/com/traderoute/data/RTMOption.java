package com.traderoute.data;

import com.traderoute.RTMUpdateListener;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.*;

//@Getter
public class RTMOption {
    private SimpleStringProperty RTMName;
    private SimpleObjectProperty<RetailerProduct> retailerProduct;
    private SimpleObjectProperty<BigDecimal> slottingPerSku, freightOutPerUnit, firstReceiver, secondReceiver,
            thirdReceiver, fourthReceiver, landedStoreCost, everydayRetailCalcd, resultingEverydayRetailOverride,
            elasticizedUnitVelocity, slottingPaybackPeriod, postSpoilsPostFreightPerUnit, unspentTradePerUnit,
            fourYearEqGpPerSku, fourYearEqGpPerUnit, annualVolumePerSku;
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
        this.elasticizedUnitVelocity = new SimpleObjectProperty<BigDecimal>();
        this.annualVolumePerSku = new SimpleObjectProperty<>();
        this.slottingPaybackPeriod = new SimpleObjectProperty<BigDecimal>();
        this.postSpoilsPostFreightPerUnit = new SimpleObjectProperty<BigDecimal>();
        this.unspentTradePerUnit = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerSku = new SimpleObjectProperty<BigDecimal>();
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<BigDecimal>();

        this.retailerProduct = new SimpleObjectProperty<RetailerProduct>();

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

        this.retailerProduct = new SimpleObjectProperty<RetailerProduct>();

    }

    public void setupListeners() {
        retailerProductProperty().addListener(((arg, oldVal, newVal) -> {
            yearOneStoreCountProperty().addListener(new RTMUpdateListener<>(this, true));
            firstReceiverProperty().addListener(new RTMUpdateListener<>(this, true));
            resultingEverydayRetailOverrideProperty().addListener(new RTMUpdateListener<>(this, true));
            productProperty().addListener(new RTMUpdateListener<>(this, false));
            spoilsAndFeesProperty().addListener(new RTMUpdateListener(this, false));
            minOverrideProperty().addListener(new RTMUpdateListener<>(this, true));
            weeklyUSFWAtMinProperty().addListener(new RTMUpdateListener<>(this, true));
            everydayGPMProperty().addListener(((arg1, oldVal1, newVal1) -> {
                updateResultingEverydayRetailCald();
            }));
        }));
        slottingPerSkuProperty().addListener(new RTMUpdateListener<>(this, false));
        freightOutPerUnitProperty().addListener(new RTMUpdateListener<>(this, false));

        /*
         * Check if landedStoreCostProperty changed, if it it did calculate everyday Retail
         */
        landedStoreCostProperty().addListener(((arg, oldVal, newVal) -> {
            updateResultingEverydayRetailCald();
        }));

    }

    public void updateResultingEverydayRetailCald() {
        if (allPositive(observableArrayList(everydayGPMProperty(), landedStoreCost))) {
            BigDecimal newValue = (getLandedStoreCost().multiply(new BigDecimal("100")))
                    .divide((getEverydayGPM().subtract(new BigDecimal("100"))), 4, RoundingMode.HALF_UP).abs();
            setEverydayRetailCalcd(newValue);
            setEverydayRetailOverride(newValue);
        }
    }

    public void updateElasticizedUnitVelocity() {
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(), resultingEverydayRetailOverride))
                && getMinOverride().compareTo(new BigDecimal("100000000000")) < 0) {
            if (getMinOverride().compareTo(getResultingEverydayRetailOverride()) == 0) {
                setElasticizedUnitVelocity(this.getWeeklyUSFWAtMin());
            } else {
                setElasticizedUnitVelocity(((getResultingEverydayRetailOverride().subtract(getMinOverride()))
                        .divide((getMinOverride()), 4, RoundingMode.HALF_UP)
                        .multiply(getProduct().getElasticityMultiple()).multiply(getWeeklyUSFWAtMin()))
                                .add(getWeeklyUSFWAtMin()));
            }
        }
    }

    public void updateEstimatedAnnualVolumePerSku() {
        if (getYearOneStoreCount() > 0 && getElasticizedUnitVelocity().compareTo(new BigDecimal("0.0")) > 0) {
            setAnnualVolumePerSku(((new BigDecimal("52")
                    .multiply(new BigDecimal(getYearOneStoreCount()).multiply(getElasticizedUnitVelocity())))
                            .setScale(4, RoundingMode.HALF_UP)));
        }
    }

    public void updateSlottingPaybackPeriod() {
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(),
                annualVolumePerSku))) {
            if (getSlottingPerSku().compareTo(new BigDecimal("0.0")) == 0) {
                setSlottingPaybackPeriod(new BigDecimal("0.0"));
            } else {
                setSlottingPaybackPeriod(calcSlottingPaybackPeriod());
            }
        }
    }

    public void updatePostFreightPostSpoilsWeCollect() {
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(),
                annualVolumePerSku))) {
            setPostSpoilsPostFreightPerUnit(calcPostSpoilsAndFreightWeCollectPerUnit());
        }
    }

    public void updateUnspentTrade() {
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(),
                annualVolumePerSku))) {
            setUnspentTradePerUnit(calcPostSpoilsAndStdAllowancesAvailableTrade());
        }
    }

    public void updateFourYearEqGpPerSku() {
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(),
                annualVolumePerSku))) {
            setFourYearEqGpPerSku(calcGrossProfit().setScale(4, RoundingMode.HALF_UP));
        }
    }

    public void updateFourYearEqGpPerUnit() {
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(),
                annualVolumePerSku))) {
            setFourYearEqGpPerUnit(calcGrossProfitPerUnit().setScale(4, RoundingMode.HALF_UP));
        }
    }
    public boolean allPositive(ObservableList<SimpleObjectProperty<BigDecimal>> properties){
        boolean allGreaterThanZero = true;
        List<SimpleObjectProperty<BigDecimal>> propertiesSmallerThanZero =
                properties.stream().filter(e -> e.get().compareTo(new BigDecimal("0.0"))<=0).collect(Collectors.toList());
        if (propertiesSmallerThanZero.size()>0){
            allGreaterThanZero = false;
        } return allGreaterThanZero;
    }

    public Product getProduct() {
        return getRetailerProduct().getProduct();
    }

    public SimpleObjectProperty<Product> productProperty() {
        return getRetailerProduct().productProperty();
    }

    public Integer getYearOneStoreCount() {
        return getRetailerProduct().getYearOneStoreCount();
    }

    public SimpleIntegerProperty yearOneStoreCountProperty() {
        return getRetailerProduct().yearOneStoreCountProperty();
    }

    public BigDecimal getEverydayGPM() {
        return getRetailerProduct().getEverydayGpm();
    }

    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
        return getRetailerProduct().everydayGpmProperty();
    }

    public BigDecimal getSpoilsAndFees() {
        return getRetailerProduct().getSpoilsAndFees();
    }

    public SimpleObjectProperty<BigDecimal> spoilsAndFeesProperty() {
        return getRetailerProduct().spoilsAndFeesProperty();
    }

    public boolean isFob() {
        if (getFreightOutPerUnit().compareTo(new BigDecimal(0.0)) > 0) {
            return false;
        }
        return true;
    }

    public BigDecimal calcFourYearUnitVolumePerSku() {
        return getAnnualVolumePerSku().multiply(new BigDecimal(4.0));
    }

    public BigDecimal calcOurFreightCost() {
        return calcFourYearUnitVolumePerSku().multiply(getFreightOutPerUnit());
    }

    public BigDecimal calcGrossRevenueList() {
        return calcFourYearUnitVolumePerSku()
                .multiply(getProduct().getUnitListCost().setScale(4, RoundingMode.HALF_UP));
    }

    public BigDecimal calcFobDiscount() {
        if (isFob()) {
            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost()))
                    .multiply(calcFourYearUnitVolumePerSku());
        }
        return new BigDecimal("0.0");
    }

    public BigDecimal calcGrossRevenueActual() {
        return calcGrossRevenueList().subtract(calcFobDiscount());
    }

    public BigDecimal calcSpoilsTrade() { // retest // Put spoils+fees field value in parameter
        return calcGrossRevenueActual().multiply(getSpoilsAndFees());
    }

    public BigDecimal calcStandardAllowanceTrade() { // retest //HARDCODED FOR NOW, LIST PRICE NEEDED
        if (isFob()) {
            return ((((getProduct().getUnitListCost()).subtract(getFirstReceiver()))
                    .multiply(calcFourYearUnitVolumePerSku())).subtract(calcFobDiscount())).setScale(4,
                            RoundingMode.HALF_UP);
        }
        BigDecimal zeroValue = (getProduct().getUnitListCost()).subtract(getFirstReceiver());
        zeroValue = zeroValue.multiply(calcFourYearUnitVolumePerSku());
        return zeroValue.setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal calcAfterSpoilsAndStdAllowanceTrade() { // retest //HARDCODED FOR NOW, LIST PRICE NEEDED, NET1 GOAL
                                                             // NEEDED
        return (calcFourYearUnitVolumePerSku()
                .multiply((getProduct().getUnitListCost()).subtract(getProduct().getUnitNet1Goal())))
                        .subtract(calcSpoilsTrade()).subtract(calcStandardAllowanceTrade());
    }

    public BigDecimal calcIfFobFreightCredit() {
        if (isFob()) {
            return ((getProduct().getUnitListCost()).subtract(getProduct().getUnitFobCost()))
                    .multiply(calcFourYearUnitVolumePerSku());
        }
        return new BigDecimal("0.0");
    }

    public BigDecimal calcEqualsNet1Rev() {
        return calcGrossRevenueList().subtract(calcSpoilsTrade()).subtract(calcStandardAllowanceTrade())
                .subtract(calcAfterSpoilsAndStdAllowanceTrade());
    }

    public BigDecimal calcTotalFobAndFreightSpending() {
        return calcOurFreightCost().add(calcFobDiscount());
    }

    public BigDecimal calcEqualsNet2Rev() {
        return calcEqualsNet1Rev().subtract(calcTotalFobAndFreightSpending());
    }

    public BigDecimal calcEqualsNet3Rev() {
        return calcEqualsNet2Rev().subtract(getSlottingPerSku());
    }

    public BigDecimal calcNetRev3Rate() {
        if (calcFourYearUnitVolumePerSku().equals(new BigDecimal("0.0"))) {
            return new BigDecimal("0.0");
        }
        return calcEqualsNet3Rev().divide((calcFourYearUnitVolumePerSku()), 4, RoundingMode.HALF_UP);
    }
=
    public BigDecimal calcTotalCogs() {
        return calcFourYearUnitVolumePerSku().multiply(getProduct().getUnitBlendedCogs());
    }
    public BigDecimal calcGrossProfit() {
        return calcEqualsNet3Rev().subtract(calcTotalCogs());
    }

    public BigDecimal calcGrossProfitPerUnit() {
        return calcGrossProfit().divide((calcFourYearUnitVolumePerSku()), 4, RoundingMode.HALF_UP);
    }
    // IMPLEMENT THIS IN FIRST TABLE CONTROLLER, get the max from gross profit
    // public BigDecimal getGrossProfitIndex(){
    // if (getGrossProfit().equals(new BigDecimal("0.0"))){
    // return new BigDecimal("0.0");
    // }
    // return getGrossProfit()
    // }

    public BigDecimal calcSlottingPaybackPeriod() {
        return (getSlottingPerSku().divide((calcGrossProfitPerUnit()), 4, RoundingMode.HALF_UP))
                .divide(getAnnualVolumePerSku(), 4, RoundingMode.HALF_UP);
    }

    public BigDecimal calcPostSpoilsAndFreightWeCollect() {
        return calcGrossRevenueList().subtract(calcSpoilsTrade()).subtract(calcOurFreightCost())
                .subtract(calcFobDiscount());
    }

    public BigDecimal calcPostSpoilsAndFreightWeCollectPerUnit() {
        return calcPostSpoilsAndFreightWeCollect().divide((calcFourYearUnitVolumePerSku()), 4, RoundingMode.HALF_UP);
    }

    public BigDecimal calcPostSpoilsAndStdAllowancesAvailableTrade() {
        return calcAfterSpoilsAndStdAllowanceTrade().divide((calcFourYearUnitVolumePerSku()), 4, RoundingMode.HALF_UP);
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

    public RetailerProduct getRetailerProduct() {
        return retailerProduct.get();
    }

    public SimpleObjectProperty<RetailerProduct> retailerProductProperty() {
        return retailerProduct;
    }

    public void setRetailerProduct(RetailerProduct retailerProduct) {
        this.retailerProduct.set(retailerProduct);
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

    public void setEverydayRetailOverride(BigDecimal resultingEverydayRetailOverride) {
        this.resultingEverydayRetailOverride.set(resultingEverydayRetailOverride);
    }

    public BigDecimal getMinOverride() {
        return getRetailerProduct().getMinOverride();
    }

    public SimpleObjectProperty<BigDecimal> minOverrideProperty() {
        return getRetailerProduct().minOverrideProperty();
    }

    public BigDecimal getWeeklyUSFWAtMin() {
        return getRetailerProduct().getWeeklyUSFWAtMin();
    }

    public SimpleObjectProperty<BigDecimal> weeklyUSFWAtMinProperty() {
        return getRetailerProduct().weeklyUSFWAtMinProperty();
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
