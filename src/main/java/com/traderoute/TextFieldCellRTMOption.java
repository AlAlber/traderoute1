package com.traderoute;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;

public class TextFieldCellRTMOption<RTMOption, BigDecimal> extends TextFieldTableCell<RTMOption, BigDecimal> {
    public void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("Hello");
        }
        if (item == null) {
            setText("");
        }
        else {
            setText("$"+ item.toString() );
        }
    }
}
