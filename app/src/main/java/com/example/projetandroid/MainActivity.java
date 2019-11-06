package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;




public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    public DatabaseHelper dbs;
    /*GenreFragment genreFragment;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("TAG = "+TAG);
        dbs = new DatabaseHelper(this);
        Log.d(TAG, "my log is here");
        //setContentView(R.layout.activity_main);
        createArtist("test", "test", "test", "tesgrregregregsdq", "america", "Jazz");
        createArtist("test", "test", "test", "tesgrregregregsdq", "america", "Pop");
        createArtist("test", "test", "test", "tesgrregregregsdq", "america", "Rock");
        Cursor testquery= dbs.getAllGenre();
        //Log.d(TAG, ""+testquery.getCount());

        //ici on peux donc afficher tout les genre
        if (testquery.moveToFirst()){
            do{
                String data = testquery.getString(testquery.getColumnIndex("genre"));
                Log.d(TAG, data);
            }while(testquery.moveToNext());
        }


        testquery.close();

        Cursor query2 = dbs.getAllArtistWith("Pop");
        if (query2.moveToFirst()){
            do{
                String data = query2.getString(query2.getColumnIndex("name"));
                Log.d(TAG, data);
            }while(query2.moveToNext());
        }
        query2.close();
        Log.d(TAG ,"i'm here!!!");

        /*
        genreFragment = (GenreFragment) getSupportFragmentManager().findFragmentById(R.id.genreID);
        setContentView(R.layout.activity_main);
        */


    }

    private void createArtist(String surname, String name, String photo, String biography, String flag, String genre){
        Artist artist = new Artist(surname, name, photo, biography, flag, genre);
        long id= dbs.insertArtist(artist);
    }


}
