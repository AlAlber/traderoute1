package com.traderoute;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class PricingPromotionController implements Initializable {
    @FXML
    private TableView<Parameter<?>> pricingPromotionTableOne;

    @FXML
    private TableColumn<Parameter<?>, Object> januaryColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> februaryColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> marchColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> aprilColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> mayColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> juneColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> julyColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> augustColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> septemberColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> octoberColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> novemberColumn;

    @FXML
    private TableColumn<Parameter<?>, Object> decemberColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pricingPromotionTableOne.setEditable(true);
        pricingPromotionTableOne.setItems(getParameters());
        januaryColumn.setCellValueFactory(cellData -> (ObservableValue<Object>)cellData.getValue().januaryProperty());
        februaryColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().februaryProperty());
        marchColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().marchProperty());
        aprilColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().aprilProperty());
        mayColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().mayProperty());
        juneColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().juneProperty());
        julyColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().julyProperty());
        augustColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().augustProperty());
        septemberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().septemberProperty());
        octoberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().octoberProperty());
        novemberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().novemberProperty());
        decemberColumn.setCellValueFactory(cellData-> (ObservableValue<Object>)cellData.getValue().decemberProperty());

        januaryColumn.setCellFactory(tc -> new ParameterValueEditingCell());
        februaryColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        marchColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        aprilColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        mayColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        juneColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        julyColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        augustColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        septemberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        octoberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        novemberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
        decemberColumn.setCellFactory(tc-> new ParameterValueEditingCell());
    }


    public ObservableList<Parameter<?>> getParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "$", new BigDecimal("0.0"),new BigDecimal("0.104") ,new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104"),new BigDecimal("0.104")));
        parameters.add(new IntegerParameter("Store count", "&", 10, 20, 10, 20, 10, 20, 10, 20, 10, 20, 10, 20));
        return parameters;
    }

    @FXML
    private void switchToSecondTable(ActionEvent event) throws IOException {
        App.setRoot("secondTable");
    }
    @FXML
    private void switchToAssortment(ActionEvent event) throws IOException {
        App.setRoot("assortment");
    }
}
