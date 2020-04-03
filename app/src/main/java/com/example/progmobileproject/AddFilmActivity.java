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

    String userName;
    String email;
    String password;

    ArrayList<EditText> acteursArrayList = new ArrayList<>();

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

            ArrayList<String>  actorsList = new ArrayList<>();
            for (EditText acteur : acteursArrayList){
                    actorsList.add(acteur.getText().toString());
               }


           Log.i("acteurs",actorsList.toString());//affiche mauvais truc

            Intent it = new Intent(this,PageAcceuilModeConnecte.class);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userName = extras.getString("username");
                email = extras.getString("email");
                password = extras.getString("password");
            }
            it.putExtra("username",userName);
            it.putExtra("email",email);
            it.putExtra("password",password);

            Film movie = new Film(editTitreFilm.getText().toString(),
                    Integer.valueOf(editAnnee.getText().toString()),
                    actorsList.toString() ,//Acteurs savedInstanceState.getStringArray("acteurs")
                    editResume.getText().toString(),
                    editGenre.getText().toString(),
                    null,
                    userName);//pathimage

            Log.i("ACTEURS MOVIE",movie.getActeurs());


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
        acteursArrayList.add(new EditText(this));

        //prendre le dernier editText créé
        EditText editNewActeur = acteursArrayList.get(acteursArrayList.size() - 1);
        //pour gerer l'entrée utilisateur vu que c'est gerer dynamiquement on met ici et non pas dans le fichier xml
        editNewActeur.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        if(content != null)
        {
            editNewActeur.setText(content);

        }
        addActeursLayout.addView(editNewActeur);
    }





}
