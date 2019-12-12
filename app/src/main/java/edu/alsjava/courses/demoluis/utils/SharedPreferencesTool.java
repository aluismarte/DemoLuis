package edu.alsjava.courses.demoluis.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Elemento encargado de manejar las prefencias internas de la aplicacion
 * <p>
 * Created by aluis on 11/20/19.
 */
public class SharedPreferencesTool {

    private static final String FIRST_RUN = "FIRST_RUN";
    private static final String POLICY = "POLICY";
    private static final String SESSION = "SESSION";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesTool(Context context) {
        sharedPreferences = context.getSharedPreferences("demoluis", Context.MODE_PRIVATE);
    }

    public boolean showIntro() {
        return sharedPreferences.getBoolean(FIRST_RUN, true);
    }

    public void showIntro(boolean show) {
        sharedPreferences.edit().putBoolean(FIRST_RUN, show).apply();
    }

    public boolean showPolicy() {
        return sharedPreferences.getBoolean(POLICY, true);
    }

    public void showPolicy(boolean show) {
        sharedPreferences.edit().putBoolean(POLICY, show).apply();
    }

    public String getSession() {
        return sharedPreferences.getString(SESSION, "");
    }

    public void setSession(String session) {
        sharedPreferences.edit().putString(SESSION, session).apply();
    }
}
