package com.example.progmobileproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        //cette méthode se lance lorsque le BroadcastReceiver reçoie une intent broadcast
        //ici c'est donc lorsqu'il va recevoir notre notification dans le canal
        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.createNotification();

    }
}
