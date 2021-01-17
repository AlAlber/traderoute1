package com.traderoute;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class firstTableController implements Initializable {

    // Assign Values to
    @FXML private TableView<RTMOption> secondTableView;
    @FXML private TableView<RTMOption> firstTableView;
    @FXML private TableColumn<RTMOption, String> RTMNameColumn;
    @FXML private TableColumn<RTMOption, Integer> slottingPerSkuColumn;
    @FXML private TableColumn<RTMOption, Double> freightOutPerUnitColumn;
    @FXML private TableColumn<RTMOption, Double> firstReceiverColumn;
    @FXML private TableColumn<RTMOption, Double> secondReceiverColumn;
    @FXML private TableColumn<RTMOption, Double> thirdReceiverColumn;
    @FXML private TableColumn<RTMOption, Double> fourthReceiverColumn;
    @FXML private TableColumn<RTMOption, Double> landedStoreCostColumn;
    @FXML private TableColumn<RTMOption, Double> resultingEverydayRetailCalcdColumn;
    @FXML private TableColumn<RTMOption, Integer> resultingEverydayRetailOverrideColumn;
    @FXML private TableColumn<RTMOption, String> weeklyVelocityAtMinColumn;
    @FXML private TableColumn<RTMOption, Integer> weeklyVelocityUfswColumn;
    @FXML private TableColumn<RTMOption, String> RTMNameColumn2;
    @FXML private TableColumn<RTMOption, Integer> elasticizedEstimatedUnitVelocityColumn;
    @FXML private TableColumn<RTMOption, Integer> estimatedAnnualVolumePerSkuColumn;
    @FXML private TableColumn<RTMOption, Integer> slottingPaybackPeriodColumn;
    @FXML private TableColumn<RTMOption, Integer> postFreightPostSpoilsWeCollectColumn;
    @FXML private TableColumn<RTMOption, Integer> tradePerUnitColumn;
    @FXML private TableColumn<RTMOption, Integer> fourYearEqGpPerSkuColumn;
    @FXML private TableColumn<RTMOption, Double> fourYearEqGpPerUnitColumn;

    @FXML private TextField everyDayGPMField;
    @FXML private TextField yearOneStoreCountField;
    @FXML private TextField spoilsFeesField;
    @FXML private TextField RTMNameField;
    @FXML private TextField slottingPerSkuField;
    @FXML private TextField freightOutPerUnitField;
    @FXML private TextField firstReceiverField;
    @FXML private TextField secondReceiverField;
    @FXML private TextField thirdReceiverField;
    @FXML private TextField fourthReceiverField;
    @FXML private TextField resultingEveryDayRetailOverrideField;
    @FXML private TextField weeklyVelocityUfswField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set up cellValue factories

        RTMNameColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, String>("RTMName"));
        slottingPerSkuColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("slottingPerSku"));
        freightOutPerUnitColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Double>("freightOutPerUnit"));
        firstReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Double>("firstReceiver"));
        secondReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Double>("secondReceiver"));
        thirdReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Double>("thirdReceiver"));
        fourthReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption,Double>("fourthReceiver"));
        landedStoreCostColumn.setCellValueFactory(new PropertyValueFactory<RTMOption,Double>("landedStoreCost"));
        resultingEverydayRetailCalcdColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Double>("resultingEverydayRetailCalcd"));
        resultingEverydayRetailOverrideColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("resultingEverydayRetailOverride"));
        RTMNameColumn2.setCellValueFactory(new PropertyValueFactory<RTMOption, String>("RTMName"));
//      weeklyVelocityUfswColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("weeklyVelocityUsfw"));
//      resultingEverydayRetailOverride.setCellFactory(new PropertyValueFactory<RTMOption, Double>("Resulting Everyday Override"));
        firstTableView.setItems(getRTMOptions());
        secondTableView.setItems(getRTMOptions());
//      firstTableView.setEditable(true);
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        //Set up input validators for Every Day GPM %, Spoils + Fees %, Year One Store Count

        //Set up input validators for Every Day GPM %, Spoils + Fees %, Year One Store Count

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>() {

            @Override
            public BigDecimal fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return BigDecimal.valueOf(0.0);
                } else {
                    return new BigDecimal(s);
                }
            }


            @Override
            public String toString(BigDecimal d) {
                return d.toString();
            }
        };

        TextFormatter<BigDecimal> textFormatter = new TextFormatter<>(converter, new BigDecimal("0.0"), filter);
        TextFormatter<BigDecimal> textFormatter2 = new TextFormatter<>(converter, new BigDecimal(0.0), filter);
        TextFormatter<BigDecimal> textFormatter3 = new TextFormatter<>(converter, new BigDecimal(0.0), filter);
        everyDayGPMField.setTextFormatter(textFormatter);
        spoilsFeesField.setTextFormatter(textFormatter2);
        yearOneStoreCountField.setTextFormatter(textFormatter3);

        textFormatter.valueProperty().addListener((ObservableValue<? extends BigDecimal> obs, BigDecimal oldValue, BigDecimal newValue) -> {
            System.out.println("User entered value: "+newValue);

        });
        //Set New Everyday GPM % to the input value
//        textFormatter.valueProperty().addListener((ObservableValue<? extends Double> obs, Double oldValue, Double newValue) -> {
////                    System.out.println("User entered value: "+newValue.doubleValue());
//            for (RTMOption row : firstTableView.getItems()) {
//                row.setEveryDayGPM(newValue);
//                firstTableView.refresh();
//                System.out.println(row.toString());
//            }
//        });
        // Calculate the resulting everyday retail cost from everyday GPM, Landed Store Cost Property
        for (RTMOption row : firstTableView.getItems()) {
            row.resultingEverydayRetailProperty().bind(Bindings.divide(Bindings.multiply(
                    100,row.landedStoreCostProperty()), Bindings.subtract(100,row.everyDayGPMProperty())));;
        }

        // Set Cell Factories for second Table View

        RTMNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        slottingPerSkuColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        freightOutPerUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        firstReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        landedStoreCostColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        resultingEverydayRetailCalcdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        resultingEverydayRetailOverrideColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        weeklyVelocityUfsw.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

    }
    // Loads dummy data
    public ObservableList<RTMOption> getRTMOptions()
    {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOptions.add(new RTMOption("Frank",1.3,0,0.5,
                0.1,0.3,3.95,
                0,0,0,
                0,0,0,
                0,0,0,0));
        RTMOptions.add(new RTMOption("Rebecca",2.1,0,
                0.01,0.03,0.02,0.5,
                0,0,0,
                0,0,0,
                0,0,0,
                0));
        RTMOptions.add(new RTMOption("Mr.",3.1,0,
                0.03,0.1,0.2,0.2,
                0,0,0,
                0,0,0,
                0,0,0,0
                ));

        return RTMOptions;
    }
}
