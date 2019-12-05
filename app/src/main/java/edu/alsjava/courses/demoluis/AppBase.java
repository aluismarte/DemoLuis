package edu.alsjava.courses.demoluis;

import android.app.Application;

import androidx.room.Room;

import edu.alsjava.courses.demoluis.utils.BluetoothPrinterManager;
import edu.alsjava.courses.demoluis.utils.Constants;
import edu.alsjava.courses.demoluis.utils.db.DemoLuisDB;
import edu.alsjava.courses.demoluis.utils.db.migrations.Version2Migration;

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
        Constants.get();
        Constants.get().demoLuisDB = Room.databaseBuilder(this, DemoLuisDB.class, DemoLuisDB.DB_NAME)
                .addMigrations(Version2Migration.migrationVersion2)
                .build();
    }

}
