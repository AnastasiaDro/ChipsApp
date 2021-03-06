package com.geekbrains.chipsapp.ChipsFragment;


import android.app.Activity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.geekbrains.chipsapp.interfaces.Observable;
import com.geekbrains.chipsapp.interfaces.Observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//Синглтон
public class ChipsModel implements Observable {
    private static ChipsModel instance;
    //Список наблюдателей
    public List<Observer> observers;
    //Map жетонов
    private HashMap chipsMap;
    int chipsNumber;
    int checkedChipsNum;
    //флаг ченкутости жетона
    boolean isChecked;
    int chipMinNum;
    int chipMaxNum;
    double diagonalInches;
   Activity activity;



    ViewGroup.LayoutParams layoutParams;


    //конструктор
    private ChipsModel() {
        //Список наблюдателей
        observers = new LinkedList<>();
        chipsMap = new HashMap();
        chipsNumber = 1;
        checkedChipsNum = 0;
        chipMinNum = 1;
        chipMaxNum = 20;
            }

//Метод получения chipsModel
    public static ChipsModel getInstance() {
        if (instance == null) {
            instance = new ChipsModel();
        }
        return instance;
    }

//добавить наблюдателя
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Наблюдатель добавлен. Список наблюдателей " + observers.toString());
    }

//удалить наблюдателя
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Наблюдатель удалён. Список наблюдателей " + observers.toString());
    }

//уведомить наблюдателей
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateViewData();
        }

    }




    //геттеры и сеттеры
    //количество жетонов
    public int getChipsNumber() {
        return chipsNumber;
    }
    public void setChipsNumber(int chipsNumber) {
        this.chipsNumber = chipsNumber;
    }

    //количество собранных жетонов
    public int getCheckedChipsNum() { return checkedChipsNum; }
    public void setCheckedChipsNum(int checkedChipsNumber) {this.checkedChipsNum = checkedChipsNumber;}
    public boolean getIsChecked() { return isChecked;}
    public void setIsChecked(boolean checked) {isChecked = checked;}

    //минимальное и максимальное число жетонов
    public int getChipMinNum() { return chipMinNum; }
    public int getChipMaxNum() { return chipMaxNum; }

    //активность
    public void setActivity(Activity activity) { this.activity = activity; }

    //диагональ

    public double getDiagonalInches() { return diagonalInches; }

    public ViewGroup.LayoutParams getLayoutParams() { return layoutParams; }
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) { this.layoutParams = layoutParams; }
}
