package com.example.usr.app13_4;

import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient extends Thread {
    private Handler activity_h;
    private boolean flag = false;
    private String str;

    public MyClient(Handler h) {
        this.activity_h = h;
    }

    @Override
    public void run() {

        try {
            // 서버 IP 객체 생성
            InetAddress serverAddr = InetAddress.getByName("192.168.56.1");

            // 서버와 통신할 Socket 객체 생성. 파라메터 : 서버IP, port
            Socket socket = new Socket(serverAddr, 1234);

            // 서버와 데이터 통신을 위한 스트림을 소켓을 통해 얻는다(쓰기용).
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));

            // 서버와 데이터 통신을 위한 스트림을 소켓을 통해 얻는다(읽기용).
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            while (true) {
                // flag가 true이면
                if (flag) {

                    // 쓰기용 스트림에 스트링 한줄 출력
                    out.write(str + "\n");

                    // 강제출력
                    out.flush();

                    // 읽기용 스트림에서 한라인 읽음
                    String read_str = in.readLine();

                    // 풀에서 메시지 객체 하나를 꺼내옴
                    Message msg1 = Message.obtain();

                    // 메시지의 종류 지정
                    msg1.what = 0;

                    // 메시지의 객체형 멤버에 전달하려는 스트링 저장
                    msg1.obj = read_str;

                    // 액티비티쪽 핸들러에 메시지 전달
                    activity_h.sendMessage(msg1);

                    flag = false;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Handler myHandler = new Handler() {

        // 메시지를 전달받으면 호출
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // Message 클래스의 멤버 변수 what 값이 무엇인가에 따라 처리
            // what은 메시지의 종류를 지정하는데 사용
            if (msg.what == 0) {

                // 메시지 객체의 obj 멤버 값을 스트링으로 다운 캐스팅해 저장
                str = (String) msg.obj;

                // flag가 false이면 true로 설정
                if (!flag)
                    flag = true;
            }
        }
    };

    public Handler getActivity_h() {
        return activity_h;
    }

    public void setActivity_h(Handler activity_h) {
        this.activity_h = activity_h;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Handler getMyHandler() {
        return myHandler;
    }

    public void setMyHandler(Handler myHandler) {
        this.myHandler = myHandler;
    }

}
