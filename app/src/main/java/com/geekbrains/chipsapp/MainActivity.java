package com.geekbrains.chipsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.geekbrains.chipsapp.ChipsFragment.ChipsFragment;
import com.geekbrains.chipsapp.aboutFragment.AboutFragment;


public class MainActivity extends AppCompatActivity {

    //Фрагменты
    ChipsFragment chipsFragment;
    AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    //моздание и публикация фрагмента
    private void initFragment() {
        chipsFragment = new ChipsFragment();
        chipsFragment.postFragment(this, R.id.placeForFr);
    }

}
