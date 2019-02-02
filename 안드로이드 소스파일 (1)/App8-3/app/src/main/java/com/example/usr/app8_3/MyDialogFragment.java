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

        //���̾�α׿� ����� �׸�� ����
        final CharSequence[] colors = {"red", "green", "blue"};

        //���̾�α� ���¸� ������ ���� ����
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Ÿ��Ʋ ����
        builder.setTitle(mParam1)

                // ������ư ����� ����
                .setSingleChoiceItems(colors, 0, new DialogInterface.OnClickListener() {
                    //��� �� �ϳ��� �����ϸ� �����
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //������ ������ ������� color�� ����
                        //which�� ������ ��ġ ��
                        color = (String)colors[which];
                    }
                })

                //������ư ����
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //������ư Ŭ���ϸ� ���� ��Ƽ��Ƽ�� doPositiveClick() ȣ��
                        ((MainActivity) getActivity()).doPositiveClick(color);
                    }
                })

                //������ư ����
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //������ư Ŭ���ϸ� ���� ��Ƽ��Ƽ�� doNegativeClick() ȣ��
                        ((MainActivity) getActivity()).doNegativeClick();
                    }
                });

        //������ ������ ���·� ���̾�α� ����
        return builder.create();

    }

}
