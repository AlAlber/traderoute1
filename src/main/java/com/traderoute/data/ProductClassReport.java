package com.traderoute.data;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.testfx.api.FxRobot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductClassReport {
    private SimpleStringProperty retailerName;
    private SimpleBooleanProperty committed;
    private SimpleIntegerProperty pointsOfDistribution, storeCount;
    private SimpleObjectProperty<BigDecimal> totalVolumetrics, everydayVolumetrics, promotedVolumetrics, grossRevenue,
            tradeRevenue, net1Revenue, net1Pod, net1Rate, averageSkus, averageSellingPrice, weeklyVelocityUfsw;
    private SimpleObjectProperty<Retailer> retailer;
    private SimpleObjectProperty<RetailerProduct> retailerProduct;
    private SimpleObjectProperty<ObservableList<Boolean>> selectedYears;
    private SimpleBooleanProperty onlyCommitted;
    private SimpleStringProperty selectedRtm;

    public ProductClassReport(Retailer retailer, RetailerProduct retailerProduct, ObservableList<Boolean> years,
            Boolean onlyCommitted) {
        this.retailer = new SimpleObjectProperty<>(retailer);
        this.retailerProduct = new SimpleObjectProperty<>(retailerProduct);
        this.selectedYears = new SimpleObjectProperty<>(years);
        this.retailerName = new SimpleStringProperty(getRetailer().getRetailerName());
        this.onlyCommitted = new SimpleBooleanProperty(onlyCommitted);
        // if (retailerProduct.getPromoPlans().get(selectedYear).getCommitted()) {
        // this.committed = new SimpleStringProperty("Yes");
        // } else {
        // this.committed = new SimpleStringProperty("No");
        // }
        // ASK JOHN ABOUT THIS ONE
        for (PromoPlan promoPlan : getRetailerProduct().getPromoPlans()) {
            this.committed = new SimpleBooleanProperty(promoPlan.getCommitted());
        }

        this.storeCount = new SimpleIntegerProperty(getRetailer().getYearOneStoreCount());
        BigDecimal totalVolumetrics1 = new BigDecimal("0.0");
        BigDecimal everydayVolumetrics1 = new BigDecimal("0.0");
        BigDecimal promotedVolumetrics1 = new BigDecimal("0.0");
        BigDecimal grossRevenue1 = new BigDecimal("0.0");
        BigDecimal tradeRevenue1 = new BigDecimal("0.0");
        BigDecimal net1Revenue1 = new BigDecimal("0.0");
        BigDecimal averageSellingPrice1 = new BigDecimal("0.0"); // EDIT THIS
        BigDecimal weeklyVelocityUfsw1 = new BigDecimal("0.0");

        for (PromoPlan promoPlan : getCurrentPromoPlans()) {
            totalVolumetrics1 = totalVolumetrics1.add(promoPlan.getSumValue(2, true));
            everydayVolumetrics1 = everydayVolumetrics1.add(promoPlan.getTwelveMonthTotal(5));
            promotedVolumetrics1 = promotedVolumetrics1.add(promoPlan.getTwelveMonthTotal(6));
            grossRevenue1 = grossRevenue1.add(promoPlan.getSumValue(0, true));
            tradeRevenue1 = tradeRevenue1
                    .add(promoPlan.getTwelveMonthTotal(7).subtract(promoPlan.getTwelveMonthTotal(8)));
            net1Revenue1 = net1Revenue1.add(promoPlan.getSumValue(1, true));

            averageSellingPrice1 = averageSellingPrice1.add(promoPlan.getSumValue(2, false));
            weeklyVelocityUfsw1 = weeklyVelocityUfsw1.add(promoPlan.getWeeklyPromoUfsw());
        }
        if (getCurrentPromoPlans().size() > 0) {
            averageSellingPrice1 = averageSellingPrice1.divide(new BigDecimal(getCurrentPromoPlans().size()), 2,
                    RoundingMode.HALF_UP);
        }
        this.totalVolumetrics = new SimpleObjectProperty(totalVolumetrics1);
        this.everydayVolumetrics = new SimpleObjectProperty(everydayVolumetrics1);
        this.promotedVolumetrics = new SimpleObjectProperty(promotedVolumetrics1);
        this.grossRevenue = new SimpleObjectProperty(grossRevenue1);
        this.tradeRevenue = new SimpleObjectProperty(tradeRevenue1);
        this.net1Revenue = new SimpleObjectProperty(net1Revenue1);

        this.averageSellingPrice = new SimpleObjectProperty(averageSellingPrice1);
        this.weeklyVelocityUfsw = new SimpleObjectProperty(weeklyVelocityUfsw1);
        this.pointsOfDistribution = new SimpleIntegerProperty(calculatePointsOfDistribution());
        this.net1Pod = new SimpleObjectProperty(calculateNet1Pod());

        this.net1Rate = new SimpleObjectProperty(calculateNet1Rate());
        this.averageSkus = new SimpleObjectProperty(calculateCurrentSkus());
        this.selectedRtm = new SimpleStringProperty(
                getRetailerProduct().getPromoPlans().get(0).getSelectedRtm().getRtmName());

    }

    public ProductClassReport(String retailerName, Boolean committed, Integer storeCount, BigDecimal totalVolumetrics,
            BigDecimal everydayVolumetrics, BigDecimal promotedVolumetrics, BigDecimal grossRevenue,
            BigDecimal tradeRevenue, BigDecimal net1Revenue, BigDecimal averageSellingPrice,
            BigDecimal weeklyVelocityUfsw, Integer pointsOfDistribution, BigDecimal net1Pod, BigDecimal net1Rate,
            BigDecimal averageSkus, String selectedRtm, ObservableList<Integer> skuTotals) {
        this.retailerName = new SimpleStringProperty(retailerName);
        this.committed = new SimpleBooleanProperty(committed);
        this.storeCount = new SimpleIntegerProperty(storeCount);
        this.totalVolumetrics = new SimpleObjectProperty(totalVolumetrics);
        this.everydayVolumetrics = new SimpleObjectProperty(everydayVolumetrics);
        this.promotedVolumetrics = new SimpleObjectProperty(promotedVolumetrics);
        this.grossRevenue = new SimpleObjectProperty(grossRevenue);
        this.tradeRevenue = new SimpleObjectProperty(tradeRevenue);
        this.net1Revenue = new SimpleObjectProperty(net1Revenue);

        this.averageSellingPrice = new SimpleObjectProperty(averageSellingPrice);
        this.weeklyVelocityUfsw = new SimpleObjectProperty(weeklyVelocityUfsw);
        this.pointsOfDistribution = new SimpleIntegerProperty(pointsOfDistribution);
        this.net1Pod = new SimpleObjectProperty(net1Pod);

        this.net1Rate = new SimpleObjectProperty(net1Rate);
        this.averageSkus = new SimpleObjectProperty(averageSkus);
        this.selectedRtm = new SimpleStringProperty(selectedRtm);
    };

    public void updateAll() {
        BigDecimal totalVolumetrics1 = new BigDecimal("0.0");
        BigDecimal everydayVolumetrics1 = new BigDecimal("0.0");
        BigDecimal promotedVolumetrics1 = new BigDecimal("0.0");
        BigDecimal grossRevenue1 = new BigDecimal("0.0");
        BigDecimal tradeRevenue1 = new BigDecimal("0.0");
        BigDecimal net1Revenue1 = new BigDecimal("0.0");
        BigDecimal averageSellingPrice1 = new BigDecimal("0.0"); // EDIT THIS
        BigDecimal weeklyVelocityUfsw1 = new BigDecimal("0.0");

        for (PromoPlan promoPlan : getCurrentPromoPlans()) {
            totalVolumetrics1 = totalVolumetrics1.add(promoPlan.getSumValue(2, true));
            everydayVolumetrics1 = everydayVolumetrics1.add(promoPlan.getTwelveMonthTotal(5));
            promotedVolumetrics1 = promotedVolumetrics1.add(promoPlan.getTwelveMonthTotal(6));
            grossRevenue1 = grossRevenue1.add(promoPlan.getSumValue(0, true));
            tradeRevenue1 = tradeRevenue1
                    .add(promoPlan.getTwelveMonthTotal(7).subtract(promoPlan.getTwelveMonthTotal(8)));
            net1Revenue1 = net1Revenue1.add(promoPlan.getSumValue(1, true));

            averageSellingPrice1 = averageSellingPrice1.add(promoPlan.getSumValue(2, false));
            weeklyVelocityUfsw1 = weeklyVelocityUfsw1.add(promoPlan.getWeeklyPromoUfsw());
        }
        if (getCurrentPromoPlans().size() > 0) {
            averageSellingPrice1 = averageSellingPrice1.divide(new BigDecimal(getCurrentPromoPlans().size()), 2,
                    RoundingMode.HALF_UP);
        }
        setTotalVolumetrics(totalVolumetrics1);
        setEverydayVolumetrics(everydayVolumetrics1);
        setPromotedVolumetrics(promotedVolumetrics1);
        setGrossRevenue(grossRevenue1);
        setTradeRevenue(tradeRevenue1);
        setNet1Revenue(net1Revenue1);

        setAverageSellingPrice(averageSellingPrice1);
        setWeeklyVelocityUfsw(weeklyVelocityUfsw1);
        setPointsOfDistribution(calculatePointsOfDistribution());
        setNet1Pod(calculateNet1Pod());

        setNet1Rate(calculateNet1Rate());
        setAverageSkus(calculateCurrentSkus());

    }

    public BigDecimal calculateNet1Rate() {
        BigDecimal net1Rate = new BigDecimal("0.0");
        for (PromoPlan promoPlan : getCurrentPromoPlans()) {
            if (promoPlan.getSumValue(2, true).compareTo(new BigDecimal("0.0")) > 0
                    && (promoPlan.getSumValue(1, true).divide(promoPlan.getSumValue(2, true), 2, RoundingMode.HALF_UP))
                            .compareTo(new BigDecimal("0.0")) > 0) {
                net1Rate = net1Rate.add(
                        promoPlan.getSumValue(1, true).divide(promoPlan.getSumValue(2, true), 2, RoundingMode.HALF_UP));
            }
        }
        return net1Rate;
    }

    public BigDecimal calculateNet1Pod() {
        if (calculatePointsOfDistribution() > 0) {
            return getNet1Revenue().divide(new BigDecimal(calculatePointsOfDistribution()), 2, RoundingMode.HALF_UP);
        }
        return new BigDecimal("0.0");
    }

    public BigDecimal calculateCurrentSkus() {
        BigDecimal count = new BigDecimal("0.0");
        if (getRetailerProduct() != null) {
            for (Sku sku : retailerProduct.get().getSkus()) {
                if (sku.getStatus().toLowerCase(Locale.ROOT).equals("current")) {
                    count = count.add(new BigDecimal("1.0"));
                }
            }
            return count;
        }
        return new BigDecimal("0.0");
    }

    public int calculatePointsOfDistribution() {
        int count = 0;
        if (getRetailerProduct() != null) {
            for (Sku sku : retailerProduct.get().getSkus()) {
                if (sku.getStatus().toLowerCase(Locale.ROOT).equals("current")) {
                    count++;
                }
            }
            return count * retailer.get().getYearOneStoreCount();
        }
        return 0;
    }

    public ObservableList<PromoPlan> getCurrentPromoPlans() {
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        for (int i = 0; i < selectedYears.get().size(); i++) {
            Boolean selectedYear = selectedYears.get().get(i);
            if (getRetailerProduct() != null) {
                if (isOnlyCommitted()) {
                    if (getRetailerProduct().getPromoPlans().get(i).getCommitted()) {
                        if (selectedYear) {
                            getRetailerProduct().getPromoPlans().get(i).setRetailer(retailer.get());
                            promoPlans.add(getRetailerProduct().getPromoPlans().get(i));
                        }
                    }
                } else {
                    if (selectedYear) {
                        getRetailerProduct().getPromoPlans().get(i).setRetailer(retailer.get());
                        promoPlans.add(getRetailerProduct().getPromoPlans().get(i));
                    }
                }
            } else {
                return FXCollections.observableArrayList();
            }
        }
        return promoPlans;
    }
    // Function used for testing
    public void setColumn (BiConsumer<ProductClassReport, String> setColumn,ProductClassReport report, String value){
        setColumn.accept(report, value);
    }

    public String getSelectedRtm() {
        return selectedRtm.get();
    }

    public SimpleStringProperty selectedRtmProperty() {
        return selectedRtm;
    }

    public void setSelectedRtm(String selectedRtm) {
        this.selectedRtm.set(selectedRtm);
    }

    public boolean isOnlyCommitted() {
        return onlyCommitted.get();
    }

    public SimpleBooleanProperty onlyCommittedProperty() {
        return onlyCommitted;
    }

    public void setOnlyCommitted(boolean onlyCommitted) {
        this.onlyCommitted.set(onlyCommitted);
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public SimpleObjectProperty<Retailer> retailerProperty() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
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

    public ObservableList<Boolean> getSelectedYears() {
        return selectedYears.get();
    }

    public SimpleObjectProperty<ObservableList<Boolean>> selectedYearsProperty() {
        return selectedYears;
    }

    public void setSelectedYears(ObservableList<Boolean> selectedYears) {
        this.selectedYears.set(selectedYears);
    }

    public Boolean getYear(int index) {
        return getSelectedYears().get(index);
    }

    public String getRetailerName() {
        return retailerName.get();
    }

    public SimpleStringProperty retailerNameProperty() {
        return retailerName;
    }

    public boolean isCommitted() {
        return committed.get();
    }

    public SimpleBooleanProperty committedProperty() {
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed.set(committed);
    }

    public Integer getStoreCount() {
        return storeCount.get();
    }

    public SimpleIntegerProperty storeCountProperty() {
        return storeCount;
    }

    public void setStoreCount (int storeCount){ this.storeCount.set(storeCount);}

    public BigDecimal getTotalVolumetrics() {
        return totalVolumetrics.get();
    }

    public SimpleObjectProperty<BigDecimal> totalVolumetricsProperty() {
        return totalVolumetrics;
    }

    public void setTotalVolumetrics(BigDecimal totalVolumetrics) {
        this.totalVolumetrics.set(totalVolumetrics);
    }

    public BigDecimal getEverydayVolumetrics() {
        return everydayVolumetrics.get();
    }

    public SimpleObjectProperty<BigDecimal> everydayVolumetricsProperty() {
        return everydayVolumetrics;
    }

    public void setEverydayVolumetrics(BigDecimal everydayVolumetrics) {
        this.everydayVolumetrics.set(everydayVolumetrics);
    }

    public BigDecimal getPromotedVolumetrics() {
        return promotedVolumetrics.get();
    }

    public SimpleObjectProperty<BigDecimal> promotedVolumetricsProperty() {
        return promotedVolumetrics;
    }

    public void setPromotedVolumetrics(BigDecimal promotedVolumetrics) {
        this.promotedVolumetrics.set(promotedVolumetrics);
    }

    public BigDecimal getGrossRevenue() {
        return grossRevenue.get();
    }

    public SimpleObjectProperty<BigDecimal> grossRevenueProperty() {
        return grossRevenue;
    }

    public void setGrossRevenue(BigDecimal grossRevenue) {
        this.grossRevenue.set(grossRevenue);
    }

    public BigDecimal getTradeRevenue() {
        return tradeRevenue.get();
    }

    public SimpleObjectProperty<BigDecimal> tradeRevenueProperty() {
        return tradeRevenue;
    }

    public void setTradeRevenue(BigDecimal tradeRevenue) {
        this.tradeRevenue.set(tradeRevenue);
    }

    public BigDecimal getNet1Revenue() {
        return net1Revenue.get();
    }

    public SimpleObjectProperty<BigDecimal> net1RevenueProperty() {
        return net1Revenue;
    }

    public void setNet1Revenue(BigDecimal net1Revenue) {
        this.net1Revenue.set(net1Revenue);
    }

    public BigDecimal getNet1Pod() {
        return net1Pod.get();
    }

    public SimpleObjectProperty<BigDecimal> net1PodProperty() {
        return net1Pod;
    }

    public void setNet1Pod(BigDecimal net1Pod) {
        this.net1Pod.set(net1Pod);
    }

    public BigDecimal getNet1Rate() {
        return net1Rate.get();
    }

    public SimpleObjectProperty<BigDecimal> net1RateProperty() {
        return net1Rate;
    }

    public void setNet1Rate(BigDecimal net1Rate) {
        this.net1Rate.set(net1Rate);
    }

    public int getPointsOfDistribution() {
        return pointsOfDistribution.get();
    }

    public SimpleIntegerProperty pointsOfDistributionProperty() {
        return pointsOfDistribution;
    }

    public void setPointsOfDistribution(int pointsOfDistribution) {
        this.pointsOfDistribution.set(pointsOfDistribution);
    }

    public BigDecimal getAverageSkus() {
        return averageSkus.get();
    }

    public SimpleObjectProperty<BigDecimal> averageSkusProperty() {
        return averageSkus;
    }

    public void setAverageSkus(BigDecimal averageSkus) {
        this.averageSkus.set(averageSkus);
    }

    public BigDecimal getAverageSellingPrice() {
        return averageSellingPrice.get();
    }

    public SimpleObjectProperty<BigDecimal> averageSellingPriceProperty() {
        return averageSellingPrice;
    }

    public void setAverageSellingPrice(BigDecimal averageSellingPrice) {
        this.averageSellingPrice.set(averageSellingPrice);
    }

    public BigDecimal getWeeklyVelocityUfsw() {
        return weeklyVelocityUfsw.get();
    }

    public SimpleObjectProperty<BigDecimal> weeklyVelocityUfswProperty() {
        return weeklyVelocityUfsw;
    }

    public void setWeeklyVelocityUfsw(BigDecimal weeklyVelocityUfsw) {
        this.weeklyVelocityUfsw.set(weeklyVelocityUfsw);
    }
    // public Object getTotalVolumetrics() {
    // return getCurrentPromoPlan().getSumValue(2, false);
    // }
    //
    // public SimpleObjectProperty totalVolumetricsProperty() {
    // return new SimpleObjectProperty(getCurrentPromoPlan().getSumValue(2, false));
    // }
    //
    // public void setTotalVolumetrics(Object totalVolumetrics) {
    // this.totalVolumetrics.set(totalVolumetrics);
    // }
    //
    // public Object getEverydayVolumetrics() {
    // return everydayVolumetrics.get();
    // }
    //
    // public SimpleObjectProperty everydayVolumetricsProperty() {
    // return everydayVolumetrics;
    // }
    //
    // public void setEverydayVolumetrics(Object everydayVolumetrics) {
    // this.everydayVolumetrics.set(everydayVolumetrics);
    // }
    //
    // public Object getPromotedVolumetrics() {
    // return promotedVolumetrics.get();
    // }
    //
    // public SimpleObjectProperty promotedVolumetricsProperty() {
    // return promotedVolumetrics;
    // }
    //
    // public void setPromotedVolumetrics(Object promotedVolumetrics) {
    // this.promotedVolumetrics.set(promotedVolumetrics);
    // }
    //
    // public Object getGrossRevenue() {
    // return grossRevenue.get();
    // }
    //
    // public SimpleObjectProperty grossRevenueProperty() {
    // return grossRevenue;
    // }
    //
    // public void setGrossRevenue(Object grossRevenue) {
    // this.grossRevenue.set(grossRevenue);
    // }
    //
    // public Object getTradeRevenue() {
    // return tradeRevenue.get();
    // }
    //
    // public SimpleObjectProperty tradeRevenueProperty() {
    // return tradeRevenue;
    // }
    //
    // public void setTradeRevenue(Object tradeRevenue) {
    // this.tradeRevenue.set(tradeRevenue);
    // }
    //
    // public Object getNet1Revenue() {
    // return net1Revenue.get();
    // }
    //
    // public SimpleObjectProperty net1RevenueProperty() {
    // return net1Revenue;
    // }
    //
    // public void setNet1Revenue(Object net1Revenue) {
    // this.net1Revenue.set(net1Revenue);
    // }
    //
    // public Object getNet1Pod() {
    // return net1Pod.get();
    // }
    //
    // public SimpleObjectProperty net1PodProperty() {
    // return net1Pod;
    // }
    //
    // public void setNet1Pod(Object net1Pod) {
    // this.net1Pod.set(net1Pod);
    // }
    //
    // public Object getNet1Rate() {
    // return net1Rate.get();
    // }
    //
    // public SimpleObjectProperty net1RateProperty() {
    // return net1Rate;
    // }
    //
    // public void setNet1Rate(Object net1Rate) {
    // this.net1Rate.set(net1Rate);
    // }
    //
    // public Object getPointsOfDistribution() {
    // return pointsOfDistribution.get();
    // }
    //
    // public SimpleObjectProperty pointsOfDistributionProperty() {
    // return pointsOfDistribution;
    // }
    //
    // public void setPointsOfDistribution(Object pointsOfDistribution) {
    // this.pointsOfDistribution.set(pointsOfDistribution);
    // }
    //
    // public Object getAverageSkus() {
    // return averageSkus.get();
    // }
    //
    // public SimpleObjectProperty averageSkusProperty() {
    // return averageSkus;
    // }
    //
    // public void setAverageSkus(Object averageSkus) {
    // this.averageSkus.set(averageSkus);
    // }
    //
    // public Object getAverageSellingPrice() {
    // return averageSellingPrice.get();
    // }
    //
    // public SimpleObjectProperty averageSellingPriceProperty() {
    // return averageSellingPrice;
    // }
    //
    // public void setAverageSellingPrice(Object averageSellingPrice) {
    // this.averageSellingPrice.set(averageSellingPrice);
    // }
    //
    // public Object getWeeklyVelocityUfsw() {
    // return weeklyVelocityUfsw.get();
    // }
    //
    // public SimpleObjectProperty weeklyVelocityUfswProperty() {
    // return weeklyVelocityUfsw;
    // }
    //
    // public void setWeeklyVelocityUfsw(Object weeklyVelocityUfsw) {
    // this.weeklyVelocityUfsw.set(weeklyVelocityUfsw);
    // }
}
