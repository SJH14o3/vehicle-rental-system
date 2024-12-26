package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInController {
    @FXML
    private TextField textFirstName;
    @FXML
    private TextField textLastName;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textPasswordVisible;
    @FXML
    private TextArea textAddress;
    @FXML
    private TextArea textPhone;
    @FXML
    private PasswordField textPassword;
    @FXML
    private CheckBox checkBoxPassword;


    @FXML
    // when a user clicked cancel button, a confirmation message will show up
    private void cancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to cancel creating account?");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Statics.getMainStage());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                switchToLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void verifySubmit() {
        boolean success = true;
        StringBuilder sb = new StringBuilder();
        String firstName = textFirstName.getText();
        if (firstName.isBlank()) {
            success = false;
            sb.append("first name cannot be empty or blank\n");
        } else if (!User.isValidName(firstName)) {
            success = false;
            sb.append("wrong first name format\n");
        }
        String lastName = textLastName.getText();
        if (lastName.isBlank()) {
            success = false;
            sb.append("last name cannot be empty or blank\n");
        } else if (!User.isValidName(lastName)) {
            success = false;
            sb.append("wrong last name format\n");
        }
        String userName = textUsername.getText();
        if (userName.isBlank()) {
            success = false;
            sb.append("username cannot be empty or blank\n");
        } else if (!User.isValidUsername(userName)) {
            success = false;
            sb.append("username must only contain letters and digits with length of 3 to 15\n");
        }
        // checkbox is toggled twice to make text fields match content and bring it back to it first state.
        checkBoxPassword.setSelected(checkBoxPassword.isSelected());
        checkBoxPassword.setSelected(checkBoxPassword.isSelected());
        String password = textPassword.getText();
        if (password.isEmpty()) {
            success = false;
            sb.append("password cannot be empty\n");
        }
        String email = textEmail.getText();
        if (email.isBlank()) {
            success = false;
            sb.append("email cannot be blank\n");
        } else if (!User.isValidEmail(email)) {
            success = false;
            sb.append("wrong email format, accepted format: example@email.com\n");
        }
        String address = textAddress.getText();
        if (address.isBlank()) {
            success = false;
            sb.append("address cannot be blank\n");
        }
        String phoneWhole = textPhone.getText();
        String[] phones = phoneWhole.split("\n");
        if (phoneWhole.isBlank()) {
            success = false;
            sb.append("phone field cannot be empty\n");
        } else {
            for(String num : phones) {
                if (!User.isValidPhoneNumber(num)) {
                    success = false;
                    sb.append("wrong phone number format\n");
                    break;
                }
            }
        }
        // these were error checking with formatting to stop a not required database call
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

        // checking if username is unique
        if (Database.checkForDuplicateUserName(userName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("username is duplicated");
            alert.setContentText("try another username");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Statics.getMainStage());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        User user = new User(userName, firstName, lastName, email, phones, address, password);
        // if adding user was successful, this block will run
        if (Database.addUser(user)) {
            // Create an Alert of type INFORMATION
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Completed");
            alert.setContentText("You have been signed up!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Statics.getMainStage());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    switchToLogin();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("an error occurred with database");
            alert.setContentText("try again later");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Statics.getMainStage());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
    }

    @FXML
    private void togglePasswordVisibility() {
        String password;
        if (checkBoxPassword.isSelected()) {
            password = textPassword.getText();
            textPasswordVisible.setText(password);
            textPasswordVisible.setVisible(true);
        }
        else {
            password = textPasswordVisible.getText();
            textPassword.setText(password);
            textPasswordVisible.setVisible(false);
        }
    }

    private void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/login.fxml"));
        Scene scene = new Scene(loader.load());
        Statics.getMainStage().setScene(scene);
        Statics.getMainStage().show();
    }


}
