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

        //���α׷��� �ٸ� ���� �ʱⰪ���� ����
        myProgress.setProgress(vol);

    }

    //���ε� ��ư�� Ŭ���ϸ� ���񽺿� ���ε��ϵ��� ����
    public void bind(View view){
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    //������ ��ư�� Ŭ���ϸ� ������ ������ �޼��� ȣ��
    public void volumeUp(View view){
        if (myBinder != null) {
            vol = ((MyService.MyInner) myBinder).vol_up();
            myProgress.setProgress(vol);
        } else {
            Toast.makeText(getApplicationContext(), "bind first", Toast.LENGTH_SHORT).show();
        }

    }

    //�����ٿ� ��ư�� Ŭ���ϸ� ������ �����ٿ� �޼��� ȣ��
    public void volumeDown(View view){
        if (myBinder != null) {
            vol = ((MyService.MyInner) myBinder).vol_down();
            myProgress.setProgress(vol);
        } else {
            Toast.makeText(getApplicationContext(), "bind first", Toast.LENGTH_SHORT).show();
        }

    }

    //����ε� ��ư�� Ŭ���ϸ� ���񽺿� ����ε��ϵ��� ����
    public void unbind(View view){
        unbindService(sc);
        myBinder = null;
    }

    //ServiceConnection ��ü ����. ServiceConnection�� �������̽��� ���ø����̼� ��
    //���� ���¸� ����͸�
    public ServiceConnection sc = new ServiceConnection() {
        //���񽺿� Ŀ�ؼ��� �����Ǿ����� ȣ��
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            myBinder = arg1;
        }

        //Ŀ�ؼ��� �սǵ����� ȣ��. ���� ���񽺰� ���Ե� ���μ����� ���������� ��
        //���Ѵ�.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            myBinder = null;
        }
    };

}
