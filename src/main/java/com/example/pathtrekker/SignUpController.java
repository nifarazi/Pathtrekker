package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    ChangeScene change=new ChangeScene();

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

    @FXML
    void CancelAction(MouseEvent event) throws IOException {
        Stage stage=(Stage) SignUpCancel.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");
    }

    @FXML
    void JoinNowAction(MouseEvent event) throws IOException{
        Stage stage=(Stage) SignUpJoinNow.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");
    }

}
