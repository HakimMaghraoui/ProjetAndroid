package com.example.projetandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
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

        return 0;

    }
}
