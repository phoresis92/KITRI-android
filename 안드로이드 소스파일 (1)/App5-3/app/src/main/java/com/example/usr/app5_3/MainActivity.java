package com.example.usr.app5_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayList<String> strs;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //ArrayList ����
        strs = new ArrayList<String>();

        //ArrayList�� ���ڿ� ����
        strs.add("aaa");
        strs.add("bbb");
        strs.add("ccc");

        //����Ʈ �信�� ����� ����� ����
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
        //����Ʈ �信 ����� ����
        listView.setAdapter(adapter);

        //����Ʈ �� �׸� ���� ��� ����
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        //CHOICE_MODE_MULTIPLE_MODAL �̺�Ʈ�� ���� �����ʸ� ����Ʈ�信 ����
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            String str = "";

            //����Ʈ �� �׸� ���� ���°� ���ϸ� ȣ��
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                //�׸� üũ ���°� true�̸� ���� �׸��� �ؽ�Ʈ�� ������� str�� ����
                if(checked){
                    str = strs.get(position);
                }
            }

            //�׼� �޴� �׸��� �����ϸ� ȣ��
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                //������ �޴� �׸��� ������ ���� ó��
                //����Ʈ �信 ������ �׸��� �ؽ�Ʈ�� �޴� �׸��� �ؽ�Ʈ�� ���
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(getApplicationContext(), str+"item1", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    case R.id.item2:
                        Toast.makeText(getApplicationContext(), str+"item2", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    case R.id.item3:
                        Toast.makeText(getApplicationContext(), str+"item3", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            //�׼� �޴��� ������ �� ȣ��
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // �޴� �������ͷ� menu_main.xml�� �޴� ��ü�� ��ȯ
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
    }

}
