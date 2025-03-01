package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.paint.Color;

public class MapController {

    @FXML
    private Button GenerateButton;

    @FXML
    private TextField Location1;

    @FXML
    private TextField Location2;

    @FXML
    private WebView webview;

    @FXML
    private Label Message;

    @FXML
    void GenerateButtonAction(MouseEvent event) {
        String loc1 = Location1.getText();
        String loc2 = Location2.getText();

        if (loc1.isEmpty() || loc2.isEmpty()) {
            Message.setTextFill(Color.RED);
            Message.setText("Please Enter Both Locations.");
            return;
        }

        double distance = DistanceCalculator.calculateDistance(loc1, loc2);

        if (distance == -1) {
            Message.setTextFill(Color.RED);
            Message.setText("Error fetching coordinates.");
            return;
        }
        else
        {
            Message.setText("Generated Successfully!");
        }

        showMap(loc1, loc2);

    }

    private void showMap(String location1, String location2) {
        WebEngine webEngine = webview.getEngine();
        String googleMapsUrl = "https://www.google.com/maps/dir/" +
                location1.replace(" ", "+") + "/" +
                location2.replace(" ", "+");
        webEngine.load(googleMapsUrl);
    }

}
