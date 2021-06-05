package com.traderoute.tables;

import com.traderoute.cells.CustomTextCell;
import com.traderoute.cells.PromoRowEditCell1;
import com.traderoute.data.PromoRow;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class PricingPromotionTable extends CustomTable {

    private TableColumn<PromoRow<?>, Object> promoRowNameCol;
    private TableColumn<PromoRow<?>, Object> janCol;
    private TableColumn<PromoRow<?>, Object> febCol;
    private TableColumn<PromoRow<?>, Object> marCol;
    private TableColumn<PromoRow<?>, Object> aprCol;
    private TableColumn<PromoRow<?>, Object> mayCol;
    private TableColumn<PromoRow<?>, Object> junCol;
    private TableColumn<PromoRow<?>, Object> julCol;
    private TableColumn<PromoRow<?>, Object> augCol;
    private TableColumn<PromoRow<?>, Object> sepCol;
    private TableColumn<PromoRow<?>, Object> octCol;
    private TableColumn<PromoRow<?>, Object> novCol;
    private TableColumn<PromoRow<?>, Object> decCol;

    public PricingPromotionTable () {
        getSelectionModel().setCellSelectionEnabled(true);
    }
    @Override
    public String getTableCellStyles(int col, int row, Object item) {
        PromoRow<?> promoRow = (PromoRow<?>) this.getItems().get(row);
        String style = "";
        if (promoRow.getJanuary() instanceof String) {
            if (item.equals("")) {
                style = "-fx-background-color:  transparent";
                // TODO MOVE THIS TO CELL INSTEAD
//              setPrefHeight(40); // CAUSING BUG: SEASONALITY INDICE GETS 40 WIDTH OUTTA NOWHERE
            } else {
                style = "-fx-background-color:  rgb(255,255,255, 0.3);\n"
                        + "    -fx-background-insets: 0, 0 0 1 0;";
            }
        }
        else {
            int comparator = new BigDecimal(item.toString()).compareTo(new BigDecimal("0.0"));
            if (promoRow.getName().startsWith("Total Volume")) {
                style = "-fx-text-fill: #A79543;";
            }
            else if (promoRow.getName().startsWith("Gross Profit")) {
                if (comparator >= 0) {
                    style = "-fx-text-fill: green;";
                }
                else {
                    style = "-fx-text-fill: red;";
                }
            }
            else {
                int i = comparator;
                if (i == 0) {
                    style = "-fx-text-fill:  rgb(255,255,255, 0.3);";
                }
                else {
                    style = "-fx-background-color:  rgb(255,255,255, 0.3);\n"
                            + "    -fx-background-insets: 0, 0 0 1 0;";
                }
            }
        }
        return style;
    }

    @Override
    public void setCellFactories() {
        promoRowNameCol.setCellFactory(tc -> new CustomTextCell<>());

        // Calculation Table
        janCol.setCellFactory(tc -> new PromoRowEditCell1());
        febCol.setCellFactory(tc -> new PromoRowEditCell1());
        marCol.setCellFactory(tc -> new PromoRowEditCell1());
        aprCol.setCellFactory(tc -> new PromoRowEditCell1());
        mayCol.setCellFactory(tc -> new PromoRowEditCell1());
        junCol.setCellFactory(tc -> new PromoRowEditCell1());
        julCol.setCellFactory(tc -> new PromoRowEditCell1());
        augCol.setCellFactory(tc -> new PromoRowEditCell1());
        sepCol.setCellFactory(tc -> new PromoRowEditCell1());
        octCol.setCellFactory(tc -> new PromoRowEditCell1());
        novCol.setCellFactory(tc -> new PromoRowEditCell1());
        decCol.setCellFactory(tc -> new PromoRowEditCell1());
    }

    @Override
    public void setCellValueFactories() {
        promoRowNameCol.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().nameProperty());
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
