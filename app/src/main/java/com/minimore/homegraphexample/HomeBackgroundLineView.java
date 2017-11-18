package com.minimore.homegraphexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by goodn on 2017-11-18.
 */

public class HomeBackgroundLineView extends View {

    private Paint linePaint;

    private int graphWidth;

    private int graphHeight;

    private Path path;

    public HomeBackgroundLineView(Context context) {
        super(context);
        init();

    }

    public HomeBackgroundLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        linePaint = new Paint();
        linePaint.setStrokeWidth(CommonUtil.convertDpToPixel(1));
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#D6dddddd"));
        linePaint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        graphWidth = MeasureSpec.getSize(widthMeasureSpec);
        graphHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(graphWidth, graphHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawGraph(canvas);

    }

    private void drawGraph(Canvas canvas) {

        int intervalX = graphWidth / 24;

        for (int i = 1; i < 25; i++) {
            if (i % 6 == 5) {
                linePaint.setColor(Color.parseColor("#1ce6e6e6"));
                linePaint.setStrokeWidth(2);
            } else {
                linePaint.setColor(Color.parseColor("#D6dddddd"));
                linePaint.setStrokeWidth(2);
            }
            canvas.drawLine(intervalX * i, getBottom(), intervalX * i, getTop(), linePaint);
        }


    }
}
