package com.gabriel.gagnier.calculatricetemporel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.gabriel.gagnier.calculatricetemporel.R;
import com.gabriel.gagnier.calculatricetemporel.fragments.listEventFragment.CRUDListEventsFragment;
import com.gabriel.gagnier.calculatricetemporel.util.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DataBaseHelper mDbHelper;

    //TODO ergonomie en mode calculatrice
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
            Intent it = new Intent();
            it.setAction("com.gabriel.gagnier.calculatricetemporel.SETTINGS");
            startActivity(it);
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

    public void launchEvents(View V)
    {
        CRUDListEventsFragment newFragment = new CRUDListEventsFragment();
        newFragment.show(getFragmentManager(),"CRUDListEventsFragment");
    }
}
