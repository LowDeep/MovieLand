package com.example.progmobileproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.progmobileproject.Classes.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {

    Context context;
    String filmTitle[];
    String filmYear[];
    String filmGender[];
    String filmResume[];
    ArrayList<Film> listefilms;


    //int images[];

    public FilmAdapter(Context c, String title[], String year[], String gender[], String resume[]){

        super(c,R.layout.listitem_dvd,R.id.listItemDVD_titre);
        this.context = c;
        this.filmTitle = title;
        this.filmYear=year;
        this.filmGender=gender;
        this.filmResume=resume;
    }

    public FilmAdapter(Context c, ArrayList<Film> listefilms){

        super(c,0,listefilms);
        this.context = c;
        this.listefilms = listefilms;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View item = layoutInflater.inflate(R.layout.listitem_dvd,parent,false);

        TextView myTitle = (TextView)item.findViewById(R.id.listItemDVD_titre);
        TextView myAnne = (TextView)item.findViewById(R.id.listItemDVD_annee);
        TextView myGenre = (TextView)item.findViewById(R.id.listItemDVD_genre);
        TextView myResume = (TextView)item.findViewById(R.id.listItemDVD_resume);



        myTitle.setText(listefilms.get(position).getTitre());
        myAnne.setText(String.valueOf(listefilms.get(position).getAnnee()));
        myGenre.setText(listefilms.get(position).getGenre());
        myResume.setText(listefilms.get(position).getResume());

        /*
        item.setOnClickListener(v -> {
            Intent it = new Intent(context, ViewFilmActivity.class);
            //ICI??

                }

        );*/
        return item;
    }






}
