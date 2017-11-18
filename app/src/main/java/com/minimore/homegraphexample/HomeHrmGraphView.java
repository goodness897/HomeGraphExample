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

public class HomeHrmGraphView extends View {

    private Paint linePaint;

    private int graphWidth;

    private int graphHeight;

    private Path path;

    public HomeHrmGraphView(Context context) {
        super(context);
        init();

    }

    public HomeHrmGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        linePaint = new Paint();
        linePaint.setStrokeWidth(CommonUtil.convertDpToPixel(0.7f));
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#fa5959"));
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

        float startX = getLeft();
        float max = 200;
        float step = graphHeight / max;
        List<Integer> hrmList = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            hrmList.add((int) (Math.random() * 200));
        }

        path.moveTo(startX, getBottom() - step * hrmList.get(0));
        for (int i = 1; i < 25; i++) {
            path.lineTo(intervalX * i, getBottom() - step * hrmList.get(i));
        }
        path.lineTo(getRight(), getBottom());
        canvas.drawPath(path, linePaint);


    }
}
