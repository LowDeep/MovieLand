package com.example.progmobileproject.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.progmobileproject.DataBase.LocalSQLiteOpenHelper;

public class Compte {

    private int idCompte;

    public int getIdCompte() {
        return idCompte;
    }

    private String username;
    private String email;
    private String password;

    public Compte(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public boolean InsertCompte(Context context) {

        ContentValues content = new ContentValues();

        content.put("username", this.getUsername());
        content.put("email", this.getEmail());
        content.put("password", this.getPassword());
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getWritableDatabase();
        long result = db.insert("Comptes", null, content);
        if (result == -1) {
            System.out.println("insertion false");
            return false;
        } else {
            System.out.println("insertion true");
            return true;
        }

    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public Compte getCompteByuserNameAndPassword(Context context) {

        Compte compte = null;
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();/*
        String sqlSelect = "Select * from Comptes where username="+this.getUsername()+ " and password="+this.getPassword();
        Cursor c=db.rawQuery(sqlSelect, null);
        if (c != null) {
            System.out.println("cursor non null");
        }
        return c;*/
        String where = "username="+this.getUsername()+" and password = "+this.getPassword();
        Cursor cursor = db.query(false,"Comptes", new String[] {"idC","username","email","password"},where,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            compte = new Compte(cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("password")));
            compte.setIdCompte(cursor.getInt(cursor.getColumnIndex("idC")));
        }

        cursor.close();
        db.close();

        return compte;
    }


    public void UpdateCompte(Integer idC, Context context) {

        ContentValues content = new ContentValues();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("Update Comptes set username=" + this.getUsername() + "," + " mail=" + this.getEmail() + "," +
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
                    resu += cursor.getString(cursor.getColumnIndex("mail"));
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
    }
}