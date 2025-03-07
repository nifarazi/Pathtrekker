package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.List;

public class ItineraryResultController {

    @FXML
    private Label hotelNameLabel, amenitiesLabel, nightlyRateLabel, totalCostLabel, emailLabel, phoneLabel;
    @FXML
    private Button backButton;
    @FXML
    private VBox destinationsVBox;

    @FXML
    public void initialize() {
        Hotel hotel = ItineraryData.getHotel();
        if (hotel != null) {
            hotelNameLabel.setText(hotel.getName());
            amenitiesLabel.setText(hotel.getAmenities());
            nightlyRateLabel.setText(String.format("৳%.2f", hotel.getNightlyRate()));
            totalCostLabel.setText(String.format("৳%.2f", hotel.getTotalCost()));
            emailLabel.setText(" " + hotel.getEmail());
            phoneLabel.setText("" + hotel.getPhoneNumber());
        } else {
            hotelNameLabel.setText("No hotel found.");
            amenitiesLabel.setText("N/A");
            nightlyRateLabel.setText("N/A");
            totalCostLabel.setText("N/A");
            emailLabel.setText("N/A");
            phoneLabel.setText("N/A");
        }

        List<Destination> destinations = ItineraryData.getDestinations();
        List<Event> nightEvents = ItineraryData.getNightEvents();

        if (destinations != null && !destinations.isEmpty()) {
            for (int day = 1; day <= ItineraryData.getNumDays(); day++) {
                VBox dayBox = new VBox(10);
                Label dayLabel = new Label("Day " + day);
                dayLabel.setFont(new Font(18));
                dayBox.getChildren().add(dayLabel);

                // Add morning and afternoon destinations
                for (int i = 0; i < destinations.size(); i++) {
                    Destination dest = destinations.get(i);
                    if (dest.getDay() == day) {
                        dayBox.getChildren().add(createLabel(dest.getTimeSlot() + " - Name: " + dest.getName()));
                        dayBox.getChildren().add(createLabel("Description: " + dest.getDescription()));
                        dayBox.getChildren().add(createLabel("Top Attractions: " + dest.getTopAttractions()));
                        dayBox.getChildren().add(createLabel("Local Cuisine: " + dest.getLocalCuisine()));
                        dayBox.getChildren().add(createLabel("Transport Info: " + dest.getTransportInfo()));
                        dayBox.getChildren().add(createLabel("Open: " + dest.getOpeningTime() + " - Close: " + dest.getClosingTime()));

                        Destination nextDest = (i + 1 < destinations.size()) ? destinations.get(i + 1) : null;
                        if (nextDest != null && dest.getDay() == nextDest.getDay()) {
                            double distance = DistanceCalculator.getDistance(dest.getName(), nextDest.getName());
                            dayBox.getChildren().add(createLabel("Distance to next: " + String.format("%.2f km", distance)));
                        }

                        Button showMapButton = new Button("Show Map");
                        showMapButton.setFont(new Font(16));
                        final int currentIndex = i;
                        showMapButton.setOnAction(e -> showMap(dest, currentIndex, destinations));
                        dayBox.getChildren().add(showMapButton);
                    }
                }

                // Add night event for the day
                if (nightEvents != null && day - 1 < nightEvents.size()) {
                    Event nightEvent = nightEvents.get(day - 1);
                    dayBox.getChildren().add(createLabel("Night - Event: " + nightEvent.getName()));
                    dayBox.getChildren().add(createLabel("Open: " + nightEvent.getOpeningTime() + " - Close: " + nightEvent.getClosingTime()));
                    dayBox.getChildren().add(createLabel("Location: " + nightEvent.getLocation()));
                    dayBox.getChildren().add(createLabel("Description: " + nightEvent.getDescription()));
                }

                destinationsVBox.getChildren().add(dayBox);
            }
        }
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(16));
        return label;
    }

    private void showMap(Destination dest, int index, List<Destination> destinations) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathtrekker/Map.fxml"));
            Parent root = loader.load();

            MapController mapController = loader.getController();
            String loc1, loc2;

            boolean isFirstDestinationOfTheDay = (index == 0) || (destinations.get(index - 1).getDay() != dest.getDay());

            if (isFirstDestinationOfTheDay) {
                loc1 = ItineraryData.getHotel().getName();
                loc2 = dest.getName();
                System.out.println("Showing map from Hotel → " + loc2);
            } else {
                Destination morningDest = destinations.stream()
                        .filter(d -> d.getDay() == dest.getDay() && d.getTimeSlot().equals("Morning"))
                        .findFirst()
                        .orElse(null);
                loc1 = (morningDest != null) ? morningDest.getName() : ItineraryData.getHotel().getName();
                loc2 = dest.getName();
                System.out.println("Showing map from " + loc1 + " → " + loc2);
            }

            mapController.setLocations(loc1, loc2);

            Stage mapStage = new Stage();
            mapStage.setTitle("Map View");
            mapStage.setScene(new Scene(root, 1080, 756));
            mapStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Map.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        ChangeScene change = new ChangeScene();
        change.changeScene(stage, "itineraryUI.fxml");
    }
}