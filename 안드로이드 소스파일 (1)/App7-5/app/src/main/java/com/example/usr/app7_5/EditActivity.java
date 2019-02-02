package com.example.usr.app7_5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = (EditText) findViewById(R.id.editText);

        //�� ��Ƽ��Ƽ�� Ȱ��ȭ�ϱ� ���� ������ ����Ʈ�� �����Ѵ�.
        intent = getIntent();

        //����Ʈ�� ����� �߰� ���� ��
        // �̸��� "text"�� ���� ���� ���� str�� ����
        String str = intent.getStringExtra("text");

        //str ���� ����Ʈ �ؽ�Ʈ�� ���
        editText.setText(str);
    }

    //��ư Ŭ�� �� ȣ���
    public void save(View view){

        //����Ʈ �ؽ�Ʈ�� ����ڰ� ������ �ؽ�Ʈ�� ���� str�� ����
        String str = editText.getText().toString();

        //str ���� ����Ʈ�� �߰� ������ ����. Ű �̸��� "text"�� �Ѵ�.
        intent.putExtra("text", str);

        //�� ��Ƽ��Ƽ�� �۾� ����ڵ带 �ۼ��Ѵ�.
        setResult(RESULT_OK, intent);

        //���� ��Ƽ��Ƽ ����
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
