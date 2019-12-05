package edu.alsjava.courses.demoluis.utils.db.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by aluis on 12/4/19.
 */
public class Version2Migration {

    public static final Migration migrationVersion2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Lamentablemente aqui va todo en SQL normal
            database.beginTransaction();
            database.execSQL("ALTER TABLE authors ADD description text");
            database.endTransaction();
        }
    };
}
