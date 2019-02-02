package com.example.usr.app7_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText name;
    private EditText tel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name = (EditText) findViewById(R.id.editText3);
        tel = (EditText) findViewById(R.id.editText4);

        //�� ��Ƽ��Ƽ�� Ȱ��ȭ�ϱ� ���� ������ ����Ʈ ��ȯ
        intent = getIntent();

        //����Ʈ�� ����� "member"��� �̸��� Member ��ü�� �д´�.
        Member m = (Member) intent.getSerializableExtra("member");

        //���� ��ü�� ���� �ƴϸ� ����Ʈ �ؽ�Ʈ�� �̸��� ��ȭ��ȣ ���
        if(m != null){
            name.setText(m.getName());
            tel.setText(m.getTel());
        }
    }

    //save��ư�� Ŭ���ϸ� ȣ���
    public void save(View view){

        //������ �̸�, ��ȭ��ȣ�� Member ��ü�� ����
        Member m = new Member();
        m.setName(name.getText().toString());
        m.setTel(tel.getText().toString());

        //������ Member ��ü m�� ����Ʈ�� ����
        intent.putExtra("member", m);

        //���� ��Ƽ��Ƽ�� ����ڵ� ����
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
