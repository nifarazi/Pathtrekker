package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button reviewRatingsButton, hotelsButton, transportationButton, localAttractionsButton;

    @FXML
    private MenuButton adminMenuButton;

    private final ChangeScene sceneChanger = new ChangeScene(); // Using ChangeScene class

    private String username;

    public void setUsername(String username) {
        this.username = username;
        adminMenuButton.setText(username);
    }

    @FXML
    public void initialize() {
        setButtonStyle(reviewRatingsButton);
        setButtonStyle(hotelsButton);
        setButtonStyle(transportationButton);
        setButtonStyle(localAttractionsButton);
        setButtonStyle(adminMenuButton);
        setupAdminMenu();
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #1C3D24; -fx-text-fill: #CDE8D4; -fx-font-size: 14px; "
                + "-fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
    }

    private void setButtonStyle(MenuButton menuButton) {
        menuButton.setStyle("-fx-background-color: #90CAB3; -fx-text-fill: white; -fx-font-size: 14px; "
                + "-fx-padding: 0.2px; -fx-border-radius: 0.2px; -fx-font-weight: bold;");
    }

    private void setupAdminMenu() {
        MenuItem logoutItem = new MenuItem("Log Out");
        logoutItem.setStyle("-fx-background-color: #1C3D24; -fx-text-fill: #CDE8D4; -fx-font-size: 14px; "
                + "-fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
        logoutItem.setOnAction(event -> handleLogout());

        adminMenuButton.getItems().add(logoutItem);
    }

    @FXML
    private void handleHotelsButton() {
        try {
            Stage stage = (Stage) hotelsButton.getScene().getWindow();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminHotel.fxml");
        } catch (IOException e) {
            showAlert("Error", "Failed to load the Hotel Management page.\n" + e.getMessage());
        }
    }

    @FXML
    private void handleReviewRatingsButton() {
        showAlert("Review and Ratings", "Opening Review and Ratings Page...");
    }

    @FXML
    private void handleTransportationButton() {
        try {
            Stage stage = (Stage) transportationButton.getScene().getWindow();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminTransport.fxml"); // Navigate to Transport Management
        } catch (IOException e) {
            showAlert("Error", "Failed to load the Transport Management page.\n" + e.getMessage());
        }
    }

    @FXML
    private void handleLocalAttractionsButton() {
        try {
            Stage stage = (Stage) localAttractionsButton.getScene().getWindow();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminEvents.fxml"); // Navigate to Events Management
        } catch (IOException e) {
            showAlert("Error", "Failed to load the Events Management page.\n" + e.getMessage());
        }
    }

    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure you want to log out?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Stage stage = (Stage) adminMenuButton.getScene().getWindow();
                    sceneChanger.changeScene(stage, "/com/example/pathtrekker/OpeningPage.fxml");
                } catch (IOException e) {
                    showAlert("Error", "Failed to log out.\n" + e.getMessage());
                }
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}