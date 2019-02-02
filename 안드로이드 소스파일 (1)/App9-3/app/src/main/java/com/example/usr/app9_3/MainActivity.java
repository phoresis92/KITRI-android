package com.example.usr.app9_3;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MainActivity extends Activity {
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //브로드캐스트 리시버 객체 생성
        receiver = new MyReceiver();

        //액션명을 파라메터로 전달하여 인텐트 필터 생성
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        //생성한 리시버에 인텐트 필터 등록
        registerReceiver(receiver, filter);
    }

    void showDialog(String sms) {
        //다이얼로그 프래그먼트 생성
        DialogFragment newFragment = MyDialogFragment.newInstance(sms);

        //다이얼로그 실행
        newFragment.show(getFragmentManager(), "dialog");
    }

    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Intent의 extra정보를 Bundle 객체에 저장
            Bundle bundle = intent.getExtras();

            String msg;
            String sender;
            int i;

            if (bundle != null) {

                // 키가 "pdus"인 데이터를 Bundle에서 꺼내 배열에 저장
                Object rawData[] = (Object[]) bundle.get("pdus");

                SmsMessage[] sms = new SmsMessage[rawData.length];
                for (i = 0; i < rawData.length; i++) {

                    // raw 데이터로 되어있는 문자 메시지 정보를 SmsMessage객체로 변환하
                    //여 배열에 저장
                    sms[i] = SmsMessage.createFromPdu((byte[]) rawData[i]);
                }
                for (i = 0; i < sms.length; i++) {

                    // SmsMessage 객체에서 문자 메시지 텍스트 꺼내 저장
                    msg = sms[i].getMessageBody();

                    // SmsMessage 객체에서 보낸 사람의 번호 꺼내 저장
                    sender = sms[i].getOriginatingAddress();

                    //다이얼로그를 생성하는 메서드 호출
                    showDialog(msg + " from " + sender);
                }
            }
        }
    }
}