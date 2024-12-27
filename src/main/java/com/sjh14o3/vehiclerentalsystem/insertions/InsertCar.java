package com.sjh14o3.vehiclerentalsystem.insertions;

import com.sjh14o3.vehiclerentalsystem.Database;
import com.sjh14o3.vehiclerentalsystem.data.Car;
import com.sjh14o3.vehiclerentalsystem.data.Date;
import com.sjh14o3.vehiclerentalsystem.data.MotorizedVehicle;
import com.sjh14o3.vehiclerentalsystem.data.Vehicle;

public class InsertCar {
    public static void main(String[] args) {
        String vehicle_folder_uri = "charger";

        String vehicle_make = "Dodge";
        String vehicle_model = "Charger SRT Hellcat";
        short vehicle_year = (short) 2019;
        String vehicle_color = "Red";
        int vehicle_rental_daily = 100;
        short weight = (short) 2076;
        int distance_travelled = 26540;
        byte condition = Vehicle.CONDITION_NEEDS_MAINTENANCE; // CONDITION_GOOD, CONDITION_NEW , CONDITION_NEEDS_MAINTENANCE
        byte size = Vehicle.SIZE_LARGE; // SIZE_SMALL , SIZE_MEDIUM , SIZE_LARGE
        byte gears = (byte) 8; // gear counts
        short power = (short) 707; // in horsepower
        short torque = (short) 650; // in N.M
        byte transmission = MotorizedVehicle.TRANSMISSION_AT; // TRANSMISSION_MANUAL, TRANSMISSION_AT, TRANSMISSION_DCT, TRANSMISSION_CVT
        short maxSpeed = (short) 320; // in KM.H
        double zeroToHundred = 4.8; // in seconds
        byte engine_type = MotorizedVehicle.ENGINE_PETROL; // ENGINE_PETROL, ENGINE_DIESEL, ENGINE_HYBRID, ENGINE_PLUGIN_HYBRID, ENGINE_ELECTRIC
        byte[] fuel_types = {MotorizedVehicle.FUEL_PETROL}; // for hybrid, use electric with main, options: FUEL_PETROL, FUEL_DIESEL, FUEL_ELECTRIC
        short fuel_capacity = (short) 70; // 0 if vehicle is electric - value in liter
        short battery_capacity = 0; // 0 if vehicle is not electric, hybrid or plug-in hybrid - value in KW.H
        byte classification = MotorizedVehicle.CLASSIFICATION_SPORT; // CLASSIFICATION_CITY, CLASSIFICATION_SPORT, CLASSIFICATION_OFF_ROAD
        byte seats = (short) 5;
        short trunk_capacity = (short) 470; // in liter
        byte segment = Car.SEGMENT_SEDAN; // SEGMENT_SUV, SEGMENT_CROSSOVER, SEGMENT_PICKUP_TRUCK, SEGMENT_SEDAN, SEGMENT_COUPE, SEGMENT_WAGON, SEGMENT_HATCH_BACK, SEGMENT_CONVERTIBLE, SEGMENT_MPV, SEGMENT_VAN
        byte doors = (byte) 4;

        Car car = new Car(vehicle_make,vehicle_model, vehicle_year, vehicle_folder_uri, vehicle_color, vehicle_rental_daily, weight, distance_travelled, condition, size, gears, power, torque, transmission, maxSpeed, zeroToHundred, engine_type, fuel_types, fuel_capacity, battery_capacity, classification, seats, trunk_capacity, segment, doors);
        Database.insertCar(car);
    }
}
