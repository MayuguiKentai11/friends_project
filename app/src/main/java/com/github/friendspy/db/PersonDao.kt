package com.github.friendspy.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.friendspy.models.Person

@Dao
interface PersonDao {

    @Insert
    fun insertOne(person : Person)

    @Query("SELECT* FROM person")
    fun getAll(): List<Person>

    @Delete
    fun delete(person: Person)

}