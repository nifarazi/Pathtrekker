package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {

    ChangeScene change = new ChangeScene();

    @FXML
    public ImageView EyeClose;

    @FXML
    public ImageView EyeOpen;

    @FXML
    public TextField ShowPass;

    @FXML
    private Button SignIn;

    @FXML
    private Button SignInCancel;

    @FXML
    private PasswordField SignInPassword;

    @FXML
    private TextField SignInUsername;

    /**
     * Initializes the controller and applies dynamic styles to buttons.
     */
    @FXML
    public void initialize() {
        // Apply dynamic styles to the buttons
        setButtonStyle(SignIn);
        setButtonStyle(SignInCancel);
    }

    /**
     * Dynamically sets button styles for hover and click effects.
     */
    private void setButtonStyle(Button button) {
        // Default appearance
        button.setStyle("-fx-background-color: #0B5638; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");

        // On hover - slightly darker
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #083f29; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));

        // On hover exit - reset to default
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #0B5638; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));

        // On click - even darker
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #05291b; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;"));

        // On release - back to hover if still hovering, otherwise default
        button.setOnMouseReleased(event -> {
            if (button.isHover()) {
                button.setStyle("-fx-background-color: #083f29; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
            } else {
                button.setStyle("-fx-background-color: #0B5638; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
            }
        });
    }

    /**
     * Handles the cancel action to navigate back to the opening page.
     */
    @FXML
    void BackAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) SignInCancel.getScene().getWindow();
        change.changeScene(stage, "OpeningPage.fxml");
    }

    /**
     * Handles the sign-in functionality by validating the login credentials.
     */
    @FXML
    void SignInAction(MouseEvent event) throws IOException {
        String username = SignInUsername.getText().trim(); // Trim to remove leading/trailing spaces
        String password;
        if (ShowPass.isVisible()) {
            password = ShowPass.getText();
        } else {
            password = SignInPassword.getText();
        }

        // Validate login credentials
        boolean isValid = SignInUserJDBC.validateLogin(username, password);

        // Show alert based on login success or failure
        Alert alert = new Alert(isValid ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isValid ? "Login Successful" : "Login Failed");
        alert.setHeaderText(null);
        alert.setContentText(isValid ? "Welcome, " + username + "!" : "Invalid username or password.");
        alert.setGraphic(new javafx.scene.shape.Circle(15, javafx.scene.paint.Color.GREEN));
        alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-font-family: Arial; -fx-background-color: #f0f8ff;");
        alert.showAndWait();

        if (isValid) {
            // Set the current username in UploadProfileUserJDBC after successful login
            UploadProfileUserJDBC.setCurrentUsername(username);
            System.out.println("Username set in SignInController: " + username); // Debugging

            // Navigate to Home screen
            Stage stage = (Stage) SignIn.getScene().getWindow();
            change.changeScene(stage, "Home.fxml");
        }

    }

    /**
     * Toggles password hiding.
     */
    @FXML
    public void PassHideAction(MouseEvent mouseEvent) {
        EyeClose.setVisible(true);
        EyeOpen.setVisible(false);
        SignInPassword.setText(ShowPass.getText());
        ShowPass.setVisible(false);
        SignInPassword.setVisible(true);
    }

    /**
     * Toggles password visibility.
     */
    @FXML
    public void PassShowAction(MouseEvent mouseEvent) {
        EyeClose.setVisible(false);
        EyeOpen.setVisible(true);
        ShowPass.setText(SignInPassword.getText());
        ShowPass.setVisible(true);
        SignInPassword.setVisible(false);
    }
}