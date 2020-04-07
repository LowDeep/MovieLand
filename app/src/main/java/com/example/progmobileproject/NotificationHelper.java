package com.example.progmobileproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.example.progmobileproject.MainApplicationPage;
import com.example.progmobileproject.R;

public class NotificationHelper {

    private Context mContext;
    private static final String NOTIFICATION_CHANNEL_ID = "10002";

    NotificationHelper(Context context) {
        mContext = context;
    }


    //classe pour utiliser les notifications, l'utilisateur est notifier chaque jour a 19h
    void createNotification()
    {

        Intent intent = new Intent(mContext , MainApplicationPage.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String notificationContent=Integer.toString(R.string.notification);
        //CREATION DE LA NOTIFICATION : ON INITIALISE LE CONTENU QUE L4ON VEUT
        @SuppressWarnings("deprecation")
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setSmallIcon(R.mipmap.ic_movieland);
        mBuilder.setContentTitle("MOVIELAND")
                .setContentText("Revenez vite pour découvrir les nouveautés ou bien pour en partager ! :)")
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        //CREATION DU CANAL DANS LEQUEL LA NOTIFICATION SERA ENVOYER POUR ETRE TRAITER
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_MOVIELAND", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 , mBuilder.build());
    }

}
