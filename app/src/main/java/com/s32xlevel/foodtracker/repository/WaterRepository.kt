package com.s32xlevel.foodtracker.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.s32xlevel.foodtracker.model.Water
import java.time.LocalDateTime

@Dao
interface WaterRepository {
    @Query("SELECT * FROM water WHERE water.user_id = :userId AND water.date_time = :dateTime")
    fun findAllByDateTime(userId: Int, dateTime: LocalDateTime): List<Water>

    @Insert
    fun create(water: Water)

    @Delete
    fun delete(water: Water)//, userId:Int)
}