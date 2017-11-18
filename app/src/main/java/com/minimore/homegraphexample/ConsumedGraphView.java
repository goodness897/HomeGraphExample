package com.minimore.homegraphexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goodn on 2017-11-18.
 */

public class ConsumedGraphView extends View {

    private Paint linePaint;

    private Paint fillPaint;

    private int graphWidth;

    private int graphHeight;

    private Path path;

    public ConsumedGraphView(Context context) {
        super(context);
        init();

    }

    public ConsumedGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {


        linePaint = new Paint();
        linePaint.setStrokeWidth(CommonUtil.convertDpToPixel(1.6f));
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#feb039"));
        linePaint.setStyle(Paint.Style.STROKE);


        fillPaint = new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(Color.parseColor("#66feb039"));
        fillPaint.setStyle(Paint.Style.FILL_AND_STROKE);

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
        float startX = getLeft();
        float max = 2000;
        float ratioY = graphHeight / max;
        List<Integer> consumedList = new ArrayList<>();
        int consumed = 0;

        for (int i = 0; i < 25; i++) {
            consumed = consumed + (int) (Math.random() * 100);
            consumedList.add(consumed);
        }

        path.moveTo(startX, getBottom() - ratioY * consumedList.get(0));
        for (int i = 1; i < 25; i++) {
            path.lineTo(intervalX * i, getBottom() - ratioY * consumedList.get(i));
        }
        path.lineTo(getRight(), getBottom());


        canvas.drawPath(path, fillPaint);
        canvas.drawPath(path, linePaint);


    }
}
