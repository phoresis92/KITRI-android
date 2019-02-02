package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class menutest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menutest);
    }

    //메뉴 항목 추가 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"add");
        menu.add(0,2,0,"edit");
        menu.add(0,3,0,"del");


        return true;
    }

    //메뉴 항목을 선택했을때 호출될 핸들러
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case 1:
                Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "del", Toast.LENGTH_SHORT).show();
                break;

        }

        return true;
    }



}
