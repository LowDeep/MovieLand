package com.example.progmobileproject.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.progmobileproject.DataBase.LocalSQLOpenHelper;

public class Compte {


    private String username;
    private String email;
    private String password;

    //contructeur depuis des strings
    public Compte(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    //constructeur vide
    public Compte(){};

    //constructeur dpeuis un cursor
    public Compte(Cursor cursor){

        this.username = cursor.getString(cursor.getColumnIndex("username"));
        this.email = cursor.getString(cursor.getColumnIndex("email"));
        this.password = cursor.getString(cursor.getColumnIndex("password"));

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    //Fonction pour ajouter une instance account dans la bdd
    public  void insererCompte(Context context){



        ContentValues values = new ContentValues();
        values.put("username",this.getUsername());
        values.put("email",this.getEmail());
        values.put("password",this.getPassword());


        //insertion
        LocalSQLOpenHelper helper = new LocalSQLOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("accounts",null,values);
        db.close();

        Toast.makeText(context, "Inscription réussie!",Toast.LENGTH_SHORT).show();

    }



    //obtenir dvd depuis son usenrmae et password
    public static Compte getAccoutByUsernamePassword(Context context , String username, String password)
    {
        Compte retour = null;
        LocalSQLOpenHelper helper = new LocalSQLOpenHelper((context));

        SQLiteDatabase db = helper.getReadableDatabase();
        String where = "username = '" +username +"' and password = '"+password+"'";
        Cursor cursor = db.query(true,"accounts", new String[] {"username","email","password"},where,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            retour = new Compte(cursor);
        }

        cursor.close();
        db.close();

        return retour;
    }

/*
    public void UpdateCompte(Integer idC, Context context) {

        ContentValues content = new ContentValues();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("Update Comptes set username=" + this.getUsername() + "," + " email=" + this.getEmail() + "," +
                " password=" + this.getPassword() + " where idC=" + idC, null);
    }

    public Cursor getAllComptes(Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Comptes", null);
        if (c != null) {
            System.out.println("cursor non null");
        }
        return c;
    }


    public String showData(Cursor cursor, Context context) {
        String resu = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    resu += cursor.getString(cursor.getColumnIndex("username"));
                    resu += ";";
                    resu += cursor.getString(cursor.getColumnIndex("email"));
                    resu += ";";
                    resu += cursor.getString(cursor.getColumnIndex("password"));
                    resu += "\n";


                } while (cursor.moveToNext());
            }
        }
        return resu;
    }

    public Cursor getCompteById(String idC, Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM comptes where idC=" + idC, null);
        if (c != null) {
            System.out.println("cursor non null");
        }
        return c;
    }



    public boolean CompteValide(String username, String password, Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        String sqlSelect = "Select * from Comptes where username="+username+ " and password="+password;
        Cursor c=db.rawQuery(sqlSelect, null);
        if(c==null) return false;
        else return true;
    }*/
}