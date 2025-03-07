package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class ItineraryResultController {

    @FXML
    private ImageView backgroundImage; // Added to handle the background image

    @FXML
    private Label hotelNameLabel, amenitiesLabel, nightlyRateLabel, totalCostLabel, emailLabel, phoneLabel;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // Load the background image dynamically
        try {
            Image image = new Image(getClass().getResource("/Images/RESULT.png").toExternalForm());
            backgroundImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Error: Background image not found!");
            e.printStackTrace();
        }

        // Ensure ItineraryData.getHotel() is not null before using it
        Hotel hotel = ItineraryData.getHotel();

        if (hotel != null) {
            hotelNameLabel.setText(hotel.getName());
            amenitiesLabel.setText(hotel.getAmenities());
            nightlyRateLabel.setText(String.format("৳%.2f", hotel.getNightlyRate()));
            totalCostLabel.setText(String.format("৳%.2f", hotel.getTotalCost()));
            emailLabel.setText(" " + hotel.getEmail());
            phoneLabel.setText("" + hotel.getPhoneNumber());
        } else {
            hotelNameLabel.setText("No hotel found for the selected criteria.");
            amenitiesLabel.setText("N/A");
            nightlyRateLabel.setText("N/A");
            totalCostLabel.setText("N/A");
            emailLabel.setText("N/A");
            phoneLabel.setText("N/A");
        }
    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        ChangeScene change = new ChangeScene(); // Initialize ChangeScene object
        change.changeScene(stage, "itineraryUI.fxml"); // Ensure the file name is correct
    }
}
