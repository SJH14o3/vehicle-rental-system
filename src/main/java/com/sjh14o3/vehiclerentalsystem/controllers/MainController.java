package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.User;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label labelStateBar;
    @FXML
    private CheckBox checkBoxAvailability;
    @FXML
    private CheckBox checkBoxCar;
    @FXML
    private CheckBox checkBoxMotorbike;
    @FXML
    private CheckBox checkBoxBicycle;
    @FXML
    private ListView<String> vehicleListView;

    private Vehicle[] vehicles;

    @FXML
    private void applyClicked() {
        vehicles = Database.getFilteredVehicles(checkBoxCar.isSelected(), checkBoxMotorbike.isSelected(),
                checkBoxBicycle.isSelected(), checkBoxAvailability.isSelected());
        vehicleListView.getItems().clear();
        vehicleListView.getItems().addAll(getInfoStreams());
    }

    private void resetCheckBoxes() {
        if ((!checkBoxCar.isSelected()) && (!checkBoxMotorbike.isSelected()) && (!checkBoxBicycle.isSelected())) {
            checkBoxCar.setSelected(true);
            checkBoxMotorbike.setSelected(true);
            checkBoxBicycle.setSelected(true);
        }
    }

    @FXML
    private void toggleCar() {
        if (!checkBoxCar.isSelected()) {
            resetCheckBoxes();
        }
    }
    @FXML
    private void toggleMotorbike() {
        if (!checkBoxMotorbike.isSelected()) {
            resetCheckBoxes();
        }
    }
    @FXML
    private void toggleBicycle() {
        if (!checkBoxBicycle.isSelected()) {
            resetCheckBoxes();
        }
    }
    @FXML
    private void switchToReservations() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/reservations.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Statics.getMainStage().setScene(scene);
        Statics.getMainStage().show();
    }

    private String[] getInfoStreams() {
        String[] out = new String[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            out[i] = vehicles[i].cardInformation();
        }
        return out;
    }

    private void setStateBarLabelData() {
        if (Statics.getUser().getReservationStatus() == User.STATUS_FREE) {
            labelStateBar.setText("  Choose a vehicle to reserve");
            labelStateBar.setStyle("-fx-background-color: #74FF00;");
        } else if (Statics.getUser().getReservationStatus() == User.STATUS_RESERVED) {
            labelStateBar.setText("  You have reserved a vehicle");
            labelStateBar.setStyle("-fx-background-color: #FCFF5E;");
        } else if ((Statics.getUser().getReservationStatus() == User.STATUS_INUSE)) {
            labelStateBar.setText("  You are using a reserved vehicle");
            labelStateBar.setStyle("-fx-background-color: #FF6249;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicles = Database.getAllVehicles();
        setStateBarLabelData();
        vehicleListView.getItems().addAll(getInfoStreams());
        vehicleListView.setCellFactory(new Callback<>() {

            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            try {
                                // Load the FXML for the custom item
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sjh14o3/vehiclerentalsystem/vehicle-card.fxml"));
                                AnchorPane customItem = loader.load();

                                // Get the controller and set the data
                                VehicleCardController controller = loader.getController();
                                controller.setData(item);

                                // Set the custom item as the graphic for this cell
                                setGraphic(customItem);
                            } catch (IOException e) {
                                e.printStackTrace();
                                setText("Error loading item");
                            }
                        }
                    }
                };
            }
        });
    }

}
