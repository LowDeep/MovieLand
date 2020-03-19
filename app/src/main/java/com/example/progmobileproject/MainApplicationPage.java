package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.progmobileproject.Classes.Film;

import java.util.ArrayList;

public class MainApplicationPage extends AppCompatActivity {

    ImageView logo_connection;
    ListView list_films;
    ArrayList<Film> listeFilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_application_layout);


        logo_connection = (ImageView)findViewById(R.id.LogoConnexion);
        list_films = (ListView)findViewById(R.id.list_films);

        Film f = new Film();
        //Log.i("username", userName);
        ArrayList<Film> listefilms = f.getFilmList(this);
        //Log.i("list",listefilms.toString());

        FilmAdapter adapter = new FilmAdapter(this, listefilms);
        list_films.setAdapter(adapter);



        //quand on clique sur un element de la liste on est rediriger vers son contenu
        list_films.setOnItemClickListener(//lambda expression avec l'id du film que l'utilisateur a choisit
                (parent, view, position, id) -> {
                    Intent it = new Intent(this,ViewFilmActivity.class);
                    it.putExtra("filmId",id+1);
                    startActivity(it);
                }
        );


        //quand on clique sur le logo de connection on est rediriger vers la page de connection
        logo_connection.setOnClickListener(v->{
            Intent it = new Intent(this , ConnectionActivity.class);
            startActivity(it);
        });

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

    }
}
