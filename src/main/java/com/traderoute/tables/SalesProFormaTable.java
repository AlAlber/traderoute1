package com.traderoute.tables;

import com.traderoute.CustomColumn;
import com.traderoute.cells.CustomTextCell;
import com.traderoute.cells.PromoRowNonEditCell;
import com.traderoute.data.PromoRow;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

public class SalesProFormaTable extends CustomTable{
    private TableColumn<PromoRow<?>, String> promoRowNameCol = new TableColumn<>();
    private TableColumn<PromoRow<?>, Object> totalsCol = new CustomColumn(155.0, "Totals", "Totals");
    private TableColumn<PromoRow<?>, Object> janCol = new CustomColumn(93.0, "January","January");
    private TableColumn<PromoRow<?>, Object> febCol = new CustomColumn(93.0, "February", "February");
    private TableColumn<PromoRow<?>, Object> marCol = new CustomColumn(93.0, "March", "March");
    private TableColumn<PromoRow<?>, Object> aprCol = new CustomColumn(93.0, "April", "April");
    private TableColumn<PromoRow<?>, Object> mayCol = new CustomColumn(93.0, "May", "May");
    private TableColumn<PromoRow<?>, Object> junCol = new CustomColumn(93.0, "June", "June");
    private TableColumn<PromoRow<?>, Object> julCol = new CustomColumn(93.0, "July", "July");
    private TableColumn<PromoRow<?>, Object> augCol = new CustomColumn(93.0, "August", "August");
    private TableColumn<PromoRow<?>, Object> sepCol = new CustomColumn(93.0, "September", "September");
    private TableColumn<PromoRow<?>, Object> octCol = new CustomColumn(93.0, "October", "October");
    private TableColumn<PromoRow<?>, Object> novCol = new CustomColumn(93.0, "November", "November");
    private TableColumn<PromoRow<?>, Object> decCol = new CustomColumn(93.0, "December", "December");

    public SalesProFormaTable () {
        setEditable(false);
        getColumns().addAll(promoRowNameCol, totalsCol, janCol, febCol,
                marCol, aprCol, mayCol, junCol, julCol, augCol, sepCol,
                octCol, novCol, decCol);
    }

    @Override
    public void setCellFactories() {
        promoRowNameCol.setCellFactory(tc -> new CustomTextCell<>());
        //TODO REPLACE PROMOROWNONEDITCELL
        janCol.setCellFactory(tc -> new PromoRowNonEditCell());
        febCol.setCellFactory(tc -> new PromoRowNonEditCell());
        marCol.setCellFactory(tc -> new PromoRowNonEditCell());
        aprCol.setCellFactory(tc -> new PromoRowNonEditCell());
        mayCol.setCellFactory(tc -> new PromoRowNonEditCell());
        junCol.setCellFactory(tc -> new PromoRowNonEditCell());
        julCol.setCellFactory(tc -> new PromoRowNonEditCell());
        augCol.setCellFactory(tc -> new PromoRowNonEditCell());
        sepCol.setCellFactory(tc -> new PromoRowNonEditCell());
        octCol.setCellFactory(tc -> new PromoRowNonEditCell());
        novCol.setCellFactory(tc -> new PromoRowNonEditCell());
        decCol.setCellFactory(tc -> new PromoRowNonEditCell());
    }

    @Override
    public void setCellValueFactories() {
        promoRowNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        totalsCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().totalProperty());
        janCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().januaryProperty());
        febCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().februaryProperty());
        marCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().marchProperty());
        aprCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().aprilProperty());
        mayCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().mayProperty());
        junCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().juneProperty());
        julCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().julyProperty());
        augCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().augustProperty());
        sepCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().septemberProperty());
        octCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().octoberProperty());
        novCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().novemberProperty());
        decCol.setCellValueFactory(cellData -> (ObservableValue<Object>) cellData.getValue().decemberProperty());

    }
}
