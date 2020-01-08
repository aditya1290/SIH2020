package com.example.sih2020;

import java.util.Date;

public class PastRecord {



    Date serviceDate;
    String serviceMan;
    String description;
    boolean done;

    public PastRecord(Date serviceDate, String serviceMan, String description, boolean isDone) {
        this.serviceDate = serviceDate;
        this.serviceMan = serviceMan;
        this.description = description;
        this.done = isDone;
    }
    public PastRecord(){}


    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceMan() {
        return serviceMan;
    }



    public void setServiceMan(String serviceMan) {
        this.serviceMan = serviceMan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
