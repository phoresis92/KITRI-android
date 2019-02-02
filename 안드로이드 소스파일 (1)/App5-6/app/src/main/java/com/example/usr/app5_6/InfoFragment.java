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
        // res/layout/fragment1.xml 레이아웃을 현재 프래그먼트 UI로 생성
        View view = inflater.inflate(R.layout.fragment1, container, false);

        //이 레이아웃에 정의한 텍스트 뷰를 객체로 변환
        text = (TextView) view.findViewById(R.id.textView);

        //생성된 뷰 객체 리턴
        return view;
    }
}
