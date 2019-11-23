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
        //ici tu peut recupere ta string artist depuis l'activity bio comme ça
        ActivityBio activityBio= (ActivityBio)getActivity();
        String artistS=activityBio.getArtist();
        //pour utiliser la base de donnees il faut que tu la recuper depuis le context
        db = new DatabaseHelper(getContext());
        //et la tu fait tu le chose qui te sert en utilisant aussi le methode de la base de donnees en partant de db
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