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
        //���̾�α� �����׸�Ʈ ��ü ����
        MyDialogFragment fragment = new MyDialogFragment();

        //Ű�� ���� ������ ���� ��ü ����
        Bundle args = new Bundle();

        //�ʱ�ȭ �Ķ���� ����
        args.putString(ARG_PARAM1, param1);

        //���̾�α� �����׸�Ʈ ��ü�� �ƱԸ�Ʈ ����
        fragment.setArguments(args);

        //������ �����׸�Ʈ ����
        return fragment;
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //�����׸�Ʈ�� �ƱԸ�Ʈ�� �о� ���� mParam1�� ����
        mParam1 = getArguments().getString(ARG_PARAM1);

        //Builder�� �̿��� �˷�Ʈ ���̾�α��� ���¸� ����
        return new AlertDialog.Builder(getActivity())
                //������ ����
                .setIcon(R.mipmap.ic_launcher)
                //Ÿ��Ʋ ����
                .setTitle(mParam1)
                //������ư ����. Ÿ��Ʋ�� "ok"
                //Ŭ���ϸ� ���� ��Ƽ��Ƽ�� doPositiveClick()ȣ��
                .setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).doPositiveClick();
                            }
                        }
                )
                //������ư ����. Ÿ��Ʋ�� "cancel"
                //Ŭ���ϸ� ���� ��Ƽ��Ƽ�� doNegativeClick() ȣ��
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).doNegativeClick();
                            }
                        }
                )
                //������ ������ ���·� ���̾�α� ����
                .create();
    }

}