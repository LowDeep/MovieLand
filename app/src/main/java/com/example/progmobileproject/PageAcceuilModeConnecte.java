package com.example.progmobileproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progmobileproject.Classes.Film;

import java.util.ArrayList;
import java.util.List;

public class PageAcceuilModeConnecte extends AppCompatActivity {


    Button ajouter_film;

    String userName;
    String email;
    String password;
    String pathImage;

    ListView listview ;


    public static final String SHARED_PREFS="sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil_mode_connecte);
        Bundle extras = getIntent().getExtras();
       // userName = getIntent().getStringExtra("username")
        if (extras != null) {
            userName = extras.getString("username");
            email = extras.getString("email");
            password = extras.getString("password");
            pathImage = extras.getString("pathImage");
        }


        //listview des qui affiche les films
        listview = (ListView) findViewById(R.id.user_list_films);


        Film f = new Film();
        //Log.i("username", userName);
        ArrayList<Film> listefilms = f.getMyfilmList(this,userName);
        //Log.i("list",listefilms.toString());

        FilmAdapter adapter = new FilmAdapter(this, listefilms);
        listview.setAdapter(adapter);



        //quand on clique sur un element de la liste on est rediriger vers son contenu
        listview.setOnItemClickListener(//lambda expression avec l'id du film que l'utilisateur a choisit
                (parent, view, position, id) -> {
                    Intent it = new Intent(this,ViewFilmActivity.class);
                    it.putExtra("filmId",id+1); // il faut mettre l'id du film
                    startActivity(it);
                }
        );

        ajouter_film = (Button)findViewById(R.id.ajout_film_button);
        ajouter_film.setOnClickListener(v -> {
            Intent it = new Intent(this,AddFilmActivity.class);

            it.putExtra("username", userName);
            it.putExtra("email", email);
            it.putExtra("password",password);
            it.putExtra("pathImage",pathImage);
            startActivity(it);
        });

    }



    //methodes pour le menu

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_connecte,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it ;
        switch(item.getItemId()){
            case R.id.action_mon_compte:
                it = new Intent(this,AfficheCompteActivity.class);
                it.putExtra("username",userName);
                it.putExtra("email",email);
                it.putExtra("password",password);
                it.putExtra("pathImage",pathImage);

                startActivity(it);

                return true;
            case R.id.action_modifier_mes_infos:
                 it = new Intent(this,ModifCompteActivity.class);
                it.putExtra("username",userName);
                it.putExtra("email",email);
                it.putExtra("password",password);
                it.putExtra("pathImage",pathImage);

                startActivity(it);

                return true;
            case R.id.action_deconnection:
                it = new Intent(this,MainApplicationPage.class);

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
                SharedPreferences.Editor  editor = sharedPreferences.edit();
                editor.remove("username");
                editor.putBoolean("Connected",false);
                editor.commit();


               startActivity(it);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

