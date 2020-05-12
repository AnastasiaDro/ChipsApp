package com.geekbrains.chipsapp.ChipsFragment;

import android.util.Log;
import android.view.View;

public class ChipClickListener implements View.OnClickListener {

    private ChipView chipView;
    ChipsModel chipsModel;

    public ChipClickListener (ChipView chipView){
        this.chipView = chipView;
        chipsModel = ChipsModel.getInstance();
    }

    @Override
    public void onClick(View v) {
        chipView.changeColor();
        if(chipView.getIsChecked()==true){
            chipsModel.setCheckedChipsNum(chipsModel.getCheckedChipsNum()+1);
        }
        if(chipView.getIsChecked()==false){
            chipsModel.setCheckedChipsNum(chipsModel.getCheckedChipsNum()-1);
        }
        Log.d("ChipClickListener", "Число чекнутых жетонов: "+chipsModel.getCheckedChipsNum());
    }
}
