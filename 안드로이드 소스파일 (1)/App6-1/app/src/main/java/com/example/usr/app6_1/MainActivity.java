package com.example.usr.app6_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText et;
    private Button add;
    private ListView lv;
    private ArrayAdapter<String> aa;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        add = (Button) findViewById(R.id.button);
        lv = (ListView) findViewById(R.id.listView);

        data = new ArrayList<String>();

        //사용자 정의 텍스트 뷰가 정의된 레이아웃 파일을 어댑터에 지정
        aa = new ArrayAdapter<String>(this, R.layout.item, data);

        lv.setAdapter(aa);

    }

    public void saveData(View view){
        data.add(0, et.getText().toString());
        et.setText("");
        aa.notifyDataSetChanged();
    }
}
