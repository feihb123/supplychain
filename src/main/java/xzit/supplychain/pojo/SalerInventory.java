package xzit.supplychain.pojo;

public class SalerInventory {


    private String productId;
    private String companyName;
    private String productName;
    private String type;
    private int amount;
    private Double price;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) { this.productId = productId; }

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


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) { this.price = price;}

    @Override
    public String toString() {

        return "SalerInventory{" +
                "productId='" + productId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount + '\'' +
                ",price=" + price +
                '}';
    }
}




