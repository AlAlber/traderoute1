package com.traderoute.cells;

import com.traderoute.BigDecimalTextField;
import com.traderoute.DoubleInputConverter;
import com.traderoute.IntegerTextField;
import com.traderoute.NumberTextField;
import com.traderoute.data.RTMOptionBuilder;
import com.traderoute.data.RetailerProduct;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.*;

import javax.sound.midi.SysexMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public abstract class NumberEditCell1 extends TableCell
    {
        private NumberTextField textField;
        private String pre = "";
        private String post = "";
        private Number maxValue;
        private Number minValue;
        private Number defaultValue;

    public NumberEditCell1(String pre, String post, Number defaultValue, Number minValue, Number maxValue) {
        this.pre = pre;
        this.post = post;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
//    public NumberEditCell1 pre(String pre) {
//        this.pre = pre;
//        return this;
//    }
//    public NumberEditCell1 post(String post) {
//        this.post = post;
//        return this;
//    }
    public NumberEditCell1 defaultValue(String post) {
        this.post = post;
        return this;
    }


    public NumberTextField getTextField() {
        return textField;
    }

        @Override
        public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            TableColumn<?, ?> column = getTableColumn();
            createTextField();
            if (!column.isEditable()) {
                return;
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
        //TODO add this in subclass
//        if (getTableRow().getIndex() == 5 || getTableRow().getIndex() == 8) {
//            return;
//        }
        // Product product = getTableRow().getItem();// If i dont do this it removes the pre
        setText(getString(pre, post));

        setGraphic(null);
    }

        @Override
        public void updateItem(Object item, boolean empty) {

        super.updateItem(item, empty);
        // TODO Add in subclass
//        if (getTableRow().getIndex() == 5 || getTableRow().getIndex() == 8) {
//            return;
//        }
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
                setText(getString(pre, post));
                setTooltip(new Tooltip(getString(pre, post)));
            }
            setGraphic(null);
        }
    }

    private void createTextField() {
        textField = getSpecificTextField(defaultValue, minValue, maxValue);
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.selectAll();
        textField.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        processEdit();
                    }
                });
    }
    public abstract NumberTextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue);

    private void processEdit() {
        String text = textField.getText();
        if (text.equals("")) {
            commitEdit(getZero());
        }
        commitNumberEdit(text);
    }
    public abstract void commitNumberEdit(String text);

    public abstract Number getZero();

    public abstract String getString(String pre, String post);

        @Override
        public void commitEdit(Object item) {
        // This block is necessary to support commit on losing focus, because
        // the baked-in mechanism sets our editing state to false before we can
        // intercept the loss of focus. The default commitEdit(...) method
        // simply bails if we are not editing...
        if (!isEditing() && !item.equals(getItem())) {
            TableView<Object> table = getTableView();
            if (table != null) {
                TableColumn<Object, Object> column = getTableColumn();
                TableColumn.CellEditEvent<Object, Object> event = new TableColumn.CellEditEvent<>(table,
                        new TablePosition<Object, Object>(table, getIndex(), column), TableColumn.editCommitEvent(),
                        item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);
    }

}
