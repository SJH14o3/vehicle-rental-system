package com.sjh14o3.vehiclerentalsystem;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.sjh14o3.vehiclerentalsystem.data.*;
import com.sjh14o3.vehiclerentalsystem.data.Date;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private static final String RESERVATION_VEHICLE_ID = "vehicleId";
    private static final String RESERVATION_USER_ID = "userId";
    private static final String RESERVATION_DATE = "reservationDate";
    private static final String RESERVATION_START_DATE = "startDate";
    private static final String RESERVATION_END_DATE = "endDate";
    private static final String RESERVATION_STATUS = "status";
    private static final String RESERVATION_PAYMENT = "payment";

    private static final String PAYMENT_AMOUNT = "amount";

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
                    vehicles.add(getVehicle(vehicleDoc));
                }
            }
        }
        return vehicles.toArray(new Vehicle[0]);
    }

    public static Vehicle[] getFilteredVehicles(boolean allowCar, boolean allowMotorbike, boolean allowBicycle, boolean onlyAvailable) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            // Access the database and collection
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);

            // Build the filter for VEHICLE_TYPE
            List<Bson> typeFilters = new ArrayList<>();
            if (allowCar) typeFilters.add(Filters.eq(VEHICLE_TYPE, Vehicle.TYPE_CAR));
            if (allowMotorbike) typeFilters.add(Filters.eq(VEHICLE_TYPE, Vehicle.TYPE_MOTORBIKE));
            if (allowBicycle) typeFilters.add(Filters.eq(VEHICLE_TYPE, Vehicle.TYPE_BICYCLE));

            Bson typeCondition = Filters.or(typeFilters);

            // Add "status" condition if required
            Bson finalFilter = onlyAvailable ? Filters.and(typeCondition, Filters.eq(VEHICLE_AVAILABILITY_STATUS, 1)) : typeCondition;

            // Query the collection with the built filter
            List<Vehicle> vehicles = new ArrayList<>(20);
            for (Document doc: collection.find(finalFilter)) {
                vehicles.add(getVehicle(doc));
            }
            return vehicles.toArray(new Vehicle[0]);
        }
    }

    public static Vehicle getVehicle(Document doc) {
        ObjectId id = doc.getObjectId(MONGODB_ID);
        byte type = Byte.parseByte(doc.getInteger(VEHICLE_TYPE).toString());
        String make = doc.getString(VEHICLE_MAKE);
        String model = doc.getString(VEHICLE_MODEL);
        short year = Short.parseShort(doc.getInteger(VEHICLE_YEAR).toString());
        String folderURI = doc.getString(VEHICLE_FOLDER_URI);
        byte gears = Byte.parseByte(doc.getInteger(VEHICLE_GEARS).toString());
        byte status = Byte.parseByte(doc.getInteger(VEHICLE_AVAILABILITY_STATUS).toString());
        int dailyRate = doc.getInteger(VEHICLE_RENTAL_RATE);
        short weight = Short.parseShort(doc.getInteger(VEHICLE_WEIGHT).toString());
        if (type == Vehicle.TYPE_BICYCLE) {
            String material = doc.getString(BICYCLE_MATERIAL);
            return new Bicycle(id, make, model, year, folderURI, dailyRate, status, weight, type, gears, material);
        } else {
            short power = Short.parseShort(doc.getInteger(MOTORIZED_POWER).toString());
            short torque = Short.parseShort(doc.getInteger(MOTORIZED_TORQUE).toString());
            byte transmission = Byte.parseByte(doc.getInteger(MOTORIZED_TRANSMISSION).toString());
            if (type == Vehicle.TYPE_CAR) {
                return new Car(id, make, model, year, folderURI, dailyRate, status, weight, type, gears, power, torque, transmission);
            } else {
               return new MotorBike(id, make, model, year, folderURI, dailyRate, status, weight, type, gears, power, torque, transmission);
            }
        }
    }

    public static Vehicle getVehicleUsingId(String id) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);
            ObjectId objectId = new ObjectId(id);
            Document vehicleDoc = collection.find(Filters.eq(MONGODB_ID, objectId)).first();
            assert vehicleDoc != null;
            return getVehicle(vehicleDoc);
        }
    }

    public static Vehicle getFullVehicleDetails(ObjectId id) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> vehiclesCollection = database.getCollection(COLLECTION_VEHICLES);

            Document query = new Document(MONGODB_ID, new ObjectId(String.valueOf(id)));
            Document result = vehiclesCollection.find(query).first();

            if (result == null) {
                return null; // No vehicle found with this ID
            }

            // Extract common fields
            String make = result.getString(VEHICLE_MAKE);
            String model = result.getString(VEHICLE_MODEL);
            short year = result.getInteger(VEHICLE_YEAR).shortValue();
            Date dateAdded = Date.convertStringToDate(result.getString(VEHICLE_DATE_ADDED));
            String folderUri = result.getString(VEHICLE_FOLDER_URI);
            String color = result.getString(VEHICLE_COLOR);
            int rate = result.getInteger(VEHICLE_RENTAL_RATE);
            byte status = result.getInteger(VEHICLE_AVAILABILITY_STATUS).byteValue();
            short weight = result.getInteger(VEHICLE_WEIGHT).shortValue();
            int distance = result.getInteger(VEHICLE_DISTANCE_TRAVELLED);
            byte condition = result.getInteger(VEHICLE_CONDITION).byteValue();
            byte size = result.getInteger(VEHICLE_SIZE).byteValue();
            byte type = result.getInteger(VEHICLE_TYPE).byteValue();
            byte gears = result.getInteger(VEHICLE_GEARS).byteValue();

            if (type != Vehicle.TYPE_BICYCLE) {
                short power = result.getInteger(MOTORIZED_POWER).shortValue();
                short torque = result.getInteger(MOTORIZED_TORQUE).shortValue();
                byte transmission = result.getInteger(MOTORIZED_TRANSMISSION).byteValue();
                short maxSpeed = result.getInteger(MOTORIZED_MAX_SPEED).shortValue();
                double zeroTo100 = result.getDouble(MOTORIZED_ZERO_TO_HUNDRED_TIME);
                byte engineType = result.getInteger(MOTORIZED_ENGINE_TYPE).byteValue();
                Integer[] fuelTypesInt = result.getList(MOTORIZED_FUEL_TYPE, Integer.class).toArray(new Integer[0]);
                byte[] fuels = new byte[fuelTypesInt.length];
                for (int i = 0; i < fuels.length; i++) {
                    fuels[i] = fuelTypesInt[i].byteValue();
                }
                short fuelCapacity = result.getInteger(MOTORIZED_FUEL_CAPACITY).shortValue();
                short batteryCapacity = result.getInteger(MOTORIZED_BATTERY_CAPACITY).shortValue();
                byte classification = result.getInteger(MOTORIZED_CLASSIFICATION).byteValue();
                // Determine the specific type of vehicle
                if (type == Vehicle.TYPE_CAR) {
                    byte seats = result.getInteger(CAR_SEATS).byteValue();
                    short trunkCapacity = result.getInteger(CAR_TRUNK_CAPACITY).shortValue();
                    byte segment = result.getInteger(CAR_SEGMENT).byteValue();
                    byte doors = result.getInteger(CAR_DOORS).byteValue();

                    Car car = new Car();
                    car.setMake(make);
                    car.setModel(model);
                    car.setYear(year);
                    car.setDateAdded(dateAdded);
                    car.setImageFolderURI(folderUri);
                    car.setColor(color);
                    car.setDailyRentalRate(rate);
                    car.setAvailabilityStatus(status);
                    car.setWeight(weight);
                    car.setDistanceTravelled(distance);
                    car.setCondition(condition);
                    car.setSize(size);
                    car.setType(type);
                    car.setGears(gears);

                    car.setPower(power);
                    car.setTorque(torque);
                    car.setMaxSpeed(maxSpeed);
                    car.setZeroToHundredTime(zeroTo100);
                    car.setEngineType(engineType);
                    car.setFuelType(fuels);
                    car.setFuelCapacity(fuelCapacity);
                    car.setBatteryCapacity(batteryCapacity);
                    car.setClassification(classification);
                    car.setTransmission(transmission);


                    car.setSeats(seats);
                    car.setTrunkCapacity(trunkCapacity);
                    car.setSegment(segment);
                    car.setDoors(doors);

                    return car;

                } else if (type == Vehicle.TYPE_MOTORBIKE) {
                    String[] accessories = result.getList(VEHICLE_ACCESSORIES, String.class).toArray(new String[0]);

                    MotorBike bike = new MotorBike();
                    bike.setMake(make);
                    bike.setModel(model);
                    bike.setYear(year);
                    bike.setDateAdded(dateAdded);
                    bike.setImageFolderURI(folderUri);
                    bike.setColor(color);
                    bike.setDailyRentalRate(rate);
                    bike.setAvailabilityStatus(status);
                    bike.setWeight(weight);
                    bike.setDistanceTravelled(distance);
                    bike.setCondition(condition);
                    bike.setSize(size);
                    bike.setType(type);
                    bike.setGears(gears);


                    bike.setPower(power);
                    bike.setTorque(torque);
                    bike.setMaxSpeed(maxSpeed);
                    bike.setZeroToHundredTime(zeroTo100);
                    bike.setEngineType(engineType);
                    bike.setFuelType(fuels);
                    bike.setFuelCapacity(fuelCapacity);
                    bike.setBatteryCapacity(batteryCapacity);
                    bike.setClassification(classification);
                    bike.setTransmission(transmission);

                    bike.setAccessories(accessories);

                    return bike;

                }
            } else if (type == Vehicle.TYPE_BICYCLE) {
                byte handleBar = result.getInteger(BICYCLE_HANDLEBAR).byteValue();
                String[] accessories = result.getList(VEHICLE_ACCESSORIES, String.class).toArray(new String[0]);
                String material = result.getString(BICYCLE_MATERIAL);

                Bicycle bicycle = new Bicycle();
                bicycle.setMake(make);
                bicycle.setModel(model);
                bicycle.setYear(year);
                bicycle.setDateAdded(dateAdded);
                bicycle.setImageFolderURI(folderUri);
                bicycle.setColor(color);
                bicycle.setDailyRentalRate(rate);
                bicycle.setAvailabilityStatus(status);
                bicycle.setWeight(weight);
                bicycle.setDistanceTravelled(distance);
                bicycle.setCondition(condition);
                bicycle.setSize(size);
                bicycle.setType(type);
                bicycle.setGears(gears);

                bicycle.setHandleBar(handleBar);
                bicycle.setAccessories(accessories);
                bicycle.setMaterial(material);

                return bicycle;
            }

            return null; // If no matching type is found
        }
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

    public static boolean addReservation(Reservation reservation) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_RESERVATIONS);
            // Create the document to insert
            Document newDocument = new Document()
                    .append(RESERVATION_PAYMENT, new Document(PAYMENT_AMOUNT, reservation.getPayment().getAmount()))
                    .append(RESERVATION_DATE, reservation.getReservationDate().DateToFormattedString())
                    .append(RESERVATION_START_DATE, reservation.getStartDate().DateToFormattedString())
                    .append(RESERVATION_END_DATE, reservation.getEndDate().DateToFormattedString())
                    .append(RESERVATION_STATUS, reservation.getStatus())
                    .append(RESERVATION_USER_ID, reservation.getUserId()) // ObjectId of the user
                    .append(RESERVATION_VEHICLE_ID, reservation.getVehicleId()); // ObjectId of the vehicle

            try {
                collection.insertOne(newDocument);
                updateUserStatus(Statics.getUser().getId(), User.STATUS_RESERVED);
                Statics.getUser().setReservationStatus(User.STATUS_RESERVED);
                updateVehicleStatus(Statics.getVehicle().getId(), Vehicle.AVAILABILITY_RESERVED);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void updateUserStatus(ObjectId userid, byte newStatus) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_USERS);
            Document updateQuery = new Document("$set", new Document(USER_RES_STATUS, newStatus));
            collection.updateOne(new Document(MONGODB_ID, userid), updateQuery);
        }
    }

    public static void updateVehicleStatus(ObjectId vehicleId, byte newStatus) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_VEHICLES);
            Document updateQuery = new Document("$set", new Document(VEHICLE_AVAILABILITY_STATUS, newStatus));
            collection.updateOne(new Document(MONGODB_ID, vehicleId), updateQuery);
        }
    }

    public static List<RowInfo> getUserReservations(ObjectId userId) {
        List<RowInfo> reservationList = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> reservationsCollection = database.getCollection(COLLECTION_RESERVATIONS);

            // Aggregate pipeline to join reservations with vehicles
            List<Bson> pipeline = List.of(
                    Aggregates.match(Filters.eq(RESERVATION_USER_ID, userId)),
                    Aggregates.lookup(COLLECTION_VEHICLES, RESERVATION_VEHICLE_ID, MONGODB_ID, "vehicleInfo")
            );

            // Execute the aggregation
            MongoIterable<Document> results = reservationsCollection.aggregate(pipeline);

            // Parse results
            for (Document doc : results) {
                ObjectId id = doc.getObjectId(MONGODB_ID);
                String reservationDate = Date.fancyFormatting(doc.getString(RESERVATION_DATE));
                String startDate = Date.fancyFormatting(doc.getString(RESERVATION_START_DATE));
                String endDate = Date.fancyFormatting(doc.getString(RESERVATION_END_DATE));
                byte statusByte = doc.getInteger(RESERVATION_STATUS).byteValue();
                String status = Reservation.getReservationStatusAsString(statusByte);
                int payed = doc.get(RESERVATION_PAYMENT, Document.class).getInteger(PAYMENT_AMOUNT);
                // Get vehicle info from joined data
                List<Document> vehicleInfoDocs = doc.getList("vehicleInfo", Document.class);
                String vehicleInfo = vehicleInfoDocs != null && !vehicleInfoDocs.isEmpty() ?
                        vehicleInfoDocs.get(0).getString(VEHICLE_MAKE) + " " +
                                vehicleInfoDocs.get(0).getString(VEHICLE_MODEL) + " " +
                                vehicleInfoDocs.get(0).getInteger(VEHICLE_YEAR)
                        : "Unknown Vehicle";

                // Create and add RowInfo
                RowInfo rowInfo = new RowInfo(id, reservationDate, vehicleInfo, startDate, endDate, status, payed);
                reservationList.add(rowInfo);
            }
        }

        return reservationList;
    }

    // this function finds out if a user last reservation is in waiting mode since they can cancel it.
    public static boolean isLastReservationInWaitingMode(ObjectId userId) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> reservationsCollection = database.getCollection(COLLECTION_RESERVATIONS);

            // Aggregate pipeline to find the latest reservation for the user, ignoring cancel status
            List<Bson> pipeline = List.of(
                    Aggregates.match(Filters.and(
                            Filters.eq(RESERVATION_USER_ID, userId),        // Filter by user ID
                            Filters.ne(RESERVATION_STATUS, Reservation.STATUS_CANCELLED)             // Exclude reservations with cancel status
                    )),
                    Aggregates.sort(Sorts.descending(RESERVATION_DATE)),  // Sort by reservationDate (latest first)
                    Aggregates.limit(1)  // Take only the latest reservation
            );

            // Execute the aggregation
            Document latestReservation = reservationsCollection.aggregate(pipeline).first();

            // Check the status of the latest reservation
            if (latestReservation != null) {
                int status = latestReservation.getInteger(RESERVATION_STATUS);
                return status == 3;  // Status 3 indicates "waiting mode"
            }
        }

        return false;  // No valid reservations found or not in waiting mode
    }

    // the row was found by last function now updates to cancelled
    public static boolean updateLastReservationStatusToCancel(ObjectId userId) {
        if (isLastReservationInWaitingMode(userId)) {
            try (MongoClient mongoClient = MongoClients.create(URI)) {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> reservationsCollection = database.getCollection(COLLECTION_RESERVATIONS);
                MongoCollection<Document> usersCollection = database.getCollection(COLLECTION_USERS);
                MongoCollection<Document> vehiclesCollection = database.getCollection(COLLECTION_VEHICLES);

                // Aggregate pipeline to find the latest reservation for the user, ignoring canceled status
                List<Bson> pipeline = List.of(
                        Aggregates.match(Filters.and(
                                Filters.eq(RESERVATION_USER_ID, userId),       // Filter by user ID
                                Filters.ne(RESERVATION_STATUS, Reservation.STATUS_CANCELLED)             // Exclude reservations with status = 4
                        )),
                        Aggregates.sort(Sorts.descending(RESERVATION_DATE)),  // Sort by reservationDate (latest first)
                        Aggregates.limit(1)  // Take only the latest reservation
                );

                // Execute the aggregation to get the latest reservation
                Document latestReservation = reservationsCollection.aggregate(pipeline).first();

                // Check if a valid reservation is found
                if (latestReservation != null) {
                    ObjectId reservationId = latestReservation.getObjectId(MONGODB_ID);
                    ObjectId vehicleId = latestReservation.getObjectId(RESERVATION_VEHICLE_ID);

                    // Update the reservation status to cancelled
                    UpdateResult reservationUpdateResult = reservationsCollection.updateOne(
                            Filters.eq(MONGODB_ID, reservationId),      // Match the reservation by its _id
                            Updates.set(RESERVATION_STATUS, Reservation.STATUS_CANCELLED)               // Set status to 4
                    );

                    System.out.println("user id : " + userId);
                    Statics.getUser().setReservationStatus(User.STATUS_FREE);
                    // Update the user's reservationStatus to free
                    UpdateResult userUpdateResult = usersCollection.updateOne(
                            Filters.eq(MONGODB_ID, userId),              // Match the user by userId
                            Updates.set(USER_RES_STATUS, User.STATUS_FREE)     // Set reservationStatus to free
                    );

                    // Update the vehicle's availabilityStatus to available
                    System.out.println("vehicle id : " + vehicleId);
                    UpdateResult vehicleUpdateResult = vehiclesCollection.updateOne(
                            Filters.eq(MONGODB_ID, vehicleId),           // Match the vehicle by vehicleId
                            Updates.set(VEHICLE_AVAILABILITY_STATUS, Vehicle.AVAILABILITY_AVAILABLE)    // Set availabilityStatus to available
                    );

                    // Return true if all updates were successful
                    return reservationUpdateResult.getMatchedCount() > 0
                            && reservationUpdateResult.getModifiedCount() > 0
                            && userUpdateResult.getMatchedCount() > 0
                            && userUpdateResult.getModifiedCount() > 0
                            && vehicleUpdateResult.getMatchedCount() > 0
                            && vehicleUpdateResult.getModifiedCount() > 0;
                }
            }
        }

        return false;  // Reservation is either not in waiting mode or update failed
    }

    public static void updateReservationStatuses() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> reservationsCollection = database.getCollection(COLLECTION_RESERVATIONS);
            MongoCollection<Document> usersCollection = database.getCollection(COLLECTION_USERS);
            MongoCollection<Document> vehiclesCollection = database.getCollection(COLLECTION_VEHICLES);

            // Get all reservations
            FindIterable<Document> reservations = reservationsCollection.find();

            for (Document reservation : reservations) {
                String reservationDate = reservation.getString(RESERVATION_DATE);
                String startDate = reservation.getString(RESERVATION_START_DATE);
                String endDate = reservation.getString(RESERVATION_END_DATE);
                ObjectId userId = reservation.getObjectId(RESERVATION_USER_ID);
                ObjectId vehicleId = reservation.getObjectId(RESERVATION_VEHICLE_ID);

                LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
                LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);

                // Case 1: If today is between the start and end dates
                if (!today.isBefore(start) && !today.isAfter(end)) {
                    reservationsCollection.updateOne(
                            Filters.eq(MONGODB_ID, reservation.getObjectId(MONGODB_ID)),
                            Updates.set(RESERVATION_STATUS, Reservation.STATUS_USING)
                    );

                    usersCollection.updateOne(
                            Filters.eq(MONGODB_ID, userId),
                            Updates.set(USER_RES_STATUS, User.STATUS_INUSE)
                    );

                    vehiclesCollection.updateOne(
                            Filters.eq(MONGODB_ID, vehicleId),
                            Updates.set(VEHICLE_AVAILABILITY_STATUS, Vehicle.AVAILABILITY_AVAILABLE)
                    );
                }
                // Case 2: If today is past the end date
                else if (today.isAfter(end)) {
                    // Update reservation to status 1 (completed)
                    reservationsCollection.updateOne(
                            Filters.eq(MONGODB_ID, reservation.getObjectId(MONGODB_ID)),
                            Updates.set(RESERVATION_STATUS, Reservation.STATUS_USING)
                    );

                    // Update user to status 1 (reservation completed)
                    usersCollection.updateOne(
                            Filters.eq(MONGODB_ID, userId),
                            Updates.set(USER_RES_STATUS, User.STATUS_FREE)
                    );

                    // Update vehicle to status 1 (available)
                    vehiclesCollection.updateOne(
                            Filters.eq(MONGODB_ID, vehicleId),
                            Updates.set(VEHICLE_AVAILABILITY_STATUS, Vehicle.AVAILABILITY_AVAILABLE)
                    );
                }
            }
        }
    }

}
