package com.example.usr.app8_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //토스트 출력 함수
    public void showToast(View view) {

        //LayoutInflater 객체를 시스템으로부터 받아옴
        LayoutInflater inflater = getLayoutInflater();

        //LayoutInflater 객체의 inflate 메서드로 토스트 UI XML파일을 View 객체로 변환
        View layout = inflater.inflate(R.layout.toastmain, null);

        //이미지 뷰 레퍼런스 저장
        ImageView image = (ImageView) layout.findViewById(R.id.imageView);

        //이미지 뷰에 출력할 이미지 리소스 지정
        image.setImageResource(R.mipmap.ic_launcher);

        //텍스트 뷰 레퍼런스 저장
        TextView text = (TextView) layout.findViewById(R.id.textView2);

        //텍스트 뷰에 출력할 텍스트 지정
        text.setText("hello toast!");

        //토스트 객체 생성
        Toast toast = new Toast(getApplicationContext());

        //토스트 출력 위치 설정 수직 중앙에 출력
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        //토스트 출력 시간 지정
        toast.setDuration(Toast.LENGTH_LONG);

        //토스트의 뷰를 UI XML 파일로 생성한 뷰로 지정
        toast.setView(layout);

        //토스트 출력
        toast.show();
    }

}
