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
