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

    //저장 버튼 클릭 이벤트 처리
    public void save(View view){
        try {
            //test.txt 파일을 이어쓰기 모드로 오픈
            fos = openFileOutput("test.txt", MODE_APPEND);

            //에디트 텍스트에 사용자가 입력한 문자열 읽어, 파일에 쓰기
            fos.write(et.getText().toString().getBytes());

            //파일닫기
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //읽기 버튼 클릭 이벤트 처리
    public void read(View view){
        try {
            //text.txt 파일을 읽기 모드로 오픈
            fis = openFileInput("test.txt");

            StringBuffer sbuf = new StringBuffer();
            byte[] buf = new byte[40];

            //파일의 끝을 만날때까지 파일에서 40바이트씩 읽어 buf에 저장
            while ((fis.read(buf, 0, 40)) != -1) {

                //buf 배열의 바이트 데이터를 문자열로 변환
                String str = new String(buf);

                //변환한 문자열을 스트링 버퍼에 저장
                sbuf.append(str);

                //파일에 남은 데이터가 40 바이트보다 작으면 buf 배열에
                //복사했을때 끝 부분에 쓰레기 값이 남는다.
                //이를 처리하기 위해 배열 전체를 공백문자로 처리하고
                //파일에서 읽은 데이터를 쓴다.
                if (fis.available() < 40) {
                    //Arrays.fill() : 배열을 지정한 값으로 채움
                    //param1 : 처리할 배열
                    //param2 : 시작 인덱스
                    //param3 : 끝 인덱스
                    //param4 : 배열을 채울 데이터
                    Arrays.fill(buf, 0, 40, (byte) ' ');
                }

                //파일 닫기
                fis.close();

                //스트링 버퍼 값을 텍스트 뷰에 출력
                tv.setText(sbuf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
