package com.example.usr.app5_6;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {
    public static TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // res/layout/fragment1.xml ���̾ƿ��� ���� �����׸�Ʈ UI�� ����
        View view = inflater.inflate(R.layout.fragment1, container, false);

        //�� ���̾ƿ��� ������ �ؽ�Ʈ �並 ��ü�� ��ȯ
        text = (TextView) view.findViewById(R.id.textView);

        //������ �� ��ü ����
        return view;
    }
}
