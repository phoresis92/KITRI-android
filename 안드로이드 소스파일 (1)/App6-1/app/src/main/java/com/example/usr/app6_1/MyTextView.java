package com.example.usr.app6_1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

//TextView�� ��ӹ޴� �Ļ�Ŭ������ ����
public class MyTextView extends TextView {

    // Resources : ���ø����̼� ���ҽ��� �����ϴ� Ŭ����
    Resources myRes;

    public MyTextView(Context context) {
        super(context);
        myRes = getResources();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myRes = getResources();
    }

    // ��(�ؽ�Ʈ ��)�� �׸���. �䰡 ���ϴ� ���·� ��µǵ��� �� �޼��带 ������.
    @Override
    protected void onDraw(Canvas canvas) {

        // �ؽ�Ʈ ���� �������� ����� ������ colors.xml���� ���ҽ� ���·� �о��
        int bgColor = myRes.getColor(R.color.bgColor);

        // �� ���ο��� ���� ������ ��濡 ����
        canvas.drawColor(bgColor);

        // ���� �׸��� ����� ����Ʈ ��ü ����
        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // ���� �������� ����� ������ colors.xml���� ���ҽ� ���·� �о��
        linePaint.setColor(myRes.getColor(R.color.lineColor));

        // ������ �׸���.
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(),
                getMeasuredHeight(), linePaint);

        // �ؽ�Ʈ �� ���
        super.onDraw(canvas);
    }

}

