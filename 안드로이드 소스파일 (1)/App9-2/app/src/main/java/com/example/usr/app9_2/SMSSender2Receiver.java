package com.example.usr.app9_2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSSender2Receiver extends BroadcastReceiver {
    public SMSSender2Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // sms 수신결과 토스트로 출력
        int result = getResultCode();
        switch (result) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "receive : success", Toast.LENGTH_SHORT).show();
                break;
            case Activity.RESULT_CANCELED:
                Toast.makeText(context, "receive : fail", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
