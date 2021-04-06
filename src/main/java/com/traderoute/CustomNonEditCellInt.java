package com.traderoute;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;

public class CustomNonEditCellInt<Object, Integer> extends TableCell<Object, Integer> {
    private String per;
    private String dollar;

    public CustomNonEditCellInt(String dollar, String per){
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
        }
        else {
                setText(dollar + String.valueOf( item) + per);
            }
        }
    }
