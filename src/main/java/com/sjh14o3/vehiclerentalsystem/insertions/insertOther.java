package com.sjh14o3.vehiclerentalsystem.insertions;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.data.Bicycle;
import com.sjh14o3.vehiclerentalsystem.data.MotorBike;
import com.sjh14o3.vehiclerentalsystem.data.MotorizedVehicle;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;

public class insertOther {
    public static void main(String[] args) {
        MotorBike ducatiPanigale = new MotorBike("Ducati", "Panigale V4", (short) 2023, "panigale", "Ducati Red", 150, (short) 195, 1000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_MOTORBIKE, (byte) 6, (short) 214, (short) 124, MotorizedVehicle.TRANSMISSION_AT, (short) 300, 2.8, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 17, (short) 0, MotorizedVehicle.CLASSIFICATION_SPORT, new String[]{"ABS", "Traction Control", "Riding Modes"});

        MotorBike harleyDavidson = new MotorBike("Harley-Davidson", "Street Glide", (short) 2022, "street_glide", "Vivid Black", 120, (short) 375, 5000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_LARGE, Vehicle.TYPE_MOTORBIKE, (byte) 6, (short) 90, (short) 150, MotorizedVehicle.TRANSMISSION_AT, (short) 180, 6.0, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 22.7, (short) 0, MotorizedVehicle.CLASSIFICATION_CITY, new String[]{"Infotainment System", "Saddlebags", "Cruise Control"});

        MotorBike hondaCBR = new MotorBike("Honda-Motors", "CBR1000RR-R Fireblade", (short) 2021, "cbr", "Grand Prix Red", 140, (short) 201, 7500, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_MOTORBIKE, (byte) 6, (short) 215, (short) 113, MotorizedVehicle.TRANSMISSION_AT, (short) 299, 2.9, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 16.1, (short) 0, MotorizedVehicle.CLASSIFICATION_SPORT, new String[]{"ABS", "Quick shifter", "TFT Display"});

        MotorBike yamahaR1 = new MotorBike("Yamaha", "YZF-R1", (short) 2023, "r1", "Icon Blue", 145, (short) 199, 2000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_MOTORBIKE, (byte) 6, (short) 200, (short) 112, MotorizedVehicle.TRANSMISSION_AT, (short) 299, 2.9, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 17, (short) 0, MotorizedVehicle.CLASSIFICATION_SPORT, new String[]{"Traction Control", "Slide Control", "Lift Control"});

        MotorBike kawasakiNinja = new MotorBike("Kawasaki", "Ninja ZX-10R", (short) 2022, "ninja", "Lime Green", 135, (short) 207, 4000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_MOTORBIKE, (byte) 6, (short) 203, (short) 115, MotorizedVehicle.TRANSMISSION_AT, (short) 299, 2.9, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 17, (short) 0, MotorizedVehicle.CLASSIFICATION_SPORT, new String[]{"Cornering ABS", "Launch Control", "Engine Brake Control"});

        Bicycle specializedTarmac = new Bicycle("Specialized", "Tarmac SL7", (short) 2023, "tarmac", "Color Run Silver", 50, (short) 7, 500, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_BICYCLE, (byte) 22, Bicycle.HANDLEBAR_DROP, new String[]{"Carbon Wheels", "Power Meter"}, "Carbon Fiber");

        Bicycle trekDomane = new Bicycle("Trek", "Domane SL 6", (short) 2022, "domane", "Quicksilver", 40, (short) 9, 1000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_BICYCLE, (byte) 22, Bicycle.HANDLEBAR_DROP, new String[]{"Endurance Geometry", "IsoSpeed Decoupler"}, "Aluminum");

        Bicycle giantDefy = new Bicycle("Giant", "Defy Advanced 2", (short) 2021, "defy", "Metallic Black", 35, (short) 8.5, 1500, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_BICYCLE, (byte) 22, Bicycle.HANDLEBAR_DROP, new String[]{"D-Fuse Seatpost", "Composite Fork"}, "Composite");

        Bicycle marinFairfax = new Bicycle("Marin", "Fairfax SC4", (short) 2022, "fairfax", "Charcoal", 30, (short) 11, 2000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_BICYCLE, (byte) 24, Bicycle.HANDLEBAR_FLAT, new String[]{"Fitness Geometry", "Disc Brakes"}, "Aluminum");

        Bicycle cannondaleTopstone = new Bicycle("Cannondale", "Topstone 1", (short) 2023, "topstone", "Mantis", 45, (short) 10, 750, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, Vehicle.TYPE_BICYCLE, (byte) 11, Bicycle.HANDLEBAR_DROP, new String[]{"Gravel Geometry", "SmartSense Compatible"}, "Aluminum");

        /*Database.insertBicycle(specializedTarmac);
        Database.insertBicycle(trekDomane);
        Database.insertBicycle(giantDefy);
        Database.insertBicycle(marinFairfax);
        Database.insertBicycle(cannondaleTopstone);
        Database.insertMotorBike(ducatiPanigale);
        Database.insertMotorBike(harleyDavidson);
        Database.insertMotorBike(hondaCBR);
        Database.insertMotorBike(yamahaR1);
        Database.insertMotorBike(kawasakiNinja);*/
    }
}
