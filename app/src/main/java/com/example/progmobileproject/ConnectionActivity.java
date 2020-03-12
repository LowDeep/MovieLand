package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobileproject.Classes.Compte;

public class ConnectionActivity extends AppCompatActivity {

    Button btn_inscription;
    EditText text_username;
    EditText text_password;
    Button btn_connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connection);

        btn_inscription = (Button) findViewById(R.id.Inscription_connection);
        text_username = (EditText) findViewById(R.id.PseudoC);
        text_password = (EditText) findViewById(R.id.PasswordC);
        btn_connexion = (Button) findViewById(R.id.Connexion_connection);

        //definition bouton inscription pour rediriger vers la page d'inscription
        btn_inscription.setOnClickListener(v->{
            Intent it = new Intent(this , InscriptionActivity.class);
            startActivity(it);
        });

        btn_connexion.setOnClickListener(v ->{
            Intent it = new Intent(this,PageAcceuilModeConnecte.class);

            //on cherche si le compte existe bien dans la base de données si oui on commence l'intent
            //sinon on affiche un message d'erreur
            Compte compte = new Compte(text_username.getText().toString(),null,
                                        text_password.getText().toString());
            Compte compteRecup = compte.getCompteByuserNameAndPassword(this);

            if(compteRecup !=null){
            it.putExtra("idCompte",compteRecup.getIdCompte());

            startActivity(it);}else{
                Toast.makeText(this, "Compte invalide", Toast.LENGTH_LONG).show();
            }

        });



    }
}
