package com.sjh14o3.vehiclerentalsystem.insertions;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.data.Car;
import com.sjh14o3.vehiclerentalsystem.data.MotorizedVehicle;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;

public class InsertCar2 {
    public static void main(String[] args) {
        byte[] fuelType = {MotorizedVehicle.FUEL_PETROL, MotorizedVehicle.FUEL_ELECTRIC};
        byte[] hybridFuelType = {MotorizedVehicle.FUEL_PETROL, MotorizedVehicle.FUEL_ELECTRIC};
        byte[] petrolFuelType = {MotorizedVehicle.FUEL_PETROL};



        // Creating the Car instance
        Car toyotaRav4XLE = new Car(
                "Toyota",                    // Make
                "RAV4 XLE",                      // Model
                (short) 2024,                 // Year
                "rav4",        // Image Folder URI
                "Lunar Rock",                // Color
                4500,                         // Daily Rental Rate (example in USD cents)
                (short) 1650,                 // Weight in KG
                0,                            // Distance Travelled (new car)
                Vehicle.CONDITION_NEW,        // Condition
                Vehicle.SIZE_MEDIUM,          // Size
                (byte) 8,                     // Gears (8-speed automatic)
                (short) 203,                  // Power in HP
                (short) 184,                  // Torque in N·m
                MotorizedVehicle.TRANSMISSION_AT, // Transmission (Automatic)
                (short) 190,                  // Max Speed in KM/H
                8.2,                          // 0-100 km/h time in seconds
                MotorizedVehicle.ENGINE_HYBRID, // Engine Type
                fuelType,                     // Fuel Type
                (short) 55,                   // Fuel Capacity in liters
                (short) 18,                   // Battery Capacity in kWh (for hybrid system)
                MotorizedVehicle.CLASSIFICATION_CITY, // Classification
                (byte) 5,                     // Seats
                (short) 580,                  // Trunk Capacity in liters
                Car.SEGMENT_CROSSOVER,        // Segment
                (byte) 5                      // Doors
        );

        // Honda HR-V 2022 (example trim: EX)
        Car hondaHRV2022 = new Car(
                "Honda",                     // Make
                "HR-V",                      // Model
                (short) 2022,                 // Year
                "hrv",          // Image Folder URI
                "Crystal Black Pearl",       // Color
                3000,                         // Daily Rental Rate (example in USD cents)
                (short) 1425,                 // Weight in KG
                0,                            // Distance Travelled (new car)
                Vehicle.CONDITION_NEW,        // Condition
                Vehicle.SIZE_MEDIUM,          // Size
                (byte) 6,                     // Gears (CVT transmission)
                (short) 158,                  // Power in HP
                (short) 138,                  // Torque in N·m
                MotorizedVehicle.TRANSMISSION_CVT, // Transmission (CVT)
                (short) 180,                  // Max Speed in KM/H
                9.8,                          // 0-100 km/h time in seconds
                MotorizedVehicle.ENGINE_PETROL, // Engine Type
                hybridFuelType,               // Fuel Type
                (short) 50,                   // Fuel Capacity in liters
                (short) 0,                    // Battery Capacity in kWh (not hybrid)
                MotorizedVehicle.CLASSIFICATION_CITY, // Classification
                (byte) 5,                     // Seats
                (short) 470,                  // Trunk Capacity in liters
                Car.SEGMENT_CROSSOVER,        // Segment
                (byte) 5                      // Doors
        );

        // Hyundai Sonata Hybrid 2020 (example trim: Limited)
        Car hyundaiSonataHybrid2020 = new Car(
                "Hyundai",                   // Make
                "Sonata Hybrid",             // Model
                (short) 2020,                 // Year
                "sonata", // Image Folder URI
                "Shimmering Silver Pearl",  // Color
                3200,                         // Daily Rental Rate (example in USD cents)
                (short) 1540,                 // Weight in KG
                0,                            // Distance Travelled (new car)
                Vehicle.CONDITION_NEW,        // Condition
                Vehicle.SIZE_MEDIUM,          // Size
                (byte) 6,                     // Gears (6-speed automatic)
                (short) 192,                  // Power in HP
                (short) 139,                  // Torque in N·m
                MotorizedVehicle.TRANSMISSION_AT, // Transmission (Automatic)
                (short) 180,                  // Max Speed in KM/H
                8.3,                          // 0-100 km/h time in seconds
                MotorizedVehicle.ENGINE_HYBRID, // Engine Type
                hybridFuelType,               // Fuel Type
                (short) 45,                   // Fuel Capacity in liters
                (short) 1.6,                  // Battery Capacity in kWh
                MotorizedVehicle.CLASSIFICATION_CITY, // Classification
                (byte) 5,                     // Seats
                (short) 510,                  // Trunk Capacity in liters
                Car.SEGMENT_SEDAN,            // Segment
                (byte) 4                      // Doors
        );

        // Volkswagen GTI 2024 (example trim: Autobahn)
        Car volkswagenGTI2024 = new Car(
                "Volkswagen",                // Make
                "GTI",                       // Model
                (short) 2024,                 // Year
                "gti",     // Image Folder URI
                "Deep Black Pearl",          // Color
                4000,                         // Daily Rental Rate (example in USD cents)
                (short) 1400,                 // Weight in KG
                0,                            // Distance Travelled (new car)
                Vehicle.CONDITION_NEW,        // Condition
                Vehicle.SIZE_SMALL,           // Size
                (byte) 7,                     // Gears (7-speed DCT)
                (short) 241,                  // Power in HP
                (short) 273,                  // Torque in N·m
                MotorizedVehicle.TRANSMISSION_DCT, // Transmission (DCT)
                (short) 250,                  // Max Speed in KM/H
                5.6,                          // 0-100 km/h time in seconds
                MotorizedVehicle.ENGINE_PETROL, // Engine Type
                petrolFuelType,               // Fuel Type
                (short) 50,                   // Fuel Capacity in liters
                (short) 0,                    // Battery Capacity in kWh
                MotorizedVehicle.CLASSIFICATION_SPORT, // Classification
                (byte) 5,                     // Seats
                (short) 374,                  // Trunk Capacity in liters
                Car.SEGMENT_HATCH_BACK,       // Segment
                (byte) 5                      // Doors
        );

        // Mercedes-Benz S-Class 2022 (example trim: S 580 4MATIC)
        Car mercedesSClass2022 = new Car(
                "Mercedes-Benz",             // Make
                "S-Class",                   // Model
                (short) 2022,                 // Year
                "images/mercedes/s_class",   // Image Folder URI
                "Obsidian Black Metallic",  // Color
                7000,                         // Daily Rental Rate (example in USD cents)
                (short) 1990,                 // Weight in KG
                0,                            // Distance Travelled (new car)
                Vehicle.CONDITION_NEW,        // Condition
                Vehicle.SIZE_LARGE,           // Size
                (byte) 9,                     // Gears (9-speed automatic)
                (short) 496,                  // Power in HP
                (short) 700,                  // Torque in N·m
                MotorizedVehicle.TRANSMISSION_AT, // Transmission (Automatic)
                (short) 250,                  // Max Speed in KM/H
                4.4,                          // 0-100 km/h time in seconds
                MotorizedVehicle.ENGINE_HYBRID, // Engine Type (mild hybrid system)
                hybridFuelType,               // Fuel Type
                (short) 70,                   // Fuel Capacity in liters
                (short) 48,                   // Battery Capacity in kWh (mild hybrid)
                MotorizedVehicle.CLASSIFICATION_LUXURY, // Classification
                (byte) 5,                     // Seats
                (short) 540,                  // Trunk Capacity in liters
                Car.SEGMENT_SEDAN,            // Segment
                (byte) 4                      // Doors
        );
        Car lexusTX = new Car("Lexus", "TX", (short) 2025, "tx", "Atomic Silver", 150, (short) 2000, 0, Vehicle.CONDITION_NEW, Vehicle.SIZE_LARGE, (byte) 8, (short) 366, (short) 406, MotorizedVehicle.TRANSMISSION_AT, (short) 209, 6.1, MotorizedVehicle.ENGINE_HYBRID, new byte[]{MotorizedVehicle.FUEL_PETROL, MotorizedVehicle.FUEL_ELECTRIC}, (short) 65, (short) 10, MotorizedVehicle.CLASSIFICATION_CITY, (byte) 7, (short) 20, Car.SEGMENT_SUV, (byte) 4);

        // 2021 BMW M2
        Car bmwM2 = new Car("BMW", "M2", (short) 2021, "m2", "Long Beach Blue Metallic", 120, (short) 1600, 25000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_SMALL, (byte) 7, (short) 405, (short) 500, MotorizedVehicle.TRANSMISSION_MANUAL, (short) 250, 4.2, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 52, (short) 0, MotorizedVehicle.CLASSIFICATION_SPORT, (byte) 4, (short) 390, Car.SEGMENT_COUPE, (byte) 2);

        // 2023 Cadillac Escalade
        Car cadillacEscalade = new Car("Cadillac", "Escalade", (short) 2023, "escalade", "Black Raven", 200, (short) 2700, 10000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_LARGE, (byte) 10, (short) 420, (short) 624, MotorizedVehicle.TRANSMISSION_AT, (short) 180, 6.1, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 91, (short) 0, MotorizedVehicle.CLASSIFICATION_CITY, (byte) 8, (short) 25, Car.SEGMENT_SUV, (byte) 4);

        // 2025 Ford Mustang
        Car fordMustang = new Car("Ford", "Mustang", (short) 2025, "mustang", "Grabber Blue", 130, (short) 1700, 0, Vehicle.CONDITION_NEW, Vehicle.SIZE_MEDIUM, (byte) 10, (short) 480, (short) 569, MotorizedVehicle.TRANSMISSION_AT, (short) 250, 4.0, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 61, (short) 0, MotorizedVehicle.CLASSIFICATION_SPORT, (byte) 4, (short) 13, Car.SEGMENT_COUPE, (byte) 2);

        // 2022 Tesla Model 3
        Car teslaModel3 = new Car("Tesla", "Model 3", (short) 2022, "tmodel3", "Pearl White Multi-Coat", 40, (short) 1800, 15000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_MEDIUM, (byte) 1, (short) 450, (short) 493, MotorizedVehicle.TRANSMISSION_AT, (short) 225, 3.1, MotorizedVehicle.ENGINE_ELECTRIC, new byte[]{MotorizedVehicle.FUEL_ELECTRIC}, (short) 0, (short) 75, MotorizedVehicle.CLASSIFICATION_CITY, (byte) 5, (short) 15, Car.SEGMENT_SEDAN, (byte) 4);

        Car fordSuperDuty = new Car("Ford", "Super Duty", (short) 2023, "/images/ford_super_duty", "Oxford White", 250, (short) 3500, 5000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_LARGE, (byte) 10, (short) 475, (short) 1050, MotorizedVehicle.TRANSMISSION_AT, (short) 160, 7.5, MotorizedVehicle.ENGINE_DIESEL, new byte[]{MotorizedVehicle.FUEL_DIESEL}, (short) 129, (short) 0, MotorizedVehicle.CLASSIFICATION_OFF_ROAD, (byte) 6, (short) 65, Car.SEGMENT_PICKUP_TRUCK, (byte) 4);

        // 2024 Kia Carnival
        Car kiaCarnival = new Car("Kia", "Carnival", (short) 2024, "/images/kia_carnival", "Snow White Pearl", 180, (short) 2000, 2000, Vehicle.CONDITION_GOOD, Vehicle.SIZE_LARGE, (byte) 8, (short) 290, (short) 355, MotorizedVehicle.TRANSMISSION_AT, (short) 190, 7.0, MotorizedVehicle.ENGINE_PETROL, new byte[]{MotorizedVehicle.FUEL_PETROL}, (short) 72, (short) 0, MotorizedVehicle.CLASSIFICATION_CITY, (byte) 8, (short) 40, Car.SEGMENT_MPV, (byte) 4);
        /*Database.insertCar(toyotaRav4XLE);
        Database.insertCar(hondaHRV2022);
        Database.insertCar(volkswagenGTI2024);
        Database.insertCar(mercedesSClass2022);
        Database.insertCar(hyundaiSonataHybrid2020);
        Database.insertCar(lexusTX);
        Database.insertCar(bmwM2);
        Database.insertCar(cadillacEscalade);
        Database.insertCar(fordMustang);
        Database.insertCar(teslaModel3);*/
        //Database.insertCar(fordSuperDuty);
        //Database.insertCar(kiaCarnival);
    }
}
