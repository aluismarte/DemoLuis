package edu.alsjava.courses.demoluis.utils.lifecycle;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by aluis on 12/2/19.
 */
public class LifeCycleDemo implements LifecycleObserver {

    private Context context;
    private String activityName;

    public LifeCycleDemo(Context context, String activityName) {
        this.context = context;
        this.activityName = activityName;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        String format = String.format("Start from: %s", activityName);
        Toast.makeText(context, format, Toast.LENGTH_LONG).show();
        Log.i("Test Lifecycle", format);
    }

}
