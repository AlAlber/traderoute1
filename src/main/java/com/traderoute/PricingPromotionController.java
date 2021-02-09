package com.traderoute;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PricingPromotionController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
