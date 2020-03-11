package com.example.progmobileproject.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.progmobileproject.DataBase.LocalSQLiteOpenHelper;

public class Compte {

    private String name;
    private String mail;
    private String numero;
    private String password;

    public Compte(String n, String m, String num, String pas) {
        this.name = n;
        this.mail = m;
        this.numero = num;
        this.password = pas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean InsertCompte(Compte c, Context context) {

        ContentValues content = new ContentValues();

        content.put("username", c.getName());
        content.put("mail", c.getMail());
        content.put("password", c.getPassword());
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        long result = db.insert("Comptes", null, content);
        if (result == -1) {
            System.out.println("insertion false");
            return false;
        } else {
            System.out.println("insertion true");
            return true;
        }

    }


    public void UpdateCompte(Integer idC, Compte cnew, Context context) {

        ContentValues content = new ContentValues();

        String username = cnew.getName();
        String mail = cnew.getNumero();
        String password = cnew.getPassword();
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("Update Comptes set username=" + username + "," + " mail=" + mail + "," + " password=" + password + " where idC=" + idC, null);


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

    public Cursor getOneCompte(String idC, Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM comptes where idC=" + idC, null);
        if (c != null) {
            System.out.println("cursor non null");
        }
        return c;
    }

    public static Cursor getOneCompte(String username, String password, Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        String sqlSelect = "Select * from Comptes where username="+username+ " and password="+password;
        Cursor c=db.rawQuery(sqlSelect, null);
        if (c != null) {
            System.out.println("cursor non null");
        }
        return c;
    }

    public static boolean CompteValide(String username, String password, Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));
        SQLiteDatabase db = helper.getReadableDatabase();
        String sqlSelect = "Select * from Comptes where username="+username+ " and password="+password;
        Cursor c=db.rawQuery(sqlSelect, null);
        if(c==null) return false;
        else return true;
    }
}