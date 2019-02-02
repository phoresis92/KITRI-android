package com.example.usr.app10_4;

import android.os.Handler;
import android.os.Message;

public class MyThread extends Thread{
    private int cnt;
    private boolean flag;
    private Handler h;
    private Message msg;

    public MyThread(Handler h) {
        cnt=0;
        flag=true;
        this.h = h;
    }

    @Override
    public void run() {
        while(flag){
            //메시지 객체 생성
            msg=new Message();
            cnt++;
            msg.arg1=cnt;

            if(cnt%2==0){
                //메시지의 종류 지정
                msg.what=0;
            }else{
                //메시지의 종류 지정
                msg.what=1;
            }

            //메시지 큐 끝에 메시지 추가
            h.sendMessage(msg);
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){}
        }
    }
    public int getCnt() {
        return cnt;
    }
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
