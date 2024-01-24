package com.example.todotry.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todotask")
data class TODOTaskTable(
    @PrimaryKey(autoGenerate = true) var id :Long=0,val title:String,val descripatation:String,val icon:Int ,val time:String,val Date:String)
