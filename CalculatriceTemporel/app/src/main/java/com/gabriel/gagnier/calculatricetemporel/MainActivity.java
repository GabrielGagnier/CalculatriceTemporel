package com.gabriel.gagnier.calculatricetemporel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DataBaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDbHelper = new DataBaseHelper(getApplicationContext());
        db = mDbHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchAdd(View V){
        Intent it = new Intent();
        it.setAction("com.gabriel.gagnier.calculatricetemporel.ADD");
        startActivity(it);
    }

    public void launchDelta(View V){
        Intent it = new Intent();
        it.setAction("com.gabriel.gagnier.calculatricetemporel.DELTA");
        startActivity(it);
    }

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
    }
}
