package com.example2.hushe.myapplication;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vo.Member;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter("com.example2.hushe.myapplication.myAction2"));
    }

    public void onBtn1(View v){
        //전화걸기 프로그램의 다이얼링 액티비티를 묵시적으로 활성화
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+12345));
        startActivity(intent);
    }

    public void onBtn2(View v){
        //묵시적으로 같은 프로그램 내에 있는 리시버를 활성화
        Intent intent = new Intent("com.example2.hushe.myapplication.myAction2");
        sendBroadcast(intent);
    }

    public void onBtn3(View v){
        //다른 앱의 액티비티를 묵시적으로 활성화
        //컴포넌트의 패키지명과 그 클래스의 풀네임을 갖는 객체
        ComponentName cn = new ComponentName("com.example.hushe.intenttest", "com.example.hushe.intenttest.Main3Activity");
        //인텐트에 묵시적 활성을 위한 액션, 데이터, 카테고리를 작성
        Intent intent = new Intent("com.example.hushe.intenttest.activity2");
        //인텐트에 활성화할 컴포넌트 정보 추가
        intent.setComponent(cn);
        startActivity(intent);
    }

    public void onBtn4(View v){
        ComponentName cn = new ComponentName("com.example.hushe.intenttest", "com.example.hushe.intenttest.Main2Activity");
        Intent intent = new Intent("com.example.hushe.intenttest.actionEdit");
        //인텐트에 전달할 객체 저장
        intent.putExtra("m", new Member("aaa",11));
        intent.setComponent(cn);
        startActivity(intent);
    }

}
