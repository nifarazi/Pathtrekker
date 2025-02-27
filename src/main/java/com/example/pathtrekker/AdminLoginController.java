package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {
    ChangeScene change=new ChangeScene();
    @FXML
    private Button AdminCancel;

    @FXML
    private Button AdminLogin;

    @FXML
    private PasswordField AdminPassword;

    @FXML
    private TextField AdminUsername;

    @FXML
    void AdminCancelAction(MouseEvent event) throws IOException {

        Stage stage=(Stage) AdminCancel.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");

    }

    @FXML
    void AdminLoginAction(MouseEvent event) throws IOException {
        String username = AdminUsername.getText();
        String password = AdminPassword.getText();

        boolean isValid = AdminLoginJDBC.validateAdminLogin(username, password);

        Alert alert = new Alert(isValid ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isValid ? "Login Successful" : "Login Failed");
        alert.setHeaderText(null);
        alert.setContentText(isValid ? "Welcome, " + username + "!" : "Invalid username or password.");


        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButtonType);
        Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
        okButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");


        alert.setGraphic(new javafx.scene.shape.Circle(15, isValid ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED));
        alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-font-family: Arial; -fx-background-color: #f0f8ff;");
        alert.showAndWait();

        if (isValid) {
            Stage stage = (Stage) AdminLogin.getScene().getWindow();
            change.changeScene(stage, "AdminDashboard.fxml");
        } else {
            Stage stage = (Stage) AdminLogin.getScene().getWindow();
            return;
        }

    }

}
