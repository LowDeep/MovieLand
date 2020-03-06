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
            view = layoutInflater.inflate(R.layout.layout_affichage_film_miniature,parent);
        }else{
            view = convertView;
        }
        Film film = getItem(position);

        TextView titre = (TextView)view.findViewById(R.id.listeItemDVD_titre);
        TextView genre = (TextView)view.findViewById(R.id.listeItemDVD_genre);
        TextView resume = (TextView)view.findViewById(R.id.listItemDVD_resume);



        titre.setText(film.getTitre());
        genre.setText(String.valueOf(film.getGenre()));
        resume.setText(film.getResume());

        return view;
    }


}
