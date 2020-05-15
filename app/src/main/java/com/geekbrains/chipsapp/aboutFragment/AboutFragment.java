package com.geekbrains.chipsapp.aboutFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.chipsapp.interfaces.FragmentInterface;
import com.geekbrains.chipsapp.R;

public class AboutFragment extends Fragment implements FragmentInterface {


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
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
        ft.addToBackStack("tag1");
        ft.commit();
    }
}
