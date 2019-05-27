package xzit.supplychain.pojo;

import java.sql.Timestamp;

public class Appointment {
    private int aid;
    private Timestamp time;
    private String photo;
    private String shopname;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getShopname() {
        return shopname;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "time=" + time +
                ", photo='" + photo + '\'' +
                ", shopname='" + shopname + '\'' +
                '}';
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
