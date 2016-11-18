package com.gabriel.gagnier.calculatricetemporel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gabriel.gagnier.calculatricetemporel.R;
import com.gabriel.gagnier.calculatricetemporel.fragments.dateFragment.DatePickerFragment;
import com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment.SaveEventFragment;
import com.gabriel.gagnier.calculatricetemporel.fragments.listEventFragment.ListEventsFragment;
import com.gabriel.gagnier.calculatricetemporel.util.DateUtils;

public class AddActivity extends AppCompatActivity {
    private boolean gotResult = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_add);
        if(this.getIntent().getIntExtra("editable",0)!=0){
            EditText editDate = (EditText) findViewById(this.getIntent().getIntExtra("editable",0));
            editDate.setText(this.getIntent().getStringExtra("value"));
        }

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
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditText((EditText) findViewById(R.id.editTextDatePicker));
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
                    this.gotResult = true;
                    break;
                case "Mois" :
                    textViewResultatDate.setText(DateUtils.addMonth(date,add));
                    this.gotResult = true;
                    break;
                case "Années" :
                    textViewResultatDate.setText(DateUtils.addYears(date,add));
                    this.gotResult = true;
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
        SaveEventFragment newFragment = new SaveEventFragment();
        if(this.gotResult)
            newFragment.setSavedDate(((TextView) this.findViewById(R.id.textViewResultatDate)).getText().toString());
        newFragment.show(getFragmentManager(),"saveEvent");
    }

    public void select(View V){
        ListEventsFragment newFragment = new ListEventsFragment();
        newFragment.setIdText(R.id.editTextDatePicker);
        newFragment.show(getFragmentManager(),"datePicker");
    }
}
