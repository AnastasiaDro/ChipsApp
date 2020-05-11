package com.geekbrains.chipsapp.ChipsFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.chipsapp.interfaces.FragmentInterface;
import com.geekbrains.chipsapp.R;
import static com.geekbrains.chipsapp.Constants.*;
import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES;
import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES_CHIPS_NUMBER;

public class ChipsFragment extends Fragment implements FragmentInterface {

    //сохранение настроек интерфейса
    private SharedPreferences mSettings;
    //число жетонов
    int chipsNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //вытащим настройки
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //восстановим количество жетонов
        chipsNumber = mSettings.getInt(APP_PREFERENCES_CHIPS_NUMBER, 1);
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chips, container, false);


        return view;
    }




    @Override
    public void findViews(View view) {

    }

@Override
    public void postFragment(AppCompatActivity activity, int placeId) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(placeId, this);
        ft.commit();
    }


    //сохраним тут число жетонов
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        //сохраним число жетонов
        editor.putInt(APP_PREFERENCES_CHIPS_NUMBER,  chipsNumber);
        editor.apply();
    }
    }