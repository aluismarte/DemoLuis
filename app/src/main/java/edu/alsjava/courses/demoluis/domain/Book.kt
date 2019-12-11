package edu.alsjava.courses.demoluis.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import edu.alsjava.courses.demoluis.model.BookType
import edu.alsjava.courses.demoluis.utils.db.converters.BigDecimalConverter
import edu.alsjava.courses.demoluis.utils.db.converters.BookTypeConverter
import java.math.BigDecimal

/**
 * Created by aluis on 12/4/19.
 */
@Entity(tableName = "books", foreignKeys = [ForeignKey(entity = Author::class, parentColumns = ["id"], childColumns = ["author"])])
class Book {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var name: String? = null

    var description: String? = null

    @TypeConverters(BookTypeConverter::class)
    var bookType: BookType = BookType.NONE

    @TypeConverters(BigDecimalConverter::class)
    var price: BigDecimal? = null

    var author: Long? = null
}