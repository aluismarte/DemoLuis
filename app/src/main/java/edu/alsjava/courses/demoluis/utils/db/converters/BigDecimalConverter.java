package edu.alsjava.courses.demoluis.utils.db.converters;

import androidx.room.TypeConverter;

import java.math.BigDecimal;

/**
 * Created by aluis on 12/4/19.
 */
public class BigDecimalConverter {

    @TypeConverter
    public String from(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @TypeConverter
    public BigDecimal to(String data) {
        if (data == null) {
            return null;
        }
        return new BigDecimal(data);
    }
}
