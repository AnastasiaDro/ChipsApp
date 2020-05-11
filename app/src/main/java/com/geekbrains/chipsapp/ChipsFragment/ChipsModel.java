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


//конструктор
    private ChipsModel() {
        //Список наблюдателей
        observers = new LinkedList<>();
        chipsMap = new HashMap();
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
}
