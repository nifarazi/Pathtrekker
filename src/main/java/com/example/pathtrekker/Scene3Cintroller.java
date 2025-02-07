package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene3Cintroller {
    ChangeScene change=new ChangeScene();

    @FXML
    private Button Back;

    @FXML
    void BackAction(MouseEvent event) throws IOException {
        Stage stage=(Stage) Back.getScene().getWindow();
        change.changeScene(stage,"OpeningPage.fxml");
    }

}
