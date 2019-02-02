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

    //text의 클릭 이벤트 함수
    public void showMenu(View v) {

        //팝업 메뉴 생성
        PopupMenu popup = new PopupMenu(this, v);

        // 팝업 메뉴에 클릭 이벤트 처리
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            //팝엡 메뉴 항목 중 하나 선택하면 실행
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //선택한 메뉴 항목의 id를 비교하여 개별처리
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

        //리소스 메뉴 항목을 팝엡 메뉴로 변환
        popup.inflate(R.menu.menu_main);

        //팝업 메뉴 화면에 출력
        popup.show();
    }

    public void print(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
