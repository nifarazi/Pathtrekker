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
import java.util.HashMap;
import java.util.Map;

public class HotelApp extends Application {
    private Stage primaryStage;
    private ComboBox<String> divisionComboBox;
    private ComboBox<String> districtComboBox;
    private DatePicker checkInDate, checkOutDate;
    private Spinner<Integer> adultsSpinner, childrenSpinner, roomsSpinner;
    private ComboBox<String> budgetComboBox;
    private Map<String, String[]> divisionToDistricts;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showHotelInputPage(primaryStage);
    }

    public void showHotelInputPage(Stage primaryStage) {
        Image backgroundImage = new Image(getClass().getResourceAsStream("/HOTEL.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1080, 768, true, true, false, true));

        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(background));
        stackPane.setPadding(new Insets(40));

        Font labelFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 22);
        Font inputFont = Font.font("Arial", FontWeight.NORMAL, 16);
        Font buttonFont = Font.font("Arial", FontWeight.NORMAL, 16);

        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);

        initializeDivisionDistricts();

        divisionComboBox = new ComboBox<>();
        divisionComboBox.getItems().addAll("Dhaka", "Chattogram", "Rajshahi", "Khulna", "Barishal", "Sylhet", "Rangpur", "Mymensingh");
        divisionComboBox.setPromptText("Select Division");
        divisionComboBox.setPrefSize(200, 30);
        divisionComboBox.setStyle("-fx-font: 16px 'Arial';");

        districtComboBox = new ComboBox<>();
        districtComboBox.setPromptText("Select District");
        districtComboBox.setPrefSize(200, 30);
        districtComboBox.setStyle("-fx-font: 16px 'Arial';");

        divisionComboBox.setOnAction(e -> updateDistricts());

        checkInDate = new DatePicker();
        checkInDate.setPrefSize(200, 30);

        checkOutDate = new DatePicker();
        checkOutDate.setPrefSize(200, 30);

        adultsSpinner = new Spinner<>(1, 10, 1);
        adultsSpinner.setPrefSize(200, 30);

        childrenSpinner = new Spinner<>(0, 10, 0);
        childrenSpinner.setPrefSize(200, 30);

        roomsSpinner = new Spinner<>(1, 10, 1);
        roomsSpinner.setPrefSize(200, 30);

        budgetComboBox = new ComboBox<>();
        budgetComboBox.getItems().addAll("Low", "Mid", "High");
        budgetComboBox.setValue("Mid");
        budgetComboBox.setPrefSize(200, 30);

        // Search Button with interactive styles
        Button submitButton = new Button("Search");
        submitButton.setFont(buttonFont);
        submitButton.setPrefSize(100, 20);
        configureButtonStyles(submitButton); // Apply interactive styles
        submitButton.setOnAction(e -> handleSearch());

        // Home Button (optional: you can apply styles here too if desired)
        Button homeButton = new Button("Home");
        homeButton.setFont(buttonFont);
        homeButton.setPrefSize(100, 20);
        configureButtonStyles(homeButton); // Optionally apply to Home button as well
        homeButton.setOnAction(e -> loadHomeScene());

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(submitButton, homeButton);

        // GridPane setup remains the same...
        Label divisionLabel = new Label("Division:");
        divisionLabel.setFont(labelFont);
        grid.add(divisionLabel, 0, 0);
        grid.add(divisionComboBox, 1, 0);

        Label districtLabel = new Label("District:");
        districtLabel.setFont(labelFont);
        grid.add(districtLabel, 2, 0);
        grid.add(districtComboBox, 3, 0);

        Label checkInLabel = new Label("Check-in Date:");
        checkInLabel.setFont(labelFont);
        grid.add(checkInLabel, 0, 1);
        grid.add(checkInDate, 1, 1);

        Label checkOutLabel = new Label("Check-out Date:");
        checkOutLabel.setFont(labelFont);
        grid.add(checkOutLabel, 2, 1);
        grid.add(checkOutDate, 3, 1);

        Label adultsLabel = new Label("Number of Adults:");
        adultsLabel.setFont(labelFont);
        grid.add(adultsLabel, 0, 2);
        grid.add(adultsSpinner, 1, 2);

        Label childrenLabel = new Label("Number of Children:");
        childrenLabel.setFont(labelFont);
        grid.add(childrenLabel, 2, 2);
        grid.add(childrenSpinner, 3, 2);

        Label roomsLabel = new Label("Number of Rooms:");
        roomsLabel.setFont(labelFont);
        grid.add(roomsLabel, 0, 3);
        grid.add(roomsSpinner, 1, 3);

        Label budgetLabel = new Label("Budget:");
        budgetLabel.setFont(labelFont);
        grid.add(budgetLabel, 2, 3);
        grid.add(budgetComboBox, 3, 3);

        StackPane.setAlignment(grid, Pos.CENTER);
        VBox buttonContainer = new VBox(buttonBox);
        StackPane.setAlignment(buttonContainer, Pos.CENTER);
        StackPane.setMargin(buttonContainer, new Insets(576, 0, 0, 0));

        stackPane.getChildren().addAll(grid, buttonContainer);

        Scene scene = new Scene(stackPane, 1080, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to configure interactive button styles
    private void configureButtonStyles(Button button) {
        // Default style
        button.setStyle("-fx-background-color: #85aa9b; -fx-text-fill: white;");

        // Hover effect
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white;"));

        // Mouse exit effect
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #85aa9b; -fx-text-fill: white;"));

        // Button pressed effect
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #248977; -fx-text-fill: white;"));

        // Button released effect
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #469D89; -fx-text-fill: white;"));
    }

    // Rest of the methods remain unchanged...
    private void initializeDivisionDistricts() {
        divisionToDistricts = new HashMap<>();
        divisionToDistricts.put("Dhaka", new String[]{"Dhaka", "Faridpur", "Gazipur", "Gopalganj", "Kishoreganj", "Madaripur", "Manikganj", "Munshiganj", "Narayanganj", "Narsingdi", "Rajbari", "Shariatpur", "Tangail"});
        divisionToDistricts.put("Chattogram", new String[]{"Bandarban", "Brahmanbaria", "Chandpur", "Chattogram", "Cox's Bazar", "Cumilla", "Feni", "Khagrachhari", "Lakshmipur", "Noakhali", "Rangamati"});
        divisionToDistricts.put("Rajshahi", new String[]{"Bogura", "Chapainawabganj", "Joypurhat", "Naogaon", "Natore", "Pabna", "Rajshahi", "Sirajganj"});
        divisionToDistricts.put("Khulna", new String[]{"Bagerhat", "Chuadanga", "Jashore", "Jhenaidah", "Khulna", "Kushtia", "Magura", "Meherpur", "Narail", "Satkhira"});
        divisionToDistricts.put("Barishal", new String[]{"Barguna", "Barishal", "Bhola", "Jhalokati", "Patuakhali", "Pirojpur"});
        divisionToDistricts.put("Sylhet", new String[]{"Habiganj", "Maulvibazar", "Sunamganj", "Sylhet"});
        divisionToDistricts.put("Rangpur", new String[]{"Dinajpur", "Gaibandha", "Kurigram", "Lalmonirhat", "Nilphamari", "Panchagarh", "Rangpur", "Thakurgaon"});
        divisionToDistricts.put("Mymensingh", new String[]{"Jamalpur", "Mymensingh", "Netrokona", "Sherpur"});
    }

    private void updateDistricts() {
        districtComboBox.getItems().clear();
        String selectedDivision = divisionComboBox.getValue();
        if (selectedDivision != null && divisionToDistricts.containsKey(selectedDivision)) {
            districtComboBox.getItems().addAll(divisionToDistricts.get(selectedDivision));
            districtComboBox.setPromptText("Select District");
        } else {
            districtComboBox.setPromptText("Select a Division First");
        }
    }

    private void handleSearch() {
        StringBuilder errorMessage = new StringBuilder("Please fill in the following fields:\n");
        boolean hasError = false;

        if (divisionComboBox.getValue() == null) {
            errorMessage.append("- Division\n");
            hasError = true;
        }
        if (districtComboBox.getValue() == null) {
            errorMessage.append("- District\n");
            hasError = true;
        }
        if (checkInDate.getValue() == null) {
            errorMessage.append("- Check-in Date\n");
            hasError = true;
        }
        if (checkOutDate.getValue() == null) {
            errorMessage.append("- Check-out Date\n");
            hasError = true;
        }

        if (hasError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Missing Required Fields");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        } else {
            String division = divisionComboBox.getValue();
            int roomsRequired = roomsSpinner.getValue();
            String budget = budgetComboBox.getValue();
            new HotelResultsPage(primaryStage, this).showResults(division, budget, roomsRequired);
        }
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