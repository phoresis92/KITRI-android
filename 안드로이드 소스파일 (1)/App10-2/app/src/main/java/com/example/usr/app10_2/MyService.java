package com.example.usr.app10_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private int num=0;

    //Binder 클래스를 상속받은 inner 클래스의 객체. 이 클래스의 구현은 아래에 있다
    private Binder m=new MyInner();

    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(getApplicationContext(), "onStartCommand()", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    //클라이언트가 바인드하면 호출되는 메서드. Binder를 상속받은 클래스 객체를 반환
    @Override
    public IBinder onBind(Intent intent) {
        num++;
        Toast.makeText(getApplicationContext(), "onBind()", Toast.LENGTH_SHORT).show();
        return m;

    }

    //한 클라이언트가 unbind하고 새 클라이언트가 bind를 요청하면 호출된다.
    //클라이언트 하나로 실행하면 호출되지 않는다.
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(getApplicationContext(), "onRebind()", Toast.LENGTH_SHORT).show();
    }

    //클라이언트가 바인드를 끊기 위해 unbindService() 메서드를 호출하면 호출되는
    //메서드
    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        Toast.makeText(getApplicationContext(), "onUnbind()", Toast.LENGTH_SHORT).show();
        return false;
    }

    //Binder를 상속받는 inner 클래스로 클라이언트가 이 클래스의 객체를 전달받아 이
    // 클래스의 멤버 메서드를 호출할 수 있다. 이는 클라이언트와 서비스 사이의 기능
    // 요청과 응답의 매커니즘이다.
    class MyInner extends Binder{
        public int getNum(){
            Toast.makeText(getApplicationContext(), "inner class methode, num:"+num, Toast.LENGTH_SHORT).show();
            return num;
        }
    }

}
