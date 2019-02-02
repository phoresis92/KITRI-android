package com.example.addrbook.addrbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import vo.Member;

public class editFormIntent extends AppCompatActivity {

    private EditText nameE;
    private EditText telE;

    private Spinner spinnerE;
    private ArrayAdapter<String> adapter;
    private String[] showlist = {"profile1","profile2","profile3"};
    private int[] datalist = {R.drawable.profile, R.drawable.profile2,R.drawable.profile3};
    private int img_res = 0;


    private Button editBtnE;
    private ImageView profileE;

    //private Intent intent;

    private Member m;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form_intent);

        nameE = (EditText) findViewById(R.id.nameE);
        telE = (EditText) findViewById(R.id.telE);

        editBtnE = (Button) findViewById(R.id.editBtnE);
        profileE = (ImageView) findViewById(R.id.profileE);

/*        spinnerE = (Spinner) findViewById(R.id.spinnerE);
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
        });*/



        //인텐트 가져오기
        intent = getIntent();
        Member m = (Member) intent.getSerializableExtra("m");

        //기존 목록 설정
        nameE.setText(m.getName());
        telE.setText(m.getTel());
        profileE.setImageResource(m.getImg_res());
        img_res = m.getImg_res();
        Toast.makeText(this, img_res+"", Toast.LENGTH_SHORT).show();

    }

    public void onEditBtn(View v){
        m = new Member(nameE.getText().toString(), telE.getText().toString(), img_res);
        intent.putExtra("m", m);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onBack(View v){
        finish();
    }

    public void onImage(View v){

        Intent intent = new Intent(this, proFileGrid.class);
        intent.putExtra("img_res", img_res);
        Toast.makeText(this, img_res+": 선택하러감", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:

                    img_res = intent.getIntExtra("img_res_ok",0);
                    System.out.println(img_res);
                    Toast.makeText(this, "하고옴"+img_res, Toast.LENGTH_SHORT).show();
                    profileE.setImageResource(img_res);
                    break;
            }
            //adapter.notifyDataSetChanged();
        }
    }
}
