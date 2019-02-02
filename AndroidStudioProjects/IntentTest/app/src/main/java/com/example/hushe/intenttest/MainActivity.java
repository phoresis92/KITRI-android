package com.example.hushe.intenttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import vo.Member;

public class MainActivity extends AppCompatActivity {

    private MyReceiver2 mr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //브로드캐스트 리시버2 등록
        mr2 = new MyReceiver2(this);
        //메니페스트 파일에 리시버를 등록하는 것과 동일. //param1:리시버객체 , param2:인텐트 필터객체
        registerReceiver(mr2, new IntentFilter("myAction"));
    }

    public void onStart(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        //intent.putExtra("name", "aaa");
        //intent.putExtra("age", 23);
        Member m = new Member("bbb", 12);
        intent.putExtra("m", m);
        startActivity(intent);
    }

    public void onResult(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        Member m = new Member("두번째", 55);
        intent.putExtra("m", m);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:
                    Member m = (Member) data.getSerializableExtra("m");
                    Toast.makeText(this, m.getName()+":"+m.getAge(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }// if end

    }// onActivityResult end


    public void onRec1(View v){
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("x", 10);//다른 프로그램에서 실행시 DTO는 동일 패키지여야한다
        intent.putExtra("y", 20);
        sendBroadcast(intent);
    }

    public void onRec2(View v){
        Intent intent = new Intent("myAction");
        sendBroadcast(intent);
    }

    class MyReceiver2 extends BroadcastReceiver {

        private Activity a;
        public MyReceiver2(Activity a){
            this.a = a;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "mr2 생성", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(a);
            builder.setMessage("리시버 객체 직접 생성").setPositiveButton("확인", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }


    public void onBtn5(View v){
        Intent intent = new Intent("com.example2.hushe.myapplication.myAction2");
        sendBroadcast(intent);
    }

}
