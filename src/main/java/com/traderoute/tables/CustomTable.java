package com.traderoute.tables;

import com.traderoute.TableWithStyles;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Optional;

public abstract class CustomTable extends TableView{
    public CustomTable() {
        getStylesheets().add("@../styles/Styles.css");
        setPrefWidth(1482);
    }

    public String getTableCellStyles(int col, int row, Object item){
        return "";
    }

    public abstract void setCellFactories();

    public abstract void setCellValueFactories ();


}
