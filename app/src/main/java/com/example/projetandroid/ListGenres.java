package com.example.projetandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import java.util.ArrayList;

public class ListGenres extends ListFragment implements AdapterView.OnItemClickListener {
    private DatabaseHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_genres, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db=new DatabaseHelper(getContext());
        Cursor cursor= db.getAllGenre();
        ArrayList<String> genres = new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            genres.add(cursor.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,genres);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), ActivityArtist.class);
        TextView textView=(TextView) view.findViewById(android.R.id.text1);
        String text= textView.getText().toString();
        intent.putExtra("genre",text);
        startActivity(intent);
    }
}
