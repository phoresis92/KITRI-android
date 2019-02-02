package com.example.usr.app7_5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void editText(View view){

        //EditActivity�� ��������� Ȱ��ȭ�� ����Ʈ ����
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        //����Ʈ�� �߰� ������ �ؽ�Ʈ ���� �ؽ�Ʈ�� �����Ѵ�. Ű�̸��� "text"�� �Ѵ�.
        intent.putExtra("text", textView.getText().toString());

        //��Ƽ��Ƽ Ȱ��ȭ. ��û�ڵ�=1
        startActivityForResult(intent, 1);
    }

    //�ٸ� ��Ƽ��Ƽ�� �湮�ϰ� �ǵ��ƿ��� �ڵ� ȣ���
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //��û�ڵ� ��
        switch (requestCode){
            //��û�ڵ尡 1�̰� ����ڵ尡 RESULT_OK�̸�
            // ����Ʈ���� Ű�̸��� "text"�� ���� ���� �ؽ�Ʈ �信 ���
            case 1:
                if(resultCode == RESULT_OK){
                    String str = data.getStringExtra("text");
                    textView.setText(str);
                }
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
