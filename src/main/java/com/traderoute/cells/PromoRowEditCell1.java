
package com.traderoute.cells;

import com.traderoute.BigDecimalTextField;
import com.traderoute.DoubleInputConverter;
import com.traderoute.IntegerTextField;
import com.traderoute.NumberTextField;
import com.traderoute.data.PromoRow;
import com.traderoute.data.PromoPlan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class PromoRowEditCell1 extends NumberEditCell1 {
    private TextField textField;
//    private PromoRow<?> currentRow;
//    private TableColumn<PromoRow<?>, ?> currentCol;
//    private int rowIndex;


    public PromoRowEditCell1() {
//        this.currentRow = (PromoRow) getTableRow().getItem();
//        this.rowIndex = getTableRow().getIndex();
    }
    public PromoRow getRow(){
        return (PromoRow) getTableRow().getItem();
    }
    public int getRowIndex(){
        return getTableRow().getIndex();
    }

    @Override
    public CellSpecs get_specs(){
        return ((PromoRow) getTableRow().getItem()).getSpecs();
    }


    @Override
    public void startEdit() {
        // bail on empty rows
        if (getRowIndex() == 5 || getRowIndex() == 8) {
            return;
        }
        TableColumn<PromoRow<?>, ?> column = getTableColumn();
        // BAIL for first line when getting past first value
        int colIndex = getTableView().getColumns().indexOf(column);
        if (getRowIndex() == 0 && colIndex > 1) {
            return;
        }
        if (!getRow().isEditable()) {
            return;
        }
        super.startEdit();
//            createTextField();
//
//            if (promoRow.getJanuary() instanceof BigDecimal) {
//                textField.setTextFormatter(new TextFormatter(new DoubleInputConverter(),
//                        Double.valueOf(textField.getText()), DoubleInputConverter.getFilter()));
//            } else if (promoRow.getJanuary() instanceof Integer) {
//                textField.textProperty().addListener(new ChangeListener<String>() {
//                    private boolean changing;
//
//                    @Override
//                    public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                        String newValue) {
//                        if (!newValue.matches("\\d*")) {
//                            textField.setText(newValue.replaceAll("[^\\d]", ""));
//                        }
//                        if (!changing) {
//                            try {
//                                changing = true;
//                                textField.setText(newValue);
//                            } finally {
//                                changing = false;
//                            }
//                        }
//                    }
//                });
//            }
//            setText(null);
//            setGraphic(textField);
//            textField.selectAll();
//            textField.setOnAction(evt -> processEdit()); // react to enter
//        }
    }

    @Override
    public void cancelEdit() {
        if (getRowIndex() == 5 || getRowIndex() == 8) {
            return;
        }
        super.cancelEdit();

//        if (currentRow.getSpecs().getPre().equals("%")) { // If i dont do this it removes the pre
//            setText(getItem().toString() + currentRow.getSpecs().getPre());
//        } else {
//            setText(currentRow.getSpecs().getPre() + getItem().toString());
//        }
//        setGraphic(null);
    }

//    public void bailOnEmptyRows() {
//        if (getTableRow().getIndex() == 5 | getTableRow().getIndex() == 8) {
//            return;
//        }
//    }

    @Override
    public void updateItem(Object item, boolean empty) {

        if (getRowIndex() == 5 || getRowIndex() == 8) {
            return;
        }
        super.updateItem(item, empty);
        addStyling();
//        if (empty) {
//            setText(null);
//            setGraphic(null);
//        } else {
//            if (isEditing()) {
//                if (textField != null) {
//                    textField.setText(getString());
//                }
//                setText(null);
//                setGraphic(textField);
//            } else {
                // SHOULD BE DEFINED WITH DEFAULT MIN MAX PARAMS
//                if (getTableRow().getIndex() == 12 || getTableRow().getIndex() == 20) {
//                    if ((int) item > (PromoPlan
//                            .getWeeksInPeriod(getTableView().getColumns().indexOf(getTableColumn())))) {
//                        setItem(0);
//                    }
//                }
//                if (currentRow.getSpecs().getPre().equals("%")) {
//                    setText(getString() + currentRow.getPre());
//                    setTooltip(new Tooltip(getString() + currentRow.getPre()));
//                } else {
//                    setText(currentRow.getPre() + getString());
//                    setTooltip(new Tooltip(currentRow.getPre() + getString()));
//                }
//                setGraphic(null);

//        }
    }
    public void addStyling(){
        if (getRow() == null){
            return;
        }
        if (getRow().getJanuary() instanceof String) {
            if (getItem().equals("")) {
                setStyle("-fx-background-color:  transparent");
                setPrefHeight(40); // CAUSING BUG: SEASONALITY INDICE GETS 40 WIDTH OUTTA NOWHERE
            } else {
                setStyle("-fx-background-color:  rgb(255,255,255, 0.3);\n"
                        + "    -fx-background-insets: 0, 0 0 1 0;");
            }
        } else {
            int comparator = new BigDecimal(getItem().toString()).compareTo(new BigDecimal("0.0"));
            if (getRow().getName().startsWith("Total Volume")) {
                setStyle("-fx-text-fill: #A79543;");
            } else if (getRow().getName().startsWith("Gross Profit")) {
                if (comparator >= 0) {
                    setStyle("-fx-text-fill: green;");
                } else {
                    setStyle("-fx-text-fill: red;");
                }
            } else {
                int i = comparator;
                if (i > 0) {
                    setStyle("-fx-background-color:  rgb(255,255,255, 0.3);\n"
                            + "    -fx-background-insets: 0, 0 0 1 0;");
                }
                if (i == 0) {
                    setStyle("-fx-text-fill:  rgb(255,255,255, 0.3);");
                }
            }
        }

    }

//    private void createTextField() {
//        textField = new TextField(getString(specs));
//        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
//        textField.selectAll();
//        textField.focusedProperty()
//                .addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
//                    if (!arg2) {
//                        processEdit();
//                    }
//                });
//    }

    @Override
    public TextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue) {
        if (getRow()==null){
            return null;
        }
        if (getRow().getJanuary() instanceof BigDecimal) {
            return new BigDecimalTextField(defaultValue, minValue, maxValue, false);
        } else if (getRow().getJanuary() instanceof Integer){
            return new IntegerTextField(defaultValue, minValue, maxValue, false);
        } else {
            return new TextField();
        }
    }

