package com.example.usr.app8_1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogFragment extends DialogFragment {
    // 초기화 파라메터의 키이름으로 사용할 상수 정의
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 초기화 파라메터 값을 저장할 변수 선언
    private String mParam1;
    private String mParam2;

    public static MyDialogFragment newInstance(String param1, String param2) {
        //다이얼로그 프래그먼트 객체 생성
        MyDialogFragment fragment = new MyDialogFragment();

        //키와 값을 저장할 번들 객체 생성
        Bundle args = new Bundle();

        //초기화 파라메터 저장
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        //다이얼로그 프래그먼트 객체에 아규먼트 설정
        fragment.setArguments(args);
        return fragment;
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    //다이얼로그 프래그먼트 객체가 생성될 때 호출
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //프래그먼트의 아규먼트가 널이 아니면
        if (getArguments() != null) {

            //아규먼트 값을 읽어 멤버변수 mParam1, mParam2에 저장
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //다이얼로그 창의 레이아웃 파일을 뷰 객체로 변환
        View v =  inflater.inflate(R.layout.fragment_my_dialog, container, false);

        //레이아웃 파일의 텍스트 뷰 객체 읽어옴
        TextView textView = (TextView) v.findViewById(R.id.textView);

        //텍스트 뷰에 부모 액티비티에서 받아온 아규먼트 값인 이름과 나이를 출력
        textView.setText("name:"+mParam1+", age"+mParam2);

        //레이아웃 파일의 버튼 객체 읽어옴
        Button btn = (Button) v.findViewById(R.id.ok);

        //버튼의 클릭 이벤트 처리
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼이 클릭되면 현재 다이얼로그를 닫는다.
                MyDialogFragment.this.getDialog().cancel();
            }
        });

        //생성한 뷰를 리턴
        return v;
    }

}
