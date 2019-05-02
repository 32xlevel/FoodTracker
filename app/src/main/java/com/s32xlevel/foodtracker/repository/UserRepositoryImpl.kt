package com.s32xlevel.foodtracker.repository

import android.content.Context
import android.database.sqlite.SQLiteException
import com.s32xlevel.foodtracker.model.User
import org.jetbrains.anko.db.*
import java.sql.Time
import java.time.LocalTime

class UserRepositoryImpl(val context: Context): UserRepository {
    private val Context.database: DBHelper
        get() = DBHelper.getInstance(context.applicationContext)

    override fun findAll(): List<User> = context.database.use {
        val users = ArrayList<User>()
        select(DBHelper.UserTable.TABLE_NAME, DBHelper.UserTable.ID, DBHelper.UserTable.NAME,
            DBHelper.UserTable.GENDER, DBHelper.UserTable.WEIGHT, DBHelper.UserTable.HEIGHT,
            DBHelper.UserTable.START_DAY_TIME, DBHelper.UserTable.END_DAY_TIME, DBHelper.UserTable.RATE_WATER).exec {
            parseList(object : MapRowParser<List<User>> {
                override fun parseRow(columns: Map<String, Any?>): List<User> {
                    val id = columns.getValue(DBHelper.UserTable.ID).toString().toInt()
                    val name = columns.getValue(DBHelper.UserTable.NAME).toString()
                    val gender = columns.getValue(DBHelper.UserTable.GENDER).toString()
                    val weight = columns.getValue(DBHelper.UserTable.WEIGHT).toString().toInt()
                    val height = columns.getValue(DBHelper.UserTable.HEIGHT).toString().toInt()
                    val startDayTime = columns.getValue(DBHelper.UserTable.START_DAY_TIME).toString()
                    val endDayTime = columns.getValue(DBHelper.UserTable.END_DAY_TIME).toString()
                    val rateWater = columns.getValue(DBHelper.UserTable.RATE_WATER).toString().toDouble()
                    val foodNumber = columns.getValue(DBHelper.UserTable.FOOD_COUNT_DAY).toString().toByte()

                    val user = User(id.toString().toInt(), name, gender, weight, height, rateWater, foodNumber, startDayTime, endDayTime)
                    users.add(user)
                    return users
                }
            })
        }
        users
    }

    override fun findById(id: Int): User? = context.database.use {
        val user = User(null, null, null, null, null, null, null, null, null)
        select(DBHelper.UserTable.TABLE_NAME).whereSimple("_id = $id")
            .parseOpt(object : MapRowParser<User> {
                override fun parseRow(columns: Map<String, Any?>): User {
                    val id = columns.getValue(DBHelper.UserTable.ID).toString().toInt()
                    val name = columns.getValue(DBHelper.UserTable.NAME).toString()
                    val gender = columns.getValue(DBHelper.UserTable.GENDER).toString()
                    val weight = columns.getValue(DBHelper.UserTable.WEIGHT).toString().toInt()
                    val height = columns.getValue(DBHelper.UserTable.HEIGHT).toString().toInt()
                    val startDayTime = columns.getValue(DBHelper.UserTable.START_DAY_TIME).toString()
                    val endDayTime = columns.getValue(DBHelper.UserTable.END_DAY_TIME).toString()
                    val rateWater = columns.getValue(DBHelper.UserTable.RATE_WATER).toString().toDouble()
                    val foodNumber = columns.getValue(DBHelper.UserTable.FOOD_COUNT_DAY).toString().toByte()
                    user.id = id
                    user.name = name
                    user.gender = gender
                    user.weight = weight
                    user.height = height
                    user.startDayTime = startDayTime
                    user.endDayTime = endDayTime
                    user.rateWater = rateWater
                    user.foodNumber = foodNumber

                    return user
                }
            })
        if (user.id == null) {
            null
        } else {
            user
        }
    }

    override fun delete(id: Int): Unit = context.database.use {
        delete(DBHelper.UserTable.TABLE_NAME, "_id = $id")
    }

    override fun save(user: User): User {
        val db = context.database.writableDatabase
        if (user.id == null) {
            user.id = db.insert(DBHelper.UserTable.TABLE_NAME,
                DBHelper.UserTable.NAME to user.name, DBHelper.UserTable.GENDER to user.gender,
                DBHelper.UserTable.WEIGHT to user.weight, DBHelper.UserTable.HEIGHT to user.height,
                DBHelper.UserTable.START_DAY_TIME to user.startDayTime, DBHelper.UserTable.END_DAY_TIME to user.endDayTime,
                DBHelper.UserTable.RATE_WATER to user.rateWater, DBHelper.UserTable.FOOD_COUNT_DAY to user.foodNumber).toInt()
        } else {
            db.update(DBHelper.UserTable.TABLE_NAME,
                DBHelper.UserTable.NAME to user.name, DBHelper.UserTable.GENDER to user.gender,
                DBHelper.UserTable.WEIGHT to user.weight, DBHelper.UserTable.HEIGHT to user.height,
                DBHelper.UserTable.START_DAY_TIME to user.startDayTime, DBHelper.UserTable.END_DAY_TIME to user.endDayTime,
                DBHelper.UserTable.RATE_WATER to user.rateWater, DBHelper.UserTable.FOOD_COUNT_DAY to user.foodNumber)
                .whereSimple("${DBHelper.UserTable.ID} = ${user.id}")
                .exec()
        }
        db.close()
        return user
    }
}