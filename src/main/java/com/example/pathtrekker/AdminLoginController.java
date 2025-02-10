package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void AdminLoginAction(MouseEvent event) throws IOException{
        Stage stage=(Stage) AdminLogin.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");
    }

}
