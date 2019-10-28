package com.example.projetandroid;

import android.media.Image;

public class Artist {

    private int Id;
    private String surname;
    private String name;
    private String photo;
    private String biography;
    private String flag;
    private String genre;

    //DataBase Variable
    public static final String TABLE_NAME="Artist";
    public static final String COLUMN_ID ="id";
    public static final String COLUMN_SURNAME="surname";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_PHOTO="photo";
    public static final String COLUMN_BIOGRAPHY="biography";
    public static final String COLUMN_FLAG="flag";
    public static final String COLUMN_GENRE="genre";

    public static final String CREATE_TABLE ="CREATE TABLE"+TABLE_NAME+"("+COLUMN_SURNAME+" TEXT,"
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME+" TEXT,"
            + COLUMN_PHOTO+" TEXT,"
            + COLUMN_BIOGRAPHY+" TEXT,"
            + COLUMN_FLAG+" TEXT,"
            + COLUMN_GENRE+" TEXT"+")";


    //Constructrors
    public Artist(){}

    public Artist(String surname, String name, String photo, String biography, String flag, String genre) {
        this.surname = surname;
        this.name = name;
        this.photo = photo;
        this.biography = biography;
        this.flag = flag;
        this.genre = genre;
    }

    //Getters and Setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public String getBiography() { return biography; }

    public void setBiography(String biography) { this.biography = biography; }

    public String getFlag() { return flag; }

    public void setFlag(String flag) { this.flag = flag; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }



}
