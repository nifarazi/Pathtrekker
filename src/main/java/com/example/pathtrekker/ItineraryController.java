package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
public class ItineraryController {

    @FXML
    private Button ItineraryBack, generateButton;

    @FXML
    private TextField numberOfPeopleField, numberOfDaysField, districtField, divisionField;

    @FXML
    private Label peopleLabel, daysLabel, districtLabel, divisionLabel;

    @FXML
    private CheckBox lowBudget, mediumBudget, highBudget, mountainPerson, beachPerson;
    @FXML
    private CheckBox summerSeason, autumnSeason, springSeason, winterSeason;
    @FXML
    private CheckBox cityPerson, countryPerson, historicalSites, adventureSports, foodCulture, wildlifeNature;

    @FXML
    private Pane blackBlurredBox;

    ChangeScene change = new ChangeScene();

    @FXML
    public void initialize() {
        blackBlurredBox.setPadding(new Insets(20, 20, 20, 20));

        numberOfPeopleField.textProperty().addListener((observable, oldValue, newValue) -> {
            peopleLabel.setText("Entered: " + newValue);
        });

        numberOfDaysField.textProperty().addListener((observable, oldValue, newValue) -> {
            daysLabel.setText("Entered: " + newValue);
        });

        districtField.textProperty().addListener((observable, oldValue, newValue) -> {
            districtLabel.setText("Entered: " + newValue);
        });

        divisionField.textProperty().addListener((observable, oldValue, newValue) -> {
            divisionLabel.setText("Entered: " + newValue);
        });
    }
    private boolean validateInput() {
        return !numberOfPeopleField.getText().isEmpty() &&
                !numberOfDaysField.getText().isEmpty() &&
                !districtField.getText().isEmpty() &&
                !divisionField.getText().isEmpty();
    }
    @FXML
    private void handleGenerateButton() {
        if (!validateInput()) {
            showAlert(Alert.AlertType.WARNING, "Unfulfilled Boxes", "Please fill all the boxes before generating the itinerary.");
            return;
        }

        try {
            int numPeople = Integer.parseInt(numberOfPeopleField.getText());
            int numDays = Integer.parseInt(numberOfDaysField.getText());
            String division = divisionField.getText();
            String budget = getBudgetPreference();

            Hotel selectedHotel = fetchHotel(division, budget, numPeople, numDays);

            if (selectedHotel == null) {
                showAlert(Alert.AlertType.ERROR, "No Hotel Found", "No available hotels match your criteria.");
                return;
            }

            // Store data
            ItineraryData.setHotel(selectedHotel);
            ItineraryData.setNumPeople(numPeople);
            ItineraryData.setNumDays(numDays);

            // 🔴 CHANGE SCENE TO RESULT PAGE
            System.out.println("Changing scene to ItineraryResult.fxml...");
            Stage stage = (Stage) generateButton.getScene().getWindow();
            change.changeScene(stage, "ItineraryResult.fxml");

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for people and days.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Could not load Itinerary Result Page.");
        }
    }

    /**
     * Fetches a hotel from the database based on user's selection.
     * Calculates total cost based on people, days, and room requirement.
     */
    private Hotel fetchHotel(String division, String budget, int numPeople, int numDays) {
        String sql = "SELECT * FROM hotel WHERE division = ? AND price_range = ? AND available_rooms > 0 ORDER BY available_rooms DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, division);
            pstmt.setString(2, budget);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int roomsRequired = (int) Math.ceil(numPeople / 2.0);
                double totalCost = roomsRequired * rs.getDouble("nightly_rate") * numDays;

                return new Hotel(
                        rs.getString("name"),
                        rs.getString("amenities"),
                        rs.getDouble("nightly_rate"),
                        totalCost,
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getBudgetPreference() {
        if (lowBudget.isSelected()) return "low";
        if (mediumBudget.isSelected()) return "mid";
        return "high";
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
        Stage stage = (Stage) ItineraryBack.getScene().getWindow();
        change.changeScene(stage, "Home.fxml");
    }
}
