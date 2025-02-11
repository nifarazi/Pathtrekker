package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    ChangeScene change=new ChangeScene();

    @FXML
    private Button SignIn;

    @FXML
    private Button SignInCancel;

    @FXML
    private PasswordField SignInPassword;

    @FXML
    private TextField SignInUsername;

    @FXML
    void BackAction(MouseEvent event) throws IOException {
        Stage stage= (Stage) SignInCancel.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");
    }

    @FXML
    void SignInAction(MouseEvent event) throws IOException{
        String username = SignInUsername.getText();
        String password = SignInPassword.getText();

        boolean isValid = SignInUserJDBC.validateLogin(username, password);

        Alert alert = new Alert(isValid ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isValid ? "Login Successful" : "Login Failed");
        alert.setHeaderText(null);
        alert.setContentText(isValid ? "Welcome, " + username + "!" : "Invalid username or password.");
        alert.setGraphic(new javafx.scene.shape.Circle(15, javafx.scene.paint.Color.GREEN));
        alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-font-family: Arial; -fx-background-color: #f0f8ff;");
        alert.showAndWait();

        if (isValid) {
            Stage stage = (Stage) SignIn.getScene().getWindow();
            change.changeScene(stage, "Home.fxml");
        }

    }

}
