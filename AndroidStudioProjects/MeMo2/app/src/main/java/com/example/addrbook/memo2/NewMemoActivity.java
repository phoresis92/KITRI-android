package com.example.addrbook.memo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewMemoActivity extends AppCompatActivity {

    private EditText fileName;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);

        fileName = (EditText) findViewById(R.id.fileName);
        content = (EditText) findViewById(R.id.content);

    }


    public void onSave(View view){
        String name = fileName.getText().toString();
        String path = "/data/data/com.example.addrbook.memo2/files/";
        File f = new File(path+name);
        if(f.exists()){
            Toast.makeText(this, "이미 사용중인 파일명", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
            return;
        }
        try {
                FileOutputStream fos = openFileOutput(fileName.getText().toString(), MODE_APPEND);
                fos.write(content.getText().toString().getBytes());
                fos.close();
                content.setText("");
                setResult(RESULT_OK);
                finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
