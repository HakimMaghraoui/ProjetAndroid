package com.example.projetandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG=DatabaseHelper.class.getSimpleName();

    private static final int DATABASE_VERSION=2;
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
        values.put(Artist.COLUMN_SONG, artist.getSong());
        long  id = sqLiteDatabase.insert(artist.TABLE_NAME, null, values);

        sqLiteDatabase.close();

        return id;
//comment

    }

    public void initData(){
        createArtist("Miles", "Devis", "artist_miles_devis", "Miles Dewey Davis III, né le 26 mai 1926 à Alton (Illinois) et mort le 28 septembre 1991 à Santa Monica (Californie), est un compositeur et trompettiste de jazz américain.",R.drawable.flag_us, "Jazz", "blueingreen");
        createArtist("Queen", "", "artist_queen", "Queen est un groupe de rock britannique, originaire de Londres, en Angleterre. Il est formé en 1970 par Freddie Mercury, Brian May et Roger Taylor, ces deux derniers étant issus du groupe Smile. L’année suivante, le bassiste John Deacon vient compléter la formation. Les quatre membres de Queen sont tous des auteurs-compositeurs.", R.drawable.flag_uk, "Rock", "bohemianrhabsody");
        createArtist("WolfgangAmadeus", "Mozart", "artist_wolfgangamadeus_mozart", "Wolfgang Amadeus Mozart ou Johannes Chrysostomus Wolfgangus Theophilus Mozart, né à Salzbourg (principauté du Saint-Empire romain germanique) le 27 janvier 1756 et mort à Vienne le 5 décembre 1791, est un compositeur.", R.drawable.flag_ger, "Classic", "lacrimosa");
        createArtist("Michael", "Jackson", "artist_michael_jackson", "Michael Jackson, de son nom complet Michael Joseph Jackson, né le 29 août 1958 à Gary (Indiana) et mort le 25 juin 2009 à Los Angeles (Californie), est un auteur-compositeur-interprète, danseur-chorégraphe et acteur américain. Le Livre Guinness des records le désigne comme étant l’artiste le plus couronné de tous les temps. Reconnu comme l’artiste le plus titré de tous les temps par le Rock and Roll Hall of Fame, il est une figure principale de l'histoire de l'industrie du spectacle et l'une des icônes culturelles majeures du XXe siècle.", R.drawable.flag_us, "Pop", "billiejean");
        createArtist("Merle", "Haggard", "artist_merle_haggard", "Merle Ronald Haggard, né à Bakersfield (Californie) le 6 avril 1937 et mort le 6 avril 2016 à Palo Cedro, est un chanteur, guitariste et compositeur de musique country. Émergeant de la prison dans les années 1960, il participe avec Buck Owens et son groupe The Strangers à l'émergence du Bakersfield sound, caractérisé par le son caractéristique de la Fender Telecaster associée aux traditionnelles guitares country, les harmonies vocales serrées et une certaine rugosité qui n'existait pas dans le Nashville sound. Dans les années 1970, il participe à la montée du mouvement outlaw country, aux côtés de Mickey Newbury et Johnny Cash, entre autres.", R.drawable.flag_us, "Country", "arethegoodtimereallyover");
        createArtist("Eminem", "", "artist_eminem", "Eminem, souvent stylisé EMINƎM, de son vrai nom Marshall Bruce Mathers III, né le 17 octobre 19721 à Saint Joseph dans l'État du Missouri, est un rappeur américain, également producteur, acteur, et fondateur de label. En plus de sa carrière solo, il est aussi membre du groupe D12, dont il est le cofondateur, et compose le duo Bad Meets Evil avec Royce da 5'9\". Il a également fait partie, dans sa jeunesse, d'un groupe nommé Soul Intent.", R.drawable.flag_us, "Hip-Hop", "loseyourself");
        createArtist("Skrillex", "", "artist_skrillex", "Skrillex, de son vrai nom Sonny John Moore, né le 15 janvier 1988 à Los Angeles, est un DJ et compositeur américain de musique électronique. Après avoir grandi au nord de la Californie, Sonny Moore rejoint le groupe de post-hardcore From First to Last en tant que chanteur en 2004 et y enregistre deux albums studio (Dear Diary, My Teen Angst Has a Body Count en 2004, et Heroine en 2006) avant de se lancer dans une carrière musicale en solo. Il lance sa première tournée en solo la même année. Aux côtés d'une nouvelle formation de groupe, Sonny Moore joue à l'Alternative Press Tour en soutien à des groupes comme All Time Low et The Rocket Summer et apparaît sur la couverture des « 100 groupes à connaître » du magazine Alternative Press.", R.drawable.flag_us, "Electronic", "bangarang");

    }
    private void createArtist(String surname, String name, String photo, String biography, int flag, String genre, String song){
        Artist artist = new Artist(surname, name, photo, biography, flag, genre, song);
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
        get all of the information of an artist with a string s that contains name and surname, split by " "
        @param: String s
        @return: Cursor query
     */
    public Cursor getArtistWith(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] tmp=s.split(" ");
        String surname=tmp[1];
        String name=tmp[0];

        Cursor query = db.rawQuery("SELECT "+"*"+" FROM "+Artist.TABLE_NAME+" WHERE "+Artist.COLUMN_NAME+"="+"'"+name+"' AND "+Artist.COLUMN_SURNAME+"="+"'"+surname+"';", new String[]{});
        return  query;
    }


}
