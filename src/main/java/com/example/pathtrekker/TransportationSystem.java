package com.example.pathtrekker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TransportationSystem extends Application {
    private TextField fromField, toField;
    private DatePicker datePicker;
    private ComboBox<Integer> seatsComboBox;
    private ChangeScene cs = new ChangeScene();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Transportation System");
        primaryStage.setScene(createTransportScene(primaryStage));
        primaryStage.show();
    }

    public Scene createTransportScene(Stage stage) {
        BorderPane root = new BorderPane();
        try {
            Image bgImage = new Image(getClass().getResourceAsStream("/TRANSPORTATION.png"));
            BackgroundImage background = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, true)
            );
            root.setBackground(new Background(background));
        } catch (Exception e) {
            System.err.println("Error loading background: " + e.getMessage());
        }

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #2c3e50; -fx-text-fill: white;");
        backBtn.setOnAction(e -> handleBack(stage));
        root.setTop(backBtn);
        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);

        GridPane grid = createFormGrid();
        Button showTripsBtn = createShowTripsButton();
        Button backToHomeBtn = createBackButton(stage);

        VBox centerBox = new VBox(30, grid, showTripsBtn, backToHomeBtn);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(204, 0, 10, 0));
        root.setCenter(centerBox);

        return new Scene(root, 1024, 768);
    }

    private GridPane createFormGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(8);
        grid.setPadding(new Insets(35, 15, 15, 15));

        addCompactFormItem(grid, "FROM:", 0, 0, fromField = new TextField());
        addCompactFormItem(grid, "DEPARTURE DATE:", 0, 2, datePicker = new DatePicker());
        addCompactFormItem(grid, "TO:", 1, 0, toField = new TextField());
        addCompactFormItem(grid, "SEATS:", 1, 2, seatsComboBox = new ComboBox<>());

        seatsComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        return grid;
    }

    private void addCompactFormItem(GridPane grid, String labelText, int col, int row, Control input) {
        Label label = new Label(labelText);
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 0 0 2 0;");
        grid.add(label, col, row);
        grid.add(input, col, row + 1);
        input.setPrefWidth(180);
    }

    private Button createShowTripsButton() {
        Button btn = new Button("SHOW TRIPS");
        btn.setStyle("-fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 8 20;");
        btn.setOnAction(e -> handleShowTrips());
        return btn;
    }

    private Button createBackButton(Stage stage) {
        Button btn = new Button("Back to Home");
        btn.setStyle("-fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 8 20;");
        btn.setOnAction(e -> handleBack(stage));
        return btn;
    }

    private void handleShowTrips() {
        System.out.println("Searching trips...");
    }

    private void handleBack(Stage stage) {
        try {
            cs.changeScene(stage, "Home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}