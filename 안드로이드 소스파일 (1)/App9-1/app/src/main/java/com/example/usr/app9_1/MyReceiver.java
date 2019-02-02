package com.example.usr.app9_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //����Ʈ�� ����� �߰� ������ �д´�.
        String name = intent.getStringExtra("name");

        //�佺Ʈ�� �޽��� ���
        Toast.makeText(context, "hi  "+name+"~~\nfrom receiver", Toast.LENGTH_SHORT).show();
    }
}
