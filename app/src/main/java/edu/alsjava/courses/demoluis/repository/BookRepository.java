package edu.alsjava.courses.demoluis.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.alsjava.courses.demoluis.domain.Author;
import edu.alsjava.courses.demoluis.domain.Book;

/**
 * Created by aluis on 12/4/19.
 */
@Dao
public interface BookRepository {

    @Query("SELECT COUNT(id) FROM books")
    int countAll();

    @Query("SELECT * FROM books WHERE id = :id")
    Book findById(Long id);

    @Query("SELECT * FROM books WHERE author = :author")
    List<Book> findAllByAuthor(Long author);

    @Query("SELECT * FROM books LIMIT :limit,:offset")
    List<Book> findAllBy(int limit, int offset);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book... books);

    @Update
    void update(Book book);

    @Update
    void update(List<Book> books);

    @Delete
    void delete(Book... books);
}
