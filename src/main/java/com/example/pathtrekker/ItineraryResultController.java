package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItineraryResultController {

    @FXML
    private Label hotelNameLabel, amenitiesLabel, nightlyRateLabel, totalCostLabel, emailLabel, phoneLabel, commentDisplayLabel;
    @FXML
    private Button backButton, saveCommentButton;
    @FXML
    private VBox destinationsVBox;
    @FXML
    private TextArea commentArea;

    private int itineraryId; // To track the current itinerary

    @FXML
    public void initialize() {
        // Fetch itineraryId from somewhere (e.g., ItineraryData or passed from ItineraryController)
        itineraryId = ItineraryData.getItineraryId(); // Assume this is added to ItineraryData

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

        // Load existing comment
        loadComment();

        refreshDestinations();
    }

    private void refreshDestinations() {
        destinationsVBox.getChildren().clear();
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

                        // Add swap functionality
                        ComboBox<String> swapComboBox = new ComboBox<>();
                        swapComboBox.getItems().addAll(fetchAvailableDestinations(ItineraryData.getHotel().getDivision()));
                        swapComboBox.setPromptText("Swap with...");
                        Button swapButton = new Button("Swap Destination");
                        swapButton.setFont(new Font(16));
                        swapButton.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-weight: bold;");
                        swapButton.setOnAction(e -> swapDestination(currentIndex, swapComboBox.getValue()));
                        dayBox.getChildren().addAll(swapComboBox, swapButton);
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
    private void saveComment() {
        String comment = commentArea.getText();
        if (comment != null && !comment.trim().isEmpty()) {
            saveCommentToDatabase(comment);
            commentDisplayLabel.setText(comment);
            commentArea.clear();
        }
    }

    private void loadComment() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT comment FROM itineraryhotel WHERE id = ?")) {
            pstmt.setInt(1, itineraryId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String comment = rs.getString("comment");
                if (comment != null) {
                    commentDisplayLabel.setText(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveCommentToDatabase(String comment) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE itineraryhotel SET comment = ? WHERE id = ?")) {
            pstmt.setString(1, comment);
            pstmt.setInt(2, itineraryId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> fetchAvailableDestinations(String division) {
        List<String> destinations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM final_destinations WHERE division = ?")) {
            pstmt.setString(1, division);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                destinations.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    private void swapDestination(int index, String newDestName) {
        if (newDestName == null || newDestName.trim().isEmpty()) {
            return;
        }

        List<Destination> destinations = ItineraryData.getDestinations();
        Destination oldDest = destinations.get(index);

        // Fetch new destination details from database
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM final_destinations WHERE name = ?")) {
            pstmt.setString(1, newDestName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Destination newDest = new Destination(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("top_attractions"),
                        rs.getString("weather_info"),
                        rs.getString("local_cuisine"),
                        rs.getString("transport_info"),
                        rs.getString("opening_time"),
                        rs.getString("closing_time")
                );
                newDest.setDay(oldDest.getDay());
                newDest.setTimeSlot(oldDest.getTimeSlot());

                // Update in-memory list
                destinations.set(index, newDest);
                ItineraryData.setDestinations(destinations);

                // Update database
                try (PreparedStatement updateStmt = conn.prepareStatement(
                        "UPDATE itinerary_destinations SET destination_name = ? WHERE itinerary_id = ? AND day = ? AND time_slot = ?")) {
                    updateStmt.setString(1, newDestName);
                    updateStmt.setInt(2, itineraryId);
                    updateStmt.setInt(3, oldDest.getDay());
                    updateStmt.setString(4, oldDest.getTimeSlot());
                    updateStmt.executeUpdate();
                }

                // Refresh UI
                refreshDestinations();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Destination not found in database.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error swapping destination.");
            alert.showAndWait();
        }
    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        ChangeScene change = new ChangeScene();
        change.changeScene(stage, "itineraryUI.fxml");
    }
}