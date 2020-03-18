package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progmobileproject.Classes.Film;

public class ViewFilmActivity extends AppCompatActivity {

    TextView texteTitreFilm;
    TextView texteAnneeFilm;
    TextView texteGenreFilm;
    LinearLayout layoutActeurs;
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
        layoutActeurs = (LinearLayout) findViewById(R.id.layoutActeurs);
        texteResumeFilm = (TextView) findViewById(R.id.resumeFilm);

        Intent intent = getIntent();
        //on recupere l'id du film clique , pour aller le chercher dans la bdd et l'afficher
        long filmId = intent.getLongExtra("filmId",-1);

        //recuperer le dvd depuis la bdd
        //film = Film.getFilm(this, filmId);





    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //INTERNATIONNALISATION des mots
        texteTitreFilm.setText(String.format(getString(R.string.titre_du_film),film.getTitre()));
        texteAnneeFilm.setText(String.format(getString(R.string.annee_de_sortie),film.getAnnee()));
        texteGenreFilm.setText(String.format(getString(R.string.genre),film.getGenre()));
        texteResumeFilm.setText(String.format(getString(R.string.resume),film.getResume()));
        //ON RECUPERE LA LISTE DES ACTEURS
        /*for(String acteur : film.getActeur()){
            TextView tv = new TextView(this);
            //on recupere l'acteur on le met dans un tv puis on lajoute au layout acteurs pour l'afficher
            tv.setText(String.format(getString(R.string.acteurs),acteur));
            layoutActeurs.addView(tv);
        }*/
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
