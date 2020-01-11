package com.example.sih2020.model;

public class Request {
    String serviceMan;
    String Responsible;
    String description;
    boolean status;
    String complaintId;

    public Request(){
    }

    public Request(String serviceMan, String responsible, String description, boolean status, String complaintId) {
        this.serviceMan = serviceMan;
        Responsible = responsible;
        this.description = description;
        this.status = status;
        this.complaintId = complaintId;
    }

    public String getServiceMan() {
        return serviceMan;
    }

    public String getResponsible() {
        return Responsible;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setServiceMan(String serviceMan) {
        this.serviceMan = serviceMan;
    }

    public void setResponsible(String responsible) {
        Responsible = responsible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }
}
