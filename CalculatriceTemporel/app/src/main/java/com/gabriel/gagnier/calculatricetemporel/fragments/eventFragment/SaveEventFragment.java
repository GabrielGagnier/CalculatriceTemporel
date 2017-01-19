package com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.gabriel.gagnier.calculatricetemporel.util.date.DateUtils;

public class SaveEventFragment extends AbstractEventFragment{

    /**
     * date contenue dans le champ editDate a l'initialisation du fragment
     */
    protected String savedDate = DateUtils.now();



    @Override
    protected void initComponent(View view) {
        this.editTextDate.setText(savedDate);
    }

    @Override
    protected void onButtonEventFragmentAction() {
        int id = 0;
        String libelle = editTextLibelle.getText().toString();
        String date = editTextDate.getText().toString();
        String commentaire = editTextCommentaire.getText().toString();
        int notification;
        if(checkBoxNotification.isChecked())
            notification = 1;
        else
            notification = 0;
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues cv = new ContentValues(4);
        cv.put("libelle",libelle);
        cv.put("date",date);
        cv.put("commentaire",commentaire);
        cv.put("notification",notification);

        mHelper.openDataBase();
        db.insert(mHelper.getTableEvenements(), null, cv); //insere l'element dans la bdd

        //cree la notification en cas de besoin
        if(notification == 1) {
            String query = "SELECT _id from " + mHelper.getTableEvenements() + " order by _id DESC limit 1";
            Cursor c = db.rawQuery(query, null);
            if (c != null && c.moveToFirst()) {
                id = Integer.parseInt(Long.toString(c.getLong(0))); //The 0 is the column index, we only have 1 column, so the index is 0
            }
            try{
                scheduleNotification(commentaire,libelle,id,date);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }


    public void setSavedDate(String savedDate) {
            this.savedDate = savedDate;
    }
}
