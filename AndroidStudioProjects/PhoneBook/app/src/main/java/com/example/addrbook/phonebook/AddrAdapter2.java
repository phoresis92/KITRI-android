package com.example.addrbook.phonebook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vo.Member;

public class AddrAdapter2 extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Member> list;

    TextView telText;
    TextView nameText;

    public AddrAdapter2(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = (ArrayList<Member>) objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //이미지 프레임 만들기
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        //데이터 입력
        Member mem = list.get(position);

        nameText = (TextView) convertView.findViewById(R.id.nameText);
        telText = (TextView) convertView.findViewById(R.id.telText);

        nameText.setText(mem.getName());
        telText.setText((mem.getTel()));

        //프로필 사진 설정
        ImageView imageView = convertView.findViewById(R.id.profileImg_layout);

        if(mem.getImg_res() != 0){
            imageView.setImageResource(mem.getImg_res());
        }

        //문자 메세지 이벤트 설정
        ImageView sms = (ImageView) convertView.findViewById(R.id.sms);
        sms.setFocusable(false);//sms가 버튼일 경우 클릭이 안될 수 있어서 ?
        sms.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(context, list.get(position).getName()+"님 "+list.get(position).getTel()+"로 문자를 보냅니다.", Toast.LENGTH_SHORT).show();

                smsWrite();

            }
        });

        //전화 걸기 이벤트 설정
        ImageView call = (ImageView) convertView.findViewById(R.id.call);
        call.setFocusable(false);
        call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(context, list.get(position).getName()+"님 "+list.get(position).getTel()+"로 전화를 겁니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ telText.getText().toString())); //tel: 문법적 요소로 꼭 넣어주자
                context.startActivity(intent);

                // action_Call 로 하면 manifest에 uses-permission android:name="android.permission.Call_Phone" //퍼미션을 받아야 한다.

                //명시적 방법
/*                Intent intent1 = new Intent(context, Main2Activity.class);
                context.startActivity(intent1);*/

            }

        });




        return convertView;
    }

    public void smsWrite(){
        ComponentName cn = new ComponentName("com.example.addrbook.sms", "com.example.addrbook.sms.writeMsg");
        Intent intent = new Intent("com.example.addrbook.sms.writeMsg");
        //인텐트에 전달할 객체 저장
        intent.putExtra("number", telText.getText().toString());
        intent.setComponent(cn);
        context.startActivity(intent);
    }

}
