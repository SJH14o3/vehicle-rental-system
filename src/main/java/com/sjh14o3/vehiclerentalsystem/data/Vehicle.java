package com.sjh14o3.vehiclerentalsystem.data;

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
}
