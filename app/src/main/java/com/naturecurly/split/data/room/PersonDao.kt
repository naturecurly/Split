package com.naturecurly.split.data.room

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface PersonDao {
    @Query("SELECT * FROM people")
    fun getAllPeople(): Maybe<List<Person>>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(vararg person: Person)

    @Delete
    fun delete(person: Person)

    @Query("DELETE FROM people WHERE id = :id")
    fun deleteById(id: Long)
}