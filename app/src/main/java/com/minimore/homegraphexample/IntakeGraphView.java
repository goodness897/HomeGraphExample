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

public class IntakeGraphView extends View {

    private Paint linePaint;

    private Paint fillPaint;

    private int graphWidth;

    private int graphHeight;

    private Path path;

    public IntakeGraphView(Context context) {
        super(context);
        init();

    }

    public IntakeGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        linePaint = new Paint();
        linePaint.setStrokeWidth(CommonUtil.convertDpToPixel(1.6f));
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#2b87e2"));
        linePaint.setStyle(Paint.Style.STROKE);


        fillPaint = new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(Color.parseColor("#b35ab8c6"));
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
        float ratioY = 2000;
        float step = graphHeight / ratioY;
        List<Integer> intakeGraphList = new ArrayList<>();
        int intake = 0;

        for (int i = 0; i < 25; i++) {
            intake = intake + (int) (Math.random() * 100);
            intakeGraphList.add(intake);
        }

        path.moveTo(startX, getBottom() - step * intakeGraphList.get(0));
        for (int i = 1; i < 25; i++) {
            path.lineTo(intervalX * i, getBottom() - step * intakeGraphList.get(i));
        }

        canvas.drawPath(path, fillPaint);
        canvas.drawPath(path, linePaint);


    }
}
