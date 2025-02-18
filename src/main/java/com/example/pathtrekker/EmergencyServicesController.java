package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EmergencyServicesController {

    ChangeScene cs=new ChangeScene();

    @FXML
    private Button HomeBack;

    @FXML
    void HomeBackAction(MouseEvent event) throws IOException {
        Stage stage=(Stage) HomeBack.getScene().getWindow();
        cs.changeScene(stage,"Home.fxml");

    }

}
