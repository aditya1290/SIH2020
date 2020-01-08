package com.example.sih2020;

import android.net.Uri;

import java.net.URI;
import java.util.Date;
import java.util.List;

public class Machine {
    String serialNumber;
    Date installationDate;
    String department;
    int serviceTime;
   // Uri QRImage;
    String link;
    List<PastRecord> pastRecordList;

    public Machine()
    {

    public void setLink(String link) {
        this.link = link;
    }

    public Machine(String serialNumber, Date installationDate, String department, int serviceTime, String link, List<PastRecord> pastRecordList) {
        this.serialNumber = serialNumber;
        this.installationDate = installationDate;
        this.department = department;
        this.serviceTime = serviceTime;
        this.link = link;
        this.pastRecordList = pastRecordList;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<PastRecord> getPastRecordList() {
        return pastRecordList;
    }

    public void setPastRecordList(List<PastRecord> pastRecordList) {
        this.pastRecordList = pastRecordList;
    }
}
