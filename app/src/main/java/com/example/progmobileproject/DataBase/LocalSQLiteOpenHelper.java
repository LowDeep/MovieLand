package com.example.progmobileproject.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME = "MovieLand.db";
    static int DB_VERSION = 1;

    public LocalSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFileTable = "CREATE TABLE Film(id INTEGER PRIMARY KEY, titre TEXT , annee NUMERIC, genre TEXT , acteurs TEXT , resume TEXT, pathImage TEXT)";
        db.execSQL(sqlFileTable);

        String sqlFileTable2 = "CREATE TABLE Comptes(idC INTEGER PRIMARY KEY, username text not null, mail text not null, password text not null)";
        db.execSQL(sqlFileTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
