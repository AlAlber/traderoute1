package com.traderoute.cells;

import com.traderoute.data.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.*;

import java.util.regex.Pattern;

public class IntEditCell extends TableCell<Product, Integer> {
    private String pre;
    private String post;
    private TextField textField;
    private final Pattern intPattern = Pattern.compile("-?\\d+");
    private final Pattern bigDecimalPattern = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

    public IntEditCell(String pre, String post) {

        this.pre = pre;
        this.post = post;

    }

    public TextField getTextField() {
        return textField;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            // if (getTableRow().getIndex()==5 || getTableRow().getIndex()==8){
            // return; //bail on empty rows
            // }
            TableColumn<Product, ?> column = getTableColumn();
            // int colIndex = getTableView().getColumns().indexOf(column); // BAIL for first line when getting past
            // first value
            // if (getTableRow().getIndex()==0 && colIndex>1){
            // return;
            // }
            createTextField();
            Product product = getTableRow().getItem();
            if (!column.isEditable()) {
                return;
            }
            // if (getItem() instanceof BigDecimal) {
            // textField.setTextFormatter(new TextFormatter(firstTableController.getDoubleInputConverter(),
            // Double.valueOf(textField.getText()), firstTableController.getDoubleInputFilter()));
            //// }
            // if (getItem() instanceof Integer) {
            textField.textProperty().addListener(new ChangeListener<String>() {
                private boolean changing;

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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

            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.setOnAction(evt -> processEdit()); // react to enter
            // }
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        // if (getTableRow().getIndex()==5 || getTableRow().getIndex()==8){
        // return;
        // }
        // Product product = getTableRow().getItem();// If i dont do this it removes the pre
        setText(getItem().toString());

        setGraphic(null);
    }

    @Override
    public void updateItem(Integer item, boolean empty) {

        super.updateItem(item, empty);
        // if (getTableRow().getIndex()==5 || getTableRow().getIndex()==8){
        // return;
        //// }
        // Product product = getTableRow().getItem();
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItem().toString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setTooltip(new Tooltip(getString()));
            }
            setGraphic(null);
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
        }
    }

    private void createTextField() {
        textField = new TextField(getItem().toString());
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
        Product product = getTableRow().getItem();
        // if (getItem() instanceof Integer){
        // if (text.equals("")){
        // commitEdit(0);
        // }else {
        // commitEdit(Integer.parseInt(text));
        // }
        // }else
        // if (getItem() instanceof BigDecimal){
        // commitEdit(new BigDecimal(text));
        // }
        // else {
        commitEdit(Integer.valueOf(text));
        // }
    }

    private String getString() {
        // if (getItem() instanceof BigDecimal) {
        // return getItem() == null ? "" :pre +(getItem()).setScale(2, RoundingMode.HALF_UP).toString() +post;
        // }
        return getItem() == null ? "" : pre + getItem().toString() + post;
    }

    @Override
    public void commitEdit(Integer item) {
        // This block is necessary to support commit on losing focus, because
        // the baked-in mechanism sets our editing state to false before we can
        // intercept the loss of focus. The default commitEdit(...) method
        // simply bails if we are not editing...
        if (!isEditing() && !item.equals(getItem())) {
            TableView<Product> table = getTableView();
            if (table != null) {
                TableColumn<Product, Integer> column = getTableColumn();
                TableColumn.CellEditEvent<Product, Integer> event = new TableColumn.CellEditEvent<>(table,
                        new TablePosition<Product, Integer>(table, getIndex(), column), TableColumn.editCommitEvent(),
                        item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);
    }
}
