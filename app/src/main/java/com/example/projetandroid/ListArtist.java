package com.example.projetandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListArtist extends ListFragment implements AdapterView.OnItemClickListener  {
    private DatabaseHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_artist, container, false);
        return view;
    }

    //comment
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DatabaseHelper(getContext());
        Activity2 activity2 = (Activity2) getActivity();
        String genre=activity2.getGenre();
        Cursor cursor = db.getAllArtistWith(genre);

        ArrayList<String> textartist = new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            textartist.add(cursor.getString(0)+" "+cursor.getString(1));
        }
        String[] listartisttext = new String[textartist.size()];
        listartisttext=textartist.toArray(listartisttext);
        ArrayList<String>imgartist = new ArrayList<>();
        for (cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            imgartist.add(cursor.getString(2));
        }
        String[] listartistimg = new String[imgartist.size()];
        listartistimg=imgartist.toArray(listartistimg);
        List<HashMap<String,String>> aList = new ArrayList<>();
        for (int i=0; i<imgartist.size();i++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("ListText",listartisttext[i]);
            hm.put("ListImage",(listartistimg[i]));
            aList.add(hm);
        }
        String[]from={"ListText","ListImage"};
        int[]to={R.id.listartist_text,R.id.listartist_image};
        SimpleAdapter simpleAdapter= new SimpleAdapter(getContext(),aList,R.layout.listartist_items,from,to);
        getListView().setAdapter(simpleAdapter);

        //setListAdapter(simpleAdapter);

        getListView().setOnItemClickListener(this);


        /*ArrayList<String>imgartist = new ArrayList<>();
        for (cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            imgartist.add(cursor.getString(2));
        }
        ArrayList<String> nomartist = new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            nomartist.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2));
        }
        List<HashMap<String,String>> hashMaps = new ArrayList<>();
        for(int i=0;i<imgartist.size();i++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_item_text",nomartist.get(i));
            hm.put("listview_image",imgartist.get(i));
        }
        String[] from={"listview_item_text","listview_image"};
        int[] to={R.id.listview_item_text,R.id.listview_image};
        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),hashMaps,R.layout.single_list_item,from,to);
        setListAdapter(simpleAdapter);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(view.getContext(),ActivityBio.class);
        TextView textView=(TextView) view.findViewById(R.id.listartist_text);
        String text= textView.getText().toString();
        System.out.println("TEXT= "+text);
        intent.putExtra("artist",text);

        startActivity(intent);
    }
}
