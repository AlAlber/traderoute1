// package com.traderoute;
//
// import javafx.beans.property.ObjectProperty;
// import javafx.beans.value.ChangeListener;
// import javafx.scene.control.*;
// import javafx.util.StringConverter;
// import org.w3c.dom.Text;
//
// import java.math.BigDecimal;
//
// public class ParameterValueEditingCell extends TableCell<Parameter<?>, Object> {
// private TextField textField;
// private ObjectProperty<StringConverter<?>> converter;
// @Override
// public void updateItem(Object item, boolean empty) {
// super.updateItem(item, empty);
// if (empty || item == null) {
// setText(null);
// setGraphic(null);
// setStyle("\n" +
// " -fx-border-color: transparent;\n" +
// " -fx-table-cell-border-color: transparent;");
// } else {
// Parameter<?> param = getTableView().getItems().get(getIndex());
// TableColumn<Parameter<?>, ?> column = getTableColumn();
// int colIndex = getTableView().getColumns().indexOf(column);
// if (isEditing()) {
// setText(null);
// setGraphic(param.getEditor(colIndex));
// } else {
// setText(param.getPre() + item.toString());
// setGraphic(null);
// if (param.getJanuary() instanceof String) {
// if (item.equals("")) {
// setPrefHeight(40); // CAUSING BUG: SEASONALITY INDICE GETS 40 WIDTH OUTTA NOWHERE
// } else {
// setStyle("-fx-background-color: rgb(255,255,255, 0.3);\n" +
// " -fx-background-insets: 0, 0 0 1 0;");
// }
// }
// if (param.getJanuary() instanceof Number) {
// int comparator = new BigDecimal(item.toString()).compareTo(new BigDecimal("0.0"));
// if (param.getName().startsWith("Total Volume")){
// setStyle("-fx-text-fill: #A79543;");
// } else if (param.getName().startsWith("Gross Profit")){
// if (comparator >=0){
// setStyle("-fx-text-fill: green;");
// } else {
// setStyle("-fx-text-fill: red;");
// }
// } else {
// int i = comparator;
// if (i > 0) {
// setStyle("-fx-background-color: rgb(255,255,255, 0.3);\n" +
// " -fx-background-insets: 0, 0 0 1 0;");
// }
// if (i == 0) {
// setStyle("-fx-text-fill: rgb(255,255,255, 0.3);");
// }
// }
// }
// }
// }
// }
//
// @Override
// public void startEdit() {
// Parameter<?> param = getTableRow().getItem();
// TableColumn<Parameter<?>,?> column = getTableColumn();
// int colIndex = getTableView().getColumns().indexOf(column);
// ((TextField) param.getEditor(colIndex)).setOnAction(evt -> { // enable ENTER commit
// if (param.getJanuary() instanceof String){
// commitEdit(
// ((TextField) param.getEditor(colIndex)).getText());
// };
// if (param.getJanuary() instanceof BigDecimal){
// commitEdit(
// new BigDecimal(((TextField) param.getEditor(colIndex)).getText()));
// }
// if (param.getJanuary() instanceof Integer){
// commitEdit(
// Integer.valueOf(((TextField) param.getEditor(colIndex)).getText()));
// }
// });
//
// ChangeListener<? super Boolean> changeListener = (observable, oldSelection, newSelection) ->
// {
// if (! newSelection) {
// if (param.getJanuary() instanceof String){
// commitEdit(
// ((TextField) param.getEditor(colIndex)).getText());
// };
// if (param.getJanuary() instanceof BigDecimal){
// commitEdit(
// new BigDecimal(((TextField) param.getEditor(colIndex)).getText()));
// }
// if (param.getJanuary() instanceof Integer){
// commitEdit(
// Integer.valueOf(((TextField) param.getEditor(colIndex)).getText()));
// }
// }
// };
// param.getEditor(colIndex).focusedProperty().addListener(changeListener);
//
// // check if current parameter is editable and bail if not:
//
// int index = getIndex();
// if (index < 0 || index >= getTableView().getItems().size()) {
// return ;
// }
// if (getTableView().getItems().get(index).getEditor(0).isEditable())
// if (colIndex!=0 && !getTableView().getItems().get(index).isEditable()) {
// return;
// }
// if (colIndex==0 && ! getTableView().getItems().get(index).getEditor(0).isEditable()){
// return;
// }
//
// super.startEdit();
// setText(null);
// setGraphic(getTableView().getItems().get(getIndex()).getEditor(colIndex));
// ((TextField)param.getEditor(colIndex)).selectAll();
// }
//
// @Override
// public void cancelEdit() {
// super.cancelEdit();
// Object item = getItem();
// Parameter<?> param = getTableView().getItems().get(getIndex());
// setText(item == null ? null : param.getPre()+ item.toString());
// setGraphic(null);
// }
//// @Override
//// public void commitEdit(Object item){
//// if (isEditing()) {
//// super.commitEdit(item);
//// } else {
//// final TableView table = getTableView();
//// if (table != null) {
//// TablePosition position = new TablePosition(getTableView(), getTableRow().getIndex(), getTableColumn());
//// TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, position, TableColumn.editCommitEvent(),
// item);
//// Event.fireEvent(getTableColumn(), editEvent);
//// }
//// updateItem(item, false);
//// if (table != null) {
//// table.edit(-1, null);
//// }
////
//// }
////
//// }
// private String getString(){
// return getItem() == null ? null : getItem().toString();
// }
//
// }