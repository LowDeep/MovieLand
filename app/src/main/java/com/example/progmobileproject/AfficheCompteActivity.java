package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AfficheCompteActivity extends AppCompatActivity {

    Button btn_enregistrerModifCompte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_compte);

        Bundle extras = this.getIntent().getExtras();

        Integer idExtra=extras.getInt("idC");
        String usernameExtra=extras.getString("username");
        String mailExtra=extras.getString("mail");
        String passExtra=extras.getString("mail");

        EditText pass = (EditText) findViewById(R.id.PasswordM);

        TextView t1=(TextView)findViewById(R.id.textUsername);
        TextView t2=(TextView)findViewById(R.id.textEmail);
        TextView t3=(TextView)findViewById(R.id.textPassword);

        t1.setText(usernameExtra);
        t2.setText(mailExtra);
        t3.setText(passExtra);


        btn_enregistrerModifCompte = (Button) findViewById(R.id.ModifCompte);

        //definition bouton direction vers la page de modification du compte
        btn_enregistrerModifCompte.setOnClickListener(v->{
            Intent it = new Intent(this,ModifCompteActivity.class);
            it.putExtra("idC", idExtra);
            it.putExtra("username", usernameExtra);
            it.putExtra("mail", mailExtra);
            it.putExtra("password", passExtra);
            startActivity(it);

        });

    }


}
