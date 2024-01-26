package com.example.todotry.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface namedao {
    @Insert
    suspend fun insert(data:Second)
    @Update
    suspend fun update(data:Second)
    @Delete
    suspend fun Delete(data:Second)
    @Query("SELECT * FROM name")
    suspend fun getdata():List<Second>

}