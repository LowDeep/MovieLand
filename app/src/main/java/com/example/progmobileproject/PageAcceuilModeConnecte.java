package com.example.progmobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PageAcceuilModeConnecte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil_mode_connecte);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_connecte,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_mon_compte:
                Toast.makeText(this,"FAUT LANCER INTENTION POUR AFFICHER LES INFOS DU COMPTe",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_modifier_mes_infos:
                Toast.makeText(this,"FAUT LANCER INTENTION POUR modifier les infos DU COMPTe",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_deconnection:
                Toast.makeText(this,"FAUT LANCER INTENTION deconnection => revenir page d'accueil de l'appli",Toast.LENGTH_LONG);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

