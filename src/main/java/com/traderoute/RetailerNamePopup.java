package com.traderoute;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RetailerNamePopup {
    static String retailerName;

    public static String display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        JFXTextField retailerField = new JFXTextField();
        retailerField.setPromptText("Ex. Ahold Giant Landover");
        JFXButton saveButton = new JFXButton("Save");

        saveButton.setOnAction(e -> {
            if (retailerField.getText() == null) {
                retailerName = "";
            } else {
                retailerName = retailerField.getText();
            }
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, retailerField, saveButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return retailerName;
    }
}
