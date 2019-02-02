package com.example.hushe.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class editFormIntent extends AppCompatActivity {

    private EditText nameE;
    private EditText telE;
    private EditText addrE;

    private Spinner spinnerE;
    private ArrayAdapter<String> adapter;
    private String[] showlist = {"profile1","profile2","profile3"};
    private int[] datalist = {R.drawable.profile, R.drawable.profile2,R.drawable.profile3};
    private int img_res = 0;


    private Button editBtnE;
    private ImageView profileE;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form_intent);

        nameE = (EditText) findViewById(R.id.nameE);
        telE = (EditText) findViewById(R.id.telE);
        addrE = (EditText) findViewById(R.id.addrE);

        editBtnE = (Button) findViewById(R.id.editBtnE);
        profileE = (ImageView) findViewById(R.id.profileE);

        spinnerE = (Spinner) findViewById(R.id.spinnerE);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, showlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerE.setAdapter(adapter);
        spinnerE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                img_res = datalist[position];
                profileE.setImageResource(datalist[position]);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //인텐트 가져오기
        intent = getIntent();
        Member m = (Member) intent.getSerializableExtra("m");

        //기존 목록 설정
        nameE.setText(m.getName());
        telE.setText(m.getTel());
        addrE.setText(m.getAddress());
        profileE.setImageResource(m.getImg_res());



    }

    public void onEditBtn(View v){
        Member m = new Member(nameE.getText().toString(), telE.getText().toString(), addrE.getText().toString(), img_res);
        intent.putExtra("m", m);
        setResult(RESULT_OK, intent);
        finish();
    }





}
