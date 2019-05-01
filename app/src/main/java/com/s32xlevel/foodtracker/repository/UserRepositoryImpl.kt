package com.s32xlevel.foodtracker.repository

import android.content.Context
import android.database.sqlite.SQLiteException
import com.s32xlevel.foodtracker.model.User
import org.jetbrains.anko.db.*
import java.sql.Time
import java.time.LocalTime

class UserRepositoryImpl(private val context: Context): UserRepository {
    private val Context.database: DBHelper
        get() = DBHelper.getInstance(context.applicationContext)

    override fun findAll(): List<User> = context.database.use {
        select(DBHelper.UserTable.TABLE_NAME).exec { parseList(classParser()) }
    }

    override fun findById(id: Int): User? = context.database.use {
        try {
            select(DBHelper.UserTable.TABLE_NAME).whereSimple("id = $id").exec { parseOpt(classParser()) }
        } catch (e: SQLiteException) {
            null
        }
    }

    override fun delete(id: Int): Unit = context.database.use {
        delete(DBHelper.UserTable.TABLE_NAME, "id = $id")
    }

    override fun save(user: User) {
        val db = context.database.writableDatabase
        if (user.id == null) {
            db.insert(DBHelper.UserTable.TABLE_NAME,
                DBHelper.UserTable.NAME to user.name, DBHelper.UserTable.GENDER to user.gender,
                DBHelper.UserTable.WEIGHT to user.weight, DBHelper.UserTable.HEIGHT to user.height,
                DBHelper.UserTable.START_DAY_TIME to user.startDayTime, DBHelper.UserTable.END_DAY_TIME to user.endDayTime,
                DBHelper.UserTable.RATE_WATER to user.rateWater, DBHelper.UserTable.FOOD_COUNT_DAY to user.foodNumber)
        } else {
            db.update(DBHelper.UserTable.TABLE_NAME,
                DBHelper.UserTable.NAME to user.name, DBHelper.UserTable.GENDER to user.gender,
                DBHelper.UserTable.WEIGHT to user.weight, DBHelper.UserTable.HEIGHT to user.height,
                DBHelper.UserTable.START_DAY_TIME to user.startDayTime, DBHelper.UserTable.END_DAY_TIME to user.endDayTime,
                DBHelper.UserTable.RATE_WATER to user.rateWater, DBHelper.UserTable.FOOD_COUNT_DAY to user.foodNumber)
                .whereSimple("_id = ?", user.id.toString())
                .exec()
        }
    }
}