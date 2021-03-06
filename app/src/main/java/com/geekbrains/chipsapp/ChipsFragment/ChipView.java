package com.geekbrains.chipsapp.ChipsFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.geekbrains.chipsapp.R;

public class ChipView extends View {

    //неактивный цвет
    int baseColor = Color.GRAY;
    //цвет, когда выбрали жетон
    int checkedColor = Color.RED;
    float x;
    float y;
    // Ширина элемента
    int width = 70;
    // Высота элемента
    int height = 70;
    ChipsModel chipsModel = ChipsModel.getInstance();

    private boolean isChecked;

    //радиус
    int radius;

    //Краска
    private Paint chipPaint;

    public ChipView(Context context) {
        super(context);
        init();
       }

    // Вызывается при добавлении элемента в макет
    // AttributeSet attrs - набор параметров, указанных в макете для этого
    // элемента
    public ChipView (Context context, AttributeSet attrs){
        super(context, attrs);
        Log.d("КОНСТРУКТОР 2", "сработал консруктор 2");
        initAttr(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого
    // элемента
    // int defStyleAttr - базовый установленный стиль
    public ChipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("DEFSTYLEATR ", ""+defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого
    // элемента
    // int defStyleAttr - базовый установленный стиль
    // int defStyleRes - ресурс стиля, если он не был определён в предыдущем параметре
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d("DEFSTYLEATR ", ""+defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    private void init() {
        radius = width/2;
        chipPaint = new Paint();
        isChecked = chipsModel.getIsChecked();
        if (isChecked == true) {
            chipPaint.setColor(checkedColor);
        } else {
            chipPaint.setColor(baseColor);
        }
        chipPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, chipPaint);
    }

    public void setRadius(int radius) {
        this.radius = radius;
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
        radius = (int) (height/2);
    }

    // Инициализация атрибутов пользовательского элемента из xml
    @SuppressLint("ResourceAsColor")
    private void initAttr(Context context, AttributeSet attrs) {
        // При помощи этого метода получаем доступ к набору атрибутов.
        // На выходе - массив со значениями
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChipView, 0, 0);

        // Чтобы получить какое-либо значение из этого массива,
        // надо вызвать соответствующий метод и передать в этот метод имя
        // ресурса, указанного в файле определения атрибутов (attrs.xml)
        baseColor = typedArray.getColor(R.styleable.ChipView_baseColor, Color.GRAY);

        // Вторым параметром идёт значение по умолчанию. Оно будет подставлено,
        // если атрибут не будет указан в макете
        checkedColor = typedArray.getColor(R.styleable.ChipView_checkedColor, Color.RED);
        radius = typedArray.getInteger(R.styleable.ChipView_radius, 45);
        isChecked = typedArray.getBoolean(R.styleable.ChipView_isChecked, false);

// В конце работы дадим сигнал, что массив со значениями атрибутов
        // больше не нужен. Система в дальнейшем будет переиспользовать этот
        // объект, и мы больше не получим к нему доступ из этого элемента
        typedArray.recycle();
    }


    //изменить цвет жетона, когда на него нажали
    public void changeColor() {
        if (isChecked == true) {
            chipPaint.setColor(baseColor);
            isChecked = false;
        } else {
            chipPaint.setColor(checkedColor);
            isChecked = true;
        }
        invalidate();
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public void setPaintColor(){
        if (isChecked){
            chipPaint.setColor(checkedColor);
        }
        if (!isChecked){
            chipPaint.setColor(baseColor);
        }
        invalidate();
    }



}
