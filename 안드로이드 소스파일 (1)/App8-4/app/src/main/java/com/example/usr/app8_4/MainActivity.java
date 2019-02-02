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

        //notification(알림)을 발생하고 관리하는 매니저 객체 생성
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //notification(알림)애 대한 설정이 가능한 빌더 객체 생성
        mBuilder =
                new Notification.Builder(this)
                        //알림 발생시 상태바에 띄울 아이콘 설정
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //알림 타이틀 설정
                        .setContentTitle("My notification")
                        //알림 내용 설정
                        .setContentText("Hello World!");

        //확장뷰를 클릭했을때 이동할 액티비티를 활성화할 인텐트
        Intent resultIntent = new Intent(this, MainActivity.class);

        //확장 메시지를 사용자가 클릭했을 때 시작할 액티비티를 제어할 PendingIntent 객체 생성
        //param1:PendingIntent가 시작시켜야 할 액티비티의 Context
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
                0,  //param2:sender를 위한 요청 코드(현재 사용 안됨)
                resultIntent,  //param3:시작될 액티비티의 Intent
                //param4:플래그
                PendingIntent.FLAG_UPDATE_CURRENT);

        //생성한 PendingIntent 설정
        mBuilder.setContentIntent(resultPendingIntent);

        //사용자가 알림 확인하면 초기화
        mBuilder.setAutoCancel(true);

    }

    //버튼 클릭 시 호출
    public void notify(View view){

        //알림 횟수 설정
        mBuilder.setNumber(++number);

        //알림 발생
        mNotificationManager.notify(1, mBuilder.build());

    }

}
