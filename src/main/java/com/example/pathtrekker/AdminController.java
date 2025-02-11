package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    ChangeScene change=new ChangeScene();

    @FXML
    private Button AdminBack;

    @FXML
    void AdminBackAction(MouseEvent event) throws IOException {
        Stage stage=(Stage) AdminBack.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");

    }

}
