package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DeltaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delta);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTime);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerTime, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void showDatePickerDialog1(View v){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditText((EditText) findViewById(R.id.editTextDate1));
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void showDatePickerDialog2(View v){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditText((EditText) findViewById(R.id.editTextDate1));
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void calcul(View v){
        try {
            String date1 = ((EditText) findViewById(R.id.editTextDate1)).getText().toString();
            String date2 = ((EditText) findViewById(R.id.editTextDate2)).getText().toString();
            String time = ((Spinner) findViewById(R.id.spinnerTime)).getSelectedItem().toString();
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewDateRes);
            switch(time){
                case "Jours" :
                    textViewResultatDate.setText(Integer.toString(DateUtils.deltaDays(date1,date2)));
                    break;
                case "Mois" :
                    textViewResultatDate.setText(Integer.toString(DateUtils.deltaMonth(date1,date2)));
                    break;
                case "Années" :
                    textViewResultatDate.setText(Integer.toString(DateUtils.deltaYear(date1,date2)));
                    break;
                default:
                    textViewResultatDate.setText("Données mal renseigné!");
                    break;
            }
        }catch(Exception e){
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewDateRes);
            textViewResultatDate.setText("Données mal renseigné!");
        }
    }

    public void select1(View v){
        SelectEventFragment newFragment = new SelectEventFragment();
        newFragment.setIdText(R.id.editTextDate1);
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void select2(View v){
        SelectEventFragment newFragment = new SelectEventFragment();
        newFragment.setIdText(R.id.editTextDate2);
        newFragment.show(getFragmentManager(),"datePicker");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText editDate1 = (EditText) findViewById(R.id.editTextDate1);
        EditText editDate2 = (EditText) findViewById(R.id.editTextDate2);
        editDate1.setText(savedInstanceState.getString("date1"));
        editDate2.setText(savedInstanceState.getString("date2"));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("date1", ((EditText) findViewById(R.id.editTextDate1)).getText().toString());
        savedInstanceState.putString("date2", ((EditText) findViewById(R.id.editTextDate2)).getText().toString());
    }
}
