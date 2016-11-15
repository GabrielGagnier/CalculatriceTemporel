package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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
        DialogFragment newFragment = new DatePickerFragment();
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
        //TODO cree l'entity et la persist

    }
}
