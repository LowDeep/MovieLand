package com.example.progmobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.progmobileproject.Classes.Film;

import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {

    //pas besoin de cette classe je le fais dans le code exemple page accueil mode connecte
    Context context;

    public FilmAdapter(Context context , List<Film> listFilm){
        super(context,-1, listFilm);
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_dvd,parent);
        }else{
            view = convertView;
        }
        Film film = getItem(position);

        TextView titre = (TextView)view.findViewById(R.id.listItemDVD_titre);
        TextView genre = (TextView)view.findViewById(R.id.listItemDVD_genre);
        TextView resume = (TextView)view.findViewById(R.id.listItemDVD_resume);



        titre.setText(film.getTitre());
        genre.setText(String.valueOf(film.getGenre()));
        resume.setText(film.getResume());

        return view;
    }


}
