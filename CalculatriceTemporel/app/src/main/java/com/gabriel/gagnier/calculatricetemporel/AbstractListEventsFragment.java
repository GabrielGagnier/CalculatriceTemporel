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

/**
 * Created by gagnier on 16/11/16.
 */

public abstract class AbstractListEventsFragment extends DialogFragment implements AdapterView.OnItemClickListener{


    protected ListView mList;

    protected DataBaseHelper mHelper;
    protected SQLiteDatabase maDB;
    protected Cursor mCursor;
    protected SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtention du helper
        mHelper = new DataBaseHelper(this.getActivity());
    }

    /**
     * initialise d'eventuel nouveaux composant a la vue
     * @param view
     */
    protected abstract void initComponent(View view);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_list_events, container, false);
        mList = (ListView) v.findViewById(R.id.listEvents);
        mList.setOnItemClickListener(this);
        this.initComponent(v);
        return v;
    }

    /**
     * fait l'action sur le click d'un evenement avant de fermer le fragment
     */
    protected abstract void onItemClickAction();

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mCursor.moveToPosition(i);
        this.onItemClickAction();
        this.getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }


    @Override
    public void onResume(){
        super.onResume();
        try {
            maDB = mHelper.getWritableDatabase();
            String[] columns = new String[]{DataBaseHelper.getColumnId(), DataBaseHelper.getLIBELLE(),
                    DataBaseHelper.getDATE(), DataBaseHelper.getCOMMENTAIRE(), DataBaseHelper.getNOTIFICATION()};
            mCursor = maDB.query(DataBaseHelper.getTableEvenements(), columns, null, null, null, null,
                    null);
            String[] headers = new String[]{DataBaseHelper.getLIBELLE(), DataBaseHelper.getDATE()};
            mAdapter = new SimpleCursorAdapter(this.getActivity(), android.R.layout.two_line_list_item,
                    mCursor, headers, new int[]{android.R.id.text1, android.R.id.text2, CursorAdapter.NO_SELECTION});
            mList.setAdapter(mAdapter);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        //Close all connections
        maDB.close();
        mCursor.close();
    }
}
