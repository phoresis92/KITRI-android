package com.example.addrbook.filetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {

    private EditText fileName;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fileName = (EditText) findViewById(R.id.fileName);
        content = (EditText) findViewById(R.id.content);

    }

    public void onRead(View view) throws IOException {
        FileInputStream fi = openFileInput(fileName.getText().toString());
        StringBuffer sb = new StringBuffer();
        byte[] arr = new byte[40];
        int len = 0 ;
        while(true){
            len = fi.read(arr);
            if(len<=0){
                break;
            }
            String s = new String(arr);
            sb.append(s);
            if(fi.available()<40){
                Arrays.fill(arr,0,40,(byte)0);
            }
        }
        content.setText(sb.toString());
        fi.close();


    }

    public void onWrite(View view) throws IOException {
        FileOutputStream fo = openFileOutput(fileName.getText().toString(), MODE_APPEND);
        fo.write(content.getText().toString().getBytes());
        fo.close();
        content.setText("");
    }


}
