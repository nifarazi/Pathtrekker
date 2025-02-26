package com.example.pathtrekker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProfilePageController {

    @FXML
    private Label dateTimeLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameKeyLabel;

    @FXML
    private Label usernameKeyLabel;

    @FXML
    private Label emailKeyLabel;

    @FXML
    private ListView<BucketListItem> bucketListView;

    @FXML
    private TextField bucketListInput;

    @FXML
    private Button addToListButton;

    // New Trip Marker button
    @FXML
    private Button tripMarkerButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox profileSection;

    @FXML
    private VBox bucketListSection;

    @FXML
    private VBox viewItinerariesSection;

    @FXML
    private HBox mainContainer;

    @FXML
    private Button homeButton;

    @FXML
    private Button logOutButton;

    ChangeScene cs = new ChangeScene();

    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            dateTimeLabel.setText(LocalDateTime.now().format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Apply CSS styling to match the provided image
        rootPane.setStyle("-fx-background-color: transparent;");

        profileSection.setStyle("-fx-background-color: rgba(100,150,130,0.5); -fx-padding: 20px; -fx-border-radius: 10px;");
        fullNameLabel.setFont(new Font("Arial", 16));
        usernameLabel.setFont(new Font("Arial", 14));
        emailLabel.setFont(new Font("Arial", 14));
        fullNameLabel.setTextFill(Color.DARKGREEN);
        usernameLabel.setTextFill(Color.DARKBLUE);
        emailLabel.setTextFill(Color.DARKRED);

        bucketListSection.setStyle("-fx-background-color: rgba(150,180,160,0.5); -fx-padding: 20px; -fx-border-radius: 10px;");
        bucketListInput.setPromptText("Enter place to visit");

        viewItinerariesSection.setStyle("-fx-background-color: rgba(120,160,140,0.5); -fx-padding: 20px; -fx-border-radius: 10px;");

        mainContainer.setStyle("-fx-spacing: 20px;");

        // Style for "Add to List" button
        addToListButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        );
        addToListButton.setOnMouseEntered(e -> addToListButton.setStyle(
                "-fx-background-color: #588b76; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));
        addToListButton.setOnMouseExited(e -> addToListButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));

        // Style for "Trip Marker" button
        tripMarkerButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        );
        tripMarkerButton.setOnMouseEntered(e -> tripMarkerButton.setStyle(
                "-fx-background-color: #588b76; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));
        tripMarkerButton.setOnMouseExited(e -> tripMarkerButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));

        // Style for "Home" button
        homeButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-padding: 5px 10px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        );
        homeButton.setOnMouseEntered(e -> homeButton.setStyle(
                "-fx-background-color: #588b76; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-padding: 5px 10px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));
        homeButton.setOnMouseExited(e -> homeButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-padding: 5px 10px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));

        // Style for "Log Out" button
        logOutButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-padding: 5px 10px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        );
        logOutButton.setOnMouseEntered(e -> logOutButton.setStyle(
                "-fx-background-color: #588b76; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-padding: 5px 10px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));
        logOutButton.setOnMouseExited(e -> logOutButton.setStyle(
                "-fx-background-color: #85aa9b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-padding: 5px 10px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-cursor: hand;"
        ));

        bucketListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(BucketListItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    CheckBox checkBox = new CheckBox(item.getPlace());
                    checkBox.setSelected(item.isVisited());
                    checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                        item.setVisited(isSelected);
                        String username = ProfileUserJDBC.getCurrentUsername();
                        MyProfileJDBC.updateBucketListItemVisited(username, item.getPlace(), isSelected);
                    });
                    updateVisitedStyle(checkBox, item.isVisited());
                    setGraphic(checkBox);
                }
            }
            private void updateVisitedStyle(CheckBox checkBox, boolean isVisited) {
                if (isVisited) {
                    checkBox.setStyle("-fx-text-fill: gray; -fx-strikethrough: true;");
                } else {
                    checkBox.setStyle("-fx-text-fill: black; -fx-strikethrough: false;");
                }
            }
        });
        loadUserDetails();
        setLabelStyles();
        loadBucketList();
    }

    private void setLabelStyles() {
        Font robotoBold = Font.font("Roboto Bold", 14);

        fullNameLabel.setFont(robotoBold);
        fullNameLabel.setTextFill(Color.WHITE);

        usernameLabel.setFont(robotoBold);
        usernameLabel.setTextFill(Color.WHITE);

        emailLabel.setFont(robotoBold);
        emailLabel.setTextFill(Color.WHITE);

        nameKeyLabel.setFont(robotoBold);
        nameKeyLabel.setTextFill(Color.WHITE);

        usernameKeyLabel.setFont(robotoBold);
        usernameKeyLabel.setTextFill(Color.WHITE);

        emailKeyLabel.setFont(robotoBold);
        emailKeyLabel.setTextFill(Color.WHITE);
    }

    private void loadUserDetails() {
        String username = ProfileUserJDBC.getCurrentUsername();
        MyProfileJDBC.User user = MyProfileJDBC.getUserDetails(username);
        if (user != null) {
            fullNameLabel.setText(user.getFirstName() + " " + user.getLastName());
            usernameLabel.setText(user.getUsername());
            emailLabel.setText(user.getEmail());
        }
    }

    private void loadBucketList() {
        String username = ProfileUserJDBC.getCurrentUsername();
        List<MyProfileJDBC.BucketListItem> bucketListItems = MyProfileJDBC.getBucketListItems(username);
        bucketListView.getItems().clear();
        for (MyProfileJDBC.BucketListItem item : bucketListItems) {
            bucketListView.getItems().add(new BucketListItem(item.getPlace(), item.isVisited()));
        }
    }

    @FXML
    private void handleAddToList() {
        String place = bucketListInput.getText().trim();
        if (!place.isEmpty()) {
            String username = ProfileUserJDBC.getCurrentUsername();
            MyProfileJDBC.addBucketListItem(username, place);
            bucketListView.getItems().add(new BucketListItem(place, false));
            bucketListInput.clear();
        }
    }

    @FXML
    private void handleTripMarker() {
        Stage stage=(Stage) tripMarkerButton.getScene().getWindow();
        TravelMapController mapController = new TravelMapController();
        Stage mapStage = new Stage();
        mapController.start(mapStage);
        mapStage.show();

    }

    @FXML
    private void handleHome() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        cs.changeScene(stage, "Home.fxml");
    }

    @FXML
    private void handleLogOut() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) logOutButton.getScene().getWindow();
                try {
                    cs.changeScene(stage, "OpeningPage.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static class BucketListItem {
        private final String place;
        private boolean visited;

        public BucketListItem(String place, boolean visited) {
            this.place = place;
            this.visited = visited;
        }

        public String getPlace() {
            return place;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }
}
