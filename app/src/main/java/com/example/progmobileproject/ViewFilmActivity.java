package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progmobileproject.Classes.Film;

public class ViewFilmActivity extends AppCompatActivity {

    TextView texteTitreFilm;
    TextView texteAnneeFilm;
    TextView texteGenreFilm;
    TextView texteActeurs;
    TextView texteResumeFilm;

    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on affiche le fichier xml qui contient les ressources
        setContentView(R.layout.activity_view_film);



        //onrecupere les textview a partir des id
        texteTitreFilm = (TextView) findViewById(R.id.titreFilm);
        texteAnneeFilm = (TextView) findViewById(R.id.anneeFilm);
        texteGenreFilm = (TextView) findViewById(R.id.genreFilm);
        texteActeurs = (TextView) findViewById(R.id.acteursFilm);
        texteResumeFilm = (TextView) findViewById(R.id.resumeFilm);

        Intent intent = getIntent();
        //on recupere l'id du film clique , pour aller le chercher dans la bdd et l'afficher
        long filmId = intent.getLongExtra("filmId",-1);

        Log.i("id film",String.valueOf(filmId));
        //recuperer le dvd depuis la bdd
        film = Film.getFilm(this, filmId);

        Log.i("valeurs film",film.getActeurs());

        texteTitreFilm.setText(film.getTitre().toString());
        texteAnneeFilm.setText(String.valueOf(film.getAnnee()));
        texteGenreFilm.setText(film.getGenre());
        texteActeurs.setText(film.getActeurs());
        texteResumeFilm.setText(film.getResume());


        Button navig =findViewById(R.id.Infos);
        navig.setOnClickListener(v -> {
                String url = "https://www.youtube.com/results?search_query="+film.getTitre().toString();
                Intent intent1 = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                startActivity(intent1);
        });







    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //INTERNATIONNALISATION des mots

        String acteurs="";
        //ON RECUPERE LA LISTE DES ACTEURS
        /*for(String acteur : film.getActeurs()){
            /*TextView tv = new TextView(this);
            //on recupere l'acteur on le met dans un tv puis on lajoute au layout acteurs pour l'afficher
            tv.setText(String.format(getString(R.string.acteurs),acteur));
            layoutActeurs.addView(tv);
            acteurs+=acteur+"\n";
        }
*/
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
