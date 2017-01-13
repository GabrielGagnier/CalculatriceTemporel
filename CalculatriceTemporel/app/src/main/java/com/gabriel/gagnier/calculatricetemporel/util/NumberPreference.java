package com.gabriel.gagnier.calculatricetemporel.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by gagnier on 13/01/17.
 */

public class NumberPreference extends DialogPreference {
    private String number;
    private EditText picker = null;

    public NumberPreference(Context ctxt) {
        this(ctxt, null);
    }

    public NumberPreference(Context ctxt, AttributeSet attrs) {
        this(ctxt, attrs, android.R.attr.dialogPreferenceStyle);
    }

    public NumberPreference(Context ctxt, AttributeSet attrs, int defStyle) {
        super(ctxt, attrs, defStyle);
    }

    @Override
    protected View onCreateDialogView() {
        picker = new EditText(getContext());
        picker.setInputType(InputType.TYPE_CLASS_NUMBER);
        return (picker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        picker.setText(number);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            number = picker.getText().toString();
            setSummary(getSummary());
            if (callChangeListener(number)) {
                persistString(number);
                notifyChanged();
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return (a.getString(index));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {

        if (restoreValue) {
            if (defaultValue == null) {
                number = getPersistedString("1");
            } else {
                number = getPersistedString((String) defaultValue);
            }
        } else {
            if (defaultValue == null) {
                number = "1";
            } else {
                number =(String) defaultValue;
            }
        }
        setSummary(getSummary());
    }

    @Override
    public CharSequence getSummary() {
        if (number == null) {
            return null;
        }
        return number;
    }
}
