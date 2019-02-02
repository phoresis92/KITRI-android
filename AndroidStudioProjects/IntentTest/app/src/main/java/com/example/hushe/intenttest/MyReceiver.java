package com.example.hushe.intenttest;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int x = intent.getIntExtra("x", 0);
        int y = intent.getIntExtra("y", 0);
        Toast.makeText(context, "x:"+x+", y:"+y, Toast.LENGTH_SHORT).show();



    }
}
