package com.traderoute.cells;

import com.traderoute.data.ProductClassReport;
import javafx.scene.control.TableCell;

public class CustomNonEditCellInt<Object, Integer> extends TableCell<Object, Integer> {
    private String per;
    private String dollar;

    public CustomNonEditCellInt(String dollar, String per) {
        super();
        this.per = per;
        this.dollar = dollar;
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("Hello");
        }
        if (item == null) {
            setText("");
        } else {
            if (getTableRow().getIndex() == 0 && getTableRow().getItem() instanceof ProductClassReport) {
                setStyle("-fx-background-color: rgb(105,105,105,0.5)");
            }
            setText(dollar + String.valueOf(item) + per);
        }
    }
}
