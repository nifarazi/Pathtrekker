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

        Button homeButton = new Button("Back to Search");
        homeButton.setStyle("-fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black; -fx-padding: 8 20;");
        homeButton.setOnAction(e -> hotelApp.showHotelInputPage(primaryStage));

        Button homeControllerButton = new Button("Home Page");
        homeControllerButton.setStyle("-fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black; -fx-padding: 8 20;");
        homeControllerButton.setOnAction(e -> hotelApp.loadHomeScene());

        buttonBox.getChildren().addAll(homeButton, homeControllerButton);


        root.setCenter(gridPane);
        root.setBottom(buttonBox);


        Scene resultScene = new Scene(root, 1080, 768);
        primaryStage.setScene(resultScene);
        primaryStage.show();
    }
}
