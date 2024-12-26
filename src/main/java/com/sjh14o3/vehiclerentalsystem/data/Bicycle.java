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
}
