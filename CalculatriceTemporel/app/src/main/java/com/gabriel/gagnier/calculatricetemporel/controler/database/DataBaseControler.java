package com.gabriel.gagnier.calculatricetemporel.controler.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gagnier on 20/01/17.
 */

public class DataBaseControler {

    public static void update(SQLiteDatabase db, String tableName, int id, ContentValues cv){
        db.delete(tableName, "_id = ?", new String[]{Integer.toString(id)});


        db.insert(tableName, null, cv); //insere l'element dans la bdd
    }

    public static Long lastId(SQLiteDatabase db, String tableName){
        Long res = 0L;
        String query = "SELECT _id from " + tableName + " order by _id DESC limit 1";
        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            res = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
        }
        c.close();
        return res;
    }
}
