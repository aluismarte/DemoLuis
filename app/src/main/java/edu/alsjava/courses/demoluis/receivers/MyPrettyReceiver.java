package edu.alsjava.courses.demoluis.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by aluis on 12/10/19.
 */
public class MyPrettyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean state = intent.getBooleanExtra("state", false);
        Toast.makeText(context, "Network?: " + state, Toast.LENGTH_LONG).show();
    }
}