//    private void processEdit() {
//        String text = textField.getText();
//        PromoRow<?> promoRow = getTableRow().getItem();
//        if (promoRow.getJanuary() instanceof Integer) {
//            if (text.equals("")) {
//                commitEdit(0);
//            } else {
//                commitEdit(Integer.parseInt(text));
//            }
//        } else if (promoRow.getJanuary() instanceof BigDecimal) {
//            commitEdit(new BigDecimal(text));
//        } else {
//            commitEdit(text);
//        }
//    }

    @Override
    public void commitValueEdit(String text) {
        if (getRow().getJanuary() instanceof BigDecimal){
            commitEdit(new BigDecimal(text));
        } else if (getRow().getJanuary() instanceof Integer){
            commitEdit(java.lang.Integer.valueOf(text));
        } else {
            commitEdit(text);
        }

    }

    @Override
    public Object getZeroOrEmptyString() {
        if (getRow().getJanuary() instanceof BigDecimal){
            return new BigDecimal("0.0");
        } else if (getRow().getJanuary() instanceof Integer){
            return 0;
        } else {
            return "";
        }
    }

    @Override
    public String getString(CellSpecs specs) {
        if (specs == null){
            return getItem().toString();
        }
        if (getItem() instanceof BigDecimal) {
            return getItem() == null ? "" : specs.getPre() + ((BigDecimal) getItem())
                    .setScale(2, RoundingMode.HALF_UP).toString() + specs.getPost();
        }
        // WORKS the same for Integer and Stirng row, String row just wont have pre or post
        return getItem() == null ? "" : specs.getPre() + getItem().toString() + specs.getPost();
    }


//    @Override
//    public void commitEdit(Object item) {
//        // This block is necessary to support commit on losing focus, because
//        // the baked-in mechanism sets our editing state to false before we can
//        // intercept the loss of focus. The default commitEdit(...) method
//        // simply bails if we are not editing...
//        if (!isEditing() && !item.equals(getItem())) {
//            TableView<PromoRow<?>> table = getTableView();
//            if (table != null) {
//                TableColumn<PromoRow<?>, Object> column = getTableColumn();
//                TableColumn.CellEditEvent<PromoRow<?>, Object> event = new TableColumn.CellEditEvent<>(table,
//                        new TablePosition<PromoRow<?>, Object>(table, getIndex(), column),
//                        TableColumn.editCommitEvent(), item);
//                Event.fireEvent(column, event);
//            }
//        }
//
//        super.commitEdit(item);
//    }
}