package com.example.todotry.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TODOTaskTable::class], version = 1)
abstract class TODOdatabase:RoomDatabase() {
    abstract fun gettoDAO():TODoDAO


}