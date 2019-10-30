package com.example.projetandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class GenreFragment extends Fragment {

    private static final String TAG=GenreFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<String> listeGenres= new ArrayList();
        DatabaseHelper dbs = new DatabaseHelper(getContext());
        Cursor c = dbs.getAllGenre() ;
        c.moveToFirst();
        while (c.moveToNext()){
            listeGenres.add(c.getString(c.getColumnIndex("genre")));
        }
        System.out.println("TAG = "+TAG);
        Log.d(TAG,listeGenres.get(0));
        return super.getView();
    }
}
