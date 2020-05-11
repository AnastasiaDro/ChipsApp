package com.geekbrains.chipsapp.ChipsFragment;

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
        chipsModel.setIsChecked(chipView.getIsChecked());
    }
}
