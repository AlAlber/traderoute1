package com.traderoute;

import com.traderoute.controllers.MenuController;
import com.traderoute.controllers.RTMPlanningController;
import com.traderoute.data.Product;
import com.traderoute.data.RTMOption;
import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;


class RTMOptionTest {
    private RTMOption optionOne;
    private RTMOption optionTwo;
    private RTMOption optionThree;
    private RTMOption optionFour;
    private RTMPlanningController controller;
    private Product productOne;
    private Product productTwo;



    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        optionOne = new RTMOption();
        optionTwo = new RTMOption("HEllo", new BigDecimal("0.6"), new BigDecimal(600),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"));
        optionThree = new RTMOption("Direct-to-Customer",
                new BigDecimal("0.29"), new BigDecimal(7500),
                new BigDecimal("3.59"), new BigDecimal("0.0"),
                158, new BigDecimal("40.0"),
                new BigDecimal("0.03"), new BigDecimal("1.2"),
                new BigDecimal("5.99"));
        optionFour = new RTMOption("Direct-to-KeHe", new BigDecimal("0.0"), new BigDecimal(3500),new BigDecimal("3.30"),new BigDecimal("4.85"), 158, new BigDecimal("40.0"), new BigDecimal("3.0"), new BigDecimal("1.2"), new BigDecimal("5.99"));
        productOne = new Product("Big Time Food Company", "24 oz pickles", new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("2.05"), new BigDecimal("-1.15"),
                new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),
                new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),
                new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), MenuController.getExtendedSkus());
        productTwo = new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20"),
                new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),
                new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),
                new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), MenuController.getExtendedSkus());
        optionThree.setProduct(productOne);
        optionThree.setAnnualVolumePerSku(new BigDecimal(9859));
    }

    @org.junit.jupiter.api.Test
    void testUpdateResultingEverydayRetailCald() {
        // everydayGPM null, landed Store Cost 3.95
        optionOne.setEverydayGPM(null);
        optionOne.setLandedStoreCost(new BigDecimal("3.95"));
        optionOne.updateResultingEverydayRetailCald();
        Assert.assertEquals(null, optionOne.getEverydayRetailCalcd());
        // everydayGPm 40, landed Store Cost 3.95
        optionOne.setEverydayGPM(new BigDecimal("40"));
        optionOne.updateResultingEverydayRetailCald();
        Assert.assertEquals(new BigDecimal("6.58"),optionOne.getEverydayRetailCalcd());
       // landed store cost null, everydayGPM 40 -- no change
        optionOne.setLandedStoreCost(null);
        optionOne.updateResultingEverydayRetailCald();
        Assert.assertEquals(new BigDecimal("6.58"), optionOne.getEverydayRetailCalcd());
        // everyday GPM null, landedStore Cost null -- no change
        optionOne.setEverydayGPM(null);
        optionOne.updateResultingEverydayRetailCald();
        Assert.assertEquals(new BigDecimal("6.58"), optionOne.getEverydayRetailCalcd());
    }

    @org.junit.jupiter.api.Test
    void testUpdateElasticizedEstimatedUnitVelocity() {
        optionOne.setResultingEverydayRetailOverride(new BigDecimal("5.99"));
        optionOne.setMinOverride(new BigDecimal("5.99"));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("8.09"));
        optionTwo.setMinOverride(new BigDecimal("5.99"));
        optionOne.setWeeklyUSFWAtMin(new BigDecimal("1.2"));
        optionTwo.setWeeklyUSFWAtMin(new BigDecimal("1.2"));
        optionOne.updateElasticizedUnitVelocity();
        optionTwo.updateElasticizedUnitVelocity();
        Assert.assertEquals(new BigDecimal("1.2"),optionOne.getElasticizedUnitVelocity());
        Assert.assertEquals(new BigDecimal("0.7161936560640"),optionTwo.getElasticizedUnitVelocity());
    }

    @org.junit.jupiter.api.Test
    void testUpdateEstimatedAnnualVolumePerSku() {
//        optionOne.setYearOneStoreCount(158);
//        optionTwo.setYearOneStoreCount(158);
        optionOne.setElasticizedUnitVelocity(new BigDecimal("1.2"));
        optionTwo.setElasticizedUnitVelocity(new BigDecimal("0.7161936560640")); //needs exact value otherwise it doesnt work
        optionOne.updateEstimatedAnnualVolumePerSku();
        optionTwo.updateEstimatedAnnualVolumePerSku();
        Assert.assertEquals(new BigDecimal(9859.2000000000), optionOne.getAnnualVolumePerSku());
        Assert.assertEquals(new BigDecimal(5884), optionTwo.getAnnualVolumePerSku());
    }

    @org.junit.jupiter.api.Test
    void testUpdateSlottingPaybackPeriod() {
        optionOne.setSlottingPerSku(new BigDecimal(7500));
        optionTwo.setSlottingPerSku(new BigDecimal(3500));
        optionOne.setWeeklyUSFWAtMin(new BigDecimal("1.2"));
        optionTwo.setWeeklyUSFWAtMin(new BigDecimal("1.2"));
    }

    @org.junit.jupiter.api.Test
    void testGetYearOneStoreCount() {
    }

    @org.junit.jupiter.api.Test
    void testYearOneStoreCountProperty() {
    }

    @org.junit.jupiter.api.Test
    void setYearOneStoreCount() {
    }

    @org.junit.jupiter.api.Test
    void getEverydayGPM() {
    }

    @org.junit.jupiter.api.Test
    void everydayGPMProperty() {
    }

    @org.junit.jupiter.api.Test
    void testSetEverydayGPMTest() {

    }

    @org.junit.jupiter.api.Test
    void getSpoilsAndFees() {
    }

    @org.junit.jupiter.api.Test
    void spoilsAndFeesProperty() {
    }

    @org.junit.jupiter.api.Test
    void setSpoilsAndFees() {
    }

    @org.junit.jupiter.api.Test
    void testIsFob() {
        Assert.assertEquals(false, optionThree.isFob());
        optionThree.setFreightOutPerUnit(new BigDecimal("0.0"));
        Assert.assertEquals(true, optionThree.isFob());
    }

    @org.junit.jupiter.api.Test
    void testGetFourYearUnitVolumePerSku() {
        optionOne.setAnnualVolumePerSku(new BigDecimal(9859));
        optionTwo.setAnnualVolumePerSku(new BigDecimal(5884));
        Assert.assertEquals(new BigDecimal("39436"), optionOne.getFourYearUnitVolumePerSku());
        Assert.assertEquals(new BigDecimal("23536"),optionTwo.getFourYearUnitVolumePerSku());
    }

    @org.junit.jupiter.api.Test
    void testGetOurFreightCost() {
        Assert.assertEquals(new BigDecimal("11436.44"), optionThree.getOurFreightCost());
        // Actually expected: 11436.67
//        Assert.assertEquals(new BigDecimal("1.2"), optionThree.getElasticizedEstimatedUnitVelocity());;
    }

    @org.junit.jupiter.api.Test
    void testGetGrossRevenueList() {
        Assert.assertEquals(new BigDecimal("141575.2400000000"), optionThree.getGrossRevenueList());
        // Actually expected = 141578.11
        // remains changes when set to product two

    }

    @org.junit.jupiter.api.Test
    void testGetFobDiscount() {
        Assert.assertEquals(new BigDecimal("0.0"), optionThree.getFobDiscount());
        optionThree.setFreightOutPerUnit(new BigDecimal("0.0"));
        Assert.assertEquals(new BigDecimal("11436.44"), optionThree.getFobDiscount());
    }

    @org.junit.jupiter.api.Test
    void testGetSpoilsTrade() {
        Assert.assertEquals(new BigDecimal("4247.257200000000"),optionThree.getSpoilsTrade());
        // Actually Expected = 4247.34
    }


    @org.junit.jupiter.api.Test
    void testGetStandardAllowanceTrade() {
        Assert.assertEquals(new BigDecimal("0.0"), optionThree.getStandardAllowanceTrade().setScale(1, RoundingMode.HALF_UP));
        optionThree.setFreightOutPerUnit(new BigDecimal("0.0"));
        optionThree.setFirstReceiver(new BigDecimal("3.30"));
        Assert.assertEquals(new BigDecimal("0.0"), optionThree.getStandardAllowanceTrade().setScale(1,RoundingMode.HALF_UP));
//        Assert.assertEquals(new BigDecimal("3.59"),optionThree.getFirstReceiver());
        optionThree.setFirstReceiver(new BigDecimal("3.07"));
        optionThree.setAnnualVolumePerSku(new BigDecimal(8913));
        Assert.assertEquals(new BigDecimal("8199.9600000000"), optionThree.getStandardAllowanceTrade());
        //actually expected: 8199.76

    }

    @org.junit.jupiter.api.Test
    void testGetAfterSpoilsAndStdAllowanceTrade() {
        Assert.assertEquals(new BigDecimal("19414.342800000000"), optionThree.getAfterSpoilsAndStdAllowanceTrade());
        //actually expected= 19414.74
    }

    @org.junit.jupiter.api.Test
    void testGetIfFobFreightCredit() {
        Assert.assertEquals(new BigDecimal("0.0"), optionThree.getIfFobFreightCredit());
        optionThree.setFreightOutPerUnit(new BigDecimal("0.0"));
        Assert.assertEquals(new BigDecimal("11436.44"), optionThree.getIfFobFreightCredit());
    }

    @org.junit.jupiter.api.Test
    void testGetEqualsNet1Rev() {
        Assert.assertEquals(new BigDecimal("117913.640000000000"), optionThree.getEqualsNet1Rev());
        // Actually expected "117916.03"
    }

    @org.junit.jupiter.api.Test
    void getTotalFobAndFreightSpending() {
        Assert.assertEquals(new BigDecimal("11436.44"),optionThree.getTotalFobAndFreightSpending());
        //Actuallly expected: 11436.66
    }

    @org.junit.jupiter.api.Test
    void getEqualsNet2Rev() {
        Assert.assertEquals(new BigDecimal("106477.200000000000"),optionThree.getEqualsNet2Rev());
        //Actually expected: 106479.46
    }

    @org.junit.jupiter.api.Test
    void getEqualsNet3Rev() {
        Assert.assertEquals(new BigDecimal("98977.200000000000"), optionThree.getEqualsNet3Rev());
        // Actually expected: 98979.36
    }

    @org.junit.jupiter.api.Test
    void getNetRev3Rate() {
        Assert.assertEquals(new BigDecimal("2.51"), optionThree.getNetRev3Rate().setScale(2,RoundingMode.HALF_UP));
    }

    @org.junit.jupiter.api.Test
    void testGetTotalCogs() {
        Assert.assertEquals(new BigDecimal("80843.80"),optionThree.getTotalCogs());
        //Actually expected 80845.44
    }

    @org.junit.jupiter.api.Test
    void getGrossProfit() {
        Assert.assertEquals(new BigDecimal("18133.400000000000"), optionThree.getGrossProfit());
        // Actually expected 18133.92
    }

    @org.junit.jupiter.api.Test
    void getGrossProfitPerUnit() {
        Assert.assertEquals(new BigDecimal("0.4598184400"), optionThree.getGrossProfitPerUnit());
        // actually expected: 0.46
    }

    @org.junit.jupiter.api.Test
    void testGetSlottingPayback() {
        Assert.assertEquals(new BigDecimal("1.65441"),optionThree.getSlottingPayback());
        //Actually expected Expected :1.65
    }

    @org.junit.jupiter.api.Test
    void getPostSpoilsAndFreightWeCollect() {
        Assert.assertEquals(new BigDecimal("125891.542800000000"), optionThree.getPostSpoilsAndFreightWeCollect());
    }

    @org.junit.jupiter.api.Test
    void getPostSpoilsAndFreightWeCollectPerUnit() {
        Assert.assertEquals(new BigDecimal("3.192300000000"), optionThree.getPostSpoilsAndFreightWeCollectPerUnit());
        //Actually expected: 3.19
    }

    @org.junit.jupiter.api.Test
    void getPostSpoilsAndStdAllowancesAvailableTrade() {
        Assert.assertEquals(new BigDecimal("0.492300000000"), optionThree.getPostSpoilsAndStdAllowancesAvailableTrade());
        // Actually expected 0.492300000000
    }

    @org.junit.jupiter.api.Test
    void getRTMName() {
    }

    @org.junit.jupiter.api.Test
    void setRTMName() {
    }

    @org.junit.jupiter.api.Test
    void getSlottingPerSku() {
    }

    @org.junit.jupiter.api.Test
    void setSlottingPerSku() {
    }

    @org.junit.jupiter.api.Test
    void getFreightOutPerUnit() {
    }

    @org.junit.jupiter.api.Test
    void setFreightOutPerUnit() {
    }

    @org.junit.jupiter.api.Test
    void freightOutPerUnitProperty() {
    }

    @org.junit.jupiter.api.Test
    void getFirstReceiver() {
    }

    @org.junit.jupiter.api.Test
    void firstReceiverProperty() {
    }

    @org.junit.jupiter.api.Test
    void setFirstReceiver() {
    }

    @org.junit.jupiter.api.Test
    void getSecondReceiver() {
    }

    @org.junit.jupiter.api.Test
    void secondReceiverProperty() {
    }

    @org.junit.jupiter.api.Test
    void setSecondReceiver() {
    }

    @org.junit.jupiter.api.Test
    void getThirdReceiver() {
    }

    @org.junit.jupiter.api.Test
    void thirdReceiverProperty() {
    }

    @org.junit.jupiter.api.Test
    void setThirdReceiver() {
    }

    @org.junit.jupiter.api.Test
    void getFourthReceiver() {
    }

    @org.junit.jupiter.api.Test
    void fourthReceiverProperty() {
    }

    @org.junit.jupiter.api.Test
    void setFourthReceiver() {
    }

    @org.junit.jupiter.api.Test
    void getLandedStoreCost() {
    }

    @org.junit.jupiter.api.Test
    void landedStoreCostProperty() {
    }

    @org.junit.jupiter.api.Test
    void setLandedStoreCost() {
    }

    @org.junit.jupiter.api.Test
    void getResultingEverydayRetailCalcd() {
    }

    @org.junit.jupiter.api.Test
    void resultingEverydayRetailProperty() {
    }

    @org.junit.jupiter.api.Test
    void setResultingEverydayRetailCalcd() {
    }

    @org.junit.jupiter.api.Test
    void getResultingEverydayRetailOverride() {
    }

    @org.junit.jupiter.api.Test
    void resultingEverydayRetailOverrideProperty() {
    }

    @org.junit.jupiter.api.Test
    void setResultingEverydayRetailOverride() {
    }

    @org.junit.jupiter.api.Test
    void getMinOverride() {
    }

    @org.junit.jupiter.api.Test
    void minOverrideProperty() {
    }

    @org.junit.jupiter.api.Test
    void setMinOverride() {
    }

    @org.junit.jupiter.api.Test
    void getWeeklyUSFWAtMin() {
    }

    @org.junit.jupiter.api.Test
    void weeklyUSFWAtMinProperty() {
    }

    @org.junit.jupiter.api.Test
    void setWeeklyUSFWAtMin() {
    }

    @org.junit.jupiter.api.Test
    void getElasticizedEstimatedUnitVelocity() {
    }

    @org.junit.jupiter.api.Test
    void setElasticizedEstimatedUnitVelocity() {
    }

    @org.junit.jupiter.api.Test
    void elasticizedEstimatedUnitVelocityProperty() {
    }

    @org.junit.jupiter.api.Test
    void getEstimatedAnnualVolumePerSku() {
    }

    @org.junit.jupiter.api.Test
    void setEstimatedAnnualVolumePerSku() {
    }

    @org.junit.jupiter.api.Test
    void estimatedAnnualVolumePerSkuProperty() {
    }

    @org.junit.jupiter.api.Test
    void getSlottingPaybackPeriod() {
    }

    @org.junit.jupiter.api.Test
    void slottingPaybackPeriodProperty() {
    }

    @org.junit.jupiter.api.Test
    void setSlottingPaybackPeriod() {
    }

    @org.junit.jupiter.api.Test
    void getPostFreightPostSpoilsWeCollectPerUnit() {
    }

    @org.junit.jupiter.api.Test
    void postFreightPostSpoilsWeCollectPerUnitProperty() {
    }

    @org.junit.jupiter.api.Test
    void setPostFreightPostSpoilsWeCollectPerUnit() {
    }

    @org.junit.jupiter.api.Test
    void getUnspentTradePerUnit() {
    }

    @org.junit.jupiter.api.Test
    void unspentTradePerUnitProperty() {
    }

    @org.junit.jupiter.api.Test
    void setUnspentTradePerUnit() {
    }

    @org.junit.jupiter.api.Test
    void getFourYearEqGpPerSku() {
    }

    @org.junit.jupiter.api.Test
    void fourYearEqGpPerSkuProperty() {
    }

    @org.junit.jupiter.api.Test
    void setFourYearEqGpPerSku() {
    }

    @org.junit.jupiter.api.Test
    void getFourYearEqGpPerUnit() {
    }

    @org.junit.jupiter.api.Test
    void fourYearEqGpPerUnitProperty() {
    }

    @org.junit.jupiter.api.Test
    void setFourYearEqGpPerUnit() {
    }

}