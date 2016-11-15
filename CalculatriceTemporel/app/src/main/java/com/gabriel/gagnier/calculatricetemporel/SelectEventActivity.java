package com.gabriel.gagnier.calculatricetemporel;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SelectEventActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mList;
    private DataBaseHelper mHelper;
    private SQLiteDatabase maDB;
    private Cursor mCursor;
    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_event);

        mList = (ListView) findViewById(R.id.listEvents);
        mList.setOnItemClickListener(this);

        //obtention du helper
        mHelper = new DataBaseHelper(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mCursor.moveToPosition(i);
        String rowDate = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.getDATE()));
        final Intent it = new Intent();
        it.setAction(this.getIntent().getStringExtra("caller"));
        it.putExtra("editable",this.getIntent().getIntExtra("editable",0));
        it.putExtra("value", rowDate);
        startActivity(it);
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
            mAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
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
