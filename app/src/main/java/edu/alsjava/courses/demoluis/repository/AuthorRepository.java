package edu.alsjava.courses.demoluis.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.alsjava.courses.demoluis.domain.Author;

/**
 * Created by aluis on 12/4/19.
 */
@Dao
public interface AuthorRepository {

    @Query("SELECT * FROM authors WHERE name = :name")
    List<Author> findAllByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Author... author);

    @Update
    void update(Author author);

    @Update
    void update(List<Author> authors);

    @Delete
    void delete(Author... author);
}
