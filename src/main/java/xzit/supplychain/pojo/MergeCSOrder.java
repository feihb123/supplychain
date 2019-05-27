package xzit.supplychain.pojo;

import java.sql.Timestamp;

public class MergeCSOrder {
    String productId;
    String amount;

    String address;
    String tel;

    @Override
    public String toString() {
        return "MergeCSOrder{" +
                "productId='" + productId + '\'' +
                ", amount='" + amount + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
