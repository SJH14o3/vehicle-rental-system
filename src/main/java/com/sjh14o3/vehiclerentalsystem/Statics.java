package com.sjh14o3.vehiclerentalsystem;


import com.sjh14o3.vehiclerentalsystem.data.User;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Statics {
    // static class used to retrieve global variables
    private static Stage mainStage;
    private static User user;
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
}
