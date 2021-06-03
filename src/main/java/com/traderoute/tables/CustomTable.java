package com.traderoute.tables;

import com.traderoute.TableWithStyles;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public abstract class CustomTable extends TableView{
    public CustomTable() {
        getStylesheets().add("@../styles/Styles.css");
        setPrefWidth(1482);
    }

    public abstract String getTableCellStyles(int col, int row);

    public abstract void setCellFactories(ObservableList<TableColumn> columns);

    public abstract void setCellValueFactories (ObservableList<TableColumn> columns);

    
}
