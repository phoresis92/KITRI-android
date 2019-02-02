package com.example.usr.app8_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private String color;
    private String mParam1;

    public static MyDialogFragment newInstance(String param1) {
        //다이얼로그 프래그먼트 객체 생성
        MyDialogFragment fragment = new MyDialogFragment();

        //키와 값을 저장할 번들 객체 생성
        Bundle args = new Bundle();

        //초기화 파라메터 저장
        args.putString(ARG_PARAM1, param1);

        //다이얼로그 프래그먼트 객체에 아규먼트 설정
        fragment.setArguments(args);

        //생성한 프래그먼트 리턴
        return fragment;
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //프래그먼트의 아규먼트을 읽어 변수 mParam1에 저장
        mParam1 = getArguments().getString(ARG_PARAM1);

        //다이얼로그에 출력할 항목들 저장
        final CharSequence[] colors = {"red", "green", "blue"};

        //다이얼로그 형태를 설정할 빌더 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 타이틀 설정
        builder.setTitle(mParam1)

                // 라디오버튼 목록을 설정
                .setSingleChoiceItems(colors, 0, new DialogInterface.OnClickListener() {
                    //목록 중 하나를 선택하면 실행됨
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //선택한 색상을 멤버변수 color에 저장
                        //which는 선택한 위치 값
                        color = (String)colors[which];
                    }
                })

                //긍정버튼 설정
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //긍정버튼 클릭하면 메인 액티비티의 doPositiveClick() 호출
                        ((MainActivity) getActivity()).doPositiveClick(color);
                    }
                })

                //부정버튼 설정
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //부정버튼 클릭하면 메인 액티비티의 doNegativeClick() 호출
                        ((MainActivity) getActivity()).doNegativeClick();
                    }
                });

        //빌더로 설정한 형태로 다이얼로그 생성
        return builder.create();

    }

}
