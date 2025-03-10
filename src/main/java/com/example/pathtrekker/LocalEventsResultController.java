package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class LocalEventsResultController {

    @FXML
    private GridPane resultsGrid;

    @FXML
    private Button backButton;

    private static String selectedDivision;
    private final ChangeScene cs = new ChangeScene();

    public static void setSelectedDivision(String division) {
        selectedDivision = division;
    }

    @FXML
    public void initialize() {
        // Apply custom styles to backButton
        setButtonStyle(backButton);

        if (selectedDivision != null && !selectedDivision.isEmpty()) {
            loadEvents(selectedDivision);
        } else {
            Label noSelectionLabel = new Label("No division selected.");
            noSelectionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
            resultsGrid.add(noSelectionLabel, 0, 0);
        }
    }

    private void loadEvents(String division) {
        resultsGrid.getChildren().clear();
        try {
            List<LocalEvent> events = LocalEventsJDBC.getEventsByDivision(division);
            if (events == null || events.isEmpty()) {
                Label noEventsLabel = new Label("No events available in " + division);
                noEventsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
                resultsGrid.add(noEventsLabel, 0, 0);
            } else {
                int row = 0;
                for (LocalEvent event : events) {
                    GridPane eventBox = new GridPane();
                    eventBox.setHgap(10);
                    eventBox.setVgap(5);
                    eventBox.setStyle("-fx-border-color: #588b76; -fx-padding: 15; -fx-background-color: #d0ded8; -fx-background-radius: 10;");

                    Label eventName = new Label("Event: " + event.getEventName());
                    Label dateLabel = new Label("Date: " + event.getStartDate() + " to " + event.getEndDate());
                    Label timeLabel = new Label("Time: " + event.getOpeningTime() + " - " + event.getClosingTime());
                    Label locationLabel = new Label("Location: " + event.getLocation());
                    Label descriptionLabel = new Label(event.getDescription());

                    eventBox.add(eventName, 0, 0);
                    eventBox.add(dateLabel, 0, 1);
                    eventBox.add(timeLabel, 0, 2);
                    eventBox.add(locationLabel, 0, 3);
                    eventBox.add(descriptionLabel, 0, 4);

                    resultsGrid.add(eventBox, 0, row++);
                }
            }
        } catch (Exception e) {
            Label errorLabel = new Label("Error loading events: " + e.getMessage());
            errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
            resultsGrid.add(errorLabel, 0, 0);
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

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #67B99A; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #67B99A; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #248977; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));
    }
}