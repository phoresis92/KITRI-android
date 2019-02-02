package com.example.addrbook.addrbook;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import vo.Member;

public class addProfile extends AppCompatActivity {

    Intent intent;

    //edit
    private EditText nameEdit;
    private EditText telEdit;

    ImageView mainProfileImage;

    //spinner
    private Spinner spinner;
    private ArrayAdapter<String> aa;
    private String[] imgs = {"profile1","profile2","profile3"};
    private int[] img_res ={R.drawable.profile, R.drawable.profile2, R.drawable.profile3};
    private int selected_img_res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        intent = getIntent();

        //edit
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        telEdit = (EditText) findViewById(R.id.telEdit);

        mainProfileImage = (ImageView) findViewById(R.id.mainProfileImage);

        //spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item ,imgs);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        //spinner select event
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_img_res = img_res[position];
                mainProfileImage.setImageResource(img_res[position]);
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }// onCreate method

    //======================================================================================

    public void onChImage(View v){
        intent = new Intent(this, proFileGrid.class);
        intent.putExtra("img_res", selected_img_res);
        startActivityForResult(intent, 1);
    }

    public void onBack(View v){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:
                    selected_img_res =data.getIntExtra("img_res_ok",0);
                    Toast.makeText(this, selected_img_res, Toast.LENGTH_SHORT).show();
                    mainProfileImage.setImageResource(selected_img_res);
                    aa.notifyDataSetChanged();
                    break;
            }
        }
    }

    public void saveProfile(View v){
        String[] arr = {nameEdit.getText().toString(), telEdit.getText().toString()};
        for(String s : arr){
            if(s.equals("")){
                Toast.makeText(this, "공백을 모두 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Integer.parseInt(arr[1]);
            }catch (Exception e){
                Toast.makeText(this, "전화번호를 숫자로 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            if(Integer.parseInt(arr[1])<=0){
                arr[1] = Math.abs(Integer.parseInt(arr[1]))+"";
            }
        }
        Member m = new Member(arr[0],arr[1],selected_img_res);
        intent.putExtra("m", m);
        setResult(RESULT_OK, intent);
        finish();
    }

}// Main class
