module com.sjh14o3.vehiclerentalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.desktop;


    opens com.sjh14o3.vehiclerentalsystem to javafx.fxml;
    opens com.sjh14o3.vehiclerentalsystem.controllers to javafx.fxml;
    exports com.sjh14o3.vehiclerentalsystem;
}