package com.gabriel.gagnier.calculatricetemporel;


import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;

/**
 * Created by thibault on 16/11/2016.
 */

public class CRUDListEventsFragment extends AbstractListEventsFragment {
    private Button buttonNew;

    @Override
    protected void initComponent(View view) {
       GridLayout grid = (GridLayout) view.findViewById(R.id.gridLayoutListEventsFragment);
        this.buttonNew = new Button(view.getContext());

        buttonNew.setText("New Event");
        buttonNew.setTextColor(getResources().getColor(R.color.white));
        buttonNew.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        buttonNew.setLayoutParams(params);
        buttonNew.setGravity(Gravity.END);

        grid.addView(buttonNew, new GridLayout.LayoutParams(
                GridLayout.spec(3,GridLayout.END),
                GridLayout.spec(1,GridLayout.END)));
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEventFragment newFragment = new SaveEventFragment();
                newFragment.show(getFragmentManager(),"newEvent");
            }
        });
    }

    @Override
    protected void onItemClickAction() {
        UpdateDeleteEventFragment newFragment = new UpdateDeleteEventFragment();

        newFragment.setId(mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.getColumnId())));
        newFragment.setNotification(mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.getNOTIFICATION())));
        newFragment.setCommentaire(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getCOMMENTAIRE())));
        newFragment.setLibele(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getLIBELLE())));
        newFragment.setDate(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getDATE())));

        newFragment.show(getFragmentManager(),"updateFragment");
    }
}
