package com.sjh14o3.vehiclerentalsystem.data;

import org.bson.types.ObjectId;

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

    public String getHandleBarAsString() {
        return switch (handleBar) {
            case HANDLEBAR_FLAT -> "Flat";
            case HANDLEBAR_DROP -> "Drop";
            case HANDLEBAR_UPRIGHT -> "Upright";
            default -> "Unknown";
        };
    }

    @Override
    public String[] getAttributesAsStringArray() {
        String[] baseAttributes = super.getAttributesAsStringArray();
        String[] bicycleAttributes = new String[] {
                "handleBar: " + getHandleBarAsString(),
                "accessories: " + String.join(", ", accessories),
                "material: " + material
        };

        return mergeArrays(baseAttributes, bicycleAttributes);
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
    public Bicycle(ObjectId id, String make, String model, short year, String imageFolderURI, int dailyRentalRate, byte availabilityStatus, short weight, byte type, byte gears, String material) {
        super(id, make, model, year, imageFolderURI, dailyRentalRate, availabilityStatus, weight, type, gears);
        this.material = material;
    }

    public Bicycle() {}

    @Override
    public String cardInformation() {
        return getImageFolderURI() + "~" + getMake() + "~" + getModel() + "~" + getYear() + "~" + getWeight() + " KG~" +
                getMaterial() + "~" + getGears() + " Speed~" + getDailyRentalRate()  + "$~" + getType() + "~" +
                getAvailabilityStatus() + "~" + getId();
    }
}
