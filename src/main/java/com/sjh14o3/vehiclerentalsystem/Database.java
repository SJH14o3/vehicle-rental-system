package com.sjh14o3.vehiclerentalsystem;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.sjh14o3.vehiclerentalsystem.data.User;
import org.bson.Document;

import java.util.Arrays;


public class Database {
    private static final String URI = "mongodb://localhost:27017/";
    private static final String DATABASE_NAME = "vrs";
    private static final String COLLECTION_USERS = "users";


    private static final String USER_USERNAME = "userName";
    private static final String USER_FIRSTNAME = "firstName";
    private static final String USER_LASTNAME = "lastName";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONES = "phoneNumbers";
    private static final String USER_DATE_JOINED = "dateJoined";
    private static final String USER_RES_STATUS = "reservationStatus";
    private static final String USER_ADDRESS = "address";
    private static final String USER_PASSWORD = "password";

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
}
