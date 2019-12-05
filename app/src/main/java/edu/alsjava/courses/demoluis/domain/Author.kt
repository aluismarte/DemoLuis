package edu.alsjava.courses.demoluis.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by aluis on 12/4/19.
 */
@Entity(tableName = "authors")
class Author {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var name: String? = null

    var description: String? = null
}