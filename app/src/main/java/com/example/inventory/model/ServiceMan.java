package com.example.inventory.model;

import java.util.List;

public class ServiceMan {

    String userName, Email;
    int load = 0;

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    List<String> pendingRequest, completedRequest, pendingComplaint, completedComplaint;

    public ServiceMan(){}

    public ServiceMan(String userName, String email, List<String> pendingRequest, List<String> completedRequest, List<String> pendingComplaint, List<String> completedComplaint) {
        this.userName = userName;
        Email = email;
        this.pendingRequest = pendingRequest;
        this.completedRequest = completedRequest;
        this.pendingComplaint = pendingComplaint;
        this.completedComplaint = completedComplaint;
    }

    public List<String> getPendingRequest() {
        return pendingRequest;
    }

    public List<String> getCompletedRequest() {
        return completedRequest;
    }

    public List<String> getPendingComplaint() {
        return pendingComplaint;
    }

    public List<String> getCompletedComplaint() {
        return completedComplaint;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setPendingRequest(List<String> pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public void setCompletedRequest(List<String> completedRequest) {
        this.completedRequest = completedRequest;
    }

    public void setPendingComplaint(List<String> pendingComplaint) {
        this.pendingComplaint = pendingComplaint;
    }

    public void setCompletedComplaint(List<String> completedComplaint) {
        this.completedComplaint = completedComplaint;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImageURL() {return  "https://firebasestorage.googleapis.com/v0/b/sihinventory.appspot.com/o/7.jpg?alt=media&token=32c913d6-f42c-408b-ac7f-dd22e08c018a";}
}
