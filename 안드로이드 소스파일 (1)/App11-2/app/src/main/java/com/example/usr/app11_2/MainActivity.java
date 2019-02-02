package com.example.usr.app11_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends Activity {
    private FileOutputStream fos;
    private FileInputStream fis;
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);

    }

    //���� ��ư Ŭ�� �̺�Ʈ ó��
    public void save(View view){
        try {
            //test.txt ������ �̾�� ���� ����
            fos = openFileOutput("test.txt", MODE_APPEND);

            //����Ʈ �ؽ�Ʈ�� ����ڰ� �Է��� ���ڿ� �о�, ���Ͽ� ����
            fos.write(et.getText().toString().getBytes());

            //���ϴݱ�
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //�б� ��ư Ŭ�� �̺�Ʈ ó��
    public void read(View view){
        try {
            //text.txt ������ �б� ���� ����
            fis = openFileInput("test.txt");

            StringBuffer sbuf = new StringBuffer();
            byte[] buf = new byte[40];

            //������ ���� ���������� ���Ͽ��� 40����Ʈ�� �о� buf�� ����
            while ((fis.read(buf, 0, 40)) != -1) {

                //buf �迭�� ����Ʈ �����͸� ���ڿ��� ��ȯ
                String str = new String(buf);

                //��ȯ�� ���ڿ��� ��Ʈ�� ���ۿ� ����
                sbuf.append(str);

                //���Ͽ� ���� �����Ͱ� 40 ����Ʈ���� ������ buf �迭��
                //���������� �� �κп� ������ ���� ���´�.
                //�̸� ó���ϱ� ���� �迭 ��ü�� ���鹮�ڷ� ó���ϰ�
                //���Ͽ��� ���� �����͸� ����.
                if (fis.available() < 40) {
                    //Arrays.fill() : �迭�� ������ ������ ä��
                    //param1 : ó���� �迭
                    //param2 : ���� �ε���
                    //param3 : �� �ε���
                    //param4 : �迭�� ä�� ������
                    Arrays.fill(buf, 0, 40, (byte) ' ');
                }

                //���� �ݱ�
                fis.close();

                //��Ʈ�� ���� ���� �ؽ�Ʈ �信 ���
                tv.setText(sbuf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
