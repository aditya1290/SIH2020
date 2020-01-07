package com.example.sih2020;

import java.util.Date;

public class PastRecord {

    Date serviceDate;
    String serviceMan;
    String Description;
    boolean isDone;


    public PastRecord() {

    }

    public PastRecord(Date serviceDate, String serviceMan, String description, boolean isDone) {
        this.serviceDate = serviceDate;
        this.serviceMan = serviceMan;
        Description = description;
        this.isDone = isDone;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public String getServiceMan() {
        return serviceMan;
    }

    public String getDescription() {
        return Description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServiceMan(String serviceMan) {
        this.serviceMan = serviceMan;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
