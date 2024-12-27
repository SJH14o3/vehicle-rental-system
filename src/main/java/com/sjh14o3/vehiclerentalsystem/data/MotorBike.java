package com.sjh14o3.vehiclerentalsystem.data;

public final class MotorBike extends MotorizedVehicle {
    private String[] accessories;

    public String[] getAccessories() {
        return accessories;
    }

    public void setAccessories(String[] accessories) {
        this.accessories = accessories;
    }
    // used for adding full info to database
    public MotorBike(String make, String model, short year, String imageFolderURI, String color, int dailyRentalRate,
                     short weight, int distanceTravelled, byte condition, byte size, byte type, byte gears, short power,
                     short torque, byte transmission, short maxSpeed, double zeroToHundredTime, byte engineType, byte[] fuelType,
                     short fuelCapacity, short batteryCapacity, byte classification, String[] accessories) {
        super(make, model, year, imageFolderURI, color, dailyRentalRate, weight, distanceTravelled, condition, size, type, gears, power, torque, transmission, maxSpeed, zeroToHundredTime, engineType, fuelType, fuelCapacity, batteryCapacity, classification);
        this.accessories = accessories;
    }

    // used for main list view
    public MotorBike(String id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears, short power, short torque, byte transmission) {
        super(id, make, model, year, imageFolderURI, dailyRentalRate, availabilityStatus, weight, type, gears, power, torque, transmission);
    }
}
