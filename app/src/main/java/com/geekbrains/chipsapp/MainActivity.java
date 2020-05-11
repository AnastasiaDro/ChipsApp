package com.geekbrains.chipsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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



    //создание и публикация фрагмента
    private void initFragment() {
        chipsFragment = new ChipsFragment();
        chipsFragment.postFragment(this, R.id.placeForFr);
    }

    //создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem changeChipsNumIt = menu.findItem(R.id.changeChipsNum);
        MenuItem aboutAppIt = menu.findItem(R.id.aboutApp);
        return true;
    }

}
