package com.example.usr.app8_4;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //notification(�˸�)�� �߻��ϰ� �����ϴ� �Ŵ��� ��ü ����
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //notification(�˸�)�� ���� ������ ������ ���� ��ü ����
        mBuilder =
                new Notification.Builder(this)
                        //�˸� �߻��� ���¹ٿ� ��� ������ ����
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //�˸� Ÿ��Ʋ ����
                        .setContentTitle("My notification")
                        //�˸� ���� ����
                        .setContentText("Hello World!");

        //Ȯ��並 Ŭ�������� �̵��� ��Ƽ��Ƽ�� Ȱ��ȭ�� ����Ʈ
        Intent resultIntent = new Intent(this, MainActivity.class);

        //Ȯ�� �޽����� ����ڰ� Ŭ������ �� ������ ��Ƽ��Ƽ�� ������ PendingIntent ��ü ����
        //param1:PendingIntent�� ���۽��Ѿ� �� ��Ƽ��Ƽ�� Context
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
                0,  //param2:sender�� ���� ��û �ڵ�(���� ��� �ȵ�)
                resultIntent,  //param3:���۵� ��Ƽ��Ƽ�� Intent
                //param4:�÷���
                PendingIntent.FLAG_UPDATE_CURRENT);

        //������ PendingIntent ����
        mBuilder.setContentIntent(resultPendingIntent);

        //����ڰ� �˸� Ȯ���ϸ� �ʱ�ȭ
        mBuilder.setAutoCancel(true);

    }

    //��ư Ŭ�� �� ȣ��
    public void notify(View view){

        //�˸� Ƚ�� ����
        mBuilder.setNumber(++number);

        //�˸� �߻�
        mNotificationManager.notify(1, mBuilder.build());

    }

}
