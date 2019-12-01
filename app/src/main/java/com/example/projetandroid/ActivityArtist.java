package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityArtist extends AppCompatActivity {
    String genre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comment
        Intent intent=getIntent();
        genre=intent.getStringExtra("genre");
        setContentView(R.layout.activity2);
    }

    public String getGenre(){
        return genre;
    }
}
