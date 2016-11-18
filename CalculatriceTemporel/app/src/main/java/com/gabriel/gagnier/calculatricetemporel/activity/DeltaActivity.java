package com.gabriel.gagnier.calculatricetemporel.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gabriel.gagnier.calculatricetemporel.R;
import com.gabriel.gagnier.calculatricetemporel.fragments.dateFragment.DatePickerFragment;
import com.gabriel.gagnier.calculatricetemporel.fragments.listEventFragment.ListEventsFragment;
import com.gabriel.gagnier.calculatricetemporel.util.DateUtils;

public class DeltaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delta);

        EditText editTextDate1 = (EditText) findViewById(R.id.editTextDate1);
        EditText editTextDate2 = (EditText) findViewById(R.id.editTextDate2);
        editTextDate1.setText(DateUtils.now());
        editTextDate2.setText(DateUtils.now());
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
        newFragment.setEditText((EditText) findViewById(R.id.editTextDate2));
        newFragment.show(getFragmentManager(),"datePicker");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void centerText(TextView textView){
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void calcul(View v){
        try {
            String date1 = ((EditText) findViewById(R.id.editTextDate1)).getText().toString();
            String date2 = ((EditText) findViewById(R.id.editTextDate2)).getText().toString();
            String time = ((Spinner) findViewById(R.id.spinnerTime)).getSelectedItem().toString();
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewDateRes);
            textViewResultatDate.setTextSize(48);
            this.centerText(textViewResultatDate);
            switch(time){
                case "Jours" :
                    textViewResultatDate.setText(Integer.toString(DateUtils.deltaDays(date1,date2)));
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.green));
                    break;
                case "Mois" :
                    textViewResultatDate.setText(Integer.toString(DateUtils.deltaMonth(date1,date2)));
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.green));
                    break;
                case "Années" :
                    textViewResultatDate.setText(Integer.toString(DateUtils.deltaYear(date1,date2)));
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.green));
                    break;
                default:
                    textViewResultatDate.setText("Données mal renseigné!");
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.red));
                    break;
            }
        }catch(Exception e){
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewDateRes);
            textViewResultatDate.setText("Données mal renseigné!");
            textViewResultatDate.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public void select1(View v){
        ListEventsFragment newFragment = new ListEventsFragment();
        newFragment.setIdText(R.id.editTextDate1);
        newFragment.show(getFragmentManager(),"datePicker");
    }

    public void select2(View v){
        ListEventsFragment newFragment = new ListEventsFragment();
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
