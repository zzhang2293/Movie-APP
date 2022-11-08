package com.bean;

/**
 * the parent class of customer and business
 */
public class User {
    private String loginName; // cannot repeat
    private String userName;  // real name
    private String passWord;
    private String gender;
    private String phone;
    private double money;

    public User(String loginName, String userName, String gender, String phone, double money) {
        this.loginName = loginName;
        this.userName = userName;
        this.gender = gender;
        this.phone = phone;
        this.money = money;
    }
    public User(){

    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money += money;
    }
}
