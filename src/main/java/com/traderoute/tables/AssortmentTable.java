package com.traderoute.tables;

import com.traderoute.data.Sku;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

public class AssortmentTable extends CustomTable{
    private TableColumn<Sku, String> flavorDescriptionCol;
    private TableColumn<Sku, StringProperty> statusCol;
    private TableColumn<Sku, String> skuNotesCol;

    public AssortmentTable (){
        getColumns().addAll(flavorDescriptionCol, statusCol, skuNotesCol);
    }

    @Override
    public void setCellFactories() {
        flavorDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setCellFactory(col -> {
            TableCell<Sku, StringProperty> c = new TableCell<>();
            ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Current", "Targeted", "Discontinued"));
            c.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue != null) {
                    comboBox.valueProperty().unbindBidirectional(oldValue);
                }
                if (newValue != null) {
                    comboBox.valueProperty().bindBidirectional(newValue);
                }
            });
            c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
            return c;
        });
        skuNotesCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @Override
    public void setCellValueFactories() {
        flavorDescriptionCol.setCellValueFactory(celldata -> celldata.getValue().flavorDescriptionProperty());
        statusCol.setCellValueFactory(i -> {
            final StringProperty value = i.getValue().statusProperty();
            // binding to constant value
            return Bindings.createObjectBinding(() -> value);
        });
        skuNotesCol.setCellValueFactory(celldata -> celldata.getValue().skuNotesProperty());
    }
}
