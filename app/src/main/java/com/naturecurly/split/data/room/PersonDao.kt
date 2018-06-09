package com.naturecurly.split.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe

@Dao
interface PersonDao {
    @Query("SELECT * FROM people")
    fun getAllPeople(): Maybe<List<Person>>

    @Insert
    fun insert(vararg person: Person)

    @Delete
    fun delete(person: Person)
}