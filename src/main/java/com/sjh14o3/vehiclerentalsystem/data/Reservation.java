package com.sjh14o3.vehiclerentalsystem.data;

import org.bson.types.ObjectId;

public class Reservation {
    private ObjectId id;
    private ObjectId userId;
    private ObjectId vehicleId;
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private byte status;
    private Payment payment;
    private String vehicleDetails;

    public static final byte STATUS_FINISHED = 1; // end date has been passed
    public static final byte STATUS_USING = 2; // currently between
    public static final byte STATUS_RESERVED = 3;
    public static final byte STATUS_CANCELLED = 4; // it was reserved but user deleted it

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(ObjectId vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public static String getReservationStatusAsString(byte status) {
        return switch (status) {
            case STATUS_FINISHED -> "Finished";
            case STATUS_USING -> "Using";
            case STATUS_RESERVED -> "Reserved";
            case STATUS_CANCELLED -> "Cancelled";
            default -> "Unknown";
        };
    }

    // when a new reservation is made
    public Reservation(ObjectId userId, ObjectId vehicleId, Date reservationDate, Date startDate, Date endDate, int amount) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = STATUS_RESERVED;
        this.payment = new Payment(amount);
    }

    public Reservation(ObjectId id, ObjectId vehicleId, Date reservationDate, Date startDate, Date endDate, byte status, Payment payment, String vehicleDetails) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.payment = payment;
        this.vehicleDetails = vehicleDetails;
    }
}
