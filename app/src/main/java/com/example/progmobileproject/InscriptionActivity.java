package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.progmobileproject.Classes.Compte;


public class InscriptionActivity extends AppCompatActivity {

    EditText editText_username;
    EditText editText_password;
    EditText editText_validate_password;
    EditText editText_email;
    //defining AwesomeValidation object
    AwesomeValidation awesomeValidation;

    Button bouton_enregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        editText_email = findViewById(R.id.textEmail);
        editText_password = findViewById(R.id.textPassword);
        editText_validate_password = findViewById(R.id.textValidatePassword);
        editText_username = findViewById(R.id.textUsername);
        bouton_enregistrer = findViewById(R.id.bouton_enregistrer);

        awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);

        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.textEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this,R.id.textValidatePassword,R.id.textPassword,R.string.passworderror);

        bouton_enregistrer.setOnClickListener(v -> {
            //si on clique on verifie que le formulaire est bien
          if( submitForm()){
           Intent it = new Intent(this,ConnectionActivity.class);
           //on cree un compte et on l'enregistre dans la base de donnée
              Compte account = new Compte(editText_username.getText().toString(),
                                            editText_email.getText().toString(),
                                            editText_password.getText().toString());
              account.insererCompte(this);

          startActivity(it);
          }
        });


    }

    private boolean submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            return true;
            }return false;
    }
}
