package com.example.addrbook.sms.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SMS {

    private String number;
    private String msg;
    private String date;
    private String time;

    public SMS() {
    }

    public SMS(String number, String msg) {
        this.number = number;
        this.msg = msg;


/*        SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일 hh시 mm분 ss초");
        this.date = sdf.format(new Date());*/

        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH)+1;
        int d = c.get(Calendar.DATE);
        int t = c.get(Calendar.HOUR);
        int mi = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);
        this.date = y+"년"+m+"월"+d+"일";
        this.time = t+"시"+mi+"분"+s+"초";

    }


    @Override
    public String toString() {
        String msgTogo;
        try{
            msgTogo = msg.substring(0,10);
        }catch (Exception e){
            msgTogo = msg;
        }
        return "from: " + number +", msg: " + msgTogo + ", time: "+date;
    }

    public String getTime() {
        return date+time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
