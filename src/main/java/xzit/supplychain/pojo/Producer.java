package xzit.supplychain.pojo;

public class Producer {

    private int id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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
                ", companyName='" + companyName + '\'' +
                ", photo='" + photo + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
