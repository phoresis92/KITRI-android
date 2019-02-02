package com.example.usr.app5_7;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button red;
    private Button green;
    private Button blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        red = (Button) findViewById(R.id.button);
        green = (Button) findViewById(R.id.button2);
        blue = (Button) findViewById(R.id.button3);

    }

    //��ư Ŭ���� ȣ���
    public void changeFragment(View view){

        //Ŭ���� ��ư�� id����
        int id = view.getId();
        Fragment fragment = null;

        //id�� ���� �����׸�Ʈ ����
        switch (id){
            case R.id.button:
                //1��ư�̸� ���� �����׸�Ʈ ����
                fragment = new RedFragment();
                break;
            case R.id.button2:
                //2��ư�̸� ��� �����׸�Ʈ ����
                fragment = new GreenFragment();
                break;
            case R.id.button3:
                //3��ư�̸� �Ķ� �����׸�Ʈ ����
                fragment = new BlueFragment();
                break;
        }

        //�����׸�Ʈ ��ü ����
        FragmentManager fragmentManager = getFragmentManager();
        //�����׸�Ʈ Ʈ����� ����
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //�����׸�Ʈ ��ü
        fragmentTransaction.replace(R.id.fragment, fragment);
        //�����Ϸ�
        fragmentTransaction.commit();
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
