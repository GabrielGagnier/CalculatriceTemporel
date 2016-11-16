package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
