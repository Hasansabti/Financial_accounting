package tech.sabtih.financial_accounting.models;


public class User {

    String uid;
    String name,phone;

    public User(String uid, String name, String phone) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
