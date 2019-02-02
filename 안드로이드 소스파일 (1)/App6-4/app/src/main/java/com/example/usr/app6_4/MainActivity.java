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
        //액티비티 뷰로 24라인에 정의된 View 하위 클래스 객체로 지정
        setContentView(new CustomDrawableView(getApplicationContext()));
    }

    // ShapeDrawable를 그릴 View 하위 클래스 정의
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

            // 타원을 그리는 ShapeDrawable 객체 생성
            mDrawableOval = new ShapeDrawable(new OvalShape());

            // 색상 설정
            mDrawableOval.getPaint().setColor(0xff74AC23);

            // 도형의 위치와 가로, 세로 길이 지정
            mDrawableOval.setBounds(x, y, x + width, y + height);

            x = 10;
            y = 120;
            width = 300;
            height = 300;

            // 원호를 그리는 ShapeDrawable 객체 생성
            mDrawableArc = new ShapeDrawable(new ArcShape(10, 280));

            // 색상 설정
            mDrawableArc.getPaint().setColor(0xff74AC23);

            // 도형의 위치와 가로, 세로 길이 지정
            mDrawableArc.setBounds(x, y, x + width, y + height);

            x = 350;

            // 사각형을 그리는 ShapeDrawable 객체 생성
            mDrawableRect = new ShapeDrawable(new RectShape());

            // 색상 설정
            mDrawableRect.getPaint().setColor(0xff74AC23);

            // 도형의 위치와 가로, 세로 길이 지정
            mDrawableRect.setBounds(x, y, x + width, y + height);

        }

        protected void onDraw(Canvas canvas) {

            // 텍스트 출력에 사용할 Paint 객체 생성
            Paint textPaint = new Paint();

            // Paint 객체에 색상 설정
            textPaint.setColor(0xff893456);

            // 텍스트 크기 설정
            textPaint.setTextSize(60);

            // 텍스트 출력
            canvas.drawText("2D Draw Test", 10, 80, textPaint);

            // ShapeDrawable 객체로 도형을 그린다
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
