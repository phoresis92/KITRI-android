package com.example.addrbook.addrbook;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class proFileGrid extends AppCompatActivity {

    private Intent intent;

    private GridView gridView;
    private ImageAdapter imageAdapter;

    private int before;
    private int selected_res;
    private int[] img_res ={R.drawable.profile, R.drawable.profile2, R.drawable.profile3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_file_grid);

        gridView = (GridView) findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        intent = getIntent();
        before = intent.getExtras().getInt("img_res");
        Toast.makeText(this, before+": 넘어온", Toast.LENGTH_SHORT).show();



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_res = img_res[position];
                Toast.makeText(proFileGrid.this, selected_res, Toast.LENGTH_SHORT).show();
                intent.putExtra("img_res_ok", selected_res);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}


    class ImageAdapter extends BaseAdapter{

        Integer[] imgs = {R.drawable.profile,R.drawable.profile2,R.drawable.profile3};

        private Context context;

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

                imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else{
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(imgs[position]);
            return imageView;
        }
    }