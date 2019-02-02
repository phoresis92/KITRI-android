package com.example.hushe.myapp;

import java.io.Serializable;

public class Member implements Serializable {

    private String name;
    private String tel;
    private String address;
    private int img_res;

    public Member() {
    }

    public Member(String name, String tel, String address, int img_res) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.img_res = img_res;
    }

    public Member(String name, String tel, String address) {
        this.name = name;

        this.tel = tel;
        this.address = address;
    }

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int imp_res) {
        this.img_res = imp_res;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " / " +tel + " / " +address ;
    }
}
