package com.geekbrains.chipsapp.ChipsFragment;


import com.geekbrains.chipsapp.interfaces.Observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//Синглтон
public class ChipsModel {
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



    //конструктор
    private ChipsModel() {
        //Список наблюдателей
        observers = new LinkedList<>();
        chipsMap = new HashMap();
        chipsNumber = 1;
        int checkedChipsNumber = 0;
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
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Наблюдатель добавлен. Список наблюдателей " + observers.toString());
    }

//удалить наблюдателя
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Наблюдатель удалён. Список наблюдателей " + observers.toString());
    }

//уведомить наблюдателей
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

}
