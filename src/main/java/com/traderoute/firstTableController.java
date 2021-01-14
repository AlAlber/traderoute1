package com.traderoute;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class firstTableController implements Initializable {


    @FXML private TableView<RTMOption> secondTableView;
    @FXML private TableView<RTMOption> firstTableView;
    @FXML private TableColumn<RTMOption, String> RTMNameColumn;
    @FXML private TableColumn<RTMOption, Integer> slottingPerSkuColumn;
    @FXML private TableColumn<RTMOption,Integer> freightOutPerUnitColumn;
    @FXML private TableColumn<RTMOption,Integer> firstReceiverColumn;
    @FXML private TableColumn<RTMOption,Integer> secondReceiverColumn;
    @FXML private TableColumn<RTMOption,Integer> thirdReceiverColumn;
    @FXML private TableColumn<RTMOption, Integer> fourthReceiverColumn;
    @FXML private TableColumn<RTMOption, Integer> resultingEverydayRetailCalcdColumn;
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
        RTMNameColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, String>("RTMName"));
        slottingPerSkuColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("slottingPerSku"));
        freightOutPerUnitColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("freightOutPerUnit"));
        firstReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("firstReceiver"));
        secondReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("secondReceiver"));
        thirdReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("thirdReceiver"));
        fourthReceiverColumn.setCellValueFactory(new PropertyValueFactory<RTMOption,Integer>("fourthReceiver"));
        resultingEverydayRetailCalcdColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("resultingEverydayRetailCalcd"));
        resultingEverydayRetailOverrideColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("resultingEverydayRetailOverride"));
        RTMNameColumn2.setCellValueFactory(new PropertyValueFactory<RTMOption, String>("RTMName"));
//        weeklyVelocityUfswColumn.setCellValueFactory(new PropertyValueFactory<RTMOption, Integer>("weeklyVelocityUsfw"));
//        resultingEverydayRetailOverride.setCellFactory(new PropertyValueFactory<RTMOption, Double>("Resulting Everyday Override"));
        firstTableView.setItems(getRTMOptions());
        secondTableView.setItems(getRTMOptions());
//      firstTableView.setEditable(true);

        RTMNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        slottingPerSkuColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        freightOutPerUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        firstReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        secondReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        thirdReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        fourthReceiverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        resultingEverydayRetailOverrideColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        weeklyVelocityUfsw.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

    }
    public ObservableList<RTMOption> getRTMOptions()
    {
        ObservableList<RTMOption> RTMOptions = FXCollections.observableArrayList();
        RTMOptions.add(new RTMOption("Frank",1,0,0,
                0,0,0,0,
                0,0,0,
                0,0,0,
                0,0,0,0));
        RTMOptions.add(new RTMOption("Rebecca",2,0,
                0,0,0,0,
                0,0,0,
                0,0,0,
                0,0,0,
                0,0));
        RTMOptions.add(new RTMOption("Mr.",3,0,
                0,0,0,0,
                0,0,0,
                0,0,0,
                0,0,0,0
                ,0));

        return RTMOptions;
    }
}
