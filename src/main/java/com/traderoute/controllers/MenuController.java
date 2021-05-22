package com.traderoute.controllers;

import com.traderoute.*;
import com.traderoute.data.*;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private static RetailerProduct firstRP;
    private static RetailerProduct secondRP;

    @FXML
    private AnchorPane mainAnchorPane;
    private HostServices hostServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            Window window = mainAnchorPane.getScene().getWindow();
            window.setHeight(mainAnchorPane.getPrefHeight());
            window.setWidth(mainAnchorPane.getPrefWidth());
            window.centerOnScreen();

        });

    }

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    private void switchToCustomerMenu(ActionEvent event) throws IOException {
        FXMLLoader retailerSelectionLoader = App.createFXMLLoader("retailerSelection");
        App.setSceneRoot(retailerSelectionLoader.load());

        RetailerSelectionController retailerSelectionController = retailerSelectionLoader.getController();
        retailerSelectionController.setRetailers(getExampleRetailers());
    }

    @FXML
    public void switchToProductClassReporting(ActionEvent event) throws IOException {
        FXMLLoader productClassReportingLoader = App.createFXMLLoader("productClassReporting");
        App.setSceneRoot(productClassReportingLoader.load());

        ProductClassReportingController productClassReportingController = productClassReportingLoader.getController();
        productClassReportingController.setHostServices(getHostServices());
        productClassReportingController.setRetailers(getExampleRetailers());

    }

    @FXML
    public void switchToSalesProSelection(ActionEvent event) throws IOException {
        FXMLLoader salesProSelectionLoader = App.createFXMLLoader("salesProSelection");
        App.setSceneRoot(salesProSelectionLoader.load());

        SalesProSelectionController salesProSelectionController = salesProSelectionLoader.getController();
        salesProSelectionController.setHostServices(getHostServices());
        salesProSelectionController.setRetailers(getExampleRetailers());
        salesProSelectionController.setProducts(getExampleProducts());
    }

    public void switchToProductsAndPricing(ActionEvent event) throws IOException {
        FXMLLoader productsPricingLoader = App.createFXMLLoader("productsPricing");
        App.setSceneRoot(productsPricingLoader.load());
    }

    public void switchToCalendar(ActionEvent event) throws IOException {
        FXMLLoader calendarLoader = App.createFXMLLoader("calendar");
        App.setSceneRoot(calendarLoader.load());
    }

    public static void bindWidthAndHeightTogether(Window window, double widthToHeightRatio) {
        ChangeListener<Number> listener = new ChangeListener<>() {

            private boolean changing;

            @Override
            public void changed(ObservableValue<? extends Number> obs, Number ov, Number nv) {
                if (!changing) {
                    changing = true;
                    try {
                        if (obs == window.widthProperty()) {
                            window.setHeight(nv.doubleValue() / widthToHeightRatio);
                        } else {
                            window.setWidth(nv.doubleValue() * widthToHeightRatio);
                        }
                    } finally {
                        changing = false;
                    }
                }
            }
        };
        window.widthProperty().addListener(listener);
        window.heightProperty().addListener(listener);
    }

    private ObservableList<Retailer> getExampleRetailers() {
        ObservableList<Retailer> retailers = FXCollections.observableArrayList();
        retailers.add(new Retailer("Ahold Giant", getRetailerProducts(), 0, new BigDecimal("40"), 158,
                new BigDecimal("3.0")));
        retailers.add(new Retailer("Ahold Small", getDifferentRetailerProducts(), 1, new BigDecimal("40"), 183,
                new BigDecimal("3.0")));
        retailers.add(new Retailer("New Retailer", getTestRetailerProducts(), 1, new BigDecimal("40"), 183,
                new BigDecimal("3.0")));
        // retailers.add(new Retailer());
        return retailers;
    }

    /*
     * Loads dummy table data
     */
    public static ObservableList<RTMOption> getRTMOptions() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Direct-to-Customer")
                .freightOutPerUnit(new BigDecimal("0.29"))
                .slottingPerSku(new BigDecimal("7500"))
                .firstReceiver(new BigDecimal("3.59"))
                .landedStoreCost(new BigDecimal("3.59"))
                .everydayRetailOverride(new BigDecimal("5.99")).buildRtmOption();
        RTMOption optionTwo = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option2")
                .slottingPerSku(new BigDecimal("3500"))
                .firstReceiver(new BigDecimal("3.07"))
                .secondReceiver(new BigDecimal("3.07"))
                .landedStoreCost(new BigDecimal("3.79"))
                .everydayRetailCalcd(new BigDecimal("6.32"))
                .everydayRetailOverride(new BigDecimal("6.49")).buildRtmOption();
        RTMOption optionThree = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option 3").buildRtmOption();
        RTMOption optionFour = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option 4").buildRtmOption();
        RTMOptions.addAll(testOption, optionTwo, optionThree, optionFour);
        return RTMOptions;
    }

    public static ObservableList<RTMOption> getTestRTMOptions() {
        RTMOption rtmOption1 = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option 1")
                .firstReceiver(new BigDecimal("3.59")).buildRtmOption();
        RTMOption rtmOption2 = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option 2").buildRtmOption();
        RTMOption rtmOption3 = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option 3").buildRtmOption();
        RTMOption rtmOption4 = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option 4").buildRtmOption();;
        return FXCollections.observableArrayList(rtmOption1, rtmOption2, rtmOption3, rtmOption4);
    }

    /*
     * Loads dummy table data
     */
    public static ObservableList<RTMOption> getDifferentRTMOptions2() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOptionBuilder().retailerProduct(secondRP)
                .rtmName("Direct-to-Customer")
                .freightOutPerUnit(new BigDecimal("0.14"))
                .slottingPerSku(new BigDecimal("5000"))
                .firstReceiver(new BigDecimal("1.49"))
                .landedStoreCost(new BigDecimal("1.49"))
                .everydayRetailCalcd(new BigDecimal("2.48"))
                .everydayRetailOverride(new BigDecimal("2.49")).buildRtmOption();
        RTMOption optionTwo = new RTMOptionBuilder().retailerProduct(secondRP)
                .rtmName("Direct-to-KeHE Model")
                .freightOutPerUnit(new BigDecimal("0.14"))
                .slottingPerSku(new BigDecimal("2500"))
                .firstReceiver(new BigDecimal("1.49"))
                .secondReceiver(new BigDecimal("1.69"))
                .landedStoreCost(new BigDecimal("1.69"))
                .everydayRetailCalcd(new BigDecimal("2.82"))
                .everydayRetailOverride(new BigDecimal("2.99")).buildRtmOption();
        RTMOption optionThree = new RTMOptionBuilder().retailerProduct(secondRP)
                .rtmName("KeHE F.O.B. Model")
                .freightOutPerUnit(new BigDecimal("0.0"))
                .slottingPerSku(new BigDecimal("2500"))
                .firstReceiver(new BigDecimal("1.35"))
                .secondReceiver(new BigDecimal("1.79"))
                .landedStoreCost(new BigDecimal("1.79"))
                .everydayRetailCalcd(new BigDecimal("2.98"))
                .everydayRetailOverride(new BigDecimal("2.99")).buildRtmOption();
        RTMOption optionFour = new RTMOptionBuilder().retailerProduct(secondRP)
                .rtmName("Option 4").buildRtmOption();
        RTMOptions.addAll(testOption, optionTwo,  optionThree, optionFour);
        return RTMOptions;
    }

    public static ObservableList<RTMOption> getDifferentRTMOptions() {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOption testOption = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Direct-to-Customer")
                .freightOutPerUnit(new BigDecimal("0.29"))
                .slottingPerSku(new BigDecimal("5000"))
                .firstReceiver(new BigDecimal("3.59"))
                .landedStoreCost(new BigDecimal("3.59"))
                .everydayRetailOverride(new BigDecimal("5.99")).buildRtmOption();
        RTMOption optionTwo = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Direct-to-KeHE")
                .freightOutPerUnit(new BigDecimal("0.29"))
                .slottingPerSku(new BigDecimal("2500"))
                .firstReceiver(new BigDecimal("3.59"))
                .secondReceiver(new BigDecimal("4.19"))
                .landedStoreCost(new BigDecimal("4.19"))
                .everydayRetailCalcd(new BigDecimal("6.98"))
                .everydayRetailOverride(new BigDecimal("6.99")).buildRtmOption();
        RTMOption optionThree = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("F.O.B. Model")
                .slottingPerSku(new BigDecimal("2500"))
                .firstReceiver(new BigDecimal("3.30"))
                .secondReceiver(new BigDecimal("4.68"))
                .landedStoreCost(new BigDecimal("4.68"))
                .everydayRetailCalcd(new BigDecimal("7.80"))
                .everydayRetailOverride(new BigDecimal("7.99")).buildRtmOption();
        RTMOption optionFour = new RTMOptionBuilder().retailerProduct(firstRP)
                .rtmName("Option4").buildRtmOption();
        RTMOptions.addAll(testOption,optionTwo, optionThree, optionFour);
        return RTMOptions;
    }

    /*
     * Load dummy RetailerProduct
     */
    public static ObservableList<RetailerProduct> getRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts = FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList(getExampleSkus().get(0), getExampleSkus().get(1),
                getExampleSkus().get(2), getExampleSkus().get(3), getExampleSkus().get(4));
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        meetings.addAll(new Meeting("Review Meeting", "here", LocalDate.of(2022, 12, 5), "11:15", "will be fun"),
                new Meeting());
        firstRP = new RetailerProduct(getExampleProducts().get(0), getRTMOptions(), skus, meetings,
                getDummyPromoPlans(), new RetailerProductSpecs(158, new BigDecimal("40.0"), new BigDecimal("0.03"), new BigDecimal("1.2")));
        retailerProducts.add(firstRP);
        retailerProducts.get(0).getRtmOptions().stream().forEach(i -> i.setRetailerProduct(retailerProducts.get(0)));
        return retailerProducts;
    }

    /**
     * Only difference so far is that RTM Optiosn are getTestRTMOptions
     *
     * @return
     */
    public ObservableList<RetailerProduct> getTestRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts = FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList(getExampleSkus().get(0), getExampleSkus().get(1),
                getExampleSkus().get(2), getExampleSkus().get(3), getExampleSkus().get(4));
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        meetings.addAll(new Meeting("Review Meeting", "here", LocalDate.of(2022, 12, 5), "11:15", "will be fun"),
                new Meeting());
        firstRP = new RetailerProduct(getExampleProducts().get(0), getTestRTMOptions(), skus, meetings,
                getDummyPromoPlans(),new RetailerProductSpecs( 183, new BigDecimal("40.0"), new BigDecimal("0.03"), new BigDecimal("1.2")));
        retailerProducts.add(firstRP);
        retailerProducts.get(0).getRtmOptions().stream().forEach(i -> i.setRetailerProduct(retailerProducts.get(0)));
        return retailerProducts;
    }

    static Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    /*
     * Load dummy RetailerProduct
     */
    public static ObservableList<RetailerProduct> getDifferentRetailerProducts() {
        ObservableList<RetailerProduct> retailerProducts = FXCollections.observableArrayList();
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        skus.addAll(getExampleSkus().get(4), getExampleSkus().get(5), getExampleSkus().get(6), getExampleSkus().get(7),
                getExampleSkus().get(8));
        meetings.addAll(getExampleMeetings().get(4), getExampleMeetings().get(5), getExampleMeetings().get(6),
                getExampleMeetings().get(7));
        firstRP = new RetailerProduct(getExampleProducts().get(4), getDifferentRTMOptions(), skus, meetings,
                getDifferentDummyPromoPlans(), new RetailerProductSpecs(183, new BigDecimal("40.0"),
                new BigDecimal("0.03"), new BigDecimal("1.2")));
        retailerProducts.add(firstRP);
        ObservableList<Sku> skus2 = FXCollections.observableArrayList();
        ObservableList<Meeting> meetings2 = FXCollections.observableArrayList();
        skus2.addAll(getExampleSkus().get(9), getExampleSkus().get(10), getExampleSkus().get(11));
        meetings2.addAll(getExampleMeetings().get(8), getExampleMeetings().get(9), getExampleMeetings().get(10),
                getExampleMeetings().get(11));
        secondRP = new RetailerProduct(getExampleProducts().get(1), getDifferentRTMOptions2(), skus2,
                meetings2, getDifferentDummyPromoPlans2(),new RetailerProductSpecs( 183,
                new BigDecimal("40.0"), new BigDecimal("0.03"), new BigDecimal("1.2")));
        retailerProducts.add(secondRP);
        return retailerProducts;
    }

    /*
     * Loads dummy product data
     */
    public static ObservableList<Product> getExampleProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Big Time Food Company", "24 oz pickles", new BigDecimal("3.59"),
                new BigDecimal("0.29"), new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("2.05"),
                new BigDecimal("-1.15"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), getExtendedSkus()));
        products.add(new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"),
                new BigDecimal("0.14"), new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"),
                new BigDecimal("-1.20"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), getExtendedSkus()));
        products.add(new Product("Big Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"),
                new BigDecimal("0.14"), new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"),
                new BigDecimal("-1.20"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), getExtendedSkus()));
        products.add(new Product("Small Time Food Company", "12 oz pickle juice", new BigDecimal("1.49"),
                new BigDecimal("0.14"), new BigDecimal("1.35"), new BigDecimal("1.29"), new BigDecimal("0.78"),
                new BigDecimal("-1.20"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), getExtendedSkus()));
        products.add(new Product("Small Time Food Company", "32 oz mixes", new BigDecimal("3.59"),
                new BigDecimal("0.29"), new BigDecimal("3.30"), new BigDecimal("2.99"), new BigDecimal("1.60"),
                new BigDecimal("-1.05"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), getExtendedSkus()));
        return products;
    }

    public static ObservableList<Sku> getExtendedSkus() {
        ObservableList<Sku> extendedSkus = FXCollections.observableArrayList();
        extendedSkus.add(new Sku("Dill Pickles", 6, new BigDecimal("2.40"), new BigDecimal("15.0"),
                new BigDecimal("14.40"), new BigDecimal("0.6"), new BigDecimal("0.33"), new BigDecimal("12.00"),
                new BigDecimal("8.00"), new BigDecimal("6.00"), new BigDecimal("3.5"), new BigDecimal("3.5"),
                new BigDecimal("5.5"), 20, 8, 160, new BigDecimal("53.00"), 2500, 18, 9, "859994-00600 6",
                "859994-00606 8", new BigDecimal("3.59")));
        extendedSkus.add(new Sku("Dill Pickles 1", 6, new BigDecimal("2.40"), new BigDecimal("15.0"),
                new BigDecimal("14.40"), new BigDecimal("0.6"), new BigDecimal("0.33"), new BigDecimal("12.00"),
                new BigDecimal("8.00"), new BigDecimal("6.00"), new BigDecimal("3.5"), new BigDecimal("3.5"),
                new BigDecimal("5.5"), 20, 8, 160, new BigDecimal("53.00"), 2500, 18, 9, "859994-00600 6",
                "859994-00606 8", new BigDecimal("3.59")));
        extendedSkus.add(new Sku("Dill Pickles 2", 6, new BigDecimal("2.40"), new BigDecimal("15.0"),
                new BigDecimal("14.40"), new BigDecimal("0.6"), new BigDecimal("0.33"), new BigDecimal("12.00"),
                new BigDecimal("8.00"), new BigDecimal("6.00"), new BigDecimal("3.5"), new BigDecimal("3.5"),
                new BigDecimal("5.5"), 20, 8, 160, new BigDecimal("53.00"), 2500, 18, 9, "859994-00600 6",
                "859994-00606 8", new BigDecimal("3.59")));
        extendedSkus.add(new Sku("Dill Pickles 3", 6, new BigDecimal("2.40"), new BigDecimal("15.0"),
                new BigDecimal("14.40"), new BigDecimal("0.6"), new BigDecimal("0.33"), new BigDecimal("12.00"),
                new BigDecimal("8.00"), new BigDecimal("6.00"), new BigDecimal("3.5"), new BigDecimal("3.5"),
                new BigDecimal("5.5"), 20, 8, 160, new BigDecimal("53.00"), 2500, 18, 9, "859994-00600 6",
                "859994-00606 8", new BigDecimal("3.59")));
        extendedSkus.add(new Sku("Dill Pickles 4", 6, new BigDecimal("2.40"), new BigDecimal("15.0"),
                new BigDecimal("14.40"), new BigDecimal("0.6"), new BigDecimal("0.33"), new BigDecimal("12.00"),
                new BigDecimal("8.00"), new BigDecimal("6.00"), new BigDecimal("3.5"), new BigDecimal("3.5"),
                new BigDecimal("5.5"), 20, 8, 160, new BigDecimal("53.00"), 2500, 18, 9, "859994-00600 6",
                "859994-00606 8", new BigDecimal("3.59")));

        return extendedSkus;
    }

    public static ObservableList<Meeting> getExampleMeetings() {
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        meetings.add(new Meeting("First Meeting", "At Home", LocalDate.of(2022, 12, 5), "17:05", "gonna be cool"));
        meetings.add(new Meeting("Second Meeting", "At Home", LocalDate.of(2021, 01, 9), "17:05", "cant wait"));
        meetings.add(new Meeting("Third Meeting", "At Home", LocalDate.of(2020, 02, 13), "17:05", "another one!"));
        meetings.add(new Meeting("Fourth Meeting", "At Home", LocalDate.of(2022, 12, 10), "17:05", "be punctual"));
        meetings.add(new Meeting("Fifth Meeting", "At Home", LocalDate.of(2022, 10, 5), "17:05", "be late"));
        meetings.add(new Meeting("Sixth Meeting", "At Home", LocalDate.of(2024, 12, 5), "17:05", "call first"));
        meetings.add(new Meeting("Seventh Meeting", "At Home", LocalDate.of(2022, 12, 5), "17:45", "email first"));
        meetings.add(new Meeting("Eigth Meeting", "At Home", LocalDate.of(2022, 12, 5), "18:05", "zzz"));

        meetings.add(new Meeting("Ninth Meeting", "Atnot Home", LocalDate.of(2029, 10, 5), "17:05", "be late"));
        meetings.add(new Meeting("Tenth Meeting", "Atnto Home", LocalDate.of(2022, 12, 5), "17:25", "call first"));
        meetings.add(new Meeting("Eleventh Meeting", "Ate Home", LocalDate.of(2022, 12, 10), "17:45", "email first"));
        meetings.add(new Meeting("Twelveth Meeting", "At v Home", LocalDate.of(2021, 1, 1), "18:05", "zzz"));
        return meetings;
    }

    public static ObservableList<Sku> getExampleSkus() {
        ObservableList<Sku> skus = FXCollections.observableArrayList();
        skus.add(new Sku("First Sku", "current", "love this one"));
        skus.add(new Sku("Second Sku", "current", "Not my favorite"));
        skus.add(new Sku("Third Sku", "discontinued", "Hate it"));
        skus.add(new Sku("Fourth Sku", "targeted", "Hate it"));
        skus.add(new Sku("Fifth Sku", "targeted", "Hate it"));
        skus.add(new Sku("Sixth Sku", "discontinued", "Hate it"));
        skus.add(new Sku("Seventh Sku", "current", "Hate it"));
        skus.add(new Sku("Eighth Sku", "current", "Hate it"));
        skus.add(new Sku("Ninth Sku", "current", "Hate it"));

        skus.add(new Sku("Tentth Sku", "discontinued", "Hate it"));
        skus.add(new Sku("Twelvth Sku", "current", ". it"));
        skus.add(new Sku("13th Sku", "current", "Lov it"));
        skus.add(new Sku("14th Sku", "current", "got it"));
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

    public static ObservableList<PromoPlan> getDummyPromoPlans() {
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.75"), getRTMOptions().get(1), false));
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.75"), getRTMOptions().get(0), false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.0"), getRTMOptions().get(2), false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.0"), getRTMOptions().get(0), false));
        return promoPlans;
    }

    public static ObservableList<PromoPlan> getDifferentDummyPromoPlans() {
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getDifferentParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.8"), getDifferentRTMOptions().get(0), false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.0"), getDifferentRTMOptions().get(1), true));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.10"), getDifferentRTMOptions().get(1), false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.0"), getDifferentRTMOptions().get(2), false));
        return promoPlans;
    }

    public static ObservableList<PromoPlan> getDifferentDummyPromoPlans2() {
        ObservableList<PromoPlan> promoPlans = FXCollections.observableArrayList();
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("9.0"), getDifferentRTMOptions2().get(1), false));
        promoPlans.add(new PromoPlan(getDifferentParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.0"), getDifferentRTMOptions2().get(3), true));
        promoPlans.add(new PromoPlan(getParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.00001"), getDifferentRTMOptions2().get(0), false));
        promoPlans.add(new PromoPlan(getEmptyParameters(), getToplineSummaries(), getRetailerSummaries(),
                new BigDecimal("0.090"), getDifferentRTMOptions2().get(2), false));
        return promoPlans;
    }

    public static ObservableList<Parameter<?>> getParameters() {
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("5.0"), new BigDecimal("5.0"),
                new BigDecimal("5.0"), new BigDecimal("5.0"), new BigDecimal("5.0"), new BigDecimal("5.0"),
                new BigDecimal("6.0"), new BigDecimal("6.0"), new BigDecimal("6.0"), new BigDecimal("6.0"),
                new BigDecimal("6.0"), new BigDecimal("6.0"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("50.00"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("7000.00"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(
                new IntegerParameter("Store Count", "", 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158));
        parameters.add(new BigDecimalParameter());
        ;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("6.49"), new BigDecimal("6.49"),
                new BigDecimal("6.49"), new BigDecimal("6.49"), new BigDecimal("6.49"), new BigDecimal("6.49"),
                new BigDecimal("6.49"), new BigDecimal("6.49"), new BigDecimal("6.49"), new BigDecimal("6.49"),
                new BigDecimal("6.49"), new BigDecimal("6.49"), false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.89"),
                new BigDecimal("3.89"), new BigDecimal("3.89"), new BigDecimal("3.89"), new BigDecimal("3.89"),
                new BigDecimal("3.89"), new BigDecimal("3.89"), new BigDecimal("3.89"), new BigDecimal("3.89"),
                new BigDecimal("3.89"), new BigDecimal("3.89"), new BigDecimal("3.89"), false));
        parameters.add(new BigDecimalParameter());
        ;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("0.91"),
                new BigDecimal("0.91"), new BigDecimal("0.93"), new BigDecimal("0.95"), new BigDecimal("1.07"),
                new BigDecimal("1.27"), new BigDecimal("1.46"), new BigDecimal("1.23"), new BigDecimal("0.86"),
                new BigDecimal("0.80"), new BigDecimal("0.82"), new BigDecimal("0.86"), false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("5.99"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("5.99"),
                new BigDecimal("5.99"), new BigDecimal("5.99"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("5.99"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("40.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("40.0"),
                new BigDecimal("40.0"), new BigDecimal("40.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("40.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("2.5"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("2.5"), new BigDecimal("2.5"), new BigDecimal("2.5"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("2.5"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("500"),
                new BigDecimal("500"), new BigDecimal("500"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("500"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.59"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("3.59"),
                new BigDecimal("3.59"), new BigDecimal("3.59"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("3.59"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("7.7"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("7.7"),
                new BigDecimal("7.7"), new BigDecimal("7.7"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("7.7"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR", "", "", "", "", "4 Week TPR",
                "4 Week TPR", "4 Week TPR", "", "", "", "4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        return parameters;
    }

    public static ObservableList<Parameter<?>> getDifferentParameters() {
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("2.0"), new BigDecimal("2.0"),
                new BigDecimal("2.0"), new BigDecimal("2.0"), new BigDecimal("2.0"), new BigDecimal("2.0"),
                new BigDecimal("2.75"), new BigDecimal("2.75"), new BigDecimal("2.75"), new BigDecimal("2.75"),
                new BigDecimal("2.75"), new BigDecimal("2.75"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("75.00"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("2500.00"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(
                new IntegerParameter("Store Count", "", 183, 183, 183, 183, 183, 183, 183, 183, 183, 183, 183, 183));
        parameters.add(new BigDecimalParameter());
        ;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("5.99"), new BigDecimal("5.99"),
                new BigDecimal("5.99"), new BigDecimal("5.99"), new BigDecimal("5.99"), new BigDecimal("5.99"),
                new BigDecimal("5.99"), new BigDecimal("5.99"), new BigDecimal("5.99"), new BigDecimal("5.99"),
                new BigDecimal("5.99"), new BigDecimal("5.99"), false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("3.59"),
                new BigDecimal("3.59"), new BigDecimal("3.59"), new BigDecimal("3.59"), new BigDecimal("3.59"),
                new BigDecimal("3.59"), new BigDecimal("3.59"), new BigDecimal("3.59"), new BigDecimal("3.59"),
                new BigDecimal("3.59"), new BigDecimal("3.59"), new BigDecimal("3.59"), false));
        parameters.add(new BigDecimalParameter());
        ;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("1.00"),
                new BigDecimal("0.94"), new BigDecimal("0.85"), new BigDecimal("0.83"), new BigDecimal("0.99"),
                new BigDecimal("1.02"), new BigDecimal("0.92"), new BigDecimal("1.04"), new BigDecimal("0.91"),
                new BigDecimal("0.96"), new BigDecimal("1.14"), new BigDecimal("1.52"), false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("4.99"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("4.99"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("4.99"), new BigDecimal("4.99"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("35.00"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("35.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("35.00"), new BigDecimal("35.00"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 4, 4));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("4.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("4.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("4.0"), new BigDecimal("4.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("500"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("500"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("500"), new BigDecimal("500"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("3.24"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("3.24"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("3.24"), new BigDecimal("3.24"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("9.8"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("9.8"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("9.8"), new BigDecimal("9.8"), false));
        parameters.add(new StringParameter("Promotional Commentary", "", "4 Week TPR", "", "", "", "", "4 Week TPR",
                "4 Week TPR", "4 Week TPR", "", "", "", "4 Week TPR"));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        return parameters;
    }

    public static ObservableList<Parameter<?>> getEmptyParameters() {
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Confidence %", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Store Count", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter());
        ;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter());
        ;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters
                .add(new StringParameter("Promotional Commentary", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Total Volume=", "", new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        parameters.add(new BigDecimalParameter("Gross Profit (Plan)=", "$", new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0"), false));
        return parameters;
    }

}
