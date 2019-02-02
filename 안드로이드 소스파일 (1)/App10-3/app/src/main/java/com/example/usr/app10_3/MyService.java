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

    //Ŭ���̾�Ʈ�� ���ε��ϸ� ȣ��Ǵ� �޼���. Binder�� ��ӹ��� Ŭ���� ��ü�� ��ȯ//�Ѵ�.
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "onBind()", Toast.LENGTH_SHORT).show();
        return m;

    }

    //Ŭ���̾�Ʈ�� ���ε带 ���� ���� unbindService()�޼��带 ȣ���ϸ� ȣ��Ǵ� ��//����
    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        Toast.makeText(getApplicationContext(), "onUnbind()", Toast.LENGTH_SHORT).show();
        return false;
    }

    //Binder�� ��ӹ޴� inner Ŭ������ Ŭ���̾�Ʈ�� �� Ŭ������ ��ü�� ���޹޾� �� //Ŭ������ ��� �޼��带 ȣ���� �� �ִ�. �̴� Ŭ���̾�Ʈ�� ���� ������ ��� //��û�� ������ ��Ŀ�����̴�.
    class MyInner extends Binder{
        //������ �����ϴ� �޼���. ������ �� �������� ��ȯ
        public int vol_up(){
            volume+=10;
            if(volume>=100)
                volume=100;
            Toast.makeText(getApplicationContext(), "volume up", Toast.LENGTH_SHORT).show();
            return volume;
        }

        //������ �����ϴ� �޼���. ���ҵ� �� �������� ��ȯ
        public int vol_down(){
            volume-=10;
            if(volume<0)
                volume=0;
            Toast.makeText(getApplicationContext(), "volume down", Toast.LENGTH_SHORT).show();
            return volume;
        }
    }

}
