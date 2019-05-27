package xzit.supplychain.pojo;


import java.sql.Date;
import java.sql.Timestamp;

public class WPOrder {
    private int orderId;
    private int wholesalerId;
    private int producerId;
    private Timestamp time;
    private String productId;
    private int amount;
    private double price;
    private String address;
    private int status;
    private String meaning;
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "WPOrder{" +
                "orderId=" + orderId +
                ", wholesalerId=" + wholesalerId +
                ", producerId=" + producerId +
                ", time=" + time +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getWholesalerId() {
        return wholesalerId;
    }

    public void setWholesalerId(int wholesalerId) {
        this.wholesalerId = wholesalerId;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
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
}
