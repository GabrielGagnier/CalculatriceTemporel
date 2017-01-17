package com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.gabriel.gagnier.calculatricetemporel.R;
import com.gabriel.gagnier.calculatricetemporel.services.NotificationService;
import com.gabriel.gagnier.calculatricetemporel.util.date.DateUtils;

public class SaveEventFragment extends AbstractEventFragment{

    /**
     * date contenue dans le champ editDate a l'initialisation du fragment
     */
    protected String savedDate = DateUtils.now();



    @Override
    protected void initComponent(View view) {
        this.editTextDate.setText(savedDate);
    }

    @Override
    protected void onButtonEventFragmentAction() {
        Long id = 0L;
        String libelle = editTextLibelle.getText().toString();
        String date = editTextDate.getText().toString();
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

        //cree la notification en cas de besoin
        if(notification == 1) {
            String query = "SELECT _id from " + mHelper.getTableEvenements() + " order by _id DESC limit 1";
            Cursor c = db.rawQuery(query, null);
            if (c != null && c.moveToFirst()) {
                id = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
            }
            try{
                scheduleNotification(commentaire,libelle,id,date);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

    /**
     * lance l'intentService de notification (qui est un thread non gere par l'aplication)
     * @param message
     * @param title
     * @param date
     */
    private void scheduleNotification(String message, String title, Long id, String date) throws Exception {
        Long delay = DateUtils.delayToBeNotifiate(date, (this.getActivity()).getApplicationContext());
        Intent intentNotif = new Intent(this.getActivity(), NotificationService.class);
        Bundle bundleNotif = new Bundle();

        Notification notification = createNotif(message,title);
        bundleNotif.putParcelable("notification",notification);
        bundleNotif.putLong("id",id);
        bundleNotif.putLong("delay",delay);
        intentNotif.putExtras(bundleNotif);
        this.getActivity().startService(intentNotif);
    }

    /**
     * créé la notification
     * @param message
     * @param title
     * @return
     */
    private Notification createNotif(String message,String title){
        Notification.Builder builder = new Notification.Builder(this.getActivity());
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.ic_info_black_24dp);
        return builder.build();
    }

    public void setSavedDate(String savedDate) {
            this.savedDate = savedDate;
    }
}
