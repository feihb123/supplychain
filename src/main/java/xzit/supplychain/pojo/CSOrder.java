package xzit.supplychain.pojo;

import java.sql.Timestamp;

public class CSOrder {


    long orderId;
    int consumerId;
    int salerId;
    Timestamp time;
    String productId;
    int amount;
    double price;
    String address;
    int status;
    String tel;
    String meaning;
    String photo;
    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "CSOrder{" +
                "orderId=" + orderId +
                ", consumerId=" + consumerId +
                ", salerId=" + salerId +
                ", time=" + time +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", tel='" + tel + '\'' +
                '}';
    }

    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public int getSalerId() {
        return salerId;
    }

    public void setSalerId(int salerId) {
        this.salerId = salerId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
