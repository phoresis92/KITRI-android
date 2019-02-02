package com.example.usr.app5_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu_main.xml 리소스의 메뉴 항목을 액션 바에 추가
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //action_search 메뉴 항목을 searchItem에 저장
        MenuItem searchItem = menu.findItem(R.id.action_search);

        //searchItem 항목이 사용할 액션 뷰를 리턴
        SearchView searchView = (SearchView) searchItem.getActionView();

        //액션 뷰에 기본 텍스트 지정
        searchView.setQueryHint("name or email");

        //사용자가 질의를 변경할때 발생하는 이벤트 처리
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            //텍스트가 변경되면 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            //질의를 작성하고 엔터를 입력하면 호출
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
