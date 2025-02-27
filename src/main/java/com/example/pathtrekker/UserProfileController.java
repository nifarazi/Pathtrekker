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

public class UserProfileController {

    @FXML
    private VBox userFeedContainer;

    @FXML
    private Button backToMomentsButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView profileImage;

    ChangeScene cs = new ChangeScene();

    @FXML
    public void initialize() {
        String username = UploadProfileUserJDBC.getCurrentUsername();
        usernameLabel.setText(username);
        loadUserPhotos(username);
    }

    private void loadUserPhotos(String username) {
        userFeedContainer.getChildren().clear();
        List<Photo> userPhotos = UserProfileJDBC.getUserPhotos(username);

        for (Photo photo : userPhotos) {
            VBox postBox = new VBox(10);
            postBox.setStyle("-fx-border-color: #588b76; -fx-padding: 15; -fx-background-color: #d0ded8; -fx-background-radius: 10;");
            postBox.setAlignment(Pos.CENTER);

            ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(photo.getImage())));
            imageView.setFitWidth(350);
            imageView.setPreserveRatio(true);

            Label captionLabel = new Label("Caption: " + photo.getCaption());
            captionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

            Label likesLabel = new Label(photo.getLikeCount() + " Likes");
            likesLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

            VBox commentsBox = new VBox();
            commentsBox.setSpacing(5);
            for (Comment c : photo.getComments()) {
                Label commentLabel = new Label(c.getUsername() + ": " + c.getCommentText());
                commentLabel.setStyle("-fx-font-size: 11px;");
                commentsBox.getChildren().add(commentLabel);
            }

            postBox.getChildren().addAll(imageView, captionLabel, likesLabel, commentsBox);
            userFeedContainer.getChildren().add(postBox);
        }
    }

    @FXML
    private void goToMoments() {
        Stage stage = (Stage) backToMomentsButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Moments.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
