package com.sjh14o3.vehiclerentalsystem.data;

import com.sjh14o3.vehiclerentalsystem.Statics;

public abstract class Vehicle {
    protected String id;
    protected String Make;
    protected String Model;
    protected short year;
    protected Date dateAdded;
    protected String ImageFolderURI;
    protected String Color;
    protected int dailyRentalRate;
    protected byte availabilityStatus;
    protected short weight;
    protected int distanceTravelled;
    protected byte condition;
    protected byte size;
    protected byte type;
    protected byte gears;

    public static final byte AVAILABILITY_AVAILABLE = 1;
    public static final byte AVAILABILITY_RESERVED = 2;
    public static final byte AVAILABILITY_UNAVAILABLE = 3;

    public static final byte CONDITION_GOOD = 1;
    public static final byte CONDITION_NEW = 2;
    public static final byte CONDITION_NEEDS_MAINTENANCE = 3;

    public static final byte SIZE_SMALL = 1;
    public static final byte SIZE_MEDIUM = 2;
    public static final byte SIZE_LARGE = 3;

    public static final byte TYPE_CAR = 1;
    public static final byte TYPE_MOTORBIKE = 2;
    public static final byte TYPE_BICYCLE = 3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getImageFolderURI() {
        return ImageFolderURI;
    }

    public void setImageFolderURI(String imageFolderURI) {
        ImageFolderURI = imageFolderURI;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getDailyRentalRate() {
        return dailyRentalRate;
    }

    public void setDailyRentalRate(int dailyRentalRate) {
        this.dailyRentalRate = dailyRentalRate;
    }

    public byte getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(byte availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public short getWeight() {
        return weight;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public byte getCondition() {
        return condition;
    }

    public void setCondition(byte condition) {
        this.condition = condition;
    }

    public byte getSize() {
        return size;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public byte getGears() {
        return gears;
    }

    public void setGears(byte gears) {
        this.gears = gears;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
    // this function will return information needed for card as a string stream. ~ is used to split stream into needed parts
    public abstract String cardInformation();

    // used for storing in database
    public Vehicle(String make, String model, short year, String imageFolderURI, String color, int dailyRentalRate,
                   short weight, int distanceTravelled, byte condition, byte size, byte type, byte gears) {
        Make = make;
        Model = model;
        this.year = year;
        this.dateAdded = Date.getCurrentDateAsClass();
        ImageFolderURI = imageFolderURI;
        Color = color;
        this.dailyRentalRate = dailyRentalRate;
        this.availabilityStatus = AVAILABILITY_AVAILABLE;
        this.weight = weight;
        this.distanceTravelled = distanceTravelled;
        this.condition = condition;
        this.size = size;
        this.type = type;
        this.gears = gears;
    }

    // used for main list view
    public Vehicle(String id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears) {
        this.id = id;
        Make = make;
        Model = model;
        this.year = year;
        ImageFolderURI = imageFolderURI;
        this.dailyRentalRate = dailyRentalRate;
        this.availabilityStatus = availabilityStatus;
        this.weight = weight;
        this.type = type;
        this.gears = gears;
    }
}
