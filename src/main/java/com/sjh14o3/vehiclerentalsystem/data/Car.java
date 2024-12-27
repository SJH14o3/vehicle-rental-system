package com.sjh14o3.vehiclerentalsystem.data;

import org.bson.types.ObjectId;

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

    public String getSegmentAsString() {
        String out;
        switch (segment) {
            case SEGMENT_SUV -> out = "SUV";
            case SEGMENT_CROSSOVER -> out = "Crossover";
            case SEGMENT_PICKUP_TRUCK -> out = "Pickup Truck";
            case SEGMENT_SEDAN -> out = "Sedan";
            case SEGMENT_COUPE -> out = "Coupe";
            case SEGMENT_WAGON -> out = "Wagon";
            case SEGMENT_HATCH_BACK -> out = "Hatch Back";
            case SEGMENT_CONVERTIBLE -> out = "Convertible";
            case SEGMENT_MPV -> out = "MPV";
            case SEGMENT_VAN -> out = "VAN";
            default -> out = "";
        }
        return out;
    }

    @Override
    public String[] getAttributesAsStringArray() {
        String[] baseAttributes = super.getAttributesAsStringArray();
        String[] carAttributes = new String[] {
                "seats: " + seats,
                "trunkCapacity: " + trunkCapacity + " L",
                "segment: " + getSegmentAsString(),
                "doors: " + doors
        };

        return mergeArrays(baseAttributes, carAttributes);
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
    public Car(ObjectId id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears, short power, short torque, byte transmission) {
        super(id, make, model, year, imageFolderURI, dailyRentalRate, availabilityStatus, weight, type, gears, power, torque, transmission);
    }

    // used to set full information
    public Car() {
        super();
    }
}
