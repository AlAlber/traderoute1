package com.traderoute;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import static javafx.beans.binding.Bindings.add;

public class RetailerSelectionController implements Initializable {

    @FXML
    private TextField retailerSelectField;

    @FXML
    private JFXListView<Retailer> retailerList;

    private ObservableList<Retailer> retailers = FXCollections.observableArrayList();

    ObservableList<Retailer> observableRetailerList ;
    FilteredList<Retailer> filterItems;

    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton changeButton;

    @FXML
    private Label retailerNameLabel;
    @FXML
    private Label yearOneStoreCountLabel;
    @FXML
    private Label everydayGpmLabel;
    @FXML
    private Label spoilsFeesLabel;
    @FXML
    private Label totalPromoPlansLabel;
    @FXML
    private Label totalCommittedPromoPlansLabel;

    @FXML
    private TableView<Meeting> meetingOverviewTable;
    @FXML
    private TableView<Product> productMeetingTable;

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
    private TableColumn<Product, String> brandNameColumn;
    @FXML
    private TableColumn<Product, String> productClassColumn;

    @FXML
    private JFXButton editRetailerButton;

    private SimpleObjectProperty<Retailer> currentRetailer = new SimpleObjectProperty<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        retailerList.setItems(filterItems);

        addButton.setOnAction(e -> {
            String retailerName = RetailerNamePopup.display("Add a new Retailer", "Type the Name of the Retailer you'd like to add");
            Retailer retailer = new Retailer();
            retailer.setRetailerName(retailerName);
            observableRetailerList.add(retailer);
        });
        changeButton.setOnAction(e -> {
            Retailer retailer = retailerList.getSelectionModel().getSelectedItem();
            System.out.println(retailer.getRetailerName());
            String retailerName = RetailerNamePopup.display("Change the Name of a Retailer", "Type the new name of this Retailer");
            if (!retailerName.equals("")) {
                retailer.setRetailerName(retailerName);
            }
            System.out.println(retailerName);
            retailerList.refresh();
        });


        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        notesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());

        brandNameColumn.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
        productClassColumn.setCellValueFactory(cellData -> cellData.getValue().productClassProperty());

        descriptionColumn.setCellFactory(tc -> new CustomTextCell());
        locationColumn.setCellFactory(tc -> new CustomTextCell<>());
        dateColumn.setCellFactory(tc -> new CustomTextCell<>());
        timeColumn.setCellFactory(tc -> new CustomTextCell<>());
        notesColumn.setCellFactory(tc -> new CustomTextCell<>());

        brandNameColumn.setCellFactory(tc -> new CustomTextCell<>());
        productClassColumn.setCellFactory(tc -> new CustomTextCell<>());


        retailerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Retailer>() {
            @Override
            public void changed(ObservableValue<? extends Retailer> observable, Retailer oldRetailer, Retailer newRetailer) {
                retailerNameLabel.setText(newRetailer.getRetailerName());
                yearOneStoreCountLabel.setText("Year One Store Count = $" + newRetailer.getYearOneStoreCount());
                everydayGpmLabel.setText("Everyday GPM = " + newRetailer.getEverydayGPM().toString() + "%");
                spoilsFeesLabel.setText("Spoils & Fees = " + newRetailer.getSpoilsFees().toString() + "%");

                currentRetailer.set(newRetailer);

                ObservableList<Meeting> allMeetings = FXCollections.observableArrayList();
                ObservableList<Product> correspondingProducts = FXCollections.observableArrayList();
                int totalCommitted = 0;
                for (RetailerProduct retailerProduct : newRetailer.getRetailerProducts()) {
                    for (PromoPlan promoPlan : retailerProduct.getPromoPlans()) {
                        if (promoPlan.getCommitted()) {
                            totalCommitted++;
                        }
                    }
                    for (Meeting meeting : retailerProduct.getMeetings()) {
                        allMeetings.add(meeting);
                        meeting.getDate();
                        correspondingProducts.add(retailerProduct.getProduct());
                    }
                }
                meetingOverviewTable.setItems(allMeetings);
                productMeetingTable.setItems(correspondingProducts);

                totalCommittedPromoPlansLabel.setText("Total Committed Promotional Plans = " + totalCommitted);

            }
        });





        retailerList.setCellFactory(param -> new ListCell<Retailer>() {
            @Override
            protected void updateItem(Retailer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getRetailerName() == null) {
                    setText(null);
                } else {
                    setText(item.getRetailerName());
                }
            }
        });
    }

    public ObservableList<Retailer> getRetailers() {
        return retailers;
    }

    public void setRetailers(ObservableList<Retailer> retailers) {
        this.retailers = retailers;
        ObservableList<Retailer> observableRetailerList =retailers;
        filterItems = new FilteredList<>(observableRetailerList);
        retailerList.setItems(filterItems);
        // bind predicate to text filterInput text
        filterItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            String text = retailerSelectField.getText();
            if (text == null || text.isEmpty()) {
                return null;
            } else {
                final String uppercase = text.toUpperCase();
                return (retailer) -> retailer.getRetailerName().toUpperCase().contains(uppercase);
            }
        }, retailerSelectField.textProperty()));
    }


    @FXML
    private void switchToAssortment(ActionEvent event) throws IOException {
    }

    @FXML
    private void switchToPricingPromotion(ActionEvent event) throws IOException {
    }

    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        if (currentRetailer != null) {
            FXMLLoader secondTableLoader = App.createFXMLLoader("secondTable");
            App.setSceneRoot(secondTableLoader.load());

            firstTableController firstTableController = secondTableLoader.getController();
            System.out.println("current Retailer=" + currentRetailer.get().getRetailerName());
            firstTableController.setRetailer(currentRetailer.get());
        }
    }

    @FXML
    private void switchToProductClassReporting(ActionEvent event) throws IOException {
        FXMLLoader productClassReportingLoader = App.createFXMLLoader("productClassReporting");
        App.setSceneRoot(productClassReportingLoader.load());

        ProductClassReportingController productClassReportingController = productClassReportingLoader.getController();
        productClassReportingController.setRetailers(getRetailers());
    }

}