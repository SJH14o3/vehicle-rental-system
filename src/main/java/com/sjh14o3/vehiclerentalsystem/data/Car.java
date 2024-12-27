package com.sjh14o3.vehiclerentalsystem.data;

public final class  Car extends MotorizedVehicle {
    private byte seats;
    private short trunkCapacity;
    private byte segment;
    private byte doors;

    public static final byte SEGMENT_SUV = 1;
    public static final byte SEGMENT_CROSSOVER = 2;
    public static final byte SEGMENT_PICKUP_TRUCK = 3;
    public static final byte SEGMENT_SEDAN = 4;
    public static final byte SEGMENT_COUPE = 5;
    public static final byte SEGMENT_WAGON = 6;
    public static final byte SEGMENT_HATCH_BACK = 7;
    public static final byte SEGMENT_CONVERTIBLE = 8;
    public static final byte SEGMENT_MPV = 9;
    public static final byte SEGMENT_VAN = 10;

    public byte getSeats() {
        return seats;
    }

    public void setSeats(byte seats) {
        this.seats = seats;
    }

    public short getTrunkCapacity() {
        return trunkCapacity;
    }

    public void setTrunkCapacity(short trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
    }

    public byte getSegment() {
        return segment;
    }

    public void setSegment(byte segment) {
        this.segment = segment;
    }

    public byte getDoors() {
        return doors;
    }

    public void setDoors(byte doors) {
        this.doors = doors;
    }

    // used for adding to database
    public Car(String make, String model, short year, String imageFolderURI, String color, int dailyRentalRate,
               short weight, int distanceTravelled, byte condition, byte size, byte gears, short power,
               short torque, byte transmission, short maxSpeed, double zeroToHundredTime, byte engineType, byte[] fuelType,
               short fuelCapacity, short batteryCapacity, byte classification, byte seats, short trunkCapacity, byte segment, byte doors) {
        super(make, model, year, imageFolderURI, color, dailyRentalRate, weight, distanceTravelled, condition, size, Vehicle.TYPE_CAR, gears, power, torque, transmission, maxSpeed, zeroToHundredTime, engineType, fuelType, fuelCapacity, batteryCapacity, classification);
        this.seats = seats;
        this.trunkCapacity = trunkCapacity;
        this.segment = segment;
        this.doors = doors;
    }

    // used for main list view
    public Car(String id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears, short power, short torque, byte transmission) {
        super(id, make, model, year, imageFolderURI, dailyRentalRate, availabilityStatus, weight, type, gears, power, torque, transmission);
    }
}
