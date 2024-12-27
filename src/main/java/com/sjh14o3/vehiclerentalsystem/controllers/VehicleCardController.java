package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.User;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class VehicleCardController {
    @FXML
    private ImageView imageCar;
    @FXML
    private ImageView imageMake;
    @FXML
    private ImageView torqueImage;
    @FXML
    private ImageView powerImage;
    @FXML
    private Button buttonReserve;
    @FXML
    private Label textRent;
    @FXML
    private Label textMake;
    @FXML
    private Label textModel;
    @FXML
    private Label textYear;
    @FXML
    private Label textPower; // weight for bicycle
    @FXML
    private Label textTorque; // material for bicycle
    @FXML
    private Label textGearbox;
    @FXML
    private ListView<String> informationBoard;

    private String id;

    @FXML
    private void switchToFullDetail() {
        Statics.setVehicle(Database.getVehicleUsingId(id));
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

    public void setData(String data) {
        String[] dataArray = data.split("~");
        // if image location is incorrect, a default image will be used instead
        try {
            imageCar.setImage(new Image(getClass().getResource("/images/vehicles/" + dataArray[0] + "/1.jpeg").toExternalForm()));
        } catch (IllegalArgumentException | NullPointerException e) {
            imageCar.setImage(new Image(getClass().getResource("/images/default.png").toExternalForm()));
        }
        try {
            imageMake.setImage(new Image(getClass().getResource("/images/logos/" + dataArray[1] + ".png").toExternalForm()));
        } catch (IllegalArgumentException | NullPointerException e) {
            imageMake.setImage(new Image(getClass().getResource("/images/default.png").toExternalForm()));
        }
        textMake.setText(dataArray[1]);
        textModel.setText(dataArray[2]);
        textYear.setText(dataArray[3]);
        textPower.setText(dataArray[4]);
        textTorque.setText(dataArray[5]);
        textGearbox.setText(dataArray[6]);
        textRent.setText(dataArray[7]);
        id = dataArray[10];
        if (Byte.parseByte(dataArray[8]) == Vehicle.TYPE_BICYCLE) {
            powerImage.setImage(new Image(getClass().getResource("/images/weight.png").toExternalForm()));
            torqueImage.setImage(new Image(getClass().getResource("/images/material.png").toExternalForm()));
        }
        byte status = Byte.parseByte(dataArray[9]);
        if (Statics.getUser().getReservationStatus() != User.STATUS_FREE) {
            buttonReserve.setDisable(true);
            buttonReserve.setText("can't reserve");
            return;
        }
        if (status == Vehicle.AVAILABILITY_RESERVED) {
            buttonReserve.setDisable(true);
            buttonReserve.setText("Reserved");
        } else if (status == Vehicle.AVAILABILITY_UNAVAILABLE) {
            buttonReserve.setDisable(true);
            buttonReserve.setText("Unavailable");
        }
    }
}
