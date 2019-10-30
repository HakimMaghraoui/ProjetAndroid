package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    public DatabaseHelper dbs;
    GenreFragment genreFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("TAG = "+TAG);
        dbs = new DatabaseHelper(this);
        Log.d(TAG, "my log is here");
        setContentView(R.layout.activity_main);
        createArtist("test", "test", "test", "tesgrregregregsdq", "america", "Jazz");
        createArtist("test", "test", "test", "tesgrregregregsdq", "america", "Pop");
        createArtist("test", "test", "test", "tesgrregregregsdq", "america", "Rock");
        Cursor testquery= dbs.getAllGenre();
        Log.d(TAG, ""+testquery.getCount());
        Log.d(TAG ,"i'm here!!!");

        setContentView(R.layout.activity_main);
        genreFragment = (GenreFragment) getSupportFragmentManager().findFragmentById(R.id.genreID);

    }

    private void createArtist(String surname, String name, String photo, String biography, String flag, String genre){
        Artist artist = new Artist(surname, name, photo, biography, flag, genre);
        long id= dbs.insertArtist(artist);
    }


}
