package com.example.usr.app8_1;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText name;
    private EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        age = (EditText) findViewById(R.id.editText2);
    }

    //CONFIRM 버튼을 클릭하면 호출
    public void showDialog(View view){
        //사용자가 입력한 이름, 나이를 EditText로 부터 읽는다.
        String str1 = name.getText().toString();
        String str2 = age.getText().toString();

        //프래그먼트의 트랜잭션 시작
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        //태그명이 dialog인 프래그먼트 객체를 읽어옴
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");

        //프래그먼트가 널이 아니면 프래그먼트를 제거
        if (prev != null) {
            ft.remove(prev);
        }

        //백키를 눌렀을때 이동할 프래그먼트를 널로 설정
        ft.addToBackStack(null);

        //새 다이얼로그 프래그먼트 생성한다.
        //파라메터로 사용자가 입력한 이름과 나이를 전달한다.
        DialogFragment newFragment = MyDialogFragment.newInstance(str1, str2);

        //다이얼로그 프래그먼트를 화면에 보이게 한다.
        newFragment.show(ft, "dialog");

        //EditText를 초기화
        name.setText("");
        age.setText("");
    }

}
