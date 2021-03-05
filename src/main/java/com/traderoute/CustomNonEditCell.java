package com.traderoute;

import javafx.scene.control.TableCell;
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
            setText(dollar + String.format("%,.2f", item) + per);
        }
    }
}
