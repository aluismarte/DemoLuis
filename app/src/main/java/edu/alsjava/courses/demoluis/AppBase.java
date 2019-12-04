package edu.alsjava.courses.demoluis;

import android.app.Application;

import edu.alsjava.courses.demoluis.utils.BluetoothPrinterManager;

/**
 * Created by aluis on 12/3/19.
 */
public class AppBase extends Application {

    public static AppBase appBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appBase = this;
        BluetoothPrinterManager.get();
    }

}
