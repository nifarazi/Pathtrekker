package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Calendar;

public class HomeController {

    @FXML
    private AnchorPane An1;

    @FXML
    private AnchorPane An2;

    @FXML
    private AnchorPane An3;

    @FXML
    private AnchorPane An4;

    @FXML
    private AnchorPane An5;

    @FXML
    private Label Day;

    @FXML
    private Label Month;

    @FXML
    private Label Year;

    @FXML
    private Button Destination;

    @FXML
    private Button Hotel;

    @FXML
    private Button Itinerary;

    @FXML
    private Button Em;

    @FXML
    private Button Transport;

    @FXML
    private Label UsernameSpace;


    private void loadUsername() {
        String username = ProfileUserJDBC.getCurrentUsername();
        UsernameSpace.setText(username);
    }

    Calendar now = Calendar.getInstance();
    int y = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH) + 1;
    int d = now.get(Calendar.DATE);

    ChangeScene cs = new ChangeScene();

    @FXML
    void DestinationAction(MouseEvent event) {
        Stage stage = (Stage) Destination.getScene().getWindow();
        try {
            cs.changeScene(stage, "DestinationSearch.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Stage stage = (Stage) Itinerary.getScene().getWindow();
        try {
            cs.changeScene(stage, "itineraryUI.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Stage stage = (Stage) Em.getScene().getWindow();
        try {
            cs.changeScene(stage, "EmergencyServices.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void UsernameAction(MouseEvent event) {
        Stage staage = (Stage) UsernameSpace.getScene().getWindow();
        try {
            cs.changeScene(staage, "Profile.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

        An1.getStyleClass().add("anchor-pane-hover");
        An2.getStyleClass().add("anchor-pane-hover");
        An3.getStyleClass().add("anchor-pane-hover");
        An4.getStyleClass().add("anchor-pane-hover");
        An5.getStyleClass().add("anchor-pane-hover");
        loadUsername();
        Day.setText(String.valueOf(d));
        Month.setText(String.valueOf(month));
        Year.setText(String.valueOf(y));
    }

}