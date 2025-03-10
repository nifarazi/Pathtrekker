package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

public abstract class BaseReviewController {

    @FXML
    protected VBox CommentDisplay;

    protected ArrayList<ReviewComClass> commentData = new ArrayList<>();

    public void initialize() {
        loadComments();
    }

    public void loadComments() {
        CommentDisplay.getChildren().clear();
        commentData.clear();
        CommentToClass();
        ShowComment();
    }

    void CommentToClass() {
        String query = "SELECT * FROM ReviewComment";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return;
            }
            while (resultSet.next()) {
                String comment = resultSet.getString("comment");
                int rating = resultSet.getInt("rating");
                String username = resultSet.getString("username");
                int id = resultSet.getInt("id");

                ReviewComClass reviewComClass = new ReviewComClass(username, comment, id, rating);
                ArrayList<ReviewRepClass> replies = ReplyToClass(connection, id);

                reviewComClass.setReplies(replies);
                commentData.add(reviewComClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<ReviewRepClass> ReplyToClass(Connection connection, int id) throws SQLException {
        String query2 = "SELECT reply, username FROM ReviewReply WHERE comment_id = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setInt(1, id);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        ArrayList<ReviewRepClass> replies = new ArrayList<>();
        if (resultSet2 != null) {
            while (resultSet2.next()) {
                String reply = resultSet2.getString("reply");
                String replyUsername = resultSet2.getString("username");
                ReviewRepClass reviewRep = new ReviewRepClass(replyUsername, reply, id);
                replies.add(reviewRep);
            }
        }
        return replies;
    }

    void ShowComment() {
        for (ReviewComClass reviewCom : commentData) {
            VBox display = new VBox(5);
            display.setStyle("-fx-border-color: #588b76; -fx-padding: 15; -fx-background-color: #d0ded8; -fx-background-radius: 10;");

            HBox commentHbox = new HBox(10);
            Label usernameLabel = new Label(reviewCom.getUsername());
            usernameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #18392b");
            Label ratingLabel = new Label();
            ratingLabel.setStyle("-fx-font-size: 15px; -fx-font-style: italic; -fx-text-fill: #18392b");
            ratingLabel.setText(" (Rating: " + reviewCom.getRating() + " )");
            commentHbox.getChildren().addAll(usernameLabel, ratingLabel);

            VBox cd= new VBox();
            Text commentText = new Text();
            commentText.setText(reviewCom.getCommentText());
            commentText.setStyle("-fx-font-size: 15px; -fx-text-fill: #18392b;");
            commentText.setWrappingWidth(950);
            cd.getChildren().add(commentText);

            VBox replyBox = new VBox(5);
            replyBox.setSpacing(5);
            for (ReviewRepClass reviewReply : reviewCom.getReplies()) {
                TextFlow replyTextFlow = new TextFlow();

                Text replyUsername = new Text(reviewReply.getUsername() + ": ");
                replyUsername.setStyle("-fx-font-size: 12px; -fx-text-fill: #18392b; -fx-font-weight: bold;");

                Text replyText = new Text(reviewReply.getReply());
                replyText.setStyle("-fx-font-size: 12px; -fx-text-fill: #18392b;");

                replyTextFlow.getChildren().addAll(replyUsername, replyText);
                replyBox.getChildren().add(replyTextFlow);
            }

            HBox makeReplies = new HBox(5);
            TextField replySpace = new TextField();
            replySpace.setMinWidth(400);
            replySpace.setPromptText("Reply to this comment...");
            replySpace.setFocusTraversable(FALSE);
            replySpace.setStyle("-fx-background-color: white;");
            Button replyButton = getButton(reviewCom, replySpace);
            makeReplies.getChildren().addAll(replySpace, replyButton);

            display.getChildren().addAll(commentHbox, cd, replyBox, makeReplies);
            CommentDisplay.getChildren().add(display);
        }
    }

    protected abstract Button getButton(ReviewComClass reviewCom, TextField replySpace);

    protected void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}