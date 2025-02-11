package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    ChangeScene change=new ChangeScene();
    @FXML
    private Button HomeBack;

    @FXML
    void HomeBAckAction(MouseEvent event) throws IOException {
        Stage stage= (Stage) HomeBack.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");

    }

}
