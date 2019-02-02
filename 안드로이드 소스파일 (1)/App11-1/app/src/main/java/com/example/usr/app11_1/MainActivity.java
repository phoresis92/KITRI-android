package com.example.usr.app11_1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends Activity {
    private EditText name;
    private EditText tel;
    private String name_str;
    private String tel_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main.xml�� ��ġ�� �� �������� ��ü�� �ڹ� �ڵ忡�� ������ �� �ֵ���
        //���۷��� ����
        name=(EditText)findViewById(R.id.editText);
        tel=(EditText)findViewById(R.id.editText2);

    }

    //����Ʈ �ؽ�Ʈ�� Ŭ���ϸ� ȣ��
   public void clear(View view){
       //����Ʈ �ؽ�Ʈ�� �ؽ�Ʈ �ʱ�ȭ
       ((EditText)view).setText("");
   }

    //onPause()�޼���� ��Ƽ��Ƽ�� �ٸ� ��Ƽ��Ƽ�� ���� �������� ȣ��ȴ�.
    @Override
    protected void onPause() {
        super.onPause();

        //�����۷����� �� ���ø����̼ǿ����� ����ϵ��� private ���� ����
        SharedPreferences myPref = getPreferences(MODE_PRIVATE);

        //�����۷����� ���⸦ �ϱ� ���� Editor ��ü ����
        SharedPreferences.Editor editor = myPref.edit();

        //�̸��� �Է��ϴ� ����Ʈ �ؽ�Ʈ�� �Էµ� ���� String ��ü name_str�� ����
        name_str=name.getText().toString();

        //name_str ���� �����۷����� 'name'Ű�� ����
        editor.putString("name", name_str);

        //��ȭ��ȣ�� �Է��ϴ� ����Ʈ �ؽ�Ʈ�� �Էµ� ���� String ��ü tel_str�� ����
        tel_str=tel.getText().toString();

        //tel_str ���� �����۷����� 'tel'Ű�� ����
        editor.putString("tel", tel_str);

        //�����۷����� ���� Ŀ��
        editor.commit();
    }

    //onResume()�� ��Ƽ��Ƽ�� ȭ�鿡 �㶧 ȣ��Ǵ� �޼���
    @Override
    protected void onResume() {
        super.onResume();

        //�����۷��� private ���� ����
        SharedPreferences myPref = getPreferences(MODE_PRIVATE);

        //�����۷����� 'name'Ű�� ������ �� ���� �о� �̸��� �Է��ϴ� ����Ʈ
        //�ؽ�Ʈ�� ���
        if(myPref.contains("name")){
            name.setText(myPref.getString("name", ""));
        }

        //�����۷����� 'tel'Ű�� ������ �� ���� �о� ��ȭ��ȣ�� �Է��ϴ� ����Ʈ
        //�ؽ�Ʈ�� ���
        if(myPref.contains("tel")){
            tel.setText(myPref.getString("tel", ""));
        }

        //getAll()�޼���� �����۷����� ��� ������ �о�� �ʿ� ����
        Map m=myPref.getAll();

        //�ʿ��� ����� ���ͷ����� ����
        Iterator it=m.keySet().iterator();

        //���� ������ ������ŭ ���� �� �׸��� Ű�� ���� �α�Ĺ�� ���
        while(it.hasNext()){
            String str=(String)it.next();
            Log.i(str, (String) m.get(str));
        }
    }

}
