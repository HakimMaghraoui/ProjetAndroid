package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String genre=intent.getStringExtra("genre");
        Bundle bundle = new Bundle();
        bundle.putString("genre",genre);
        ListArtist listArtist = new ListArtist();
        listArtist.setArguments(bundle);
        System.out.println("ACTIVITY2 GENRE = "+genre);

        setContentView(R.layout.activity2);
    }
}
