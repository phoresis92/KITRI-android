package com.example.usr.contentprovidertest2;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Member;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView lv;
    private ArrayList<String> list;

    private Cursor c;
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.usr.contentprovidertest/email");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 ,list);
        list = new ArrayList<>();
        lv.setAdapter(adapter);


    }

    //app\src\main\java\com\example\usr\
    //12-3

    public void show(){
        c = getContentResolver().query(CONTENT_URI, null,null,null,null)
        adapter.clear();
        if(c.moveToFirst()){
            do {
                com.example.usr.contentprovidertest2.Member
            }
        }

    }


}
