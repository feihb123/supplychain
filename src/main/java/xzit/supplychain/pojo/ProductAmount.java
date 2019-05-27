package xzit.supplychain.pojo;

public class ProductAmount {


    private String productId;
    private String companyName;
    private String productName;
    private String type;
    private String photo;
    private int amount;
    private double price;
    private int salerId;

    public int getSalerId() {
        return salerId;
    }

    public void setSalerId(int salerId) {
        this.salerId = salerId;
    }
    @Override
    public String toString() {
        return "ProductAmount{" +
                "productId='" + productId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", photo='" + photo + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
