package com.geekbrains.chipsapp.ChipsFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.chipsapp.interfaces.FragmentInterface;
import com.geekbrains.chipsapp.R;
import com.geekbrains.chipsapp.interfaces.Observer;

import org.xmlpull.v1.XmlPullParser;

import java.security.acl.Group;

import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES;
import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES_CHIPS_NUMBER;

public class ChipsFragment extends Fragment implements FragmentInterface, Observer {

    //Модель
    ChipsModel chipsModel;
    //число жетонов
    int chipsNumber;
    int checkedChipsNum;
    ChipsFragment chipsFragment;

    //для отладки вид жетона
    ChipView chipView;
    Context context;
    TableLayout tableLayout;
    TableRow tableRow1;
    int forPhoneDivider;
    int forTabDivider;
    float widthDpi;
    float heightDpi;
    //для вычисления размеров экрана
    double diagonalInches;
    ViewGroup.LayoutParams layoutParams;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chipsModel = ChipsModel.getInstance();

        takeChipsSettings();
        //в строке у телефона будет 5 жетонов
        forPhoneDivider = 5;
        //в строке у планшета - 10
        forTabDivider = 10;
        context = getContext();
        diagonalInches = getDispDiagonalInches();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chips, container, false);
        findViews(view);
        init();
        return view;
    }

    //найдём все View
    @Override
    public void findViews(View view) {
        chipView = view.findViewById(R.id.chipView);
        if (chipView.getLayoutParams() != null) {
            layoutParams = chipView.getLayoutParams();
            chipsModel.setLayoutParams(layoutParams);
        }
        //находим представление нашей таблицы
        tableLayout = view.findViewById(R.id.chipTableLayout);
        //находим представление строки
        tableRow1 = view.findViewById(R.id.tableRow1);
    }

    //инициализируем все нужное
    private void init() {
        ChipClickListener chipClickListener = new ChipClickListener(chipView);
        chipView.setOnClickListener(chipClickListener);
        chipView.setIsChecked(chipsModel.getIsChecked());
    }

    @Override
    public void postFragment(AppCompatActivity activity, int placeId) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(placeId, this);
        ft.commit();
        updateViewData();
    }

    @Override
    public void onPause() {
        super.onPause();
        chipsModel.removeObserver(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        chipsModel.registerObserver(this);
//        updateViewData();
//        takeChipsSettings();
//        //в строке у телефона будет 5 жетонов
//        forPhoneDivider = 5;
//        //в строке у планшета - 10
//        forTabDivider = 10;
//        context = getContext();
//        diagonalInches = getDispDiagonalInches();
    }

    @Override
    public void updateViewData() {
        Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                Log.d("updateViewData", "СРАБОТАЛ");
                //получим кол-во жетонов и кол-во отмеченных жетонов
                takeChipsSettings();
                tableLayout.removeAllViews();
                //проверим, телефон или планшет, меньше 7 - телефон
                if (diagonalInches < 6) {
                    createAllChips(forPhoneDivider);
                }
                if (diagonalInches >= 6) {
                    createAllChips(forTabDivider);
                }
            }
        });
    }


    private void takeChipsSettings() {
        //получим число жетонов из модели
        chipsNumber = chipsModel.getChipsNumber();
        //получим число собранных жетонов
        checkedChipsNum = chipsModel.getCheckedChipsNum();
        Log.d("takeChipsSettings", "число жетонов: " + chipsNumber + " отмеченных: " + checkedChipsNum);
    }


    private void createAllChips(int divider) {
        //получим число строк: округлим в большую сторону кол-во жетонов
        //на делитель (5 или 10)
        //TODO неправильно округляет, падла
        int rowCount = (int) Math.floor(chipsNumber / divider);
        if ((chipsNumber % divider)> 0){
            ++rowCount;
        }
        Log.d("createAllChips", "rowCount: " + rowCount);
        createChipsAndRows(rowCount, divider);
    }

    //в зависимости от числа строк и делителя (разного для телефона и планшета)
    //нарисуем все строки и столбцы
    private void createChipsAndRows(int rowCount, int divider) {
        int chipsNum = chipsNumber;
        int chekedNum = checkedChipsNum;
        float chipSize = heightDpi/rowCount-2;
        for (int i = 0; i < rowCount; i++) {
            TableRow tableRow = new TableRow(context);
            tableRow.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            tableRow.setLayoutParams(layoutParams);
            //если делитель (напр 5) менше числа жетонов (напр 8),
            //то мы отрисуем в строке 5
            //если оставшихся жетонов меньше (5 отрисовали, 3 осталось),
            //то отрисуем оставшиеся 3
            chekedNum = createChipsForRow(chipSize, tableRow, divider, chekedNum, chipsNum);
            //в конце цикла уменьшаем число жетонов на делитель
            chipsNum = chipsNum - divider;
            tableLayout.addView(tableRow);
        }
        //tableLayout.setLayoutParams(layoutParams);
    }

    //заполнение строки кастомными жетончиками
    //и отмечание их, если они отмечены
    private int createChipsForRow(float chipSize, TableRow tableRow, int chipsNumInRow, int checkedNum, int chipsNum) {
        int counter = 0;
        tableRow.removeAllViews();
        for (int i = 0; i < chipsNumInRow && i < chipsNum; i++) {
            ChipView chipView = new ChipView(context);
            chipView.setLayoutParams(chipsModel.getLayoutParams());
            if (checkedNum > 0) {
                chipView.setIsChecked(true);
                chipView.setPaintColor();
                checkedNum--;
            }
            chipView.setOnClickListener(new ChipClickListener(chipView));
            tableRow.addView(chipView);
            counter++;
        }
        Log.d("Создано жетонов: ", "" + counter);
        counter = 0;
        return checkedNum;
    }

    private double getDispDiagonalInches() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //количество пикселей в ширину и высоту
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        //кол-во пикселей на дюйм в ширину и высоту
        widthDpi = metrics.xdpi;
        heightDpi = metrics.ydpi;
        //дюймы в ширину и высоту
        float widthInches = widthPixels / widthDpi;
        float heightInches = heightPixels / heightDpi;
        //диагональ
        double diagonalInches = Math.sqrt(
                (widthInches * widthInches)
                        + (heightInches * heightInches));
        Log.d("getDispDiagonalImches", "диагональ: " + diagonalInches);
        return diagonalInches;
    }
}