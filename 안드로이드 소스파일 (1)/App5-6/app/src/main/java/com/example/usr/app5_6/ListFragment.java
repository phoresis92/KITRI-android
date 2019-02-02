package com.example.usr.app5_6;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListFragment extends Fragment {
    private ListView listView;
    private ArrayList<String> strs;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // res/layout/fragment2.xml 레이아웃을 현재 프래그먼트 UI로 생성
        View view = inflater.inflate(R.layout.fragment2, container, false);

        //이 레이아웃에 정의한 리스트 뷰 객체 변환하여 현재 클래스에서 사용할 수 있게 함
        listView = (ListView) view.findViewById(R.id.listView);

        //ArrayList 생성하여 샘플 문자열 저장
        strs = new ArrayList<String>();
        strs.add("aaa");
        strs.add("bbb");
        strs.add("ccc");
        strs.add("ddd");

        //리스트 뷰가 사용할 어댑터 생성
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strs);

        //리스트 뷰에 어댑터 설정
        listView.setAdapter(adapter);

        //리스트 뷰에서 한 항목을 선택했을 때 발생하는 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //선택한 항목의 문자열을 InfoFragment의 텍스트 뷰에 출력
                InfoFragment.text.setText(strs.get(position));
            }
        });

        //생성한 뷰 리턴
        return view;
    }
}
