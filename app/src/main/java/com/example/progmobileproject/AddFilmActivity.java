package com.example.progmobileproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progmobileproject.Classes.Film;

import java.util.ArrayList;

public class AddFilmActivity extends AppCompatActivity {



    LinearLayout addActeursLayout;
    EditText editTitreFilm;
    EditText editAnnee;
    EditText editResume;
    EditText editGenre;
    Button btnOk;
    Button btnAddActeur;

    ArrayList<EditText> acteurs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        editTitreFilm = (EditText)findViewById(R.id.addFilm_titre);
        editAnnee = (EditText)findViewById(R.id.addFilm_annee);
        editResume = (EditText)findViewById(R.id.addDVD_resume);
        editGenre = (EditText)findViewById(R.id.addFilm_genre);

        btnAddActeur = (Button)findViewById(R.id.addFilm_addActeur);
        btnOk = (Button)findViewById(R.id.addDVD_ok);
        addActeursLayout = (LinearLayout)findViewById(R.id.addFilm_addActeurLayout);

        //Déclaration du CLIQUE SUR LE BOUTON +
        btnAddActeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActeur(null);
            }
        });
        //est ce que c'est une recréation suite a une rotation de l'écran ?
        if(savedInstanceState != null)
        {
            String [] listeActeurs = savedInstanceState.getStringArray("acteurs");
            for(String s : listeActeurs)
            {
                addActeur(s);
            }

        }else{
            //Aucun acteur saisi, on affiche un composant edit Text vide
            //addActeur(null);
        }

        //action bouton ajouter film
        btnOk.setOnClickListener(v -> {
            for (EditText acteur : acteurs)
                Log.i("Acteur", acteur.getText().toString());

            Intent it = new Intent(this,PageAcceuilModeConnecte.class);

            Film movie = new Film(editTitreFilm.getText().toString(),
                    Integer.valueOf(editAnnee.getText().toString()),
                   null ,//Acteurs savedInstanceState.getStringArray("acteurs")
                    editResume.getText().toString(),
                    editGenre.getText().toString(),
                    null);//pathimage



            movie.insert(this);
            startActivity(it);

        });

    }
    //pr sauvegarder les acteurs fils
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        String[] acteurs = new String[addActeursLayout.getChildCount()];
        for(int i=0; i<addActeursLayout.getChildCount();i++)
        {
            View child = addActeursLayout.getChildAt(i);
            if(child instanceof  EditText)
            {
                acteurs[i] = ((EditText)child).getText().toString();
            }
        }
        //Ajouter les acteurs dans un StringArray
        outState.putStringArray("acteurs",acteurs);
        super.onSaveInstanceState(outState);
    }
    private void addActeur(String content){
        //creer un nouveau editText
        acteurs.add(new EditText(this));

        //prendre le dernier editText créé
        EditText editNewActeur = acteurs.get(acteurs.size() - 1);
        //pour gerer l'entrée utilisateur vu que c'est gerer dynamiquement on met ici et non pas dans le fichier xml
        editNewActeur.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        if(content != null)
        {
            editNewActeur.setText(content);

        }
        addActeursLayout.addView(editNewActeur);
    }





}
