package com.example.projetandroid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class BioArtist extends AppCompatActivity{
    RelativeLayout relativeLayout;

    TextView bioText;

    ImageView bioPic;
    String artistS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bio_artist);
        relativeLayout=findViewById(R.id.relativeLayout);
        bioText=findViewById(R.id.bioText);
        bioPic=findViewById(R.id.bioPic);
        ActivityBio activityBio = (ActivityBio) getActivity();
        artistS=activityBio.getArtist();
        //Artist artist =
        bioPic.setImageResource(artist.);

    }
}
