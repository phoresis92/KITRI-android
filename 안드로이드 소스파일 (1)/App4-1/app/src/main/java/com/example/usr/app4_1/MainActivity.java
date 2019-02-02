package com.example.usr.app4_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //�ɼ� �޴� �Ǵ� �׼ǹٿ� �׸� �߰� �޼���

        //menu_main.xml�� ������ �޴� �׸��� menu�� �߰��Ѵ�.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //���ڿ� ���ҽ��� string ��ü�� ��ȯ
        String title = getString(R.string.action_more1);

        //��Ÿ�ӽÿ� �޴� �׸� �߰�. param1:�׷�ID, param2:�׸�ID, param3:�׸����, param4:�׸� Ÿ��Ʋ
        menu.add(0, 1, 0, title);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // �ɼ� �޴� �Ǵ� �׼ǹ��� �׼��� Ŭ���ϸ� �ڵ� ȣ��Ǵ� �޼���
        //param:Ŭ���� �׸� ��ü

        //Ŭ���� �׸� ��ü�� id��ȯ
        int id = item.getItemId();

        //�׸��� id�� ���� ó��
        switch (id){
            case 1:
                printToast("menu item1");
                return true;
            case R.id.action_m2:
                printToast("menu item2");
                return true;
            case R.id.action_m3:
                printToast("menu item3");
                return true;
            case R.id.file_open:
                printToast("file open");
                return true;
            case R.id.file_save:
                printToast("file save");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void printToast(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
