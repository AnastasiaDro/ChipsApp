package com.geekbrains.chipsapp.aboutFragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

public class OnLinkClickListener implements View.OnClickListener {

    String link;
    View v;

    public OnLinkClickListener(String link, View v){
        this.link = link;
        this.v = v;
    }

    @Override
    public void onClick(View view) {
        Intent browIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        v.getContext().startActivity(browIntent);
    }
}
