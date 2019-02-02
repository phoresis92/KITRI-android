package com.example.hushe.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddrAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Member> list;


    public AddrAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.list = (ArrayList<Member>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            //========================= 이미지 레이아웃 까지 생성
        }
        Member m = list.get(position);

        TextView t_name = convertView.findViewById(R.id.textView2);
        TextView t_tel = convertView.findViewById(R.id.textView5);
        TextView t_add = convertView.findViewById(R.id.textView6);

        //프로필 사진
        ImageView iv = convertView.findViewById(R.id.imageView);

        t_name.setText(m.getName());
        t_tel.setText(m.getTel());
        t_add.setText(m.getAddress());
            //========================= 데이터 입력 과정

        //프로필 사진 입력
        if(m.getImg_res() != 0){
            iv.setImageResource(m.getImg_res());
        }

        return convertView;
    }
}
