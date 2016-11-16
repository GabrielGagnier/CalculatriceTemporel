package com.gabriel.gagnier.calculatricetemporel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import javax.xml.parsers.FactoryConfigurationError;

/**
 * Created by thibault on 16/11/2016.
 */

public class UpdateDeleteEventFragment extends AbstractEventFragment {

    private int id;
    private int notification;
    private String date;
    private String libele;
    private String commentaire;

    private Button buttonDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater,container,savedInstanceState);

        GridLayout grid = (GridLayout) v.findViewById(R.id.gridLayoutFragmentEvent);
        this.buttonDelete = new Button(v.getContext());

        buttonDelete.setText("Delete");
        buttonDelete.setTextColor(getResources().getColor(R.color.white));
        buttonDelete.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        buttonDelete.setLayoutParams(params);
        buttonDelete.setGravity(Gravity.START);

        grid.addView(buttonDelete, new GridLayout.LayoutParams(
                        GridLayout.spec(5,GridLayout.START),
                        GridLayout.spec(0,GridLayout.START)));
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                mHelper.openDataBase();

                db.delete(mHelper.getTableEvenements(), "_id = ?", new String[]{Integer.toString(id)});


                getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
        });

        this.editTextLibelle.setText(libele);
        this.editTextDatePickerSave.setText(date);
        this.editTextCommentaire.setText(commentaire);

        if(0 == notification)
            this.checkBoxNotification.toggle();

        this.buttonEventFragment.setText("Update");
        this.buttonEventFragment.setOnClickListener(new View.OnClickListener() {

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
