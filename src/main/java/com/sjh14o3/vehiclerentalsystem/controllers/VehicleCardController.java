package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.data.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VehicleCardController {
    @FXML
    private ImageView imageCar;
    @FXML
    private ImageView imageMake;
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
    private void reserve() {
        System.out.println("reserve");
    }

    @FXML
    private void switchToFullDetail() {
        System.out.println(textMake.getText() + textModel.getText());
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


    }
}
