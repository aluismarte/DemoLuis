package edu.alsjava.courses.demoluis.model;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by aluis on 11/27/19.
 */
public class Seasons { // Siempre uso el plural aqui

    public static final int WINTER = 0;
    public static final int SPRING = 1;
    public static final int SUMMER = 2;
    public static final int FALL = 3;

    @IntDef({WINTER, SPRING, SUMMER, FALL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Season {} // esta parte siempre va en singular
}
