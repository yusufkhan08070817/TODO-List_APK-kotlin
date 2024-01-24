package com.example.todotry.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface TODoDAO {
    @Insert
    suspend fun insertiask(data:TODOTaskTable)
    @Update
    suspend fun updatetiask(data:TODOTaskTable)
    @Delete
    suspend fun deletetiask(data:TODOTaskTable)

    @Query("SELECT * FROM todotask")
    fun getdata():LiveData<List<TODOTaskTable>>

    @Query("SELECT * FROM todotask WHERE Date = :date")
    fun getRecordsByDate(date: String): LiveData<List<TODOTaskTable>>

    @Query("SELECT * FROM todotask WHERE title = :title")
    fun getRecordsBytitle(title: String): LiveData<List<TODOTaskTable>>
}