package com.example.usr.app11_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // main.xml�� ������ �������� �ڹ� �ڵ忡�� ����� �� �ֵ��� ���۷��� ȹ��
        et = (EditText) findViewById(R.id.editText2);

        //���޵� ����Ʈ�� ����� 'name'Ű�� �����͸� �о� ����Ʈ �ؽ�Ʈ�� ���
        Intent intent=getIntent();
        et.setText(intent.getStringExtra("name"));

    }

    public void edit(View view){
        //����ڰ� ����Ʈ �ؽ�Ʈ�� �Է��� �����͸� name ������ ����
        String name=et.getText().toString();

        //name�� ���鹮�ڰ� �ƴϸ�
        if(!name.equals("")) {

            //���� ��Ƽ��Ƽ�� ���ư� �� ������ �����͸� ������ ����Ʈ ����
            Intent data = new Intent();

            //������ ����Ʈ�� ������ �̸� ����
            data.putExtra("name", name);

            //����Ʈ�� ��� �ڵ� ����
            setResult(RESULT_OK, data);

            //�� ��Ƽ��Ƽ ����. ���� ��Ƽ��Ƽ�� �̵�
            finish();
        }
    }
}
