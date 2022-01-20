package com.example.mvi_design_patern.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheDto: BlogCacheDto): Long

    @Query("SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheDto>
}