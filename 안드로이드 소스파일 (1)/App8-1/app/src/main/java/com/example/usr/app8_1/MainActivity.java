package com.example.usr.app8_1;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText name;
    private EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        age = (EditText) findViewById(R.id.editText2);
    }

    //CONFIRM ��ư�� Ŭ���ϸ� ȣ��
    public void showDialog(View view){
        //����ڰ� �Է��� �̸�, ���̸� EditText�� ���� �д´�.
        String str1 = name.getText().toString();
        String str2 = age.getText().toString();

        //�����׸�Ʈ�� Ʈ����� ����
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        //�±׸��� dialog�� �����׸�Ʈ ��ü�� �о��
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");

        //�����׸�Ʈ�� ���� �ƴϸ� �����׸�Ʈ�� ����
        if (prev != null) {
            ft.remove(prev);
        }

        //��Ű�� �������� �̵��� �����׸�Ʈ�� �η� ����
        ft.addToBackStack(null);

        //�� ���̾�α� �����׸�Ʈ �����Ѵ�.
        //�Ķ���ͷ� ����ڰ� �Է��� �̸��� ���̸� �����Ѵ�.
        DialogFragment newFragment = MyDialogFragment.newInstance(str1, str2);

        //���̾�α� �����׸�Ʈ�� ȭ�鿡 ���̰� �Ѵ�.
        newFragment.show(ft, "dialog");

        //EditText�� �ʱ�ȭ
        name.setText("");
        age.setText("");
    }

}
