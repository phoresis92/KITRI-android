package com.example.usr.app6_3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {
    private ImageView imageView;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰 파일 main.xml에 정의한 스피너의 레퍼런스 할당
        spinner = (Spinner) findViewById(R.id.spinner);

        //이미지 뷰 생성
        imageView = (ImageView) findViewById(R.id.imageView);

        //스피너 항목으로 사용할 텍스트 저장
        final String[] sizes = {"300 x 300", "400 x 400", "500 x 500"};

        //스피너에서 사용할 어댑터 생성
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sizes);

        //drop down view 생성. param : drop down view에 사용할 뷰 리소스
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        //스피너에 생성한 어댑터 설정
        spinner.setAdapter(adapter);

        //리소스 img1을 비트맵으로 생성
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);

        //어댑터 뷰의 항목중 하나를 선택하면 호출되는 콜백 메서드 등록
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {

                //선택한 스피너 항목의 텍스트 저장
                String name = sizes[position];
                Bitmap bitmap2 = null;

                switch (name) {
                    case "300 x 300":
                        //생성한 비트맵의 크기를 300 x 300으로 변환
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                        break;
                    case "400 x 400":
                        //생성한 비트맵의 크기를 400 x 400으로 변환
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
                        break;
                    case "500 x 500":
                        //생성한 비트맵의 크기를 500 x 500으로 변환
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
                        break;
                }

                //이미지뷰에 크기를 변환한 비트맵 bitmap2를 출력
                imageView.setImageBitmap(bitmap2);
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
