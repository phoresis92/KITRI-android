package com.example.usr.app7_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final static int A = 1;
    private final static int B = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMyActivity(View view){
        String tag = ((Button)view).getText().toString();
        Intent intent = null;
        int type = 0;
        switch(tag){
            case "A":
                intent = new Intent(getApplicationContext(), ActivityA.class);
                type = A;
                break;
            case "B":
                intent = new Intent(getApplicationContext(), ActivityB.class);
                type = B;
                break;
        }
        startActivityForResult(intent, type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case A:
                Toast.makeText(getApplicationContext(), "return from ActivityA", Toast.LENGTH_SHORT).show();
                break;
            case B:
                Toast.makeText(getApplicationContext(), "return from ActivityB", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
