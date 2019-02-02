package com.example.usr.app10_4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private MyThread m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //핸들러 객체 생성
        Handler myHandler=new Handler(){
            //메시지를 전달받으면 호출
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                //Message 클래스의 멤버 변수 what 값이 무엇인가에 따라 처리
                //what은 메시지의 종류를 지정하는데 사용
                if(msg.what==0){
                    Toast.makeText(getApplicationContext(), "짝수 : " +
                            msg.arg1, Toast.LENGTH_SHORT).show();
                }else if(msg.what==1){
                    Toast.makeText(getApplicationContext(), "홀수 : "+
                            msg.arg1, Toast.LENGTH_SHORT).show();
                }
            }
        };

        //thread 객체 생성. 파라메터로 Handler 객체 전달
        m=new MyThread(myHandler);
        
        //thread 시작
        m.start();

    }

    //버튼 클릭하면 thread 종료
    public void stop(View view){
        m.setFlag(false);
    }
}
