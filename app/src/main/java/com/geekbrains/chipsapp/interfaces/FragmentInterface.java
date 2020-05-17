package com.geekbrains.chipsapp.interfaces;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public interface FragmentInterface {

    //находит кнопки и т.п.
    void findViews(View view);
    //вставляет фрагмент
    public void postFragment(AppCompatActivity activity, int placeId);

}
