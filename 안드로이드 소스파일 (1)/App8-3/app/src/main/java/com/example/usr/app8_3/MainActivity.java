package com.example.usr.app8_3;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog();
    }
    void showDialog() {
        //다이얼로그 프래그먼트 생성
        DialogFragment newFragment = MyDialogFragment.newInstance("favorite color?");

        //다이얼로그 실행
        newFragment.show(getFragmentManager(), "dialog");
    }
    //다이얼로그의 긍정버튼 클릭시 호출
    public void doPositiveClick(String color) {
        //다이얼로그에서 선택한 색상 출력
        Toast.makeText(this, color, Toast.LENGTH_SHORT).show();
    }

    //다이얼로그의 부정 버튼 클릭시 호출
    public void doNegativeClick() {
        //토스트로 취소되었음을 알림
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }
}
