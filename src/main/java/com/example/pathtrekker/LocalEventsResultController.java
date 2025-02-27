package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class LocalEventsResultController {

    @FXML
    private VBox resultsContainer;

    @FXML
    private Button backButton;

    private static String selectedDivision;
    private final ChangeScene cs = new ChangeScene();

    public static void setSelectedDivision(String division) {
        selectedDivision = division;
    }

    @FXML
    public void initialize() {
        if (selectedDivision != null && !selectedDivision.isEmpty()) {
            loadEvents(selectedDivision);
        } else {
            Label noSelectionLabel = new Label("No division selected.");
            noSelectionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
            resultsContainer.getChildren().add(noSelectionLabel);
        }
    }

    private void loadEvents(String division) {
        resultsContainer.getChildren().clear();
        try {
            List<LocalEvent> events = LocalEventsJDBC.getEventsByDivision(division);
            if (events == null || events.isEmpty()) {
                Label noEventsLabel = new Label("No events available in " + division);
                noEventsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
                resultsContainer.getChildren().add(noEventsLabel);
            } else {
                for (LocalEvent event : events) {
                    VBox eventBox = new VBox(10);
                    eventBox.setStyle("-fx-border-color: #588b76; -fx-padding: 15; -fx-background-color: #d0ded8; -fx-background-radius: 10;");

                    Label eventName = new Label("Event: " + event.getEventName());
                    Label dateLabel = new Label("Date: " + event.getStartDate() + " to " + event.getEndDate());
                    Label timeLabel = new Label("Time: " + event.getOpeningTime() + " - " + event.getClosingTime());
                    Label locationLabel = new Label("Location: " + event.getLocation());
                    Label descriptionLabel = new Label(event.getDescription());

                    eventBox.getChildren().addAll(eventName, dateLabel, timeLabel, locationLabel, descriptionLabel);
                    resultsContainer.getChildren().add(eventBox);
                }
            }
        } catch (Exception e) {
            Label errorLabel = new Label("Error loading events: " + e.getMessage());
            errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
            resultsContainer.getChildren().add(errorLabel);
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "LocalEvents.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error changing scene to LocalEvents.fxml");
        }
    }
}
