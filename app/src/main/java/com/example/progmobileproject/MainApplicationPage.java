package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.progmobileproject.Classes.Film;

import java.util.ArrayList;

public class MainApplicationPage extends AppCompatActivity {

    ImageView logo_connection;
    ListView list_films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_application_layout);


        logo_connection = (ImageView)findViewById(R.id.LogoConnexion);
        list_films = (ListView)findViewById(R.id.list_films);


        //quand on clique sur un element de la liste on est rediriger vers son contenu
        list_films.setOnItemClickListener(//lambda expression avec l'id du film que l'utilisateur a choisit
                (parent, view, position, id) -> {
                    startViewDVDActivity(id);
                }
        );
    }

    //methode pour commencer une activite qui affiche les infos sur le film entre en parametre
    private void startViewDVDActivity(long filmId)
    {
        Intent intent = new Intent(this, ViewFilmActivity.class);
        intent.putExtra("filmId",filmId);//on envoie le film id a l'acitivité suivante pour pouvoir recuperer les infos sur le film et l'afficher
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //on recupere la liste des film depuis la base de données et on la met dans une liste
        ArrayList<Film> filmList = Film.getFilmList(this);
        FilmAdapter filmAdapter = new FilmAdapter(this, filmList);
        //on met la liste dans l'adapter qui sera affiché via layout_affichage_film_miniature
        list_films.setAdapter(filmAdapter);
    }
}
