package xzit.supplychain.pojo;

public class Product {
    private String productId;
    private String companyName;
    private String productName;
    private String type;

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

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
