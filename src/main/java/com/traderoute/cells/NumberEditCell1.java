package com.traderoute.cells;

import com.traderoute.BigDecimalTextField;
import com.traderoute.DoubleInputConverter;
import com.traderoute.IntegerTextField;
import com.traderoute.NumberTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public abstract class NumberEditCell1<Object, Number> extends TableCell<Object, Number>
    {
        private String pre;
        private String post;
        private NumberTextField textField;
        private Number maxValue;
        private Number minValue;
        private Number defaultValue;
        private final Pattern intPattern = Pattern.compile("-?\\d+");
        private final Pattern bigDecimalPattern = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

    public NumberEditCell1(String pre, String post, Number defaultValue, Number minValue, Number maxValue) {
        this.pre = pre;
        this.post = post;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
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
        setText(getItem().toString());

        setGraphic(null);
    }

        @Override
        public void updateItem(Number item, boolean empty) {

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
                setText(getString());
                setTooltip(new Tooltip(getString()));
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
        commitNumberEdit();
        //TODO add in subclass
//        if (getItem() instanceof Integer) {
//            commitEdit((Number) Integer.valueOf(text));
//        } else if (getItem() instanceof BigDecimal) {
//            commitEdit((Number) new BigDecimal(text));
//        }
    }
    public abstract void commitNumberEdit();

    public abstract Number getZero();

    public abstract String getString();
//    {
//        if (getItem() instanceof BigDecimal) {
//            return getItem() == null ? ""
//                    : pre + ((BigDecimal) getItem()).setScale(2, RoundingMode.HALF_UP).toString() + post;
//        }
//        return getItem() == null ? "" : pre + getItem().toString() + post;
//    }

        @Override
        public void commitEdit(Number item) {
        // This block is necessary to support commit on losing focus, because
        // the baked-in mechanism sets our editing state to false before we can
        // intercept the loss of focus. The default commitEdit(...) method
        // simply bails if we are not editing...
        if (!isEditing() && !item.equals(getItem())) {
            TableView<Object> table = getTableView();
            if (table != null) {
                TableColumn<Object, Number> column = getTableColumn();
                TableColumn.CellEditEvent<Object, Number> event = new TableColumn.CellEditEvent<>(table,
                        new TablePosition<Object, Number>(table, getIndex(), column), TableColumn.editCommitEvent(),
                        item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);
    }

}
