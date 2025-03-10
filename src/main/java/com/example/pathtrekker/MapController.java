package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class MapController {

    @FXML
    public Button HomeButton;
    @FXML
    private Button GenerateButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField Location1, Location2;
    @FXML
    private WebView webview;
    @FXML
    private Label Message;

    private static String loc1, loc2;

    public static void setLocations(String location1, String location2) {

        loc1 = location1;
        loc2 = location2;
    }

    @FXML
    public void initialize() {
        if (loc1 != null && loc2 != null) {
            Location1.setText(loc1);
            Location2.setText(loc2);
            showMap(loc1, loc2);
        }
    }

    @FXML
    void GenerateButtonAction(MouseEvent event) {
        String loc1Input = Location1.getText();
        String loc2Input = Location2.getText();

        if (loc1Input.isEmpty() || loc2Input.isEmpty()) {
            Message.setTextFill(Color.RED);
            Message.setText("Please Enter Both Locations.");
            return;
        }

        double distance = DistanceCalculator.getDistance(loc1Input, loc2Input);
        if (distance == -1) {
            Message.setTextFill(Color.RED);
            Message.setText("Error fetching coordinates.");
            return;
        } else {
            Message.setText("Generated Successfully! Distance: " + String.format("%.2f km", distance));
        }

        showMap(loc1Input, loc2Input);
    }

    private void showMap(String location1, String location2) {
        WebEngine webEngine = webview.getEngine();
        String googleMapsUrl = "https://www.google.com/maps/dir/" +
                location1.replace(" ", "+") + "/" +
                location2.replace(" ", "+");
        webEngine.load(googleMapsUrl);
    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        ChangeScene change = new ChangeScene();
        change.changeScene(stage, "ItineraryResult.fxml");
    }

    public void BackToHomeAction(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) HomeButton.getScene().getWindow();
            ChangeScene change = new ChangeScene();
            change.changeScene(stage, "Home.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}