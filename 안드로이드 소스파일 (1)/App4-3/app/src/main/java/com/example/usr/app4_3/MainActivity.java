package com.example.usr.app4_3;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends ListActivity {
    private ArrayList<String> strs;
    private ArrayAdapter<String> adapter;
    private EditText input;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.editText);
        save = (Button)findViewById(R.id.button);

        //데이터 저장할 리스트 생성
        strs = new ArrayList<String>();

        //어댑터 뷰에서 사용할 어댑터 생성
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);

        //생성한 어댑터를 리스트뷰에 설정
        setListAdapter(adapter);

        //save버튼의 클릭 이벤트 처리
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //에디트 텍스트에 입력한 텍스트를 리스트 0번째 방에 저장
                strs.add(0, input.getText().toString());

                //어댑터가 접근하는 리스트의 데이터 변경이 발생하면 리스트뷰에 다시 출력
                adapter.notifyDataSetChanged();

                //에디트 텍스트에 새 데이터 입력하도록 초기화
                input.setText("");
            }
        });

    }

    //리스트 뷰의 클릭 이벤트 처리
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //리스트 뷰에서 한 항목에 클릭이 발생하면 클릭된 항목의 텍스트를 토스트로 출력
        Toast.makeText(getApplicationContext(), strs.get(position),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
