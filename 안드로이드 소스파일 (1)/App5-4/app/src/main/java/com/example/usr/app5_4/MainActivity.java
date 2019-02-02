package com.example.usr.app5_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text1);

    }

    //text�� Ŭ�� �̺�Ʈ �Լ�
    public void showMenu(View v) {

        //�˾� �޴� ����
        PopupMenu popup = new PopupMenu(this, v);

        // �˾� �޴��� Ŭ�� �̺�Ʈ ó��
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            //�˿� �޴� �׸� �� �ϳ� �����ϸ� ����
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //������ �޴� �׸��� id�� ���Ͽ� ����ó��
                switch (item.getItemId()) {
                    case R.id.red:
                        print("red");
                        return true;
                    case R.id.blue:
                        print("blue");
                        return true;
                    case R.id.green:
                        print("green");
                        return true;
                    default:
                        return false;
                }
            }
        });

        //���ҽ� �޴� �׸��� �˿� �޴��� ��ȯ
        popup.inflate(R.menu.menu_main);

        //�˾� �޴� ȭ�鿡 ���
        popup.show();
    }

    public void print(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
