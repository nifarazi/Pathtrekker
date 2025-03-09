package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminReview extends BaseReviewController {

    ChangeScene cs = new ChangeScene();

    @FXML
    private Button DashboardButton;

    @Override
    protected @NotNull Button getButton(ReviewComClass reviewCom, TextField replySpace) {
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

    @FXML
    void DashboardReturnAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) DashboardButton.getScene().getWindow();
        cs.changeScene(stage, "AdminDashboard.fxml");
    }
}