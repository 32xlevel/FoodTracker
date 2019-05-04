package com.s32xlevel.foodtracker.repository

import android.content.Context
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.model.Water
import org.jetbrains.anko.db.*
import java.sql.Date
import java.util.*

class WaterRepositoryImpl(private val context: Context) : WaterRepository {
    private val Context.database: DBHelper
        get() = DBHelper.getInstance(context.applicationContext)

    override fun findAllByDate(userId: Int, date: String): List<Water> = context.database.use {
        val waters = select(DBHelper.WaterTable.TABLE_NAME)
            .whereArgs(
                "${DBHelper.WaterTable.USER_ID} = {userId} AND ${DBHelper.WaterTable.DATE} = {date}",
                "userId" to userId, "date" to date
            )
            .exec { parseList(classParser<Water>()) }

        if (waters.isNotEmpty()) {
            waters
        } else {
            Collections.emptyList()
        }
    }

    override fun findAll(): List<Water> = context.database.use {
        val waters = select(DBHelper.WaterTable.TABLE_NAME).exec { parseList(classParser<Water>()) }
        waters
    }

    override fun create(water: Water, userId: Int) {
        val db = context.database.writableDatabase
        if (userId == AuthorizedUser.id) {
            db.insert(
                DBHelper.WaterTable.TABLE_NAME,
                DBHelper.WaterTable.DATE to water.date,
                DBHelper.WaterTable.TIME to water.time,
                DBHelper.WaterTable.volume to water.volume,
                DBHelper.WaterTable.source to water.source,
                DBHelper.WaterTable.USER_ID to userId
            )
        }
    }

    override fun delete(id: Int, userId: Int): Unit = context.database.use {
        if (userId == AuthorizedUser.id) {
            delete(DBHelper.WaterTable.TABLE_NAME, "_id = $id AND user_id = $userId")
        }
    }
}