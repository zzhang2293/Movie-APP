package com.bean;

public class Business extends User{
    // shop name
    private String shopName;
    // address
    private String address;

    public Business(String loginName, String userName, String gender, String phone, double money, String shopName, String address){
        super(loginName, userName, gender, phone, money);
        this.shopName = shopName;
        this.address = address;
    }
    public Business(){

    }
    public String getShopName() {
        return shopName;
    }

    public void setShopNamae(String shopNamae) {
        this.shopName = shopNamae;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void addMoney(double money){
        setMoney(money);

    }
}
