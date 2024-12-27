package com.sjh14o3.vehiclerentalsystem.data;

public class ReserveOrder {
    private Date date;
    private int days;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public ReserveOrder(Date date, int days) {
        this.date = date;
        this.days = days;
    }
}
