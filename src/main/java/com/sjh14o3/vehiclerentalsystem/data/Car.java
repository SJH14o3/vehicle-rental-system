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
}
