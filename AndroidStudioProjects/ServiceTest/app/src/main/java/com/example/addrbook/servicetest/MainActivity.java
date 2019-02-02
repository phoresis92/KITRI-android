package com.example.addrbook.servicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private MyService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.progressBar2);

    }

    //start service
    public void onBtn1(View v){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    //bind service
    public void onBtn2(View v){
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    //unbind service
    public void onBtn3(View v){
        if(binder==null){
            Toast.makeText(this, "바인드 먼저", Toast.LENGTH_SHORT).show();
            return;
        }
        unbindService(sc);
        binder = null;

    }

    //stop service
    public void onBtn4(View v){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);;
    }
    //=================================================================================================


    //커넥션 객체 생성
    private ServiceConnection sc = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(getApplicationContext(), "바인드 성공", Toast.LENGTH_SHORT).show();
            System.out.println("바인드 성공");
//            System.out.println("activity에서 num: "+((MyService.MyBinder)service).getNum());//down casting
//            System.out.println("activity에서 num 150으로 변경");
//            ((MyService.MyBinder)service).setNum(150);
//            System.out.println("activity에서 num: "+((MyService.MyBinder)service).getNum());

            binder = (MyService.MyBinder)service;
            pb.setProgress(binder.getNum());

            //pb.setProgress(((MyService.MyBinder)service).getNum());

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(), "바인드 실패", Toast.LENGTH_SHORT).show();
            System.out.println("바인드 실패");
        }
    };

    //=================================================================================================

    public void onUp(View v){
        /*Intent intent = new Intent(this, MyService.class);
        bindService(intent,sc,Context.BIND_AUTO_CREATE);*/
        if(binder == null){
            Toast.makeText(this, "바인드 먼저", Toast.LENGTH_SHORT).show();
            return;
        }
        pb.setProgress(binder.volUp());

    }

    public void onDown(View v){
        /*Intent intent = new Intent(this, MyService.class);
        bindService(intent,sc,Context.BIND_AUTO_CREATE);*/
        if(binder == null){
            Toast.makeText(this, "바인드 먼저", Toast.LENGTH_SHORT).show();
            return;
        }
        pb.setProgress(binder.volDn());

    }

}
