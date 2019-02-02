package com.example.addrbook.servicetest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private int num;

    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        System.out.println("onCreate()");
        num = 50;
    }

    class MyBinder extends Binder{
        int getNum(){
            return num;
        }
        void setNum(int n){
            num = n;
       }

       int volUp(){
            num+=10;
            if(num>100){
                num = 100;
            }
            return num;
       }

       int volDn(){
            num-=10;
            if(num <0){
                num=0;
            }
            return num;
       }

    }
    private MyBinder mb = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();
        System.out.println("onBind()");
        return mb;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand()", Toast.LENGTH_SHORT).show();
        for(int i = 0 ; i < 10 ; i++){
            num++;
            Toast.makeText(this, "num: "+num, Toast.LENGTH_SHORT).show();
            System.out.println("num: "+num);
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind()", Toast.LENGTH_SHORT).show();
        System.out.println("onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(this, "onRebind()", Toast.LENGTH_SHORT).show();
        System.out.println("onRebind()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
        System.out.println("onDestroy()");

    }



}
