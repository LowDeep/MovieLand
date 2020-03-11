package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ConnectionActivity extends AppCompatActivity {

    Button btn_inscription;

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

    }
}
