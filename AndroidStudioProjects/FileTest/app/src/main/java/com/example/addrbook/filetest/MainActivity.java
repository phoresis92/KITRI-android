package com.example.addrbook.filetest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

    }

    public void onRead(View view){

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
/*        Map map = sharedPreferences.getAll();
        Iterator it = map.keySet().iterator();
        String result = "";
        while(it.hasNext()){
            String key = (String) it.next();
            String val = (String) map.get(key);
            result += key + " : " + val + "\n";
        }*/

        String str = null;
        str+="str: "+sharedPreferences.getString("str","");
        str+="\nbool: "+sharedPreferences.getBoolean("bool",false);
        str+="\nint: "+sharedPreferences.getInt("int",0);
        textView.setText(str);


    }

    public void onWrite(View view){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("str", "aaa");
        editor.putBoolean("bool", true);
        editor.putInt("int", 123);
        editor.commit();


    }

}
