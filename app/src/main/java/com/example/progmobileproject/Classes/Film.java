package com.example.progmobileproject.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.progmobileproject.DataBase.LocalSQLOpenHelper;

import java.util.ArrayList;

public class Film {

    private long id;
    private String titre;
    private int annee;
    private String genre;
    private ArrayList<String> acteurs;
    private String resume;
    private String pathImage;
    private String username;



    //Contructeur1 pour creer un film depuis un curseur
    private Film(Cursor cursor)
    {
        id = cursor.getLong(cursor.getColumnIndex("id"));
        titre = cursor.getString(cursor.getColumnIndex("titre"));
        annee = cursor.getInt(cursor.getColumnIndex("annee"));
       // acteurs = cursor.getString(cursor.getColumnIndex("acteurs")).split(";");
        resume = cursor.getString(cursor.getColumnIndex("resume"));
        genre = cursor.getString(cursor.getColumnIndex("genre"));
        pathImage = cursor.getString(cursor.getColumnIndex("pathImage"));
    }

    //Constructeur 2 pour creer un film vide
    public Film(){

    }

    //constructeur 3 pour creer un film avec des valeurs depuis des string
    public Film(String titre, int annee, ArrayList<String> acteurs, String resume, String genre, String pathImage, String username){
       // this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.acteurs = acteurs;
        this.resume= resume;
        this.genre  = genre ;
        this.pathImage = pathImage;
        this.username= username;

    }


    //GETTERS - SETTERS

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnnee() {
        return annee;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<String> getActeurs() {
        return acteurs;
    }

    public String getResume() {
        return resume;
    }

    public String getPathImage() {
        return pathImage;
    }

    public String getUsername() {
        return username;
    }

    //METHODE DE MANIPULATION DE DONNEEs

    //obtenir liste dvd dans la db
    public static ArrayList<Film> getFilmList(Context context)
    {
        ArrayList<Film> listeFilm = new ArrayList<>();
        LocalSQLOpenHelper helper = new LocalSQLOpenHelper(context);

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "movies", new String[]{"id","titre","annee","genre","acteurs", "resume","pathImage","username"},null,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            listeFilm.add(new Film(cursor));
        }
        cursor.close();
        db.close();

        return listeFilm;
    }

    //obtenir liste dvd que l'utilisateur a creer dans la db
    public static ArrayList<Film> getMyfilmList(Context context,String username)
    {
        ArrayList<Film> listeFilm = new ArrayList<>();
        LocalSQLOpenHelper helper = new LocalSQLOpenHelper(context);

        SQLiteDatabase db = helper.getReadableDatabase();
        String whereClose = "username= '"+username+"'";

        Cursor cursor = db.query(true, "movies", new String[]{"id","titre","annee","genre","acteurs", "resume","pathImage","username"},whereClose,null,null,null,"titre",null);

        while (cursor.moveToNext())
        {
            listeFilm.add(new Film(cursor));
        }
        Log.i("LISTE FILMS FILM",listeFilm.toString());
        cursor.close();
        db.close();

        return listeFilm;
    }

    //methode qui permet de recuperer un film depuis la base de données depuis son idFilm
    public static Film getFilm(Context context, long filmId) {
       Film f = null;
        LocalSQLOpenHelper helper = new LocalSQLOpenHelper((context));

        SQLiteDatabase db = helper.getReadableDatabase();
        String where = "id = '" +String.valueOf(filmId)+"'";
        Cursor cursor = db.query(true,"movies", new String[]{"id","titre","genre","annee","acteurs","resume","pathImage","username"},where,null,null,null,"titre",null);

        if(cursor.moveToFirst())
        {
            f = new Film(cursor);
        }

        cursor.close();
        db.close();

        return f;
    }


    //inserer une nouvelle instance Film dans la bdd
    public void insert(Context context){



        ContentValues values = new ContentValues();
        values.put("titre",this.getTitre());
        values.put("genre",this.getGenre());
        values.put("acteurs",this.getActeurs().toString());
        values.put("annee",this.getAnnee());
        values.put("resume",this.getResume());
        values.put("username",this.getUsername());

        //Log.i("values INSERT",values.toString());

        //insertion
        LocalSQLOpenHelper helper = new LocalSQLOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("movies",null,values);
        db.close();

        Toast.makeText(context, "Insertion réussie!",Toast.LENGTH_SHORT).show();


    }

    //mettre a jour un enregistrement
    public void update(Context context)
    {/*
        ContentValues values = new ContentValues();
        values.put("titre",this.titre);
        values.put("annee",this.annee);
        values.put("genre",this.genre);
        if(this.acteurs != null)
        {
            String listeActeurs = new String();
            for(int i=0; i<this.acteurs.length; i++){
                listeActeurs += this.acteurs[i];
                if(i < this.acteurs.length -1)
                {
                    listeActeurs += ";";
                }
            }
            values.put("acteurs", listeActeurs);
        }
        values.put("resume",this.resume);
        values.put("pathImage",this.pathImage);

        String whereClause = "id = "+ String.valueOf(this.id);

        LocalSQLOpenHelper helper = new LocalSQLOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();

        db.update("Film",values,whereClause,null);
        db.close();*/
    }

    //supprimer l'instance
    public void delete(Context context){

        String whereClause = "id = ?";
      //  ArrayList<String> whereArgs = new String[1];
      //  whereArgs[0] = String.valueOf(this.id);

        LocalSQLOpenHelper helper = new LocalSQLOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();

        //db.delete("Film", whereClause,whereArgs);
        db.close();
    }


}

