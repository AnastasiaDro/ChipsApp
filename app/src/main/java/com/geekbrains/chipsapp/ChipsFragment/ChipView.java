package com.geekbrains.chipsapp.ChipsFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class ChipView extends View {

    //неактивный цвет
    private int baseColor = Color.GRAY;
    //цвет, когда выбрали жетон
    private int checkedColor = Color.RED;
    float x;
    float y;
    // Ширина элемента
    private int width = 0;
    // Высота элемента
    private int height = 0;
    boolean isChecked;

    //радиус
    private int radius;

    //Краска
    private Paint chipPaint;

    public ChipView(Context context, boolean isChecked) {
        super(context);
        init();
        this.isChecked = isChecked;
        radius = width/7;
    }

    private void init() {
        chipPaint = new Paint();
        chipPaint.setColor(baseColor);
        chipPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, chipPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Получаем реальный радиус
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();
        //получим координаты (центр)
        x = width/2;
        y = height/2;
//        height = h - getPaddingTop() - getPaddingBottom();
//        // Отрисовка градусника
//        thermomRect.set(padding * 2,
//                padding + headHeight,
//                width - padding * 2,
//                height - padding);
//
//        tailRect.set(padding * 6,
//                padding,
//                width - padding * 6,
//                height - thermomRect.height());
//
//        tempLevel.set(7 * padding,
//                (int) ((height - 2 * padding - headWidth) * ((double) level / (double) 100)),
//                width - padding * 7,
//                height - 4 * padding);
    }

    //изменить цвет жетона, когда на него нажали
    private void changeColor() {
        if (isChecked == true) {
            chipPaint.setColor(baseColor);
        } else {
            chipPaint.setColor(checkedColor);
        }
        invalidate();
    }


}
