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

        // res/layout/fragment2.xml ���̾ƿ��� ���� �����׸�Ʈ UI�� ����
        View view = inflater.inflate(R.layout.fragment2, container, false);

        //�� ���̾ƿ��� ������ ����Ʈ �� ��ü ��ȯ�Ͽ� ���� Ŭ�������� ����� �� �ְ� ��
        listView = (ListView) view.findViewById(R.id.listView);

        //ArrayList �����Ͽ� ���� ���ڿ� ����
        strs = new ArrayList<String>();
        strs.add("aaa");
        strs.add("bbb");
        strs.add("ccc");
        strs.add("ddd");

        //����Ʈ �䰡 ����� ����� ����
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strs);

        //����Ʈ �信 ����� ����
        listView.setAdapter(adapter);

        //����Ʈ �信�� �� �׸��� �������� �� �߻��ϴ� �̺�Ʈ ó��
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //������ �׸��� ���ڿ��� InfoFragment�� �ؽ�Ʈ �信 ���
                InfoFragment.text.setText(strs.get(position));
            }
        });

        //������ �� ����
        return view;
    }
}
