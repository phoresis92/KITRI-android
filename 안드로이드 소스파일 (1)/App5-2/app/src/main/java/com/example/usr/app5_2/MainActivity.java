package com.example.usr.app5_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private EditText str;
    private Button save;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> strs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        str = (EditText) findViewById(R.id.editText);
        save = (Button) findViewById(R.id.button);
        list = (ListView) findViewById(R.id.listView);

        strs = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
        list.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strs.add(0, str.getText().toString());
                adapter.notifyDataSetChanged();
                str.setText("");
            }
        });

        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        //컨텍스트 메뉴에 메뉴 리소스에서 정의한 항목들을 추가
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemId = item.getItemId();
        int idx = info.position;

        switch (itemId){
            case R.id.context_item1:
                Toast.makeText(this, "edit "+strs.get(idx), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.context_item2:
                Toast.makeText(this, "delete "+strs.get(idx), Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
