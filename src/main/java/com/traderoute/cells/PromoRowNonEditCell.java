package com.traderoute.cells;

import com.traderoute.data.PromoRow;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PromoRowNonEditCell extends TableCell<PromoRow<?>, Object> {
    @Override
    public void updateItem(Object item, boolean empty) {

        super.updateItem(item, empty);
        PromoRow<?> promoRow = getTableRow().getItem();
        if (empty || item == null) {
            setText("");
            setGraphic(null);
        } else {
            String itemString = "";
            if (item instanceof BigDecimal) {
                if (item != null) {
                    itemString = (((BigDecimal) item).setScale(0, RoundingMode.HALF_UP).toString());
                }
            } else if (item instanceof Integer) {
                if (item != null) {
                    itemString = item.toString();
                }
            } else {
                itemString = (String) item;
            }

            if (promoRow.getPre().equals("%")) {
                setText(itemString + promoRow.getPre());
                setTooltip(new Tooltip(itemString + promoRow.getPre()));
            } else {
                setText(promoRow.getPre() + itemString);
                setTooltip(new Tooltip(promoRow.getPre() + itemString));
            }
            // if (param.getJanuary() instanceof String) {
            // if (item.equals("")) {
            // setStyle("-fx-background-color: transparent");
            // setPrefHeight(40); // CAUSING BUG: SEASONALITY INDICE GETS 40 WIDTH OUTTA NOWHERE
            // } else {
            // setStyle("-fx-background-color: rgb(255,255,255, 0.3);\n" +
            // " -fx-background-insets: 0, 0 0 1 0;");
            // }
            // }
            // if (param.getJanuary() instanceof Number) {
            // int comparator = new BigDecimal(item.toString()).compareTo(new BigDecimal("0.0"));
            // if (param.getName().startsWith("Total Volume")){
            // setStyle("-fx-text-fill: #A79543;");
            // } else if (param.getName().startsWith("Gross Profit")){
            // if (comparator >=0){
            // setStyle("-fx-text-fill: green;");
            // } else {
            // setStyle("-fx-text-fill: red;");
            // }
            // } else {
            // int i = comparator;
            // if (i > 0) {
            // setStyle("-fx-background-color: rgb(255,255,255, 0.3);\n" +
            // " -fx-background-insets: 0, 0 0 1 0;");
            // }
            // if (i == 0) {
            // setStyle("-fx-text-fill: rgb(255,255,255, 0.3);");
            // }
            // }
            // }
            // }
        }
    }

    // private String getString() {
    //
    // }
}
