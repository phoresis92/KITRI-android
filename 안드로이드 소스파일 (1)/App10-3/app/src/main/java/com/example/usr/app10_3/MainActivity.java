package com.example.usr.app10_3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ProgressBar myProgress;
    private int vol = 50;
    IBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myProgress = (ProgressBar) findViewById(R.id.progressBar);

        //프로그레스 바를 볼륨 초기값으로 설정
        myProgress.setProgress(vol);

    }

    //바인드 버튼을 클릭하면 서비스에 바인드하도록 구현
    public void bind(View view){
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    //볼륨업 버튼을 클릭하면 서비스의 볼륨업 메서드 호출
    public void volumeUp(View view){
        if (myBinder != null) {
            vol = ((MyService.MyInner) myBinder).vol_up();
            myProgress.setProgress(vol);
        } else {
            Toast.makeText(getApplicationContext(), "bind first", Toast.LENGTH_SHORT).show();
        }

    }

    //볼륨다운 버튼을 클릭하면 서비스의 볼륨다운 메서드 호출
    public void volumeDown(View view){
        if (myBinder != null) {
            vol = ((MyService.MyInner) myBinder).vol_down();
            myProgress.setProgress(vol);
        } else {
            Toast.makeText(getApplicationContext(), "bind first", Toast.LENGTH_SHORT).show();
        }

    }

    //언바인드 버튼을 클릭하면 서비스에 언바인드하도록 구현
    public void unbind(View view){
        unbindService(sc);
        myBinder = null;
    }

    //ServiceConnection 객체 생성. ServiceConnection는 인터페이스로 어플리케이션 서
    //비스의 상태를 모니터링
    public ServiceConnection sc = new ServiceConnection() {
        //서비스에 커넥션이 수립되었을때 호출
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            myBinder = arg1;
        }

        //커넥션이 손실됐을때 호출. 보통 서비스가 포함된 프로세스가 망가졌을때 발
        //생한다.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            myBinder = null;
        }
    };

}
