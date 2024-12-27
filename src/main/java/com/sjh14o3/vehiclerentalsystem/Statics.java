package com.sjh14o3.vehiclerentalsystem;


import com.sjh14o3.vehiclerentalsystem.data.Reservation;
import com.sjh14o3.vehiclerentalsystem.data.ReserveOrder;
import com.sjh14o3.vehiclerentalsystem.data.User;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Statics {
    // static class used to retrieve global variables
    private static Stage mainStage;
    private static User user;
    private static Vehicle vehicle;
    private static ReserveOrder reserveOrder;
    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Statics.user = user;
    }

    public static Vehicle getVehicle() {
        return vehicle;
    }
    public static void setVehicle(Vehicle vehicle) {
        Statics.vehicle = vehicle;
    }

    public static ReserveOrder getReserveOrder() {
        return reserveOrder;
    }

    public static void setReserveOrder(ReserveOrder reserveOrder) {
        Statics.reserveOrder = reserveOrder;
    }
}
