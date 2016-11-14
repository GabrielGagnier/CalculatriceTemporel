package com.gabriel.gagnier.calculatricetemporel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SaveEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_event);
        String date = this.getIntent().getStringExtra("date");
        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextDate.setText(date);
    }
}
