package com.example.todotry.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TODOTaskTable::class,Second::class,notcompletetable::class], version = 3)
abstract class TODOdatabase:RoomDatabase() {
    abstract fun gettoDAO():TODoDAO
    abstract fun fetseconddao():namedao

abstract fun notcompletedao():notcompletetableDao
}