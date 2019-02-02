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

    //ServiceConnection ��ü ����. ServiceConnection�� �������̽��� ���ø����̼�
    //������ ���¸� ����͸�
    public ServiceConnection sc=new ServiceConnection(){

        //���񽺿� Ŀ�ؼ��� �����Ǿ����� ȣ��
        //param : ����� ���� ������Ʈ �̸�, ���񽺿� ����� ä���� IBinder ��ü
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {

            //�� ��° �Ķ������ IBinder ��ü�� ���� Ÿ���� Service�� Inner Ŭ����
            //�� �ٿ�ĳ�����Ͽ� getNum()�޼��� ȣ��
            num=((MyService.MyInner)arg1).getNum();
            Toast.makeText(getApplicationContext(), "Activity, num:"+num,
                    Toast.LENGTH_SHORT).show();
        }

        //Ŀ�ؼ��� �սǵ����� ȣ��.
        //param : Ŀ�ؼ��� �սǵ� ���� ������Ʈ�� �̸�
        @Override
        public void onServiceDisconnected(ComponentName name) { }

    };

}
