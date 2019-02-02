package com.example.usr.app7_6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class MemberAdapter extends ArrayAdapter<Member> {
    private ArrayList<Member> items;
    private Context context;
    private int resId;

    public MemberAdapter(Context context, int textViewResourceId,
                         ArrayList<Member> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        resId = textViewResourceId;
        this.items = objects;
    }

    // 어댑터 뷰에 데이터를 한 항목씩 출력하는 메서드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 한 항목을 출력할때 사용할 뷰가 있는지 확인하여 없으면 새로 생성
        // 이 프로그램에서는 폰북의 한 사람의 정보를 출력하는 뷰를
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resId, null);
        }

        // 어댑터 뷰에 출력할 순서의 데이터를 m 변수에 저장
        Member m = items.get(position);

        if (m != null) {
            TextView t1 = (TextView) convertView.findViewById(R.id.textView);
            final TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
            ImageButton call = (ImageButton) convertView.findViewById(R.id.imageButton2);
            ImageButton sms = (ImageButton) convertView.findViewById(R.id.imageButton);
            // list_item.xml의 텍스트 뷰에 m객체의 name출력
            if (t1 != null) {
                t1.setText(m.getName());
            }
            // list_item.xml의 텍스트 뷰에 m객체의 tel출력
            if (t2 != null) {
                t2.setText(m.getTel());
            }

            //전화걸기 버튼의 클릭 이벤트 처리
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //안드로이드 기본 프로그램의 다이얼링 액티비티를
                    // 묵시적으로 활성화할 인텐트 생성
                    Intent intent = new Intent(Intent.ACTION_CALL,  Uri.parse("tel:" + t2.getText()));

                    //액티비티 활성화
                    context.startActivity(intent);

                }
            });

            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ComponentName cn = new ComponentName("com.example.usr.app9_2","com.example.usr.app9_2.MainActivity");
                    Intent intent = new Intent("com.example.usr.app9_2.sms");
                    intent.putExtra("tel", t2.getText().toString());
                    intent.setComponent(cn);
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }
}

