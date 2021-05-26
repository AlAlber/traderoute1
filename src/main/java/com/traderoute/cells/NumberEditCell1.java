package com.traderoute.cells;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.*;

public abstract class NumberEditCell1 extends TableCell
    {
        private TextField textField;
//        private String pre = "";
//        private String post = "";
//        private Number maxValue;
//        private Number minValue;
//        private Number defaultValue;
        private CellSpecs _specs = StdSpecs.INTPOS6$.getSpecs();

//    public NumberEditCell1(CellSpecs specs) {
////        String pre, String post, Number defaultValue, Number minValue, Number maxValue
////        this.pre = pre;
////        this.post = post;
////        this.defaultValue = defaultValue;
////        this.minValue = minValue;
////        this.maxValue = maxValue;
//        this.specs = specs;
//    }

    public NumberEditCell1(CellSpecs specs) {
        this._specs = specs;
    }
    public NumberEditCell1(){
    }


        //    public NumberEditCell1 pre(String pre) {
//        this.pre = pre;
//        return this;
//    }
//    public NumberEditCell1 post(String post) {
//        this.post = post;
//        return this;
//    }
//    public NumberEditCell1 defaultValue(String post) {
//        this.post = post;
//        return this;
//    }
    public CellSpecs get_specs(){
        return _specs;
    }


    public TextField getTextField() {
        return textField;
    }

//    public NumberEditCell1 specs(CellSpecs _specs){
//        this._specs = _specs;
//        return this;
//    }


    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            TableColumn<?, ?> column = getTableColumn();
            createTextField();
            if (!column.isEditable()) {
                return;
            }
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
        setText(getString(get_specs()));

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
//            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItem().toString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString(get_specs()));
                setTooltip(new Tooltip(getString(get_specs())));
            }
            setGraphic(null);
        }
    }


    private void createTextField() {
        textField = getSpecificTextField(get_specs().getDefaultValue(),
                get_specs().getMinValue(), get_specs().getMaxValue());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.selectAll();
        textField.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        processEdit();
                    }
                });
    }
    public abstract TextField getSpecificTextField(Number defaultValue, Number minValue, Number maxValue);

    private void processEdit() {
        String text = textField.getText();
        if (text.equals("")) {
            commitEdit(getZeroOrEmptyString());
        }
        commitValueEdit(text);
    }
    public abstract void commitValueEdit(String text);

    public abstract Object getZeroOrEmptyString();

    public abstract String getString(CellSpecs specs);

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
