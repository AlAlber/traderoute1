package com.traderoute.tables;

import com.traderoute.cells.*;
import com.traderoute.data.ProductClassReport;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class ProductClassReportTable extends CustomTable{
    private TableColumn<ProductClassReport, String> retailerNameCol;
    private TableColumn<ProductClassReport, Boolean> committedCol;
    private TableColumn<ProductClassReport, Integer> storeCountCol;
    private TableColumn<ProductClassReport, BigDecimal> totalVolumetricsCol;
    private TableColumn<ProductClassReport, BigDecimal> everydayVolumetricsCol;
    private TableColumn<ProductClassReport, BigDecimal> promotedVolumetricsCol;
    private TableColumn<ProductClassReport, BigDecimal> grossRevenueCol;
    private TableColumn<ProductClassReport, BigDecimal> tradeRevenueCol;
    private TableColumn<ProductClassReport, BigDecimal> net1RevenueCol;
    private TableColumn<ProductClassReport, BigDecimal> net1PodCol;
    private TableColumn<ProductClassReport, BigDecimal> net1RateCol;
    private TableColumn<ProductClassReport, BigDecimal> pointsOfDistributionCol;
    private TableColumn<ProductClassReport, BigDecimal> averageSkusCol;
    private TableColumn<ProductClassReport, BigDecimal> averageSellingPriceCol;
    private TableColumn<ProductClassReport, BigDecimal> weeklyVelocityUfswCol;
    private TableColumn<ProductClassReport, String> selectedRtmCol;

    public ProductClassReportTable (){
        getColumns().addAll(retailerNameCol, committedCol, storeCountCol,
                totalVolumetricsCol, everydayVolumetricsCol, promotedVolumetricsCol,
                grossRevenueCol, tradeRevenueCol, net1RevenueCol, net1PodCol,
                net1RateCol, pointsOfDistributionCol, averageSkusCol, averageSellingPriceCol,
                weeklyVelocityUfswCol, selectedRtmCol);
        setEditable(false);
    }

    @Override
    public void setCellFactories() {
        CellSpecs intNoPreSpecs = StdSpecs.INTPOS5X.getSpecs();
        CellSpecs decNoPreSpecs = StdSpecs.DECPOS5X.getSpecs();
        CellSpecs decSpecs = StdSpecs.DECPOS6$.getSpecs();
        // FIX THIS IT SHOULD ALLOW VALUES LARGER THAN 10000

        retailerNameCol.setCellFactory(tc -> new CustomTextCell<>());
        committedCol.setCellFactory(tc -> new CustomTextCell<>());
        storeCountCol.setCellFactory(tc -> new IntEditCell1<>(intNoPreSpecs));
        totalVolumetricsCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        everydayVolumetricsCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        promotedVolumetricsCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        grossRevenueCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs));
        tradeRevenueCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs));
        net1RevenueCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs));
        net1PodCol.setCellFactory(tc -> new BigDecimalEditCell1(StdSpecs.INTPOS6$.getSpecs()));
        net1RateCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs));
        pointsOfDistributionCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        averageSkusCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        averageSellingPriceCol.setCellFactory(tc -> new BigDecimalEditCell1(decSpecs));
        weeklyVelocityUfswCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        selectedRtmCol.setCellFactory(tc -> new CustomTextCell<>());
    }

    @Override
    public void setCellValueFactories() {
        retailerNameCol.setCellValueFactory(cellData -> cellData.getValue().retailerNameProperty());
        committedCol.setCellValueFactory(cellData -> cellData.getValue().committedProperty());
        storeCountCol.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().storeCountProperty());
        totalVolumetricsCol.setCellValueFactory(cellData -> cellData.getValue().totalVolumetricsProperty());
        everydayVolumetricsCol.setCellValueFactory(cellData -> cellData.getValue().everydayVolumetricsProperty());
        promotedVolumetricsCol.setCellValueFactory(cellData -> cellData.getValue().promotedVolumetricsProperty());
        grossRevenueCol.setCellValueFactory(cellData -> cellData.getValue().grossRevenueProperty());
        tradeRevenueCol.setCellValueFactory(cellData -> cellData.getValue().tradeRevenueProperty());
        net1RevenueCol.setCellValueFactory(cellData -> cellData.getValue().net1RevenueProperty());
        net1PodCol.setCellValueFactory(cellData -> cellData.getValue().net1PodProperty());
        net1RateCol.setCellValueFactory(cellData -> cellData.getValue().net1RateProperty());
        pointsOfDistributionCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().pointsOfDistributionProperty());
        averageSkusCol.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().averageSkusProperty());
        averageSellingPriceCol.setCellValueFactory(cellData -> cellData.getValue().averageSellingPriceProperty());
        weeklyVelocityUfswCol.setCellValueFactory(cellData -> cellData.getValue().weeklyVelocityUfswProperty());
        selectedRtmCol.setCellValueFactory(cellData -> cellData.getValue().selectedRtmProperty());
    }
}
