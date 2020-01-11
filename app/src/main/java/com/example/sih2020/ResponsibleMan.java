package com.example.sih2020;

import java.util.List;

public class ResponsibleMan {

    String email, userName;
    List<String> pendingApprovalRequest;
    List<String> pendingComplaints;
    List<String> completedComplaints;


    public ResponsibleMan()
    {
    }

    public ResponsibleMan(String email, String userName, List<String> pendingApprovalRequest, List<String> pendingComplaints, List<String> completedComplaints) {
        this.email = email;
        this.userName = userName;
        this.pendingApprovalRequest = pendingApprovalRequest;
        this.pendingComplaints = pendingComplaints;
        this.completedComplaints = completedComplaints;
    }
    // -----------------------------------------------------------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // -----------------------------------------------------------------------------------------------

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    // -----------------------------------------------------------------------------------------------

    public List<String> getPendingApprovalRequest() {
        return pendingApprovalRequest;
    }

    public void setPendingApprovalRequest(List<String> pendingApprovalRequest) {
        this.pendingApprovalRequest = pendingApprovalRequest;
    }

    // -----------------------------------------------------------------------------------------------
    public List<String> getPendingComplaints() {
        return pendingComplaints;
    }

    public void setPendingComplaints(List<String> pendingComplaints) {
        this.pendingComplaints = pendingComplaints;
    }
    
    // -----------------------------------------------------------------------------------------------
    public List<String> getCompletedComplaints() {
        return completedComplaints;
    }

    public void setCompletedComplaints(List<String> completedComplaints) {
        this.completedComplaints = completedComplaints;
    }
    // -----------------------------------------------------------------------------------------------
}
