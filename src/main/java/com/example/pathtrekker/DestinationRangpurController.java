package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DestinationRangpurController implements Initializable {

    @FXML
    private VBox destinationContainer;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button backButton;

    private final int perPage = 14;

    ChangeScene cs = new ChangeScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDestinations();

        if (scrollPane != null) {
            scrollPane.setContent(destinationContainer);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        } else {
            System.err.println("Error: scrollPane is null! Check fx:id in FXML.");
        }

        // Apply CSS for background and spacing
        destinationContainer.setStyle("-fx-padding: 15px; -fx-background-color: #f8f8f8;");
    }

    private void loadDestinations() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Fetch destinations from the database
            rs = DestinationRangpurJDBC.getDhakaDestinations(perPage, 0);
            boolean hasResults = false;

            while (rs.next()) {
                hasResults = true;
                String destinationName = rs.getString("name");

                // Load image from resources folder (fallback to default image)
                String imagePath = getClass().getResource("/images/" + destinationName.replace(" ", "_") + ".png") != null
                        ? getClass().getResource("/images/" + destinationName.replace(" ", "_") + ".png").toString()
                        : getClass().getResource("/images/home.png").toString();
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200); // Larger image
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);

                // Destination Info
                VBox infoBox = new VBox();
                infoBox.setSpacing(10);
                infoBox.setStyle("-fx-padding: 15px; -fx-background-color: white; "
                        + "-fx-border-color: #ccc; -fx-border-radius: 10px; "
                        + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 8, 0, 0, 3);");

                Label name = new Label(destinationName);
                name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

                // Create VBox to store each line separately
                VBox descriptionBox = new VBox();
                descriptionBox.setSpacing(5);
                descriptionBox.setStyle("-fx-padding: 10px; -fx-background-color: #f9f9f9; -fx-border-radius: 5px;");

                Label descTitle = new Label("Description:");
                descTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                // Split description into multiple lines
                String[] descriptionLines = rs.getString("description").split("\\. "); // Split by sentence
                for (String line : descriptionLines) {
                    Label descLine = new Label("• " + line.trim());
                    descLine.setStyle("-fx-font-size: 16px; -fx-text-fill: #444;");
                    descLine.setWrapText(true); // Enable text wrapping
                    descLine.setMaxWidth(400); // Set max width to fit within the container
                    descriptionBox.getChildren().add(descLine);
                }

                Label weather = new Label("Weather: " + rs.getString("weather_info"));
                Label cuisine = new Label("Cuisine: " + rs.getString("local_cuisine"));
                Label transport = new Label("Transport: " + rs.getString("transport_info"));
                Label openingTime = new Label("Opening Time: " + rs.getString("opening_time"));
                Label closingTime = new Label("Closing Time: " + rs.getString("closing_time"));

                // Add Wikipedia link
                String wikipediaLink = rs.getString("wikipedia_link");
                Hyperlink wikiLink = new Hyperlink("Wikipedia: " + wikipediaLink);
                wikiLink.setStyle("-fx-font-size: 16px; -fx-text-fill: #0077cc;");
                wikiLink.setOnAction(event -> {
                    try {
                        java.awt.Desktop.getDesktop().browse(new URI(wikipediaLink));
                    } catch (IOException | URISyntaxException e) {
                        System.err.println("Error opening Wikipedia link: " + e.getMessage());
                    }
                });

                for (Label label : new Label[]{weather, cuisine, transport, openingTime, closingTime}) {
                    label.setStyle("-fx-font-size: 16px; -fx-text-fill: #444;");
                }

                infoBox.getChildren().addAll(name, descTitle, descriptionBox, weather, cuisine, transport, openingTime, closingTime, wikiLink);

                // Horizontal Box (Image + Info)
                HBox hbox = new HBox(15, imageView, infoBox);
                hbox.setStyle("-fx-padding: 15px; -fx-background-color: #fff; "
                        + "-fx-border-radius: 10px; -fx-border-color: #ddd; "
                        + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 8, 0, 0, 3);");

                destinationContainer.getChildren().add(hbox);
            }

            if (!hasResults) {
                System.out.println("No destinations found.");
            } else {
                System.out.println("Loaded destinations.");
            }

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing Connection: " + e.getMessage());
                }
            }
        }
    }

    @FXML
    private void handlebackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        cs.changeScene(stage, "DestinationSearch.fxml");
    }
}