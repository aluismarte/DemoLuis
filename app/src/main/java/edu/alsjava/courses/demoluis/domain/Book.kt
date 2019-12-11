package edu.alsjava.courses.demoluis.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import edu.alsjava.courses.demoluis.model.BookType
import edu.alsjava.courses.demoluis.utils.db.converters.BigDecimalConverter
import edu.alsjava.courses.demoluis.utils.db.converters.BookTypeConverter
import java.math.BigDecimal

/**
 * Created by aluis on 12/4/19.
 */
@Entity(tableName = "books") // Always table name in plural
class Book {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var name: String? = null

    var description: String? = null

    @TypeConverters(BookTypeConverter::class)
    var bookType: BookType = BookType.NONE

    @TypeConverters(BigDecimalConverter::class)
    var price: BigDecimal? = null

    @Relation(parentColumn = "id", entityColumn = "author", entity = Author::class)
    var author: Author? = null // Tengo una relacion opcional, puede ser null
}