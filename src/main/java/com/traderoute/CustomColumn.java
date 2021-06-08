package com.traderoute;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;

public class CustomColumn<T extends Object, A extends Object> extends TableColumn {
    private Label lbl;
    private Tooltip tip;
    public CustomColumn (double prefWidth, String lbl, String tip){
        this.lbl = new Label(lbl);
        this.tip = new Tooltip(tip);
        setPrefWidth(prefWidth);
        addLabelTipsToCol(this.lbl, this.tip);
    }

    public void addLabelTipsToCol(Label label, Tooltip tip){
        label.setTooltip(tip);
        this.setGraphic(label);
    }
}
