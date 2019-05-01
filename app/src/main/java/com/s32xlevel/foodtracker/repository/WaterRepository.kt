package com.s32xlevel.foodtracker.repository

import com.s32xlevel.foodtracker.model.Water
import java.time.LocalDateTime

interface WaterRepository {
    fun findAllByDateTime(userId: Int, dateTime: LocalDateTime): List<Water>

    fun create(water: Water, userId: Int)

    fun delete(water: Water, userId: Int)
}