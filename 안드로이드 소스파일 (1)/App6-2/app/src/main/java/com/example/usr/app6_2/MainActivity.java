package com.example.usr.app6_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {
    private ImageView iv;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이미지 뷰 레퍼런스 할당
        iv = (ImageView) findViewById(R.id.imageView);

        //뷰 파일 main.xml에 정의한 스피너의 레퍼런스 할당
        spinner = (Spinner) findViewById(R.id.spinner);

        final String[] imgNames = {"img1", "img2", "img3"};

        //스피너에서 사용할 어댑터 생성
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, imgNames);

        //drop down view 생성. param : drop down view에 사용할 뷰 리소스
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //스피너에 생성한 어댑터 설정
        spinner.setAdapter(adapter);

        //어댑터 뷰의 항목중 하나를 선택하면 호출되는 콜백 메서드 등록
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = imgNames[position];

                switch (name) {
                    case "img1":
                        // res/drawable/img.jpg 이미지를 이미지 뷰에 설정.
                        // 이 파일이 먼저 drawable 디렉토리에 복사되어 있어야 함.
                        iv.setImageResource(R.drawable.img1);
                        break;
                    case "img2":
                        iv.setImageResource(R.drawable.img2);
                        break;
                    case "img3":
                        iv.setImageResource(R.drawable.img3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
