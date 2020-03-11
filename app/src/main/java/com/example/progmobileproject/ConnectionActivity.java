package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ConnectionActivity extends AppCompatActivity {

    Button btn_inscription;
    Button btn_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connection);

        btn_inscription = (Button) findViewById(R.id.Inscription_connection);

        //definition bouton inscription pour rediriger vers la page d'inscription
        btn_inscription.setOnClickListener(v->{
            Intent it = new Intent(this , InscriptionActivity.class);
            //LES METTRE SUR UN COMPTE ET AJOUTER LE COMPTE DANS LA BDD
            startActivity(it);
        });

        btn_connection = (Button) findViewById(R.id.Connexion_connection);
        btn_connection.setOnClickListener( v->{
            Intent it = new Intent(this, PageAcceuilModeConnecte.class);
            //RECUPERER TOUTES LES INFORMATIONS SUR LES EDIT TEXT
            //VERIFIER QUE LE COMPTE EXISTE DEJA
           

            startActivity(it);
        });


    }
}
