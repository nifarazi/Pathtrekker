package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.util.List;

public class MomentsController {

    @FXML
    private VBox feedContainer;

    @FXML
    private Button homeButton, uploadButton, profileButton;

    ChangeScene cs = new ChangeScene();

    @FXML
    public void initialize() {
        loadPhotos();
    }

    private void loadPhotos() {
        feedContainer.getChildren().clear();
        List<Photo> photos = MomentsJDBC.getPhotos();

        for (Photo photo : photos) {
            VBox postBox = new VBox(10);
            postBox.setStyle("-fx-border-color: #588b76; -fx-padding: 15; -fx-background-color: #d0ded8; -fx-background-radius: 10;");
            postBox.setAlignment(Pos.CENTER);

            Label userLabel = new Label(photo.getUsername() + " uploaded:");
            userLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(photo.getImage())));
            imageView.setFitWidth(400);
            imageView.setPreserveRatio(true);

            Label captionLabel = new Label("Caption: " + photo.getCaption());
            captionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

            Label likesLabel = new Label(photo.getLikeCount() + " Likes");
            likesLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

            Button likeButton = new Button("Like");
            likeButton.setStyle("-fx-background-color: #85aa9b; -fx-text-fill: white;");
            likeButton.setOnAction(e -> {
                MomentsJDBC.likePhoto(UploadProfileUserJDBC.getCurrentUsername(), photo.getId());
                loadPhotos();
            });

            TextField commentField = new TextField();
            commentField.setPromptText("Add a comment...");
            commentField.setStyle("-fx-background-color: white;");

            Button commentButton = new Button("Comment");
            commentButton.setStyle("-fx-background-color: #85aa9b; -fx-text-fill: white;");
            commentButton.setOnAction(e -> {
                MomentsJDBC.addComment(UploadProfileUserJDBC.getCurrentUsername(), photo.getId(), commentField.getText());
                loadPhotos();
            });

            VBox commentsBox = new VBox();
            commentsBox.setSpacing(5);
            for (Comment c : photo.getComments()) {
                Label commentLabel = new Label(c.getUsername() + ": " + c.getCommentText());
                commentLabel.setStyle("-fx-font-size: 11px;");
                commentsBox.getChildren().add(commentLabel);
            }

            postBox.getChildren().addAll(userLabel, imageView, captionLabel, likesLabel, likeButton, commentField, commentButton, commentsBox);
            feedContainer.getChildren().add(postBox);
        }
    }

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
