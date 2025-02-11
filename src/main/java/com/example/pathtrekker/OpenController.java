package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenController {

    ChangeScene change=new ChangeScene();

    @FXML
    private Button OpenSignInButton;

    @FXML
    private Button OpenSignUpButton;

    @FXML
    private Button OpenAdminButton;

    @FXML
    void Scene2Clicked(MouseEvent event) throws IOException {
        Stage stage=(Stage) OpenSignInButton.getScene().getWindow();
        change.changeScene(stage,"SignIn.fxml");

    }

    @FXML
    void Scene3Clicked(MouseEvent event) throws IOException {
        Stage stage=(Stage) OpenSignUpButton.getScene().getWindow();
        change.changeScene(stage,"SignUp.fxml");
    }

    @FXML
    void Scene4Clicked(MouseEvent event) throws IOException {
        Stage stage=(Stage) OpenAdminButton.getScene().getWindow();
        change.changeScene(stage,"AdminLogin.fxml");
    }

}
