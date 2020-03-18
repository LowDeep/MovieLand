package com.example.progmobileproject.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSQLOpenHelper extends SQLiteOpenHelper
{
    static String DB_NAME = "accounts.db";
    static int DB_VERSION = 1;
    public LocalSQLOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String create_table_accounts = "CREATE TABLE accounts ( username TEXT PRIMARY KEY, email TEXT , password TEXT)";
        String create_table_movies = "CREATE TABLE movies ( id INTEGER PRIMARY KEY ,titre TEXT , annee INTEGER , acteurs TEXT,resume TEXT , genre TEXT , pathImage BLOB, username String)";


        db.execSQL(create_table_movies);
        db.execSQL(create_table_accounts);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
