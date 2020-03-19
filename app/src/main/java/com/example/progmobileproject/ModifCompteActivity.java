package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobileproject.Classes.Compte;
import com.example.progmobileproject.R;

public class ModifCompteActivity extends AppCompatActivity {

    Button btn_enregistrerModif;
    EditText ancienpass;
    EditText mail;
    EditText pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_compte);
        ancienpass = (EditText) findViewById(R.id.PasswordAncien);
        mail = (EditText) findViewById(R.id.EmailM);
        pass = (EditText) findViewById(R.id.PasswordM);

        Bundle extras = this.getIntent().getExtras();

        String username=extras.getString("username");
        String password=extras.getString("password");
        String mailExtra=extras.getString("email");


        btn_enregistrerModif = (Button) findViewById(R.id.EnregistrerM);

        //definition bouton connexion
        btn_enregistrerModif.setOnClickListener(v->{
            Intent it = new Intent(this,PageAcceuilModeConnecte.class);
            EditText mailF = (EditText) findViewById(R.id.EmailM);
            EditText passF = (EditText) findViewById(R.id.PasswordM);
            if (ancienpass.equals(pass)){
                Compte compte=new Compte(username, mailF.toString(),passF.toString());
                compte.UpdateCompte(this);// a refaire
            }else{
                Toast.makeText(this,"Mauvais mot de passe",Toast.LENGTH_LONG);
            }

            it.putExtra("username", username);
            it.putExtra("email",mailF.getText().toString());
            it.putExtra("password",passF.getText().toString());

            startActivity(it);

        });
    }


}
