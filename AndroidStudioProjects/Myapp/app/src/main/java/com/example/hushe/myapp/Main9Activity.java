package com.example.hushe.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Main9Activity extends AppCompatActivity {

    private GridView gv;
    private ImageAdapter ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        gv = (GridView) findViewById(R.id.gv);
        ia = new ImageAdapter(this);
        gv.setAdapter(ia);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main9Activity.this, position+"", Toast.LENGTH_SHORT).show();
                boolean flag = ia.setPos(position);
                if(!flag){
                    ia.notifyDataSetChanged();
                }
            }
        });

    }

    class ImageAdapter extends BaseAdapter{

        Integer[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.qq1, R.drawable.qq2, R.drawable.qq3};

        private Context context;

        private int pos1;
        private boolean flag = false;

        public boolean setPos(int pos){
            if(flag){ //두번째 클릭
                Integer tmp = imgs[pos1];
                imgs[pos1] = imgs[pos];
                imgs[pos] = tmp;
                flag = false;
            }else{ // 첫번째 클릭
                pos1 = pos;
                flag = true;
            }
            return flag;
        }

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public Object getItem(int position) {
            return imgs[position];
        }

        @Override
        public long getItemId(int position) {
            return imgs[position];
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(context);

                //그리드뷰에 이미지 뷰가 출력될 때 정렬 방법 지정
                imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgs[position]);
            return imageView;
        }
    }

}
