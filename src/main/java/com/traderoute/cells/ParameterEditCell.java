
package com.traderoute.cells;

import com.traderoute.DoubleInputConverter;
import com.traderoute.data.Parameter;
import com.traderoute.data.PromoPlan;
import com.traderoute.controllers.RTMPlanningController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class ParameterEditCell extends TableCell<Parameter<?>, Object> {
    private TextField textField;
    private final Pattern intPattern = Pattern.compile("-?\\d+");
    private final Pattern bigDecimalPattern = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

    public ParameterEditCell() {

    }

    public TextField getTextField() {
        return textField;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            if (getTableRow().getIndex() == 5 || getTableRow().getIndex() == 8) {
                return; // bail on empty rows
            }
            TableColumn<Parameter<?>, ?> column = getTableColumn();
            int colIndex = getTableView().getColumns().indexOf(column); // BAIL for first line when getting past first
                                                                        // value
            if (getTableRow().getIndex() == 0 && colIndex > 1) {
                return;
            }
            createTextField();
            Parameter<?> param = getTableRow().getItem();
            if (!param.isEditable()) {
                return;
            }
            if (param.getJanuary() instanceof BigDecimal) {
                textField.setTextFormatter(new TextFormatter(new DoubleInputConverter(),
                        Double.valueOf(textField.getText()), DoubleInputConverter.getFilter()));
            } else if (param.getJanuary() instanceof Integer) {
                textField.textProperty().addListener(new ChangeListener<String>() {
                    private boolean changing;

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {
                        if (!newValue.matches("\\d*")) {
                            textField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                        if (!changing) {
                            try {
                                changing = true;
                                textField.setText(newValue);
                            } finally {
                                changing = false;
                            }
                        }
                    }
                });
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.setOnAction(evt -> processEdit()); // react to enter
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        if (getTableRow().getIndex() == 5 || getTableRow().getIndex() == 8) {
            return;
        }
        Parameter<?> param = getTableRow().getItem();
        if (param.getPre().equals("%")) { // If i dont do this it removes the pre
            setText(getItem().toString() + param.getPre());
        } else {
            setText(param.getPre() + getItem().toString());
        }
        setGraphic(null);
    }

    public void bailOnEmptyRows() {
        if (getTableRow().getIndex() == 5 | getTableRow().getIndex() == 8) {
            return;
        }
    }

    @Override
    public void updateItem(Object item, boolean empty) {

        super.updateItem(item, empty);
        if (getTableRow().getIndex() == 5 || getTableRow().getIndex() == 8) {
            return;
        }
        Parameter<?> param = getTableRow().getItem();
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                if (getTableRow().getIndex() == 12 || getTableRow().getIndex() == 20) {
                    if ((int) item > (PromoPlan
                            .getWeeksInPeriod(getTableView().getColumns().indexOf(getTableColumn())))) {
                        setItem(0);
                    }
                }
                if (param.getPre().equals("%")) {
                    setText(getString() + param.getPre());
                    setTooltip(new Tooltip(getString() + param.getPre()));
                } else {
                    setText(param.getPre() + getString());
                    setTooltip(new Tooltip(param.getPre() + getString()));
                }
                setGraphic(null);
                if (param.getJanuary() instanceof String) {
                    if (item.equals("")) {
                        setStyle("-fx-background-color:  transparent");
                        setPrefHeight(40); // CAUSING BUG: SEASONALITY INDICE GETS 40 WIDTH OUTTA NOWHERE
                    } else {
                        setStyle("-fx-background-color:  rgb(255,255,255, 0.3);\n"
                                + "    -fx-background-insets: 0, 0 0 1 0;");
                    }
                }
                if (param.getJanuary() instanceof Number) {
                    int comparator = new BigDecimal(item.toString()).compareTo(new BigDecimal("0.0"));
                    if (param.getName().startsWith("Total Volume")) {
                        setStyle("-fx-text-fill: #A79543;");
                    } else if (param.getName().startsWith("Gross Profit")) {
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
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.selectAll();
        textField.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        processEdit();
                    }
                });
    }

    private void processEdit() {
        String text = textField.getText();
        Parameter<?> param = getTableRow().getItem();
        if (param.getJanuary() instanceof Integer) {
            if (text.equals("")) {
                commitEdit(0);
            } else {
                commitEdit(Integer.parseInt(text));
            }
        } else if (param.getJanuary() instanceof BigDecimal) {
            commitEdit(new BigDecimal(text));
        } else {
            commitEdit(text);
        }
    }

    private String getString() {
        if (getItem() instanceof BigDecimal) {
            return getItem() == null ? "" : ((BigDecimal) getItem()).setScale(2, RoundingMode.HALF_UP).toString();
        }
        return getItem() == null ? "" : getItem().toString();
    }

    @Override
    public void commitEdit(Object item) {
        // This block is necessary to support commit on losing focus, because
        // the baked-in mechanism sets our editing state to false before we can
        // intercept the loss of focus. The default commitEdit(...) method
        // simply bails if we are not editing...
        if (!isEditing() && !item.equals(getItem())) {
            TableView<Parameter<?>> table = getTableView();
            if (table != null) {
                TableColumn<Parameter<?>, Object> column = getTableColumn();
                TableColumn.CellEditEvent<Parameter<?>, Object> event = new TableColumn.CellEditEvent<>(table,
                        new TablePosition<Parameter<?>, Object>(table, getIndex(), column),
                        TableColumn.editCommitEvent(), item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);
    }
}