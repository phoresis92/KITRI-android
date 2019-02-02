package com.example.usr.app10_3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private int volume=50;
    private Binder m=new MyInner();

    public MyService() {
    }

    //클라이언트가 바인드하면 호출되는 메서드. Binder를 상속받은 클래스 객체를 반환//한다.
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "onBind()", Toast.LENGTH_SHORT).show();
        return m;

    }

    //클라이언트가 바인드를 끊기 위해 unbindService()메서드를 호출하면 호출되는 메//서드
    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        Toast.makeText(getApplicationContext(), "onUnbind()", Toast.LENGTH_SHORT).show();
        return false;
    }

    //Binder를 상속받는 inner 클래스로 클라이언트가 이 클래스의 객체를 전달받아 이 //클래스의 멤버 메서드를 호출할 수 있다. 이는 클라이언트와 서비스 사이의 기능 //요청과 응답의 매커니즘이다.
    class MyInner extends Binder{
        //볼륨을 증가하는 메서드. 증가된 뒤 볼륨값을 반환
        public int vol_up(){
            volume+=10;
            if(volume>=100)
                volume=100;
            Toast.makeText(getApplicationContext(), "volume up", Toast.LENGTH_SHORT).show();
            return volume;
        }

        //볼륨을 감소하는 메서드. 감소된 뒤 볼륨값을 반환
        public int vol_down(){
            volume-=10;
            if(volume<0)
                volume=0;
            Toast.makeText(getApplicationContext(), "volume down", Toast.LENGTH_SHORT).show();
            return volume;
        }
    }

}
