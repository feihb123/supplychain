package xzit.supplychain.pojo;

public class WholeSaler {

    private int id;
    private String userName;
    private String wholesalerId;
    private String companyName;
    private String photo;
    private String location;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", wholesalerId='" + wholesalerId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", photo='" + photo + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getWholesalerId() { return wholesalerId; }

    public void setWholesalerId(String wholesalerId) { this.wholesalerId = wholesalerId; }
}