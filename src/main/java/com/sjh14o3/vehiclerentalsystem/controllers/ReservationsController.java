package com.sjh14o3.vehiclerentalsystem.controllers;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.Statics;
import com.sjh14o3.vehiclerentalsystem.data.Reservation;
import com.sjh14o3.vehiclerentalsystem.data.RowInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationsController implements Initializable {
    @FXML
    private Button buttonCancel;
    @FXML
    private TableView<RowInfo> tableView;
    @FXML
    private Label labelUsername;
    @FXML
    private void goBack() {
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
    private void cancelReserve() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Statics.getMainStage());
        alert.setTitle("cancel reservation?");
        alert.setHeaderText("Do you want to cancel your waiting reservation?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Database.updateLastReservationStatusToCancel(Statics.getUser().getId());
            tableView.getItems().clear();
            tableView.getItems().addAll(Database.getUserReservations(Statics.getUser().getId()));
            buttonCancel.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUsername.setText(Statics.getUser().getUserName() + "'s reservations");
        TableColumn<RowInfo, String> reservationDateCol = new TableColumn<RowInfo, String>("reservation Date");
        reservationDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        TableColumn<RowInfo, String> vehicleInfoCol = new TableColumn<RowInfo, String>("Vehicle's Information");
        vehicleInfoCol.setCellValueFactory(new PropertyValueFactory<>("vehicleInfo"));

        TableColumn<RowInfo, String> startDateCol = new TableColumn<RowInfo, String>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<RowInfo, String> endDateCol = new TableColumn<RowInfo, String>("End Date");
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<RowInfo, String> statusCol = new TableColumn<RowInfo, String>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<RowInfo, String> payedCol = new TableColumn<RowInfo, String>("Total Payed");
        payedCol.setCellValueFactory(new PropertyValueFactory<>("payed"));

        tableView.getColumns().addAll(reservationDateCol, vehicleInfoCol, startDateCol, endDateCol, statusCol, payedCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.getItems().addAll(Database.getUserReservations(Statics.getUser().getId()));

        boolean lastReservationIsWaiting = Database.isLastReservationInWaitingMode(Statics.getUser().getId());
        if (lastReservationIsWaiting) {
            buttonCancel.setDisable(false);
        }
    }
}
