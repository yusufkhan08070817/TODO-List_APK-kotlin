package com.example.todotry.OBJ

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todotry.Room.TODOdatabase

object DataBaseOBJ {
  lateinit var database:TODOdatabase
    fun initialize(application: Context){
        database= Room.databaseBuilder(application,TODOdatabase::class.java,"TOdatabase").build()
    }
}