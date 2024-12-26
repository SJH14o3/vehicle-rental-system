package com.sjh14o3.vehiclerentalsystem;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
// program starts here
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // setting up application window
        //TODO: make a thread to update users status based on date
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Vehicle Rental System");
        stage.setScene(scene);
        stage.setX(320);
        stage.setY(180);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/images/icon.png")).toExternalForm()));
        stage.setResizable(false);
        // clicking on close will ask user to verify it.
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to exit?");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() != ButtonType.OK) {
                    event.consume();
                }
            }
        });
        Statics.setMainStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
