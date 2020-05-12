package com.geekbrains.chipsapp;

import androidx.appcompat.app.AppCompatActivity;
import static com.geekbrains.chipsapp.Constants.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.geekbrains.chipsapp.ChipsFragment.ChipsFragment;
import com.geekbrains.chipsapp.ChipsFragment.ChipsModel;
import com.geekbrains.chipsapp.aboutFragment.AboutFragment;

import static com.geekbrains.chipsapp.Constants.APP_PREFERENCES_CHIPS_NUMBER;


public class MainActivity extends AppCompatActivity {

    //сохранение настроек интерфейса
    private SharedPreferences mSettings;
    //число жетонов
    int chipsNumber;
    //число отмеченных жетонов
    int checkedChipsNum;
    boolean isChecked;

//модель
    ChipsModel chipsModel = ChipsModel.getInstance();

    //Фрагменты
    ChipsFragment chipsFragment;
    AboutFragment aboutFragment;
    ChipsNumAlert chipsNumAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //восстановим количество жетонов
        chipsNumber = mSettings.getInt(APP_PREFERENCES_CHIPS_NUMBER, 1);
        checkedChipsNum = mSettings.getInt(APP_PREFERENCES_CHECKED_CHIPS_NUMBER, 1);
        //временная переменная
        chipsModel.setChipsNumber(chipsNumber);
        chipsModel.setCheckedChipsNum(checkedChipsNum);
        setContentView(R.layout.activity_main);
        initFragment();
    }



    //создание и публикация фрагмента
    private void initFragment() {
        chipsFragment = new ChipsFragment();
        aboutFragment = new AboutFragment();
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

    //обработка нажатий на пункты optionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeChipsNum:
                //должно выскакивать окошко с выбором количества жетонов
                chipsNumAlert  = new ChipsNumAlert();
                chipsNumAlert.show(getSupportFragmentManager(), "chipsNumAlert");
                return true;
            case R.id.aboutApp:
                //переходим на фрагмент настроек
                aboutFragment.postFragment(this, R.id.placeForFr);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //сохраним тут число жетонов
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        //сохраним число жетонов
        editor.putInt(APP_PREFERENCES_CHIPS_NUMBER, chipsModel.getChipsNumber());
        editor.putInt(APP_PREFERENCES_CHECKED_CHIPS_NUMBER, chipsModel.getCheckedChipsNum());
        editor.apply();
    }
}
