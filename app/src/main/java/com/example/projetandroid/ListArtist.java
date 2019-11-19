package com.example.projetandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class ListArtist extends ListFragment implements AdapterView.OnItemClickListener  {

    DatabaseHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_artist, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DatabaseHelper(getContext());
        Cursor cursor = db.getAllArtistWith("Jazz");
        ArrayList<String> listartist = new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            listartist.add(cursor.getString(0));
        }
        String[] arr = new String[listartist.size()];
        arr=listartist.toArray(arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,listartist);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
