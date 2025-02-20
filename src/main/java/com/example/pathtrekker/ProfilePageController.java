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

    ChangeScene cs=new ChangeScene();

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

        // Add styles for the "Add to List" button
        addToListButton.setStyle(
                "-fx-background-color: #85aa9b; " + /* Green background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 14px; " + /* Font size */
                        "-fx-padding: 10px 20px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        );

        // Add hover effect for the "Add to List" button
        addToListButton.setOnMouseEntered(e -> addToListButton.setStyle(
                "-fx-background-color: #588b76; " + /* Darker green on hover */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 14px; " + /* Font size */
                        "-fx-padding: 10px 20px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        ));
        addToListButton.setOnMouseExited(e -> addToListButton.setStyle(
                "-fx-background-color: #85aa9b; " + /* Green background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 14px; " + /* Font size */
                        "-fx-padding: 10px 20px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        ));

        // Add styles for the "Home" button
        homeButton.setStyle(
                "-fx-background-color: #85aa9b; " + /* Green background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 12px; " + /* Font size */
                        "-fx-padding: 5px 10px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        );

        // Add hover effect for the "Home" button
        homeButton.setOnMouseEntered(e -> homeButton.setStyle(
                "-fx-background-color: #588b76; " + /* Darker green on hover */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 12px; " + /* Font size */
                        "-fx-padding: 5px 10px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        ));
        homeButton.setOnMouseExited(e -> homeButton.setStyle(
                "-fx-background-color: #85aa9b; " + /* Green background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 12px; " + /* Font size */
                        "-fx-padding: 5px 10px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        ));

        // Add styles for the "Log Out" button
        logOutButton.setStyle(
                "-fx-background-color: #85aa9b; " + /* Green background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 12px; " + /* Font size */
                        "-fx-padding: 5px 10px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        );

        // Add hover effect for the "Log Out" button
        logOutButton.setOnMouseEntered(e -> logOutButton.setStyle(
                "-fx-background-color: #588b76; " + /* Darker green on hover */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 12px; " + /* Font size */
                        "-fx-padding: 5px 10px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        ));
        logOutButton.setOnMouseExited(e -> logOutButton.setStyle(
                "-fx-background-color: #85aa9b; " + /* Green background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-size: 12px; " + /* Font size */
                        "-fx-padding: 5px 10px; " + /* Padding */
                        "-fx-border-radius: 5px; " + /* Rounded corners */
                        "-fx-background-radius: 5px; " + /* Rounded corners */
                        "-fx-cursor: hand;" /* Pointer cursor on hover */
        ));

        // Set custom cell factory for bucket list items
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
                    checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> item.setVisited(isSelected));
                    setGraphic(checkBox);
                }
            }
        });
        loadUserDetails();
        setLabelStyles();
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

    @FXML
    private void handleAddToList() {
        String place = bucketListInput.getText().trim();
        if (!place.isEmpty()) {
            bucketListView.getItems().add(new BucketListItem(place, false));
            bucketListInput.clear();
        }
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