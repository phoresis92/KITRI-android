package com.example.usr.app10_2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){
        Intent intent=new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }

    public void bind(View view){
        Intent intent=new Intent(getApplicationContext(), MyService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);

    }

    public void unbind(View view){
        unbindService (sc);
    }

    public void stop(View view){
        Intent intent=new Intent(getApplicationContext(), MyService.class);
        stopService(intent);

    }

    //ServiceConnection 객체 생성. ServiceConnection는 인터페이스로 어플리케이션
    //서비스의 상태를 모니터링
    public ServiceConnection sc=new ServiceConnection(){

        //서비스에 커넥션이 수립되었을때 호출
        //param : 연결된 서비스 컴포넌트 이름, 서비스와 통신할 채널인 IBinder 객체
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {

            //두 번째 파라메터인 IBinder 객체를 원래 타입인 Service의 Inner 클래스
            //로 다운캐스팅하여 getNum()메서드 호출
            num=((MyService.MyInner)arg1).getNum();
            Toast.makeText(getApplicationContext(), "Activity, num:"+num,
                    Toast.LENGTH_SHORT).show();
        }

        //커넥션이 손실됐을때 호출.
        //param : 커넥션이 손실된 서비스 컴포넌트의 이름
        @Override
        public void onServiceDisconnected(ComponentName name) { }

    };

}
