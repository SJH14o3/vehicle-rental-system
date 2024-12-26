package com.sjh14o3.vehiclerentalsystem;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Statics {
    // static class used to retrieve global variables
    private static Stage mainStage;
    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }
}
