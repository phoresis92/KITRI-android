package com.example.addrbook.phonebook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import vo.Member;


public class NewItemActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText numberText;
    private ImageView pImage;
    private Button saveBtn;

    private int img_res;

    private Intent intent;

    private PhoneBookDBA dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        dba = new PhoneBookDBA(this);

        nameText = (EditText) findViewById(R.id.nameText);
        numberText = (EditText) findViewById(R.id.numberText);
        pImage = (ImageView) findViewById(R.id.pImage);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        //수정시!!!
        Intent intent = getIntent();
        Member m = (Member) intent.getSerializableExtra("m");
        if(m != null){
            nameText.setText(m.getName());
            numberText.setText(m.getTel());
            pImage.setImageResource(m.getImg_res());
            img_res = m.getImg_res();
        }
        //===========

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String n = nameText.getText().toString();
                String t = numberText.getText().toString();
                if(n.equals("")||t.equals("")){
                    Toast.makeText(NewItemActivity.this, "모두 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                Member m = new Member(n, t, img_res);


                Intent intent = new Intent();
                intent.putExtra("m", m);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        pImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectImgActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:
                    intent = getIntent();
                    img_res = (int)data.getLongExtra("imgId",0);
                    Toast.makeText(this, "img_res", Toast.LENGTH_SHORT).show();
                    pImage.setImageResource(img_res);
                    break;
            }
        }

    }

}
