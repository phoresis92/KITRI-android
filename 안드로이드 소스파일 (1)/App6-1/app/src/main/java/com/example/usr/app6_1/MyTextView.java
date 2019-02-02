package com.example.usr.app6_1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

//TextView를 상속받는 파생클래스를 정의
public class MyTextView extends TextView {

    // Resources : 어플리케이션 리소스에 접근하는 클래스
    Resources myRes;

    public MyTextView(Context context) {
        super(context);
        myRes = getResources();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myRes = getResources();
    }

    // 뷰(텍스트 뷰)를 그린다. 뷰가 원하는 형태로 출력되도록 이 메서드를 재정의.
    @Override
    protected void onDraw(Canvas canvas) {

        // 텍스트 뷰의 배경색으로 사용할 색상을 colors.xml에서 리소스 형태로 읽어옴
        int bgColor = myRes.getColor(R.color.bgColor);

        // 윗 라인에서 읽은 색상값을 배경에 적용
        canvas.drawColor(bgColor);

        // 선을 그릴때 사용할 페인트 객체 생성
        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 라인 색상으로 사용할 색상을 colors.xml에서 리소스 형태로 읽어옴
        linePaint.setColor(myRes.getColor(R.color.lineColor));

        // 라인을 그린다.
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(),
                getMeasuredHeight(), linePaint);

        // 텍스트 뷰 출력
        super.onDraw(canvas);
    }

}

