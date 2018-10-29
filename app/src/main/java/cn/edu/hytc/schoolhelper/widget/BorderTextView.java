package cn.edu.hytc.schoolhelper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by admin on 2018/10/18.
 */

@SuppressLint("DrawAllocation")
public class BorderTextView extends android.support.v7.widget.AppCompatTextView {
    public BorderTextView(Context context) {
        super(context);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int sroke_width = 1;

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();

        //  将边框设为黑色
        paint.setColor(Color.BLACK);

        //  画TextView的4个边
        canvas.drawLine(0, 0, this.getWidth() - sroke_width, 0, paint);
        canvas.drawLine(0, 0, 0, this.getHeight() - sroke_width, paint);
        canvas.drawLine(this.getWidth() - sroke_width, 0, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);
        canvas.drawLine(0, this.getHeight() - sroke_width, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);

        super.onDraw(canvas);
    }
}