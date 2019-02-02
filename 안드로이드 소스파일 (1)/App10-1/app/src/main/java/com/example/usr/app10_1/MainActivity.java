package com.example.usr.app10_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //start버튼에 클릭 시 호출
    public void start(View view){
        //서비스 활성화에 사용할 인텐트 생성
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        //서비스 시작
        startService(intent);

    }

    //stop버튼에 클릭 시 호출
    public void stop(View view){
        //서비스 종료에 사용할 인텐트 생성
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        //서비스 종료
        stopService(intent);

    }
}
