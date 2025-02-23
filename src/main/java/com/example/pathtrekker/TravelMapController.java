package com.example.pathtrekker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TravelMapController extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mirpurdohs832";

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
    private Canvas canvas;
    private GraphicsContext gc;
    private Image mapImage;
    private String currentUsername;

    @Override
    public void start(Stage primaryStage) {
        mapImage = new Image(getClass().getResource("/Bangladesh.png").toExternalForm());
        canvas = new Canvas(1200, 800);
        gc = canvas.getGraphicsContext2D();

        TextField inputField = new TextField();
        inputField.setPromptText("Enter division name (e.g., Dhaka, Chittagong)");

        Button visitedButton = new Button("Mark as Visited (Red)");
        Button plannedButton = new Button("Mark as Planned (Blue)");
        Button backButton = new Button("Back to Profile");
        Button loadDatabaseButton = new Button("Load from Database");

        visitedButton.setOnAction(e -> handleUserInput(inputField.getText().trim(), true));
        plannedButton.setOnAction(e -> handleUserInput(inputField.getText().trim(), false));
        backButton.setOnAction(e -> handleBackToProfile(primaryStage));
        loadDatabaseButton.setOnAction(e -> loadBucketListFromDB());

        VBox controls = new VBox(5, inputField, visitedButton, plannedButton, loadDatabaseButton, backButton);
        StackPane root = new StackPane(canvas, controls);

        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("Bangladesh Travel Map");
        primaryStage.setScene(scene);
        primaryStage.show();

        currentUsername = ProfileUserJDBC.getCurrentUsername(); // Fetch logged-in user
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
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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
        String sql = "INSERT INTO bucket_list (username, place, visited) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE visited = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, currentUsername);
            stmt.setString(2, division);
            stmt.setBoolean(3, isVisited);
            stmt.setBoolean(4, isVisited);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void drawMap() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(mapImage, 0, 0, canvas.getWidth(), canvas.getHeight());

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

    private void handleBackToProfile(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathtrekker/profile.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
