package com.example.usr.app10_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private int num=0;

    //Binder Ŭ������ ��ӹ��� inner Ŭ������ ��ü. �� Ŭ������ ������ �Ʒ��� �ִ�
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

    //Ŭ���̾�Ʈ�� ���ε��ϸ� ȣ��Ǵ� �޼���. Binder�� ��ӹ��� Ŭ���� ��ü�� ��ȯ
    @Override
    public IBinder onBind(Intent intent) {
        num++;
        Toast.makeText(getApplicationContext(), "onBind()", Toast.LENGTH_SHORT).show();
        return m;

    }

    //�� Ŭ���̾�Ʈ�� unbind�ϰ� �� Ŭ���̾�Ʈ�� bind�� ��û�ϸ� ȣ��ȴ�.
    //Ŭ���̾�Ʈ �ϳ��� �����ϸ� ȣ����� �ʴ´�.
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(getApplicationContext(), "onRebind()", Toast.LENGTH_SHORT).show();
    }

    //Ŭ���̾�Ʈ�� ���ε带 ���� ���� unbindService() �޼��带 ȣ���ϸ� ȣ��Ǵ�
    //�޼���
    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        Toast.makeText(getApplicationContext(), "onUnbind()", Toast.LENGTH_SHORT).show();
        return false;
    }

    //Binder�� ��ӹ޴� inner Ŭ������ Ŭ���̾�Ʈ�� �� Ŭ������ ��ü�� ���޹޾� ��
    // Ŭ������ ��� �޼��带 ȣ���� �� �ִ�. �̴� Ŭ���̾�Ʈ�� ���� ������ ���
    // ��û�� ������ ��Ŀ�����̴�.
    class MyInner extends Binder{
        public int getNum(){
            Toast.makeText(getApplicationContext(), "inner class methode, num:"+num, Toast.LENGTH_SHORT).show();
            return num;
        }
    }

}
