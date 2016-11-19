package com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.gabriel.gagnier.calculatricetemporel.util.DateUtils;

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
        goOnButtonEventFragment.show(getFragmentManager(),tagGoOnButtonEventFragment);
        getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
    }

    public void setSavedDate(String savedDate) {
            this.savedDate = savedDate;
    }
}
