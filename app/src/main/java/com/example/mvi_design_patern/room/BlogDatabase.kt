package com.example.mvi_design_patern.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheDto::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        val DATABASE_NAME: String = "blog_db"
    }
}