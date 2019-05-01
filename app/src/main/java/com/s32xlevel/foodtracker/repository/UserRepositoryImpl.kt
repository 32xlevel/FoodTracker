package com.s32xlevel.foodtracker.repository

import android.content.Context
import com.s32xlevel.foodtracker.model.User
import org.jetbrains.anko.db.*
import java.sql.Time
import java.time.LocalTime

class UserRepositoryImpl(private val context: Context): UserRepository {
    private val Context.database: DBHelper
        get() = DBHelper.getInstance(context.applicationContext)

    override fun findAll(): List<User> = context.database.use {
        val users = ArrayList<User>()
        select(DBHelper.UserTable.TABLE_NAME, DBHelper.UserTable.ID, DBHelper.UserTable.NAME,
            DBHelper.UserTable.GENDER, DBHelper.UserTable.WEIGHT, DBHelper.UserTable.HEIGHT,
            DBHelper.UserTable.START_DAY_TIME, DBHelper.UserTable.END_DAY_TIME, DBHelper.UserTable.RATE_WATER).orderBy("_id").parseList(object : MapRowParser<List<User>> {
            override fun parseRow(columns: Map<String, Any?>): List<User> {
                val id = columns.getValue(DBHelper.UserTable.ID)
                val name = columns.getValue(DBHelper.UserTable.ID)
                val gender = columns.getValue(DBHelper.UserTable.ID)
                val weight = columns.getValue(DBHelper.UserTable.ID)
                val height = columns.getValue(DBHelper.UserTable.ID)
                val startDayTime = Time.valueOf(columns.getValue(DBHelper.UserTable.ID).toString())
                val endDayTime = Time.valueOf(columns.getValue(DBHelper.UserTable.ID).toString())
                val rateWater = columns.getValue(DBHelper.UserTable.ID)
                val foodNumber = columns.getValue(DBHelper.UserTable.ID)

                val user = User(id.toString().toInt(), name.toString(), gender.toString(),
                    weight.toString().toInt(), height.toString().toInt(), rateWater.toString().toInt(),
                    foodNumber.toString().toByte(), startDayTime, endDayTime)
                users.add(user)
                return users
            }
        })
        users
    }

    override fun findById(id: Int): User = context.database.use {
        val user = User(null, null, null, null, null, null, null, null, null)
        select(DBHelper.UserTable.TABLE_NAME).whereSimple("_id = ?", id.toString())
            .parseOpt(object : MapRowParser<User> {
                override fun parseRow(columns: Map<String, Any?>): User {
                    val id = columns.getValue(DBHelper.UserTable.ID).toString().toInt()
                    val name = columns.getValue(DBHelper.UserTable.ID).toString()
                    val gender = columns.getValue(DBHelper.UserTable.ID).toString()
                    val weight = columns.getValue(DBHelper.UserTable.ID).toString().toInt()
                    val height = columns.getValue(DBHelper.UserTable.ID).toString().toInt()
                    val startDayTime = Time.valueOf(columns.getValue(DBHelper.UserTable.ID).toString())
                    val endDayTime = Time.valueOf(columns.getValue(DBHelper.UserTable.ID).toString())
                    val rateWater = columns.getValue(DBHelper.UserTable.ID).toString().toInt()
                    val foodNumber = columns.getValue(DBHelper.UserTable.ID).toString().toByte()
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
        user
    }

    override fun delete(id: Int): Unit = context.database.use {
        delete(DBHelper.UserTable.TABLE_NAME, "id = ?", arrayOf(id.toString()))
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