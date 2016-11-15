package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SaveEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_event);
        String date = this.getIntent().getStringExtra("date");
        EditText editTextDate = (EditText) findViewById(R.id.editTextDatePicker);
        editTextDate.setText(date);
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setIdText(R.id.editTextDatePicker);
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void save(View v){
        String libelle = ((EditText) findViewById(R.id.editTextLibelle)).getText().toString();
        String date = ((EditText) findViewById(R.id.editTextDatePicker)).getText().toString();
        String commentaire = ((EditText) findViewById(R.id.editTextCommentaire)).getText().toString();
        int notification;
        CheckBox notif = (CheckBox) findViewById(R.id.checkBoxNotification);
        if(notif.isChecked())
            notification = 1;
        else
            notification = 0;

        DataBaseHelper DBHelper = new DataBaseHelper(getApplicationContext());
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues cv = new ContentValues(4);
        cv.put("libelle",libelle);
        cv.put("date",date);
        cv.put("commentaire",commentaire);
        cv.put("notification",notification);

        DBHelper.openDataBase();
        db.insert(DBHelper.getTableEvenements(), null, cv); //insere l'element dans la bdd


        Toast.makeText(this, "l'évènement a bien été sauvegardé", Toast.LENGTH_LONG).show();

        Button boutonSave = (Button) findViewById(R.id.buttonSave);

        boutonSave.setEnabled(false);
    }



}
