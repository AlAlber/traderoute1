package com.traderoute.controllers;

import com.traderoute.*;
import com.traderoute.data.Meeting;
import com.traderoute.data.Retailer;
import com.traderoute.data.Sku;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AssortmentController implements Initializable, MyController {
    @FXML
    private TableView<Meeting> meetingTable;
    @FXML
    private TableColumn<Meeting, String> meetingDescriptionColumn;
    @FXML
    private TableColumn<Meeting, String> locationColumn;
    @FXML
    private TableColumn<Meeting, String> timeColumn;
    @FXML
    private TableColumn<Meeting, LocalDate> dateColumn;
    @FXML
    private TableColumn<Meeting, String> notesColumn;

    @FXML
    private TableView<Sku> skuTable;
    @FXML
    private TableColumn<Sku, String> flavorDescriptionColumn;
    @FXML
    private TableColumn<Sku, StringProperty> statusColumn;
    @FXML
    private TableColumn<Sku, String> skuNotesColumn;

    @FXML
    private TextField meetingDescriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField notesField;
    @FXML
    private TextField timeField;

    @FXML
    private TextField flavorDescriptionField;
    @FXML
    private ComboBox statusBox;
    @FXML
    private TextField skuNotesField;

    @FXML
    // private JFXTextField jfxtest;

    private SimpleObjectProperty<Retailer> retailer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retailer = new SimpleObjectProperty<>();

        setCellValueFactories();

        meetingTable.setItems(getExampleMeetings()); // TODO change to retailerproduct.getMeetings
        meetingTable.setEditable(true);
        dateColumn.setEditable(false);

        skuTable.setItems(getExampleSkus());
        skuTable.setEditable(true);

        setCellFactories();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            timeField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        meetingTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        skuTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<String> skuStatuses = FXCollections.observableArrayList();
        skuStatuses.addAll("Current", "Targeted", "Discontinued");
        statusBox.setItems(skuStatuses);

    }

    public Meeting getFocusedMeeting(){
        return meetingTable.getSelectionModel().getSelectedItem();
    }
    public Sku getFocusedSku(){
        return skuTable.getSelectionModel().getSelectedItem();
    }

    public void changeDescriptionCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedMeeting().setMeetingDescription(editedCell.getNewValue().toString());
    }

    public void changeLocationCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedMeeting().setLocation(editedCell.getNewValue().toString());
    }

    public void changeTimeCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedMeeting().setTime(editedCell.getNewValue().toString());
    }

    public void changeNotesCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedMeeting().setNotes(editedCell.getNewValue().toString());
    }

    public void changeFlavorDescriptionCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedSku().setFlavorDescription(editedCell.getNewValue().toString());
    }

    public void changeStatusCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedSku().setStatus(editedCell.getNewValue().toString());
    }

    public void changeSkuNotesCellEvent(TableColumn.CellEditEvent editedCell) {
        getFocusedSku().setSkuNotes(editedCell.getNewValue().toString());
    }

    public String getDescription() {
        return meetingDescriptionField.getText() == null ? "" : meetingDescriptionField.getText();
    }

    public LocalDate getDate() {
        if (datePicker.getValue() == null) {
            LocalDate date = LocalDate.of(0, 0, 0);
            System.out.println("no correct date picked");
            return date;
        }
        return datePicker.getValue();
    }

    public String getTime() {
        return timeField.getText() == null ? "" : timeField.getText();
    }

    public String getLocation() {
        return locationField.getText() == null ? "" : locationField.getText();
    }

    public String getNotes() {
        return notesField.getText() == null ? "" : notesField.getText();
    }

    public void addMeetingEvent(ActionEvent event) {
        Meeting newMeeting = new Meeting();
        newMeeting.setMeetingDescription(getDescription());
        newMeeting.setLocation(getLocation());
        newMeeting.setDate(getDate());
        newMeeting.setTime(getTime());
        newMeeting.setNotes(getNotes());
        meetingTable.getItems().add(newMeeting);
        meetingDescriptionField.setText("");
        meetingDescriptionField.setPromptText("Description");
        locationField.setText("");
        locationField.setPromptText("Location");
        // do something here
        datePicker.setPromptText("Select a Date");
        timeField.setText("");
        timeField.setPromptText("00:00");
        notesField.setText("");
        notesField.setPromptText("Notes");
        meetingTable.refresh();
    }

    public void addSkuEvent(ActionEvent event) {
        Sku newSku = new Sku();
        newSku.setFlavorDescription(getFlavorDescription());
        newSku.setStatus(getStatus());
        newSku.setSkuNotes(getSkuNotes());
        skuTable.getItems().add(newSku);
        flavorDescriptionField.setText("");
        flavorDescriptionField.setPromptText("Flavor Description");
        skuNotesField.setText("");
        skuNotesField.setPromptText("Notes");
        skuTable.refresh();
    }

    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        FXMLLoader secondTableLoader = App.createFXMLLoader("secondTable");
        App.setSceneRoot(secondTableLoader.load());

        RTMPlanningController firstTableController = secondTableLoader.getController();
        firstTableController.setRetailer(getRetailer());
    }

    @FXML
    private void switchToPricingPromotion(ActionEvent event) throws IOException {
        FXMLLoader pricingPromotionLoader = App.createFXMLLoader("pricingPromotion");
        App.setSceneRoot(pricingPromotionLoader.load());

        PricingPromotionController pricingPromotionController = pricingPromotionLoader.getController();
        pricingPromotionController.setRetailer(getRetailer());
    }

    public void deleteSkuEvent(ActionEvent event) {
        ObservableList<Sku> selectedRows, allSkus;
        allSkus = skuTable.getItems();

        // this gives us the rows that were selected
        selectedRows = skuTable.getSelectionModel().getSelectedItems();

        // loop over the selected rows and remove the Person objects from the table
        for (Sku sku : selectedRows) {
            allSkus.remove(sku);
        }
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public String getFlavorDescription() {
        return flavorDescriptionField.getText() == null ? "" : flavorDescriptionField.getText();
    }

    public String getStatus() {
        String status = statusBox.getSelectionModel().getSelectedItem().toString();
        return status == null ? "" : status;
    }

    public String getSkuNotes() {
        return skuNotesField.getText() == null ? "" : skuNotesField.getText();
    }

    public void deleteMeetingEvent(ActionEvent event) {
        ObservableList<Meeting> selectedRows, allMeetings;
        allMeetings = meetingTable.getItems();

        // this gives us the rows that were selected
        selectedRows = meetingTable.getSelectionModel().getSelectedItems();

        // loop over the selected rows and remove the Person objects from the table
        for (Meeting person : selectedRows) {
            allMeetings.remove(person);
        }

    }

    public ObservableList<Meeting> getExampleMeetings() {
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        meetings.add(new Meeting("First Meeting", "At Home", LocalDate.of(2022, 12, 5), "17:05", "gonna be cool"));
        return meetings;
    }

    public ObservableList<Sku> getExampleSkus() {
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        skus.add(new Sku("First Sku", "Current", "love this one"));
        skus.add(new Sku("Second Sku", "Targeted", "love this one"));
        return skus;

    }

    private static Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    int[] iarr = {0};
    private void setCellFactories() {
        meetingDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        flavorDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setCellFactory(col -> {
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
        skuNotesColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    private void setCellValueFactories() {
        meetingDescriptionColumn.setCellValueFactory(celldata -> celldata.getValue().meetingDescriptionProperty());
        locationColumn.setCellValueFactory(celldata -> celldata.getValue().locationProperty());
        dateColumn.setCellValueFactory(celldata -> celldata.getValue().dateProperty());
        timeColumn.setCellValueFactory(celldata -> celldata.getValue().timeProperty());
        notesColumn.setCellValueFactory(celldata -> celldata.getValue().notesProperty());

        flavorDescriptionColumn.setCellValueFactory(celldata -> celldata.getValue().flavorDescriptionProperty());
        statusColumn.setCellValueFactory(i -> {
            final StringProperty value = i.getValue().statusProperty();
            // binding to constant value
            return Bindings.createObjectBinding(() -> value);
        });
        skuNotesColumn.setCellValueFactory(celldata -> celldata.getValue().skuNotesProperty());
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
//        RetailerProduct currentRetailerProduct = retailer.getRetailerProducts()
//                .get(retailer.getCurrentRetailerProductIndex());
//        meetingTable.setItems(currentRetailerProduct.getMeetings());
//        skuTable.setItems(currentRetailerProduct.getSkus());
        // implement something for general notes
    }
}
