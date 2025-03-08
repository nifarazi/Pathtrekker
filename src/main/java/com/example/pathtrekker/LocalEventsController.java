package com.example.pathtrekker;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LocalEventsController {

    @FXML
    private ComboBox<String> divisionDropdown;

    @FXML
    private Button searchButton, homeButton;

    private final ChangeScene cs = new ChangeScene();

    @FXML
    public void initialize() {
        divisionDropdown.getItems().addAll("Dhaka", "Chittagong", "Rajshahi", "Khulna",
                "Barisal", "Sylhet", "Rangpur", "Mymensingh");

        setButtonStyle(searchButton);
        setButtonStyle(homeButton);
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #67B99A; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #67B99A; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #248977; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
    }

    @FXML
    private void searchEvents() {
        String selectedDivision = divisionDropdown.getValue();
        if (selectedDivision != null && !selectedDivision.isEmpty()) {
            LocalEventsResultController.setSelectedDivision(selectedDivision);
            Stage stage = (Stage) searchButton.getScene().getWindow();
            try {
                cs.changeScene(stage, "LocalEventsResult.fxml");  // ✅ Safe scene transition
            } catch (IOException e) {
                showAlert("Scene Error", "Failed to load LocalEventsResult.fxml.");
                e.printStackTrace();  // Debugging output
            }
        } else {
            showAlert("Selection Error", "Please select a division before searching.");
        }
    }

    @FXML
    private void goToHome() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Home.fxml");  // ✅ Safe scene transition
        } catch (IOException e) {
            showAlert("Scene Error", "Failed to load Home.fxml.");
            e.printStackTrace();  // Debugging output
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}