package com.sjh14o3.vehiclerentalsystem.data;

public final class Bicycle extends Vehicle{
    private byte handleBar;
    private String[] accessories;
    private String material;

    public static final byte HANDLEBAR_FLAT = 1;
    public static final byte HANDLEBAR_DROP = 2;
    public static final byte HANDLEBAR_UPRIGHT = 3;


    public byte getHandleBar() {
        return handleBar;
    }

    public void setHandleBar(byte handleBar) {
        this.handleBar = handleBar;
    }

    public String[] getAccessories() {
        return accessories;
    }

    public void setAccessories(String[] accessories) {
        this.accessories = accessories;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    // used for storing in database
    public Bicycle(String make, String model, short year, String imageFolderURI, String color, int dailyRentalRate, short weight,
                   int distanceTravelled, byte condition, byte size, byte type, byte gears, byte handleBar, String[] accessories, String material) {
        super(make, model, year, imageFolderURI, color, dailyRentalRate, weight, distanceTravelled, condition, size, type, gears);
        this.handleBar = handleBar;
        this.accessories = accessories;
        this.material = material;
    }

    // used for main list view
    public Bicycle(String id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears, String material) {
        super(id, make, model, year, imageFolderURI, dailyRentalRate, availabilityStatus, weight, type, gears);
        this.material = material;
    }

    @Override
    public String cardInformation() {
        return getImageFolderURI() + "~" + getMake() + "~" + getModel() + "~" + getYear() + "~" + getWeight() + " KG~" +
                getMaterial() + "~" + getGears() + " Speed~" + getDailyRentalRate()  + "$";
    }
}
