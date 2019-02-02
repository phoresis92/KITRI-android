package com.example.usr.app7_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
    //�׼Ǹ����� ����� ���ڿ� ��� ����
    private final String myAction = "com.example.usr.app7_4.ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //��ư Ŭ�� �� ȣ��Ǵ� �޼���
    public void callReceiver(View view){

        //���������� Ȱ��ȭ�� ����Ʈ ����. �Ķ���ͷ� �׼Ǹ��� �����Ѵ�.
        //�׼��̸��� ������ ������Ʈ�� Ȱ��ȭ�� ���̴�.
        Intent intent = new Intent(myAction);

        //���ù����� ����Ʈ ����
        sendBroadcast(intent);
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
