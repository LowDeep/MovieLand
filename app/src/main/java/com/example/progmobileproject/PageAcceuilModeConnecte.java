package com.example.progmobileproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

    ListView listview ;


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
        }


        //listview des qui affiche les films
        listview = (ListView) findViewById(R.id.user_list_films);


        Film f = new Film();
        //Log.i("username", userName);
        ArrayList<Film> listefilms = f.getMyfilmList(this,userName);
        //Log.i("list",listefilms.toString());

        FilmAdapter adapter = new FilmAdapter(this, listefilms);
        listview.setAdapter(adapter);

        ajouter_film = (Button)findViewById(R.id.ajout_film_button);
        ajouter_film.setOnClickListener(v -> {
            Intent it = new Intent(this,AddFilmActivity.class);

            it.putExtra("username", userName);
            it.putExtra("email", email);
            it.putExtra("password",password);
            startActivity(it);
        });

    }



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

                startActivity(it);

                return true;
            case R.id.action_modifier_mes_infos:
                 it = new Intent(this,ModifCompteActivity.class);
                it.putExtra("username",userName);
                it.putExtra("email",email);
                it.putExtra("password",password);

                startActivity(it);

                return true;
            case R.id.action_deconnection:
                it = new Intent(this,MainApplicationPage.class);
               startActivity(it);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

