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

    //버튼 클릭시 호출됨
    public void changeFragment(View view){

        //클릭된 버튼의 id저장
        int id = view.getId();
        Fragment fragment = null;

        //id에 따라 프래그먼트 생성
        switch (id){
            case R.id.button:
                //1버튼이면 빨강 프래그먼트 생성
                fragment = new RedFragment();
                break;
            case R.id.button2:
                //2버튼이면 녹색 프래그먼트 생성
                fragment = new GreenFragment();
                break;
            case R.id.button3:
                //3버튼이면 파랑 프래그먼트 생성
                fragment = new BlueFragment();
                break;
        }

        //프래그먼트 객체 생성
        FragmentManager fragmentManager = getFragmentManager();
        //프래그먼트 트랜잭션 시작
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //프래그머트 교체
        fragmentTransaction.replace(R.id.fragment, fragment);
        //수정완료
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
