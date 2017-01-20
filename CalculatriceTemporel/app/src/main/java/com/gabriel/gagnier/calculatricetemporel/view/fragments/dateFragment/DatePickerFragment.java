package com.gabriel.gagnier.calculatricetemporel.view.fragments.dateFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;


/**
 * Created by gagnier on 10/11/16.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    /**
     * permet de set l'editText devant changer a la selection d'une dante
     */
    private EditText editText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        String date = format.format(c.getTime());
        editText.setText(date);
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
