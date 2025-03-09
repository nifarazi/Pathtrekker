package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

public class AdminReview {

    @FXML
    private VBox CommentDisplay;

    @FXML
    private Button DashboardButton;

    @FXML
    private ScrollPane ScrollBar;

    ChangeScene cs = new ChangeScene();

    ArrayList<ReviewComClass> commentData = new ArrayList<>();


    public void initialize() {
        loadComments();
    }


    public void loadComments() {
        CommentDisplay.getChildren().clear();
        commentData.clear();
        CommentToClass();
        ShowComment();
    }



    void CommentToClass(){
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



    ArrayList<ReviewRepClass> ReplyToClass(Connection connection,int id) throws SQLException {
        String query2 = "SELECT reply, username FROM ReviewReply WHERE comment_id = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setInt(1, id);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        ArrayList<ReviewRepClass> replies = new ArrayList<>(); // Create a new replies list for each comment
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


    void ShowComment(){
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

            Label CommentLabel = new Label(reviewCom.getCommentText());
            CommentLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #18392b;");

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

            display.getChildren().addAll(commentHbox, CommentLabel, replyBox, makeReplies);
            CommentDisplay.getChildren().add(display);
        }

    }

    private @NotNull Button getButton(ReviewComClass reviewCom, TextField replySpace) {
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
                preparedStatement3.setString(2, "Pathtrekker");
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void DashboardReturnAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) DashboardButton.getScene().getWindow();
        cs.changeScene(stage, "AdminDashboard.fxml");
    }

}
