package com.example.usr.app6_3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {
    private ImageView imageView;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //�� ���� main.xml�� ������ ���ǳ��� ���۷��� �Ҵ�
        spinner = (Spinner) findViewById(R.id.spinner);

        //�̹��� �� ����
        imageView = (ImageView) findViewById(R.id.imageView);

        //���ǳ� �׸����� ����� �ؽ�Ʈ ����
        final String[] sizes = {"300 x 300", "400 x 400", "500 x 500"};

        //���ǳʿ��� ����� ����� ����
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sizes);

        //drop down view ����. param : drop down view�� ����� �� ���ҽ�
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        //���ǳʿ� ������ ����� ����
        spinner.setAdapter(adapter);

        //���ҽ� img1�� ��Ʈ������ ����
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);

        //����� ���� �׸��� �ϳ��� �����ϸ� ȣ��Ǵ� �ݹ� �޼��� ���
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {

                //������ ���ǳ� �׸��� �ؽ�Ʈ ����
                String name = sizes[position];
                Bitmap bitmap2 = null;

                switch (name) {
                    case "300 x 300":
                        //������ ��Ʈ���� ũ�⸦ 300 x 300���� ��ȯ
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                        break;
                    case "400 x 400":
                        //������ ��Ʈ���� ũ�⸦ 400 x 400���� ��ȯ
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
                        break;
                    case "500 x 500":
                        //������ ��Ʈ���� ũ�⸦ 500 x 500���� ��ȯ
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
                        break;
                }

                //�̹����信 ũ�⸦ ��ȯ�� ��Ʈ�� bitmap2�� ���
                imageView.setImageBitmap(bitmap2);
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
