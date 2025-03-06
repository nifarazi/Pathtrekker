package com.example.pathtrekker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelApp extends Application {
    private Stage primaryStage;
    private TextField divisionInput, districtInput;
    private DatePicker checkInDate, checkOutDate;
    private Spinner<Integer> adultsSpinner, childrenSpinner, roomsSpinner;
    private ComboBox<String> budgetComboBox;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showHotelInputPage(primaryStage);
    }

    public void showHotelInputPage(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image(getClass().getResourceAsStream("/hotelssuggestion.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1080, 768, true, true, false, true));

        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(background));

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 0, 0, 500));
        hbox.getChildren().add(grid);
        stackPane.getChildren().add(hbox);

        Font font = Font.font("Arial", FontWeight.BOLD, 16);

        divisionInput = new TextField();
        divisionInput.setPrefSize(200, 30);
        divisionInput.setFont(font);

        districtInput = new TextField();
        districtInput.setPrefSize(200, 30);
        districtInput.setFont(font);

        checkInDate = new DatePicker();
        checkOutDate = new DatePicker();

        adultsSpinner = new Spinner<>(1, 10, 1);
        childrenSpinner = new Spinner<>(0, 10, 0);
        roomsSpinner = new Spinner<>(1, 10, 1);

        budgetComboBox = new ComboBox<>();
        budgetComboBox.getItems().addAll("Low", "Mid", "High");
        budgetComboBox.setValue("Mid");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String division = divisionInput.getText().trim();
            int roomsRequired = roomsSpinner.getValue();
            String budget = budgetComboBox.getValue();

            new HotelResultsPage(primaryStage, this).showResults(division, budget, roomsRequired);
        });

        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> loadHomeScene());

        grid.add(new Label("Division:"), 0, 0);
        grid.add(divisionInput, 1, 0);
        grid.add(new Label("District:"), 0, 1);
        grid.add(districtInput, 1, 1);
        grid.add(new Label("Check-in Date:"), 0, 2);
        grid.add(checkInDate, 1, 2);
        grid.add(new Label("Check-out Date:"), 0, 3);
        grid.add(checkOutDate, 1, 3);
        grid.add(new Label("Number of Adults:"), 0, 4);
        grid.add(adultsSpinner, 1, 4);
        grid.add(new Label("Number of Children:"), 0, 5);
        grid.add(childrenSpinner, 1, 5);
        grid.add(new Label("Number of Rooms:"), 0, 6);
        grid.add(roomsSpinner, 1, 6);
        grid.add(new Label("Budget:"), 0, 7);
        grid.add(budgetComboBox, 1, 7);
        grid.add(submitButton, 1, 8);
        grid.add(homeButton, 1, 9);

        Scene scene = new Scene(stackPane, 1080, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loadHomeScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
