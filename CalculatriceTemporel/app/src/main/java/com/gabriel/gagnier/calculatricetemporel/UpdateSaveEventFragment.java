package com.gabriel.gagnier.calculatricetemporel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by thibault on 16/11/2016.
 */

public class UpdateSaveEventFragment extends SaveEventFragment {

    private int id;
    private int notification;
    private String date;
    private String libele;
    private String commentaire;

    private LinearLayout linearLayoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_save_event, container, false);

        this.linearLayoutButton = (LinearLayout) v.findViewById(R.id.linearLayoutButton);

        Button boutonDelete = new Button(v.getContext());
        boutonDelete.setText("Delete");
        boutonDelete.setTextColor(Color.WHITE);
        boutonDelete.setBackgroundColor(0xFF3F51B5);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayoutButton.addView(boutonDelete, lp);

        boutonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                mHelper.openDataBase();

                db.delete(mHelper.getTableEvenements(), "_id = ?", new String[]{Integer.toString(id)});


                getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
        });

        this.editTextLibelle = (EditText) v.findViewById(R.id.editTextLibelle);
        this.editTextLibelle.setText(libele);
        this.editTextDatePickerSave = (EditText) v.findViewById(R.id.editTextDatePickerSave);
        this.editTextDatePickerSave.setText(date);
        this.editTextCommentaire = (EditText) v.findViewById(R.id.editTextCommentaire);
        this.editTextCommentaire.setText(commentaire);
        this.checkBoxNotification = (CheckBox) v.findViewById(R.id.checkBoxNotification);

        if(0 == notification)
            this.checkBoxNotification.toggle();

        this.buttonSave = (Button) v.findViewById(R.id.buttonSave);
        this.buttonSave.setText("Update");
        this.buttonSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                libele = editTextLibelle.getText().toString();
                date = editTextDatePickerSave.getText().toString();
                commentaire = editTextCommentaire.getText().toString();

                int notification;
                if(checkBoxNotification.isChecked())
                    notification = 1;
                else
                    notification = 0;
                SQLiteDatabase db = mHelper.getWritableDatabase();

                ContentValues cv = new ContentValues(4);
                cv.put("libelle",libele);
                cv.put("date",date);
                cv.put("commentaire",commentaire);
                cv.put("notification",notification);

                mHelper.openDataBase();

                db.delete(mHelper.getTableEvenements(), "_id = ?", new String[]{Integer.toString(id)});


                db.insert(mHelper.getTableEvenements(), null, cv); //insere l'element dans la bdd

                getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
        });
        this.buttonTimePickerSave = (Button) v.findViewById(R.id.buttonTimePickerSave);
        this.buttonTimePickerSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setEditText(editTextDatePickerSave);
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });
        return v;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }



}
