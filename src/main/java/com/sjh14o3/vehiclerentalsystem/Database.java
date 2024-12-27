package com.sjh14o3.vehiclerentalsystem;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.sjh14o3.vehiclerentalsystem.data.*;
import com.sjh14o3.vehiclerentalsystem.data.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;


public class Database {
    private static final String URI = "mongodb://localhost:27017/";
    private static final String DATABASE_NAME = "vrs";
    private static final String COLLECTION_USERS = "users";
    private static final String COLLECTION_VEHICLES = "vehicles";
    private static final String COLLECTION_RESERVATIONS = "reservations";

    private static final String MONGODB_ID = "_id";

    private static final String USER_USERNAME = "userName";
    private static final String USER_FIRSTNAME = "firstName";
    private static final String USER_LASTNAME = "lastName";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONES = "phoneNumbers";
    private static final String USER_DATE_JOINED = "dateJoined";
    private static final String USER_RES_STATUS = "reservationStatus";
    private static final String USER_ADDRESS = "address";
    private static final String USER_PASSWORD = "password";

    private static final String VEHICLE_MAKE = "make";
    private static final String VEHICLE_MODEL = "model";
    private static final String VEHICLE_YEAR = "year";
    private static final String VEHICLE_DATE_ADDED = "dateAdded";
    private static final String VEHICLE_FOLDER_URI = "folderUri";
    private static final String VEHICLE_COLOR = "color";
    private static final String VEHICLE_RENTAL_RATE = "rate";
    private static final String VEHICLE_AVAILABILITY_STATUS = "status";
    private static final String VEHICLE_WEIGHT = "weight";
    private static final String VEHICLE_DISTANCE_TRAVELLED = "distance";
    private static final String VEHICLE_CONDITION = "condition";
    private static final String VEHICLE_SIZE = "size";
    private static final String VEHICLE_TYPE = "type";
    private static final String VEHICLE_GEARS = "gears";
    private static final String VEHICLE_ACCESSORIES = "accessories";


    private static final String BICYCLE_HANDLEBAR = "handleBar";
    private static final String BICYCLE_MATERIAL = "material";

    private static final String MOTORIZED_POWER = "power";
    private static final String MOTORIZED_TORQUE = "torque";
    private static final String MOTORIZED_TRANSMISSION = "transmission";
    private static final String MOTORIZED_MAX_SPEED = "maxSpeed";
    private static final String MOTORIZED_ZERO_TO_HUNDRED_TIME = "time";
    private static final String MOTORIZED_ENGINE_TYPE = "engine";
    private static final String MOTORIZED_FUEL_TYPE = "fuel";
    private static final String MOTORIZED_FUEL_CAPACITY = "fuel_c";
    private static final String MOTORIZED_BATTERY_CAPACITY = "battery_c";
    private static final String MOTORIZED_CLASSIFICATION = "class";

    private static final String CAR_SEATS = "seats";
    private static final String CAR_TRUNK_CAPACITY = "trunk";
    private static final String CAR_SEGMENT = "segment";
    private static final String CAR_DOORS = "doors";

    public static final Byte ERROR_NO_USERNAME = -40;
    public static final Byte ERROR_PASSWORD_MISMATCHED = -41;
    public static final Byte ERROR_DATABASE = -1;
    public static final Byte MESSAGE_LOGIN_SUCCESS = 32;

