package com.gabriel.gagnier.calculatricetemporel.services.notification;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Notification;

import com.gabriel.gagnier.calculatricetemporel.util.notification.NotificationPublisher;

/**
 * Intent gerer par un thread non applicatif
 * Created by gagnier on 10/01/17.
 */

public class SendNotificationService extends IntentService  {
    private static final String NAME = "Send Notification Service";

    /**
     * constructeur par default
     */
    public SendNotificationService() {
        super(NAME);
    }

    /**
     * verifie que une notification et le delay sont bien placer en extra et envoie la notification planifier
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extras = intent.getExtras();

        //TODO ajouter les tests necessaire
        if (extras.getParcelable("notification") != null && extras.getParcelable("notification").getClass() == Notification.class) {
            sendNotification((Notification)extras.getParcelable("notification"),extras.getInt("id"),extras.getLong("delay"));
        } else {
            new IllegalArgumentException("PUSH_RECEIVED NOT HANDLED!");
        }
    }

    /**
     * planifie et envoir la notification
     * @param notification
     * @param delay
     */
    private void sendNotification(Notification notification,int idNotification, Long delay) {
        if (delay > 0) {
            Intent notificationIntent = new Intent(this, NotificationPublisher.class);
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, idNotification);
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            long futureInMillis = SystemClock.elapsedRealtime() + delay;
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        }
    }

}
