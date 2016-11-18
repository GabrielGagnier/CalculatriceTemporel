package com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gabriel.gagnier.calculatricetemporel.util.DataBaseHelper;
import com.gabriel.gagnier.calculatricetemporel.fragments.dateFragment.DatePickerFragment;
import com.gabriel.gagnier.calculatricetemporel.R;

/**
 * Created by gagnier on 16/11/16.
 */

public abstract class AbstractEventFragment extends DialogFragment {

    protected DataBaseHelper mHelper;
   // protected String savedDate;
    protected AbstractEventFragment currentFragment;

    protected EditText editTextLibelle;
    protected EditText editTextDatePickerSave;
    protected EditText editTextCommentaire;
    protected CheckBox checkBoxNotification;
    protected Button buttonTimePickerEventFragment;
    protected Button buttonEventFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtention du helper
        mHelper = new DataBaseHelper(this.getActivity());
        this.currentFragment = this;
    }

    /**
     * dans les classes fille au minimum set le onClick de buttonEventFragment
     */
    protected abstract void initComponent(View view);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_event, container, false);
        this.editTextLibelle = (EditText) v.findViewById(R.id.editTextLibelle);
        this.editTextDatePickerSave = (EditText) v.findViewById(R.id.editTextDatePickerSave);
       // editTextDatePickerSave.setText(savedDate);
        this.editTextCommentaire = (EditText) v.findViewById(R.id.editTextCommentaire);
        this.checkBoxNotification = (CheckBox) v.findViewById(R.id.checkBoxNotification);
        this.checkBoxNotification.toggle();
        this.buttonEventFragment = (Button) v.findViewById(R.id.buttonEventFragment);
        this.buttonTimePickerEventFragment = (Button) v.findViewById(R.id.buttonTimePickerEventFragment);
        this.buttonTimePickerEventFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setEditText(editTextDatePickerSave);
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });
        this.initComponent(v);
        return v;
    }
}
