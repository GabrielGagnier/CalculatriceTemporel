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
import com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment.SaveEventFragment;
import com.gabriel.gagnier.calculatricetemporel.fragments.listEventFragment.ListEventsFragment;
import com.gabriel.gagnier.calculatricetemporel.util.date.DateUtils;

public class AddActivity extends AppCompatActivity {
    /**
     * variable permettant d'afficher le log des erreurs
     */
    private boolean gotResult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText editTextDatePicker = (EditText) findViewById(R.id.editTextDatePicker);
        editTextDatePicker.setText(DateUtils.now());

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTime);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerTime, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    /**
     *accet au calendar android depuis le bouton date picker
     * @param v
     */
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditText((EditText) findViewById(R.id.editTextDatePicker));
        newFragment.show(getFragmentManager(),"datePicker");
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void centerText(TextView textView){
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    /**
     * fonction appellée a l'apuis du bouton calcul et affiche le resulata a l'ecran
     * @param v
     */
    public void calcul(View v){
        try {
            String date = ((EditText) findViewById(R.id.editTextDatePicker)).getText().toString();
            String time = ((Spinner) findViewById(R.id.spinnerTime)).getSelectedItem().toString();
            int add = Integer.parseInt(((EditText) findViewById(R.id.editTextAdd)).getText().toString());
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewResultatDate);
            textViewResultatDate.setTextSize(48);
            this.centerText(textViewResultatDate);
            switch(time){
                case "Jours" :
                    textViewResultatDate.setText(DateUtils.addDays(date,add));
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.green));
                    this.gotResult = true;
                    break;
                case "Mois" :
                    textViewResultatDate.setText(DateUtils.addMonth(date,add));
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.green));
                    this.gotResult = true;
                    break;
                case "Années" :
                    textViewResultatDate.setText(DateUtils.addYears(date,add));
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.green));
                    this.gotResult = true;
                    break;
                default:
                    textViewResultatDate.setText("Données mal renseigné!");
                    textViewResultatDate.setTextColor(getResources().getColor(R.color.red));
                    break;
            }

        }catch(Exception e){
            TextView textViewResultatDate = (TextView) findViewById(R.id.textViewResultatDate);
            textViewResultatDate.setTextColor(getResources().getColor(R.color.red));
            textViewResultatDate.setText(getResources().getString(R.string.erreur_de_saisie));
        }
    }

    /**
     *fonction appelle a l'apuis sur le bouton save event qui permet de sauvegarder le resultat du calcul ou autre evenement
     * @param V
     */
    public void saveEvent(View V){
        SaveEventFragment newFragment = new SaveEventFragment();
        if(this.gotResult)
            newFragment.setSavedDate(((TextView) this.findViewById(R.id.textViewResultatDate)).getText().toString());
        newFragment.show(getFragmentManager(),"saveEvent");
    }

    /**
     * lors de l'apuis sur le sur le bouton select la list des evenements aparais
     * @param V
     */
    public void select(View V){
        ListEventsFragment newFragment = new ListEventsFragment();
        newFragment.setIdText(R.id.editTextDatePicker);
        newFragment.show(getFragmentManager(),"ListEventsFragment");
    }
}
