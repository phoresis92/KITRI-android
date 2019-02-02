package com.example.addrbook.memo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class writeMemo extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Boolean isEdit = false;

    private int idx;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_memo);

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);

        intent = getIntent();

        String t = intent.getStringExtra("title");
        idx = intent.getIntExtra("idx",0);

        if(! (t==null) || !(t=="")){
            isEdit = true;
            String path = Environment.getDataDirectory()+"/data/com.example.addrbook.memo/files";
            File dirFile = new File(path);
            File[] fileList = dirFile.listFiles();
            for(File temp : fileList){
                if(temp.isFile() && temp.getName().equals(t)){
                    try {
                        FileInputStream fi = openFileInput(t);
                        StringBuffer sb = new StringBuffer();
                        byte[] arr = new byte[40];
                        int len = 0;
                        while (true) {
                            len = fi.read(arr);
                            if (len <= 0) {
                                break;
                            }
                            String s = new String(arr);
                            sb.append(s);
                            if (fi.available() < 40) {
                                Arrays.fill(arr, 0, 40, (byte) 0);
                            }
                        }
                        content.setText(sb.toString());
                        fi.close();
                        title.setText(t);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }//if end
            }//for end
        }// Fin


    }

    public void onSave(View view){
        String t = title.getText().toString();
        String c = content.getText().toString();
        if(t.equals("")||c.equals("")){
            Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String fileName = title.getText().toString();
            Boolean flag = true;

            String path = Environment.getDataDirectory()+"/data/com.example.addrbook.memo/files";
            System.out.println(path);
            File dirFile = new File(path);
            File[] fileList = dirFile.listFiles();
            for(File temp : fileList){
                if(temp.isFile() && fileName.equals(temp.getName())){
                    flag = false;
                }
            }
            if(isEdit){
                flag =true;

            }
            if(flag) {
                FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
                fos.write(content.getText().toString().getBytes());
                fos.close();

                Intent intent = new Intent();
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("idx", idx);
                setResult(RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(this, "동일한 이름의 제목이 있습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
