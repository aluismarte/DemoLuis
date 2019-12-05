package edu.alsjava.courses.demoluis.utils;

import edu.alsjava.courses.demoluis.utils.db.DemoLuisDB;

/**
 * Created by aluis on 12/4/19.
 */
public class Constants {

    private static volatile Constants instance = null;

    public DemoLuisDB demoLuisDB;

    private Constants() {
    }

    public static Constants get() {
        Constants result = instance;
        if (result == null) {
            synchronized (Constants.class) {
                if (instance == null) {
                    instance = result = new Constants();
                }
            }
        }
        return result;
    }
}
