package com.gabriel.gagnier.calculatricetemporel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

/**
 * Created by thibault on 16/11/2016.
 */

public class UpdateSelectEventFragment extends SelectEventFragment {

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        mCursor.moveToPosition(i);

        UpdateSaveEventFragment newFragment = new UpdateSaveEventFragment();

        newFragment.setId(mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.getColumnId())));
        newFragment.setNotification(mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.getNOTIFICATION())));
        newFragment.setCommentaire(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getCOMMENTAIRE())));
        newFragment.setLibele(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getLIBELLE())));
        newFragment.setDate(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getDATE())));

        newFragment.show(getFragmentManager(),"updateFragment");
        this.getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}
