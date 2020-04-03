package com.example.progmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.progmobileproject.Classes.Film;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainApplicationPage extends AppCompatActivity {

    ImageView logo_connection;
    ListView list_films;
    ArrayList<Film> listeFilms;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_application_layout);

        //programmation d'une notification
        myAlarm();

        logo_connection = (ImageView)findViewById(R.id.LogoConnexion);
        list_films = (ListView)findViewById(R.id.list_films);

        Film f = new Film();
        //Log.i("username", userName);
        ArrayList<Film> listefilms = f.getFilmList(this);
        //Log.i("list",listefilms.toString());

        FilmAdapter adapter = new FilmAdapter(this, listefilms);
        list_films.setAdapter(adapter);



        //quand on clique sur un element de la liste on est rediriger vers son contenu
        list_films.setOnItemClickListener(//lambda expression avec l'id du film que l'utilisateur a choisit
                (parent, view, position, id) -> {
                    Intent it = new Intent(this,ViewFilmActivity.class);
                    it.putExtra("filmId",id+1);
                    startActivity(it);
                }
        );


        //quand on clique sur le logo de connection on est rediriger vers la page de connection
        logo_connection.setOnClickListener(v->{
            Intent it = new Intent(this , ConnectionActivity.class);
            startActivity(it);
        });

    }

    //methode pour commencer une activite qui affiche les infos sur le film entre en parametre
    private void startViewDVDActivity(long filmId)
    {
        Intent intent = new Intent(this, ViewFilmActivity.class);
        intent.putExtra("filmId",filmId);//on envoie le film id a l'acitivité suivante pour pouvoir recuperer les infos sur le film et l'afficher
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //on programme une notification à 19h
    public void myAlarm() {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE,56);
        System.out.println(sdf.format(calendar.getTime()));



        if (calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        }

    }
}
