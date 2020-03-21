package com.example.progmobileproject;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.progmobileproject.Classes.Compte;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InscriptionActivity extends AppCompatActivity {

    EditText editText_username;
    EditText editText_password;
    EditText editText_validate_password;
    EditText editText_email;
    //defining AwesomeValidation object
    AwesomeValidation awesomeValidation;

    Button bouton_enregistrer;
    Button bouton_takeimage;
    Button bouton_showimage;

    private ImageView mimageview;
    private static final int REQUEST_IMAGE_CAPTURE=101;

    String currentImagePath=null;
    private static final int IMAGE_REQUEST=1;
    Uri uriImage;


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
        bouton_showimage=findViewById(R.id.bouton_showimage);

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

        bouton_takeimage.setOnClickListener(v->{

            captureImage();
        });

        bouton_showimage.setOnClickListener(v->{
                showImage(this.getCurrentFocus());
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

    public void takePicture(){
        Intent imageTakeIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (imageTakeIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(imageTakeIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_IMAGE_CAPTURE&& resultCode==RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mimageview.setImageBitmap(imageBitmap);
        }
    }

    public void captureImage(){
        Intent cameraIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager())!=null){
            File imageFile=null;
            try {
                imageFile=getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageFile!=null){
                Uri imageUri= FileProvider.getUriForFile(this,"com.example.android.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent, IMAGE_REQUEST);
            }

        }
    }

    public void showImage(View view){
        Intent intent=new Intent(this,ShowImage.class);
        intent.putExtra("image_path",currentImagePath);
        startActivity(intent);
    }

    private File getImageFile()throws IOException {
        String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName="jpg_"+timestamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile=File.createTempFile(imageName, ".jpg", storageDir);
        currentImagePath=imageFile.getAbsolutePath();
        return imageFile;

    }
}
