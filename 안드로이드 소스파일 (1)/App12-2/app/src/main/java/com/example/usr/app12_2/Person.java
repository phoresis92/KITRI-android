package com.example.usr.app12_2;

public class Person {
    private String id;
    private String label;
    private String tel;
    private int type;
    private String sType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Person(String id, String label, String tel, int type) {
        this.id = id;
        this.label = label;
        this.tel = tel;
        this.type = type;
        makeType();
    }

    public void makeType(){
        switch (type) {
            case 1:
                sType = "HOME";
                break;
            case 2:
                sType = "MOBILE";
                break;
            case 3:
                sType = "WORK";
                break;
            default:
                sType = "ETC";
        }
    }

    @Override
    public String toString() {
        return "label=" + label + ", tel=" + tel + ", (type=" + sType + ")";
    }


}
