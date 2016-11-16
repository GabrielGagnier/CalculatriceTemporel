package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SaveEventFragment extends DialogFragment{

    protected DataBaseHelper mHelper;
    protected String savedDate;
    protected SaveEventFragment currentFragment;

    protected EditText editTextLibelle;
    protected EditText editTextDatePickerSave;
    protected EditText editTextCommentaire;
    protected CheckBox checkBoxNotification;
    protected Button buttonTimePickerSave;
    protected Button buttonSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtention du helper
        mHelper = new DataBaseHelper(this.getActivity());
        this.currentFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_save_event, container, false);
        this.editTextLibelle = (EditText) v.findViewById(R.id.editTextLibelle);
        this.editTextDatePickerSave = (EditText) v.findViewById(R.id.editTextDatePickerSave);
        editTextDatePickerSave.setText(savedDate);
        this.editTextCommentaire = (EditText) v.findViewById(R.id.editTextCommentaire);
        this.checkBoxNotification = (CheckBox) v.findViewById(R.id.checkBoxNotification);
        this.checkBoxNotification.toggle();
        this.buttonSave = (Button) v.findViewById(R.id.buttonSave);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String libelle = editTextLibelle.getText().toString();
                String date = editTextDatePickerSave.getText().toString();
                String commentaire = editTextCommentaire.getText().toString();
                int notification;
                if(checkBoxNotification.isChecked())
                    notification = 1;
                else
                    notification = 0;
                SQLiteDatabase db = mHelper.getWritableDatabase();

                ContentValues cv = new ContentValues(4);
                cv.put("libelle",libelle);
                cv.put("date",date);
                cv.put("commentaire",commentaire);
                cv.put("notification",notification);

                mHelper.openDataBase();
                db.insert(mHelper.getTableEvenements(), null, cv); //insere l'element dans la bdd
                getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
        });
        this.buttonTimePickerSave = (Button) v.findViewById(R.id.buttonTimePickerSave);
        this.buttonTimePickerSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setEditText(editTextDatePickerSave);
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });
        return v;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }
}
