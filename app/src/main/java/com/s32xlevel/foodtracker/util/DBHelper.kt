package com.s32xlevel.foodtracker.util

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.s32xlevel.foodtracker.model.User
import com.s32xlevel.foodtracker.model.Water
import com.s32xlevel.foodtracker.repository.UserRepository
import com.s32xlevel.foodtracker.repository.WaterRepository

@Database(entities = [User::class, Water::class], version = 1)
abstract class DBHelper: RoomDatabase() {
    abstract var userRepository: UserRepository?
    abstract var waterRepository: WaterRepository?
}