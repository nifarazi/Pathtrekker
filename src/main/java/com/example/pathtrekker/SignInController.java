package com.example.pathtrekker;

import javafx.fxml.FXML;
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
        Stage stage= (Stage) SignIn.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");
    }

}
