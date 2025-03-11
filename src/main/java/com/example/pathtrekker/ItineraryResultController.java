package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
public class ItineraryResultController {

    @FXML
    private Label hotelNameLabel, amenitiesLabel, nightlyRateLabel, totalCostLabel, emailLabel, phoneLabel, commentDisplayLabel;
    @FXML
    private Button backButton, saveCommentButton, savePdfButton;
    @FXML
    private VBox destinationsVBox;
    @FXML
    private TextArea commentArea;
    @FXML
    private TextField itineraryNameField;

    private int itineraryId;
    private static final float PAGE_TOP = 750;
    private static final float PAGE_BOTTOM = 50;
    private static final float LINE_SPACING = 15;

    @FXML
    public void initialize() {
        itineraryId = ItineraryData.getItineraryId();

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

        loadComment();
        refreshDestinations();
    }

    private void refreshDestinations() {
        destinationsVBox.getChildren().clear();
        List<Destination> destinations = ItineraryData.getDestinations();
        List<Event> nightEvents = ItineraryData.getNightEvents();

        if (destinations != null && !destinations.isEmpty()) {
            for (int day = 1; day <= ItineraryData.getNumDays(); day++) {
                final int currentDay = day; // For lambda compatibility
                VBox dayBox = new VBox(10);
                Label dayLabel = new Label("Day " + day);
                dayLabel.setFont(new Font("Roboto", 18));
                dayLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Roboto';");
                dayBox.getChildren().add(dayLabel);

                List<Destination> dayDestinations = destinations.stream()
                        .filter(d -> d.getDay() == currentDay)
                        .collect(Collectors.toList());

                for (int i = 0; i < destinations.size(); i++) {
                    Destination dest = destinations.get(i);
                    if (dest.getDay() == day) {
                        VBox detailsBox = new VBox(5);
                        detailsBox.getChildren().addAll(
                                createLabel(dest.getTimeSlot() + " - Name: " + dest.getName()),
                                createLabel("Description: " + dest.getDescription()),
                                createLabel("Top Attractions: " + dest.getTopAttractions()),
                                createLabel("Local Cuisine: " + dest.getLocalCuisine()),
                                createLabel("Transport Info: " + dest.getTransportInfo()),
                                createLabel("Open: " + dest.getOpeningTime() + " - Close: " + dest.getClosingTime())
                        );

                        if ("Morning".equals(dest.getTimeSlot())) {
                            Destination nextDest = dayDestinations.stream()
                                    .filter(d -> "Afternoon".equals(d.getTimeSlot()))
                                    .findFirst().orElse(null);
                            if (nextDest != null) {
                                double distance = DistanceCalculator.getDistance(dest.getName(), nextDest.getName());
                                detailsBox.getChildren().add(createLabel("Distance to Afternoon: " + String.format("%.2f km", distance)));
                            }
                        } else if ("Afternoon".equals(dest.getTimeSlot()) && dayDestinations.stream().noneMatch(d -> "Morning".equals(d.getTimeSlot()))) {
                            double distance = DistanceCalculator.getDistance(ItineraryData.getHotel().getName(), dest.getName());
                            detailsBox.getChildren().add(createLabel("Distance from Hotel: " + String.format("%.2f km", distance)));
                        }

                        // Buttons layout
                        Button showMapButton = new Button("Show Map");
                        showMapButton.setFont(new Font("Roboto", 16));
                        final int currentIndex = i;
                        showMapButton.setOnAction(e -> showMap(dest, currentIndex, destinations));

                        ComboBox<String> swapComboBox = new ComboBox<>();
                        swapComboBox.getItems().addAll(fetchAvailableDestinations(ItineraryData.getHotel().getDivision(), destinations));
                        swapComboBox.setPromptText("Swap with...");

                        Button swapButton = new Button("Swap");
                        swapButton.setFont(new Font("Roboto", 16));
                        swapButton.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-weight: bold;");
                        swapButton.setOnAction(e -> swapDestination(currentIndex, swapComboBox.getValue()));

                        Button deleteButton = new Button("Delete");
                        deleteButton.setFont(new Font("Roboto", 16));
                        deleteButton.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white; -fx-font-weight: bold;");
                        deleteButton.setOnAction(e -> deleteDestination(currentIndex));

                        // Horizontal layout for top buttons, vertical for delete
                        HBox topButtonBox = new HBox(10, showMapButton, swapComboBox, swapButton);
                        dayBox.getChildren().addAll(detailsBox, topButtonBox, deleteButton);
                    }
                }

                if (nightEvents != null && day - 1 < nightEvents.size()) {
                    Event nightEvent = nightEvents.get(day - 1);
                    dayBox.getChildren().addAll(
                            createLabel("Night - Event: " + nightEvent.getName()),
                            createLabel("Open: " + nightEvent.getOpeningTime() + " - Close: " + nightEvent.getClosingTime()),
                            createLabel("Location: " + nightEvent.getLocation()),
                            createLabel("Description: " + nightEvent.getDescription())
                    );
                }

                destinationsVBox.getChildren().add(dayBox);
            }
        }
    }
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Roboto", 16));
        label.setWrapText(true);
        label.setMaxWidth(500);
        return label;
    }

    private void deleteDestination(int index) {
        List<Destination> destinations = ItineraryData.getDestinations();
        Destination toDelete = destinations.get(index);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM itinerary_destinations WHERE itinerary_id = ? AND day = ? AND time_slot = ?")) {
            pstmt.setInt(1, itineraryId);
            pstmt.setInt(2, toDelete.getDay());
            pstmt.setString(3, toDelete.getTimeSlot());
            pstmt.executeUpdate();

            destinations.remove(index);
            ItineraryData.setDestinations(destinations);
            refreshDestinations();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting destination.");
            alert.showAndWait();
        }
    }

    private void showMap(Destination dest, int index, List<Destination> destinations) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathtrekker/Map.fxml"));
            Parent root = loader.load();
            String loc1, loc2;

            boolean isFirstDestinationOfTheDay = "Morning".equals(dest.getTimeSlot()) &&
                    destinations.stream().noneMatch(d -> d.getDay() == dest.getDay() && "Morning".equals(d.getTimeSlot()) && d != dest);
            if (isFirstDestinationOfTheDay) {
                loc1 = ItineraryData.getHotel().getName();
                loc2 = dest.getName();
            } else if ("Afternoon".equals(dest.getTimeSlot())) {
                Destination morningDest = destinations.stream()
                        .filter(d -> d.getDay() == dest.getDay() && "Morning".equals(d.getTimeSlot()))
                        .findFirst()
                        .orElse(null);
                loc1 = (morningDest != null) ? morningDest.getName() : ItineraryData.getHotel().getName();
                loc2 = dest.getName();
            } else {
                return; // Shouldn't happen with current logic
            }

            MapController.setLocations(loc1, loc2);
            Stage stage = (Stage) destinationsVBox.getScene().getWindow();
            stage.setScene(new Scene(root, 1080, 756));
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

    private List<String> fetchAvailableDestinations(String division, List<Destination> currentDestinations) {
        List<String> allDestinations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM destinationsDivisions WHERE division = ?")) {
            pstmt.setString(1, division);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                allDestinations.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Exclude destinations already in the itinerary
        List<String> currentNames = currentDestinations.stream()
                .map(Destination::getName)
                .collect(Collectors.toList());
        return allDestinations.stream()
                .filter(name -> !currentNames.contains(name))
                .collect(Collectors.toList());
    }

    private void swapDestination(int index, String newDestName) {
        if (newDestName == null || newDestName.trim().isEmpty()) return;

        List<Destination> destinations = ItineraryData.getDestinations();
        Destination oldDest = destinations.get(index);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM destinationsDivisions WHERE name = ?")) {
            pstmt.setString(1, newDestName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Destination newDest = new Destination(
                        rs.getString("name"), rs.getString("description"), rs.getString("top_attractions"),
                        rs.getString("weather_info"), rs.getString("local_cuisine"), rs.getString("transport_info"),
                        rs.getString("opening_time"), rs.getString("closing_time")
                );
                newDest.setDay(oldDest.getDay());
                newDest.setTimeSlot(oldDest.getTimeSlot());

                destinations.set(index, newDest);
                ItineraryData.setDestinations(destinations);

                try (PreparedStatement updateStmt = conn.prepareStatement(
                        "UPDATE itinerary_destinations SET destination_name = ? WHERE itinerary_id = ? AND day = ? AND time_slot = ?")) {
                    updateStmt.setString(1, newDestName);
                    updateStmt.setInt(2, itineraryId);
                    updateStmt.setInt(3, oldDest.getDay());
                    updateStmt.setString(4, oldDest.getTimeSlot());
                    updateStmt.executeUpdate();
                }

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
    private void saveItineraryAsPdf() {
        String itineraryName = itineraryNameField.getText().trim();
        if (itineraryName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name for the itinerary.");
            alert.showAndWait();
            return;
        }

        itineraryName = itineraryName.replaceAll("[<>:\"/\\\\|?*]", "_");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            float yPosition = PAGE_TOP;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(25, yPosition);
            contentStream.showText("Itinerary: " + itineraryName);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            yPosition -= LINE_SPACING;
            contentStream.newLineAtOffset(0, -LINE_SPACING);

            AddTextResult result = addText(document, contentStream, "Hotel Information:", yPosition, page);
            contentStream = result.contentStream;
            yPosition = result.yPosition;
            Hotel hotel = ItineraryData.getHotel();
            if (hotel != null) {
                result = addText(document, contentStream, "Name: " + hotel.getName(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Amenities: " + hotel.getAmenities(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Nightly Rate: BDT " + String.format("%.2f", hotel.getNightlyRate()), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Total Cost: BDT " + String.format("%.2f", hotel.getTotalCost()), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Email: " + hotel.getEmail(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Phone: " + hotel.getPhoneNumber(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition - LINE_SPACING;
            }

            result = addText(document, contentStream, "Destinations:", yPosition, page);
            contentStream = result.contentStream;
            yPosition = result.yPosition;
            List<Destination> destinations = ItineraryData.getDestinations();
            for (Destination dest : destinations) {
                result = addText(document, contentStream, "Day " + dest.getDay() + " - " + dest.getTimeSlot(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Name: " + dest.getName(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Description: " + dest.getDescription(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Top Attractions: " + dest.getTopAttractions(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Local Cuisine: " + dest.getLocalCuisine(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Transport Info: " + dest.getTransportInfo(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Open: " + dest.getOpeningTime() + " - Close: " + dest.getClosingTime(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition - LINE_SPACING;
            }

            result = addText(document, contentStream, "Night Events:", yPosition, page);
            contentStream = result.contentStream;
            yPosition = result.yPosition;
            List<Event> nightEvents = ItineraryData.getNightEvents();
            for (int i = 0; i < nightEvents.size(); i++) {
                Event event = nightEvents.get(i);
                result = addText(document, contentStream, "Day " + (i + 1) + " - Night Event", yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Name: " + event.getName(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Open: " + event.getOpeningTime() + " - Close: " + event.getClosingTime(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Location: " + event.getLocation(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition;

                result = addText(document, contentStream, "Description: " + event.getDescription(), yPosition, page);
                contentStream = result.contentStream;
                yPosition = result.yPosition - LINE_SPACING;
            }

            String comment = commentDisplayLabel.getText();
            if (!comment.isEmpty()) {
                result = addText(document, contentStream, "Comment: " + comment, yPosition, page);
                contentStream = result.contentStream;
            }

            contentStream.endText();
            contentStream.close();

            String filePath = System.getProperty("user.home") + "/Downloads/" + itineraryName + ".pdf";
            File pdfFile = new File(filePath);
            if (!pdfFile.getParentFile().exists()) {
                pdfFile.getParentFile().mkdirs();
            }
            document.save(pdfFile);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            byte[] pdfBytes = baos.toByteArray();

            savePdfToDatabase(itineraryName, pdfBytes);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Itinerary saved as PDF at " + filePath + " and in the database.");
            alert.showAndWait();
            itineraryNameField.clear();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save itinerary as PDF: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private static class AddTextResult {
        PDPageContentStream contentStream;
        float yPosition;

        AddTextResult(PDPageContentStream contentStream, float yPosition) {
            this.contentStream = contentStream;
            this.yPosition = yPosition;
        }
    }

    private AddTextResult addText(PDDocument document, PDPageContentStream contentStream, String text, float yPosition, PDPage currentPage) throws IOException {
        if (yPosition < PAGE_BOTTOM) {
            contentStream.endText();
            contentStream.close();
            PDPage newPage = new PDPage();
            document.addPage(newPage);
            contentStream = new PDPageContentStream(document, newPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(25, PAGE_TOP);
            yPosition = PAGE_TOP;
        }
        contentStream.showText(text);
        contentStream.newLineAtOffset(0, -LINE_SPACING);
        return new AddTextResult(contentStream, yPosition - LINE_SPACING);
    }

    private void savePdfToDatabase(String itineraryName, byte[] pdfBytes) {
        String sql = "INSERT INTO saved_itineraries (itinerary_name, username, itinerary_id, pdf_data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itineraryName);
            String username = ProfileUserJDBC.getCurrentUsername();
            if (username == null || username.isEmpty()) {
                username = "guest";
            }
            pstmt.setString(2, username);
            pstmt.setInt(3, itineraryId);
            pstmt.setBytes(4, pdfBytes);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save PDF to database: " + e.getMessage());
        }
    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        ChangeScene change = new ChangeScene();
        change.changeScene(stage, "itineraryUI.fxml");
    }
}