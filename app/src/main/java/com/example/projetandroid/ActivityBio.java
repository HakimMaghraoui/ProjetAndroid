package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityBio extends AppCompatActivity {
    String artist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        artist=intent.getStringExtra("artist");
        //comment

        setContentView(R.layout.activity_bio);
    }

    public String getArtist() {
        return artist;
    }
}
