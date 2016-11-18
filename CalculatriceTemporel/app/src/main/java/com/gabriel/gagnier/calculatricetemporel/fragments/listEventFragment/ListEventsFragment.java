package com.gabriel.gagnier.calculatricetemporel.fragments.listEventFragment;

import android.view.View;
import android.widget.EditText;

import com.gabriel.gagnier.calculatricetemporel.util.DataBaseHelper;

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
