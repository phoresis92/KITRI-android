package com.example.usr.socket;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

class ReadServer extends Thread{

    private BufferedReader in;
    private boolean flag = true;

    public ReadServer(BufferedReader in){
        this.in = in;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        String str = "", str2 = "";
        while(flag){
            try {
                str = in.readLine();
                System.out.println("상대 메시지:"+str);
                Message msg= Message.obtain();
                msg.what=0;

                //메시지에 객체 데이터 저장
                msg.obj=str;

                //Handler에 메시지 전달
                MainActivity.getH().sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
public class MainActivity extends AppCompatActivity {
    private InetAddress serverAddr;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private EditText et;
    private TextView tv;
    private ReadServer rs;
    private static Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        et = (EditText)findViewById(R.id.editText3);
        tv = (TextView)findViewById(R.id.textView2);

        //루퍼 생성
//        Looper.prepare();
        //메시지 및 메시지 큐 사용가능
        //     Looper.loop();

        h=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==0){
                    String s= tv.getText().toString()+"\n"+(String)msg.obj;
                    tv.setText(s);
                }
            }
        };


        try {
            serverAddr = InetAddress.getByName("192.168.15.14");
            Log.d("TCP", "C: Connecting...");
            socket = new Socket(serverAddr, 4444);

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            rs = new ReadServer(in);
            rs.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Handler getH(){
        return h;
    }
    public void onSend(View view){
        String str = et.getText().toString();
        out.println(str+"\n");
        if(str.startsWith("exit")){
            rs.setFlag(false);
        }
    }


}
