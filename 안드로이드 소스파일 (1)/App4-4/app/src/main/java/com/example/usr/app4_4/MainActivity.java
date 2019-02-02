package com.example.usr.app4_4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 그리드뷰 레퍼런스 할당
        GridView gv = (GridView) findViewById(R.id.gridview);

        // 어댑터를 생성하여 그리드뷰와 연결
        gv.setAdapter(new ImageAdapter(this));

        // 그리드 뷰에 출력된 이미지를 클릭하면 실행될 클릭 리스너 생성
        AdapterView.OnItemClickListener m = new AdapterView.OnItemClickListener() {

            // 아이템 하나를 선택하면 토스트로 위치(인덱스) 출력
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Toast.makeText(MainActivity.this, arg2 + "",
                        Toast.LENGTH_SHORT).show();
            }
        };

        // 그리드뷰에 생성한 클릭 리스너 설정
        gv.setOnItemClickListener(m);
    }
}

class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    // 어댑터로 처리될 데이터의 개수 반환
    @Override
    public int getCount() {
        return Idata.length;
    }

    // 특정 위치에 있는 데이터 반환
    @Override
    public Object getItem(int arg0) {
        return null;
    }

    // 특정 위치에 있는 데이터의 id 반환
    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    private Integer[] Idata = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6 };

    // 특정 위치의 데이터를 출력할 뷰를 얻는다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);

            // 그리드뷰에 이미지 뷰가 출력될 때 정렬 방법 지정
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(Idata[position]);
        return imageView;
    }
}
