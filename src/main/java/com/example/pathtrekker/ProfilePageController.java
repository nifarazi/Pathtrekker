package com.example.pathtrekker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @FXML
    private Button deleteFromListButton;

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

    @FXML
    private ListView<String> itinerariesListView;

    ChangeScene cs = new ChangeScene();

    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            dateTimeLabel.setText(LocalDateTime.now().format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Apply CSS styling
        rootPane.setStyle("-fx-background-color: transparent;");
        profileSection.setStyle("-fx-background-color: rgba(100,150,130,0.5); -fx-padding: 20px; -fx-border-radius: 10px;");
        bucketListSection.setStyle("-fx-background-color: rgba(150,180,160,0.5); -fx-padding: 20px; -fx-border-radius: 10px;");
        bucketListInput.setPromptText("Enter place to visit");
        viewItinerariesSection.setStyle("-fx-background-color: rgba(120,160,140,0.5); -fx-padding: 20px; -fx-border-radius: 10px;");
        mainContainer.setStyle("-fx-spacing: 20px;");

        // Style buttons
        styleButton(addToListButton, "#85aa9b", "#588b76", "14px", "10px 20px");
        styleButton(deleteFromListButton, "#85aa9b", "#588b76", "14px", "10px 15px");
        styleButton(tripMarkerButton, "#85aa9b", "#588b76", "14px", "10px 20px");
        styleButton(homeButton, "#85aa9b", "#588b76", "12px", "5px 10px");
        styleButton(logOutButton, "#85aa9b", "#588b76", "12px", "5px 10px");

        // Bucket List View cell factory
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

        // Itineraries ListView setup - Use black text
        itinerariesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label label = new Label(item);
                    label.setStyle("-fx-font-family: 'Roboto'; -fx-font-size: 14px; -fx-text-fill: black;");
                    setGraphic(label);
                }
            }
        });

        // Handle click on itinerary to open PDF
        itinerariesListView.setOnMouseClicked(event -> {
            String selectedItinerary = itinerariesListView.getSelectionModel().getSelectedItem();
            if (selectedItinerary != null) {
                openItineraryPdf(selectedItinerary);
            }
        });

        // Load initial data
        loadUserDetails();
        setLabelStyles();
        loadBucketList();
        loadItineraries();

        // Debug and force refresh
        System.out.println("Items in itinerariesListView after load: " + itinerariesListView.getItems());
        itinerariesListView.refresh();
    }

    private void styleButton(Button button, String defaultColor, String hoverColor, String fontSize, String padding) {
        String baseStyle = "-fx-background-color: " + defaultColor + "; -fx-text-fill: white; -fx-font-size: " + fontSize + "; " +
                "-fx-padding: " + padding + "; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-cursor: hand;";
        String hoverStyle = "-fx-background-color: " + hoverColor + "; -fx-text-fill: white; -fx-font-size: " + fontSize + "; " +
                "-fx-padding: " + padding + "; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-cursor: hand;";

        button.setStyle(baseStyle);
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(baseStyle));
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

    private void loadItineraries() {
        String username = ProfileUserJDBC.getCurrentUsername();
        if (username == null || username.isEmpty()) {
            System.err.println("Cannot load itineraries: Username is null or empty");
            return;
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT itinerary_name FROM saved_itineraries WHERE username = ?")) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            itinerariesListView.getItems().clear();
            while (rs.next()) {
                String itineraryName = rs.getString("itinerary_name");
                itinerariesListView.getItems().add(itineraryName);
            }
            if (itinerariesListView.getItems().isEmpty()) {
                System.out.println("No itineraries found for user: " + username);
            } else {
                System.out.println("Loaded " + itinerariesListView.getItems().size() + " itineraries for user: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Database Error", "Failed to load itineraries: " + e.getMessage());
        }
    }

    private void openItineraryPdf(String itineraryName) {
        String username = ProfileUserJDBC.getCurrentUsername();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT pdf_data FROM saved_itineraries WHERE username = ? AND itinerary_name = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, itineraryName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                byte[] pdfData = rs.getBytes("pdf_data");
                File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + itineraryName + ".pdf");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(pdfData);
                }
                java.awt.Desktop.getDesktop().open(tempFile);
            } else {
                showAlert("Error", "PDF Not Found", "No PDF found for itinerary: " + itineraryName);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to Open PDF", "An error occurred: " + e.getMessage());
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
    private void handleDeleteFromList() {
        BucketListItem selectedItem = bucketListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String username = ProfileUserJDBC.getCurrentUsername();
            MyProfileJDBC.deleteBucketListItem(username, selectedItem.getPlace());
            bucketListView.getItems().remove(selectedItem);
        } else {
            showAlert("No Selection", "No item selected", "Please select an item to delete.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleTripMarker() {
        if (tripMarkerButton != null) {
            Stage stage = (Stage) tripMarkerButton.getScene().getWindow();
            TravelMapController mapController = new TravelMapController();
            Stage mapStage = new Stage();
            mapController.start(mapStage);
            mapStage.show();
        } else {
            System.err.println("tripMarkerButton is not initialized!");
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