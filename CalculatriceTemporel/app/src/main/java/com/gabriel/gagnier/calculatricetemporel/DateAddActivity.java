package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DateAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_add);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTime);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerTime, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void calcul(View v){
        try {
            String date = ((EditText) findViewById(R.id.editTextDatePicker)).getText().toString();
            String time = ((Spinner) findViewById(R.id.spinnerTime)).getSelectedItem().toString();
            int add = Integer.parseInt(((EditText) findViewById(R.id.editTextAdd)).getText().toString());
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewResultatDate);
            switch(time){
                case "Jours" :
                    textViewResultatDate.setText(DateUtils.addDays(date,add));
                    break;
                case "Mois" :
                    textViewResultatDate.setText(DateUtils.addMonth(date,add));
                    break;
                case "Années" :
                    textViewResultatDate.setText(DateUtils.addYears(date,add));
                    break;
                default:
                    textViewResultatDate.setText("Données mal renseigné!");
                    break;
            }
        }catch(Exception e){
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewResultatDate);
            textViewResultatDate.setText("Données mal renseigné!");
        }
    }

    public void saveEvent(View V){
        final Intent it = new Intent();
        it.setAction("com.gabriel.gagnier.calculatricetemporel.SAVE");
        it.putExtra("date",((TextView) findViewById(R.id.textViewResultatDate)).getText().toString());
        startActivity(it);
    }
}
