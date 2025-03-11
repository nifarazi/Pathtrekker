package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView Review;

    @FXML
    private Button Destination, Hotel, Itinerary, Em, Transport, LocalAttraction, Map, TravelMoments;

    @FXML
    private Label UsernameSpace;

    ChangeScene cs = new ChangeScene();

    @FXML
    void DestinationAction(MouseEvent event) {
        changeScene("DestinationSearch.fxml", Destination);
    }

    @FXML
    void HotelsAction(MouseEvent event) {
        Stage stage = (Stage) Hotel.getScene().getWindow();
        try {
            HotelApp hotelApp = new HotelApp();
            hotelApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ItineraryAction(MouseEvent event) {
        changeScene("itineraryUI.fxml", Itinerary);
    }

    @FXML
    void TransportAction(MouseEvent event) {
        Stage stage = (Stage) Transport.getScene().getWindow();
        TransportationSystem ts = new TransportationSystem();
        Scene transportScene = ts.createTransportScene(stage);
        stage.setScene(transportScene);
    }

    @FXML
    void EmAction(MouseEvent event) {
        changeScene("EmergencyServices.fxml", Em);
    }

    @FXML
    void UsernameAction(MouseEvent event) {
        changeScene("profile.fxml", UsernameSpace);
    }

    @FXML
    public void TravelMomentsAction(MouseEvent event) {
        changeScene("Moments.fxml", TravelMoments);
    }

    @FXML
    void LocalAttractionAction(MouseEvent event) {
        changeScene("LocalEvents.fxml", LocalAttraction);
    }

    @FXML
    void MapAction(MouseEvent event) {
        changeScene("Map.fxml", Map);
    }

    @FXML
    void ReviewAction(MouseEvent event) {
        changeScene("ReviewComment.fxml", Review);
    }

    private void changeScene(String fxmlFile, javafx.scene.Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            cs.changeScene(stage, fxmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String username = ProfileUserJDBC.getCurrentUsername();
        UsernameSpace.setText(username);
        // Apply styles to all buttons with specified width and height
        configureButtonStyles(Transport, 442, 39);
        configureButtonStyles(Destination, 372, 20);
        configureButtonStyles(Itinerary, 405, 32);
        configureButtonStyles(Hotel, 201, 30);
        configureButtonStyles(TravelMoments, 270, 35);
        configureButtonStyles(Em, 294, 29);
        configureButtonStyles(LocalAttraction, 210, 5);
        configureButtonStyles(Map, 80, 29);
    }

    private void configureButtonStyles(Button button, double width, double height) {
        if (button != null) {
            button.setPrefWidth(width);
            button.setPrefHeight(height);

            // Default style
            button.setStyle("-fx-background-color: #18392B; -fx-text-fill: #90CAB3; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5 15; -fx-background-radius: 10px;");

            // Hover effect
            button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #2A5C43; -fx-text-fill: #90CAB3; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5 15; -fx-background-radius: 10px;"));

            // Mouse exit effect (revert to default)
            button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #18392B; -fx-text-fill: #90CAB3; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5 15; -fx-background-radius: 10px;"));

            // Button pressed effect
            button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #10261D; -fx-text-fill: #90CAB3; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5 15; -fx-background-radius: 10px;"));

            // Button released effect (revert to default)
            button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #18392B; -fx-text-fill: #90CAB3; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5 15; -fx-background-radius: 10px;"));
        }
    }
}