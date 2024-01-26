package com.example.todotry.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface notcompletetableDao {
    @Insert
    suspend fun insertiask(data:notcompletetable)
    @Update
    suspend fun updatetiask(data:notcompletetable)
    @Delete
    suspend fun deletetiask(data:notcompletetable)

    @Query("SELECT * FROM notcompletetable")
    fun getdata(): List<notcompletetable>

}