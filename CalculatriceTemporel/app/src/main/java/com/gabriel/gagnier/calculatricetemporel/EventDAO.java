package com.gabriel.gagnier.calculatricetemporel;

import android.app.usage.UsageEvents;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thibault on 14/11/2016.
 */

public class EventDAO {

    private SQLiteDatabase db;

    public EventDAO()
    {

    }

    public EventDAO(SQLiteDatabase db)
    {
        this.db = db;
    }

    public SQLiteDatabase getBd()
    {
        return db;
    }

    public void setBd(SQLiteDatabase bd)
    {
        this.db = db;
    }

    public void insert(EventEntity event){
        String row = "INSERT INTO \'evenements\'(libelle, date, commentaire, notification)Values(\'" + event.getLibelle() + "\', \'"
                + event.getDate() + "\', \'" + event.getCommentaire() + "\', " + event.getNotification() + ")";

        db.execSQL(row);
    }

    public EventEntity selectById(int id) throws Exception
    {
        String request = "SELECT * FROM \'evenements\' WHERE _id= " + id;

        Cursor c = this.db.rawQuery(request, null);

        if(c!=null && c.getCount()>0) {

            c.moveToFirst();

            return new EventEntity(id, c.getString(c.getColumnIndex("libelle")),c.getString(c.getColumnIndex("date")),c.getString(c.getColumnIndex("commentaire")), c.getInt(c.getColumnIndex("notification")));
        }

        else{
            throw new Exception("Identifiant incorrect");
        }
    }

    public List<EventEntity> selectAll()
    {
        String request = "SELECT * FROM \'evenements\'";

        Cursor c = this.db.rawQuery(request, null);

        List<EventEntity> listeEvent = new ArrayList<>();

        for(int i = 0; i < c.getCount(); i++)
        {
            c.move(i);
            listeEvent.add(new EventEntity(c.getInt(c.getColumnIndex("_id")), c.getString(c.getColumnIndex("libelle")),c.getString(c.getColumnIndex("date")),c.getString(c.getColumnIndex("commentaire")), c.getInt(c.getColumnIndex("notification"))));

        }
        return listeEvent;
    }

    public void update(EventEntity oldEntry, EventEntity newEntry)
    {
        ContentValues cv = new ContentValues();

        cv.put("libelle", newEntry.getLibelle());
        cv.put("date", newEntry.getDate());
        cv.put("commentaire", newEntry.getCommentaire());
        cv.put("notification", newEntry.getNotification());

        db.update("evenements", cv, "_id="+oldEntry.getId(), null);
    }

    public void update(int id, EventEntity newEntry)
    {
        ContentValues cv = new ContentValues();

        cv.put("libelle", newEntry.getLibelle());
        cv.put("date", newEntry.getDate());
        cv.put("commentaire", newEntry.getCommentaire());
        cv.put("notification", newEntry.getNotification());

        db.update("evenements", cv, "_id="+id, null);
    }

    public void openDataBase()
    {
        this.db = SQLiteDatabase.openDatabase("/data/data/com.gabriel.gagnier.calculatricetemporel/databases/Calculatrice_Temporelle.db", null, SQLiteDatabase.OPEN_READWRITE);
    }
}
