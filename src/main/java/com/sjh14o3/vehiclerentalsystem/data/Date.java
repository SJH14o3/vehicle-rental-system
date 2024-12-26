package com.sjh14o3.vehiclerentalsystem.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private short year;
    private byte month;
    private byte day;

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    // assuming String is formatted with YYYYMMDD
    public static Date convertStringToDate(String in) {
        return new Date(Short.parseShort(in.substring(0,4)), Byte.parseByte(in.substring(4,6)), Byte.parseByte(in.substring(6,8)));
    }

    // returns current date as YYYYMMDD
    public static String getCurrentDateAsString() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Format the current date
        return currentDate.format(formatter);
    }

    public static Date getCurrentDateAsClass() {
        return convertStringToDate(getCurrentDateAsString());
    }

    // returns date as a string with YYYYMMDD format
    public String DateToFormattedString() {
        return "" + year + month + day;
    }

    public Date(short year, byte month, byte day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

}
