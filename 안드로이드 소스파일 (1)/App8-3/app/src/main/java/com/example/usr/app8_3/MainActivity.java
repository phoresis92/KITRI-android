package com.example.usr.app8_3;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog();
    }
    void showDialog() {
        //���̾�α� �����׸�Ʈ ����
        DialogFragment newFragment = MyDialogFragment.newInstance("favorite color?");

        //���̾�α� ����
        newFragment.show(getFragmentManager(), "dialog");
    }
    //���̾�α��� ������ư Ŭ���� ȣ��
    public void doPositiveClick(String color) {
        //���̾�α׿��� ������ ���� ���
        Toast.makeText(this, color, Toast.LENGTH_SHORT).show();
    }

    //���̾�α��� ���� ��ư Ŭ���� ȣ��
    public void doNegativeClick() {
        //�佺Ʈ�� ��ҵǾ����� �˸�
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }
}
