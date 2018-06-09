package com.naturecurly.split.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                  @ColumnInfo(name = "name") var name: String,
                  @ColumnInfo(name = "background") var backgroundColor: Int)