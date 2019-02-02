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
        //옵션 메뉴 또는 액션바에 항목 추가 메서드

        //menu_main.xml에 정의한 메뉴 항목을 menu에 추가한다.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //문자열 리소스를 string 객체로 변환
        String title = getString(R.string.action_more1);

        //런타임시에 메뉴 항목 추가. param1:그룹ID, param2:항목ID, param3:항목순서, param4:항목 타이틀
        menu.add(0, 1, 0, title);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 옵션 메뉴 또는 액션바의 액션을 클릭하면 자동 호출되는 메서드
        //param:클릭된 항목 객체

        //클릭된 항목 객체의 id반환
        int id = item.getItemId();

        //항목의 id에 따라 처리
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
