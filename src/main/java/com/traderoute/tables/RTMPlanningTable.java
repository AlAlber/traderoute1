package com.traderoute.tables;

import com.traderoute.CustomColumn;
import com.traderoute.cells.BigDecimalEditCell1;
import com.traderoute.cells.CellSpecs;
import com.traderoute.cells.CellSpecsBuilder;
import com.traderoute.cells.StdSpecs;
import com.traderoute.data.RTMOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;

import java.math.BigDecimal;

public class RTMPlanningTable extends CustomTable<RTMOption>{

    private int tableNumber;
    private TableColumn<RTMOption, String> rtmNameCol1 = new CustomColumn<RTMOption, String>(148.0, "Route-to-Market Options", "Please enter the most likely 'Route-To-Market' options to get the product to the market.");
    private TableColumn<RTMOption, BigDecimal> slottingPerSkuCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Slotting Per Sku", "Please enter the required slotting (placement) investment specific to this 'Route-To-Market' option.");
    private TableColumn<RTMOption, BigDecimal> freightOutPerUnitCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Freight Out Per Unit","If we're responsible for the cost of shipping for this 'Route-To-Market' option, please enter in the 'per unit cost' of this 'Freight-Out.' For F.O.B (Pick-up) Customers, Freight-Out equals $0.");
    private TableColumn<RTMOption, BigDecimal> firstReceiverCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "First Receiver Pays", "The Per Unit Cost the First Receiver pays us, typically the Unit List Cost or the Unit F.O.B");
    private TableColumn<RTMOption, BigDecimal> secondReceiverCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Second Receiver Pays", "The Per Unit Cost the Second Receiver pays to the First Receiver.");
    private TableColumn<RTMOption, BigDecimal> thirdReceiverCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Third Receiver Pays", "The Per Unit Cost the Third Receiver pays to the Second Receiver.");
    private TableColumn<RTMOption, BigDecimal> fourthReceiverCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Fourth Receiver Pays","The Per Unit Cost the Fourth Receiver pays to the Third Receiver." );
    private TableColumn<RTMOption, BigDecimal> landedStoreCostCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Landed Store Cost", "The Per Unit Cost the Retail OutLet (Last Receiver) pays prior to applying the Required GPM% to establish the Everyday Retail.");
    private TableColumn<RTMOption, BigDecimal> everydayRetailCalcdCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Calculated Everyday Retail", "The auto-calculated Resulting Everyday Retail given the Landed Store Cost and Required Gross Profit Margin %.");
    private TableColumn<RTMOption, BigDecimal> everydayRetailOverrideCol = new CustomColumn<RTMOption, BigDecimal>(148.0, "Override Everyday Retail", "Please enter the REALISTIC Everyday Retail considering the auto-calculated retail to the left.");

    private TableColumn<RTMOption, String> rtmNameCol2 = new CustomColumn<RTMOption, String>(166.0, "Route-to-Market Options", "The most likely 'Route-To-Market' options to get the product to the market.");
    private TableColumn<RTMOption, BigDecimal> elasticizedUnitVelocityCol = new CustomColumn(166.0, "Elasticized Unit Velocity", "For each 'Route-To-Market' option provided, these are the Estimated Weekly Unit Velocities given the Product Class's Price Elasticity Multiple (for each X% increase in Unit Price there is a Y% decrease in Units Sold");
    private TableColumn<RTMOption, BigDecimal> annualVolumePerSkuCol = new CustomColumn(166.0, "Annual Volume Per Sku", "For each 'Route-To-Market' option provided, these are the Estimated Annual Volumes Per SKU (accounting for price elasticities)");
    private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodCol = new CustomColumn(166.0, "Slotting Payback Period", "If Slotting is a consideration these are the Payback Periods (in years) for each route-to-market option provided to recoup the initial Slotting Investment.");
    private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsCol = new CustomColumn(166.0, "Post Freight &amp; Spoils We Collect", "For each route-to-market option provided, the Per Unit Rate we collect after which Freight Costs and Spoils are accounted (but prior to Trade Spending).");
    private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitCol = new CustomColumn(166.0, "Unspent Trade Per Unit", "Unspent Trade Per Unit");
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuCol = new CustomColumn(166.0, "4-Year EQ GP $ Per Sku", "4-Year Equivalized Gross Profit $ Per Sku");
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitCol = new CustomColumn(166.0, "4-Year EQ GP $ Per Unit", "4-Year Equivalized Gross Profit $ Per Unit");


    public RTMPlanningTable (int tableNumber){
        this.tableNumber = tableNumber;
        if (tableNumber == 1){
            setPrefHeight(130.0);
            setPrefWidth(1464.0);
            getStyleClass().add("firstTable");
            setId("rtmPlanningTable1");
            getColumns().addAll(rtmNameCol1, slottingPerSkuCol, freightOutPerUnitCol,
                    firstReceiverCol, secondReceiverCol, thirdReceiverCol, fourthReceiverCol,
                    landedStoreCostCol, everydayRetailCalcdCol, everydayRetailOverrideCol);
        }
        if (tableNumber == 2){
            setEditable(false);
            setId("rtmPlanningTable2");
            getColumns().addAll(rtmNameCol2, elasticizedUnitVelocityCol,
                    annualVolumePerSkuCol, slottingPaybackPeriodCol, postFreightPostSpoilsCol,
                    unspentTradePerUnitCol, fourYearEqGpPerSkuCol, fourYearEqGpPerUnitCol);
        }
        landedStoreCostCol.setEditable(false);
        everydayRetailCalcdCol.setEditable(false);
        getColumns().stream().forEach(tcol -> setSortPolicy(e -> false));
    }


    @Override
    public String getTableCellStyles(int col, int row, Object item) {
        String styles = "";
        if (row==0 && col<=3){
            styles = "-fx-background-color: steelblue";
        }
        return styles;
    }

    @Override
    public void setCellFactories() {
        if (tableNumber == 1){
            CellSpecs specs = StdSpecs.DECPOS6$.getSpecs();
            rtmNameCol1.setCellFactory(TextFieldTableCell.forTableColumn());
            slottingPerSkuCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            freightOutPerUnitCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            firstReceiverCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            secondReceiverCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            thirdReceiverCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            fourthReceiverCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            landedStoreCostCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            everydayRetailCalcdCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
            everydayRetailOverrideCol.setCellFactory(tc -> new BigDecimalEditCell1(specs));
        }
        if (tableNumber == 2){
            elasticizedUnitVelocityCol.setCellFactory(tc -> new BigDecimalEditCell1(
                    new CellSpecsBuilder().post(" U/F/S/W").build()));
            annualVolumePerSkuCol.setCellFactory(tc -> new BigDecimalEditCell1(
                    new CellSpecsBuilder().post(" Units").decPoints(0).build()));
            slottingPaybackPeriodCol.setCellFactory(tc ->
                    new BigDecimalEditCell1(new CellSpecsBuilder().post(" Years").build()));
            postFreightPostSpoilsCol.setCellFactory(tc -> new BigDecimalEditCell1(
                    new CellSpecsBuilder().pre("$").post(" Per Unit").build()));
            unspentTradePerUnitCol.setCellFactory(tc -> new BigDecimalEditCell1(
                    new CellSpecsBuilder().pre("$").post(" Per Unit").build()));
            fourYearEqGpPerSkuCol.setCellFactory(tc -> new BigDecimalEditCell1(
                    new CellSpecsBuilder().pre("$").post(" Per Sku").decPoints(0).build()));
            fourYearEqGpPerUnitCol.setCellFactory(tc -> new BigDecimalEditCell1(
                    new CellSpecsBuilder().pre("$").post(" Per Unit").build()));
        }
    }
    @Override
    public void setCellValueFactories() {
        if (tableNumber == 1) {
            rtmNameCol1.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
            slottingPerSkuCol.setCellValueFactory(cellData -> cellData.getValue().slottingPerSkuProperty());
            freightOutPerUnitCol.setCellValueFactory(cellData -> cellData.getValue().freightOutPerUnitProperty());
            firstReceiverCol.setCellValueFactory(cellData -> cellData.getValue().firstReceiverProperty());
            secondReceiverCol.setCellValueFactory(cellData -> cellData.getValue().secondReceiverProperty());
            thirdReceiverCol.setCellValueFactory(cellData -> cellData.getValue().thirdReceiverProperty());
            fourthReceiverCol.setCellValueFactory(cellData -> cellData.getValue().fourthReceiverProperty());
            landedStoreCostCol.setCellValueFactory(cellData -> cellData.getValue().landedStoreCostProperty());
            everydayRetailCalcdCol.setCellValueFactory(cellData -> cellData.getValue().everydayRetailCalcdProperty());
            everydayRetailOverrideCol
                    .setCellValueFactory(cellData -> cellData.getValue().everydayRetailOverrideProperty());
        } if (tableNumber ==2 ) {

            rtmNameCol2.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
            elasticizedUnitVelocityCol
                    .setCellValueFactory(cellData -> cellData.getValue().elasticizedUnitVelocityProperty());
            annualVolumePerSkuCol.setCellValueFactory(cellData -> cellData.getValue().annualVolumePerSkuProperty());
            slottingPaybackPeriodCol
                    .setCellValueFactory(cellData -> cellData.getValue().slottingPaybackPeriodProperty());
            postFreightPostSpoilsCol
                    .setCellValueFactory(cellData -> cellData.getValue().postSpoilsPostFreightPerUnitProperty());
            unspentTradePerUnitCol.setCellValueFactory(cellData -> cellData.getValue().unspentTradePerUnitProperty());
            fourYearEqGpPerSkuCol.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerSkuProperty());
            fourYearEqGpPerUnitCol.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerUnitProperty());
        }
    }

}
