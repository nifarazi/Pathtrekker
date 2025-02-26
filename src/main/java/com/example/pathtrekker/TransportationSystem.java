package com.example.pathtrekker;



import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.image.Image;

import javafx.scene.layout.*;

import javafx.stage.Stage;

import java.sql.*;



public class TransportationSystem {

    private TextField fromField, toField;

    private DatePicker datePicker;

    private ComboBox<Integer> seatsComboBox;

    private ChangeScene cs = new ChangeScene();



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



        GridPane grid = createFormGrid();

        Button showTripsBtn = createShowTripsButton(stage);

        Button homeBtn = createHomeButton(stage);



        VBox centerBox = new VBox(20, grid, showTripsBtn, homeBtn);

        centerBox.setAlignment(Pos.TOP_CENTER);

        centerBox.setPadding(new Insets(225, 0, 10, 0));

        root.setCenter(centerBox);



        return new Scene(root, 1200, 800);

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



    private Button createShowTripsButton(Stage stage) {

        Button btn = new Button("SHOW TRIPS");

        btn.setStyle("-fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 8 20;");

        btn.setOnAction(e -> showTransportResults(stage));

        return btn;

    }



    private Button createHomeButton(Stage stage) {

        Button homeBtn = new Button("Home");

        homeBtn.setStyle("-fx-font-weight: bold; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-padding: 8 20;");

        homeBtn.setOnAction(e -> handleBack(stage));

        return homeBtn;

    }



    private void showTransportResults(Stage stage) {

        BorderPane root = new BorderPane();

        try {

            Image bgImage = new Image(getClass().getResourceAsStream("/TRANSPORTATIONRESULT.png"));

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



        GridPane resultGrid = new GridPane();

        resultGrid.setAlignment(Pos.CENTER);

        resultGrid.setHgap(20);

        resultGrid.setVgap(15);

        resultGrid.setPadding(new Insets(50));



        String from = fromField.getText().trim();

        String to = toField.getText().trim();

        fetchTransportData(resultGrid, from, to);



        Button backBtn = new Button("Back");

        backBtn.setOnAction(e -> stage.setScene(createTransportScene(stage)));



        Button homeBtn = createHomeButton(stage);

        HBox buttons = new HBox(15, backBtn, homeBtn);

        buttons.setAlignment(Pos.CENTER);

        root.setBottom(buttons);



        root.setCenter(resultGrid);

        stage.setScene(new Scene(root, 1200, 800));

    }



    private void fetchTransportData(GridPane grid, String from, String to) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/register", "root", "nanjiba@282002");

             PreparedStatement stmt = conn.prepareStatement("SELECT transport_type, fare, service_name, email, phone_number FROM transport_system WHERE from_division = ? AND to_division = ?")) {

            stmt.setString(1, from);

            stmt.setString(2, to);

            ResultSet rs = stmt.executeQuery();



            int row = 0;

            while (rs.next()) {

                Label transportType = new Label("Type: " + rs.getString("transport_type"));

                Label fare = new Label("Fare: " + rs.getDouble("fare") + " BDT");

                Label serviceName = new Label("Service: " + rs.getString("service_name"));

                Label contact = new Label("Contact: " + rs.getString("email") + ", " + rs.getString("phone_number"));

                VBox box = new VBox(5, transportType, fare, serviceName, contact);

                box.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-color: black;");

                grid.add(box, row % 3, row / 3);

                row++;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private void handleBack(Stage stage) {

        try {

            cs.changeScene(stage, "Home.fxml");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}