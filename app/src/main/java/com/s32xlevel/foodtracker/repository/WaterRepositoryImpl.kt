package com.s32xlevel.foodtracker.repository

import android.content.Context
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.model.Water
import org.jetbrains.anko.db.*
import java.sql.Date

class WaterRepositoryImpl(private val context: Context) : WaterRepository {
    private val Context.database: DBHelper
        get() = DBHelper.getInstance(context.applicationContext)

    override fun findAllByDateTime(userId: Int, dateTime: String): List<Water> = context.database.use {
        val waters = select(DBHelper.WaterTable.TABLE_NAME)
            .whereArgs("user_id = $userId AND date_time = $dateTime")
            .exec { parseList(classParser<Water>()) }

        waters
    }
    override fun create(water: Water, userId: Int) {
        val db = context.database.writableDatabase
        if (userId == AuthorizedUser.id) {
            db.insert(DBHelper.WaterTable.TABLE_NAME,
                DBHelper.WaterTable.DATE_TIME to water.dateTime,
                DBHelper.WaterTable.volume to water.volume,
                DBHelper.WaterTable.USER_ID to userId)
        }
    }

    override fun delete(id: Int, userId: Int): Unit = context.database.use {
        if (userId == AuthorizedUser.id) {
            delete(DBHelper.WaterTable.TABLE_NAME, "id = $id AND user_id = $userId")
        }
    }
}