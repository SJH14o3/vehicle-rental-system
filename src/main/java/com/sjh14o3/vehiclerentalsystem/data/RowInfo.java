package com.sjh14o3.vehiclerentalsystem.data;

import org.bson.types.ObjectId;

// this class is used for reservations table information
public class RowInfo {
    private ObjectId id;
    private String reservationDate;
    private String vehicleInfo;
    private String startDate;
    private String endDate;
    private String status;
    private int payed;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }

    public RowInfo(ObjectId id, String reservationDate, String vehicleInfo, String startDate, String endDate, String status, int payed) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.vehicleInfo = vehicleInfo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.payed = payed;
    }
}
