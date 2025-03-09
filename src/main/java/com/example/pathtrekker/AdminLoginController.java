package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class AdminLoginController {

    ChangeScene change = new ChangeScene();

    @FXML
    public ImageView CloseEye;
    @FXML
    public ImageView OpenEye;
    @FXML
    public TextField PassShowField;
    @FXML
    private Button AdminCancel;

    @FXML
    private Button AdminLogin;

    @FXML
    private PasswordField AdminPassword;

    @FXML
    private TextField AdminUsername;

    @FXML
    void AdminCancelAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) AdminCancel.getScene().getWindow();
        change.changeScene(stage, "OpeningPage.fxml");
    }

    @FXML
    void AdminLoginAction(MouseEvent event) throws IOException {
        String username = AdminUsername.getText();
        String password;

        if (PassShowField.isVisible()) {
            password = PassShowField.getText();
        } else {
            password = AdminPassword.getText();
        }

        String adminId = AdminLoginJDBC.validateAdminLogin(username, password);
        boolean isValid = adminId != null;

        Alert alert = new Alert(isValid ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isValid ? "Login Successful" : "Login Failed");
        alert.setHeaderText(null);
        alert.setContentText(isValid ? "Welcome, " + username + "!" : "Invalid username or password.");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButtonType);
        Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
        okButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");

        alert.setGraphic(new javafx.scene.shape.Circle(15, isValid ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED));
        alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-font-family: Arial; -fx-background-color: #f0f8ff;");
        alert.showAndWait();

        if (isValid) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent root = loader.load();

            AdminDashboardController controller = loader.getController();
            controller.setUsername(adminId); // Set the admin ID here

            Stage stage = (Stage) AdminLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    @FXML
    public void HideAdminPassAction(MouseEvent mouseEvent) {
        OpenEye.setVisible(false);
        CloseEye.setVisible(true);
        AdminPassword.setVisible(true);
        PassShowField.setVisible(false);
        AdminPassword.setText(PassShowField.getText());
    }
    @FXML
    public void ShowAdminPassAction(MouseEvent mouseEvent) {
        OpenEye.setVisible(true);
        CloseEye.setVisible(false);
        AdminPassword.setVisible(false);
        PassShowField.setVisible(true);
        PassShowField.setText(AdminPassword.getText());
    }
}