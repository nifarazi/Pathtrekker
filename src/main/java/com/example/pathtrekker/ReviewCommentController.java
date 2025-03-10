package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Boolean.FALSE;

public class ReviewCommentController extends BaseReviewController {

    @FXML
    private TextArea CommentArea;

    @FXML
    private ComboBox<Integer> ReviewBar;

    @FXML
    private Button HomeButton;

    @FXML
    private Button SubmitButton;

    ChangeScene cs = new ChangeScene();

    @Override
    public void initialize() {
        super.initialize();
        CommentArea.setFocusTraversable(FALSE);
        CommentArea.setWrapText(true);
        ReviewBar.getItems().clear();
        ReviewBar.getItems().addAll(1, 2, 3, 4, 5);
        ReviewBar.setStyle("-fx-prompt-text-fill: #D0DED8;");
    }

    @FXML
    void SubmitButtonAction(MouseEvent event) {
        String comment = CommentArea.getText();
        Integer rating = ReviewBar.getValue();

        if (rating == null) {
            showAlert("Rating Error", "Please select a rating before submitting your comment.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO ReviewComment (username, comment, rating) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, UploadProfileUserJDBC.getCurrentUsername());
            preparedStatement.setString(2, comment);
            preparedStatement.setInt(3, rating);
            preparedStatement.executeUpdate();
            loadComments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommentArea.clear();
        ReviewBar.setValue(null);
        ReviewBar.setPromptText("Review");
    }

    @Override
    protected Button getButton(ReviewComClass reviewCom, TextField replySpace) {
        Button replyButton = new Button("Reply");
        replyButton.setStyle("-fx-background-color: #18392b; -fx-text-fill: #D0DED8;");

        replyButton.setOnAction(e -> {
            String reply = replySpace.getText();
            if (reply == null || reply.trim().isEmpty()) {
                showAlert("Reply Error", "Please enter a reply before submitting.");
                return;
            }
            try {
                Connection connection = DatabaseConnection.getConnection();
                String query3 = "INSERT INTO ReviewReply (comment_id, username, reply) VALUES (?,?,?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setInt(1, reviewCom.getId());
                preparedStatement3.setString(2, UploadProfileUserJDBC.getCurrentUsername());
                preparedStatement3.setString(3, reply);
                preparedStatement3.executeUpdate();
                loadComments();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            replySpace.clear();
        });
        return replyButton;
    }

    @FXML
    void HomeButtonAction(MouseEvent event) {
        Stage stage = (Stage) HomeButton.getScene().getWindow();
        try {
            cs.changeScene(stage, "Home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}