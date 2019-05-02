package com.s32xlevel.foodtracker.repository

import com.s32xlevel.foodtracker.model.User

interface UserRepository {
    fun findAll(): List<User>

    fun findById(id: Int): User?

    fun save(user: User): User

    fun delete(id: Int)
}