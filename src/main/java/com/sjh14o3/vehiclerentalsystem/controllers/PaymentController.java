package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.Date;
import com.sjh14o3.vehiclerentalsystem.data.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TextField fieldCard1;
    @FXML
    private TextField fieldCard2;
    @FXML
    private TextField fieldCard3;
    @FXML
    private TextField fieldCard4;
    @FXML
    private TextField fieldMonth;
    @FXML
    private TextField fieldYear;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private PasswordField fieldCVV2;

    @FXML
    private void validate() {
        boolean success = true;
        StringBuilder sb = new StringBuilder();
        if (fieldCard1.getText().isBlank() || fieldCard2.getText().isBlank() || fieldCard3.getText().isBlank() || fieldCard4.getText().isBlank()) {
            success = false;
            sb.append("Please enter card number\n");
        }
        else if (!(hasNDigits(fieldCard1.getText(), 4) && hasNDigits(fieldCard2.getText(), 4) && hasNDigits(fieldCard3.getText(), 4) && hasNDigits(fieldCard4.getText(), 4))) {
            success = false;
            sb.append("each card cell must have exactly 4 digits\n");
        }

        if (fieldCVV2.getText().isBlank() || (fieldCVV2.getText().length() < 3)) {
            success = false;
            sb.append("error with CVV2\n");
        }
        if (fieldYear.getText().isBlank() || !hasNDigits(fieldYear.getText(), 2)) {
            success = false;
            sb.append("error with year field\n");
        }
        if (fieldMonth.getText().isBlank() || !hasNDigits(fieldMonth.getText(), 2)) {
            success = false;
            sb.append("error with month field\n");
        } else {
            int month = Integer.parseInt(fieldMonth.getText());
            if (month < 1 || month > 12) {
                success = false;
                sb.append("invalid month\n");
            }
        }
        if (fieldPassword.getText().isBlank()) {
            success = false;
            sb.append("password fields cannot be empty\n");
        }
        if (!success) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Statics.getMainStage());
            alert.setContentText(sb.toString());
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Statics.getMainStage());
        alert.setTitle("Success");
        alert.setHeaderText("Operation was successful");
        alert.setContentText("Car has been rented now");
        alert.showAndWait();
        String start = Statics.getReserveOrder().getDate().DateToFormattedString();
        Reservation reservation = new Reservation(Statics.getUser().getId(),
                Statics.getVehicle().getId(),
                Date.getCurrentDateAsClass(),
                Statics.getReserveOrder().getDate() , Date.convertStringToDate(Date.createNDaysLaterFormat(start, Statics.getReserveOrder().getDays())),
                Statics.getReserveOrder().getDays() * Statics.getVehicle().getDailyRentalRate());

        Database.addReservation(reservation);
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

    @FXML
    private void cancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to cancel payment?");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Statics.getMainStage());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/vehicle.fxml"));
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

    // Function to check if the input string has exactly N digits
    public static boolean hasNDigits(String input, int N) {
        // Check if the string is not null, has exactly N characters, and only contains digits
        return input != null && input.length() == N && input.matches("\\d{" + N + "}");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldCard1.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text length has reached 5 characters
            if (newValue.length() == 4) {
                // Move focus to textField2
                fieldCard2.requestFocus();
            }
        });
        fieldCard2.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text length has reached 5 characters
            if (newValue.length() == 4) {
                // Move focus to textField2
                fieldCard3.requestFocus();
            }
        });
        fieldCard3.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text length has reached 5 characters
            if (newValue.length() == 4) {
                // Move focus to textField2
                fieldCard4.requestFocus();
            }
        });
        fieldCard4.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text length has reached 5 characters
            if (newValue.length() == 4) {
                // Move focus to textField2
                fieldCVV2.requestFocus();
            }
        });
        fieldMonth.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text length has reached 5 characters
            if (newValue.length() == 2) {
                // Move focus to textField2
                fieldYear.requestFocus();
            }
        });
        fieldYear.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the text length has reached 5 characters
            if (newValue.length() == 2) {
                // Move focus to textField2
                fieldPassword.requestFocus();
            }
        });
    }
}
