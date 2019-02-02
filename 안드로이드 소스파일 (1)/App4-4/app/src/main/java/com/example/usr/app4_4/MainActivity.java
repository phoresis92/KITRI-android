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
        // �׸���� ���۷��� �Ҵ�
        GridView gv = (GridView) findViewById(R.id.gridview);

        // ����͸� �����Ͽ� �׸����� ����
        gv.setAdapter(new ImageAdapter(this));

        // �׸��� �信 ��µ� �̹����� Ŭ���ϸ� ����� Ŭ�� ������ ����
        AdapterView.OnItemClickListener m = new AdapterView.OnItemClickListener() {

            // ������ �ϳ��� �����ϸ� �佺Ʈ�� ��ġ(�ε���) ���
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Toast.makeText(MainActivity.this, arg2 + "",
                        Toast.LENGTH_SHORT).show();
            }
        };

        // �׸���信 ������ Ŭ�� ������ ����
        gv.setOnItemClickListener(m);
    }
}

class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    // ����ͷ� ó���� �������� ���� ��ȯ
    @Override
    public int getCount() {
        return Idata.length;
    }

    // Ư�� ��ġ�� �ִ� ������ ��ȯ
    @Override
    public Object getItem(int arg0) {
        return null;
    }

    // Ư�� ��ġ�� �ִ� �������� id ��ȯ
    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    private Integer[] Idata = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6 };

    // Ư�� ��ġ�� �����͸� ����� �並 ��´�.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);

            // �׸���信 �̹��� �䰡 ��µ� �� ���� ��� ����
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
