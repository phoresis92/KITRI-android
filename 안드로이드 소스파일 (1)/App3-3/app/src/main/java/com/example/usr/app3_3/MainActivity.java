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

        //결과를 출력할 에디트 텍스트 객체 가져옴
        result = (EditText)findViewById(R.id.editText);

        //버튼들을 저장할 배열 생성
        btns = new Button[16];

        //테이블 레이아웃의 줄(TableRow)을 저장할 배열 생성
        trs = new TableRow[4];

        int i;

        //TableRow의 id들을 배열에 저장
        Integer[] ids = {R.id.tr1, R.id.tr2, R.id.tr3, R.id.tr4};

        //TableRow를 java에서 사용하기 위해 레이아웃 파일의 TableRow 객체를 가져와 배열에 저장
        for(i=0;i<ids.length;i++){
            trs[i] = (TableRow)findViewById(ids[i]);
        }

        //버튼 텍스트로 사용할 문자열들
        String[] btnText = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", "C", "=", "/"};

        //숫자 버튼을 클릭했을때 사용할 클릭 리스너 생성
        OnClickListener numClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = result.getText().toString();
                result.setText(str+((Button)v).getText());
            }
        };

        //연산자 버튼을 클릭했을때 사용할 클릭 리스너 생성
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

        //버튼 객체들을 생성하여 배열에 저장 및 적합한 클릭 리스너 설정
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

        //C(클리어)버튼 클릭 이벤트 처리
        btns[13].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                num1=0;
                num2=0;
                op="";
            }
        });

        // =버튼 클릭 이벤트 처리
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
                            Toast.makeText(getApplicationContext(), "0으로 나눌수 없음", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        r = num1 / num2;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "잘못된 연산자", Toast.LENGTH_SHORT).show();
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
