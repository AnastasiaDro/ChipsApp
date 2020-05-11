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

import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES;
import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES_CHIPS_NUMBER;

public class ChipsFragment extends Fragment implements FragmentInterface {

    //Модель
    ChipsModel chipsModel;
    //число жетонов
    int chipsNumber;
    int checkedChipsNum;

    //для отладки вид жетона
    ChipView chipView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chipsModel = ChipsModel.getInstance();
        //получим число жетонов из модели
        chipsNumber = chipsModel.getChipsNumber();
        checkedChipsNum = chipsModel.getCheckedChipsNum();
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chips, container, false);
        findViews(view);
        init();
        return view;
    }

    //найдём все View
    @Override
    public void findViews(View view) {
        chipView = view.findViewById(R.id.chipView);
    }

    //инициализируем все нужное
    private void init() {
        ChipClickListener chipClickListener = new ChipClickListener(chipView);
        chipView.setOnClickListener(chipClickListener);
    }


@Override
    public void postFragment(AppCompatActivity activity, int placeId) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(placeId, this);
        ft.commit();
    }

    }