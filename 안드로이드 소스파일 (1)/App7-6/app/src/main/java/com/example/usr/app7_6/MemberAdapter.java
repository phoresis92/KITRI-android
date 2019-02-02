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

    // ����� �信 �����͸� �� �׸� ����ϴ� �޼���
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // �� �׸��� ����Ҷ� ����� �䰡 �ִ��� Ȯ���Ͽ� ������ ���� ����
        // �� ���α׷������� ������ �� ����� ������ ����ϴ� �並
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resId, null);
        }

        // ����� �信 ����� ������ �����͸� m ������ ����
        Member m = items.get(position);

        if (m != null) {
            TextView t1 = (TextView) convertView.findViewById(R.id.textView);
            final TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
            ImageButton call = (ImageButton) convertView.findViewById(R.id.imageButton2);
            ImageButton sms = (ImageButton) convertView.findViewById(R.id.imageButton);
            // list_item.xml�� �ؽ�Ʈ �信 m��ü�� name���
            if (t1 != null) {
                t1.setText(m.getName());
            }
            // list_item.xml�� �ؽ�Ʈ �信 m��ü�� tel���
            if (t2 != null) {
                t2.setText(m.getTel());
            }

            //��ȭ�ɱ� ��ư�� Ŭ�� �̺�Ʈ ó��
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //�ȵ���̵� �⺻ ���α׷��� ���̾� ��Ƽ��Ƽ��
                    // ���������� Ȱ��ȭ�� ����Ʈ ����
                    Intent intent = new Intent(Intent.ACTION_CALL,  Uri.parse("tel:" + t2.getText()));

                    //��Ƽ��Ƽ Ȱ��ȭ
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