    // a function to check if signing up user, has inserted a unique username
    public static boolean checkForDuplicateUserName(String username) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_USERS);
            Document user = collection.find(Filters.eq(USER_USERNAME, username)).first();
            return user != null;
        }
    }

    // a function to add user to database
    public static boolean addUser(User user) {
        boolean out = true;
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            // Access the "vrs" database and "users" collection
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_USERS);

            // Create a BSON document from the user object
            Document userDoc = new Document()
                    .append(USER_USERNAME, user.getUserName())
                    .append(USER_FIRSTNAME, user.getFirstName())
                    .append(USER_LASTNAME, user.getLastName())
                    .append(USER_EMAIL, user.getEmail())
                    .append(USER_PHONES, Arrays.asList(user.getPhoneNumbers())) // Store as an array
                    .append(USER_DATE_JOINED, user.getDateJoined().DateToFormattedString())
                    .append(USER_RES_STATUS, user.getReservationStatus())
                    .append(USER_ADDRESS, user.getAddress())
                    .append(USER_PASSWORD, user.getPassword());
            // Inserting the new user into the users collection
            collection.insertOne(userDoc);
        } catch (Exception e) {
            e.printStackTrace();
            out = false;
        }
        return out;
    }

    // used for login
    public static byte authenticateUser(String username, String password) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_USERS);

            Document userDoc = collection.find(Filters.eq(USER_USERNAME, username)).first();

            // no user with the username exists
            if (userDoc == null) {
                return ERROR_NO_USERNAME;
            }

            String storedPassword = userDoc.getString(USER_PASSWORD);
            // username is found but the password mismatches
            if (!storedPassword.equals(password)) {
                return ERROR_PASSWORD_MISMATCHED;
            }

            // success
            return MESSAGE_LOGIN_SUCCESS;

        } catch (Exception e) {
            System.err.println("Error authenticating user: " + e.getMessage());
            return ERROR_DATABASE;
        }
    }

    public static boolean insertCar(Car car) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);
            Document vehicle = new Document()
                    .append(VEHICLE_MAKE, car.getMake())
                    .append(VEHICLE_MODEL, car.getModel())
                    .append(VEHICLE_YEAR, car.getYear())
                    .append(VEHICLE_DATE_ADDED, car.getDateAdded().DateToFormattedString())
                    .append(VEHICLE_FOLDER_URI, car.getImageFolderURI())
                    .append(VEHICLE_COLOR, car.getColor())
                    .append(VEHICLE_RENTAL_RATE, car.getDailyRentalRate())
                    .append(VEHICLE_AVAILABILITY_STATUS, car.getAvailabilityStatus())
                    .append(VEHICLE_WEIGHT, car.getWeight())
                    .append(VEHICLE_DISTANCE_TRAVELLED, car.getDistanceTravelled())
                    .append(VEHICLE_CONDITION, car.getCondition())
                    .append(VEHICLE_SIZE, car.getSize())
                    .append(VEHICLE_TYPE, car.getType())
                    .append(VEHICLE_GEARS, car.getGears())
                    .append(MOTORIZED_POWER, car.getPower())
                    .append(MOTORIZED_TORQUE, car.getTorque())
                    .append(MOTORIZED_TRANSMISSION, car.getTransmission())
                    .append(MOTORIZED_MAX_SPEED, car.getMaxSpeed())
                    .append(MOTORIZED_ZERO_TO_HUNDRED_TIME, car.getZeroToHundredTime())
                    .append(MOTORIZED_ENGINE_TYPE, car.getEngineType())
                    .append(MOTORIZED_FUEL_TYPE, Arrays.asList(car.getFuelsAsInt()))
                    .append(MOTORIZED_FUEL_CAPACITY, car.getFuelCapacity())
                    .append(MOTORIZED_BATTERY_CAPACITY, car.getBatteryCapacity())
                    .append(MOTORIZED_CLASSIFICATION, car.getClassification())
                    .append(CAR_SEATS, car.getSeats())
                    .append(CAR_TRUNK_CAPACITY, car.getTrunkCapacity())
                    .append(CAR_SEGMENT, car.getSegment())
                    .append(CAR_DOORS, car.getDoors());
            collection.insertOne(vehicle);
        } catch (Exception e) {
            System.out.println("Error inserting car: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean insertMotorBike(MotorBike bike) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);
            Document vehicle = new Document()
                    .append(VEHICLE_MAKE, bike.getMake())
                    .append(VEHICLE_MODEL, bike.getModel())
                    .append(VEHICLE_YEAR, bike.getYear())
                    .append(VEHICLE_DATE_ADDED, bike.getDateAdded().DateToFormattedString())
                    .append(VEHICLE_FOLDER_URI, bike.getImageFolderURI())
                    .append(VEHICLE_COLOR, bike.getColor())
                    .append(VEHICLE_RENTAL_RATE, bike.getDailyRentalRate())
                    .append(VEHICLE_AVAILABILITY_STATUS, bike.getAvailabilityStatus())
                    .append(VEHICLE_WEIGHT, bike.getWeight())
                    .append(VEHICLE_DISTANCE_TRAVELLED, bike.getDistanceTravelled())
                    .append(VEHICLE_CONDITION, bike.getCondition())
                    .append(VEHICLE_SIZE, bike.getSize())
                    .append(VEHICLE_TYPE, bike.getType())
                    .append(VEHICLE_GEARS, bike.getGears())
                    .append(MOTORIZED_POWER, bike.getPower())
                    .append(MOTORIZED_TORQUE, bike.getTorque())
                    .append(MOTORIZED_TRANSMISSION, bike.getTransmission())
                    .append(MOTORIZED_MAX_SPEED, bike.getMaxSpeed())
                    .append(MOTORIZED_ZERO_TO_HUNDRED_TIME, bike.getZeroToHundredTime())
                    .append(MOTORIZED_ENGINE_TYPE, bike.getEngineType())
                    .append(MOTORIZED_FUEL_TYPE, Arrays.asList(bike.getFuelsAsInt()))
                    .append(MOTORIZED_FUEL_CAPACITY, bike.getFuelCapacity())
                    .append(MOTORIZED_BATTERY_CAPACITY, bike.getBatteryCapacity())
                    .append(MOTORIZED_CLASSIFICATION, bike.getClassification())
                    .append(VEHICLE_ACCESSORIES, Arrays.asList(bike.getAccessories()));
            collection.insertOne(vehicle);
        } catch (Exception e) {
            System.out.println("Error inserting motorbike: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean insertBicycle(Bicycle bicycle) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);
            Document vehicle = new Document()
                    .append(VEHICLE_MAKE, bicycle.getMake())
                    .append(VEHICLE_MODEL, bicycle.getModel())
                    .append(VEHICLE_YEAR, bicycle.getYear())
                    .append(VEHICLE_DATE_ADDED, bicycle.getDateAdded().DateToFormattedString())
                    .append(VEHICLE_FOLDER_URI, bicycle.getImageFolderURI())
                    .append(VEHICLE_COLOR, bicycle.getColor())
                    .append(VEHICLE_RENTAL_RATE, bicycle.getDailyRentalRate())
                    .append(VEHICLE_AVAILABILITY_STATUS, bicycle.getAvailabilityStatus())
                    .append(VEHICLE_WEIGHT, bicycle.getWeight())
                    .append(VEHICLE_DISTANCE_TRAVELLED, bicycle.getDistanceTravelled())
                    .append(VEHICLE_CONDITION, bicycle.getCondition())
                    .append(VEHICLE_SIZE, bicycle.getSize())
                    .append(VEHICLE_TYPE, bicycle.getType())
                    .append(VEHICLE_GEARS, bicycle.getGears())
                    .append(BICYCLE_MATERIAL, bicycle.getMaterial())
                    .append(BICYCLE_HANDLEBAR, bicycle.getHandleBar())
                    .append(VEHICLE_ACCESSORIES, Arrays.asList(bicycle.getAccessories()));
            collection.insertOne(vehicle);
        } catch (Exception e) {
            System.out.println("Error inserting motorbike: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static Vehicle[] getAllVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>(20);
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document vehicleDoc = cursor.next();
                    ObjectId id = vehicleDoc.getObjectId(MONGODB_ID);
                    byte type = Byte.parseByte(vehicleDoc.getInteger(VEHICLE_TYPE).toString());
                    String make = vehicleDoc.getString(VEHICLE_MAKE);
                    String model = vehicleDoc.getString(VEHICLE_MODEL);
                    short year = Short.parseShort(vehicleDoc.getInteger(VEHICLE_YEAR).toString());
                    String folderURI = vehicleDoc.getString(VEHICLE_FOLDER_URI);
                    byte gears = Byte.parseByte(vehicleDoc.getInteger(VEHICLE_GEARS).toString());
                    byte status = Byte.parseByte(vehicleDoc.getInteger(VEHICLE_AVAILABILITY_STATUS).toString());
                    int dailyRate = vehicleDoc.getInteger(VEHICLE_RENTAL_RATE);
                    short weight = Short.parseShort(vehicleDoc.getInteger(VEHICLE_WEIGHT).toString());
                    System.out.println(id);
                    if (type == Vehicle.TYPE_BICYCLE) {
                        String material = vehicleDoc.getString(BICYCLE_MATERIAL);
                        Bicycle bicycle = new Bicycle(id.toString(), make, model, year, folderURI, dailyRate, status, weight, type, gears, material);
                        vehicles.add(bicycle);
                    } else {
                        short power = Short.parseShort(vehicleDoc.getInteger(MOTORIZED_POWER).toString());
                        short torque = Short.parseShort(vehicleDoc.getInteger(MOTORIZED_TORQUE).toString());
                        byte transmission = Byte.parseByte(vehicleDoc.getInteger(MOTORIZED_TRANSMISSION).toString());
                        if (type == Vehicle.TYPE_CAR) {
                            Car car = new Car(id.toString(), make, model, year, folderURI, dailyRate, status, weight, type, gears, power, torque, transmission);
                            vehicles.add(car);
                        } else {
                            MotorBike motorBike = new MotorBike(id.toString(), make, model, year, folderURI, dailyRate, status, weight, type, gears, power, torque, transmission);
                            vehicles.add(motorBike);
                        }
                    }
                }
            }
        }
        return vehicles.toArray(new Vehicle[0]);
    }

    public static User getUser(String username) {
        User user;
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_USERS);
            Document userDoc = collection.find(Filters.eq(USER_USERNAME, username)).first();
            ObjectId id = userDoc.getObjectId(MONGODB_ID);
            String firstName = userDoc.getString(USER_FIRSTNAME);
            String lastName = userDoc.getString(USER_LASTNAME);
            String email = userDoc.getString(USER_EMAIL);
            String date = userDoc.getString(USER_DATE_JOINED);
            byte status = Byte.parseByte(userDoc.getInteger(USER_RES_STATUS).toString());
            String address = userDoc.getString(USER_ADDRESS);
            List<String> phones = userDoc.getList(USER_PHONES, String.class);
            user = new User(id, username, firstName, lastName, email, phones.toArray(new String[0]), Date.convertStringToDate(date), status, address);
        }
        return user;
    }
}
