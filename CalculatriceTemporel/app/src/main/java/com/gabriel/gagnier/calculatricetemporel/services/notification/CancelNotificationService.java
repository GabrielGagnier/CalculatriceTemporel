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

public class CancelNotificationService extends IntentService  {
    private static final String NAME = "Cancel Notification Service";

    /**
     * constructeur par default
     */
    public CancelNotificationService() {
        super(NAME);
    }

    /**
     * verifie que une notification et le delay sont bien placer en extra et envoie la notification planifier
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extras = intent.getExtras();
        cancelNotification(extras.getInt("id"));
    }

    /**
     * annule la notification
     * @param idNotification
     */
    private void cancelNotification(int idNotification) {
            Intent notificationIntent = new Intent(this, NotificationPublisher.class);
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, idNotification);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
    }

}
