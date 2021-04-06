package com.traderoute;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class AssortmentController implements Initializable {
    @FXML
    private TableView<Meeting> meetingTableView;
    @FXML
    private TableColumn<Meeting, String> descriptionColumn;
    @FXML
    private TableColumn<Meeting, String> locationColumn;
    @FXML
    private TableColumn<Meeting, String> timeColumn;
    @FXML
    private TableColumn<Meeting, LocalDate> dateColumn;
    @FXML
    private TableColumn<Meeting, String> notesColumn;

    @FXML
    private TableView<Sku> skuTableView;
    @FXML
    private TableColumn<Sku,String> flavorDescriptionColumn;
    @FXML
    private TableColumn<Sku, String> statusColumn;
    @FXML
    private TableColumn<Sku,String> skuNotesColumn;


    @FXML
    private TextField descriptionField;
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
//    private JFXTextField jfxtest;

    private SimpleObjectProperty<Retailer> retailer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retailer = new SimpleObjectProperty<>();

        setCellValueFactories();

        meetingTableView.setItems(getExampleMeetings());
        meetingTableView.setEditable(true);
        dateColumn.setEditable(false);

        skuTableView.setItems(getExampleSkus());
        skuTableView.setEditable(true);

        setCellFactories();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            timeField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        meetingTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        skuTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<String> skuStatuses = FXCollections.observableArrayList();
        skuStatuses.addAll("Current", "Targeted","Discontinued");
        statusBox.setItems(skuStatuses);

    }


    public void changeDescriptionCellEvent(TableColumn.CellEditEvent editedCell) {
        Meeting selectedMeeting = meetingTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setDescription(editedCell.getNewValue().toString());
    }
    public void changeLocationCellEvent(TableColumn.CellEditEvent editedCell) {
        Meeting selectedMeeting = meetingTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setLocation(editedCell.getNewValue().toString());
    }
    public void changeTimeCellEvent(TableColumn.CellEditEvent editedCell) {
        Meeting selectedMeeting = meetingTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setTime(editedCell.getNewValue().toString());
    }
    public void changeNotesCellEvent(TableColumn.CellEditEvent editedCell) {
        Meeting selectedMeeting = meetingTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setNotes(editedCell.getNewValue().toString());
    }

    public void changeFlavorDescriptionCellEvent(TableColumn.CellEditEvent editedCell) {
        Sku selectedMeeting = skuTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setFlavorDescription(editedCell.getNewValue().toString());
    }
    public void changeStatusCellEvent(TableColumn.CellEditEvent editedCell) {
        Sku selectedMeeting = skuTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setStatus(editedCell.getNewValue().toString());
    }
    public void changeSkuNotesCellEvent(TableColumn.CellEditEvent editedCell) {
        Sku selectedMeeting = skuTableView.getSelectionModel().getSelectedItem();
        selectedMeeting.setSkuNotes(editedCell.getNewValue().toString());
    }

    public String getDescription(){
        if(descriptionField.getText()==null){
            return "";
        }
        return descriptionField.getText();
    }
    public LocalDate getDate(){
//        if(convertToDate(datePicker.getValue())==null){
//            return convertToDate(LocalDate.of(2000,10,5));
        if(datePicker.getValue()==null){
            LocalDate date = LocalDate.of(1999, 12, 12);
            System.out.println("no correct date picked");
            return date;
        }
        return datePicker.getValue();
    }
    public String getTime(){
        if (timeField.getText()==null){
            return "";
        }
        return timeField.getText();
    }
    public String getLocation(){
        if(locationField.getText()==null){
            return "";
        }
        return locationField.getText();
    }
    public String getNotes(){
        if(notesField.getText()==null){
            return "";
        }
        return notesField.getText();
    }

    public void addMeetingEvent(ActionEvent event){
        Meeting newMeeting = new Meeting();
        newMeeting.setDescription(getDescription());
        newMeeting.setLocation(getLocation());
        newMeeting.setDate(getDate());
        newMeeting.setTime(getTime());
        newMeeting.setNotes(getNotes());
        meetingTableView.getItems().add(newMeeting);
        descriptionField.setText("");
        descriptionField.setPromptText("Description");
        locationField.setText("");
        locationField.setPromptText("Location");
        // do something here
        datePicker.setPromptText("Select a Date");
        timeField.setText("");
        timeField.setPromptText("00:00");
        notesField.setText("");
        notesField.setPromptText("Notes");
        meetingTableView.refresh();
    }
    public void addSkuEvent(ActionEvent event){
        Sku newSku = new Sku();
        newSku.setFlavorDescription(getFlavorDescription());
        newSku.setStatus(getStatus());
        newSku.setSkuNotes(getSkuNotes());
        skuTableView.getItems().add(newSku);
        flavorDescriptionField.setText("");
        flavorDescriptionField.setPromptText("Flavor Description");
        skuNotesField.setText("");
        skuNotesField.setPromptText("Notes");
        skuTableView.refresh();
    }
    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        FXMLLoader secondTableLoader = App.createFXMLLoader("secondTable");
        App.setSceneRoot(secondTableLoader.load());

        firstTableController firstTableController =secondTableLoader.getController();
        firstTableController.setRetailer(getRetailer());
    }


    @FXML
    private void switchToPricingPromotion(ActionEvent event) throws IOException {
        FXMLLoader pricingPromotionLoader = App.createFXMLLoader("pricingPromotion");
        App.setSceneRoot(pricingPromotionLoader.load());

        PricingPromotionController pricingPromotionController =pricingPromotionLoader.getController();
        pricingPromotionController.setRetailer(getRetailer());
    }

    public void deleteSkuEvent(ActionEvent event){
        ObservableList<Sku> selectedRows, allSkus;
        allSkus = skuTableView.getItems();

        //this gives us the rows that were selected
        selectedRows = skuTableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Sku sku: selectedRows) {
            allSkus.remove(sku);
        }
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    private String getFlavorDescription() {
        if(flavorDescriptionField.getText()==null){
            return "";
        }
        return flavorDescriptionField.getText();
    }
    private String getStatus() {
        if(statusBox.getSelectionModel().getSelectedItem()==null){
            return "";
        }
        return statusBox.getSelectionModel().getSelectedItem().toString();
    }
    private String getSkuNotes(){
        if(skuNotesField.getText()==null){
            return "";
        }
        return skuNotesField.getText();
    }

    public void deleteMeetingEvent(ActionEvent event){
        ObservableList<Meeting> selectedRows, allMeetings;
        allMeetings = meetingTableView.getItems();

        //this gives us the rows that were selected
        selectedRows = meetingTableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Meeting person: selectedRows) {
            allMeetings.remove(person);
        }

    }

    public ObservableList<Meeting> getExampleMeetings() {
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        meetings.add(new Meeting ("First Meeting", "At Home", LocalDate.of(2022,12,5), "17:05", "gonna be cool"));
        return meetings;
    }
    public ObservableList<Sku> getExampleSkus() {
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        skus.add(new Sku ("First Sku", "Current", "love this one"));
        return skus;
    }
    private static Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private void setCellFactories() {
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        flavorDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Current","Targeted","Discontinued"));
        skuNotesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void setCellValueFactories() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Meeting, LocalDate>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Meeting,String>("time"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("notes"));

        flavorDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Sku, String>("flavorDescription"));
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        skuNotesColumn.setCellValueFactory(new PropertyValueFactory<Sku, String>("skuNotes"));
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
        RetailerProduct currentRetailerProduct =retailer.getRetailerProducts().get(retailer.getCurrentRetailerProductIndex());
        meetingTableView.setItems(currentRetailerProduct.getMeetings());
        skuTableView.setItems(currentRetailerProduct.getSkus());
        // implement something for general notes

    }
}
