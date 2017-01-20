package com.gabriel.gagnier.calculatricetemporel.view.fragments.eventFragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.gabriel.gagnier.calculatricetemporel.controler.database.DataBaseControler;
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
        int id;
        String tableName = mHelper.getTableEvenements();
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
        db.insert(tableName, null, cv); //insere l'element dans la bdd

        //cree la notification en cas de besoin
        if(notification == 1) {
            id = Integer.parseInt(Long.toString(DataBaseControler.lastId(db,mHelper.getTableEvenements())));
            try{
                if(id!=0)
                    scheduleNotification(commentaire,libelle,id,date);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        mHelper.close();

    }


    public void setSavedDate(String savedDate) {
            this.savedDate = savedDate;
    }
}
