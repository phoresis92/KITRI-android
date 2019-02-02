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
            // ���� IP ��ü ����
            InetAddress serverAddr = InetAddress.getByName("192.168.56.1");

            // ������ ����� Socket ��ü ����. �Ķ���� : ����IP, port
            Socket socket = new Socket(serverAddr, 1234);

            // ������ ������ ����� ���� ��Ʈ���� ������ ���� ��´�(�����).
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));

            // ������ ������ ����� ���� ��Ʈ���� ������ ���� ��´�(�б��).
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            while (true) {
                // flag�� true�̸�
                if (flag) {

                    // ����� ��Ʈ���� ��Ʈ�� ���� ���
                    out.write(str + "\n");

                    // �������
                    out.flush();

                    // �б�� ��Ʈ������ �Ѷ��� ����
                    String read_str = in.readLine();

                    // Ǯ���� �޽��� ��ü �ϳ��� ������
                    Message msg1 = Message.obtain();

                    // �޽����� ���� ����
                    msg1.what = 0;

                    // �޽����� ��ü�� ����� �����Ϸ��� ��Ʈ�� ����
                    msg1.obj = read_str;

                    // ��Ƽ��Ƽ�� �ڵ鷯�� �޽��� ����
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

        // �޽����� ���޹����� ȣ��
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // Message Ŭ������ ��� ���� what ���� �����ΰ��� ���� ó��
            // what�� �޽����� ������ �����ϴµ� ���
            if (msg.what == 0) {

                // �޽��� ��ü�� obj ��� ���� ��Ʈ������ �ٿ� ĳ������ ����
                str = (String) msg.obj;

                // flag�� false�̸� true�� ����
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
