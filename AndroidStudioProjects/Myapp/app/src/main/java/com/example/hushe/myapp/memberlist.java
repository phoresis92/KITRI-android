package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class memberlist extends AppCompatActivity {

    private TextView name;
    private EditText tel;
    private EditText add;
    private TextView result;
    Member mem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);

        name = (EditText)findViewById(R.id.nameText);
        tel = (EditText)findViewById(R.id.telText);
        add = (EditText)findViewById(R.id.addText);
        result = (TextView) findViewById(R.id.resultText);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"저장");
        menu.add(0,2,0,"수정");
        menu.add(0,3,0,"삭제");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case 1: //저장 메뉴
                save();
                break;
            case 2: //수정 메뉴
                edit();
                break;
            case 3: //삭제 메뉴
                del();
                break;
        }
        return true;
    }

    public void initText(){
        name.setText("");
        tel.setText("");
        add.setText("");
    }

    public void save(){
        //String str = "";
       /* str = name.getText().toString()+":"+tel.getText().toString()+":"+add.getText().toString();
        result.setText(str);*/
       String n = name.getText().toString();
       String t = tel.getText().toString();
       String a = add.getText().toString();

       if(n.equals("")||t.equals("")||a.equals("")){
           Toast.makeText(this, "값을 모두 입력해 주세요", Toast.LENGTH_SHORT).show();
           return;
       }
/*       mem.setName(n);
       mem.setTel(t);
       mem.setAddress(a);*/
        mem = new Member(n,t,a);
       result.setText(mem.toString());
        initText();
    }

    public void edit(){
/*        String str = "";
        str = result.getText().toString();
        String[] arr = str.split(":");
        name.setText(arr[0]);
        tel.setText(arr[1]);
        add.setText(arr[2]);*/

        if(result.equals("")||mem == null|| mem.getName()==null){
            Toast.makeText(this, "저장 먼저 해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] arr = result.getText().toString().split("/");
        /*name.setText(mem.getName());
        tel.setText(mem.getTel());
        add.setText(mem.getAddress());*/
        name.setText(arr[0].trim());
        tel.setText(arr[1].trim());
        add.setText(arr[2].trim());

    }

    public void del(){
        result.setText("");
        name.setText("");
        tel.setText("");
        add.setText("");
        mem = new Member();
    }


}
