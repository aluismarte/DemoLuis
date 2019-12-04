package edu.alsjava.courses.demoluis.utils.lifecycle;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import edu.alsjava.courses.demoluis.ui.dialogs.TurnOnBluetoothDialog;
import edu.alsjava.courses.demoluis.utils.BluetoothPrinterManager;

/**
 * Created by aluis on 12/2/19.
 */
public class LifeCycleCheckBluetooth implements LifecycleObserver {

    private Context context;
    private String activityName;

    private TurnOnBluetoothDialog turnOnBluetoothDialog;

    public LifeCycleCheckBluetooth(Context context, String activityName) {
        this.context = context;
        this.activityName = activityName;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        String format = String.format("Start from: %s", activityName);
        Toast.makeText(context, format, Toast.LENGTH_LONG).show();
        Log.i("Test Lifecycle", format);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void checkBluetooth() {
        if (turnOnBluetoothDialog == null) {
            turnOnBluetoothDialog = new TurnOnBluetoothDialog(context);
        }
        // Saco una ventana emergente para avisar que necesito el bluetooth encendido
        if (!BluetoothPrinterManager.get().isEnable()) {
            turnOnBluetoothDialog.show();
        } else {
            turnOnBluetoothDialog.dismiss();
        }
    }


}
