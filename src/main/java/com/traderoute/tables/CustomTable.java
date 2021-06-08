package com.traderoute.tables;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

public abstract class CustomTable<T extends Object> extends TableView<T>{
    public CustomTable() {
        getStylesheets().add("@../styles/Styles.css");
        setPrefWidth(1482);
        setCellFactories();
        setCellValueFactories();
    }

    public String getTableCellStyles(int col, int row, final Object item){
        return "";
    }

    public abstract void setCellFactories();

    public abstract void setCellValueFactories ();


}
