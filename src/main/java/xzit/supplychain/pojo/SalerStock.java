package xzit.supplychain.pojo;

public class SalerStock {
    private String productId;

    private String wholesalerId;
    private String companyName;
    private String productName;
    private String type;
    private int amount;
    private double price;


    @Override
    public String toString() {
        return "SalerStock{" +
                "productId='" + productId + '\'' +
                "wholesalerId='" + wholesalerId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getWholesalerId() {
        return wholesalerId;
    }

    public void setWholesalerId(String wholesalerId) {
        this.wholesalerId = wholesalerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
