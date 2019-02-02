package com.example.usr.app7_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText number;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
    }

    public void phoneCall(View view){
        //에디트 텍스트에 입력한 전화번호를 읽어온다.
        String str = number.getText().toString();

        //묵시적으로 사용할 인텐트를 생성한다. 액션명은 ACTION_CALL이고
        // 이 액션을 수행하는데 필요한 전화번호는 str이다.
        Intent intent = new Intent(Intent.ACTION_CALL,  Uri.parse("tel:"+str));

        //액티비티 활성화
        startActivity(intent);

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
