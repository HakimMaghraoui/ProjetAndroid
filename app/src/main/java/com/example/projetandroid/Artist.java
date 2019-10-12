package com.example.projetandroid;

import android.media.Image;

public class Artist {
    private String surname;
    private String name;
    private Image photo;
    private String biography;
    private String flag;
    private String genre;

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Image getPhoto() { return photo; }

    public void setPhoto(Image photo) { this.photo = photo; }

    public String getBiography() { return biography; }

    public void setBiography(String biography) { this.biography = biography; }

    public String getFlag() { return flag; }

    public void setFlag(String flag) { this.flag = flag; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }
}
