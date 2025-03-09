package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ItineraryController {

    @FXML
    private Button ItineraryBack, generateButton;
    @FXML
    private TextField numberOfPeopleField, numberOfDaysField; // Removed districtField and divisionField
    @FXML
    private Label peopleLabel, daysLabel;
    @FXML
    private ComboBox<String> divisionComboBox, districtComboBox; // Added ComboBox for divisions and districts
    @FXML
    private CheckBox lowBudget, mediumBudget, highBudget, mountainPerson, beachPerson;
    @FXML
    private CheckBox summerSeason, autumnSeason, springSeason, winterSeason;
    @FXML
    private CheckBox cityPerson, countryPerson, historicalSites, adventureSports, foodCulture, wildlifeNature;
    @FXML
    private Pane blackBlurredBox;

    // Map to store divisions and their corresponding districts
    private final Map<String, List<String>> divisionDistrictMap = new HashMap<>();

    ChangeScene change = new ChangeScene();

    @FXML
    public void initialize() {
        blackBlurredBox.setPadding(new Insets(20, 20, 20, 20));
        numberOfPeopleField.textProperty().addListener((observable, oldValue, newValue) -> peopleLabel.setText("Entered: " + newValue));
        numberOfDaysField.textProperty().addListener((observable, oldValue, newValue) -> daysLabel.setText("Entered: " + newValue));

        // Initialize division and district ComboBox
        initializeDivisionDistrictMap();
        divisionComboBox.getItems().addAll(divisionDistrictMap.keySet());
        divisionComboBox.setPromptText("Select Division");

        // Add listener to division ComboBox to update district ComboBox
        divisionComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                districtComboBox.getItems().clear();
                districtComboBox.getItems().addAll(divisionDistrictMap.get(newValue));
                districtComboBox.setPromptText("Select District");
            }
        });

        setButtonStyle(ItineraryBack);
        setButtonStyle(generateButton);
    }

    // Initialize the division-district map
    private void initializeDivisionDistrictMap() {
        divisionDistrictMap.put("Dhaka", Arrays.asList(
                "Dhaka", "Narayanganj", "Gazipur", "Manikganj", "Munshiganj", "Narsingdi",
                "Tangail", "Kishoreganj", "Netrokona", "Faridpur", "Gopalganj", "Madaripur",
                "Rajbari", "Shariatpur"
        ));

        divisionDistrictMap.put("Chattogram", Arrays.asList(
                "Chattogram", "Cox's Bazar", "Bandarban", "Khagrachari", "Rangamati",
                "Noakhali", "Feni", "Laxmipur", "Chandpur", "Cumilla", "Brahmanbaria"
        ));

        divisionDistrictMap.put("Sylhet", Arrays.asList(
                "Sylhet", "Moulvibazar", "Habiganj", "Sunamganj"
        ));

        divisionDistrictMap.put("Rajshahi", Arrays.asList(
                "Rajshahi", "Natore", "Chapainawabganj", "Pabna", "Naogaon",
                "Bogura", "Joypurhat", "Sirajganj"
        ));

        divisionDistrictMap.put("Khulna", Arrays.asList(
                "Khulna", "Bagerhat", "Satkhira", "Jessore", "Jhenaidah",
                "Magura", "Narail", "Kushtia", "Chuadanga", "Meherpur"
        ));

        divisionDistrictMap.put("Barishal", Arrays.asList(
                "Barishal", "Patuakhali", "Bhola", "Jhalokathi", "Barguna", "Pirojpur"
        ));

        divisionDistrictMap.put("Rangpur", Arrays.asList(
                "Rangpur", "Dinajpur", "Gaibandha", "Kurigram", "Lalmonirhat",
                "Nilphamari", "Panchagarh", "Thakurgaon"
        ));

        divisionDistrictMap.put("Mymensingh", Arrays.asList(
                "Mymensingh", "Netrokona", "Sherpur", "Jamalpur"
        ));


    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #90EE90; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #32CD32; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #90EE90; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #006400; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #32CD32; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
    }


    private boolean validateInput() {
        return !numberOfPeopleField.getText().isEmpty() &&
                !numberOfDaysField.getText().isEmpty() &&
                divisionComboBox.getValue() != null && // Check if division is selected
                districtComboBox.getValue() != null && // Check if district is selected
                (lowBudget.isSelected() || mediumBudget.isSelected() || highBudget.isSelected()) &&
                (mountainPerson.isSelected() || beachPerson.isSelected()) &&
                (summerSeason.isSelected() || autumnSeason.isSelected() || springSeason.isSelected() || winterSeason.isSelected()) &&
                (cityPerson.isSelected() || countryPerson.isSelected() || historicalSites.isSelected() || adventureSports.isSelected() || foodCulture.isSelected() || wildlifeNature.isSelected());
    }

    @FXML
    private void handleGenerateButton() {
        if (!validateInput()) {
            showAlert(Alert.AlertType.WARNING, "Unfulfilled Boxes", "Please fill all the boxes and select all options before generating the itinerary.");
            return;
        }

        try {
            int numPeople = Integer.parseInt(numberOfPeopleField.getText());
            int numDays = Integer.parseInt(numberOfDaysField.getText());
            String division = divisionComboBox.getValue(); // Get selected division
            String district = districtComboBox.getValue(); // Get selected district
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

            List<Event> nightEvents = fetchAndScheduleNightEvents(division, numDays);
            int itineraryId = storeItinerary(division, numPeople, numDays, selectedHotel, destinations, nightEvents);

            // Set all data in ItineraryData
            ItineraryData.setHotel(selectedHotel);
            ItineraryData.setNumPeople(numPeople);
            ItineraryData.setNumDays(numDays);
            ItineraryData.setDestinations(destinations);
            ItineraryData.setNightEvents(nightEvents);
            ItineraryData.setItineraryId(itineraryId);

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
                return new Hotel(
                        rs.getString("name"),
                        rs.getString("amenities"),
                        rs.getDouble("nightly_rate"),
                        totalCost,
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        division // Pass the division parameter
                );
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
        Set<String> usedDestinations = new HashSet<>();
        int destinationsPerDay = 2; // Morning and Afternoon

        for (int day = 1; day <= numDays; day++) {
            Destination morningDest = null;

            // Ensure remaining list is not empty before accessing
            if (!remaining.isEmpty()) {
                for (int i = 0; i < remaining.size(); i++) {
                    if (!usedDestinations.contains(remaining.get(i).getName())) {
                        morningDest = remaining.remove(i);
                        break;
                    }
                }
            }

            // Fallback: If morningDest is still null, pick a random one
            if (morningDest == null && !destinations.isEmpty()) {
                Destination original = destinations.get((int) (Math.random() * destinations.size()));
                morningDest = new Destination(original.getName(), original.getDescription(), original.getTopAttractions(),
                        original.getWeatherInfo(), original.getLocalCuisine(), original.getTransportInfo(),
                        original.getOpeningTime(), original.getClosingTime());
            }

            if (morningDest != null) {
                morningDest.setDay(day);
                morningDest.setTimeSlot("Morning");
                scheduled.add(morningDest);
                usedDestinations.add(morningDest.getName());
            }

            // Find the closest afternoon destination
            Destination afternoonDest = null;
            double minDistance = Double.MAX_VALUE;
            int closestIndex = -1;

            if (morningDest != null && !remaining.isEmpty()) {
                for (int i = 0; i < remaining.size(); i++) {
                    Destination candidate = remaining.get(i);
                    if (!usedDestinations.contains(candidate.getName())) {
                        double distance = DistanceCalculator.getDistance(morningDest.getName(), candidate.getName());
                        if (distance >= 0 && distance < minDistance) {
                            minDistance = distance;
                            afternoonDest = candidate;
                            closestIndex = i;
                        }
                    }
                }
            }

            // Remove the afternoon destination from remaining list safely
            if (afternoonDest != null && closestIndex != -1) {
                remaining.remove(closestIndex);
                afternoonDest.setDay(day);
                afternoonDest.setTimeSlot("Afternoon");
                scheduled.add(afternoonDest);
                usedDestinations.add(afternoonDest.getName());
            } else if (!destinations.isEmpty()) {
                // Fallback for missing afternoon destination
                Destination original = destinations.get((int) (Math.random() * destinations.size()));
                afternoonDest = new Destination(original.getName(), original.getDescription(), original.getTopAttractions(),
                        original.getWeatherInfo(), original.getLocalCuisine(), original.getTransportInfo(),
                        original.getOpeningTime(), original.getClosingTime());
                afternoonDest.setDay(day);
                afternoonDest.setTimeSlot("Afternoon");
                scheduled.add(afternoonDest);
                usedDestinations.add(afternoonDest.getName());
            }
        }

        return scheduled;
    }

    private List<Event> fetchAndScheduleNightEvents(String division, int numDays) {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE division = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, division);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                allEvents.add(new Event(
                        rs.getString("event_name"),
                        rs.getString("opening_time"),
                        rs.getString("closing_time"),
                        rs.getString("location"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Event> scheduledEvents = new ArrayList<>();
        for (int day = 1; day <= numDays; day++) {
            if (day <= allEvents.size()) {
                scheduledEvents.add(allEvents.get(day - 1));
            } else {
                String fallbackName = (day % 2 == 0) ? "Shopping" : "Rest Day";
                scheduledEvents.add(new Event(
                        fallbackName,
                        "18:00:00",
                        "23:00:00",
                        "Local Area",
                        fallbackName.equals("Shopping") ? "Explore local shops and markets." : "Relax at the hotel or nearby."
                ));
            }
        }
        return scheduledEvents;
    }

    private int storeItinerary(String division, int numPeople, int numDays, Hotel hotel, List<Destination> destinations, List<Event> nightEvents) {
        String itinerarySql = "INSERT INTO itineraryhotel (division, num_people, num_days, hotel_name, total_cost) VALUES (?, ?, ?, ?, ?)";
        String destSql = "INSERT INTO itinerary_destinations (itinerary_id, day, time_slot, destination_name) VALUES (?, ?, ?, ?)";
        String eventSql = "INSERT INTO itinerary_events (itinerary_id, day, event_name) VALUES (?, ?, ?)";

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

                try (PreparedStatement eventStmt = conn.prepareStatement(eventSql)) {
                    for (int i = 0; i < nightEvents.size(); i++) {
                        Event event = nightEvents.get(i);
                        eventStmt.setInt(1, itineraryId);
                        eventStmt.setInt(2, i + 1);
                        eventStmt.setString(3, event.getName());
                        eventStmt.addBatch();
                    }
                    eventStmt.executeBatch();
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