package com.gabriel.gagnier.calculatricetemporel;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by thibault on 14/11/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    public  static final String DB_NAME = "Calculatrice_Temporelle.db";
    private static final String DB_PATH = "/data/data/com.gabriel.gagnier.calculatricetemporel/databases/";  //Le chemin menant a la bdd

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    private  SQLiteDatabase myDataBase;

    private static final String TABLE_EVENEMENTS = "evenements";
    private static final String COLUMN_ID = "_id";

    public static String getTableEvenements() {
        return TABLE_EVENEMENTS;
    }

    private static final String LIBELLE = "libelle";
    private static final String DATE = "date";
    private static final String COMMENTAIRE = "commentaire";
    private static final String NOTIFICATION = "notification";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE if not exists "
            + TABLE_EVENEMENTS  + "("
                + COLUMN_ID     + " INTEGER primary key autoincrement, "
                + LIBELLE       + " TEXT not null, "
                + DATE          + " TEXT not null, "
                + COMMENTAIRE   + " TEXT, "
                + NOTIFICATION  + " INTEGER not null)" ;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(DATABASE_CREATE);
        }
        catch(SQLException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(DataBaseHelper.class.getName(), "upgrading DATABASE from version "+ i + " to " + i1 + ", which will destroy all data.");

        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLE_EVENEMENTS);

        onCreate(sqLiteDatabase);
    }

    public void openDataBase(){
        String myPath = DB_PATH + DB_NAME;

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close()
    {
        if(null != myDataBase)
            myDataBase.close();

        super.close();
    }

    public boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;

        try
        {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLException e){
            e.printStackTrace();
        }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public SQLiteDatabase getMyDataBase() {
        return myDataBase;
    }
}
