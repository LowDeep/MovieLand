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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.progmobileproject.Classes.Compte;

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
    Button bouton_enregistreImage;

    private ImageView imgAffichePhoto;
    private String photoPath = null;

    Uri image_uri;


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
      //  bouton_enregistreImage =findViewById(R.id.bouton_Enregimage);
       // imgAffichePhoto = (ImageView)findViewById(R.id.imgView);

        awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);

        //mimageview=findViewById(R.id.imageView);

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
                    openCamera();
                }
            }else{
                // system os < marshmallow
                openCamera();
            }


        });



    }

    private void openCamera() {

        //camera intent
        Intent cameraIntent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //tester que l'intent peut etre gerer
        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            //creer un nom de fichier pour le fichier temporaire, nom unique le nom du fichier on l'instancie à time
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time,".jpg",photoDir);
                //enregistrer le chemain complet de la photo
                photoPath = photoFile.getAbsolutePath();
                //creer l'uri le code pr acceder au fichier
                Uri photoUri = FileProvider.getUriForFile(InscriptionActivity.this,
                        InscriptionActivity.this.getApplicationContext().getPackageName()+".provider",
                        photoFile);
                //transfert uri vers l'intent pour l'enregistrement photo dans fichier temporaire
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                //ouvrir l'activity par rapport à l'intent
                startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //called when imag ewas captured from camera
        if(resultCode == RESULT_OK){
            //set the image captured to imageView
            imgAffichePhoto.setImageURI(image_uri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this methode called when user presses allow or deny from permission request popup
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission granted
                    openCamera();
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




}