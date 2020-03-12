package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.progmobileproject.Classes.Compte;
import com.example.progmobileproject.R;

public class ModifCompteActivity extends AppCompatActivity {

    Button btn_enregistrerModif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_compte);
        EditText username = (EditText) findViewById(R.id.PseudoM);
        EditText mail = (EditText) findViewById(R.id.EmailM);
        EditText pass = (EditText) findViewById(R.id.PasswordM);

        Bundle extras = this.getIntent().getExtras();

        Integer idExtra=extras.getInt("idC");
        String usernameExtra=extras.getString("username");
        String mailExtra=extras.getString("mail");

        username.setText(usernameExtra);
        mail.setText(mailExtra);

        btn_enregistrerModif = (Button) findViewById(R.id.EnregistrerM);

        //definition bouton connexion
        btn_enregistrerModif.setOnClickListener(v->{
            EditText usernameF = (EditText) findViewById(R.id.PseudoM);
            EditText mailF = (EditText) findViewById(R.id.EmailM);
            EditText passF = (EditText) findViewById(R.id.PasswordM);
            if ((usernameF.toString()!="")&&(mailF.toString()!="")&&(passF.toString()!="")){
                Compte compte=new Compte(usernameF.toString(), mailF.toString(),passF.toString());
                //Compte.UpdateCompte(idExtra, compte, this);// a refaire
            }
            //AFFICHER MESSAGE OK VOS MODIF SONT REALISEES
        });
    }


}
