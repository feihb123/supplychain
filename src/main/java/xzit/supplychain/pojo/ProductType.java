package xzit.supplychain.pojo;

public class ProductType {
    private int typeId;
    private String typeName;
    private String companyName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
