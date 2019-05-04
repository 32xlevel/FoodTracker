package com.s32xlevel.foodtracker.repository

import com.s32xlevel.foodtracker.model.Water
import java.sql.Date

interface WaterRepository {
    fun findAllByDate(userId: Int, date: String): List<Water>

    fun findAll(): List<Water>

    fun create(water: Water, userId: Int)

    fun delete(id: Int, userId: Int)
}