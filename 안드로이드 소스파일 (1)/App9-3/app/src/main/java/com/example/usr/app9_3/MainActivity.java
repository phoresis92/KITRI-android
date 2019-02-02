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

        //��ε�ĳ��Ʈ ���ù� ��ü ����
        receiver = new MyReceiver();

        //�׼Ǹ��� �Ķ���ͷ� �����Ͽ� ����Ʈ ���� ����
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        //������ ���ù��� ����Ʈ ���� ���
        registerReceiver(receiver, filter);
    }

    void showDialog(String sms) {
        //���̾�α� �����׸�Ʈ ����
        DialogFragment newFragment = MyDialogFragment.newInstance(sms);

        //���̾�α� ����
        newFragment.show(getFragmentManager(), "dialog");
    }

    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Intent�� extra������ Bundle ��ü�� ����
            Bundle bundle = intent.getExtras();

            String msg;
            String sender;
            int i;

            if (bundle != null) {

                // Ű�� "pdus"�� �����͸� Bundle���� ���� �迭�� ����
                Object rawData[] = (Object[]) bundle.get("pdus");

                SmsMessage[] sms = new SmsMessage[rawData.length];
                for (i = 0; i < rawData.length; i++) {

                    // raw �����ͷ� �Ǿ��ִ� ���� �޽��� ������ SmsMessage��ü�� ��ȯ��
                    //�� �迭�� ����
                    sms[i] = SmsMessage.createFromPdu((byte[]) rawData[i]);
                }
                for (i = 0; i < sms.length; i++) {

                    // SmsMessage ��ü���� ���� �޽��� �ؽ�Ʈ ���� ����
                    msg = sms[i].getMessageBody();

                    // SmsMessage ��ü���� ���� ����� ��ȣ ���� ����
                    sender = sms[i].getOriginatingAddress();

                    //���̾�α׸� �����ϴ� �޼��� ȣ��
                    showDialog(msg + " from " + sender);
                }
            }
        }
    }
}