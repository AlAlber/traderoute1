package com.traderoute.tables;

import com.traderoute.cells.BigDecimalEditCell1;
import com.traderoute.cells.CellSpecs;
import com.traderoute.cells.CellSpecsBuilder;
import com.traderoute.cells.StdSpecs;
import com.traderoute.data.RTMOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

import java.math.BigDecimal;

public class RTMPlanningTable extends CustomTable{

    private int tableNumber;

    private TableColumn<RTMOption, String> rtmNameCol1 = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> slottingPerSkuCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> freightOutPerUnitCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> firstReceiverCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> secondReceiverCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> thirdReceiverCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> fourthReceiverCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> landedStoreCostCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> everydayRetailCalcdCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> everydayRetailOverrideCol = new TableColumn<>();
    private ObservableList<TableColumn> columns1 =
            FXCollections.observableArrayList(rtmNameCol1, slottingPerSkuCol, freightOutPerUnitCol,
                    firstReceiverCol, secondReceiverCol, thirdReceiverCol, fourthReceiverCol,
                    landedStoreCostCol, everydayRetailCalcdCol, everydayRetailOverrideCol);

    private TableColumn<RTMOption, String> rtmNameCol2 = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> elasticizedUnitVelocityCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> annualVolumePerSkuCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> slottingPaybackPeriodCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> postFreightPostSpoilsPerUnitCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> unspentTradePerUnitCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerSkuCol = new TableColumn<>();
    private TableColumn<RTMOption, BigDecimal> fourYearEqGpPerUnitCol = new TableColumn<>();
    private ObservableList<TableColumn> columns2 =
            FXCollections.observableArrayList(rtmNameCol2, elasticizedUnitVelocityCol,
                    annualVolumePerSkuCol, slottingPaybackPeriodCol, postFreightPostSpoilsPerUnitCol,
                    unspentTradePerUnitCol, fourYearEqGpPerSkuCol, fourYearEqGpPerUnitCol);


    public RTMPlanningTable (int tableNumber){
        this.tableNumber = tableNumber;
        if (tableNumber == 1){
            setId("rtmPlanningTable1");
            getColumns().addAll(columns1.stream());
        }
        if (tableNumber == 2){
            setId("rtmPlanningTable2");
            getColumns().addAll(columns2.stream());
        }
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
            postFreightPostSpoilsPerUnitCol.setCellFactory(tc -> new BigDecimalEditCell1(
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
            elasticizedUnitVelocityCol
                    .setCellValueFactory(cellData -> cellData.getValue().elasticizedUnitVelocityProperty());
            annualVolumePerSkuCol.setCellValueFactory(cellData -> cellData.getValue().annualVolumePerSkuProperty());
            slottingPaybackPeriodCol
                    .setCellValueFactory(cellData -> cellData.getValue().slottingPaybackPeriodProperty());
            postFreightPostSpoilsPerUnitCol
                    .setCellValueFactory(cellData -> cellData.getValue().postSpoilsPostFreightPerUnitProperty());
            unspentTradePerUnitCol.setCellValueFactory(cellData -> cellData.getValue().unspentTradePerUnitProperty());
            fourYearEqGpPerSkuCol.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerSkuProperty());
            fourYearEqGpPerUnitCol.setCellValueFactory(cellData -> cellData.getValue().fourYearEqGpPerUnitProperty());
            rtmNameCol2.setCellValueFactory(cellData -> cellData.getValue().rtmNameProperty());
        }
    }
}
