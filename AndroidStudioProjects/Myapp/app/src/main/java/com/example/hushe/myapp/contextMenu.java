package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class contextMenu extends AppCompatActivity {

    //private TextView tv;
    private EditText et;
    private ListView lv;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Button saveBtn;

    private int idx;

    private static final int EDIT = 1;
    private static final int DEL = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        saveBtn = (Button) findViewById(R.id.saveBtn);

        //tv = (TextView) findViewById(R.id.contextMenu);
        //textView에 컨택스트 메뉴 설정
        //registerForContextMenu(tv);

        et = (EditText) findViewById(R.id.et1);
        // 1. 뷰에 리스트 뷰를 추가
        lv = (ListView) findViewById(R.id.lv1);
        // 2. 데이터를 저장할 리스트 생성
        list = new ArrayList<>();
        // 3. 리스트 뷰가 사용할 어댑터를 생성. 어댑터 생성시 사용할 리스트 연결
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        // 4. 리스트 뷰에 어댑터 설정
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    public void onSave(View view){
        Button b = (Button) view;
        String str = b.getText().toString();
        if(str.equals("SAVE")){
            list.add(0, et.getText().toString());
        }else if(str.equals("EDIT")){
            list.set(idx, et.getText().toString());
            b.setText("SAVE");
        }
        adapter.notifyDataSetChanged();
        et.setText("");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //메뉴 타이틀 설정
        menu.setHeaderTitle("UPDATE");
        menu.add(0,EDIT,0,"EDIT");
        menu.add(0,DEL,0,"DEL");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        idx = info.position; //리스트 뷰에서 사용자가 클릭한 위치
        switch (item.getItemId()){
            case EDIT:
                editForm();
                break;
            case DEL:
                list.remove(idx);
                adapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    public void editForm(){
        et.setText(list.get(idx));
        saveBtn.setText("EDIT");
    }

}
