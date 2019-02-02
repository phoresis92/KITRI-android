package com.example.usr.app10_5;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by usr on 2015-08-15.
 */
public class MyThread extends Thread {
    private Context ctx;
    public MyThread(Context ctx) {
        this.ctx=ctx;
    }
    @Override
    public void run() {
        //루퍼 생성
        Looper.prepare();
        //메시지 및 메시지 큐 사용가능
        Looper.loop();
    }
    private Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                //전달받은 메시지를 토스트로 출력
                Toast.makeText(ctx, "message from activity : "+(String)msg.obj,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    public Handler getH() {
        return h;
    }
    public void setH(Handler h) {
        this.h = h;
    }

}
