package xzit.supplychain.pojo;

public class Address {
    private int aid;
    private int consumerId;
    private String address;
    private int defaultAdd;
    private String tel;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDefaultAdd() {
        return defaultAdd;
    }

    public void setDefaultAdd(int defaultAdd) {
        this.defaultAdd = defaultAdd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
