package com.example.usr.app10_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private int num=0;

    public MyService() {
    }

    //Service �Ļ� Ŭ������ onBind()�޼��带 �� �������ؾ� ��ü�� ������ �� �ִ�
    //�� �ڵ忡���� ���ε� ��尡 �ƴϹǷ� �� �޼��带 ����� ���� ����
    //null�� ��ȯ�ϴ� �ڵ常 �Է�
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //���񽺰� �����Ǹ� ȣ��
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
    }

    //���񽺰� ���۵Ǹ� ȣ��
    //param : ����Ʈ, ���� ��û flag, ��û id
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

    //���񽺰� ���ŵǱ� �� ȣ��
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
