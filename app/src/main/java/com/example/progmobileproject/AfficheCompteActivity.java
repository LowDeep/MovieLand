package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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
        String pathImage = extras.getString("pathImage");

        //Log.i("imagePath affiche",pathImage);

        EditText pass = (EditText) findViewById(R.id.PasswordM);

        textUsername=(TextView)findViewById(R.id.textUsername);
        textemail=(TextView)findViewById(R.id.textEmail);
        textepassword=(TextView)findViewById(R.id.textPassword);
        imageCompte = (ImageView)findViewById(R.id.imageCompte);


        textUsername.setText(usernameExtra);
        textemail.setText(mailExtra);
        textepassword.setText(passExtra);
        imageCompte.setImageBitmap(StringToBitmap(pathImage));







        }



    //methode pour changer un string a un bitmap
    private Bitmap StringToBitmap(String picture){
        Bitmap bitmap = null;
        byte [ ] decoString = android.util.Base64.decode(picture, android.util.Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(decoString,0,decoString.length);
        return bitmap;

    }


}
