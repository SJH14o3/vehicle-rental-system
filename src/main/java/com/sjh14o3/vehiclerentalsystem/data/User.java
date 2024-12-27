package com.sjh14o3.vehiclerentalsystem.data;

import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private ObjectId id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String[] phoneNumbers;
    private Date dateJoined;
    private byte reservationStatus;
    private String address;
    private String password;

    public static final byte STATUS_FREE = 1;
    public static final byte STATUS_RESERVED = 2;
    public static final byte STATUS_INUSE = 3;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public byte getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(byte reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Validate first and last name (alphabetic + space, no numbers or special chars)
    public static boolean isValidName(String name) {
        // Regex allows alphabets, spaces, and certain special characters (like apostrophe or hyphen)
        String nameRegex = "^[A-Za-zÀ-ÿÁ-ÿ-' ]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    // Validate username (alphanumeric + underscores and hyphens, between 3 and 15 characters)
    public static boolean isValidUsername(String username) {
        // Username should be 3-15 characters long and can contain alphanumeric, underscore, and hyphen.
        String usernameRegex = "^[a-zA-Z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        // Create a Pattern object
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        // Create a matcher object
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String input) {
        // Regex pattern to match exactly 11 digits, possibly separated by new lines
        String regex = "[0-9]{11}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    // this constructor is used to send a new user to database
    public User(String userName, String firstName, String lastName, String email, String[] phoneNumbers, String address, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.password = password;
        this.dateJoined = Date.getCurrentDateAsClass();
        this.reservationStatus = STATUS_FREE;
    }

    // this constructor is used when user login to app
    public User(ObjectId id, String userName, String firstName, String lastName, String email, String[] phoneNumbers, Date dateJoined, byte reservationStatus, String address) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.dateJoined = dateJoined;
        this.reservationStatus = reservationStatus;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumbers=" + Arrays.toString(phoneNumbers) +
                ", dateJoined=" + dateJoined +
                ", reservationStatus=" + reservationStatus +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
