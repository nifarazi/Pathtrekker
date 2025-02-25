package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MomentsController {

    @FXML
    private Button homeButton;
    @FXML
    private Button uploadButton;
    @FXML
    private Button profileButton;

    ChangeScene cs = new ChangeScene();

    @FXML
    private void goToHome() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToUpload() {
        Stage stage = (Stage) uploadButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Upload.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToProfile() {
        Stage stage = (Stage) profileButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "UserProfile.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
