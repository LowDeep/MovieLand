package com.example.progmobileproject;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.progmobileproject.Classes.Compte;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InscriptionActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 2 ;
    private static final int IMAGE_CAPTURE_CODE = 1;
    EditText editText_username;
    EditText editText_password;
    EditText editText_validate_password;
    EditText editText_email;
    //defining AwesomeValidation object
    AwesomeValidation awesomeValidation;

    Button bouton_enregistrer;
    Button bouton_takeimage;

    ImageView imageView;

   private String pathImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        editText_email = findViewById(R.id.textEmail);
        editText_password = findViewById(R.id.textPassword);
        editText_validate_password = findViewById(R.id.textValidatePassword);
        editText_username = findViewById(R.id.textUsername);
        bouton_enregistrer = findViewById(R.id.bouton_enregistrer);
        bouton_takeimage=findViewById(R.id.bouton_takephoto);
        imageView = findViewById(R.id.imageView);

        awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);

        //mimageview=findViewById(R.id.imageView);

        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.textEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this,R.id.textValidatePassword,R.id.textPassword,R.string.passworderror);

        bouton_enregistrer.setOnClickListener(v -> {
            //si on clique on verifie que le formulaire est bien
            if( submitForm()){
                Intent it = new Intent(this,ConnectionActivity.class);
                Compte account;
                //on cree un compte et on l'enregistre dans la base de donnée
                if(pathImage == null){ //si il y a pas d'image on cree le compte sans image sinon le cree le compte avec le pathimage
                    account = new Compte(editText_username.getText().toString(),
                        editText_email.getText().toString(),
                        editText_password.getText().toString(),"");}else{
                    account = new Compte(editText_username.getText().toString(),
                            editText_email.getText().toString(),
                            editText_password.getText().toString(),
                            pathImage);

                }
                account.insererCompte(this);
                Log.i("compte path image",account.getPathImage());
                startActivity(it);
            }
        });

        //bouton prendre une photo
        bouton_takeimage.setOnClickListener(v->{
            //if request runtime permission already checked
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    //permissions not enabled request it
                    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //show popup to request permissions
                    requestPermissions(permissions, PERMISSION_CODE);
                }else{
                    //permission already granted
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                }
            }else{
                // system os < marshmallow
                  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  startActivityForResult(intent,0);

            }


        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //called when imag ewas captured from camera
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        pathImage=BitmapToString(bitmap);
       // Log.i("Imagepath",pathImage);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this methode called when user presses allow or deny from permission request popup
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission granted
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                }else{
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //methode verification formulaire inscription
    private boolean submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            return true;
        }return false;
    }

    //methode pour changer une image en string pour la stocker dans la bdd
    private String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
        byte [ ] byte_arr = stream.toByteArray();
        return android.util.Base64.encodeToString(byte_arr,0);

    }

}