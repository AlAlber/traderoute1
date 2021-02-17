package com.traderoute;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class ParameterValueEditingCell extends TableCell<Parameter<?>, Object> {
    //    private String pre;
//    public ParameterValueEditingCell(String pre){
//        this.pre=pre;
//    }
//    private TextField textField;


    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            Parameter<?> param = getTableView().getItems().get(getIndex());
            TableColumn<Parameter<?>,?> column = getTableColumn();
            int colIndex = getTableView().getColumns().indexOf(column);
            if (isEditing()) {
                setText(null);
                setGraphic(param.getEditor(colIndex));
            } else {
                setText(param.getPre() +item.toString());
                setGraphic(null);
            }
        }
    }

    @Override
    public void startEdit() {
        Parameter<?> param = getTableRow().getItem();
        TableColumn<Parameter<?>,?> column = getTableColumn();
        System.out.println(param.toString());
        int colIndex = getTableView().getColumns().indexOf(column);
//        TableColumn<Parameter<?>,?> cell =  getTableView().getColumns().get(getIndex());
        System.out.println(colIndex);

        ((TextField) param.getEditor(colIndex)).setOnAction(evt -> { // enable ENTER commit
            if (param.getJanuary() instanceof String){
            commitEdit(
                    ((TextField) param.getEditor(colIndex)).getText());
            };
            if (param.getJanuary() instanceof BigDecimal){
                commitEdit(
                        new BigDecimal(((TextField) param.getEditor(colIndex)).getText()));
            }
            if (param.getJanuary() instanceof Integer){
                commitEdit(
                        Integer.valueOf(((TextField) param.getEditor(colIndex)).getText()));
            }
        });

        ChangeListener<? super Boolean> changeListener = (observable, oldSelection, newSelection) ->
        {
            if (! newSelection) {
                commitEdit(((TextField)param.getEditor(colIndex)).getText());
            }
        };
        param.getEditor(colIndex).focusedProperty().addListener(changeListener);

        // check if current parameter is editable and bail if not:

        int index = getIndex();
        if (index < 0 || index >= getTableView().getItems().size()) {
            return ;
        }
        if (! getTableView().getItems().get(index).isEditable()) {
            return ;
        }

        super.startEdit();
        setText(null);
        setGraphic(getTableView().getItems().get(getIndex()).getEditor(colIndex));
        ((TextField)param.getEditor(colIndex)).selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        Object item = getItem();
        Parameter<?> param = getTableView().getItems().get(getIndex());
        setText(item == null ? null : param.getPre()+ item.toString());
        setGraphic(null);
    }
//    @Override
//    public void commitEdit(Object item){
//        if (isEditing()) {
//            super.commitEdit(item);
//        } else {
//            final TableView table = getTableView();
//            if (table != null) {
//                TablePosition position = new TablePosition(getTableView(), getTableRow().getIndex(), getTableColumn());
//                TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, position, TableColumn.editCommitEvent(), item);
//                Event.fireEvent(getTableColumn(), editEvent);
//            }
//            updateItem(item, false);
//            if (table != null) {
//                table.edit(-1, null);
//            }
//
//        }
//
//    }
    private String getString(){
        return getItem() == null ? null : getItem().toString();
    }

}