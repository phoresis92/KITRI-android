package com.example.usr.app8_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";

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

        //Builder를 이용해 알러트 다이얼로그의 형태를 설정
        return new AlertDialog.Builder(getActivity())
                //아이콘 설정
                .setIcon(R.mipmap.ic_launcher)
                //타이틀 설정
                .setTitle(mParam1)
                //긍정버튼 설정. 타이틀은 "ok"
                //클릭하면 메인 액티비티의 doPositiveClick()호출
                .setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).doPositiveClick();
                            }
                        }
                )
                //부정버튼 설정. 타이틀은 "cancel"
                //클릭하면 메인 액티비티의 doNegativeClick() 호출
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).doNegativeClick();
                            }
                        }
                )
                //위에서 설정한 형태로 다이얼로그 생성
                .create();
    }

}