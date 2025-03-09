package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TravelMapController {

    @FXML
    private Canvas mapCanvas;

    @FXML
    private Button backButton;

    @FXML
    private TextField inputField;

    @FXML
    private Button visitedButton, plannedButton;

    private GraphicsContext gc;
    private Image mapImage;
    private String currentUsername;

    private static final Map<String, Point2D> divisionCoordinates = Map.of(
            "Khulna", new Point2D(500, 500),
            "Dhaka", new Point2D(600, 400),
            "Barisal", new Point2D(600, 550),
            "Chittagong", new Point2D(750, 500),
            "Rajshahi", new Point2D(500, 300),
            "Mymensingh", new Point2D(600, 300),
            "Sylhet", new Point2D(730, 300),
            "Rangpur", new Point2D(500, 200)
    );

    private final Set<String> visitedDivisions = new HashSet<>();
    private final Set<String> plannedDivisions = new HashSet<>();

    @FXML
    public void initialize() {
        gc = mapCanvas.getGraphicsContext2D();
        mapImage = new Image(getClass().getResource("/Bangladesh.png").toExternalForm());
        currentUsername = ProfileUserJDBC.getCurrentUsername();

        // Set Button Styles
        visitedButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 5px;");
        plannedButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 5px;");
        backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 5px;");


        visitedButton.setOnAction(e -> handleUserInput(inputField.getText().trim(), true));
        plannedButton.setOnAction(e -> handleUserInput(inputField.getText().trim(), false));

        loadBucketListFromDB();
        drawMap();
    }

    private void handleUserInput(String division, boolean isVisited) {
        if (divisionCoordinates.containsKey(division)) {
            if (isVisited) {
                visitedDivisions.add(division);
                plannedDivisions.remove(division);
            } else {
                plannedDivisions.add(division);
                visitedDivisions.remove(division);
            }
            updateDatabase(division, isVisited);
            drawMap();
        }
    }

    private void loadBucketListFromDB() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT place, visited FROM bucket_list WHERE username = ?")) {
            stmt.setString(1, currentUsername);
            ResultSet rs = stmt.executeQuery();
            visitedDivisions.clear();
            plannedDivisions.clear();
            while (rs.next()) {
                String place = rs.getString("place");
                boolean visited = rs.getBoolean("visited");
                if (visited) {
                    visitedDivisions.add(place);
                } else {
                    plannedDivisions.add(place);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        drawMap();
    }

    private void updateDatabase(String division, boolean isVisited) {
        String checkSql = "SELECT COUNT(*) FROM bucket_list WHERE username = ? AND place = ?";
        String insertSql = "INSERT INTO bucket_list (username, place, visited) VALUES (?, ?, ?)";
        String updateSql = "UPDATE bucket_list SET visited = ? WHERE username = ? AND place = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setString(1, currentUsername);
            checkStmt.setString(2, division);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                updateStmt.setBoolean(1, isVisited);
                updateStmt.setString(2, currentUsername);
                updateStmt.setString(3, division);
                updateStmt.executeUpdate();
            } else {
                insertStmt.setString(1, currentUsername);
                insertStmt.setString(2, division);
                insertStmt.setBoolean(3, isVisited);
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void drawMap() {
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());
        gc.drawImage(mapImage, 0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        for (String division : visitedDivisions) {
            drawMarker(divisionCoordinates.get(division), Color.RED);
        }
        for (String division : plannedDivisions) {
            drawMarker(divisionCoordinates.get(division), Color.BLUE);
        }
    }

    private void drawMarker(Point2D point, Color color) {
        gc.setFill(color);
        gc.fillOval(point.getX() - 5, point.getY() - 5, 10, 10);
    }

    @FXML
    private void handleBackToProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathtrekker/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
