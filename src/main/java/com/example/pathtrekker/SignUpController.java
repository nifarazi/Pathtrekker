package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {


    ChangeScene change = new ChangeScene();
    @FXML
    public TextField PassTextField;
    @FXML
    public TextField ConPassTextField;
    @FXML
    public CheckBox ShowPassCheckBox;
    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private Button SignUpCancel;

    @FXML
    private PasswordField SignUpConfirmPassword;

    @FXML
    private TextField SignUpEmail;

    @FXML
    private Button SignUpJoinNow;

    @FXML
    private PasswordField SignUpPassword;

    @FXML
    private TextField SignUpUsername;

    /**
     * Handles the Cancel action. Returns to the opening page.
     */
    @FXML
    void CancelAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) SignUpCancel.getScene().getWindow();
        change.changeScene(stage, "OpeningPage.fxml");
    }

    /**
     * Handles the "Join Now" action. Validates input, checks username availability,
     * inserts user data into the database, sends a welcome email, and redirects to the home page.
     */
    @FXML
    void JoinNowAction(MouseEvent event) throws IOException {
        System.out.println("🔵 JoinNowAction triggered!");

        String firstName = FirstName.getText().trim();
        String lastName = LastName.getText().trim();
        String username = SignUpUsername.getText().trim();
        String email = SignUpEmail.getText().trim();
        String password;
        String confirmPassword;
        if (ShowPassCheckBox.isSelected()) {
            password = PassTextField.getText();
            confirmPassword = ConPassTextField.getText();
        } else {
            password = SignUpPassword.getText();
            confirmPassword = SignUpConfirmPassword.getText();
        }

        System.out.println("User Input -> First Name: " + firstName + ", Last Name: " + lastName +
                ", Username: " + username + ", Email: " + email);

        // 🚨 Check for empty fields
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
            System.err.println("❌ Error: One or more fields are empty!");
            showErrorAlert("Missing Information", "Please fill in all fields.");
            return;
        }

        // 🚨 Check if email already exists
        if (SignUpUserJDBC.emailExists(email)) {
            System.err.println("❌ Error: Email already in use.");
            showErrorAlert("Email Taken", "This email is already in use. Please use a different email.");
            return;
        }


        // 🚨 Validate password match
        if (!password.equals(confirmPassword)) {
            System.err.println("❌ Error: Passwords do not match.");
            showErrorAlert("Password Mismatch", "Password does not match. Try again.");
            return;
        }

        // 🚨 Check if username already exists
        if (SignUpUserJDBC.usernameExists(username)) {
            System.err.println("❌ Error: Username already taken.");
            showErrorAlert("Username Taken", "Username already taken. Try again.");
            return;
        }


        // ✅ Save user to database
        try {
            System.out.println("⚡ Calling signIn() for user: " + username);
            SignUpUserJDBC.signIn(firstName, lastName, username, email, password);
            System.out.println("✅ User successfully inserted into DB!");

            // ✅ Send welcome email (but catch errors to avoid blocking signup)
            try {
                System.out.println("📧 Sending welcome email to " + email);
                sendWelcomeEmail(firstName, lastName, email);
                System.out.println("✅ Email sent successfully!");
            } catch (Exception e) {
                System.err.println("⚠️ Email sending failed: " + e.getMessage());
            }

            // ✅ Show success message
            showSuccessAlert("Welcome", "Sign-up successful! Confirmation email has been sent.");

            // ✅ Navigate to home page
            Stage stage = (Stage) SignUpJoinNow.getScene().getWindow();
            change.changeScene(stage, "OpeningPage.fxml");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Error during sign-up: " + e.getMessage());
            showErrorAlert("Sign-up Failed", "An error occurred during sign-up. Please try again.");
        }
    }

    /**
     * Sends a welcome email to the user after successful sign-up.
     */
    private void sendWelcomeEmail(String firstName, String lastName, String email) throws Exception {
        String subject = "Welcome to Pathtrekker!";
        String body = "Hi " + firstName + " " + lastName + ",\n\n" +
                "Welcome to Pathtrekker! We're thrilled to have you with us.\n\n" +
                "If you have any questions or need assistance, feel free to reach out to us anytime.\n\n" +
                "Best regards,\nThe Pathtrekker Team";

        EmailService.sendEmail(email, subject, body);
    }

    /**
     * Shows an error alert with the given title and message.
     */
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setGraphic(new javafx.scene.shape.Circle(15, Color.RED));
        alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-font-family: Arial; -fx-background-color: #f0f8ff;");
        alert.showAndWait();
    }

    /**
     * Shows a success alert with the given title and message.
     */
    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButtonType);
        Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
        okButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");

        alert.setGraphic(new javafx.scene.shape.Circle(15, Color.GREEN));
        alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-font-family: Arial; -fx-background-color: #f0f8ff;");
        alert.showAndWait();
    }
    @FXML
    public void ShowPassCheckAction(MouseEvent mouseEvent) {
        if (ShowPassCheckBox.isSelected()) {
            PassTextField.setVisible(true);
            ConPassTextField.setVisible(true);
            SignUpPassword.setVisible(false);
            SignUpConfirmPassword.setVisible(false);
            PassTextField.setText(SignUpPassword.getText());
            ConPassTextField.setText(SignUpConfirmPassword.getText());
        } else {
            PassTextField.setVisible(false);
            ConPassTextField.setVisible(false);
            SignUpPassword.setVisible(true);
            SignUpConfirmPassword.setVisible(true);
            SignUpPassword.setText(PassTextField.getText());
            SignUpConfirmPassword.setText(ConPassTextField.getText());
        }

    }
}
