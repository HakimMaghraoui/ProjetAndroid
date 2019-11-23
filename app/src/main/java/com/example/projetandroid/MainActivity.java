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
        dbs.initData();


        Cursor testquery= dbs.getAllGenre();
        //Log.d(TAG, ""+testquery.getCount());

        //ici on peux donc afficher tout les genre
       /* if (testquery.moveToFirst()){
            do{
                String data = testquery.getString(testquery.getColumnIndex("genre"));
                Log.d(TAG, data);
            }while(testquery.moveToNext());
        }


        testquery.close();
        String[] artisstesTab= null;
        Cursor query2 = dbs.getAllArtistWith("Pop");
        int i=0;
        if (query2.moveToFirst()){
            do{
                String data = query2.getString(query2.getColumnIndex("name"));
                artisstesTab[i]= data;
                i++;
                Log.d(TAG, data);
            }while(query2.moveToNext());
        }
        query2.close();
        Log.d(TAG ,"i'm here!!!");*/


        /*
        genreFragment = (GenreFragment) getSupportFragmentManager().findFragmentById(R.id.genreID);
        setContentView(R.layout.activity_main);
        */


    }




}
