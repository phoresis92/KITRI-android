package com.example.usr.app6_4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //��Ƽ��Ƽ ��� 24���ο� ���ǵ� View ���� Ŭ���� ��ü�� ����
        setContentView(new CustomDrawableView(getApplicationContext()));
    }

    // ShapeDrawable�� �׸� View ���� Ŭ���� ����
    public class CustomDrawableView extends View {
        private ShapeDrawable mDrawableOval;
        private ShapeDrawable mDrawableRect;
        private ShapeDrawable mDrawableArc;

        public CustomDrawableView(Context context) {
            super(context);

            int x = 100;
            int y = 500;
            int width = 300;
            int height = 300;

            // Ÿ���� �׸��� ShapeDrawable ��ü ����
            mDrawableOval = new ShapeDrawable(new OvalShape());

            // ���� ����
            mDrawableOval.getPaint().setColor(0xff74AC23);

            // ������ ��ġ�� ����, ���� ���� ����
            mDrawableOval.setBounds(x, y, x + width, y + height);

            x = 10;
            y = 120;
            width = 300;
            height = 300;

            // ��ȣ�� �׸��� ShapeDrawable ��ü ����
            mDrawableArc = new ShapeDrawable(new ArcShape(10, 280));

            // ���� ����
            mDrawableArc.getPaint().setColor(0xff74AC23);

            // ������ ��ġ�� ����, ���� ���� ����
            mDrawableArc.setBounds(x, y, x + width, y + height);

            x = 350;

            // �簢���� �׸��� ShapeDrawable ��ü ����
            mDrawableRect = new ShapeDrawable(new RectShape());

            // ���� ����
            mDrawableRect.getPaint().setColor(0xff74AC23);

            // ������ ��ġ�� ����, ���� ���� ����
            mDrawableRect.setBounds(x, y, x + width, y + height);

        }

        protected void onDraw(Canvas canvas) {

            // �ؽ�Ʈ ��¿� ����� Paint ��ü ����
            Paint textPaint = new Paint();

            // Paint ��ü�� ���� ����
            textPaint.setColor(0xff893456);

            // �ؽ�Ʈ ũ�� ����
            textPaint.setTextSize(60);

            // �ؽ�Ʈ ���
            canvas.drawText("2D Draw Test", 10, 80, textPaint);

            // ShapeDrawable ��ü�� ������ �׸���
            mDrawableOval.draw(canvas);
            mDrawableRect.draw(canvas);
            mDrawableArc.draw(canvas);
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
