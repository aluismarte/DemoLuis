package edu.alsjava.courses.demoluis.utils.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import edu.alsjava.courses.demoluis.domain.Author;
import edu.alsjava.courses.demoluis.domain.Book;
import edu.alsjava.courses.demoluis.repository.AuthorRepository;
import edu.alsjava.courses.demoluis.repository.BookRepository;

/**
 * Created by aluis on 12/4/19.
 */
@Database(entities = {Book.class, Author.class}, version = 2, exportSchema = false)
public abstract class DemoLuisDB extends RoomDatabase {

    public static final String DB_NAME = "DEMO_LUIS_DB";

    public abstract AuthorRepository getAuthorRepository();

    public abstract BookRepository getBookRepository();
}
