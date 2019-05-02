package com.s32xlevel.foodtracker.repository

import com.s32xlevel.foodtracker.model.Water
import java.sql.Date

interface WaterRepository {
    fun findAllByDateTime(userId: Int, dateTime: String): List<Water>

    fun create(water: Water, userId: Int)

    fun delete(id: Int, userId: Int)
}