package com.geekbrains.chipsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import androidx.fragment.app.DialogFragment;


import com.geekbrains.chipsapp.ChipsFragment.ChipsModel;

public class ChipsNumAlert extends DialogFragment {
    int newChipsNum;
    ChipsModel chipsModel;
    NumberPicker chipNumPicker;
    Button btnOk;
    Button btnCancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.alert_chipsnum, null);
        chipsModel = ChipsModel.getInstance();
        initNumPicker(view);
        initBtns(view);
        return view;
    }

        private void changeChipsNum(){
            chipsModel.setChipsNumber(newChipsNum);
        }

        private void initNumPicker(View view) {
            chipNumPicker = view.findViewById(R.id.chipsNumPicker);
            chipNumPicker.setMinValue(chipsModel.getChipMinNum());
            chipNumPicker.setMaxValue(chipsModel.getChipMaxNum());
            newChipsNum = 1;
            chipNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    newChipsNum = newVal;
                    chipsModel.notifyObservers();
                }
            });
        }

        private void initBtns(View view){
            btnOk = view.findViewById(R.id.okBtn);
            btnCancel = view.findViewById(R.id.cancelBtn);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeChipsNum();
                    chipsModel.notifyObservers();
                    dismiss();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
}


