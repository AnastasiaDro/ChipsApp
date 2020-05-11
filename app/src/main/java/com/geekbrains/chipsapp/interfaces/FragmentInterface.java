package com.geekbrains.chipsapp.interfaces;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public interface FragmentInterface {

    //находит кнопки и т.п.
    void findViews(View view);
    //вставляет фрагмент
    public void postFragment(AppCompatActivity activity, int placeId);

}
