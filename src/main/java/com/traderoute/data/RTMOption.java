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
    private SimpleStringProperty rtmName;
    private SimpleObjectProperty<RetailerProduct> retailerProduct;
    private SimpleObjectProperty<BigDecimal> slottingPerSku, freightOutPerUnit, firstReceiver, secondReceiver,
            thirdReceiver, fourthReceiver, landedStoreCost, everydayRetailCalcd, everydayRetailOverride,
            elasticizedUnitVelocity, slottingPaybackPeriod, postSpoilsPostFreightPerUnit, unspentTradePerUnit,
            fourYearEqGpPerSku, fourYearEqGpPerUnit, annualVolumePerSku;

    public RTMOption(RetailerProduct retailerProduct, String rtmName, BigDecimal slottingPerSku, BigDecimal freightOutPerUnit,
                     BigDecimal firstReceiver, BigDecimal secondReceiver, BigDecimal thirdReceiver, BigDecimal fourthReceiver,
                     BigDecimal landedStoreCost, BigDecimal everydayRetailCalcd, BigDecimal everydayRetailOverride,
                     BigDecimal elasticizedUnitVelocity, BigDecimal annualVolumePerSku, BigDecimal slottingPaybackPeriod,
                     BigDecimal postSpoilsPostFreightPerUnit, BigDecimal unspentTradePerUnit, BigDecimal fourYearEqGpPerSku,
                     BigDecimal fourYearEqGpPerUnit) {
        this.retailerProduct = new SimpleObjectProperty<>(retailerProduct);
        this.rtmName = new SimpleStringProperty(rtmName);
        this.slottingPerSku = new SimpleObjectProperty<>(slottingPerSku);
        this.freightOutPerUnit = new SimpleObjectProperty<>(freightOutPerUnit);
        this.firstReceiver = new SimpleObjectProperty<>(firstReceiver);
        this.secondReceiver = new SimpleObjectProperty<>(secondReceiver);
        this.thirdReceiver = new SimpleObjectProperty<>(thirdReceiver);
        this.fourthReceiver = new SimpleObjectProperty<>(fourthReceiver);
        this.landedStoreCost = new SimpleObjectProperty<>(landedStoreCost);
        this.everydayRetailCalcd = new SimpleObjectProperty<>(everydayRetailCalcd);
        this.everydayRetailOverride = new SimpleObjectProperty<>(everydayRetailOverride);
        this.elasticizedUnitVelocity = new SimpleObjectProperty<>(elasticizedUnitVelocity);
        this.annualVolumePerSku = new SimpleObjectProperty<>(annualVolumePerSku);
        this.slottingPaybackPeriod = new SimpleObjectProperty<>(slottingPaybackPeriod);
        this.postSpoilsPostFreightPerUnit = new SimpleObjectProperty<>(postSpoilsPostFreightPerUnit);
        this.unspentTradePerUnit = new SimpleObjectProperty<>(unspentTradePerUnit);
        this.fourYearEqGpPerSku = new SimpleObjectProperty<>(fourYearEqGpPerSku);
        this.fourYearEqGpPerUnit = new SimpleObjectProperty<>(fourYearEqGpPerUnit);
    }

    public void setupListeners() {
        retailerProductProperty().addListener(((arg, oldVal, newVal) -> {
            yearOneStoreCountProperty().addListener(new RTMUpdateListener<>(this, true));
            firstReceiverProperty().addListener(new RTMUpdateListener<>(this, true));
            everydayRetailOverrideProperty().addListener(new RTMUpdateListener<>(this, true));
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
        if (allPositive(observableArrayList(weeklyUSFWAtMinProperty(), minOverrideProperty(), everydayRetailOverride))
                && getMinOverride().compareTo(new BigDecimal("100000000000")) < 0) {
            if (getMinOverride().compareTo(getEverydayRetailOverride()) == 0) {
                setElasticizedUnitVelocity(this.getWeeklyUSFWAtMin());
            } else {
                setElasticizedUnitVelocity(((getEverydayRetailOverride().subtract(getMinOverride()))
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
    public RetailerProductSpecs getSpecs(){
        return getRetailerProduct().getSpecs();
    }

    public Integer getYearOneStoreCount() {
        return getSpecs().getYearOneStoreCount();
    }

    public SimpleIntegerProperty yearOneStoreCountProperty() {
        return getSpecs().yearOneStoreCountProperty();
    }

    public BigDecimal getEverydayGPM() {
        return getSpecs().getEverydayGpm();
    }

    public SimpleObjectProperty<BigDecimal> everydayGPMProperty() {
        return getSpecs().everydayGpmProperty();
    }

    public BigDecimal getSpoilsAndFees() {
        return getSpecs().getSpoilsAndFees();
    }

    public SimpleObjectProperty<BigDecimal> spoilsAndFeesProperty() {
        return getSpecs().spoilsAndFeesProperty();
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
        stringBuilder += "RTMName: " + this.getRtmName() + ", Slotting per Sku:" + this.getSlottingPerSku() + "Calcd"
                + this.getEverydayRetailCalcd();
        return stringBuilder;
    }

    public String getRtmName() {
        if (rtmName.get() == null) {
            return new String("");
        }
        return rtmName.get();
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
        return rtmName;
    }

    public void setRtmName(String rtmName) {
        this.rtmName = new SimpleStringProperty(rtmName);
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

    public BigDecimal getEverydayRetailOverride() {
        if (everydayRetailOverride.get() == null) {
            return new BigDecimal("0.0");
        }
        return everydayRetailOverride.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayRetailOverrideProperty() {
        if (landedStoreCost == null) {
            return new SimpleObjectProperty<BigDecimal>(new BigDecimal("0.0"));
        }
        return everydayRetailOverride;
    }

    public void setEverydayRetailOverride(BigDecimal resultingEverydayRetailOverride) {
        this.everydayRetailOverride.set(resultingEverydayRetailOverride);
    }

    public BigDecimal getMinOverride() {
        return getSpecs().getMinOverride();
    }

    public SimpleObjectProperty<BigDecimal> minOverrideProperty() {
        return getSpecs().minOverrideProperty();
    }

    public BigDecimal getWeeklyUSFWAtMin() {
        return getSpecs().getWeeklyUSFWAtMin();
    }

    public SimpleObjectProperty<BigDecimal> weeklyUSFWAtMinProperty() {
        return getSpecs().weeklyUSFWAtMinProperty();
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
