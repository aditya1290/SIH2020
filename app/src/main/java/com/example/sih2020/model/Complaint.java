package com.example.sih2020.model;

import java.util.Date;

public class Complaint {
        String complaintGenerator;
        String complaintAllocatedTo;
        String complaintMachineId;
        Date complaintGeneratedDate;
        Date complaintCompletedDate;
        int status;
        final int generatedOnly = 1;
        final int generatedAndAccpted = 2;
        final int updateRequest = 3;
        final int RequestApproved = 4;
        final int complaintFinished = 5;
        String complaintDescription;

        public Complaint(){}

    public Complaint(String complaintGenerator, String complaintAllocatedTo, String complaintMachineId, Date complaintGeneratedDate, Date complaintCompletedDate, int status, String complaintDescription) {
        this.complaintGenerator = complaintGenerator;
        this.complaintAllocatedTo = complaintAllocatedTo;
        this.complaintMachineId = complaintMachineId;
        this.complaintGeneratedDate = complaintGeneratedDate;
        this.complaintCompletedDate = complaintCompletedDate;
        this.status = status;
        this.complaintDescription = complaintDescription;
    }

    public String getComplaintGenerator() {
        return complaintGenerator;
    }

    public String getComplaintAllocatedTo() {
        return complaintAllocatedTo;
    }

    public String getComplaintMachineId() {
        return complaintMachineId;
    }

    public Date getComplaintGeneratedDate() {
        return complaintGeneratedDate;
    }

    public Date getComplaintCompletedDate() {
        return complaintCompletedDate;
    }

    public int getStatus() {
        return status;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintGenerator(String complaintGenerator) {
        this.complaintGenerator = complaintGenerator;
    }

    public void setComplaintAllocatedTo(String complaintAllocatedTo) {
        this.complaintAllocatedTo = complaintAllocatedTo;
    }

    public void setComplaintMachineId(String complaintMachineId) {
        this.complaintMachineId = complaintMachineId;
    }

    public void setComplaintGeneratedDate(Date complaintGeneratedDate) {
        this.complaintGeneratedDate = complaintGeneratedDate;
    }

    public void setComplaintCompletedDate(Date complaintCompletedDate) {
        this.complaintCompletedDate = complaintCompletedDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
