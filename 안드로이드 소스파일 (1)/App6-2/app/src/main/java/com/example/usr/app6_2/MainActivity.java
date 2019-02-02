package com.example.usr.app6_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {
    private ImageView iv;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �̹��� �� ���۷��� �Ҵ�
        iv = (ImageView) findViewById(R.id.imageView);

        //�� ���� main.xml�� ������ ���ǳ��� ���۷��� �Ҵ�
        spinner = (Spinner) findViewById(R.id.spinner);

        final String[] imgNames = {"img1", "img2", "img3"};

        //���ǳʿ��� ����� ����� ����
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, imgNames);

        //drop down view ����. param : drop down view�� ����� �� ���ҽ�
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //���ǳʿ� ������ ����� ����
        spinner.setAdapter(adapter);

        //����� ���� �׸��� �ϳ��� �����ϸ� ȣ��Ǵ� �ݹ� �޼��� ���
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = imgNames[position];

                switch (name) {
                    case "img1":
                        // res/drawable/img.jpg �̹����� �̹��� �信 ����.
                        // �� ������ ���� drawable ���丮�� ����Ǿ� �־�� ��.
                        iv.setImageResource(R.drawable.img1);
                        break;
                    case "img2":
                        iv.setImageResource(R.drawable.img2);
                        break;
                    case "img3":
                        iv.setImageResource(R.drawable.img3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
