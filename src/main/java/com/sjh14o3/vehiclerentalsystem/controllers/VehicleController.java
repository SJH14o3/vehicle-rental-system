package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.Date;
import com.sjh14o3.vehiclerentalsystem.data.ReserveOrder;
import com.sjh14o3.vehiclerentalsystem.data.User;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleController implements Initializable {
    private Vehicle vehicle;
    private String[] imagesName;
    private int counter;
    @FXML
    private ImageView image;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> daySpinner;
    @FXML
    private Label labelTotal;
    @FXML
    private ListView<String> informationBoard;
    @FXML
    private Button reserveVehicle;

    private void setImage() {
        image.setImage(new Image(getClass().getResource("/images/vehicles/" + vehicle.getImageFolderURI() + "/" + imagesName[counter]).toExternalForm()));
    }
    @FXML
    private void nextPicture() {
        counter = (counter + 1) % imagesName.length;
        setImage();
    }
    @FXML
    private void previousPicture() {
        counter = ((counter - 1) + imagesName.length) % imagesName.length;
        setImage();
    }
    @FXML
    private void goBackToMain() {
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
    private void reserveVehicle() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Statics.getMainStage());
        alert.setTitle("Reserve Vehicle");
        alert.setHeaderText("Complete reserve?");
        alert.setContentText("this will cost you " + (daySpinner.getValue() * vehicle.getDailyRentalRate()) + "$");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            LocalDate getSelectedDate = datePicker.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = getSelectedDate.format(formatter);
            Statics.setReserveOrder(new ReserveOrder(Date.convertStringToDate(formattedDate), daySpinner.getValue()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/payment.fxml"));
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


    // assuming every item of the path are images.
    private static String[] getAllImagesPath(String folderPath) {
        System.out.println("path is: " + folderPath);
        String[] paths = null;
        File folder = new File(folderPath);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get the list of files and directories in the folder
            File[] files = folder.listFiles();
            if (files != null && files.length > 0) {
                paths = new String[files.length];
                for (int i = 0; i < files.length; i++) {
                    paths[i] = files[i].getName();
                }
            } else {
                System.out.println("The folder is empty.");
            }
        } else {
            System.out.println("The specified path is not a valid folder.");
        }
        return paths;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        counter = 0;
        vehicle = Statics.getVehicle();
        imagesName = getAllImagesPath("src/main/resources/images/vehicles/" + vehicle.getImageFolderURI() + "/");
        setImage();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate end = LocalDate.now().plusDays(15);
        datePicker.setDayCellFactory(d -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                // Disable dates before tomorrow and after 15 days from now
                if (item.isBefore(tomorrow) || item.isAfter(end)) {
                    setDisable(true);
                }
            }
        });
        datePicker.setValue(tomorrow);

        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        factory.setValue(1);
        labelTotal.setText(vehicle.getDailyRentalRate() + "$");
        daySpinner.setValueFactory(factory);
        daySpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                labelTotal.setText((vehicle.getDailyRentalRate() * daySpinner.getValue()) + "$");
            }
        });
        Vehicle fullVehicle = Database.getFullVehicleDetails(vehicle.getId());
        assert fullVehicle != null;
        informationBoard.getItems().addAll(fullVehicle.getAttributesAsStringArray());

        if (vehicle.getAvailabilityStatus() != Vehicle.AVAILABILITY_AVAILABLE || Statics.getUser().getReservationStatus() != User.STATUS_FREE) {
            reserveVehicle.setDisable(true);
        }
    }
}
