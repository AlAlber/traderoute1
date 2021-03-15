package com.traderoute;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class CustomNonEditCell<Object, BigDecimal> extends TableCell<Object, BigDecimal> {
    private String per;
    private String dollar;

    public CustomNonEditCell(String dollar, String per){
        super();
        this.per = per;
        this.dollar = dollar;
    }
    @Override
    protected void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("Hello");
        }
        if (item == null) {
            setText("");
        }
        else {
            if (getTableView().getItems().get(getIndex()) instanceof Summary) {
                Summary summary = (Summary) getTableView().getItems().get(getIndex());
                if (summary.getSummaryType().equals("Total Units")){
                    setText(String.format("%,.0f", item));
                    setTooltip(new Tooltip(String.format("%,.0f", item)));
                } else if (summary.getSummaryType().equals("GPM")){
                    setText(String.format("%,.2f", item) + "%");
                    setTooltip(new Tooltip(String.format("%,.2f", item) + "%"));
                } else{
                    setText(dollar + String.format("%,.2f", item) + per);
                    setTooltip(new Tooltip(dollar + String.format("%,.2f", item) + per));
                }
            }else {
                setText(dollar + String.format("%,.2f", item) + per);
            }
        }
    }
}
