package com.example.usr.app10_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private int num=0;

    public MyService() {
    }

    //Service 파생 클래스는 onBind()메서드를 꼭 재정의해야 객체를 생성할 수 있다
    //이 코드에서는 바인드 모드가 아니므로 이 메서드를 사용할 일이 없다
    //null을 반환하는 코드만 입력
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //서비스가 생성되면 호출
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
    }

    //서비스가 시작되면 호출
    //param : 인텐트, 시작 요청 flag, 요청 id
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        int i;
        for(i=0;i<100;i++){
            num++;
            if(num%10==0){
                Toast.makeText(getApplicationContext(), "onStartCommand()"+num, Toast.LENGTH_SHORT).show();
            }
        }
        return START_STICKY;

    }

    //서비스가 제거되기 전 호출
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
