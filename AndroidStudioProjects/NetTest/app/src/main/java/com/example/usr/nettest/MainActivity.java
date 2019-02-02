package com.example.usr.nettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText goTo;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goTo = (EditText) findViewById(R.id.goTo);
        web = (WebView) findViewById(R.id.web);



    }

    public void onGo(View v){

        String urlStr = goTo.getText().toString();
        web.loadUrl(urlStr);

    }

}
