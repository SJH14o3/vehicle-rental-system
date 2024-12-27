package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LoginController {
    @FXML
    private TextField fieldUsername;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Label labelErrorUsername;
    @FXML
    private Label labelErrorPassword;

    @FXML
    // changing the scene to sign in page
    private void switchToSignIn() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/sign-in.fxml"));
        Scene scene = new Scene(loader.load());
        Statics.getMainStage().setScene(scene);
        Statics.getMainStage().show();
    }

    @FXML
    // authenticating user but checking if fields are not blank first
    private void verifyLogin() {
        boolean success = true;
        StringBuilder sb = new StringBuilder();
        String username = fieldUsername.getText();
        if (username.isBlank()) {
            success = false;
            sb.append("username field cannot be blank\n");
        } else if (!User.isValidUsername(username)) {
            success = false;
            sb.append("username field must only contain letters and digits with length of 3 to 15\n");
        }
        String password = fieldPassword.getText();
        if (password.isBlank()) {
            success = false;
            sb.append("password field cannot be blank\n");
        }

        if (!success) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("validation failed");
            alert.setContentText(sb.toString());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Statics.getMainStage());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        byte authenticationResult = Database.authenticateUser(username, password);
        if (authenticationResult != Database.MESSAGE_LOGIN_SUCCESS) {
            String context;
            // switch case was giving me a weird error so had to do it the ugly way
            if (authenticationResult == Database.ERROR_DATABASE) {
                context = "There was an error with database, try again later";
            } else if (authenticationResult == Database.ERROR_NO_USERNAME) {
                context = "username " + username + " is not signed up";
            } else if (authenticationResult == Database.ERROR_PASSWORD_MISMATCHED) {
                context = "mismatched password for " + username;
            } else {
                context = "unknown error";
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("validation failed");
            alert.setContentText(context);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Statics.getMainStage());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        Statics.setUser(Database.getUser(username));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/main.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Statics.getMainStage().setScene(scene);
        Statics.getMainStage().show();
    }
}
