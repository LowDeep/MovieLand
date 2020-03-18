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


        String sqlFilTable = "CREATE TABLE accounts ( username TEXT PRIMARY KEY, email TEXT , password TEXT)";
        String sqlFileTable = "CREATE TABLE movies ( id INTEGER PRIMARY KEY ,titre TEXT , annee INTEGER , acteurs TEXT,resume TEXT , genre TEXT , pathImage TEXT)";


        db.execSQL(sqlFileTable);
        db.execSQL(sqlFilTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
