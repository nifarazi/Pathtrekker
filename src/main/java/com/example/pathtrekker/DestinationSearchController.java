package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class DestinationSearchController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button divisionButton1;

    @FXML
    private Button divisionButton2;

    @FXML
    private Button divisionButton3;

    @FXML
    private Button divisionButton4;

    @FXML
    private Button divisionButton5;

    @FXML
    private Button divisionButton6;

    @FXML
    private Button divisionButton7;

    @FXML
    private Button divisionButton8;

    @FXML
    private Button homeButton;

    @FXML
    public void initialize() {
        styleButton(divisionButton1, "#008CBA", "#005f73");
        styleButton(divisionButton2, "#008CBA", "#005f73");
        styleButton(divisionButton3, "#008CBA", "#005f73");
        styleButton(divisionButton4, "#008CBA", "#005f73");
        styleButton(divisionButton5, "#008CBA", "#005f73");
        styleButton(divisionButton6, "#008CBA", "#005f73");
        styleButton(divisionButton7, "#008CBA", "#005f73");
        styleButton(divisionButton8, "#008CBA", "#005f73");

        // Style home button
        styleButton(homeButton, "#f44336", "#d32f2f");

        // Set action for home button
        homeButton.setOnAction(event -> {
            try {
                Stage stage = (Stage) homeButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void styleButton(Button button, String baseColor, String hoverColor) {
        // Base style
        button.setStyle(
                "-fx-background-color: " + baseColor + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10px 20px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"
        );

        // Hover effect
        button.setOnMouseEntered(event -> {
            button.setStyle(
                    "-fx-background-color: " + hoverColor + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-padding: 10px 20px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-background-radius: 5px;" +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"
            );
        });

        // Reset style on mouse exit
        button.setOnMouseExited(event -> {
            button.setStyle(
                    "-fx-background-color: " + baseColor + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-padding: 10px 20px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-background-radius: 5px;" +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"
            );
        });
    }
}