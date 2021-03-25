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

import static com.traderoute.AssortmentController.convertToDate;
import static javafx.beans.binding.Bindings.add;

public class RetailerSelectionController implements Initializable {

    @FXML
    private TextField retailerSelectField;

//    private String[] retailerStrings = {"Hey", "What the hell", "Hello"};

    private SuggestionProvider<String> suggestionProvider;

    @FXML
    private List<String> retailerStrings = new ArrayList<>();
    @FXML
    private JFXListView<Retailer> retailerList;

    ObservableList<Retailer> observableRetailerList = getRetailers();
    FilteredList<Retailer> filterItems = new FilteredList<>(observableRetailerList);

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
    private TableColumn<Meeting, Date> dateColumn;
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

        addButton.setOnAction(e-> {
            String retailerName = RetailerNamePopup.display("Add a new Retailer", "Type the Name of the Retailer you'd like to add");
            Retailer retailer = new Retailer();
            retailer.setRetailerName(retailerName);
            observableRetailerList.add(retailer);
        });
        changeButton.setOnAction(e-> {
            Retailer retailer = retailerList.getSelectionModel().getSelectedItem();
            System.out.println(retailer.getRetailerName());
            String retailerName = RetailerNamePopup.display("Change the Name of a Retailer", "Type the new name of this Retailer");
            if (!retailerName.equals("")) {
                retailer.setRetailerName(retailerName);
            }
            System.out.println(retailerName);
            retailerList.refresh();
        });



        descriptionColumn.setCellValueFactory(cellData-> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData-> cellData.getValue().locationProperty());
        dateColumn.setCellValueFactory(cellData-> cellData.getValue().dateProperty());
        timeColumn.setCellValueFactory(cellData-> cellData.getValue().timeProperty());
        notesColumn.setCellValueFactory(cellData-> cellData.getValue().notesProperty());

        brandNameColumn.setCellValueFactory(cellData-> cellData.getValue().brandNameProperty());
        productClassColumn.setCellValueFactory(cellData-> cellData.getValue().productClassProperty());

        descriptionColumn.setCellFactory(tc-> new CustomTextCell<>());
        locationColumn.setCellFactory(tc-> new CustomTextCell<>());
        dateColumn.setCellFactory(tc-> new CustomTextCell<>());
        timeColumn.setCellFactory(tc-> new CustomTextCell<>());
        notesColumn.setCellFactory(tc-> new CustomTextCell<>());

        brandNameColumn.setCellFactory(tc-> new CustomTextCell<>());
        productClassColumn.setCellFactory(tc-> new CustomTextCell<>());


        retailerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Retailer>() {
            @Override
            public void changed(ObservableValue<? extends Retailer> observable, Retailer oldRetailer, Retailer newRetailer) {
                retailerNameLabel.setText(newRetailer.getRetailerName());
                yearOneStoreCountLabel.setText("Year One Store Count = $"+ newRetailer.getYearOneStoreCount());
                everydayGpmLabel.setText("Everyday GPM = "+ newRetailer.getEverydayGPM().toString() + "%");
                spoilsFeesLabel.setText("Spoils & Fees = " + newRetailer.getSpoilsFees().toString() + "%");

                currentRetailer.set(newRetailer);

                ObservableList<Meeting> allMeetings = FXCollections.observableArrayList();
                ObservableList<Product> correspondingProducts = FXCollections.observableArrayList();
                int totalCommitted =0;
                for (RetailerProduct retailerProduct: newRetailer.getRetailerProducts()) {
                    for (PromoPlan promoPlan : retailerProduct.getPromoPlans()){
                        if (promoPlan.getCommitted()){
                            totalCommitted++;
                        }
                    }
                    for (Meeting meeting : retailerProduct.getMeetings()){
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

    private ObservableList<Retailer> getRetailers(){
        ObservableList<Retailer> retailers = FXCollections.observableArrayList();
        retailers.add(new Retailer("Ahold Giant",getRetailerProducts(),0, new BigDecimal("40"), 158, new BigDecimal("3.0")));
        retailers.add(new Retailer("Ahold Small",getDifferentRetailerProducts(),1, new BigDecimal("40"), 183, new BigDecimal("3.0")));
//        retailers.add(new Retailer());
        return retailers;
    }
    @FXML
    private void switchToAssortment(ActionEvent event) throws IOException {
    }
    @FXML
    private void switchToPricingPromotion(ActionEvent event) throws IOException {
    }
    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        if (currentRetailer!=null) {
            FXMLLoader secondTableLoader = App.createFXMLLoader("secondTable");
            App.setSceneRoot(secondTableLoader.load());

            firstTableController firstTableController = secondTableLoader.getController();
            firstTableController.setRetailer(currentRetailer.get());
        }
    }

    /*
    Loads dummy table data
    */
    public static ObservableList<RTMOption> getRTMOptions() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOption("Direct-to-Customer", new BigDecimal("0.29"),BigDecimal.valueOf(7500), BigDecimal.valueOf(3.59),
                BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        testOption.setResultingEverydayRetailOverride(new BigDecimal("5.99"));
        testOption.setLandedStoreCost(new BigDecimal("3.59"));
        RTMOptions.add(testOption);
        RTMOption optionTwo = new RTMOption("Option2", new BigDecimal("0.0"),BigDecimal.valueOf(3500), BigDecimal.valueOf(3.07),
                BigDecimal.valueOf(3.75), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("6.49"));
        optionTwo.setResultingEverydayRetailCalcd(new BigDecimal("6.32"));
        optionTwo.setLandedStoreCost(new BigDecimal("3.79"));
        RTMOptions.add(optionTwo);
        RTMOption optionThree = new RTMOption();
        optionThree.setRTMName("Option3");
        RTMOptions.add(optionThree);
        RTMOption optionFour = new RTMOption();
        optionFour.setRTMName("Option4");
        RTMOptions.add(optionFour);
        return RTMOptions;
    }
    /*
    Loads dummy table data
    */
    public static ObservableList<RTMOption> getDifferentRTMOptions2() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOption("Direct-to-Customer", new BigDecimal("0.14"),BigDecimal.valueOf(5000), BigDecimal.valueOf(1.49),
                BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        testOption.setResultingEverydayRetailOverride(new BigDecimal("2.49"));
        testOption.setResultingEverydayRetailCalcd(new BigDecimal("2.48"));
        testOption.setLandedStoreCost(new BigDecimal("1.49"));
        RTMOptions.add(testOption);
        RTMOption optionTwo = new RTMOption("Direct-to-KeHE Model", new BigDecimal("0.14"),BigDecimal.valueOf(2500), BigDecimal.valueOf(1.49),
                BigDecimal.valueOf(1.69), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("2.99"));
        optionTwo.setResultingEverydayRetailCalcd(new BigDecimal("2.82"));
        optionTwo.setLandedStoreCost(new BigDecimal("1.69"));
        RTMOptions.add(optionTwo);
        RTMOption optionThree = new RTMOption("KeHE F.O.B. Model", new BigDecimal("0.0"),BigDecimal.valueOf(2500), BigDecimal.valueOf(1.35),
                BigDecimal.valueOf(1.79), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("2.99"));
        optionTwo.setResultingEverydayRetailCalcd(new BigDecimal("2.98"));
        optionTwo.setLandedStoreCost(new BigDecimal("1.79"));
        RTMOptions.add(optionThree);
        RTMOption optionFour = new RTMOption();
        optionFour.setRTMName("Option 4");
        RTMOptions.add(optionFour);
        return RTMOptions;
    }

    public static ObservableList<RTMOption> getDifferentRTMOptions() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOption("Direct-to-Customer", new BigDecimal("0.29"),BigDecimal.valueOf(5000), BigDecimal.valueOf(3.59),
                BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        testOption.setResultingEverydayRetailOverride(new BigDecimal("5.99"));
        testOption.setLandedStoreCost(new BigDecimal("3.59"));
        RTMOptions.add(testOption);
        RTMOption optionTwo = new RTMOption("Direct-to-KeHE", new BigDecimal("0.29"),BigDecimal.valueOf(2500), BigDecimal.valueOf(3.59),
                BigDecimal.valueOf(4.19), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        optionTwo.setResultingEverydayRetailOverride(new BigDecimal("6.99"));
        optionTwo.setResultingEverydayRetailCalcd(new BigDecimal("6.98"));
        optionTwo.setLandedStoreCost(new BigDecimal("4.19"));
        RTMOptions.add(optionTwo);
        RTMOption optionThree = new RTMOption("F.O.B. Model", new BigDecimal("0.0"),BigDecimal.valueOf(2500), BigDecimal.valueOf(3.30),
                BigDecimal.valueOf(4.68), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
        optionThree.setResultingEverydayRetailOverride(new BigDecimal("7.99"));
        optionThree.setResultingEverydayRetailCalcd(new BigDecimal("7.80"));
        optionThree.setLandedStoreCost(new BigDecimal("4.68"));
        RTMOptions.add(optionThree);
        RTMOption optionFour = new RTMOption();
        optionFour.setRTMName("Option4");
        RTMOptions.add(optionFour);
        return RTMOptions;
    }

    /*
    Load dummy RetailerProduct
     */
    public ObservableList<RetailerProduct> getRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts = FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList(getExampleSkus().get(0), getExampleSkus().get(1), getExampleSkus().get(2), getExampleSkus().get(3), getExampleSkus().get(4));
        ObservableList<Meeting>  meetings = FXCollections.observableArrayList();
        skus.addAll(new Sku("dill", "current", "great taste"), new Sku("dill", "current", "great taste"), new Sku("dill", "current", "great taste"));
        meetings.addAll(new Meeting("Review Meeting", "here", convertToDate(LocalDate.of(2022,12,5)), "11:15","will be fun"), new Meeting());
        retailerProducts.add(new RetailerProduct(getExampleProducts().get(0), getRTMOptions(), skus,meetings, getDummyPromoPlans()));
        return retailerProducts;
    }
    /*
    Load dummy RetailerProduct
     */
    public ObservableList<RetailerProduct> getDifferentRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts = FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        ObservableList<Meeting>  meetings = FXCollections.observableArrayList();
        skus.addAll(getExampleSkus().get(4), getExampleSkus().get(5), getExampleSkus().get(6), getExampleSkus().get(7), getExampleSkus().get(8));
        meetings.addAll(getExampleMeetings().get(4),getExampleMeetings().get(5), getExampleMeetings().get(6), getExampleMeetings().get(7));
        retailerProducts.add(new RetailerProduct(getExampleProducts().get(4), getDifferentRTMOptions(), skus,meetings, getDifferentDummyPromoPlans()));

        ObservableList<Sku> skus2 = FXCollections.observableArrayList();
        ObservableList<Meeting>  meetings2 = FXCollections.observableArrayList();
        skus2.addAll(getExampleSkus().get(9), getExampleSkus().get(10), getExampleSkus().get(11));
        meetings2.addAll(getExampleMeetings().get(8),getExampleMeetings().get(9), getExampleMeetings().get(10), getExampleMeetings().get(11));
        retailerProducts.add(new RetailerProduct(getExampleProducts().get(1), getDifferentRTMOptions2(),skus2,meetings2, getDifferentDummyPromoPlans2()));
        return retailerProducts;
    }

    /*
    Loads dummy product data
    */
    public ObservableList<Product> getExampleProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Big Time Food Company", "24 oz pickles", new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("2.05"), new BigDecimal("-1.15")));
        products.add(new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20")));
        products.add(new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20")));
        products.add(new Product("Small Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"), new BigDecimal("0.14"),
                new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"), new BigDecimal("-1.20")));
        products.add(new Product("Small Time Food Company", "32 oz mixes", new BigDecimal("3.59"), new BigDecimal("0.29"),
                new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("1.60"), new BigDecimal("-1.05")));
        return products;
    }

    public ObservableList<Meeting> getExampleMeetings() {
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        meetings.add(new Meeting ("First Meeting", "At Home", convertToDate(LocalDate.of(2022,12,5)), "17:05", "gonna be cool"));
        meetings.add(new Meeting ("Second Meeting", "At Home", convertToDate(LocalDate.of(2021,01,9)), "17:05", "cant wait"));
        meetings.add(new Meeting ("Third Meeting", "At Home", convertToDate(LocalDate.of(2020,02,13)), "17:05", "another one!"));
        meetings.add(new Meeting ("Fourth Meeting", "At Home", convertToDate(LocalDate.of(2022,12,10)), "17:05", "be punctual"));
        meetings.add(new Meeting ("Fifth Meeting", "At Home", convertToDate(LocalDate.of(2022,10,5)), "17:05", "be late"));
        meetings.add(new Meeting ("Sixth Meeting", "At Home", convertToDate(LocalDate.of(2024,12,5)), "17:05", "call first"));
        meetings.add(new Meeting ("Seventh Meeting", "At Home", convertToDate(LocalDate.of(2022,12,5)), "17:45", "email first"));
        meetings.add(new Meeting ("Eigth Meeting", "At Home", convertToDate(LocalDate.of(2022,12,5)), "18:05", "zzz"));

        meetings.add(new Meeting ("Ninth Meeting", "Atnot Home", convertToDate(LocalDate.of(2029,10,5)), "17:05", "be late"));
        meetings.add(new Meeting ("Tenth Meeting", "Atnto Home", convertToDate(LocalDate.of(2022,12,5)), "17:25", "call first"));
        meetings.add(new Meeting ("Eleventh Meeting", "Ate Home", convertToDate(LocalDate.of(2022,12,10)), "17:45", "email first"));
        meetings.add(new Meeting ("Twelveth Meeting", "At v Home", convertToDate(LocalDate.of(2021,1,1)), "18:05", "zzz"));
        return meetings;
    }
    public ObservableList<Sku> getExampleSkus() {
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        skus.add(new Sku ("First Sku", "Current", "love this one"));
        skus.add(new Sku ("Second Sku", "Current", "Not my favorite"));
        skus.add(new Sku ("Third Sku", "Discontinued", "Hate it"));
        skus.add(new Sku ("Fourth Sku", "Targeted", "Hate it"));
        skus.add(new Sku ("Fifth Sku", "Targeted", "Hate it"));
        skus.add(new Sku ("Sixth Sku", "Discontinued", "Hate it"));
        skus.add(new Sku ("Seventh Sku", "Current", "Hate it"));
        skus.add(new Sku ("Eighth Sku", "Current", "Hate it"));
        skus.add(new Sku ("Ninth Sku", "Current", "Hate it"));

        skus.add(new Sku ("Tentth Sku", "Discontinued", "Hate it"));
        skus.add(new Sku ("Twelvth Sku", "Current", ". it"));
        skus.add(new Sku ("13th Sku", "Current", "Lov it"));
        skus.add(new Sku ("14th Sku", "Current", "got it"));
        return skus;
    }

    private static ObservableList getToplineSummaries() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("Net Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("Total Units", new BigDecimal("0.0")));
        return summaries;
    }
    private static ObservableList getRetailerSummaries() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("GPM", new BigDecimal("0.0")));
        summaries.add(new Summary("Avg. Retail", new BigDecimal("0.0")));
        return summaries;
    }
    public static ObservableList<PromoPlan> getDummyPromoPlans(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(),new BigDecimal("0.0"), true));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false ));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false));
        return promoPlans;
    }
    public static ObservableList<PromoPlan> getDifferentDummyPromoPlans(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getDifferentParameters(), getToplineSummaries(), getRetailerSummaries(),new BigDecimal("0.0"), false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),true ));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false));
        return promoPlans;
    }
    public static ObservableList<PromoPlan> getDifferentDummyPromoPlans2(){
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getDifferentParameters(), getToplineSummaries(), getRetailerSummaries(),new BigDecimal("0.0"), false));
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),true ));
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(), new BigDecimal("0.0"),false));
        return promoPlans;
    }

    public static ObservableList<Parameter<?>> getParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("5.0"),new BigDecimal("5.0") ,new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("5.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"),new BigDecimal("6.0"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,2,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("50.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7000.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 158, 158, 158,158,158,158,158,158,158,158,158,158));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("6.49"),new BigDecimal("6.49") ,new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),new BigDecimal("6.49"),false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.89"),new BigDecimal("3.89") ,new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),new BigDecimal("3.89"),false));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("0.91"),new BigDecimal("0.91") ,new BigDecimal("0.93"),new BigDecimal("0.95"),new BigDecimal("1.07"),new BigDecimal("1.27"),new BigDecimal("1.46"),new BigDecimal("1.23"),new BigDecimal("0.86"),new BigDecimal("0.80"),new BigDecimal("0.82"),new BigDecimal("0.86"),false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("5.99"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("5.99"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("40.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("40.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("40.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4,0,0,0,0,4,4,4,0,0,0,4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("2.5"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("2.5"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2.5"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.59"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.59"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("7.7"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("7.7"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("7.7"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR","" ,"","","","4 Week TPR","4 Week TPR","4 Week TPR","","","","4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        return parameters;
    }

    public static ObservableList<Parameter<?>> getDifferentParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("2.0"),new BigDecimal("2.0") ,new BigDecimal("2.0"),new BigDecimal("2.0"),new BigDecimal("2.0"),new BigDecimal("2.0"),new BigDecimal("2.75"),new BigDecimal("2.75"),new BigDecimal("2.75"),new BigDecimal("2.75"),new BigDecimal("2.75"),new BigDecimal("2.75"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,1,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("75.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("2500.00"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 183, 183, 183,183,183,183,183,183,183,183,183,183));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("5.99"),new BigDecimal("5.99") ,new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),new BigDecimal("5.99"),false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.59"),new BigDecimal("3.59") ,new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),new BigDecimal("3.59"),false));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("1.00"),new BigDecimal("0.94") ,new BigDecimal("0.85"),new BigDecimal("0.83"),new BigDecimal("0.99"),new BigDecimal("1.02"),new BigDecimal("0.92"),new BigDecimal("1.04"),new BigDecimal("0.91"),new BigDecimal("0.96"),new BigDecimal("1.14"),new BigDecimal("1.52"),false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("4.99"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("4.99"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("4.99"),new BigDecimal("4.99"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("35.00"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("35.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("35.00"),new BigDecimal("35.00"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4,0,0,0,0,0,0,4,0,0,4,4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("4.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("4.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("4.0"),new BigDecimal("4.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("500"),new BigDecimal("500"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.24"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.24"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("3.24"),new BigDecimal("3.24"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("9.8"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("9.8"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("9.8"),new BigDecimal("9.8"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR","" ,"","","","4 Week TPR","4 Week TPR","4 Week TPR","","","","4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        return parameters;
    }

    public static ObservableList<Parameter<?>> getEmptyParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),false));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "","" ,"","","","","","","","","",""));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        return parameters;
    }


}
