package com.sjh14o3.vehiclerentalsystem.data;

import org.bson.types.ObjectId;

public abstract class MotorizedVehicle extends Vehicle {
    protected short power; // in Horse Power
    protected short torque; // in N.M
    protected byte transmission;
    protected short maxSpeed; // KM/H
    protected double zeroToHundredTime; // seconds
    protected byte engineType;
    protected byte[] fuelType;
    protected short fuelCapacity; // NULL for electric vehicle
    protected short batteryCapacity; // not NULL for electric, hybrid and plug-in hybrid
    protected byte classification;

    public static final byte TRANSMISSION_MANUAL = 1;
    public static final byte TRANSMISSION_AT = 2;
    public static final byte TRANSMISSION_DCT = 3;
    public static final byte TRANSMISSION_CVT = 4;

    public static final byte FUEL_PETROL = 1;
    public static final byte FUEL_DIESEL = 2;
    public static final byte FUEL_ELECTRIC = 3;

    public static final byte ENGINE_PETROL = 1;
    public static final byte ENGINE_DIESEL = 2;
    public static final byte ENGINE_HYBRID = 3;
    public static final byte ENGINE_PLUGIN_HYBRID = 4;
    public static final byte ENGINE_ELECTRIC = 5;

    public static final byte CLASSIFICATION_CITY = 1;
    public static final byte CLASSIFICATION_SPORT = 2;
    public static final byte CLASSIFICATION_OFF_ROAD = 3;
    public static final byte CLASSIFICATION_LUXURY = 4;


    public short getPower() {
        return power;
    }

    public void setPower(short power) {
        this.power = power;
    }

    public short getTorque() {
        return torque;
    }

    public void setTorque(short torque) {
        this.torque = torque;
    }

    public byte getTransmission() {
        return transmission;
    }

    public void setTransmission(byte transmission) {
        this.transmission = transmission;
    }

    public short getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(short maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getZeroToHundredTime() {
        return zeroToHundredTime;
    }

    public void setZeroToHundredTime(double zeroToHundredTime) {
        this.zeroToHundredTime = zeroToHundredTime;
    }

    public byte getEngineType() {
        return engineType;
    }

    public void setEngineType(byte engineType) {
        this.engineType = engineType;
    }

    public byte[] getFuelType() {
        return fuelType;
    }

    public void setFuelType(byte[] fuelType) {
        this.fuelType = fuelType;
    }

    public short getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(short fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public short getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(short batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public byte getClassification() {
        return classification;
    }

    public void setClassification(byte classification) {
        this.classification = classification;
    }

    public String getTransmissionAsString() {
        return switch (transmission) {
            case TRANSMISSION_MANUAL -> "Manual";
            case TRANSMISSION_AT -> "AT";
            case TRANSMISSION_DCT -> "DCT";
            case TRANSMISSION_CVT -> "CVT";
            default -> "UNKNOWN";
        };
    }

    public static String getFuelTypeAsString(byte fuelType) {
        return switch (fuelType) {
            case FUEL_PETROL -> "Petrol";
            case FUEL_DIESEL -> "Diesel";
            case FUEL_ELECTRIC -> "Electric";
            default -> "Unknown";
        };
    }

    public String getEngineTypeAsString() {
        return switch (engineType) {
            case ENGINE_PETROL -> "Petrol";
            case ENGINE_DIESEL -> "Diesel";
            case ENGINE_ELECTRIC -> "Electric";
            case ENGINE_HYBRID -> "Plug-in Hybrid";
            case ENGINE_PLUGIN_HYBRID -> "Hybrid";
            default -> "UNKNOWN";
        };
    }

    public String getClassificationAsString() {
        return switch (classification) {
            case CLASSIFICATION_CITY -> "City";
            case CLASSIFICATION_SPORT -> "Sport";
            case CLASSIFICATION_OFF_ROAD -> "Off Road";
            case CLASSIFICATION_LUXURY -> "Luxury";
            default -> "UNKNOWN";
        };
    }

    public Integer[] getFuelsAsInt() {
        Integer[] out = new Integer[fuelType.length];
        for (int i = 0; i < fuelType.length; i++) {
            out[i] = (int) fuelType[i];
        }
        return out;
    }

    public String getFuelCapacityAsString() {
        if (engineType == ENGINE_ELECTRIC) return "None";
        return fuelCapacity + " L";
    }

    public String getBatteryCapacityAsString() {
        if (!(engineType == ENGINE_HYBRID || engineType == ENGINE_PLUGIN_HYBRID || engineType == ENGINE_ELECTRIC)) return "None";
        else return batteryCapacity + " KW/H";
    }

    @Override
    public String cardInformation() {
        return getImageFolderURI() + "~" + getMake() + "~" + getModel() + "~" + getYear() + "~" + getPower() + " HP~" +
                getTorque() + " N.M~" + getGears() + " Speed " + getTransmissionAsString() + "~" + getDailyRentalRate() +
                "$~" + getType() + "~" + getAvailabilityStatus() + "~" + getId();
    }

    @Override
    public String[] getAttributesAsStringArray() {
        String[] baseAttributes = super.getAttributesAsStringArray();
        String[] motorizedAttributes = new String[] {
                "power: " + power + " HP",
                "torque: " + torque + " N.M",
                "transmission: " + getTransmissionAsString(),
                "maxSpeed: " + maxSpeed + "KM/H",
                "zeroToHundredTime: " + zeroToHundredTime + " s",
                "engineType: " + getEngineTypeAsString(),
                "fuelType: " + (fuelType != null ? String.join(", ", byteArrayToStringArray(fuelType)) : ""),
                "fuelCapacity: " + getFuelCapacityAsString(),
                "batteryCapacity: " + getBatteryCapacityAsString(),
                "classification: " + getClassificationAsString()
        };

        return mergeArrays(baseAttributes, motorizedAttributes);
    }

    // Helper method to convert byte array to a string array
    private static String[] byteArrayToStringArray(byte[] byteArray) {
        if (byteArray == null) {
            return new String[0];
        }
        String[] stringArray = new String[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            stringArray[i] = getFuelTypeAsString(byteArray[i]);
        }
        return stringArray;
    }

    public MotorizedVehicle(String make, String model, short year, String imageFolderURI, String color, int dailyRentalRate,
                            short weight, int distanceTravelled, byte condition, byte size, byte type, byte gears, short power,
                            short torque, byte transmission, short maxSpeed, double zeroToHundredTime, byte engineType,
                            byte[] fuelType, short fuelCapacity, short batteryCapacity, byte classification) {
        super(make, model, year, imageFolderURI, color, dailyRentalRate, weight, distanceTravelled, condition, size, type, gears);
        this.power = power;
        this.torque = torque;
        this.transmission = transmission;
        this.maxSpeed = maxSpeed;
        this.zeroToHundredTime = zeroToHundredTime;
        this.engineType = engineType;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.batteryCapacity = batteryCapacity;
        this.classification = classification;
    }

    public MotorizedVehicle(ObjectId id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears, short power, short torque, byte transmission) {
        super(id, make, model, year, imageFolderURI, dailyRentalRate, availabilityStatus, weight, type, gears);
        this.power = power;
        this.torque = torque;
        this.transmission = transmission;
    }

    public MotorizedVehicle() {
        super();
    }
}
