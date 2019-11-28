package com.example.projetandroid;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class BioArtist extends Fragment {
    RelativeLayout relativeLayout;

    TextView bioText;

    ImageView bioPic;
    String artistS;
    //comment

    DatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bio_artist, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //on récupère l'artiste
        ActivityBio activityBio= (ActivityBio)getActivity();
        String artistS=activityBio.getArtist();
        //pour utiliser la base de donnees il faut la récuperer depuis le contexte
        db = new DatabaseHelper(getContext());

        Cursor cursor =db.getArtistWith(artistS);
        if(cursor.moveToFirst()){

            String biography = cursor.getString(cursor.getColumnIndex("biography"));
            TextView biographyTxt = getView().findViewById(R.id.bioText);
            biographyTxt.setText(biography);

            String pic = cursor.getString(cursor.getColumnIndex("photo"));
            ImageView picImg = getView().findViewById(R.id.bioPic);
            System.out.println(pic);
            picImg.setImageResource(getResources().getIdentifier(pic,"drawable", activityBio.getPackageName()));
        }




    }
}
