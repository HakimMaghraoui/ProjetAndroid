package com.example.projetandroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MusicPlayer extends Fragment {


    DatabaseHelper db;
    //on veux pouvoir lancer les musiques directement depuis la liste.
    private MediaPlayer mediaPlayer;
    //flag=false si le mediaplayer est actif
    private Boolean flag = true;
    //musique qui est actuellement jouer.
    private String onPlay= "";
    private Context context=getActivity();
    private int layout;
    public int song;
    public boolean beg;

    public boolean getBeg() {
        return beg;
    }

    public void setBeg(boolean beg) {
        this.beg = beg;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    /* private ArrayList<Music> arrayList;

    public void CustomMusicAdapter(Context context, int layout, ArrayList<Music> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }*/

    //on veut pouvoir gérer les élémenst visuelles.
    private class ViewHolder {
        TextView songNameText;
        ImageView stopB, playB;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_player, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //on récupère l'artsite.
        ActivityBio activityBio= (ActivityBio)getActivity();
        String artistS=activityBio.getArtist();
        //pour utiliser la base de donnees il faut la récuperer depuis le contexte
        db = new DatabaseHelper(getContext());

        Cursor cursor =db.getArtistWith(artistS);
        /*if (cursor.moveToFirst()){

        }*/

        if(cursor.moveToFirst()){

            //String name = cursor.getString(cursor.getColumnIndex("name"));

            String songS =  cursor.getString(cursor.getColumnIndex("song"));

            Context context = getContext();
            TextView songNameText = getView().findViewById(R.id.songNameText);
            songNameText.setText(songS);

            setSong(getResources().getIdentifier(songS, "raw", activityBio.getPackageName()));
            player(getSong());

        }

//comment

    }

    public View player(int song) {
        Context context = getContext();
        final ViewHolder viewHolder;

        viewHolder=new ViewHolder();
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  View view = layoutInflater.inflate(layout, null);
        TextView songNameText = getView().findViewById(R.id.songNameText);
        ImageView playB = getView().findViewById(R.id.playB);
        ImageView stopB =getView().findViewById(R.id.stopB);
        viewHolder.songNameText=songNameText;
        viewHolder.playB=playB;
        viewHolder.stopB=stopB;
        //view.setTag(viewHolder);
      //  viewHolder.songNameText.setText(song);
        //mediaPlayer = MediaPlayer.create(context, song);
        //play Music
       viewHolder.playB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //boolean beg=false;
              // int length;
               if (flag) {
                   if(getBeg()==false) {
                       mediaPlayer = MediaPlayer.create(getContext(), getSong());
                       setBeg(true);
                   }
                   flag = false;
                   mediaPlayer.start();

                   viewHolder.playB.setImageResource(R.drawable.pause1);

               }
               else{
                   mediaPlayer.pause();
                   flag=true;
                   //length=mediaPlayer.getCurrentPosition();
                   //onPause=true;
                   viewHolder.playB.setImageResource(R.drawable.play1);

               }

           }
       });

        viewHolder.stopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=true;
                viewHolder.playB.setImageResource(R.drawable.play1);
                mediaPlayer.stop();
                setBeg(false);
            }
        });

        /*if(mediaPlayer.isPlaying() ) {
            mediaPlayer.pause();
            onPlay = "";
        }*/
        return getView();

    }


}
