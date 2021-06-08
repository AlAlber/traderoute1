package com.traderoute.tables;

import com.traderoute.CustomColumn;
import com.traderoute.cells.*;
import com.traderoute.data.ProductClassReport;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class ProductClassReportTable extends CustomTable{
    private TableColumn<ProductClassReport, BigDecimal> emptyCol = new TableColumn<>();
    private TableColumn<ProductClassReport, String> retailerNameCol = new CustomColumn(130.0, "Retailer", "Retailer");
    private TableColumn<ProductClassReport, Boolean> committedCol = new CustomColumn(60.0, "in Forecast?", "in Forecast?");
    private TableColumn<ProductClassReport, Integer> storeCountCol = new CustomColumn(63.2, "Store Count", "Store Count");
    private TableColumn<ProductClassReport, BigDecimal> totalVolumetricsCol = new CustomColumn(95.0, "Total", "Total Volumetrics");
    private TableColumn<ProductClassReport, BigDecimal> everydayVolumetricsCol = new CustomColumn(95.0, "Everyday", "Everyday Volumetrics");
    private TableColumn<ProductClassReport, BigDecimal> promotedVolumetricsCol = new CustomColumn(95.0, "Promoted", "Promoted Volumetrics");
    private TableColumn<ProductClassReport, BigDecimal> grossRevenueCol = new CustomColumn(95.0, "Gross $", "Gross Revenue $");
    private TableColumn<ProductClassReport, BigDecimal> tradeRevenueCol = new CustomColumn(95.0, "Trade $", "Trade Revenue $");
    private TableColumn<ProductClassReport, BigDecimal> net1RevenueCol = new CustomColumn(95.0, "Net 1 $", "Net 1 Revenue $");
    private TableColumn<ProductClassReport, BigDecimal> net1PodCol = new CustomColumn(95.0, "Net 1$/Pod", "Net 1$/Pod");
    private TableColumn<ProductClassReport, BigDecimal> net1RateCol = new CustomColumn(95.0, "Net1 $ Rate", "Net1 $ Rate");
    private TableColumn<ProductClassReport, BigDecimal> pointsOfDistributionCol = new CustomColumn(95.0, "Points Of Distribution", "Points Of Distribution");
    private TableColumn<ProductClassReport, BigDecimal> averageSkusCol = new CustomColumn(95.0, "Average SKUs", "Average SKUs");
    private TableColumn<ProductClassReport, BigDecimal> averageSellingPriceCol = new CustomColumn(95.0, "Average Selling Price", "Average Selling Price");
    private TableColumn<ProductClassReport, BigDecimal> weeklyVelocityUfswCol = new CustomColumn(95.0, "Weekly Velocity U/F/S/W", "Weekly Velocity U/F/S/W");
    private TableColumn<ProductClassReport, String> selectedRtmCol = new CustomColumn(150.0, "Current FY Route to Market", "Current Fiscal Year Route to Market");

    public ProductClassReportTable (){
        getColumns().addAll(retailerNameCol, committedCol, storeCountCol,
                totalVolumetricsCol, everydayVolumetricsCol, promotedVolumetricsCol, emptyCol,
                grossRevenueCol, tradeRevenueCol, net1RevenueCol, emptyCol, net1PodCol,
                net1RateCol, emptyCol,  pointsOfDistributionCol, averageSkusCol, averageSellingPriceCol,
                weeklyVelocityUfswCol, emptyCol,  selectedRtmCol);
        getColumns().stream().forEach(tcol -> setSortPolicy(e -> false));
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
