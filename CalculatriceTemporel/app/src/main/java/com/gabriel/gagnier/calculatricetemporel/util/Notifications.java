package com.gabriel.gagnier.calculatricetemporel.util;

/**
 * Created by thibault on 16/11/2016.
 */

public class Notifications {


    //FIXME a virer
    /*
    public void TEST(View V)
    {

        //mis ici pour pas perdre le code, à bouger apres peut etre
        NotificationCompat.Builder builderNotif = new NotificationCompat.Builder(this);
        builderNotif.setSmallIcon(R.mipmap.ic_launcher);
        builderNotif.setContentTitle("Ceci est un titre de notif");
        builderNotif.setContentText("Ceci est le texte de la notif");

        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builderNotif.setContentIntent(resultPendingIntent);


        mNotificationManager.notify(10, builderNotif.build());
    }*/
}
