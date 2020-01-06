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
    Uri QRImage;
    String link;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    //List<PastRecord> pastRecordList;

    public Machine (){

    }

    public Machine(String serialNo, Date installationDate, String department, int serviceTime, Uri QRImage, List<PastRecord> pastRecordList) {
        this.serialNumber = serialNo;
        this.installationDate = installationDate;
        this.department = department;
        this.serviceTime = serviceTime;
        this.QRImage = QRImage;
      //  this.pastRecordList = pastRecordList;
    }

    public String getSerialNo() {
        return serialNumber;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public String getDepartment() {
        return department;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public Uri getQRImage() {
        return QRImage;
    }

//    public List<PastRecord> getPastRecordList() {
//        return pastRecordList;
//    }

    public void setSerialNo(String serialNo) {
        this.serialNumber = serialNo;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setQRImage(Uri QRImage) {
        this.QRImage = QRImage;
    }

    public void setPastRecordList(List<PastRecord> pastRecordList) {
//        this.pastRecordList = pastRecordList;
    }
}
