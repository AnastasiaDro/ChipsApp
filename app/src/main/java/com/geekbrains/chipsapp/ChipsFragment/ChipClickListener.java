package com.geekbrains.chipsapp.ChipsFragment;

import android.view.View;

public class ChipClickListener implements View.OnClickListener {

    private ChipView chipView;

    public ChipClickListener (ChipView chipView){
        this.chipView = chipView;
    }

    @Override
    public void onClick(View v) {
        chipView.changeColor();
    }
}
