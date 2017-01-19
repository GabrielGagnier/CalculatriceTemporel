package com.gabriel.gagnier.calculatricetemporel.fragments.eventFragment;

import android.app.DialogFragment;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gabriel.gagnier.calculatricetemporel.services.notification.CancelNotificationService;
import com.gabriel.gagnier.calculatricetemporel.services.notification.SendNotificationService;
import com.gabriel.gagnier.calculatricetemporel.util.database.DataBaseHelper;
import com.gabriel.gagnier.calculatricetemporel.fragments.dateFragment.DatePickerFragment;
import com.gabriel.gagnier.calculatricetemporel.R;
import com.gabriel.gagnier.calculatricetemporel.util.date.DateUtils;

/**
 * Created by gagnier on 16/11/16.
 */

public abstract class AbstractEventFragment extends DialogFragment {

    protected DataBaseHelper mHelper;
    protected AbstractEventFragment currentFragment;
    protected DialogFragment goOnButtonEventFragment;
    protected String tagGoOnButtonEventFragment;
    protected boolean enableGoOnEventFragmnent;

    protected EditText editTextLibelle;
    protected EditText editTextDate;
    protected EditText editTextCommentaire;
    protected CheckBox checkBoxNotification;
    protected Button buttonTimePickerEventFragment;
    protected Button buttonEventFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtention du helper
        mHelper = new DataBaseHelper(this.getActivity());
        this.currentFragment = this;
        this.enableGoOnEventFragmnent = true;
    }

    /**
     * initialise les composent non present dans l'abstract
     */
    protected abstract void initComponent(View view);

    /**
     * initialise l'action donner par le bouton EventFragment
     */
    protected abstract void onButtonEventFragmentAction();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_event, container, false);
        this.editTextLibelle = (EditText) v.findViewById(R.id.editTextLibelle);
        this.editTextDate = (EditText) v.findViewById(R.id.editTextDate);
        editTextDate.setText(DateUtils.now());
        this.editTextCommentaire = (EditText) v.findViewById(R.id.editTextCommentaire);
        this.checkBoxNotification = (CheckBox) v.findViewById(R.id.checkBoxNotification);
        this.checkBoxNotification.toggle();
        this.buttonEventFragment = (Button) v.findViewById(R.id.buttonEventFragment);
        this.buttonTimePickerEventFragment = (Button) v.findViewById(R.id.buttonTimePickerEventFragment);
        this.buttonTimePickerEventFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setEditText(editTextDate);
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });
        this.buttonEventFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonEventFragmentAction();
                if(enableGoOnEventFragmnent) {
                    goOnButtonEventFragment.show(getFragmentManager(), tagGoOnButtonEventFragment);
                    getActivity().getFragmentManager().beginTransaction().remove(currentFragment).commit();
                }
            }
        });
        this.initComponent(v);
        return v;
    }

    /**
     * permet de setter le dialog fragment de retour apres l'action du bouton
     * @param goOnButtonEventFragment
     * @param tag
     */
    public void setGoOnButtonEventFragment(DialogFragment goOnButtonEventFragment, String tag) {
        this.goOnButtonEventFragment = goOnButtonEventFragment;
        this.tagGoOnButtonEventFragment = tag;
    }

    /**
     * lance l'intentService de notification (qui est un thread non gere par l'aplication)
     * @param message
     * @param title
     * @param date
     */
    protected void scheduleNotification(String message, String title, int id, String date) throws Exception {
        Long delay = DateUtils.delayToBeNotifiate(date, (this.getActivity()).getApplicationContext());
        Intent intentNotif = new Intent(this.getActivity(), SendNotificationService.class);
        Bundle bundleNotif = new Bundle();

        Notification notification = createNotif(message,title);
        bundleNotif.putParcelable("notification",notification);
        bundleNotif.putInt("id",id);
        bundleNotif.putLong("delay",delay);
        intentNotif.putExtras(bundleNotif);
        this.getActivity().startService(intentNotif);
    }

    protected void cancelNotification(int id){
        Intent intentNotif = new Intent(this.getActivity(), CancelNotificationService.class);
        Bundle bundleNotif = new Bundle();
        bundleNotif.putInt("id",id);
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
}

