package xzit.supplychain.pojo;

import java.security.Timestamp;

public class SalerSubscribe {
    private int  oddNumber;
    private String customerName;
    private String year;
    private String type;
    private int wagonNumber;
    private Timestamp appointmentTime;
    private String maintenance;
    private String phone;
    private String salerName;
    private int  salerId;
    private Timestamp registerTime;
    private String comment;
    private int statusId;



    @Override
    public String toString() {
        return "SalerSubscribe{" +
                "oddNumber='" + oddNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                "wagonNumber='" + wagonNumber + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                "maintenance='" + maintenance + '\'' +
                ", phone='" + phone + '\'' +
                "salerName='" + salerName + '\'' +
                ", salerId='" + salerId + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", statusId='" + statusId + '\'' +
                ", comment=" +comment +
                '}';
    }

    public int getOddNumber() {
        return oddNumber;
    }

    public void setOddNumber(int oddNumber) {
        this.oddNumber = oddNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public int getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(int wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSalerId() {
        return salerId;
    }

    public void setSalerId(int salerId) {
        this.salerId = salerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
