package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText)findViewById(R.id.editText);


    }




    //버튼 1의 클릭 이벤트 핸들러
    public void onBtn1(View view){
        Toast.makeText(getApplicationContext() , "1번 버튼 클릭", Toast.LENGTH_SHORT).show();
    }


    //버튼 2의 클릭 이벤트 핸들러 //editText의 값을 가져와서 Toast로 호출한다!
    public void onBtn2(View view){
        Toast.makeText(getApplicationContext(), et.getText() , Toast.LENGTH_SHORT).show();
    }
}
