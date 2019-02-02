package com.example.usr.app4_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by usr on 2015-07-24.
 */
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
            TextView t2 = (TextView) convertView.findViewById(R.id.textView2);

            // list_item.xml�� �ؽ�Ʈ �信 m��ü�� name���
            if (t1 != null) {
                t1.setText(m.getName());
            }
            // list_item.xml�� �ؽ�Ʈ �信 m��ü�� tel���
            if (t2 != null) {
                t2.setText(m.getTel());
            }
        }

        return convertView;
    }
}

