package com.geekbrains.chipsapp.aboutFragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.chipsapp.interfaces.FragmentInterface;
import com.geekbrains.chipsapp.R;


public class AboutFragment extends Fragment implements FragmentInterface {

    TextView myFBLinkTV;
    TextView aboutTherapyLinkTV;
    TextView myChannelLinkTV;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        findViews(view);
        initClickListeners();
        return view;
    }

    //инициализируем TextView
    @Override
    public void findViews(View view) {
        myFBLinkTV = view.findViewById(R.id.myFBLinkTV);
        aboutTherapyLinkTV = view.findViewById(R.id.aboutTherapyLinkTV);
        myChannelLinkTV = view.findViewById(R.id.myChannelLinkTV);
    }

    private void initClickListeners(){
        myFBLinkTV.setOnClickListener(new OnLinkClickListener("https://www.facebook.com/profile.php?id=100001054275331", myFBLinkTV));
        aboutTherapyLinkTV.setOnClickListener(new OnLinkClickListener("http://petra-center.ru/direction/kinezoterapiya/", aboutTherapyLinkTV));
        myChannelLinkTV.setOnClickListener(new OnLinkClickListener("https://www.youtube.com/channel/UCy-bYj7AH7QcmMxWWFklfkg?view_as=subscriber", myChannelLinkTV));
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
