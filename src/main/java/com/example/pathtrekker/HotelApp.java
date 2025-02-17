package com.example.pathtrekker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HotelApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Find the Perfect Stay for Your Journey");

        // Create a GridPane for the form elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(15); // Increased vertical gap
        grid.setHgap(15); // Increased horizontal gap
        grid.setAlignment(Pos.CENTER); // Center the grid in the available space

        // Load the image from resources
        Image backgroundImage = new Image(getClass().getResourceAsStream("/HOTEL.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)); // Scale background to fit

        // Create a StackPane to layer the background image and the form
        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(background));

        // Create an HBox to add padding on the left side of the GridPane
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 0, 0, 500)); // Increase left padding to shift the form further to the right
        hbox.getChildren().add(grid);

        // Add the HBox (which contains the GridPane) to the StackPane
        stackPane.getChildren().add(hbox);

        // Set a larger and bold font for labels and buttons
        Font font = Font.font("Arial", FontWeight.BOLD, 16); // Bigger and bold font

        // Division
        Label divisionLabel = new Label("Division:");
        divisionLabel.setFont(font); // Apply the font
        GridPane.setConstraints(divisionLabel, 0, 0);
        TextField divisionInput = new TextField();
        divisionInput.setPrefSize(200, 30); // Bigger input box
        divisionInput.setFont(font); // Apply the font
        GridPane.setConstraints(divisionInput, 1, 0);

        // District
        Label districtLabel = new Label("District:");
        districtLabel.setFont(font);
        GridPane.setConstraints(districtLabel, 0, 1);
        TextField districtInput = new TextField();
        districtInput.setPrefSize(200, 30);
        districtInput.setFont(font);
        GridPane.setConstraints(districtInput, 1, 1);

        // Check-in Date
        Label checkInLabel = new Label("Check-in Date:");
        checkInLabel.setFont(font);
        GridPane.setConstraints(checkInLabel, 0, 2);
        DatePicker checkInDate = new DatePicker();
        checkInDate.setPrefSize(200, 30);
        checkInDate.setStyle("-fx-font-size: 14;"); // DatePicker font size
        GridPane.setConstraints(checkInDate, 1, 2);

        // Check-out Date
        Label checkOutLabel = new Label("Check-out Date:");
        checkOutLabel.setFont(font);
        GridPane.setConstraints(checkOutLabel, 0, 3);
        DatePicker checkOutDate = new DatePicker();
        checkOutDate.setPrefSize(200, 30);
        checkOutDate.setStyle("-fx-font-size: 14;"); // DatePicker font size
        GridPane.setConstraints(checkOutDate, 1, 3);

        // Number of Adults
        Label adultsLabel = new Label("Number of Adults:");
        adultsLabel.setFont(font);
        GridPane.setConstraints(adultsLabel, 0, 4);
        Spinner<Integer> adultsSpinner = new Spinner<>(1, 10, 1);
        adultsSpinner.setPrefSize(200, 30);
        adultsSpinner.setStyle("-fx-font-size: 14;"); // Spinner font size
        GridPane.setConstraints(adultsSpinner, 1, 4);

        // Number of Children
        Label childrenLabel = new Label("Number of Children:");
        childrenLabel.setFont(font);
        GridPane.setConstraints(childrenLabel, 0, 5);
        Spinner<Integer> childrenSpinner = new Spinner<>(0, 10, 0);
        childrenSpinner.setPrefSize(200, 30);
        childrenSpinner.setStyle("-fx-font-size: 14;"); // Spinner font size
        GridPane.setConstraints(childrenSpinner, 1, 5);

        // Number of Rooms
        Label roomsLabel = new Label("Number of Rooms:");
        roomsLabel.setFont(font);
        GridPane.setConstraints(roomsLabel, 0, 6);
        Spinner<Integer> roomsSpinner = new Spinner<>(1, 10, 1);
        roomsSpinner.setPrefSize(200, 30);
        roomsSpinner.setStyle("-fx-font-size: 14;"); // Spinner font size
        GridPane.setConstraints(roomsSpinner, 1, 6);

        // Budget
        Label budgetLabel = new Label("Budget:");
        budgetLabel.setFont(font);
        GridPane.setConstraints(budgetLabel, 0, 7);
        ComboBox<String> budgetComboBox = new ComboBox<>();
        budgetComboBox.getItems().addAll("Low", "Mid", "High");
        budgetComboBox.setValue("Mid");
        budgetComboBox.setPrefSize(200, 30);
        budgetComboBox.setStyle("-fx-font-size: 14;"); // ComboBox font size
        GridPane.setConstraints(budgetComboBox, 1, 7);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefSize(200, 40); // Bigger button
        submitButton.setFont(font); // Apply the font
        GridPane.setConstraints(submitButton, 1, 8);
        submitButton.setOnAction(e -> {
            // Handle form submission
            System.out.println("Division: " + divisionInput.getText());
            System.out.println("District: " + districtInput.getText());
            System.out.println("Check-in: " + checkInDate.getValue());
            System.out.println("Check-out: " + checkOutDate.getValue());
            System.out.println("Adults: " + adultsSpinner.getValue());
            System.out.println("Children: " + childrenSpinner.getValue());
            System.out.println("Rooms: " + roomsSpinner.getValue());
            System.out.println("Budget: " + budgetComboBox.getValue());
        });

        // Add all elements to the GridPane
        grid.getChildren().addAll(divisionLabel, divisionInput, districtLabel, districtInput, checkInLabel, checkInDate,
                checkOutLabel, checkOutDate, adultsLabel, adultsSpinner, childrenLabel, childrenSpinner,
                roomsLabel, roomsSpinner, budgetLabel, budgetComboBox, submitButton);

        // Create the scene with the StackPane as the root
        Scene scene = new Scene(stackPane, 1000, 700); // Larger initial window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
