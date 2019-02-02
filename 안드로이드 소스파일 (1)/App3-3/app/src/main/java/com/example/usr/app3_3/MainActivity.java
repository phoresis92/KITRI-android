package com.example.usr.app3_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button[] btns;
    private TableRow[] trs;
    private EditText result;
    private int num1;
    private int num2;
    private String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //����� ����� ����Ʈ �ؽ�Ʈ ��ü ������
        result = (EditText)findViewById(R.id.editText);

        //��ư���� ������ �迭 ����
        btns = new Button[16];

        //���̺� ���̾ƿ��� ��(TableRow)�� ������ �迭 ����
        trs = new TableRow[4];

        int i;

        //TableRow�� id���� �迭�� ����
        Integer[] ids = {R.id.tr1, R.id.tr2, R.id.tr3, R.id.tr4};

        //TableRow�� java���� ����ϱ� ���� ���̾ƿ� ������ TableRow ��ü�� ������ �迭�� ����
        for(i=0;i<ids.length;i++){
            trs[i] = (TableRow)findViewById(ids[i]);
        }

        //��ư �ؽ�Ʈ�� ����� ���ڿ���
        String[] btnText = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", "C", "=", "/"};

        //���� ��ư�� Ŭ�������� ����� Ŭ�� ������ ����
        OnClickListener numClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = result.getText().toString();
                result.setText(str+((Button)v).getText());
            }
        };

        //������ ��ư�� Ŭ�������� ����� Ŭ�� ������ ����
        OnClickListener opClick = new OnClickListener(){
            @Override
            public void onClick(View v) {
                String str = result.getText().toString();
                if(str.equals("")){
                    str = "0";
                }
                num1 = Integer.parseInt(str);
                result.setText("");
                op = ((Button)v).getText().toString();
            }
        };

        //��ư ��ü���� �����Ͽ� �迭�� ���� �� ������ Ŭ�� ������ ����
        for(i=0;i<btns.length;i++){
            btns[i] = new Button(getApplicationContext());
            btns[i].setWidth(20);
            btns[i].setText(btnText[i]);
            trs[i / 4].addView(btns[i]);
            if(i!=13 && i!=14){
                if(i==3 || i==7 || i==11 || i==15){
                    btns[i].setOnClickListener(opClick);
                }else{
                    btns[i].setOnClickListener(numClick);
                }
            }

        }

        //C(Ŭ����)��ư Ŭ�� �̺�Ʈ ó��
        btns[13].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                num1=0;
                num2=0;
                op="";
            }
        });

        // =��ư Ŭ�� �̺�Ʈ ó��
        btns[14].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int r = 0;
                String str = result.getText().toString();
                if(str.equals("")){
                    str = "0";
                }
                num2 = Integer.parseInt(str);

                switch(op) {
                    case "+":
                        r = num1 + num2;
                        break;
                    case "-":
                        r = num1 - num2;
                        break;
                    case "*":
                        r = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            Toast.makeText(getApplicationContext(), "0���� ������ ����", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        r = num1 / num2;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "�߸��� ������", Toast.LENGTH_SHORT).show();
                        break;
                }
                result.setText(r+"");
                num1 = r;
                num2 = 0;
                op = "";
            }
        });
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
