package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
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

    }
    @FXML
    private void toggleCar() {

    }
    @FXML
    private void toggleMotorbike() {

    }
    @FXML
    private void toggleBicycle() {

    }
    @FXML
    private void switchToAccount() {

    }
    @FXML
    private void switchToReservations() {
        System.out.println("reservations");
    }

    private String[] getInfoStreams() {
        String[] out = new String[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            out[i] = vehicles[i].cardInformation();
        }
        return out;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicles = Database.getAllVehicles();
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
