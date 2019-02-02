package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class eventhandler extends AppCompatActivity {

    private TextView title;
    private Button b;
    private RadioGroup rg;
    private LinearLayout linear;
    private CheckBox chTra;
    private CheckBox chFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handler);

        title = (TextView) findViewById(R.id.title);
        b = (Button) findViewById(R.id.signup);
        linear = (LinearLayout) findViewById(R.id.linear1);
        rg = (RadioGroup) findViewById(R.id.rg);
        chTra = (CheckBox) findViewById(R.id.travel);
        chFish = (CheckBox) findViewById(R.id.fish);

        title.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String str = ((TextView)v).getText().toString();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

        chTra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CheckBox c = (CheckBox) v;
                if(c.isChecked()) {
                    Toast.makeText(getApplicationContext(), c.getText() + "가 선택되었다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), c.getText()+"가 선택 해제되었다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chFish.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CheckBox c = (CheckBox) v;
                if(c.isChecked()) {
                    Toast.makeText(getApplicationContext(), c.getText() + "가 선택되었다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), c.getText()+"가 선택 해제되었다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int cnt = 0;
                StringBuffer str = new StringBuffer();
                str.append("당신의 취미는 ");

                if(chTra.isChecked()){
                    str.append(chTra.getText()+" ");
                }else{
                    cnt++;
                }
                if(chFish.isChecked()){
                    str.append(chFish.getText()+" ");
                }else{
                    cnt++;
                }

                if(cnt == 2){
                    str.append("없습니다.");
                }else{
                    str.append("입니다.");
                }

                Toast.makeText(getApplicationContext(), str.toString() , Toast.LENGTH_SHORT).show();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*String color = "";
                RadioButton rb = (RadioButton)findViewById(checkedId);
                b.setText(rb.getText());*/

                int c = 0;
                switch(checkedId){
                    case R.id.radioButton:
                        c = 0xFFF10810;
                        break;
                    case R.id.radioButton2:
                        c = 0xFF008577;
                        break;
                    case R.id.radioButton3:
                        c = 0xFF87E9DB;
                        break;
                }

                linear.setBackgroundColor(c);
            }
        });




    }
}
