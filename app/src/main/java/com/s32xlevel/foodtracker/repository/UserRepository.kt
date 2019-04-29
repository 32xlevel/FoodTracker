package com.s32xlevel.foodtracker.repository

import androidx.room.*
import com.s32xlevel.foodtracker.model.User

@Dao
interface UserRepository {
    @Query("SELECT * FROM users")
    fun findAll(): List<User>

    @Query("SELECT * FROM users WHERE users.id = :id")
    fun findById(id: Int): User

    @Insert
    fun create(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}