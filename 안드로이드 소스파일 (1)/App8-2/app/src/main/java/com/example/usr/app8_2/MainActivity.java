package com.example.usr.app8_2;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> strs;
    private int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        strs = new ArrayList<String>();
        strs.add("aaa");
        strs.add("bbb");
        strs.add("ccc");
        strs.add("ddd");

        //����� ����
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);

        //����Ʈ �信 ����� ����
        listView.setAdapter(adapter);

        //����Ʈ �信 ���ؽ�Ʈ �޴� ���
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //���ؽ�Ʈ �޴� �׸� ����
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //���ؽ�Ʈ �޴� �׸� ���� �� ������ �� �׸��� ���� ��ü ����
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //������ �� �׸��� ��ġ ����
        idx = info.position;

        //������ �޴� �׸��� delete�̸� showDialog() ȣ��
        switch (item.getItemId()){
            case R.id.delete:
                showDialog();
                return true;
        }
        return false;
    }

    void showDialog() {
        //���̾�α� �����׸�Ʈ ����
        DialogFragment newFragment = MyDialogFragment.newInstance("are you sure?");

        //���̾�α� ����
        newFragment.show(getFragmentManager(), "dialog");
    }

    //���̾�α��� ������ư Ŭ���� ȣ��
    public void doPositiveClick() {
        //ArrayList���� ������ �׸��� ����
        strs.remove(idx);

        //����Ϳ� ������ ������ �˷� ȭ�� ����
        adapter.notifyDataSetChanged();
    }

    //���̾�α��� ���� ��ư Ŭ���� ȣ��
    public void doNegativeClick() {
        //�佺Ʈ�� ��ҵǾ����� �˸�
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }
}
