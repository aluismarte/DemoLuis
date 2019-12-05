package edu.alsjava.courses.demoluis.utils.db.converters;

import androidx.room.TypeConverter;

import edu.alsjava.courses.demoluis.model.BookType;

/**
 * Created by aluis on 12/4/19.
 */
public class BookTypeConverter {

    @TypeConverter
    public Integer from(BookType bookType) {
        if (bookType == null) {
            return 1;
        }
        switch (bookType) {
            case NONE:
                return 1;
            case HORROR:
                return 2;
            case TERROR:
                return 3;
            case THRILER:
                return 4;
        }
        return null;
    }

    @TypeConverter
    public BookType to(Integer data) {
        if (data == null) {
            return BookType.NONE;
        }
        switch (data) {
            case 1:
                return BookType.NONE;
            case 2:
                return BookType.HORROR;
            case 3:
                return BookType.TERROR;
            case 4:
                return BookType.THRILER;
        }
        return null;
    }
}
