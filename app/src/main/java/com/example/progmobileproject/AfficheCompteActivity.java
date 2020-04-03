package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progmobileproject.Classes.Compte;

import java.io.File;

public class AfficheCompteActivity extends AppCompatActivity {

    TextView textUsername;
    TextView textemail;
    TextView textepassword;
    ImageView imageCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_compte);

        Bundle extras = this.getIntent().getExtras();

        String usernameExtra=extras.getString("username");
        String mailExtra=extras.getString("email");
        String passExtra=extras.getString("password");

        EditText pass = (EditText) findViewById(R.id.PasswordM);

        textUsername=(TextView)findViewById(R.id.textUsername);
        textemail=(TextView)findViewById(R.id.textEmail);
        textepassword=(TextView)findViewById(R.id.textPassword);
        imageCompte = (ImageView)findViewById(R.id.imageCompte);


        textUsername.setText(usernameExtra);
        textemail.setText(mailExtra);
        textepassword.setText(passExtra);


        Compte compte = new Compte();

        //compte = compte.getImagePathByUsername(this,usernameExtra);

        //File imgFile = new  File(compte.getPathImage());
/*
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            imageCompte.setImageBitmap(myBitmap);

        }*/






    }


}
