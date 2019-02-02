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

        //������ ������ ����Ʈ ����
        strs = new ArrayList<String>();

        //����� �信�� ����� ����� ����
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);

        //������ ����͸� ����Ʈ�信 ����
        setListAdapter(adapter);

        //save��ư�� Ŭ�� �̺�Ʈ ó��
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //����Ʈ �ؽ�Ʈ�� �Է��� �ؽ�Ʈ�� ����Ʈ 0��° �濡 ����
                strs.add(0, input.getText().toString());

                //����Ͱ� �����ϴ� ����Ʈ�� ������ ������ �߻��ϸ� ����Ʈ�信 �ٽ� ���
                adapter.notifyDataSetChanged();

                //����Ʈ �ؽ�Ʈ�� �� ������ �Է��ϵ��� �ʱ�ȭ
                input.setText("");
            }
        });

    }

    //����Ʈ ���� Ŭ�� �̺�Ʈ ó��
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //����Ʈ �信�� �� �׸� Ŭ���� �߻��ϸ� Ŭ���� �׸��� �ؽ�Ʈ�� �佺Ʈ�� ���
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
