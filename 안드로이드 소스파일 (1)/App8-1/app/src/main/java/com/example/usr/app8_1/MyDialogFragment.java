package com.example.usr.app8_1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogFragment extends DialogFragment {
    // �ʱ�ȭ �Ķ������ Ű�̸����� ����� ��� ����
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // �ʱ�ȭ �Ķ���� ���� ������ ���� ����
    private String mParam1;
    private String mParam2;

    public static MyDialogFragment newInstance(String param1, String param2) {
        //���̾�α� �����׸�Ʈ ��ü ����
        MyDialogFragment fragment = new MyDialogFragment();

        //Ű�� ���� ������ ���� ��ü ����
        Bundle args = new Bundle();

        //�ʱ�ȭ �Ķ���� ����
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        //���̾�α� �����׸�Ʈ ��ü�� �ƱԸ�Ʈ ����
        fragment.setArguments(args);
        return fragment;
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    //���̾�α� �����׸�Ʈ ��ü�� ������ �� ȣ��
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //�����׸�Ʈ�� �ƱԸ�Ʈ�� ���� �ƴϸ�
        if (getArguments() != null) {

            //�ƱԸ�Ʈ ���� �о� ������� mParam1, mParam2�� ����
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //���̾�α� â�� ���̾ƿ� ������ �� ��ü�� ��ȯ
        View v =  inflater.inflate(R.layout.fragment_my_dialog, container, false);

        //���̾ƿ� ������ �ؽ�Ʈ �� ��ü �о��
        TextView textView = (TextView) v.findViewById(R.id.textView);

        //�ؽ�Ʈ �信 �θ� ��Ƽ��Ƽ���� �޾ƿ� �ƱԸ�Ʈ ���� �̸��� ���̸� ���
        textView.setText("name:"+mParam1+", age"+mParam2);

        //���̾ƿ� ������ ��ư ��ü �о��
        Button btn = (Button) v.findViewById(R.id.ok);

        //��ư�� Ŭ�� �̺�Ʈ ó��
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ư�� Ŭ���Ǹ� ���� ���̾�α׸� �ݴ´�.
                MyDialogFragment.this.getDialog().cancel();
            }
        });

        //������ �並 ����
        return v;
    }

}
