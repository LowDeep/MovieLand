package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.progmobileproject.Classes.Compte;

public class ConnectionActivity extends AppCompatActivity {

    Button btn_inscription;
    Button btn_connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connection);

        btn_inscription = (Button) findViewById(R.id.Inscription_connection);

        //definition bouton inscription pour rediriger vers la page d'inscription
        btn_inscription.setOnClickListener(v->{
            Intent it = new Intent(this , InscriptionActivity.class);
            startActivity(it);
        });

        btn_connexion = (Button) findViewById(R.id.Connexion_connection);

        //definition bouton connexion
        btn_inscription.setOnClickListener(v->{
            EditText pseudo = (EditText) findViewById(R.id.PseudoC);
            EditText pass = (EditText) findViewById(R.id.PasswordC);
            Integer idCursor=-1;
            String pseudoCursor="";
            String MailCursor="";
            String PassCursor="";
            if (Compte.CompteValide(pseudo.toString(), pass.toString(), this)) {

                Cursor cursor = Compte.getOneCompte(pseudo.toString(), pass.toString(), this);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {

                            idCursor = cursor.getInt(cursor.getColumnIndex("idC"));

                            pseudoCursor = cursor.getString(cursor.getColumnIndex("username"));

                            MailCursor = cursor.getString(cursor.getColumnIndex("mail"));

                            PassCursor = cursor.getString(cursor.getColumnIndex("password"));

                        } while (cursor.moveToNext());
                    }
                }
            }
            if (idCursor!=-1){
                Intent it = new Intent(this , PageAcceuilModeConnecte.class);
                it.putExtra("idUser", idCursor);
                it.putExtra("Username", pseudoCursor);
                it.putExtra("Mail", MailCursor);
                it.putExtra("Password", PassCursor);
                startActivity(it);
            }
            else {
                //RENITIALISER LS CHAMPS VOIR AFFICHER "CONNEXION IMPOSSIBLE"

                    pseudo.setText(null);
                    pass.setText(null);
            }

        });


    }
}
