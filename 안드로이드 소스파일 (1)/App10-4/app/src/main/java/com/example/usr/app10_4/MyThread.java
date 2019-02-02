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
            //�޽��� ��ü ����
            msg=new Message();
            cnt++;
            msg.arg1=cnt;

            if(cnt%2==0){
                //�޽����� ���� ����
                msg.what=0;
            }else{
                //�޽����� ���� ����
                msg.what=1;
            }

            //�޽��� ť ���� �޽��� �߰�
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
