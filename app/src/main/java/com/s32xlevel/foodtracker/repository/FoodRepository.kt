package com.s32xlevel.foodtracker.repository

import com.s32xlevel.foodtracker.model.Food

interface FoodRepository {
    fun findAll(userId: Int, date: String): List<Food>

    fun save(food: Food, userId: Int)

    fun findByTypeId(typeId: Int, date: String): Food?
}