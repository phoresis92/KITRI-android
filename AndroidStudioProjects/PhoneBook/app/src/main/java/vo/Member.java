package vo;

import java.io.Serializable;

public class Member implements Serializable {

    private int _id;
    private String name;
    private String tel;
    private int img_res;

    public Member() {
    }

    public Member(int _id,String name, String tel, int img_res) {
        this._id = _id;
        this.name = name;
        this.tel = tel;
        this.img_res = img_res;
    }

    public Member(String name, String tel, int img_res) {
        this.name = name;
        this.tel = tel;
        this.img_res = img_res;
    }

    @Override
    public String toString() {
        return name +" : "+  tel +" : "+ img_res ;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
