package com.s32xlevel.foodtracker.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context,
    DB_NAME, null,
    DB_VERSION
) {

    init {
        instanse = this
    }

    companion object {
        private var instanse: DBHelper? = null

        @Synchronized
        fun getInstance(context: Context) = instanse
            ?: DBHelper(context.applicationContext)

        const val DB_NAME = "tracker.db"
        const val DB_VERSION = 1
    }

    object UserTable {
        const val TABLE_NAME = "users"
        const val ID = "_id"
        const val NAME = "name"
        const val GENDER = "gender"
        const val WEIGHT = "weight"
        const val HEIGHT = "height"
        const val FOOD_COUNT_DAY = "food_count_day" // сколько раз в день нужно есть (ввод)
        const val START_DAY_TIME = "start_day_time" // время подъема (ввод) HH:MM
        const val END_DAY_TIME = "end_day_time" // HH:mm
        const val RATE_WATER = "rate_water" // норма воды (вычисляемое значение)
    }

    object WaterTable {
        const val TABLE_NAME = "water"
        const val ID = "_id"
        const val DATE_TIME = "date_time" // YYYY-MM-DD HH:mm
        const val volume = "volume"
        const val source = "source"
        const val USER_ID = "user_id"
    }

    object FoodTable {
        ////
    }

    // dates: https://github.com/Kotlin/anko/issues/597#issuecomment-377873925
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.createTable(
            UserTable.TABLE_NAME, true,
            UserTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            UserTable.NAME to TEXT + NOT_NULL,
            UserTable.GENDER to TEXT + NOT_NULL,
            UserTable.WEIGHT to INTEGER + NOT_NULL,
            UserTable.HEIGHT to INTEGER + NOT_NULL,
            UserTable.FOOD_COUNT_DAY to INTEGER + NOT_NULL,
            UserTable.START_DAY_TIME to TEXT + NOT_NULL,
            UserTable.END_DAY_TIME to TEXT + NOT_NULL,
            UserTable.RATE_WATER to REAL + NOT_NULL
        )

        db.createTable(
            WaterTable.TABLE_NAME, true,
            WaterTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            WaterTable.DATE_TIME to TEXT + NOT_NULL,
            WaterTable.volume to INTEGER + NOT_NULL,
            WaterTable.source to TEXT + NOT_NULL,
            WaterTable.USER_ID to INTEGER + NOT_NULL,
            FOREIGN_KEY(
                WaterTable.USER_ID,
                UserTable.TABLE_NAME,
                UserTable.ID, ON_DELETE(ConstraintActions.CASCADE)))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}