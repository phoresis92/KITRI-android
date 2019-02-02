package com.example.hushe.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import vo.Member;

public class Main2Activity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = (EditText) findViewById(R.id.editText);
        age = (EditText) findViewById(R.id.editText2);

        intent = getIntent(); // 이 액티비티를 활성화 하기위해 던진 인텐트를 꺼냄
        //인텐트에 저장된 추가정보를 추출
        //String n = intent.getStringExtra("name");
        //int a = intent.getIntExtra("age", 0);
        Member m = (Member) intent.getSerializableExtra("m");

        name.setText(m.getName());
        age.setText(m.getAge()+"");

    }

    public  void onClose(View v){
        Member m = new Member(name.getText().toString(), Integer.parseInt(age.getText().toString()));
        intent.putExtra("m", m);
        setResult(RESULT_OK, intent);
        finish();
    }

}
