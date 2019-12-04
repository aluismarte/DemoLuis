package edu.alsjava.courses.demoluis.ui.dialogs;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import edu.alsjava.courses.demoluis.R;

/**
 * Created by aluis on 12/3/19.
 */
public class TurnOnBluetoothDialog extends Dialog {

    public TurnOnBluetoothDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_turn_on_bluetooth);
        setCancelable(false);
    }
}
