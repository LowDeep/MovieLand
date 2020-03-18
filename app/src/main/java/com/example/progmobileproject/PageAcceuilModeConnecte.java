package com.example.progmobileproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progmobileproject.Classes.Film;

import java.util.ArrayList;
import java.util.List;

public class PageAcceuilModeConnecte extends AppCompatActivity {


    Button ajouter_film;

    String userName;
    String email;
    String password;

    ListView listview ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil_mode_connecte);
        Bundle extras = getIntent().getExtras();
       // userName = getIntent().getStringExtra("username")
        if (extras != null) {
            userName = extras.getString("username");
            email = extras.getString("email");
            password = extras.getString("password");
        }


        //listview des qui affiche les films
        listview = (ListView) findViewById(R.id.user_list_films);


        Film f = new Film();
        Log.i("username", userName);
        ArrayList<Film> listefilms = f.getMyfilmList(this,userName);
        Log.i("list",listefilms.toString());

        MyAdapter adapter = new MyAdapter(this, listefilms  );
        listview.setAdapter(adapter);

        ajouter_film = (Button)findViewById(R.id.ajout_film_button);
        ajouter_film.setOnClickListener(v -> {
            Intent it = new Intent(this,AddFilmActivity.class);

            it.putExtra("username", userName);
            it.putExtra("email", email);
            it.putExtra("password",password);
            startActivity(it);
        });

    }


    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String filmTitle[];
        String filmYear[];
        String filmGender[];
        String filmResume[];
        ArrayList<Film> listefilms;

        //int images[];

        MyAdapter(Context c, String title[], String year[], String gender[], String resume[]){

            super(c,R.layout.listitem_dvd,R.id.listItemDVD_titre,title);
            this.context = c;
            this.filmTitle = title;
            this.filmYear=year;
            this.filmGender=gender;
            this.filmResume=resume;
        }

        MyAdapter(Context c, ArrayList<Film> listefilms){

            super(c,R.layout.listitem_dvd,R.id.listItemDVD_titre);
            this.context = c;
            this.listefilms = listefilms;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = layoutInflater.inflate(R.layout.listitem_dvd,parent,false);
            TextView myTitle = item.findViewById(R.id.listItemDVD_titre);
            TextView myAnne = item.findViewById(R.id.listItemDVD_annee);
            TextView myGenre = item.findViewById(R.id.listItemDVD_genre);
            TextView myResume = item.findViewById(R.id.listItemDVD_resume);

            /*
            myTitle.setText(filmTitle[position]);
            myAnne.setText(filmYear[position]);
            myGenre.setText(filmGender[position]);
            myResume.setText(filmResume[position]);*/

            myTitle.setText(listefilms.get(position).getTitre());
            myAnne.setText(listefilms.get(position).getAnnee());
            myGenre.setText(listefilms.get(position).getGenre());
            myResume.setText(listefilms.get(position).getResume());

            return item;
        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_connecte,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it ;
        switch(item.getItemId()){
            case R.id.action_mon_compte:

                return true;
            case R.id.action_modifier_mes_infos:
                Intent itback = getIntent();
                 it = new Intent(this,ModifCompteActivity.class);
                Bundle bd = itback.getExtras();
                it.putExtra("username",bd.getBundle("username").toString());
                it.putExtra("email",bd.getBundle("email").toString());
                it.putExtra("password",bd.getBundle("password").toString());

                startActivity(it);

                return true;
            case R.id.action_deconnection:
                it = new Intent(this,MainApplicationPage.class);
               startActivity(it);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

