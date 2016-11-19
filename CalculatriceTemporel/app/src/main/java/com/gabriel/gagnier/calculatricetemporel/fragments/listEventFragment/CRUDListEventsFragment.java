package com.gabriel.gagnier.calculatricetemporel.fragments.listEventFragment;


import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gabriel.gagnier.calculatricetemporel.util.DataBaseHelper;
import com.gabriel.gagnier.calculatricetemporel.R;
import com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment.SaveEventFragment;
import com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment.UpdateDeleteEventFragment;

import java.util.List;

/**
 * Created by thibault on 16/11/2016.
 */

public class CRUDListEventsFragment extends AbstractListEventsFragment {

    private ImageButton buttonNew;
    private ListView listEvents;

    @Override
    protected void initComponent(View view) {
       GridLayout grid = (GridLayout) view.findViewById(R.id.gridLayoutListEventsFragment);
        this.buttonNew = new ImageButton(view.getContext());
        this.listEvents = (ListView) view.findViewById(R.id.listEvents);
        ViewGroup.LayoutParams paramsListEvent = listEvents.getLayoutParams();
        paramsListEvent.height = 600;
        listEvents.setLayoutParams(paramsListEvent);

        buttonNew.setImageResource(R.drawable.ic_border_color_black_36dp);
        buttonNew.setBackgroundColor(getResources().getColor(R.color.white));

        FrameLayout.LayoutParams paramsButtonNew = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        buttonNew.setLayoutParams(paramsButtonNew);

        grid.addView(buttonNew, new GridLayout.LayoutParams(
                GridLayout.spec(3,GridLayout.END),
                GridLayout.spec(0,GridLayout.END)));
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEventFragment newFragment = new SaveEventFragment();
                newFragment.setGoOnButtonEventFragment(new CRUDListEventsFragment(),"CRUDListEventsFragment");
                newFragment.show(getFragmentManager(),"NewEvent");
                getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
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
        newFragment.setGoOnButtonDeleteFragment(new CRUDListEventsFragment(),"CRUDListEventsFragment");
        newFragment.setGoOnButtonEventFragment(new CRUDListEventsFragment(),"CRUDListEventsFragment");
        newFragment.show(getFragmentManager(),"UpdateDeleteEventFragment");
        getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
    }
}
