package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        numberOfPeopleField.textProperty().addListener((observable, oldValue, newValue) -> peopleLabel.setText("Entered: " + newValue));
        numberOfDaysField.textProperty().addListener((observable, oldValue, newValue) -> daysLabel.setText("Entered: " + newValue));
        districtField.textProperty().addListener((observable, oldValue, newValue) -> districtLabel.setText("Entered: " + newValue));
        divisionField.textProperty().addListener((observable, oldValue, newValue) -> divisionLabel.setText("Entered: " + newValue));
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

            List<Destination> destinations = fetchAndScheduleDestinations(division, numDays);
            if (destinations.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "No Destinations", "No destinations found for your preferences.");
                return;
            }

            int itineraryId = storeItinerary(division, numPeople, numDays, selectedHotel, destinations);

            ItineraryData.setHotel(selectedHotel);
            ItineraryData.setNumPeople(numPeople);
            ItineraryData.setNumDays(numDays);
            ItineraryData.setDestinations(destinations);

            Stage stage = (Stage) generateButton.getScene().getWindow();
            change.changeScene(stage, "ItineraryResult.fxml");

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for people and days.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Could not load Itinerary Result Page.");
        }
    }

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
                return new Hotel(rs.getString("name"), rs.getString("amenities"), rs.getDouble("nightly_rate"),
                        totalCost, rs.getString("email"), rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Destination> fetchAndScheduleDestinations(String division, int numDays) {
        List<Destination> allDestinations = new ArrayList<>();
        String sql = "SELECT * FROM final_destinations WHERE division = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, division);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                allDestinations.add(new Destination(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("top_attractions"),
                        rs.getString("weather_info"),
                        rs.getString("local_cuisine"),
                        rs.getString("transport_info"),
                        rs.getString("opening_time"),
                        rs.getString("closing_time")
                ));
            }

            return scheduleDestinationsByDistance(allDestinations, numDays);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDestinations;
    }

    private List<Destination> scheduleDestinationsByDistance(List<Destination> destinations, int numDays) {
        List<Destination> scheduled = new ArrayList<>();
        List<Destination> remaining = new ArrayList<>(destinations);
        Set<String> usedDestinations = new HashSet<>(); // Track used destinations
        int destinationsPerDay = 2;
        int totalDestinationsNeeded = Math.min(numDays * destinationsPerDay, destinations.size());

        for (int day = 1; day <= numDays && !remaining.isEmpty(); day++) {
            // Select morning destination
            Destination morningDest = null;
            for (int i = 0; i < remaining.size(); i++) {
                if (!usedDestinations.contains(remaining.get(i).getName())) {
                    morningDest = remaining.remove(i);
                    break;
                }
            }
            if (morningDest == null) break; // No unused destinations left

            morningDest.setDay(day);
            morningDest.setTimeSlot("Morning");
            scheduled.add(morningDest);
            usedDestinations.add(morningDest.getName());

            // Find a unique afternoon destination with least distance
            Destination afternoonDest = null;
            double minDistance = Double.MAX_VALUE;
            int closestIndex = -1;

            for (int i = 0; i < remaining.size(); i++) {
                Destination candidate = remaining.get(i);
                if (!usedDestinations.contains(candidate.getName())) { // Ensure not used before
                    double distance = DistanceCalculator.getDistance(morningDest.getName(), candidate.getName());
                    if (distance >= 0 && distance < minDistance) {
                        minDistance = distance;
                        afternoonDest = candidate;
                        closestIndex = i;
                    }
                }
            }

            if (afternoonDest != null && remaining.size() > 0) { // Ensure there's something to remove
                remaining.remove(closestIndex);
                afternoonDest.setDay(day);
                afternoonDest.setTimeSlot("Afternoon");
                scheduled.add(afternoonDest);
                usedDestinations.add(afternoonDest.getName());
            } else {
                System.out.println("No unique afternoon destination found for Day " + day);
            }
        }

        return scheduled;
    }

    private int storeItinerary(String division, int numPeople, int numDays, Hotel hotel, List<Destination> destinations) {
        String itinerarySql = "INSERT INTO itineraryhotel (division, num_people, num_days, hotel_name, total_cost) VALUES (?, ?, ?, ?, ?)";
        String destSql = "INSERT INTO itinerary_destinations (itinerary_id, day, time_slot, destination_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement itineraryStmt = conn.prepareStatement(itinerarySql, Statement.RETURN_GENERATED_KEYS)) {
            itineraryStmt.setString(1, division);
            itineraryStmt.setInt(2, numPeople);
            itineraryStmt.setInt(3, numDays);
            itineraryStmt.setString(4, hotel.getName());
            itineraryStmt.setDouble(5, hotel.getTotalCost());
            itineraryStmt.executeUpdate();

            ResultSet rs = itineraryStmt.getGeneratedKeys();
            int itineraryId = rs.next() ? rs.getInt(1) : -1;

            if (itineraryId != -1) {
                try (PreparedStatement destStmt = conn.prepareStatement(destSql)) {
                    for (Destination dest : destinations) {
                        destStmt.setInt(1, itineraryId);
                        destStmt.setInt(2, dest.getDay());
                        destStmt.setString(3, dest.getTimeSlot());
                        destStmt.setString(4, dest.getName());
                        destStmt.addBatch();
                    }
                    destStmt.executeBatch();
                }
            }
            return itineraryId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
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