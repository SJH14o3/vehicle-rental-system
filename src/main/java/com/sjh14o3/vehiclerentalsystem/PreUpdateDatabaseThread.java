package com.sjh14o3.vehiclerentalsystem;

public class PreUpdateDatabaseThread implements Runnable {

    @Override
    public void run() {
        System.out.println("PreUpdateDatabaseThread started");
        Database.updateReservationStatuses();
        System.out.println("PreUpdateDatabaseThread finished");
    }
}
