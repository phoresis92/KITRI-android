package com.example.usr.app11_4;

/**
 * Created by usr on 2015-08-16.
 */
public class Member {
    private int id;
    private String name;

    public Member(){}
    public Member(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
