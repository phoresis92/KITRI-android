package com.example.usr.app8_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //�佺Ʈ ��� �Լ�
    public void showToast(View view) {

        //LayoutInflater ��ü�� �ý������κ��� �޾ƿ�
        LayoutInflater inflater = getLayoutInflater();

        //LayoutInflater ��ü�� inflate �޼���� �佺Ʈ UI XML������ View ��ü�� ��ȯ
        View layout = inflater.inflate(R.layout.toastmain, null);

        //�̹��� �� ���۷��� ����
        ImageView image = (ImageView) layout.findViewById(R.id.imageView);

        //�̹��� �信 ����� �̹��� ���ҽ� ����
        image.setImageResource(R.mipmap.ic_launcher);

        //�ؽ�Ʈ �� ���۷��� ����
        TextView text = (TextView) layout.findViewById(R.id.textView2);

        //�ؽ�Ʈ �信 ����� �ؽ�Ʈ ����
        text.setText("hello toast!");

        //�佺Ʈ ��ü ����
        Toast toast = new Toast(getApplicationContext());

        //�佺Ʈ ��� ��ġ ���� ���� �߾ӿ� ���
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        //�佺Ʈ ��� �ð� ����
        toast.setDuration(Toast.LENGTH_LONG);

        //�佺Ʈ�� �並 UI XML ���Ϸ� ������ ��� ����
        toast.setView(layout);

        //�佺Ʈ ���
        toast.show();
    }

}
