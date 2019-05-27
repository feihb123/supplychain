package xzit.supplychain.pojo;


import java.sql.Timestamp;

public class SWOrder {
    private int orderId;
    private int salerId;
    private int wholesalerId;
    private Timestamp time;
    private String productId;
    private int amount;
    private double price;
    private String address;
    private int status;
    private String meaning;
    private double total;

    @Override
    public String toString() {
        return "SWOrder{" +
                "orderId=" + orderId +
                ", salerId=" + salerId +
                ", wholesalerId=" + wholesalerId +
                ", time=" + time +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", meaning='" + meaning + '\'' +
                ", total=" + total +
                '}';
    }

    public int getSalerId() {
        return salerId;
    }

    public void setSalerId(int salerId) {
        this.salerId = salerId;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
