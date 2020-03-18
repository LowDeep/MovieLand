package com.example.progmobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class PageAcceuilModeConnecte extends AppCompatActivity {


    Button ajouter_film;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil_mode_connecte);

        ajouter_film = (Button)findViewById(R.id.ajout_film_button);
        ajouter_film.setOnClickListener(v -> {
            Intent it = new Intent(this,AddFilmActivity.class);
            startActivity(it);
        });
    }

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

                return true;
            case R.id.action_modifier_mes_infos:
                Intent itback = getIntent();
                 it = new Intent(this,ModifCompteActivity.class);
                Bundle bd = itback.getExtras();
                it.putExtra("username",bd.getBundle("username").toString());
                it.putExtra("email",bd.getBundle("email").toString());
                it.putExtra("password",bd.getBundle("password").toString());

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

