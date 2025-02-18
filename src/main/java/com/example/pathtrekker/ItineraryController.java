package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ItineraryController {

    @FXML
    private Button ItineraryBack;

    @FXML
    private TextField numberOfPeopleField;

    @FXML
    private TextField numberOfDaysField;

    @FXML
    private TextField districtField;

    @FXML
    private TextField divisionField;

    @FXML
    private CheckBox lowBudget;

    @FXML
    private CheckBox mediumBudget;

    @FXML
    private CheckBox highBudget;

    @FXML
    private CheckBox mountainPerson;

    @FXML
    private CheckBox beachPerson;

    @FXML
    private CheckBox cityPerson;

    @FXML
    private CheckBox countryPerson;

    @FXML
    private CheckBox historicalSites;

    @FXML
    private CheckBox adventureSports;

    @FXML
    private CheckBox foodCulture;

    @FXML
    private CheckBox wildlifeNature;

    @FXML
    private Button generateButton;

    @FXML
    private Pane blackBlurredBox;

    ChangeScene change=new ChangeScene();

    @FXML
    public void initialize() {
        // Set padding programmatically
        blackBlurredBox.setPadding(new Insets(20, 20, 20, 20));
    }

    @FXML
    private void handleGenerateButton() {
        if (numberOfPeopleField.getText().isEmpty() || numberOfDaysField.getText().isEmpty() ||
                districtField.getText().isEmpty() || divisionField.getText().isEmpty() ||
                (!lowBudget.isSelected() && !mediumBudget.isSelected() && !highBudget.isSelected()) ||
                (!mountainPerson.isSelected() && !beachPerson.isSelected()) ||
                (!cityPerson.isSelected() && !countryPerson.isSelected())) {
            showAlert(Alert.AlertType.WARNING, "Unfulfilled Boxes", "Please fill all the boxes before generating the itinerary.");
        } else {
            // Simulate itinerary creation
            boolean isSuccess = createItinerary(); // Replace with actual logic
            if (isSuccess) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Your itinerary has been successfully created!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "There was an issue creating your itinerary. Please try again.");
            }
        }
    }

    private boolean createItinerary() {
        // Simulate itinerary creation logic
        return true; // Replace with actual logic
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void ItineraryBackAction(MouseEvent event) throws IOException {
        Stage stage=(Stage) ItineraryBack.getScene().getWindow();
        change.changeScene(stage,"Home.fxml");

    }
}