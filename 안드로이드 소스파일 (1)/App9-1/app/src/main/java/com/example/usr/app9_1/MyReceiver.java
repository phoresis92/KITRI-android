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
        //인텐트에 저장된 추가 정보를 읽는다.
        String name = intent.getStringExtra("name");

        //토스트로 메시지 출력
        Toast.makeText(context, "hi  "+name+"~~\nfrom receiver", Toast.LENGTH_SHORT).show();
    }
}
