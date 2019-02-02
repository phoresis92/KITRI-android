package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {

    private EditText et;
    private EditText tempText;
    private double temp = 0;
    private double operand1 ;
    private double operand2 ;
    private String value ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        et = (EditText) findViewById(R.id.calNum);
        tempText = (EditText) findViewById(R.id.tempText);
    }

    public void onNum(View view){
        Button b = (Button) view;
        insert(b.getText().toString());
    }

    public void btn0(View view){
        if(!et.getText().toString().trim().equals("0")){
            et.append("0");
            tempText.append("0");
        }else {
            return;
        }
    }

    public void insert(String num){
        if(et.getText().toString().trim().equals("0")){
            et.setText(num);
            tempText.setText(num);
        }else{
            et.append(num);
            tempText.append(num);
        }
    }

    public void Equal(View view){

        operand2 = Double.parseDouble(et.getText().toString());
        double result = 0;
        switch(value){
            case "+":
                result = operand1+operand2;
                break;
            case "-":
                result = operand1-operand2;
                break;
            case "*":
                result = operand1*operand2;
                break;
            case "/":
                if(operand2 == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없음", Toast.LENGTH_SHORT).show();
                    clear(null);
                    return;
                }
                result = operand1/operand2;
                break;
        }
        et.setText(result+"");
    }

    public void clear(View view){
        et.setText("0");
        temp = 0;
        operand1=operand2=0;
    }

    public void onOp(View view){

        operand1 = Double.parseDouble(et.getText().toString());
        value = ((Button) view).getText().toString();
        et.setText("");

    }

/*    public void plus(View view){

        value = "plus";

        operand1 += Double.parseDouble(et.getText().toString());
        et.setText("");
    }

    public void minus(View view){
        value = "minus";
        if(operand1 == 0){
            operand1 = Double.parseDouble(et.getText().toString());
            et.setText("");
        }else{
            operand1 -= Double.parseDouble(et.getText().toString());
            et.setText("");
        }

    }

    public void multiple(View view){
        value = "multiple";
        operand1 *= Double.parseDouble(et.getText().toString());
        et.setText("");
    }

    public void divide(View view){
        value = "divide";
        operand1 /= Double.parseDouble(et.getText().toString());
        et.setText("");
}*/


}
