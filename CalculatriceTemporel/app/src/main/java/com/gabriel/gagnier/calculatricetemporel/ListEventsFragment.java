package com.gabriel.gagnier.calculatricetemporel;

import android.app.DialogFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListEventsFragment extends AbstractListEventsFragment{

    private int idText;

    @Override
    protected void initComponent(View view) {
        //aucun nouvelle item
    }

    @Override
    protected void onItemClickAction() {
        String rowDate = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getDATE()));
        EditText editTextDate1 = (EditText) this.getActivity().findViewById(idText);
        editTextDate1.setText(rowDate);
    }

    public void setIdText(int idText) {
        this.idText = idText;
    }
}
