package com.s32xlevel.foodtracker.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.s32xlevel.foodtracker.model.Food
import java.time.LocalDateTime

@Dao
interface FoodRepository {
    @Query("SELECT * FROM foods WHERE foods.user_id = :userId AND foods.date_time = :dateTime")
    fun findAll(userId: Int, dateTime: LocalDateTime)

    @Insert
    fun create(food: Food)
}