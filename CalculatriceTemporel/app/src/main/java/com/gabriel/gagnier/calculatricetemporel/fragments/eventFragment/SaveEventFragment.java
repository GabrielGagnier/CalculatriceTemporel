package com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

public class SaveEventFragment extends AbstractEventFragment{

    protected String savedDate;

    @Override
    protected void initComponent(View view) {
        this.editTextDatePickerSave.setText(savedDate);
        this.buttonEventFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String libelle = editTextLibelle.getText().toString();
                String date = editTextDatePickerSave.getText().toString();
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
                getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
        });
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }
}
