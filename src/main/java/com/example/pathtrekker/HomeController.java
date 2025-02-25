package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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

    @FXML
    private Button LocalAttraction;

    @FXML
    private Button Map;

    @FXML
    private Button TravelMoments;


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
        Stage stage = (Stage) UsernameSpace.getScene().getWindow();
        try {
            cs.changeScene(stage, "Profile.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void TravelMomentsAction(MouseEvent event) {
        Stage stage = (Stage) TravelMoments.getScene().getWindow();
        try {
            cs.changeScene(stage, "Moments.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void LocalAttractionAction(MouseEvent mouseEvent) {
    }

    @FXML
    void MapAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String username = ProfileUserJDBC.getCurrentUsername();
        UsernameSpace.setText(username);
    }
}