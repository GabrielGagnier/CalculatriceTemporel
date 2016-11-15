package com.gabriel.gagnier.calculatricetemporel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DeltaActivity extends AppCompatActivity {

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
        newFragment.setIdText(R.id.editTextDate1);
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void showDatePickerDialog2(View v){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setIdText(R.id.editTextDate2);
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
}
