package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class UploadController {

    @FXML
    private Button homeButton, selectImageButton, uploadButton;

    @FXML
    private ImageView imagePreview;

    @FXML
    private TextField captionField;

    private File selectedFile;
    ChangeScene cs = new ChangeScene();

    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            System.out.println("Selected File Path: " + selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            imagePreview.setImage(image);
        } else {
            System.out.println("No image selected.");
        }
    }

    @FXML
    private void uploadPhoto() {
        if (selectedFile == null) {
            System.out.println("No image selected.");
            return;
        }

        String caption = captionField.getText().trim();
        if (caption.isEmpty()) {
            System.out.println("Caption cannot be empty.");
            return;
        }

        String username = UploadProfileUserJDBC.getCurrentUsername();
        if (username == null || username.isEmpty()) {
            System.out.println("Error: No logged-in user. Cannot upload photo.");
            // Optionally redirect to login screen
            redirectToLogin();
            return;
        }

        try (InputStream inputStream = new FileInputStream(selectedFile)) {
            System.out.println("Uploading image for user: " + username);
            boolean success = UploadJDBC.uploadPhoto(username, inputStream, caption);
            if (success) {
                System.out.println("Photo uploaded successfully for user: " + username);
                // Optionally clear fields or navigate away
                clearFields();
            } else {
                System.out.println("Upload failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToMoments() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Moments.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void redirectToLogin() {
        Stage stage = (Stage) uploadButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Login.fxml"); // Assuming you have a Login.fxml
            System.out.println("Redirecting to login screen due to no logged-in user.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        selectedFile = null;
        imagePreview.setImage(null);
        captionField.clear();
    }
}