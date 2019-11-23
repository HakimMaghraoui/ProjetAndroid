package com.example.projetandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG=DatabaseHelper.class.getSimpleName();

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Artist";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Artist.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Artist.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public long insertArtist(Artist artist) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Artist.COLUMN_NAME, artist.getName());
        values.put(Artist.COLUMN_SURNAME, artist.getSurname());
        values.put(Artist.COLUMN_PHOTO, artist.getPhoto());
        values.put(Artist.COLUMN_BIOGRAPHY, artist.getBiography());
        values.put(Artist.COLUMN_FLAG, artist.getFlag());
        values.put(Artist.COLUMN_GENRE, artist.getGenre());
        long  id = sqLiteDatabase.insert(artist.TABLE_NAME, null, values);

        sqLiteDatabase.close();

        return id;


    }

    public void initData(){
        createArtist("Miles", "Devis", "@drawable/artist_miles_devis.png", "Miles Dewey Davis III [maɪlz ˈdjuːi ˈdeɪvɪs ðə θɝd]1, né le 26 mai 1926 à Alton (Illinois) et mort le 28 septembre 1991 à Santa Monica (Californie), est un compositeur et trompettiste de jazz américain.","USA", "Jazz");
        createArtist("Queen", "", "@drawable/artist_queen.png", "Queen [kwiːn]5 Écouter est un groupe de rock britannique, originaire de Londres, en Angleterre. Il est formé en 1970 par Freddie Mercury, Brian May et Roger Taylor, ces deux derniers étant issus du groupe Smile. L’année suivante, le bassiste John Deacon vient compléter la formation. Les quatre membres de Queen sont tous des auteurs-compositeurs.", "UK", "Rock");
        createArtist("test", "test", "test", "tesgrregregregsdq", "USA", "Pop");
        createArtist("test2", "test22", "test", "tesgrregregregsdq", "FR", "Pop");
        createArtist("test3", "test33", "test", "tesgrregregregsdq", "JP", "Pop");
        createArtist("test", "test", "test", "tesgrregregregsdq", "AL", "Rock");
    }
    private void createArtist(String surname, String name, String photo, String biography, String flag, String genre){
        Artist artist = new Artist(surname, name, photo, biography, flag, genre);
        long id= this.insertArtist(artist);
    }

    public Cursor getAllGenre(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor testquery= db.rawQuery("SELECT DISTINCT "+Artist.COLUMN_GENRE+" FROM "+Artist.TABLE_NAME, new String[]{});

        int number = testquery.getCount();

        return testquery;
    }


    public Cursor getAllArtistWith(String genre){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor query = db.rawQuery("SELECT DISTINCT "+Artist.COLUMN_NAME+", "+Artist.COLUMN_SURNAME+", "+Artist.COLUMN_FLAG+" FROM "+Artist.TABLE_NAME+" WHERE "+Artist.COLUMN_GENRE+"='"+genre+"';", new String[]{});
        return query;
    }

    /*
        get all of the information of an artist with a string s that contains name surname and flag, split by " "
        @param: String s
        @return: Cursor query
     */
    public Cursor getArtistWith(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] tmp=s.split(" ");
        String surname=tmp[0];
        String name=tmp[1];
        String flag = tmp[2];
        Cursor query = db.rawQuery("SELECT DISTINCT "+"*"+" FROM "+Artist.TABLE_NAME+" WHERE "+Artist.COLUMN_NAME+"="+"'"+name+"' AND "+Artist.COLUMN_SURNAME+"="+"'"+surname+"' AND "+Artist.COLUMN_FLAG+"="+"'"+flag+"' "+";", new String[]{});
        return  query;
    }


}
