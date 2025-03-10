package com.example.pathtrekker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class HotelResultsPage {
    private Stage primaryStage;
    private HotelApp hotelApp;

    public HotelResultsPage(Stage primaryStage, HotelApp hotelApp) {
        this.primaryStage = primaryStage;
        this.hotelApp = hotelApp;
    }

    public void showResults(String division, String budget, int roomsRequired) {
        HotelService hotelService = new HotelService();
        List<String> hotelList = hotelService.getHotels(division, budget, roomsRequired);

        BorderPane root = new BorderPane();

        try {
            Image backgroundImage = new Image(getClass().getResourceAsStream("/hotelssuggestion.png"));
            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1080, 768, false, false, true, false)
            );
            root.setBackground(new Background(background));
        } catch (Exception e) {
            System.err.println("Error loading background: " + e.getMessage());
        }

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(30);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(50, 50, 50, 50));

        int row = 0, col = 0;
        int maxCols = 2;

        if (hotelList.isEmpty()) {
            Label noResults = new Label("No hotels available for your search criteria.");
            noResults.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-font-weight: bold;");
            gridPane.add(noResults, 0, 0, maxCols, 1); // Center align across full width
        } else {
            for (String hotel : hotelList) {
                Label hotelLabel = new Label(hotel);
                hotelLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 10px; -fx-border-color: #000000; -fx-border-radius: 5px;");
                hotelLabel.setMaxWidth(400);
                hotelLabel.setWrapText(true);

                gridPane.add(hotelLabel, col, row);
                col++;

                if (col >= maxCols) {
                    col = 0;
                    row++;
                }
            }
        }

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 20, 0));

        // Back to Search Button with interactive styles
        Button homeButton = new Button("Back to Search");
        homeButton.setStyle("-fx-font-weight: bold; -fx-background-color: #85aa9b; -fx-text-fill: white; -fx-padding: 8 20;");
        configureButtonStyles(homeButton); // Apply interactive styles
        homeButton.setOnAction(e -> hotelApp.showHotelInputPage(primaryStage));

        // Home Page Button with interactive styles
        Button homeControllerButton = new Button("Home Page");
        homeControllerButton.setStyle("-fx-font-weight: bold; -fx-background-color: #85aa9b; -fx-text-fill: white; -fx-padding: 8 20;");
        configureButtonStyles(homeControllerButton); // Apply interactive styles
        homeControllerButton.setOnAction(e -> hotelApp.loadHomeScene());

        buttonBox.getChildren().addAll(homeButton, homeControllerButton);

        root.setCenter(gridPane);
        root.setBottom(buttonBox);

        Scene resultScene = new Scene(root, 1080, 768);
        primaryStage.setScene(resultScene);
        primaryStage.show();
    }

    // Method to configure interactive button styles
    private void configureButtonStyles(Button button) {
        // Default style (already set in button creation, but reinforced here)
        button.setStyle("-fx-background-color: #85aa9b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20;");

        // Hover effect
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20;"));

        // Mouse exit effect
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #85aa9b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20;"));

        // Button pressed effect
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #248977; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20;"));

        // Button released effect
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20;"));
    }
}