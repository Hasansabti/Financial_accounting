package tech.sabtih.financial_accounting.models;

public class Contract {
    String userid, username, phone, date;
    int trans_count;

    public Contract(String userid, String username, String phone, String date, int trans_count) {
        this.userid = userid;
        this.username = username;
        this.phone = phone;
        this.date = date;
        this.trans_count = trans_count;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTrans_count() {
        return trans_count;
    }

    public void setTrans_count(int trans_count) {
        this.trans_count = trans_count;
    }
}
